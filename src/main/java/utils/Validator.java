package utils;

import java.nio.charset.StandardCharsets;

public class Validator {
	
	// 空チェック
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	// 名前長さチェック
	public static boolean isValidName(String name) {
		return name != null && name.getBytes(StandardCharsets.UTF_8).length < 21;
	}
	
	// メールアドレス長さチェック
	public static boolean isValidMail(String mail) {
		return mail != null && mail.getBytes(StandardCharsets.UTF_8).length < 101;
	}
	
	// パスワード形式チェック
	public static boolean isValidPassword(String password) {
		return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,30}$");
	}	
	
	// メールアドレス形式チェック
	public static boolean isValidEmail(String email) {
		return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
	}
	
	// パスワードの一致チェック
	public static boolean isPasswordConfirmed(String password, String confirm) {
		return password != null && password.equals(confirm);
	}
}
