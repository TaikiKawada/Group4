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

        // フォームのパラメータ取得
        String salesDate = request.getParameter("salesDate");
        int accountId = Integer.parseInt(request.getParameter("staff"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String productName = request.getParameter("productName");
        int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String remarks = request.getParameter("remarks");

        // DTOにまとめる
        SalesDto dto = new SalesDto(
            salesDate,
            accountId,
            categoryId,
            productName,
            unitPrice,
            quantity,
            remarks
        );

        // DAOを呼び出してDBに登録
        boolean success = SaleDAO.insert(dto);

        // 登録成功・失敗で画面遷移を分ける
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
