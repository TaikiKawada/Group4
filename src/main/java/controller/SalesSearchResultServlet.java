package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SaleDAO;
import dto.SalesDto;

@WebServlet("/S0021.html")
public class SalesSearchResultServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        SalesDto searchCondition = new SalesDto(request);
        List<SalesDto> resultList = SaleDAO.searchSales(
            searchCondition.getSaleDate(),             // fromDate
            searchCondition.getToDate(),               // toDate（extraToDateフィールド）
            String.valueOf(searchCondition.getAccountId()),
            String.valueOf(searchCondition.getCategoryId()),
            searchCondition.getTradeName(),
            searchCondition.getNote()
        );

        request.setAttribute("resultList", resultList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_search_result.jsp");
        dispatcher.forward(request, response);
    }
}

