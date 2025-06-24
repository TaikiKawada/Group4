package controller;

import java.io.IOException;
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
import dto.SalesDto;

@WebServlet("/SalesEntryServlet")
public class SalesEntryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SalesEntryServlet() {
        super();
    }

    // 登録フォーム表示
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String, String>> staffList = AccountDao.getAccounts();
        List<Map<String, String>> categoryList = CategoryDAO.getActiveCategories();

        request.setAttribute("staffList", staffList);
        request.setAttribute("categoryList", categoryList);

        request.getRequestDispatcher("sales_entry.jsp").forward(request, response);
    }

    // 登録確認画面へ遷移
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        SalesDto dto = new SalesDto(request); // DTOでまとめて取得

        // 担当名・カテゴリ名を取得
        String staffName = AccountDao.getNameById(dto.getAccountId());
        String categoryName = CategoryDAO.getNameById(dto.getCategoryId());

        // JSPに渡す
        request.setAttribute("salesDate", dto.getSaleDate());
        request.setAttribute("staff", dto.getAccountId());
        request.setAttribute("staffName", staffName);
        request.setAttribute("category", dto.getCategoryId());
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("tradeName", dto.getTradeName());
        request.setAttribute("unitPrice", dto.getUnitPrice());
        request.setAttribute("saleNumber", dto.getSaleNumber());
        request.setAttribute("note", dto.getNote());

        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_entry_confirm.jsp");
        dispatcher.forward(request, response);
    }
}

