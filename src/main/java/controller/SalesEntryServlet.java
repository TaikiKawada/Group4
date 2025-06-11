package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.Db;

@WebServlet("/SalesEntryServlet")
public class SalesEntryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SalesEntryServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力値の取得
        String salesDate = request.getParameter("salesDate");
        String staff = request.getParameter("staff");
        String category = request.getParameter("category");
        String productName = request.getParameter("productName");
        String unitPrice = request.getParameter("unitPrice");
        String quantity = request.getParameter("quantity");
        String remarks = request.getParameter("remarks");

        // 値をリクエストスコープに格納
        request.setAttribute("salesDate", salesDate);
        request.setAttribute("staff", staff);
        request.setAttribute("category", category);
        request.setAttribute("productName", productName);
        request.setAttribute("unitPrice", unitPrice);
        request.setAttribute("quantity", quantity);
        request.setAttribute("remarks", remarks);

        // 確認画面へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_entry_confirm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> staffList = new ArrayList<>();
        List<Map<String, String>> categoryList = new ArrayList<>();

        try (Connection conn = Db.getConnection()) {

            // 担当者一覧の取得
        	// 担当者一覧の取得
        	try (PreparedStatement stmt1 = conn.prepareStatement("SELECT name FROM accounts");
        	     ResultSet rs = stmt1.executeQuery()) {
        	    while (rs.next()) {
        	        staffList.add(rs.getString("name"));
        	    }
        	}

        	// カテゴリー一覧の取得
        	try (PreparedStatement stmt2 = conn.prepareStatement("SELECT category_id, category_name FROM categories WHERE active_flg = 1");
        	     ResultSet rs2 = stmt2.executeQuery()) {
        	    while (rs2.next()) {
        	        Map<String, String> map = new HashMap<>();
        	        map.put("id", String.valueOf(rs2.getInt("category_id")));
        	        map.put("name", rs2.getString("category_name"));
        	        categoryList.add(map);
        	    }
        	}


        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("staffList", staffList);
        request.setAttribute("categoryList", categoryList);

        request.getRequestDispatcher("sales_entry.jsp").forward(request, response);
    }
}
