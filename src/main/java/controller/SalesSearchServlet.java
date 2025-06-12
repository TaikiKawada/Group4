package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import src.main.java.utils.Db; 

@WebServlet("/SalesSearchServlet")
public class SalesSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String staff = request.getParameter("staff");
        String category = request.getParameter("category");
        String productName = request.getParameter("productName");

        ArrayList<String[]> resultList = new ArrayList<>();

        try (Connection conn = Db.getConnection()) {
            StringBuilder sql = new StringBuilder("SELECT * FROM sales WHERE 1=1");
            ArrayList<Object> params = new ArrayList<>();

            if (fromDate != null && !fromDate.isEmpty()) {
                sql.append(" AND sale_date >= ?");
                params.add(fromDate);
            }

            if (toDate != null && !toDate.isEmpty()) {
                sql.append(" AND sale_date <= ?");
                params.add(toDate);
            }

            if (staff != null && !staff.isEmpty()) {
                sql.append(" AND account_id = ?");
                params.add(staff);
            }

            if (category != null && !category.isEmpty()) {
                sql.append(" AND category_id = ?");
                params.add(category);
            }

            if (productName != null && !productName.isEmpty()) {
                sql.append(" AND trade_name LIKE ?");
                params.add("%" + productName + "%");
            }

            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] row = {
                    rs.getString("sale_id"),
                    rs.getString("sale_date"),
                    rs.getString("account_id"),
                    rs.getString("category_id"),
                    rs.getString("trade_name"),
                    rs.getString("unit_price"),
                    rs.getString("sale_number"),
                    rs.getString("note")
                };
                resultList.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "検索中にエラーが発生しました");
        }

        request.setAttribute("results", resultList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_search_result.jsp");
        dispatcher.forward(request, response);
    }
}
