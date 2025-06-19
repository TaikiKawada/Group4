package utils;

import jakarta.servlet.http.HttpServletRequest;

import dto.AccountDto;

public class AuthUtil {
	
	// チェックボックス表示用フラグをリクエストにセット
	public static void setAuthorityAttributes(HttpServletRequest request, int auth) {
		request.setAttribute("hasNoneAuth", auth == 0);
		request.setAttribute("hasSalesAuth", (auth & 1) != 0);
		request.setAttribute("hasAccountAuth", (auth & 2)!= 0);
	}
	
	// アカウント登録権限を持っているか（bit 1が立っているか）
	public static boolean hasAccountEditPermission(AccountDto user) {
		return user != null && (user.getAuth() & 0b10) != 0;
	}
	
}
