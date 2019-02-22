package com.supafit.common.security;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

public class PasswordEncryptionUtil {

	 private static final byte[] linebreak = {}; // Remove Base64 encoder default linebreak
	    private static Cipher cipher;
	    private static final Base32 coder = new Base32(18, linebreak, true);

	    static {
	        try {
	            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	    }
	    
	   
	    /*
	     * Encrypt's the normal string, using AES, 
	     * and the value can be decrypted later.
	     */
	    public static synchronized String encryptString(String plainText, String privateKey) {
	        String encryptedString = null;
	        try {
	            SecretKey secretKey = new SecretKeySpec(privateKey.getBytes(), "AES");
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	            byte[] cipherText = cipher.doFinal(plainText.getBytes());
	            encryptedString = new String(coder.encode(cipherText));
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return encryptedString;
	    }

	    public static synchronized String decryptString(String codedText, String privateKey) throws Exception {
	        byte[] encypted = coder.decode(codedText.getBytes());
	        SecretKey secretKey = new SecretKeySpec(privateKey.getBytes(), "AES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        byte[] decrypted = cipher.doFinal(encypted);
	        return new String(decrypted);
	    }
	    
	    /* Encrypt password function does a SHA-1 digest of the password string
	     * and there is no way to decrypt it back.
	     * 
	     */
	    public static synchronized String encryptPassword(String password) throws java.security.NoSuchAlgorithmException
	    {
	         MessageDigest d = MessageDigest.getInstance("SHA-1");
	         d.reset();
	         d.update(password.getBytes());
	         byte[] encryptedPwd = d.digest();
	         return Base64.encodeBase64String(encryptedPwd);
	    }
}
