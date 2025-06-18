package services;

import java.util.ArrayList;
import java.util.List;

import DAO.AccountDao;
import DAO.SaleDAO;
import DTO.SalesDto;
import beans.Sale;

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
}
