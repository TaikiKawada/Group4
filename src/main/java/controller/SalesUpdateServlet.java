package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.SaleDAO;
import DTO.SalesDto;

/**
 * Servlet implementation class SalesUpdateServlet
 */
@WebServlet("/SalesUpdateServlet")
public class SalesUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    int saleId = Integer.parseInt(request.getParameter("saleId"));
	    String saleDate = request.getParameter("saleDate");
	    int accountId = Integer.parseInt(request.getParameter("staff"));
	    int categoryId = Integer.parseInt(request.getParameter("category"));
	    String tradeName = request.getParameter("tradeName");
	    int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
	    int saleNumber = Integer.parseInt(request.getParameter("saleNumber"));
	    String note = request.getParameter("note");

	    SalesDto dto = new SalesDto(saleId, saleDate, accountId, categoryId, tradeName, unitPrice, saleNumber, note);
	    boolean result = SaleDAO.update(dto);

	    if (result) {
	        response.sendRedirect("SalesDetailServlet?saleId=" + saleId);
	    } else {
	        request.setAttribute("error", "更新に失敗しました");
	        request.getRequestDispatcher("sales_edit_confirm.jsp").forward(request, response);
	    }
	}


}
