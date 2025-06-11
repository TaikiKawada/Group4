package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import src.main.java.utils.Db;

@WebServlet("/SalesDeleteConfirmServlet")
public class SalesDeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String saleId = request.getParameter("saleId");

		try (Connection conn = Db.getConnection()) {
			String sql = "SELECT * FROM sales WHERE sale_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, saleId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				request.setAttribute("saleId", rs.getString("sale_id"));
				request.setAttribute("salesDate", rs.getString("sale_date"));
				request.setAttribute("staff", rs.getString("account_id"));
				request.setAttribute("category", rs.getString("category_id"));
				request.setAttribute("productName", rs.getString("trade_name"));
				request.setAttribute("unitPrice", rs.getString("unit_price"));
				request.setAttribute("quantity", rs.getString("sale_number"));
				request.setAttribute("remarks", rs.getString("note"));
			} else {
				request.setAttribute("error", "指定された売上データが見つかりませんでした。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "データベースエラーが発生しました。");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("sales_delete_confirm.jsp");
		dispatcher.forward(request, response);
	}
}
