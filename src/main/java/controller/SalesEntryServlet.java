package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AccountDao;
import dao.CategoryDAO;
import dao.SaleDAO;
import dto.SalesDto;

@WebServlet("/SalesEntryServlet")
public class SalesEntryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 初期表示
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String, String>> staffList = AccountDao.getAccounts();
        List<Map<String, String>> categoryList = CategoryDAO.getActiveCategories();

        request.setAttribute("staffList", staffList);
        request.setAttribute("categoryList", categoryList);

        request.getRequestDispatcher("sales_entry.jsp").forward(request, response);
    }

    // DB登録処理
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        SalesDto dto = new SalesDto(request);

        boolean success = SaleDAO.insert(dto);

        if (success) {
            // 登録成功時 → 再表示（初期化）+ メッセージ
            List<Map<String, String>> staffList = AccountDao.getAllAccounts();
            List<Map<String, String>> categoryList = CategoryDAO.getActiveCategories();

            request.setAttribute("staffList", staffList);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("message", "売上情報を登録しました。");

            request.getRequestDispatcher("sales_entry.jsp").forward(request, response);
        } else {
            // 失敗時は再入力（確認画面）に戻す
            request.setAttribute("error", "登録に失敗しました。");
            request.getRequestDispatcher("sales_entry_confirm.jsp").forward(request, response);
        }
    }
}
