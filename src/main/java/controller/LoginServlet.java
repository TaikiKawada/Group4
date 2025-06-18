
package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.LoginService;

@WebServlet("/login")

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
    	
    	request.setCharacterEncoding("UTF-8");
    	    	
    	boolean isSuccess = loginService.authenticate(request);
    	
    	if(isSuccess) {
    		response.sendRedirect("Dashboard");
    		return;
    	}
    	
    	Boolean systemError = (Boolean) request.getAttribute("systemError");
    	
        if (systemError != null && systemError) {
        	request.getRequestDispatcher("/login_error.jsp").forward(request, response);
            
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
