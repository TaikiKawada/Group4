package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.AccountDto;
import utils.SessionUtil;
import utils.ValidationResult;
import utils.Validator;


@WebServlet("/S0030.html")
public class AccountEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			AccountDto account = AccountDto.fromRequest(request);
			
			// バリデーション
			ValidationResult result = new ValidationResult();
			Validator.validateName(account.getName(), result);
			Validator.validateEmail(account.getMail(), result);
			Validator.validatePassword(account.getPassword(), request.getParameter("passConfirm"), result);
			// エラーがあれば戻す
			if(result.hasErrors()) {
				request.setAttribute("errors", result.getErrors());
				request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
				return;
			}
			
			//セッションに保存
			SessionUtil.set(request, "accountData", account);
			response.sendRedirect(request.getContextPath() + "/S0031.html");
	}

}
