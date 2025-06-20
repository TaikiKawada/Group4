package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import beans.Sale;
import dao.AccountDao;
import dao.SaleDAO;
import dto.SalesDto;
import utils.Db;

public class SaleService {

    public List<SalesDto> getSalesWithAccountNames() throws Exception {
        List<Sale> sales = SaleDAO.getAllSales();
        AccountDao accountDao = new AccountDao();
        List<SalesDto> salesWithNames = new ArrayList<>();

        for (Sale s : sales) {
            String accountName = accountDao.getNameById(s.getAccountId());

            SalesDto dto = new SalesDto(
                s.getSaleId(),
                s.getSaleDate().toString(),
                s.getAccountId(),
                s.getCategoryId(),
                s.getTradeName(),
                s.getUnitPrice(),
                s.getSaleNumber(),
                s.getNote(),
                accountName,
                null // categoryNameは未使用ならnull
            );

            salesWithNames.add(dto);
        }

        return salesWithNames;
    }
    /*
     * カテゴリ別売上合計を取得するメソッド
     * @return カテゴリ名 → 売上合計金額（unitPrice * saleNumber）
     * @throws Exception
     */
    public Map<String,Integer>getCategorySalesSum()throws Exception{
    	Map<String,Integer>categorySales=new LinkedHashMap<>();
    	
    	 String sql = "SELECT c.category_name, SUM(s.unit_price * s.sale_number) AS total_sales " +
                 "FROM sales s " +
                 "JOIN categories c ON s.category_id = c.category_id " +
                 "GROUP BY c.category_name " +
                 "ORDER BY total_sales DESC";
    	 
    	 try (Connection conn = Db.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String categoryName = rs.getString("category_name");
                    int totalSales = rs.getInt("total_sales");
                    categorySales.put(categoryName, totalSales);
                }
            }

            return categorySales;
        }
    }

