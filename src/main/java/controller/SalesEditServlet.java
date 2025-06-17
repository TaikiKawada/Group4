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

import DAO.AccountDao;
import DAO.CategoryDAO;
import DAO.SaleDAO;
import DTO.AccountDto;
import DTO.SalesDto;

@WebServlet("/SalesEditServlet")
public class SalesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 編集画面表示（GET）
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int saleId = Integer.parseInt(request.getParameter("saleId"));
            SalesDto sale = SaleDAO.getSaleById(saleId); // ← DBから売上情報取得

            List<Map<String, String>> staffList = AccountDao.getAllAccounts();
            List<Map<String, String>> categoryList = CategoryDAO.getActiveCategories();

            request.setAttribute("sale", sale); // ← sale オブジェクトを渡す
            request.setAttribute("staffList", staffList);
            request.setAttribute("categoryList", categoryList);

            // ★ もし JPS 側で ${salesDate} を使ってるなら下記も追加（なくてもいい）
            request.setAttribute("salesDate", sale.getSaleDate());

            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_edit.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    // 編集内容確認へ（POST）
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Map<String, String> errors = new HashMap<>();

        // 入力取得
        String saleIdStr = request.getParameter("saleId");
        String saleDate = request.getParameter("saleDate");
        String staffStr = request.getParameter("staff");
        String categoryStr = request.getParameter("category");
        String tradeName = request.getParameter("tradeName");
        String unitPriceStr = request.getParameter("unitPrice");
        String saleNumberStr = request.getParameter("saleNumber");
        String note = request.getParameter("note");

        // 入力チェック
        if (saleDate == null || saleDate.isEmpty()) errors.put("saleDate", "販売日を入力してください");
        if (staffStr == null || staffStr.isEmpty()) errors.put("staff", "担当者を選択してください");
        if (categoryStr == null || categoryStr.isEmpty()) errors.put("category", "カテゴリーを選択してください");
        if (tradeName == null || tradeName.isEmpty()) errors.put("tradeName", "商品名を入力してください");
        if (unitPriceStr == null || unitPriceStr.isEmpty()) errors.put("unitPrice", "単価を入力してください");
        if (saleNumberStr == null || saleNumberStr.isEmpty()) errors.put("saleNumber", "個数を入力してください");

        // 権限チェック
        AccountDto user = (AccountDto) request.getSession().getAttribute("user");
        int authority = user != null ? user.getAuth() : 0;
        if (authority < 1) errors.put("authority", "この操作を行う権限がありません");

        // エラーがある場合は sales_edit.jsp に戻す
        if (!errors.isEmpty()) {
            SalesDto sale = new SalesDto(
                Integer.parseInt(saleIdStr),
                saleDate,
                staffStr != null && !staffStr.isEmpty() ? Integer.parseInt(staffStr) : 0,
                categoryStr != null && !categoryStr.isEmpty() ? Integer.parseInt(categoryStr) : 0,
                tradeName,
                unitPriceStr != null && !unitPriceStr.isEmpty() ? Integer.parseInt(unitPriceStr) : 0,
                saleNumberStr != null && !saleNumberStr.isEmpty() ? Integer.parseInt(saleNumberStr) : 0,
                note
            );

            request.setAttribute("sale", sale);
            request.setAttribute("errors", errors);
            request.setAttribute("staffList", AccountDao.getAllAccounts());
            request.setAttribute("categoryList", CategoryDAO.getActiveCategories());

            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_edit.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // 正常時：確認画面へ値を渡してフォワード
        int saleId = Integer.parseInt(saleIdStr);
        int staff = Integer.parseInt(staffStr);
        int category = Integer.parseInt(categoryStr);
        int unitPrice = Integer.parseInt(unitPriceStr);
        int saleNumber = Integer.parseInt(saleNumberStr);

        String staffName = AccountDao.getNameById(staff);
        String categoryName = CategoryDAO.getNameById(category);

        request.setAttribute("saleId", saleId);
        request.setAttribute("saleDate", saleDate);
        request.setAttribute("staff", staff);
        request.setAttribute("staffName", staffName);
        request.setAttribute("category", category);
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("tradeName", tradeName);
        request.setAttribute("unitPrice", unitPrice);
        request.setAttribute("saleNumber", saleNumber);
        request.setAttribute("note", note);

        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_edit_confirm.jsp");
        dispatcher.forward(request, response);
    }
}
