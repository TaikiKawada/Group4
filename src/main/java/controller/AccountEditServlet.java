package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.AccountDto;
import services.AccountService;
import utils.AuthUtil;
import utils.SessionUtil;
import utils.ValidationResult;
import utils.Validator;


@WebServlet("/account/edit.html")
public class AccountEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("account_id");
		if(idParam == null || idParam.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/account/search.html");
			return;
		}
		
		int accountId = Integer.parseInt(idParam);
		AccountDto account = new AccountService().findById(accountId);

		// 権限のチェック
		AuthUtil.setAuthorityAttributes(request, account.getAuth());
		
		request.setAttribute("account", account);
		request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		AccountDto loginUser = SessionUtil.getAttribute(session, "user", AccountDto.class);
		
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

		session.setAttribute("accountData", account);
		response.sendRedirect(request.getContextPath() + "/account/edit/confirm.html");

	}
}
