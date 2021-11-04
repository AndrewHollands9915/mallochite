package mallochite.encryption;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SecretKeyGenerator {
	
	public static String  GenerateKey() throws NoSuchAlgorithmException {
		
		SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		return encodedKey;
	}

}
