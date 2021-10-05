package mallochite.encryption;



public class EncryptionMain {
	
	static String secretKey = "John";
	
	public static String encrypt(String message) throws Exception {
		
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
