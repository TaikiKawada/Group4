
package controller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.LoginService;
import utils.ValidationResult;
import utils.Validator;

@WebServlet("/C0010.html")

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService loginService = new LoginService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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


            
             //システムエラー試し用
             // メールアドレスとパスワードの未入力チェック
//            if (mail == null || mail.isEmpty()) {
//                throw new SQLException("メールアドレスが未入力です。");
//            }
//
//            if (password == null || password.isEmpty()) {
//                throw new SQLException("パスワードが未入力です。");
//            }

            
            
            // バリデーションエラーがある場合
            if (result.hasErrors()) {
                request.setAttribute("errors", result.getErrors());
                request.setAttribute("form", Map.of("mail", mail));
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            // 認証処理
            if (!loginService.authenticate(request)) {
                Validator.setAuthenticationFailed(request); 
                request.setAttribute("form", Map.of("mail", mail));
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            // 認証成功
            response.sendRedirect(request.getContextPath() + "/C0020.html");
            
        }catch (Exception e) {
        // 予期しない例外時
        Validator.setSystemError(request);
        request.getRequestDispatcher("/login_error.jsp").forward(request, response);
    }
}

}
