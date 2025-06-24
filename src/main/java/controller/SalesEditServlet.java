package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AccountDao;
import dao.CategoryDAO;
import dao.SaleDAO;
import dto.AccountDto;
import dto.SalesDto;

@WebServlet("/S0023.html")
public class SalesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET：編集フォーム表示
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // saleIdだけ取得し、対象データ取得
            SalesDto paramDto = new SalesDto(request);
            SalesDto sale = SaleDAO.getSaleById(paramDto.getSaleId());

            // 担当者・カテゴリー一覧取得
            List<Map<String, String>> staffList = AccountDao.getAllAccounts();
            List<Map<String, String>> categoryList = CategoryDAO.getActiveCategories();

            // 各値をJSPに渡す
            request.setAttribute("sale", sale);
            request.setAttribute("staffList", staffList);
            request.setAttribute("categoryList", categoryList);

            // ⭐ 初期表示時はエラーなしとする
            request.setAttribute("errors", null);

            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_edit.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        request.setAttribute("errors", null);

    }

    // POST：確認画面へ遷移
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        SalesDto sale = new SalesDto(request);

        Map<String, String> errors = new HashMap<>();

        // バリデーションチェック
        if (sale.getSaleDate() == null || sale.getSaleDate().isEmpty()) {
            errors.put("saleDate", "販売日を入力してください");
        }
        if (sale.getAccountId() == 0) {
            errors.put("staff", "担当者を選択してください");
        }
        if (sale.getCategoryId() == 0) {
            errors.put("category", "カテゴリーを選択してください");
        }
        if (sale.getTradeName() == null || sale.getTradeName().isEmpty()) {
            errors.put("tradeName", "商品名を入力してください");
        }
        if (sale.getUnitPrice() == 0) {
            errors.put("unitPrice", "単価を入力してください");
        }
        if (sale.getSaleNumber() == 0) {
            errors.put("saleNumber", "個数を入力してください");
        }

        AccountDto user = (AccountDto) request.getSession().getAttribute("user");
        if (user == null || user.getAuth() < 1) {
            errors.put("authority", "この操作を行う権限がありません");
        }

        // エラーがある場合は戻る
        if (!errors.isEmpty()) {
            request.setAttribute("sale", sale);
            request.setAttribute("errors", errors);
            request.setAttribute("staffList", AccountDao.getAllAccounts());
            request.setAttribute("categoryList", CategoryDAO.getActiveCategories());

            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_edit.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // 担当名・カテゴリ名の取得
        String staffName = AccountDao.getNameById(sale.getAccountId());
        String categoryName = CategoryDAO.getNameById(sale.getCategoryId());

        // 確認画面に渡すデータ
        request.setAttribute("saleId", sale.getSaleId());
        request.setAttribute("saleDate", sale.getSaleDate());
        request.setAttribute("staff", sale.getAccountId());
        request.setAttribute("staffName", staffName);
        request.setAttribute("category", sale.getCategoryId());
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("tradeName", sale.getTradeName());
        request.setAttribute("unitPrice", sale.getUnitPrice());
        request.setAttribute("saleNumber", sale.getSaleNumber());
        request.setAttribute("note", sale.getNote());

        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_edit_confirm.jsp");
        dispatcher.forward(request, response);
    }
}
