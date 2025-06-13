package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DTO.AccountDto;

/**
 * Servlet implementation class AccountEntryServlet
 */
@WebServlet("/account/entry.html")
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
		response.setContentType("text/html; charset=UTF-8");
		request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			//入力値の取得
			String name = request.getParameter("name");
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");
			String passConfirm = request.getParameter("passConfirm");
			String[] authValues = request.getParameterValues("auth");
			
			//権限のビット値を計算
			int authTotal = 0;
			if(authValues != null) {
				for(String val : authValues) {
					authTotal |= Integer.parseInt(val);
				}
			}
			
			//パスワードの一致確認
			if(!password.equals(passConfirm)) {
				request.setAttribute("error","パスワードが一致しません");
				request.getRequestDispatcher("/account_entry.jsp").forward(request, response);
				return;
				
			}
			
			//AccountDtoに入力値を格納
			AccountDto account = new AccountDto(name, mail, password, authTotal);
			
			//セッションに保存
			HttpSession session = request.getSession();
			session.setAttribute("accountData", account);
			
			//確認画面へリダイレクト
			response.sendRedirect(request.getContextPath() + "/account/entry/confirm.html");
	}

}
