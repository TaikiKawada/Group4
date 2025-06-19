package dto;

import jakarta.servlet.http.HttpServletRequest;

public class AccountDto {
	int account_id;
	String name;
	String mail;
	String password;
	int auth;
	boolean is_deleted;
	

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
	
	public static AccountDto fromRequest(HttpServletRequest request) {
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String[] authValues = request.getParameterValues("auth");
		
		//権限のビット値を計算
		int auth = 0;
		if(authValues != null) {
			for(String val : authValues) {
				auth |= Integer.parseInt(val);
			}
		}
		return new AccountDto(name, mail, password, auth);
	}

	public boolean hasNoneAuth() {
		return auth == 0;
	}
	
	public boolean hasSalesAuth() {
		return (auth & 1) != 0;
	}
	
	public boolean hasAccountAuth() {
		return (auth & 2) != 0;
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
	
	public boolean isIs_deleted() {
		return is_deleted;
	}
	
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}	
}
