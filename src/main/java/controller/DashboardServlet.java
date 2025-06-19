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
import dto.SalesDto;
import services.SaleService;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// user情報からauthority取得
		HttpSession session = request.getSession(false);
		AccountDto loginUser = (AccountDto) session.getAttribute("user");
		int authority = loginUser.getAuth(); // int型（0〜3）

		try {
			SaleService saleService = new SaleService();
			List<SalesDto> salesWithNames = saleService.getSalesWithAccountNames();

			request.setAttribute("sales", salesWithNames);
			request.setAttribute("authority", authority);
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "売上データの取得中にエラーが発生しました。");
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
