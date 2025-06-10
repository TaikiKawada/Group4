package Controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Sale;
import beans.SaleDAO;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//セッションチェック//不要かもです
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {//個々の"user"はsession.setAttribute("user", loginUser);で確認
		    response.sendRedirect("login.jsp"); // 未ログインならログインページにリダイレクト
		    return;
		}

		try {
			//SaleDAOを使ってデータを取得
			SaleDAO saleDAO=new SaleDAO();
			List<Sale>sales=saleDAO.getAllSales();//全売上データ取得
			
			//JSPにデータを渡す
			request.setAttribute("sales", sales);
			request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "売上データの取得中にエラーが発生しました。");
			request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
