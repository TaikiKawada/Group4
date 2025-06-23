
package controller;

import java.io.IOException;

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
    	
    	String mail = request.getParameter("mail");
		String password = request.getParameter("password");
    	
    	request.setCharacterEncoding("UTF-8");    	
    	
        //アカウント情報が一致したらdashboard.jspへ遷移
        if (loginService.authenticate(request)) {
            response.sendRedirect(request.getContextPath() + "/C0020.html");

            
        } else {

        	 ValidationResult result = new ValidationResult();
             Validator.validateEmail(mail, result);
             Validator.validatePassword(password, password, result);

             if (result.hasErrors()) {
                 request.setAttribute("error", result.getErrors());
             }

            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
