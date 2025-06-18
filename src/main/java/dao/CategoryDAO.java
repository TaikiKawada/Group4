package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Db;

public class CategoryDAO {
    public static List<Map<String, String>> getActiveCategories() {
        List<Map<String, String>> list = new ArrayList<>();

        String sql = "SELECT category_id, category_name FROM categories WHERE active_flg = 1";

        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(rs.getInt("category_id")));
                map.put("name", rs.getString("category_name"));
                list.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public static String getNameById(int id) {
        String name = "";
        String sql = "SELECT category_name FROM categories WHERE category_id = ?";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("category_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }

}
