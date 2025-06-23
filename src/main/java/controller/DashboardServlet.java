package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.AccountDto;
import dto.SalesDto;
import services.SaleService;


@WebServlet("/C0020.html")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
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
			
			Map<String,Integer>categorySalesMap=saleService.getCategorySalesSum();

			request.setAttribute("sales", salesWithNames);
			request.setAttribute("categorySalesMap", categorySalesMap);
			request.setAttribute("authority", authority);
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", "売上データの取得中にエラーが発生しました。");
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		}
	}


}
