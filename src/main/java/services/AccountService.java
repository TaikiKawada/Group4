package services;

import java.util.ArrayList;

import dao.AccountDao;
import dto.AccountDto;


public class AccountService {
	private AccountDao dao = new AccountDao();

	// アカウント登録
	public void signup(AccountDto obj) {
		dao.insertAccount(obj);

	}

	// 検索
	public ArrayList<AccountDto> search(String name, String mail, ArrayList<Integer> authList) {
		return dao.searchAccounts(name, mail, authList);
	}

	// IDで1件取得
	public AccountDto findById(int id) {
		return dao.findById(id);
	}

	// 編集
	public boolean edit(AccountDto dto) {
		return dao.editAccount(dto);
	}

	// 削除
	public boolean delete(int id) {
		return dao.deleteAccount(id);
	}
}


