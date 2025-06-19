package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.AccountDto;
import utils.Db;
import utils.PasswordUtils;

public class AccountDao {

	// アカウント登録
	public void insertAccount(AccountDto obj) {
		String sql = "insert into accounts (name, mail, password, authority) values (?, ?, ?, ?)";

		try (Connection con = Db.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, obj.getName());
			ps.setString(2, obj.getMail());
			
			String hashedPassword = PasswordUtils.hashPassword(obj.getPassword());
			ps.setString(3, hashedPassword);

			ps.setInt(4, obj.getAuth());

			ps.executeUpdate();

			// 例外処理
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<Map<String, String>> getAllAccounts() {
		List<Map<String, String>> list = new ArrayList<>();
		try (Connection conn = Db.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT account_id, name FROM accounts");
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put("id", String.valueOf(rs.getInt("account_id")));
				map.put("name", rs.getString("name"));
				list.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// アカウント検索
	public ArrayList<AccountDto> searchAccounts(String name, String mail, List<Integer> authList) {
		ArrayList<AccountDto> resultList = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM accounts WHERE 1=1");
		ArrayList<Object> params = new ArrayList<>();

		// 可変条件をSQLに追加
		if (name != null && !name.isEmpty()) {
			sql.append(" and name like ?");
			params.add("%" + name + "%");
		}

		if (mail != null && !mail.isEmpty()) {
			sql.append(" and mail like ?");
			params.add("%" + mail + "%");
		}

		if (authList != null && !authList.isEmpty()) {
			boolean containsZero = false;
			int requiredBits = 0;

			for (Integer val : authList) {
				if (val == 0) {
					containsZero = true;
				} else {
					requiredBits |= val;
				}
			}

			if (containsZero && requiredBits == 0) {
				sql.append(" and cast(authority as unsigned) = 0");
			} else if (containsZero) {
				sql.append(" and (cast(authority as unsigned) = 0 or (cast(authority as unsigned) & ?) = ?)");
				params.add(requiredBits);
				params.add(requiredBits);
			} else {
				sql.append(" and (cast(authority as unsigned) & ?) = ?");
				params.add(requiredBits);
				params.add(requiredBits);
			}
		}

//		// 処理の確認
//		System.out.println("実行SQL: " + sql.toString());
//		System.out.println("パラメータ: " + params);

		try (
				Connection conn = Db.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql.toString())) {

			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}

			// SQL文の実行、resultListに格納
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AccountDto ad = new AccountDto(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getInt("authority"));
				resultList.add(ad);
			}

			// 例外処理
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	// account_idと一致するアカウントの検索
	public AccountDto findById(int accountId) {
		String sql = "select * from accounts where account_id = ?";
		AccountDto account = new AccountDto();

		try (Connection con = Db.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, accountId);

			// SQL文の実行、accountに格納
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				account.setAccount_id(rs.getInt("account_id"));
				account.setName(rs.getString("name"));
				account.setMail(rs.getString("mail"));
				account.setPassword(rs.getString("password"));
				account.setAuth(rs.getInt("authority"));
			}
			// 例外処理
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return account;
	}

	// アカウント編集
	public boolean editAccount(AccountDto account) {
		String sql = "update accounts set name = ?, mail = ?, password = ?, authority = ? where account_id = ?";

		try (Connection con = Db.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, account.getName());
			ps.setString(2, account.getMail());
			ps.setString(3, account.getPassword());
			ps.setInt(4, account.getAuth());
			ps.setInt(5, account.getAccount_id());

			// SQL文の実行
			int result = ps.executeUpdate();

			// 更新出来たらtrueを返す
			return result > 0;

			// 例外処理
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	// アカウント削除
	public boolean deleteAccount(int accountId) {
		String sql = "delete from accounts where account_id = ?";

		try (Connection con = Db.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, accountId);

			// SQL文の実行
			int result = ps.executeUpdate();

			// 削除出来たらtrueを返す
			return result > 0;

			// 例外処理
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static String getNameById(int id) {
	    String name = "";
	    String sql = "SELECT name FROM accounts WHERE account_id = ?";

	    try (Connection conn = Db.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            name = rs.getString("name");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    

	    return name;
	}

}
