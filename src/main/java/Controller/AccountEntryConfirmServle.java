package Controller;

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
public class AccountEntryConfirmServle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountEntryConfirmServle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/account_entry_confirm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection con = Db.getConnection();) {
			HttpSession session = request.getSession(false);
			
			if(session == null) {
				response.sendRedirect(request.getContextPath() + "/AccountEntryConfirmServlet");
				return;
			}
			
			AccountDto accountDto = (AccountDto) session.getAttribute("accountData");
			AccountService as = new AccountService();
			as.signup(accountDto);
			response.sendRedirect(request.getContextPath() + "/AccountEntryConfirmServlet");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
