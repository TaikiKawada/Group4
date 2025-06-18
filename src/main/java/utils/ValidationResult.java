package utils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
	private Map<String, String> errorMessages = new HashMap<>();
	
	public void addError(String key, String message) {
		errorMessages.put(key, message);
	}
	
	public boolean hasErrors() {
		return !errorMessages.isEmpty();
	}
	
	public Map<String, String> getErrors() {
		return errorMessages;
	}
}
