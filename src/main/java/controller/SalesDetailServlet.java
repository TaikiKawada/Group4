package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SaleDAO;
import dto.SalesDto;

@WebServlet("/SalesDetailServlet")
public class SalesDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // DTOにまとめて処理
            SalesDto paramDto = new SalesDto(request);
            int saleId = paramDto.getSaleId();

            // SaleDAO から該当データを取得
            SalesDto sale = SaleDAO.getSaleById(saleId);

            // JSP に渡す
            request.setAttribute("sale", sale);
            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_detail.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "売上詳細の取得に失敗しました。");
        }
    }
}
