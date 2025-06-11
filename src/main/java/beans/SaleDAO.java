package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import src.main.java.utils.Db;

public class SaleDAO {//データベースから全レコードを取得してSaleオブジェクトのリストとして返すクラス
	public static List<Sale>getAllSales(){//sales テーブルの全レコードを List<Sale> で返すメソッド
		List<Sale>list=new ArrayList<>();//DBから取得したレコードを格納するリスト
	
		try(Connection conn=Db.getConnection()){
		String sql="SELECT * FROM sales";
		PreparedStatement ps =conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();//executeQuery() で SELECT 文を実行し、結果を ResultSet で受け取る
		
			while(rs.next()) {
			Sale s=new Sale();
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
		}catch(Exception e){
		System.out.println("発生した例外:"+e.getClass().getName());
		System.out.println("メッセージ:"+e.getMessage());
		e.printStackTrace();//詳細なスタックトレース
		}
		return list;
	}
}