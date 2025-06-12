package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import beans.AccountDto;
import utils.Db;

public class LoginService {
    public boolean authenticate(HttpServletRequest request) {
        String email = request.getParameter("mail");
        String password = request.getParameter("password");

        System.out.println("認証開始: 入力メール=" + email + ", パスワード=" + (password != null ? "あり" : "なし"));

        // 入力検証
        if (!validateInputs(request, email, password)) {
            System.out.println("入力検証失敗");
            return false;
        }

        // 認証処理
        return authenticateUser(request, email, password);
    }

    private boolean validateInputs(HttpServletRequest request, String email, String password) {
        boolean isValid = true;

        if (email == null || email.isEmpty()) {
            request.setAttribute("emailError", "メールアドレスは必須です。");
            System.out.println("入力エラー: メールアドレス未入力");
            isValid = false;
        } else if (!isValidEmail(email)) {
            request.setAttribute("emailError", "有効なメールアドレスを入力してください。");
            System.out.println("入力エラー: メールアドレス形式不正");
            isValid = false;
        }

        if (password == null || password.isEmpty()) {
            request.setAttribute("passwordError", "パスワードは必須です。");
            System.out.println("入力エラー: パスワード未入力");
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
        try (Connection conn = Db.getConnection()) {
            System.out.println("DB接続成功");

            String sql = "SELECT * FROM accounts WHERE mail = ? AND password = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                System.out.println("SQL準備完了: " + ps.toString());

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                    	AccountDto loginUser = new AccountDto(0, sql, sql, sql, 0);
                        loginUser.setName(rs.getString("name"));
                        loginUser.setMail(rs.getString("mail"));
                        loginUser.setAuth(rs.getInt("authority"));  // authorityをintでセット
                        // 他に必要なカラムもセットしてください

                        HttpSession session = request.getSession();
                        session.setAttribute("user", loginUser);  // ここでAccountDtoオブジェクトをセット
                        System.out.println("認証成功: ユーザー名=" + loginUser.getName());
                        return true;
                    } else {
                        System.out.println("認証失敗: 該当ユーザーなし");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("データベース接続エラー:");
            e.printStackTrace();
        }
        return false;
    }
}
