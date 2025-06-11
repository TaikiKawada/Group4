package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.AccountDto;
import services.AccountService;
import utils.Db;

/**
 * Servlet implementation class AccountEntryConfirmServlet
 */
@WebServlet("/AccountEntryConfirmServlet")
public class AccountEntryConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountEntryConfirmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//すでにあるセッションを取得
		HttpSession session = request.getSession(false);
		
		//セッションの値をaccountに
		if(session != null) {
			AccountDto account = (AccountDto) session.getAttribute("accountData");
			
			if(account != null) {
				//権限を判定してbooleanに変換してjspに渡す
				int auth = account.getAuth();
								
				request.setAttribute("hasNoneAuth", (auth == 0));
				request.setAttribute("hasSalesAuth", (auth & 1) != 0);
				request.setAttribute("hasAccountAuth", (auth & 2) != 0);
				
			}
		}
		
		request.getRequestDispatcher("/account_entry_confirm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection con = Db.getConnection();) {
			//すでにあるセッションを取得
			HttpSession session = request.getSession(false);
			
			//セッションがなければ登録画面に
			if(session == null) {
				response.sendRedirect(request.getContextPath() + "/AccountEntryConfirmServlet");
				return;
			}
			
			//アカウント情報をデータベースに登録
			AccountDto accountDto = (AccountDto) session.getAttribute("accountData");
			AccountService as = new AccountService();
			as.signup(accountDto);
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		HttpSession session = request.getSession();
		session.removeAttribute("accountData");
		
		//登録画面へ遷移
		response.sendRedirect(request.getContextPath() + "/AccountEntryServlet");
		
	}

}
