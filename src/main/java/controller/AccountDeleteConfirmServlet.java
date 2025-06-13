package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.AccountDao;
import DTO.AccountDto;

/**
 * Servlet implementation class AccountDeleteConfirmServlet
 */
@WebServlet("/account/delete.html")
public class AccountDeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 削除するアカウントのaccount_idを取得
		int accountId = Integer.parseInt(request.getParameter("account_id"));
		
		// 削除するアカウントの情報を取得
		AccountDto account = new AccountDao().findById(accountId);
		
		// 権限のチェック
		int auth = account.getAuth();
		
		boolean hasNoneAuth = (auth == 0);
		boolean hasSalesAuth = (auth & 1) != 0;
		boolean hasAccountAuth = (auth & 2) != 0;
		
		// アカウント情報をセット
		request.setAttribute("hasNoneAuth", hasNoneAuth);
		request.setAttribute("hasSalesAuth", hasSalesAuth);
		request.setAttribute("hasAccountAuth", hasAccountAuth);		
		request.setAttribute("account", account);
		// 更新画面に遷移
		request.getRequestDispatcher("/account_delete_confirm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
