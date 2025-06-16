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
import DTO.SalesDto;

@WebServlet("/SalesEditServlet")
public class SalesEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 入力値を取得
        String saleIdStr = request.getParameter("saleId");
        String saleDate = request.getParameter("saleDate");
        String staff = request.getParameter("staff");
        String category = request.getParameter("category");
        String tradeName = request.getParameter("tradeName");
        String unitPriceStr = request.getParameter("unitPrice");
        String saleNumberStr = request.getParameter("saleNumber");
        String note = request.getParameter("note");

        // 入力値を保持するDTO生成
        SalesDto dto = new SalesDto();
        dto.setSaleId(Integer.parseInt(saleIdStr)); // 必ずある前提
        dto.setSaleDate(saleDate);
        dto.setAccountId(parseIntOrZero(staff));
        dto.setCategoryId(parseIntOrZero(category));
        dto.setTradeName(tradeName);
        dto.setUnitPrice(parseIntOrZero(unitPriceStr));
        dto.setSaleNumber(parseIntOrZero(saleNumberStr));
        dto.setNote(note);

        Map<String, String> errors = new HashMap<>();

        // バリデーション
        if (saleDate == null || saleDate.isEmpty()) errors.put("saleDate", "販売日は必須です");
        if (staff == null || staff.isEmpty()) errors.put("staff", "担当者を選択してください");
        if (category == null || category.isEmpty()) errors.put("category", "カテゴリーを選択してください");
        if (tradeName == null || tradeName.trim().isEmpty()) errors.put("tradeName", "商品名は必須です");
        if (!isNumeric(unitPriceStr)) errors.put("unitPrice", "単価は数値で入力してください");
        if (!isNumeric(saleNumberStr)) errors.put("saleNumber", "個数は数値で入力してください");

        // 権限チェック
        String authority = (String) request.getSession().getAttribute("authority");
        if (!"01".equals(authority) && !"11".equals(authority)) {
            errors.put("authority", "この操作を行う権限がありません");
        }

        // エラーがあれば戻す
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("sale", dto); // 入力値を再表示用に
            request.getRequestDispatcher("sales_edit.jsp").forward(request, response);
            return;
        }

        // DB更新
        boolean success = SaleDAO.update(dto);
        if (success) {
            response.sendRedirect("SalesDetailServlet?saleId=" + dto.getSaleId());
        } else {
            request.setAttribute("errorMessage", "更新に失敗しました");
            request.setAttribute("sale", dto);
            request.getRequestDispatcher("sales_edit.jsp").forward(request, response);
        }
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        try { Integer.parseInt(str); return true; }
        catch (NumberFormatException e) { return false; }
    }

    private int parseIntOrZero(String str) {
        try { return Integer.parseInt(str); }
        catch (NumberFormatException e) { return 0; }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 編集対象のsaleId取得とデータ読み込み
            int saleId = Integer.parseInt(request.getParameter("saleId"));
            SalesDto sale = SaleDAO.getSaleById(saleId);

            // ▼ プルダウン用データ取得（←ここがポイント！）
            List<Map<String, String>> staffList = AccountDao.getAllAccounts();
            List<Map<String, String>> categoryList = CategoryDAO.getActiveCategories();

            // JSPに渡す
            request.setAttribute("sale", sale);
            request.setAttribute("staffList", staffList);
            request.setAttribute("categoryList", categoryList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_edit.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "編集画面の表示に失敗しました。");
        }
    }


}
