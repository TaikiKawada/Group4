package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import dto.AccountDto;
import utils.Db;
import utils.ErrorMessages;
import utils.PasswordUtils;
import utils.ValidationResult;
import utils.Validator;

public class LoginService {

    public boolean authenticate(HttpServletRequest request) {
        // フォームからの入力値を取得
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        // バリデーション結果を格納するオブジェクトを作成
        ValidationResult result = new ValidationResult();

        // メールアドレスとパスワードの検証
        Validator.validateEmail(mail, result);
        Validator.validatePassword(password, password, result);

        // バリデーションエラーがある場合、エラーメッセージをリクエストにセットしてログイン画面に遷移
        if (result.hasErrors()) {
            request.setAttribute("errors", result.getErrors());
            return false;
        }

        // ユーザーの認証処理を実行
        return authenticateUser(request, mail, password);
    }

    private boolean authenticateUser(HttpServletRequest request, String mail, String password) {
        ValidationResult result = new ValidationResult();

        try (Connection conn = Db.getConnection()) {
            String sql = "SELECT name, mail, password, authority FROM accounts WHERE mail = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, mail);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String storedHash = rs.getString("password");

                        // パスワードの照合
                        if (PasswordUtils.matches(password, storedHash)) {
                            HttpSession session = request.getSession();
                            AccountDto account = new AccountDto();
                            account.setName(rs.getString("name"));
                            account.setMail(rs.getString("mail"));
                            account.setAuth(rs.getInt("authority"));
                            session.setAttribute("user", account);
                            return true;
                        } else {
                            result.addError("password", ErrorMessages.INVALID_PASSWORD_FORMAT);
                        }
                    } else {
                        result.addError("mail", ErrorMessages.INVALID_EMAIL_FORMAT);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            result.addError("system", "システムエラーが発生しました");
        }

        // バリデーションエラーがある場合、エラーメッセージをリクエストにセットしてログイン画面に遷移
        request.setAttribute("errors", result.getErrors());
        return false;
    }
}
