package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DAO.AccountDao;
import DAO.SaleDAO;
import DTO.AccountDto;
import DTO.SalesDto;
import beans.Sale;

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
		//セッションチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {//個々の"user"はsession.setAttribute("user", loginUser);で確認
		    response.sendRedirect("login.jsp"); // 未ログインならログインページにリダイレクト
		    return;
		}
		
		// user情報からauthority取得
		AccountDto loginUser = (AccountDto) session.getAttribute("user");
		int authority = loginUser.getAuth(); // int型（0〜3）

		try {
            // 売上一覧をSaleで取得
            List<Sale> sales = SaleDAO.getAllSales();

            // AccountDaoを用意
            AccountDao accountDao = new AccountDao();

            // SalesDtoのリストを作成
            List<SalesDto> salesWithNames = new ArrayList<>();

            for (Sale s : sales) {
                String accountName = accountDao.getNameById(s.getAccountId()); // ここで担当者名を取得

                SalesDto dto = new SalesDto(
                        s.getSaleId(),
                        s.getSaleDate().toString(),
                        s.getAccountId(),
                        s.getCategoryId(),
                        s.getTradeName(),
                        s.getUnitPrice(),
                        s.getSaleNumber(),
                        s.getNote(),
                        accountName,
                        null // categoryNameはnullのまま（必要なら別処理）
                );

                salesWithNames.add(dto);
            }

            // JSPに渡す
            request.setAttribute("sales", salesWithNames);
            request.setAttribute("authority", authority);

            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "売上データの取得中にエラーが発生しました。");
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
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
