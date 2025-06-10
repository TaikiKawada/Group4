package beans;

public class Accounts {
	int account_id;
	String name;
	String mail;
	String password;
	
	public Accounts(int account_id, String name, String mail, String password) {
		this.account_id = account_id;
		this.name = name;
		this.mail = mail;
		this.password = password;
	}
	
	public Accounts(String name, String mail, String password) {
		this.name = name;
		this.mail = mail;
		this.password = password;
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
	
	
}
