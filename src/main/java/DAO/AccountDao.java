package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utils.Db;

public class AccountDao {
    public static List<String> getAllAccounts() {
        List<String> list = new ArrayList<>();

        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT name FROM accounts");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


	}

