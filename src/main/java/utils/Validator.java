package utils;

import jakarta.servlet.http.HttpServletRequest;

public class Validator {
	
	public static void validateName(String name, ValidationResult result) {
		if(name == null || name.isEmpty()) {
			result.addError("name", ErrorMessages.REQUIRED_NAME);
		}else if(name.getBytes().length > 20) {
			result.addError("name", ErrorMessages.INVALID_NAME_LENGTH);
		}
	}
	
	public static void validateEmail(String email, ValidationResult result) {
		if(email == null || email.isEmpty()) {
			result.addError("email", ErrorMessages.REQUIRED_EMAIL);
		}else if(email.length() > 100) {
			result.addError("email", ErrorMessages.INVALID_EMAIL_LENGTH);
		}else if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			result.addError("email", ErrorMessages.INVALID_EMAIL_FORMAT);
		}
	}
	
	public static void validatePassword(String password, String passConfirm, ValidationResult result) {
		if(password == null || password.isEmpty()) {
			result.addError("password", ErrorMessages.REQUIRED_PASSWORD);
		}else if(passConfirm == null || passConfirm.isEmpty()) {
			result.addError("passConfirm", ErrorMessages.REQUIRED_PASSWORD_CONFIRM);			
		}else if(password.length() < 8 || password.length() > 30) {
			result.addError("password", ErrorMessages.INVALID_PASSWORD_LENGTH);
		}else if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,30}$")) {
			result.addError("password", ErrorMessages.INVALID_PASSWORD_FORMAT);
		}else if(!password.equals(passConfirm)) {
			result.addError("passConfirm", ErrorMessages.PASSWORD_MISMATCH);			
		}
	}
	
	public static void isWithinMaxBytes(String str, int maxBytes, ValidationResult result) {
		if(str.getBytes().length > maxBytes) {
			result.addError("error", ErrorMessages.INVALID_LENGTH);
		}
	}
	
	public static void setSystemError(HttpServletRequest request) {
		ValidationResult vr = new ValidationResult();
		vr.addError("error", ErrorMessages.SYSTEM_ERROR);
		request.setAttribute("errors", vr.getErrors());
	}
	
	public static void setAuthenticationFailed(HttpServletRequest request) {
		ValidationResult vr = new ValidationResult();
		vr.addError("error", ErrorMessages.AUTHENTCATION_FAILED);
		request.setAttribute("errors", vr.getErrors());
	}
	
}
