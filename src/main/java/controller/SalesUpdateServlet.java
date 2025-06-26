package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AccountDao;
import dao.CategoryDAO;
import dao.SaleDAO;
import dto.SalesDto;

@WebServlet("/S0024.html")
public class SalesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 「confirm」パラメータがある → 確認画面からの「OK」押下 → DB更新処理
        if (request.getParameter("confirm") != null) {
            SalesDto dto = new SalesDto(request);
            boolean result = SaleDAO.update(dto);

            if (result) {
                response.sendRedirect("S0022.html?saleId=" + dto.getSaleId());
            } else {
                request.setAttribute("error", "更新に失敗しました");
                request.setAttribute("dto", dto);
                request.getRequestDispatcher("sales_edit_confirm.jsp").forward(request, response);
            }

        } else {
            // 最初の編集画面から → 確認画面へ
            SalesDto dto = new SalesDto(request);
            dto.setAccountName(AccountDao.getNameById(dto.getAccountId()));
            dto.setCategoryName(CategoryDAO.getNameById(dto.getCategoryId()));

            request.setAttribute("dto", dto);
            request.getRequestDispatcher("sales_edit_confirm.jsp").forward(request, response);
        }
    }
}
