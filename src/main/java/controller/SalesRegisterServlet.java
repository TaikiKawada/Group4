package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.AccountDao;
import DAO.CategoryDAO;
import DAO.SaleDAO;
import DTO.SalesDto;

@WebServlet("/SalesRegisterServlet")
public class SalesRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力値取得
        String salesDate = request.getParameter("salesDate");
        int accountId = Integer.parseInt(request.getParameter("staff"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String tradeName = request.getParameter("tradeName");
        int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
        int saleNumber = Integer.parseInt(request.getParameter("saleNumber"));
        String note = request.getParameter("note");

        // DTOに詰める
        SalesDto dto = new SalesDto(
            salesDate,
            accountId,
            categoryId,
            tradeName,
            unitPrice,
            saleNumber,
            note
        );

        // DAOで登録
        boolean success = SaleDAO.insert(dto);

        if (success) {
            // 成功時：登録完了メッセージ付きで登録画面に戻る
            request.setAttribute("message", "売上情報を登録しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_entry.jsp");
            dispatcher.forward(request, response);

        } else {
            // 失敗時：確認画面に戻すため、すべての値＋名前もセット
            String staffName = AccountDao.getNameById(accountId);
            String categoryName = CategoryDAO.getNameById(categoryId);

            request.setAttribute("salesDate", salesDate);
            request.setAttribute("staff", accountId);
            request.setAttribute("staffName", staffName);
            request.setAttribute("category", categoryId);
            request.setAttribute("categoryName", categoryName);
            request.setAttribute("tradeName", tradeName);
            request.setAttribute("unitPrice", unitPrice);
            request.setAttribute("saleNumber", saleNumber);
            request.setAttribute("note", note);
            request.setAttribute("error", "売上情報の登録に失敗しました。");

            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_entry_confirm.jsp");
            dispatcher.forward(request, response);
        }
    }
}
