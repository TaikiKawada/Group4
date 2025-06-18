package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import dto.AccountDto;
import utils.Db;
import utils.PasswordUtils;

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

    //入力されたデータが、正しい形式に合致しているかチェックし、エラーを防止するメソッド
    private boolean validateInputs(HttpServletRequest request, String email, String password) {
        boolean isValid = true;
        
        // 1-1 メールアドレス必須入力チェック
        if (email == null || email.isEmpty()) {
            request.setAttribute("emailError", "メールアドレスが未入力です。");
            isValid = false;
        }
        
        // 1-2 メールアドレスの長さチェック
        else if (!isValidMailLength(email)) {
            request.setAttribute("emailError", "メールアドレスが長すぎます。");
            isValid = false;
        }
        
        // 1-3 メールアドレスの形式チェック
        else if (!isValidMail(email)) {
            request.setAttribute("emailError", "メールアドレスを正しく入力してください。");
            isValid = false;
        }
        
        // 1-4 パスワード必須入力チェック
        if (password == null || password.isEmpty()) {
        	request.setAttribute("passwordError", "パスワードが未入力です。");
        	isValid = false;
        }
  
        // 1-5 パスワードの長さチェック
        else if (!isValidPasswordLength(password)) {
            request.setAttribute("passwordError", "パスワードが長すぎます。");
            isValid = false;
        }

        return isValid;
    }

    // メールアドレス形式チェックメソッド
    private boolean isValidMail(String email) {
    	if(email == null)return false;
        String mailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(mailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
   
    //メールアドレス長さチェックメソッド
    private boolean isValidMailLength(String email) {
        if (email == null) return false;
        try {
            int byteLength = email.getBytes("UTF-8").length;
            return byteLength <= 101;
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //パスワード長さチェックメソッド
    private boolean isValidPasswordLength(String password) {
    	 if (password == null) return false;

    	    try {
    	        // パスワードのバイト長を取得（UTF-8エンコード想定）
    	        int byteLength = password.getBytes("UTF-8").length;

    	        // 1文字以上かつ30バイト以内かをチェック
    	        return byteLength > 0 && byteLength <= 30;
    	    } catch (java.io.UnsupportedEncodingException e) {
    	        e.printStackTrace();
    	        return false;
    	    }
    }

    // DBでユーザー認証
    private boolean authenticateUser(HttpServletRequest request, String email, String password) {
        try (Connection conn = Db.getConnection()) {//データベースと接続

            String sql = "SELECT name, mail, password, authority FROM accounts WHERE mail = ?";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);

                try (ResultSet rs = ps.executeQuery()) {//結果取得と照合。メールアドレスが登録されているか。
                    if (rs.next()) {
                    	String storedHash = rs.getString("password");//パスワードのハッシュ値を取得
                    	System.out.println("DBに保存されているハッシュ: " + storedHash);

                        if (PasswordUtils.matches(password, storedHash)) {//パスワードの照合
                        HttpSession accounts = request.getSession();//ログイン成功処理(セッション保存)
                        
                        AccountDto account = new AccountDto();
                        account.setName(rs.getString("name"));
                        account.setMail(rs.getString("mail"));
                        account.setAuth(rs.getInt("authority")); //authorityをintでセット

                        accounts.setAttribute("user", account); // AccountDtoとして保存。ユーザー名などを保存
                        return true;
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {//エラー処理
            System.out.println("データベース接続エラー:");
            e.printStackTrace();
            request.setAttribute("error", "システムエラーが発生しました。");
        }

        return false;
    }
}