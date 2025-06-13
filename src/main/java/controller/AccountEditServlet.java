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
 * Servlet implementation class AccountEditServlet
 */
@WebServlet("/account/edit.html")
public class AccountEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 更新するアカウントのaccount_idを取得
		int accountId = Integer.parseInt(request.getParameter("account_id"));
		
		// 更新するアカウントの情報を取得
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
		request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 更新する値を取得
		int accountId = Integer.parseInt(request.getParameter("account_id"));
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String[] authValues = request.getParameterValues("auth");
		
		// 権限のビット値を計算
		int auth = 0;
		if(authValues != null) {
			for(String val : authValues) {
				auth |= Integer.parseInt(val);
			}
		}
		
		// 更新情報をaccountに格納
		AccountDto account = new AccountDto(accountId, name, mail, password, auth);
		
		// セッションの取得、値の保存
		HttpSession session = request.getSession();
		session.setAttribute("accountData", account);
		// 更新確認画面に遷移
		response.sendRedirect(request.getContextPath() + "/account/edit/confirm.html");
				
		
	}

}
