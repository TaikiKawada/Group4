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


@WebServlet("/S0044.html")
public class AccountDeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 削除対象のaccount_idを取得
		String idParam = request.getParameter("account_id");
		if(idParam == null || idParam.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/S0040.html");
			return;
		}
		
		// 削除対象のアカウントを取得
		int accountId = Integer.parseInt(idParam);
		AccountDto account = new AccountService().findById(accountId);
		
		if(account == null) {
			response.sendRedirect(request.getContextPath() + "/S0040.html");
			return;
		}
		
		AuthUtil.setAuthorityAttributes(request, account.getAuth());
		request.setAttribute("account", account);
		request.getRequestDispatcher("/account_delete_confirm.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int accountId = Integer.parseInt(request.getParameter("account_id"));
		boolean success = new AccountService().delete(accountId);
		
		if(success) {
			// 削除成功時は検索結果画面に
			MessageUtil.setSuccessMessage(request, "アカウントを削除しました");
			response.sendRedirect(request.getContextPath() + "/S0041.html");
		}else {
			// 削除失敗時にエラー文表示
			MessageUtil.setSuccessMessage(request, "削除に失敗しました");
			AccountDto account = new AccountService().findById(accountId);
			if(account != null) {
				AuthUtil.setAuthorityAttributes(request, account.getAuth());
				request.setAttribute("account", account);
			}
			request.getRequestDispatcher("/account_delete_confirm.jsp").forward(request, response);
		}
	}

}
