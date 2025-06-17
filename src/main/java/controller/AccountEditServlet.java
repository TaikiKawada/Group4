package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DTO.AccountDto;
import services.AccountService;
import utils.Validator;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 更新するアカウントのaccount_idを取得
		String idParam = request.getParameter("account_id");
		// account_idがnullまたは空じゃないかのチェック
		if(idParam == null || idParam.isEmpty()) {
			response.sendRedirect("account/search.html");
			return;
		}
		
		// 更新するアカウントの情報を取得
		int accountId = Integer.parseInt(idParam);
		AccountDto account = new AccountService().findById(accountId);

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ログインしているユーザの情報を取得
		HttpSession session = request.getSession(false);
		AccountDto loginUser = (AccountDto) session.getAttribute("user");

		// ログインしていない場合、ログイン画面に
		if (loginUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		// 更新する値を取得
		int accountId = Integer.parseInt(request.getParameter("account_id"));
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String passConfirm = request.getParameter("passConfirm");
		String[] authValues = request.getParameterValues("auth");
		
		// 権限のビット値を計算
		int auth = 0;
		if (authValues != null) {
			for (String val : authValues) {
				auth |= Integer.parseInt(val);
			}
		}

		// ログインユーザの権限を取得
		int loginUserAuth = loginUser.getAuth();
		// 権限の判定（10,11の時trueを返す）
		boolean hasEditPermission = (loginUserAuth & 0b10) != 0;

		// 更新対象の権限チェック
		boolean hasNoneAuth = (auth == 0);
		boolean hasSalesAuth = (auth & 1) != 0;
		boolean hasAccountAuth = (auth & 2) != 0;

		
		// アカウント情報をセット
		request.setAttribute("hasNoneAuth", hasNoneAuth);
		request.setAttribute("hasSalesAuth", hasSalesAuth);
		request.setAttribute("hasAccountAuth", hasAccountAuth);

		// 更新情報をaccountに格納
		AccountDto account = new AccountDto(accountId, name, mail, password, auth);

		// ログインユーザに権限がない場合エラーメッセージを表示
		if (!hasEditPermission) {
			request.setAttribute("error", "権限がありません");
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
			return;
		}
		
		// バリデーション
		// 空チェック
		if (Validator.isNullOrEmpty(name) || Validator.isNullOrEmpty(mail) || Validator.isNullOrEmpty(password)) {
			request.setAttribute("error", "未入力の項目があります");
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
			return;
		}

		// 名前長さチェック
		if (!Validator.isValidName(name)) {
			request.setAttribute("error", "氏名は20バイト以内で入力してください");
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
			return;
		}

		// メールアドレス長さチェック
		if (!Validator.isValidMail(mail)) {
			request.setAttribute("error", "メールアドレスは100バイト以内で入力してください");
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
			return;
		}

		// メールアドレスの形式チェック
		if (!Validator.isValidEmail(mail)) {
			request.setAttribute("error", "メールアドレスの形式が正しくありません");
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
			return;
		}

		// パスワード形式チェック
		if (!Validator.isValidPassword(password)) {
			request.setAttribute("error", "パスワードは8文字以上30文字以内で、大文字・小文字・数字・記号をすべて含めてください");
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
			return;
		}

		//パスワードの一致確認
		if (!Validator.isPasswordConfirmed(password, passConfirm)) {
			request.setAttribute("error", "パスワードが一致しません");
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account_edit.jsp").forward(request, response);
			return;
		}

		// セッションに値を保存
		session.setAttribute("accountData", account);
		// 更新確認画面に遷移
		response.sendRedirect(request.getContextPath() + "/account/edit/confirm.html");

	}

}
