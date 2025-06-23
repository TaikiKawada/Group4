package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.LoginService;
import utils.ValidationResult;
import utils.Validator;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // レスポンスの文字エンコーディングをUTF-8に設定
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // ログイン画面へフォワード
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   

        try {
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            
            ValidationResult result = new ValidationResult();
            Validator.validateEmail(mail, result);
            Validator.validatePassword(password, password, result);


            
//               //システムエラー試し用
//            // メールアドレスとパスワードの未入力チェック
            if (mail == null || mail.isEmpty()) {
                throw new SQLException("メールアドレスが未入力です。");
            }

            if (password == null || password.isEmpty()) {
                throw new SQLException("パスワードが未入力です。");
            }

            // バリデーションエラーがある場合
            if (result.hasErrors()) {
                request.setAttribute("errors", result.getErrors());
                request.setAttribute("form", Map.of("mail", mail));
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            // 認証処理
            if (!loginService.authenticate(request)) {
                Validator.setAuthenticationFailed(request); // "error" キーに登録
                request.setAttribute("form", Map.of("mail", mail));
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            // 認証成功
            response.sendRedirect("Dashboard");
            
        }catch (Exception e) {
        // ここは予期しない例外時
        Validator.setSystemError(request);
        request.getRequestDispatcher("/login_error.jsp").forward(request, response);
    }
}
}
