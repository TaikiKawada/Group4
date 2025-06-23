package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AccountDao;
import dao.CategoryDAO;
import dto.SalesDto;

@WebServlet("/SalesRegisterServlet")
public class SalesRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        SalesDto dto = new SalesDto(request);

        String staffName = AccountDao.getNameById(dto.getAccountId());
        String categoryName = CategoryDAO.getNameById(dto.getCategoryId());

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
