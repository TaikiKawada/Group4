package dto;

public class LoginResultDto {
	private boolean success = false;
	private String emailError;
	private String passwordError;
	private AccountDto loginUser;
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
    // エラーメッセージ
    public String getEmailError() {
        return emailError;
    }
    
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }
    
	public void setPasswordError(String passwordError) {
	        this.passwordError = passwordError;
	    }
	
    // ログイン成功時のユーザー情報
    public AccountDto getLoginUser() {
        return loginUser;
    }
    
    public void setLoginUser(AccountDto loginUser) {
        this.loginUser = loginUser;
    }
}
