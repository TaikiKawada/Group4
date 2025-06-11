package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import utils.Db;

public class LoginService {
    public boolean authenticate(HttpServletRequest request) {
        String email = request.getParameter("mail");
        String password = request.getParameter("password");

//        データベースと接続して、accounts表からemail(mail)とpasswordを取得
        try (Connection conn = Db.getConnection()) {
            String sql = "SELECT * FROM accounts WHERE mail = ? AND password = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);

//                認証に成功した場合にセッションにユーザー名を保存してログイン処理を行う 
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", rs.getString("name"));
                        return true;
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
