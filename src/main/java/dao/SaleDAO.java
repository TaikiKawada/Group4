package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Sale;           // 既存のSaleクラス（表示などで使用）
import dto.SalesDto;
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

    public static List<SalesDto> searchSales(String fromDate, String toDate, String staff, String category, String tradeName, String note) {
        List<SalesDto> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s.*, a.name AS account_name, c.category_name AS category_name ");
        sql.append("FROM sales s ");
        sql.append("LEFT JOIN accounts a ON s.account_id = a.account_id ");
        sql.append("LEFT JOIN categories c ON s.category_id = c.category_id ");
        sql.append("WHERE 1=1");

        List<Object> params = new ArrayList<>();

        if (fromDate != null && !fromDate.isEmpty()) {
            sql.append(" AND s.sale_date >= ?");
            params.add(fromDate);
        }
        if (toDate != null && !toDate.isEmpty()) {
            sql.append(" AND s.sale_date <= ?");
            params.add(toDate);
        }
        if (staff != null && !staff.isEmpty()) {
            sql.append(" AND s.account_id = ?");
            params.add(Integer.parseInt(staff));
        }
        if (category != null && !category.isEmpty()) {
            sql.append(" AND s.category_id = ?");
            params.add(Integer.parseInt(category));
        }
        if (tradeName != null && !tradeName.isEmpty()) {
            sql.append(" AND s.trade_name LIKE ?");
            params.add("%" + tradeName + "%");
        }
        if (note != null && !note.isEmpty()) {
            sql.append(" AND s.note LIKE ?");
            params.add("%" + note + "%");
        }

        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

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
                    rs.getString("note"),
                    rs.getString("account_name"),
                    rs.getString("category_name")
                );
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public static SalesDto getSaleById(int saleId) {
    	SalesDto dto = null;

    	String sql = "SELECT s.*, a.name AS account_name, c.category_name AS category_name "
    	           + "FROM sales s "
    	           + "LEFT JOIN accounts a ON s.account_id = a.account_id "
    	           + "LEFT JOIN categories c ON s.category_id = c.category_id "
    	           + "WHERE s.sale_id = ?";

    	try (Connection conn = Db.getConnection();
    	     PreparedStatement stmt = conn.prepareStatement(sql)) {

    		stmt.setInt(1, saleId);
    		ResultSet rs = stmt.executeQuery();

    		if (rs.next()) {
    			dto = new SalesDto(
    				rs.getInt("sale_id"),
    				rs.getString("sale_date"),
    				rs.getInt("account_id"),
    				rs.getInt("category_id"),
    				rs.getString("trade_name"),
    				rs.getInt("unit_price"),
    				rs.getInt("sale_number"),
    				rs.getString("note"),
    				rs.getString("account_name"),
    				rs.getString("category_name")
    			);
    		}

    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	return dto;
    }
    
    public static boolean update(SalesDto dto) {
        String sql = "UPDATE sales SET sale_date = ?, account_id = ?, category_id = ?, "
                   + "trade_name = ?, unit_price = ?, sale_number = ?, note = ? "
                   + "WHERE sale_id = ?";

        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dto.getSaleDate());
            stmt.setInt(2, dto.getAccountId());
            stmt.setInt(3, dto.getCategoryId());
            stmt.setString(4, dto.getTradeName());
            stmt.setInt(5, dto.getUnitPrice());
            stmt.setInt(6, dto.getSaleNumber());
            stmt.setString(7, dto.getNote());
            stmt.setInt(8, dto.getSaleId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean deleteById(int saleId) {
        String sql = "DELETE FROM sales WHERE sale_id = ?";

        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, saleId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



} 
