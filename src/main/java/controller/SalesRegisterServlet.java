package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.SalesUtils;

@WebServlet("/SalesRegisterServlet")
public class SalesRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SalesRegisterServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // フォームデータの取得
        String salesDate = request.getParameter("salesDate");
        String staff = request.getParameter("staff");
        String category = request.getParameter("category");
        String productName = request.getParameter("productName");
        String unitPrice = request.getParameter("unitPrice");
        String quantity = request.getParameter("quantity");
        String remarks = request.getParameter("remarks");

        // DB登録
        boolean success = SalesUtils.insertSale(
            salesDate,
            staff,
            category,
            productName,
            Integer.parseInt(unitPrice),
            Integer.parseInt(quantity),
            remarks
        );

        // 遷移処理
        if (success) {
            request.setAttribute("message", "売上情報を登録しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_register_complete.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "売上情報の登録に失敗しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_entry_confirm.jsp");
            dispatcher.forward(request, response);
        }
    }
}
