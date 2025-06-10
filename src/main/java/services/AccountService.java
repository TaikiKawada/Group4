package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.AccountDto;
import utils.Db;

public class AccountService {
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
}
