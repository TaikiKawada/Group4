package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.AccountSearchDto;
import utils.SessionUtil;
import utils.ValidationResult;
import utils.Validator;


@WebServlet("/account/search.html")
public class AccountSearchFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/account_search_form.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AccountSearchDto dto = AccountSearchDto.fromRequest(request);
		
		// バリデーション
		ValidationResult result = new ValidationResult();
		Validator.isWithinMaxBytes(dto.getName(), 20, result);
		Validator.isWithinMaxBytes(dto.getMail(), 100, result);
		
		if(result.hasErrors()) {
			request.setAttribute("errors", result.getErrors());
			request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
			return;
		}
		
		SessionUtil.set(request, "lastSearchName", dto.getName());
		SessionUtil.set(request, "lastSearchMail", dto.getMail());
		SessionUtil.set(request, "lastSearchAuth", dto.getAuthList());
		
		response.sendRedirect(request.getContextPath() + "/account/search/result.html");
	}

}
