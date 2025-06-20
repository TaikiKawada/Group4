package dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class AccountSearchDto {
	String name;
	String mail;
	List<Integer> authList;

	public AccountSearchDto() {
	}

	public AccountSearchDto(String name, String mail, List<Integer> authList) {
		this.name = name != null ? name : "";
		this.mail = mail != null ? mail : "";
		this.authList = authList != null ? authList : List.of();
	}

	public static AccountSearchDto fromRequest(HttpServletRequest request) {
		AccountSearchDto dto = new AccountSearchDto();
		dto.name = request.getParameter("name");
		dto.mail = request.getParameter("mail");
		String[] authValues = request.getParameterValues("auth");
		dto.authList = new ArrayList<>();
		if (authValues != null) {
			for (String val : authValues) {
				dto.authList.add(Integer.parseInt(val));
			}
		}
		return dto;
	}

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public List<Integer> getAuthList() {
		return authList;
	}

}
