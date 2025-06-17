package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.SaleDAO;
import DTO.SalesDto;

@WebServlet("/SalesDeleteConfirmServlet")
public class SalesDeleteConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String saleIdStr = request.getParameter("saleId");

        try {
            int saleId = Integer.parseInt(saleIdStr);
            SalesDto sale = SaleDAO.getSaleById(saleId);

            if (sale != null) {
                request.setAttribute("saleId", sale.getSaleId());
                request.setAttribute("salesDate", sale.getSaleDate());
                request.setAttribute("staff", sale.getAccountId());
                request.setAttribute("staffName", sale.getAccountName());
                request.setAttribute("category", sale.getCategoryId());
                request.setAttribute("categoryName", sale.getCategoryName());
                request.setAttribute("productName", sale.getTradeName());
                request.setAttribute("unitPrice", sale.getUnitPrice());
                request.setAttribute("quantity", sale.getSaleNumber());
                request.setAttribute("remarks", sale.getNote());
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