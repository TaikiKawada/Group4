package dto;

public class AccountDto {
	int account_id;
	String name;
	String mail;
	String password;
	int auth;
	
	
	
	public AccountDto() {
	}

	public AccountDto(String name, String mail, String password, int auth) {
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.auth = auth;
	}
	
	public AccountDto(int account_id, String name, String mail, String password, int auth) {
		this.account_id = account_id;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.auth = auth;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}
	
	
	
	
}
