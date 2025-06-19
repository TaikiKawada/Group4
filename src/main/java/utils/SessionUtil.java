package utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	
	@SuppressWarnings("unchecked")
	public static <T> T getAttribte(HttpSession session, String key, Class<T> clazz) {
		if(session == null) return null;
		Object value = session.getAttribute(key);
		if(clazz.isInstance(value)) {
			return (T) value;
		}
		return null;
	}
	
	public static <T> void set(HttpServletRequest request, String key, T value) {
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
	}
}
