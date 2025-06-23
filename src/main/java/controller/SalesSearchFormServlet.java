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

@WebServlet("/S0020.html")
public class SalesSearchFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("staffList", AccountDao.getAllAccounts());
        request.setAttribute("categoryList", CategoryDAO.getActiveCategories());

        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_search_form.jsp");
        dispatcher.forward(request, response);
    }
}

