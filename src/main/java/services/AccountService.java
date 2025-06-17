package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.AccountDao;
import DTO.AccountDto;
import utils.Db;
import utils.PasswordUtils;

public class AccountService {
	private AccountDao dao = new AccountDao();
	
	// アカウント登録
	public void signup(AccountDto obj) {
		String sql = "insert into accounts (name, mail, password, authority) values (?, ?, ?, ?)";
		
		try(
			Connection con = Db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			){
			
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getMail());
			
			String hashedPassword = PasswordUtils.hashPassword(obj.getPassword());
			ps.setString(3, hashedPassword);
			ps.setInt(4, obj.getAuth());
			
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

}

