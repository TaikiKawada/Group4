package Controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SalesEntryServlet")
public class SalesEntryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SalesEntryServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力値の取得
        String salesDate = request.getParameter("salesDate");
        String staff = request.getParameter("staff");
        String category = request.getParameter("category");
        String productName = request.getParameter("productName");
        String unitPrice = request.getParameter("unitPrice");
        String quantity = request.getParameter("quantity");
        String remarks = request.getParameter("remarks");

        // 値をリクエストスコープに格納
        request.setAttribute("salesDate", salesDate);
        request.setAttribute("staff", staff);
        request.setAttribute("category", category);
        request.setAttribute("productName", productName);
        request.setAttribute("unitPrice", unitPrice);
        request.setAttribute("quantity", quantity);
        request.setAttribute("remarks", remarks);

        // 確認画面へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("sales_entry_confirm.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GETメソッドはサポートされていません。");
    }

}
