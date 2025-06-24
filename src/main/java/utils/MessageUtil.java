package utils;

import jakarta.servlet.http.HttpServletRequest;

public class MessageUtil {

	public static void setSuccessMessage(HttpServletRequest request, String message) {
		request.getSession().setAttribute("toastType", "success");
		request.getSession().setAttribute("toastMessage", message);
	}

	public static void setErrorMessage(HttpServletRequest request, String message) {
		request.getSession().setAttribute("toastType", "error");
		request.getSession().setAttribute("toastMessage", message);
	}
	
	public static void flushToRequest(HttpServletRequest request) {
		Object type = request.getSession().getAttribute("toastType");
		Object message = request.getSession().getAttribute("toastMessage");
		
		if(type != null && message != null) {
			request.setAttribute("toastType", type);
			request.setAttribute("toastMessage", message);
			request.getSession().removeAttribute("toastType");
			request.getSession().removeAttribute("toastMessage");			
		}
			
			
	}
}
