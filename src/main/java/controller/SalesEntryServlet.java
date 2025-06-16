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

import DAO.AccountDao;
import DAO.CategoryDAO;

@WebServlet("/SalesEntryServlet")
public class SalesEntryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SalesEntryServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String salesDate = request.getParameter("salesDate");
        int accountId = Integer.parseInt(request.getParameter("staff"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String tradeName = request.getParameter("tradeName");
        int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
        int saleNumber = Integer.parseInt(request.getParameter("saleNumber"));
        String note = request.getParameter("note");

        // 担当名・カテゴリ名取得（←これが重要！）
        String staffName = AccountDao.getNameById(accountId);
        String categoryName = CategoryDAO.getNameById(categoryId);

        // JSPに渡す
        request.setAttribute("salesDate", salesDate);
        request.setAttribute("staff", accountId);
        request.setAttribute("staffName", staffName);
        request.setAttribute("category", categoryId);
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("tradeName", tradeName);
        request.setAttribute("unitPrice", unitPrice);
        request.setAttribute("saleNumber", saleNumber);
        request.setAttribute("note", note);

        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_entry_confirm.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String, String>> staffList = DAO.AccountDao.getAllAccounts();
        List<Map<String, String>> categoryList = DAO.CategoryDAO.getActiveCategories();

        request.setAttribute("staffList", staffList);
        request.setAttribute("categoryList", categoryList);

        request.getRequestDispatcher("sales_entry.jsp").forward(request, response);
    }


}
