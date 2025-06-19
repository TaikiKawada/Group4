package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.AccountDto;
import utils.Db;

public class LoginDao {
	
	public AccountDto findEmail(String mail) throws SQLException, ClassNotFoundException{
        String sql = "SELECT name, mail, password, authority FROM accounts WHERE mail = ?";
        
        try (Connection conn = Db.getConnection();//データベースと接続
        	PreparedStatement ps = conn.prepareStatement(sql)){
        	
            ps.setString(1, mail);
            
            try (ResultSet rs = ps.executeQuery()) {//結果取得と照合。メールアドレスが登録されているか。
            	 if (rs.next()) {
            		 AccountDto account = new AccountDto();
                     account.setName(rs.getString("name"));
                     account.setMail(rs.getString("mail"));
                     account.setAuth(rs.getInt("authority")); //authorityをintでセット
                     account.setPassword(rs.getString("password"));
                     return account;
                     }
                 }
             }
        return null;
    }
}