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
import utils.Db;

/**
 * Servlet implementation class AccountEntryServlet
 */
@WebServlet("/AccountEntryServlet")
public class AccountEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountEntryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection con = Db.getConnection();) {
			//入力値の取得
			String name = request.getParameter("name");
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");
			
			
			// authTotal:
			// 0 = なし
			// 1 = 売上登録権限のみ
			// 2 = アカウント登録権限のみ
			// 3 = 両方
			int authTotal = 0;
			String[] authValues = request.getParameterValues("auth");
			if(authValues != null) {
				for(String val : authValues) {
					authTotal |= Integer.parseInt(val);
				}
			}
			
			int dbAuthValue = switch(authTotal) {
			case 1 -> 1;
			case 2 -> 10;
			case 3 -> 11;
			default -> 0;
			};
			
			//AccountDtoに入力値を格納
			AccountDto account = new AccountDto(name, mail, password, dbAuthValue);
			
			//セッションの取得、確認画面へ遷移
			HttpSession session = request.getSession();
			session.setAttribute("accountData", account);
			response.sendRedirect(request.getContextPath() + "/AccountEntryConfirmServlet");



		} catch (SQLException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
