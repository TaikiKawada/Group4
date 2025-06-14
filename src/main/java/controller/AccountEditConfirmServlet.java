package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DAO.AccountDao;
import DTO.AccountDto;

/**
 * Servlet implementation class AccountEditConfirmServlet
 */
@WebServlet("/account/edit/confirm.html")
public class AccountEditConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountEditConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			AccountDto account = (AccountDto) session.getAttribute("accountData");
			
			if(account != null) {
				int auth = account.getAuth();
				
				request.setAttribute("hasNoneAuth", (auth == 0));
				request.setAttribute("hasSalesAuth", (auth & 1) != 0);
				request.setAttribute("hasAccountAuth", (auth & 2) != 0);
			}
		}
		
		request.getRequestDispatcher("/account_edit_confirm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		AccountDto account = (AccountDto) session.getAttribute("accountData");
		
		AccountDao dao = new AccountDao();
		boolean success = dao.editAccount(account);
		
		if(success) {
			response.sendRedirect(request.getContextPath() + "/account/search/result.html");
		}else {
			request.setAttribute("error", "アカウントの削除に失敗しました");
			request.getRequestDispatcher("/account_edit_confirm.jsp").forward(request, response);
		}
	}

}
