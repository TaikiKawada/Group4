package utils;

import java.nio.charset.StandardCharsets;

import jakarta.servlet.http.HttpServletRequest;

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
	
	//パスワード長さチェックメソッド
    public static boolean isValidPasswordLength(String password) {
    	 if (password == null) return false;

    	    try {
    	        // パスワードのバイト長を取得（UTF-8エンコード想定）
    	        int byteLength = password.getBytes("UTF-8").length;

    	        // 1文字以上かつ30バイト以内かをチェック
    	        return byteLength > 0 && byteLength <= 30;
    	    } catch (java.io.UnsupportedEncodingException e) {
    	        e.printStackTrace();
    	        return false;
    	    }
    }
    
    // システムエラー共通処理
    public static void setSystemError(HttpServletRequest request) {
        request.setAttribute("systemError", true);
        request.setAttribute("error", "システムエラーが発生しました。");
    }

    // 入力ミスなどのエラー共通処理
    public static void setAuthenticationFailed(HttpServletRequest request) {
        request.setAttribute("error", "メールアドレス、パスワードを正しく入力してください。");
    }
}
