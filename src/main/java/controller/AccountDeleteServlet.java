package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.AccountService;

/**
 * アカウントを削除するサーブレット（完了画面なし）
 */
@WebServlet("/AccountDeleteServlet")
public class AccountDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountService service = new AccountService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("account_id");

        try {
            int id = Integer.parseInt(idParam);
            service.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 削除完了後は検索画面へリダイレクト
        response.sendRedirect("AccountSearchServlet");
    }
}
