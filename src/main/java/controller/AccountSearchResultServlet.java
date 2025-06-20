package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.AccountDto;
import dto.AccountSearchDto;
import services.AccountService;
import utils.SessionUtil;

@WebServlet("/S0041.html")
public class AccountSearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = SessionUtil.getAttribute(request.getSession(), "lastSearchName", String.class);
		String mail = SessionUtil.getAttribute(request.getSession(), "lastSearchMail", String.class);
		@SuppressWarnings("unchecked")
		List<Integer> authList = SessionUtil.getAttribute(request.getSession(), "lastSearchAuth", List.class);

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
