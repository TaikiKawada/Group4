package utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	
	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(HttpSession session, String key, Class<T> clazz) {

		if(session == null) return null;
		Object value = session.getAttribute(key);
		if(clazz.isInstance(value)) {
			return (T) value;
		}
		return null;
	}
	
	public static Object get(HttpServletRequest request, String key) {
		HttpSession session = request.getSession(false);
		return (session != null) ? session.getAttribute(key) : null;
	}
	
	public static <T> void set(HttpServletRequest request, String key, T value) {
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
	}
	
	public static void remove(HttpServletRequest request, String key) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute(key);
		}
	}
	
	public static void invalidate(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
	}
}
