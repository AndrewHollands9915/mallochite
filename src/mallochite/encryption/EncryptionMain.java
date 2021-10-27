package mallochite.encryption;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptionMain {
	
public static String secretKey;


	public static String encrypt(String message) throws Exception {
		secretKey = SecretKeyGenerator.GenerateKey();
		message = AESEncryption.encrypt(message, secretKey);
		secretKey = RSAEncryption.rsaEncrypt(secretKey);	
		
		return message;
	}
	
	public static String decrypt(String message) throws Exception {
		secretKey = RSAEncryption.rsaDecrypt(secretKey);
		message = AESEncryption.decrypt(message, secretKey);
		
		return message;
	}
	
	
	

}
