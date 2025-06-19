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

/**
 * Servlet implementation class AccountEditConfirmServlet
 */
@WebServlet("/account/edit/confirm.html")
public class AccountEditConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		AccountDto account = SessionUtil.getAttribute(session, "accountData", AccountDto.class);
		
		if (account != null) {
			AuthUtil.setAuthorityAttributes(request, account.getAuth());
			request.setAttribute("account", account);
		}
		request.getRequestDispatcher("/account_edit_confirm.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		AccountDto account = SessionUtil.getAttribute(session, "accountData", AccountDto.class);
		
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
			response.sendRedirect(request.getContextPath() + "/account/search/result.html");
		} else {
			request.setAttribute("error", "アカウントの編集に失敗しました");
			request.getRequestDispatcher("/account_edit_confirm.jsp").forward(request, response);
		}
	}
}
