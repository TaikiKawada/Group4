package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.SaleDAO;

@WebServlet("/SalesDeleteServlet")
public class SalesDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int saleId = Integer.parseInt(request.getParameter("saleId"));

		boolean success = SaleDAO.deleteById(saleId);

		if (success) {
			request.setAttribute("message", "売上情報を削除しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales_delete_complete.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("error", "削除に失敗しました。対象が存在しない可能性があります。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales_delete_confirm.jsp");
			dispatcher.forward(request, response);
		}
	}
}
