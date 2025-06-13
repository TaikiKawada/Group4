package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.AccountDao;
import DTO.AccountDto;

/**
 * Servlet implementation class AccountSearchFormServlet
 */
@WebServlet("/account/search.html")
public class AccountSearchFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountSearchFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/account_search_form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 入力値の取得
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String[] authValues = request.getParameterValues("auth");

		// 権限をリストに
		ArrayList<Integer> authList = new ArrayList<>();
		if(authValues != null) {
			for(String val : authValues) {
				authList.add(Integer.parseInt(val));
			}
		}

		
		AccountDao dao = new AccountDao();
		ArrayList<AccountDto> accounts = dao.searchAccounts(name, mail, authList);
		
		request.setAttribute("accountList", accounts);
	
		//検索結果画面へ遷移
		request.getRequestDispatcher("/account_search_result.jsp").forward(request, response);

	}

}
