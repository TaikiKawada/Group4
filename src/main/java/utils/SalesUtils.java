package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesUtils {

	private static final String DB_URL = "jdbc:mariadb://localhost:3306/Group4";

    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "root"; 

    public static boolean insertSale(String saleDate, String accountId, String categoryId,
                                     String tradeName, int unitPrice, int saleNumber, String note) {

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO sales (sale_date, account_id, category_id, trade_name, unit_price, sale_number, note) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)")
        ) {
            stmt.setString(1, saleDate);
            stmt.setString(2, accountId);
            stmt.setString(3, categoryId);
            stmt.setString(4, tradeName);
            stmt.setInt(5, unitPrice);
            stmt.setInt(6, saleNumber);
            stmt.setString(7, note);

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
