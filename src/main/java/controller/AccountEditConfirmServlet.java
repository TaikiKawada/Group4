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
import utils.MessageUtil;
import utils.SessionUtil;

/**
 * Servlet implementation class AccountEditConfirmServlet
 */
@WebServlet("/S0043.html")
public class AccountEditConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AccountDto account = SessionUtil.getAttribute(request.getSession(false), "accountData", AccountDto.class);
		if (account != null) {
			AuthUtil.setAuthorityAttributes(request, account.getAuth());
			request.setAttribute("account", account);
		}
		request.getRequestDispatcher("/account_edit_confirm.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccountDto account = SessionUtil.getAttribute(request.getSession(false), "accountData", AccountDto.class);
		
		// キャンセルボタンの処理
		if ("true".equals(request.getParameter("back"))) {
			// セッションに格納していたaccountDataを利用して編集画面に戻す
			AuthUtil.setAuthorityAttributes(request, account.getAuth());
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
			return;
		}

		boolean success = new AccountService().edit(account);

		if (success) {
			MessageUtil.setSuccessMessage(request, "アカウントを編集しました");
			response.sendRedirect(request.getContextPath() + "/S0041.html");
		} else {
			MessageUtil.setErrorMessage(request, "アカウントの編集に失敗しました");
			request.getRequestDispatcher("/account_edit_confirm.jsp").forward(request, response);
		}
	}
}
