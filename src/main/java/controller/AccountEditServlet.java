package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.AccountDto;
import services.AccountService;
import utils.AuthUtil;
import utils.SessionUtil;
import utils.ValidationResult;
import utils.Validator;


@WebServlet("/S0042.html")
public class AccountEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("account_id");
		if(idParam == null || idParam.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/S0040.html");
			return;
		}
		
		int accountId = Integer.parseInt(idParam);
		AccountDto account = new AccountService().findById(accountId);

		// 権限のチェック
		AuthUtil.setAuthorityAttributes(request, account.getAuth());

		Object flag = SessionUtil.get(request, "editSuccess");
		if(flag != null) {
			request.setAttribute("editSuccess", true);
			SessionUtil.remove(request, "editSuccess");					
		}
		
		request.setAttribute("account", account);
		request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccountDto loginUser = SessionUtil.getAttribute(request.getSession(false), "user", AccountDto.class);
		
		int accountId = Integer.parseInt(request.getParameter("account_id"));
		AccountDto account = AccountDto.fromRequest(request);
		account.setAccount_id(accountId);
		
		// 権限のチェック
		if(!AuthUtil.hasAccountEditPermission(loginUser)) {
			request.setAttribute("error", "権限がありません");
			AuthUtil.setAuthorityAttributes(request, account.getAuth());
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
			return;
		}
		
		// バリデーション
		ValidationResult result = new ValidationResult();
		Validator.validateName(account.getName(), result);
		Validator.validateEmail(account.getMail(), result);
		Validator.validatePassword(account.getPassword(), request.getParameter("passConfirm"), result);
		
		if(result.hasErrors()) {
			request.setAttribute("errors", result.getErrors());
			AuthUtil.setAuthorityAttributes(request, account.getAuth());
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
			return;
		}

		request.getSession().setAttribute("accountData", account);
		response.sendRedirect(request.getContextPath() + "/S0043.html");

	}
}
