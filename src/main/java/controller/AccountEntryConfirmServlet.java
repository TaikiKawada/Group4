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


@WebServlet("/S0031.html")
public class AccountEntryConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 確認画面表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccountDto account = SessionUtil.getAttribute(request.getSession(false), "accountData", AccountDto.class);
		
		if(account == null) {
			response.sendRedirect(request.getContextPath() + "/S0030.html");
			return;
		}
		
		// 権限のチェック
		AuthUtil.setAuthorityAttributes(request, account.getAuth());
		request.getRequestDispatcher("/account_entry_confirm.jsp").forward(request, response);
	}

	// 登録処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccountDto account = SessionUtil.getAttribute(request.getSession(false), "accountData", AccountDto.class);

		if (request.getSession() == null) {
			request.setAttribute("error", "セッションが切れました。もう一度入力してください。");
			request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
			return;
		}

		boolean success = new AccountService().signup(account);
		if(success) {
			MessageUtil.setSuccessMessage(request, "アカウントを登録しました");
			SessionUtil.remove(request, "accountData");
			response.sendRedirect(request.getContextPath() + "/S0030.html");
		} else {
			MessageUtil.setErrorMessage(request, "アカウントの登録に失敗しました");
			request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
		}
	}
}
