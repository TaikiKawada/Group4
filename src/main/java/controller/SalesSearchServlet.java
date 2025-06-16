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
import DAO.SaleDAO;
import DTO.SalesDto;

@WebServlet("/SalesSearchServlet")
public class SalesSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 検索フォームの表示
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String, String>> staffList = AccountDao.getAllAccounts();
        List<Map<String, String>> categoryList = CategoryDAO.getActiveCategories();

        request.setAttribute("staffList", staffList);
        request.setAttribute("categoryList", categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_search_form.jsp");
        dispatcher.forward(request, response);
    }

    // 検索処理の実行
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String staff = request.getParameter("staff");
        String category = request.getParameter("category");
        String productName = request.getParameter("productName");
        String note = request.getParameter("note");
        
        System.out.println("[SalesSearchServlet] 受け取った検索パラメータ:");
        System.out.println("fromDate = " + fromDate);
        System.out.println("toDate = " + toDate);
        System.out.println("staff = " + staff);
        System.out.println("category = " + category);
        System.out.println("productName = " + productName);
        System.out.println("note = " + note);

        List<SalesDto> resultList = SaleDAO.searchSales(fromDate, toDate, staff, category, productName, note);

        request.setAttribute("resultList", resultList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_search_result.jsp");
        dispatcher.forward(request, response);
    }
}
