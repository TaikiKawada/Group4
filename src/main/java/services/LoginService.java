package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import DTO.AccountDto;
import utils.Db;
import utils.PasswordUtils;
import utils.Validator;

public class LoginService {
	
	//ログイン要求のメイン処理
    public boolean authenticate(HttpServletRequest request) {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        
        // 入力検証
        if (!validateInputs(request, mail, password)) {
            return false;
        }

        // 認証処理
        return authenticateUser(request, mail, password);
    }

    //入力されたデータが、正しい形式に合致しているかチェックし、エラーを防止
    private boolean validateInputs(HttpServletRequest request, String mail, String password) {
        boolean isValid = true;
        
        // 1-1 メールアドレス必須入力チェック
        if (Validator.isNullOrEmpty(mail)) {
            request.setAttribute("emailError", "メールアドレスが未入力です。");
            isValid = false;
        }
        
        // 1-2 メールアドレスの長さチェック
        else if (!Validator.isValidMail(mail)) {
            request.setAttribute("emailError", "メールアドレスが長すぎます。");
            isValid = false;
        }
        
        // 1-3 メールアドレスの形式チェック
        else if (!Validator.isValidEmail(mail)) {
            request.setAttribute("emailError", "メールアドレスを正しく入力してください。");
            isValid = false;
        }
        
        // 1-4 パスワード必須入力チェック
        if (Validator.isNullOrEmpty(password)) {
        	request.setAttribute("passwordError", "パスワードが未入力です。");
        	isValid = false;
        }
  
        // 1-5 パスワードの長さチェック
        else if (!Validator.isValidPasswordLength(password)) {
            request.setAttribute("passwordError", "パスワードが長すぎます。");
            isValid = false;
        }

        return isValid;
    }    

    // DBでユーザー認証
    private boolean authenticateUser(HttpServletRequest request, String mail, String password) {
    	try(Connection conn = Db.getConnection()){
            String sql = "SELECT name, mail, password, authority FROM accounts WHERE mail = ?";
            
            try(PreparedStatement ps = conn.prepareStatement(sql)){
            	ps.setString(1,mail);
            	
            	try(ResultSet rs = ps.executeQuery()){
            		if(rs.next()) {
            			String storedHash = rs.getString("password");//パスワードのハッシュ値を取得

                    if (PasswordUtils.matches(password, storedHash)) {//パスワードの照合
                        HttpSession session = request.getSession();//ログイン成功処理(セッション保存)
                        
                        AccountDto account = new AccountDto();
                        account.setName(rs.getString("name"));
                        account.setMail(rs.getString("mail"));
                        account.setAuth(rs.getInt("authority"));

                        session.setAttribute("user", account);
                        return true;
                    }
            	}
    		}       
         }
     } catch (SQLException | ClassNotFoundException e) {
    	 Validator.setSystemError(request);
         return false;
        }
    	
    	Validator.setAuthenticationFailed(request);
        return false;
    }
}