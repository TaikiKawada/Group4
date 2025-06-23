package services;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import dto.AccountDto;
import utils.Db;
import utils.PasswordUtils;
import utils.Validator;
 
public class LoginService {
 
    // ログイン要求のメイン処理
    public boolean authenticate(HttpServletRequest request) {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
 
        return authenticateUser(request, mail, password);
    }
 
    // DBでユーザー認証
    private boolean authenticateUser(HttpServletRequest request, String mail, String password) {   	
        try (Connection conn = Db.getConnection()) {
            String sql = "SELECT name, mail, password, authority FROM accounts WHERE mail = ? AND is_deleted = false";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, mail);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String storedHash = rs.getString("password");
                        if (PasswordUtils.matches(password, storedHash)) {
                            HttpSession session = request.getSession();
                            
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
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        	//Validator.setSystemError(request);
            return false;
        }
        Validator.setAuthenticationFailed(request);
        return false;
    }
}

 