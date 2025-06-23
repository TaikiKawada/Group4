package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SaleDAO;
import dto.SalesDto;

@WebServlet("/S0025.html")
public class SalesDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // SalesDtoにリクエストからの値をまとめて処理させる
        SalesDto dto = new SalesDto(request);
        int saleId = dto.getSaleId();

        boolean success = SaleDAO.deleteById(saleId);

        String nextPage = success ? "sales_delete_complete.jsp" : "sales_delete_confirm.jsp";
        String message = success ? "売上情報を削除しました。" : "削除に失敗しました。対象が存在しない可能性があります。";

        request.setAttribute(success ? "message" : "error", message);
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
}

