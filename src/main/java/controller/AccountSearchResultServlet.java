package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.AccountDto;
import dto.AccountSearchDto;
import services.AccountService;
import utils.SessionUtil;

@WebServlet("/account/search/result.html")
public class AccountSearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String name = SessionUtil.getAttribute(session, "lastSearchName", String.class);
		String mail = SessionUtil.getAttribute(session, "lastSearchMail", String.class);
		@SuppressWarnings("unchecked")
		List<Integer> authList = SessionUtil.getAttribute(session, "lastSearchAuth", List.class);

		if (name == null)
			name = "";
		if (mail == null)
			mail = "";
		if (authList == null)
			authList = List.of();

		AccountSearchDto dto = new AccountSearchDto(name, mail, authList);
		
		List<AccountDto> accounts = new AccountService().search(dto.getName(), dto.getMail(), dto.getAuthList());

		request.setAttribute("accountList", accounts);
		request.getRequestDispatcher("/account_search_result.jsp").forward(request, response);
	}
}
