package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

import utils.Db;

public class AccountService {
	public void signup(User obj) {
		String sql = "insert into user (name, mail, pass) values (?, ?, ?)";
		
		try(
			Connection con = Db.open();
			PreparedStatement ps = con.prepareStatement(sql);
			){
			
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getMail());
			ps.setString(3, obj.getPass());
			
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
}
