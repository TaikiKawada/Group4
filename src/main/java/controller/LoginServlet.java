package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.LoginService;

@WebServlet("/ログイン画面")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService loginService = new LoginService();

    @Override
    //login.jspを取得
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        //アカウント情報が一致したらdashboard.jspへ遷移
        if (loginService.authenticate(request)) {
            response.sendRedirect("dashboard.jsp");
            
        //アカウント情報が一致しなかったらエラーメッセージを出す
        } else {
            request.setAttribute("error", "メールアドレスまたはパスワードが正しくありません。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
