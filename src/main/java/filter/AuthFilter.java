package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({ "*.html", "/Sales*", "*.jsp" })
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// ログインが必要ないパスは除外
		String path = req.getRequestURI();
		if(path.endsWith("/C0010.html")) {
			chain.doFilter(request, response);
			return;
		}
		
		// ログインしているか確認
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			res.sendRedirect(req.getContextPath() + "/C0010.html");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}
