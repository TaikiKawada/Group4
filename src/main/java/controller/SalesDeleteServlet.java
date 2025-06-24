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

        SalesDto dto = new SalesDto(request);
        int saleId = dto.getSaleId();

        boolean success = SaleDAO.deleteById(saleId);

        if (success) {
            // 削除成功 → 検索条件入力画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/S0020.html");
        } else {
            // 削除失敗 → 確認画面にエラーメッセージ付きで戻る
            request.setAttribute("error", "削除に失敗しました。対象が存在しない可能性があります。");
            request.setAttribute("saleId", saleId);
            request.getRequestDispatcher("sales_delete_confirm.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        SalesDto dto = new SalesDto(request);
        int saleId = dto.getSaleId();

        SalesDto sale = SaleDAO.getSaleById(saleId);

        if (sale == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("sale", sale);
        request.getRequestDispatcher("sales_delete_confirm.jsp").forward(request, response);
    }

}


