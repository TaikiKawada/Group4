package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.AccountDto;
import utils.Db;

public class AccountDao {
	public ArrayList<AccountDto> search(String name, String mail, Integer auth) {
	    StringBuilder sql = new StringBuilder("SELECT account_id, name, mail, auth FROM accounts WHERE 1=1");
	    ArrayList<AccountDto> accountResearchList = new ArrayList<>();
	    ArrayList<Object> params = new ArrayList<>();

	    if (name != null && !name.trim().isEmpty()) {
	        sql.append(" AND name LIKE ?");
	        params.add("%" + name.trim() + "%");
	    }

	    if (mail != null && !mail.trim().isEmpty()) {
	        sql.append(" AND mail LIKE ?");
	        params.add("%" + mail.trim() + "%");
	    }

	    if (auth != null) {
	        sql.append(" AND (auth & ?) != 0");
	        params.add(auth);
	    }

	    try (
	        Connection con = Db.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql.toString());
	    ) {
	        for (int i = 0; i < params.size(); i++) {
	            ps.setObject(i + 1, params.get(i));
	        }

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                AccountDto ad = new AccountDto();
	                ad.setAccount_id(rs.getInt("account_id"));
	                ad.setName(rs.getString("name"));
	                ad.setMail(rs.getString("mail"));
	                ad.setAuth(rs.getInt("auth"));
	                accountResearchList.add(ad);
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    return accountResearchList;
	}

}
