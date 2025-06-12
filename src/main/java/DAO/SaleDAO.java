package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.SalesDto;        // DTOクラス（登録などで使用）
import beans.Sale;           // 既存のSaleクラス（表示などで使用）
import utils.Db;

public class SaleDAO {

    // すべての売上を取得する（既存処理）
    public static List<Sale> getAllSales() {
        List<Sale> list = new ArrayList<>();

        try (Connection conn = Db.getConnection()) {
            String sql = "SELECT * FROM sales";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Sale s = new Sale();
                s.setSaleId(rs.getInt("sale_id"));
                s.setSaleDate(rs.getDate("sale_date"));
                s.setAccountId(rs.getInt("account_id"));
                s.setCategoryId(rs.getInt("category_id"));
                s.setTradeName(rs.getString("trade_name"));
                s.setUnitPrice(rs.getInt("unit_price"));
                s.setSaleNumber(rs.getInt("sale_number"));
                s.setNote(rs.getString("note"));
                list.add(s);
            }

        } catch (Exception e) {
            System.out.println("発生した例外:" + e.getClass().getName());
            System.out.println("メッセージ:" + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    // 売上を登録する（新規追加）
    public static boolean insert(SalesDto dto) {
        String sql = "INSERT INTO sales "
                   + "(sale_date, account_id, category_id, trade_name, unit_price, sale_number, note) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dto.getSaleDate());
            stmt.setInt(2, dto.getAccountId());
            stmt.setInt(3, dto.getCategoryId());
            stmt.setString(4, dto.getTradeName());
            stmt.setInt(5, dto.getUnitPrice());
            stmt.setInt(6, dto.getSaleNumber());
            stmt.setString(7, dto.getNote());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<SalesDto> searchByProductName(String keyword) {
        List<SalesDto> list = new ArrayList<>();

        String sql = "SELECT * FROM sales WHERE trade_name LIKE ?";

        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SalesDto dto = new SalesDto(
                    rs.getInt("sale_id"),
                    rs.getString("sale_date"),
                    rs.getInt("account_id"),
                    rs.getInt("category_id"),
                    rs.getString("trade_name"),
                    rs.getInt("unit_price"),
                    rs.getInt("sale_number"),
                    rs.getString("note")
                );
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
