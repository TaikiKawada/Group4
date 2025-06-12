package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.SaleDAO;
import DTO.SalesDto;

@WebServlet("/SalesSearchServlet")
public class SalesSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SalesSearchServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String keyword = request.getParameter("keyword");

        // DAOで検索
        List<SalesDto> results = SaleDAO.searchByProductName(keyword);

        // JSPへ渡す
        request.setAttribute("resultList", results);
        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_search_result.jsp");
        dispatcher.forward(request, response);
    }
}
