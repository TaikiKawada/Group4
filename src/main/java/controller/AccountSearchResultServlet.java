package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DAO.AccountDao;
import DTO.AccountDto;

/**
 * Servlet implementation class AccountSearchResultServlet
 */
@WebServlet("/account/search/result.html")
public class AccountSearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSearchResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションの取得
		HttpSession session = request.getSession();
		
		// セッションの値を格納
		String name = (String) session.getAttribute("lastSearchName");
		String mail = (String) session.getAttribute("lastSearchMail");
		@SuppressWarnings("unchecked")
		ArrayList<Integer> authList = (ArrayList<Integer>) session.getAttribute("lastSearchAuth");
		
		AccountDao dao = new AccountDao();
		ArrayList<AccountDto> accounts = dao.searchAccounts(name, mail, authList);
		
		request.setAttribute("accountList", accounts);
		
		request.getRequestDispatcher("/account_search_result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
