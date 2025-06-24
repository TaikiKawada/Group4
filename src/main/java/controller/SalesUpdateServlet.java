package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SaleDAO;
import dto.SalesDto;

/**
 * Servlet implementation class SalesUpdateServlet
 */
@WebServlet("/S0024.html")
public class SalesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        SalesDto dto = new SalesDto(request); // ← DTOでパラメータ処理完結

        boolean result = SaleDAO.update(dto);

        if (result) {
            response.sendRedirect("S0022.html?saleId=" + dto.getSaleId());
        } else {
            request.setAttribute("error", "更新に失敗しました");
            request.getRequestDispatcher("sales_edit_confirm.jsp").forward(request, response);
        }
    }
}

