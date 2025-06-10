package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.Db;

@WebServlet("/SalesDeleteServlet")
public class SalesDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String saleId = request.getParameter("saleId");

		boolean success = false;

		try (Connection conn = Db.getConnection()) {
			String sql = "DELETE FROM sales WHERE sale_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, saleId);

			int rows = stmt.executeUpdate();
			success = (rows > 0);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "データベースエラーが発生しました。");
		}

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
