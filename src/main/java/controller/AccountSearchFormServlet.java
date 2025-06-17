package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import utils.Validator;

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
		// セッションの取得
		HttpSession session = request.getSession();

		// 入力値の取得
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String[] authValues = request.getParameterValues("auth");

		// 権限をリストに
		ArrayList<Integer> authList = new ArrayList<>();
		if (authValues != null) {
			for (String val : authValues) {
				authList.add(Integer.parseInt(val));
			}
		}
		
		// バリデーション
		// 名前長さチェック
		if (!Validator.isValidName(name)) {
			request.setAttribute("error", "氏名は20バイト以内で入力してください");
			request.getRequestDispatcher("/account_search_form.jsp").forward(request, response);
			return;
		}

		// メールアドレス長さチェック
		if (!Validator.isValidMail(mail)) {
			request.setAttribute("error", "メールアドレスは100バイト以内で入力してください");
			request.getRequestDispatcher("/account_search_form.jsp").forward(request, response);
			return;
		}

		// セッションに値を保存
		session.setAttribute("lastSearchName", name);
		session.setAttribute("lastSearchMail", mail);
		session.setAttribute("lastSearchAuth", authList);

		//検索結果画面へ遷移
		response.sendRedirect(request.getContextPath() + "/account/search/result.html");
	}

}
