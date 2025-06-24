package utils;

import jakarta.servlet.http.HttpServletRequest;

public class MessageUtil {

	public static void setSuccessMessage(HttpServletRequest request, String message) {
		if (message != null && !message.trim().isEmpty()) {
			request.getSession().setAttribute("alertType", "success");
			request.getSession().setAttribute("alertMessage", message);
		}
	}

	public static void setErrorMessage(HttpServletRequest request, String message) {
		if (message != null && !message.trim().isEmpty()) {
			request.getSession().setAttribute("alertType", "error");
			request.getSession().setAttribute("alertMessage", message);
		}
	}

	public static void flushToRequest(HttpServletRequest request) {
		Object type = request.getSession().getAttribute("alertType");
		Object message = request.getSession().getAttribute("alertMessage");

		if (type != null && message != null) {
			request.setAttribute("alertType", type);
			request.setAttribute("alertMessage", message);
			request.getSession().removeAttribute("alertType");
			request.getSession().removeAttribute("alertMessage");
		}

	}
}
