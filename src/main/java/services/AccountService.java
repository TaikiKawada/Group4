package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AccountDao;
import DTO.AccountDto;
import utils.Db;

public class AccountService {
	
	// アカウント登録
	public void signup(AccountDto obj) {
		String sql = "insert into accounts (name, mail, password, authority) values (?, ?, ?, ?)";
		
		try(
			Connection con = Db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			){
			
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getMail());
			ps.setString(3, obj.getPassword());
			ps.setInt(4, obj.getAuth());
			
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	// アカウント検索
	public ArrayList<AccountDto> searchAccounts(String name, String mail, String authStr) {
		Integer auth = null;
		if (authStr != null && !authStr.isEmpty()) {
			try {
				auth = Integer.parseInt(authStr);
		    } catch (NumberFormatException e) {
		    	e.printStackTrace();
		    }
		}
		
		AccountDao dao = new AccountDao();
		return dao.search(name, mail, auth);
	}
}

