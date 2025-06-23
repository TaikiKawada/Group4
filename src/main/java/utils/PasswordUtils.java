package utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	//パスワードをハッシュ化するメソッド
    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    //ハッシュと平文パスワードを照合するメソッド
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
    
}
