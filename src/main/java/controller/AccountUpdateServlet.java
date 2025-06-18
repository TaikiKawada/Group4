package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DTO.AccountDto;
import services.AccountService;

/**
 * アカウント編集確定処理を行うサーブレット（完了画面なし）
 */
@WebServlet("/AccountUpdateServlet")
public class AccountUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountService service = new AccountService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("account_id");
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String authority = request.getParameter("authority");

        try {
            AccountDto dto = new AccountDto(
                    Integer.parseInt(id),
                    name,
                    mail,
                    password,
                    Integer.parseInt(authority)
            );

            boolean result = service.edit(dto);

            // 完了画面は出さずに一覧画面にリダイレクト
            response.sendRedirect("AccountSearchServlet");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "更新中にエラーが発生しました。");
            request.getRequestDispatcher("/WEB-INF/jsp/account/account_edit_confirm.jsp")
                   .forward(request, response);
        }
    }
}
