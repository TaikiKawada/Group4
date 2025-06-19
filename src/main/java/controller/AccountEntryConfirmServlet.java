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
import utils.SessionUtil;


@WebServlet("/account/entry/confirm.html")
public class AccountEntryConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 確認画面表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AccountDto account = SessionUtil.getAttribte(request.getSession(false), "accountData", AccountDto.class);
		
		if(account == null) {
			response.sendRedirect(request.getContextPath() + "/account/entry.html");
			return;
		}
		
		// 権限のチェック
		request.setAttribute("hasNoneAuth", account.hasNoneAuth());
		request.setAttribute("hasSalesAuth", account.hasSalesAuth());
		request.setAttribute("hasAccountAuth", account.hasAccountAuth());
		
		request.getRequestDispatcher("/account_entry_confirm.jsp").forward(request, response);
	}

	// 登録処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		AccountDto account = SessionUtil.getAttribte(session, "accountData", AccountDto.class);
		
		if (session == null) {
			request.setAttribute("error", "セッションが切れました。もう一度入力してください。");
			request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
			return;
		}

		new AccountService().signup(account);
		session.removeAttribute("accountData");
		
		response.sendRedirect(request.getContextPath() + "/account/entry.html");
	}

}
