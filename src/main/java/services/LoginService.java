package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import src.main.java.utils.Db;

public class LoginService {
    public boolean authenticate(HttpServletRequest request) {
        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        
     // 入力検証
        if (!validateInputs(request, email, password)) {
            return false;
        }

        // 認証処理
        return authenticateUser(request, email, password);
    }

    private boolean validateInputs(HttpServletRequest request, String email, String password) {
        boolean isValid = true;

        // メールアドレスの未入力チェック
        if (email == null || email.isEmpty()) {
            request.setAttribute("emailError", "メールアドレスは必須です。");
            isValid = false;
        } else if (!isValidEmail(email)) {
            // メールアドレスの形式チェック
            request.setAttribute("emailError", "有効なメールアドレスを入力してください。");
            isValid = false;
        }
        // パスワードの未入力チェック
        if (password == null || password.isEmpty()) {
            request.setAttribute("passwordError", "パスワードは必須です。");
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean authenticateUser(HttpServletRequest request, String email, String password) {

//        データベースと接続して、accounts表からemail(mail)とpasswordを取得
        try (Connection conn = Db.getConnection()) {
            String sql = "SELECT * FROM accounts WHERE mail = ? AND password = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);

//                認証に成功した場合にセッションにユーザー名を保存してログイン処理を行う 
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", rs.getString("name"));
                        return true;
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
