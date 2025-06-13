package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import utils.Db;

public class LoginService {

    // 最大試行回数
    private static final int MAX_ATTEMPTS = 5;

    public boolean authenticate(HttpServletRequest request) {
        HttpSession session = request.getSession();
     

        // セッションから試行回数を取得（初期値は0）
        Integer failCount = (Integer) session.getAttribute("loginAttempts");
        if (failCount == null) {
            failCount = 0;
        }

        if (failCount >= MAX_ATTEMPTS) {
            request.setAttribute("error", "ログイン試行回数が上限に達しました。しばらく時間をおいて再試行してください。");
            return false;
        }
        
        
        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        

        // 入力検証（空欄、形式）
        if (!validateInputs(request, email, password)) {
            return false;
        }

        // 認証処理（DBチェック）
        if (authenticateUser(request, email, password)) {
            session.removeAttribute("failCount"); // 成功したらリセット
            return true;
        } else {
            // 認証失敗時、カウントアップしてメッセージ
            session.setAttribute("failCount", failCount + 1);
            request.setAttribute("error", "メールアドレスまたはパスワードが正しくありません。");
            return false;
        }
    }

    // 入力バリデーション
    private boolean validateInputs(HttpServletRequest request, String email, String password) {
        boolean isValid = true;

        if (email == null || email.isEmpty()) {
            request.setAttribute("emailError", "メールアドレスは必須です。");
            isValid = false;
        } else if (!isValidEmail(email)) {
            request.setAttribute("emailError", "有効なメールアドレスを入力してください。");
            isValid = false;
        }

        if (password == null || password.isEmpty()) {
            request.setAttribute("passwordError", "パスワードは必須です。");
            isValid = false;
        }

        return isValid;
    }

    // メールアドレス形式チェック
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // DBでユーザー認証
    private boolean authenticateUser(HttpServletRequest request, String email, String password) {
        try (Connection conn = Db.getConnection()) {
            String sql = "SELECT * FROM accounts WHERE mail = ? AND password = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", rs.getString("name")); // ユーザー名などを保存
                        return true;
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "システムエラーが発生しました。");
        }

        return false;
    }
}
