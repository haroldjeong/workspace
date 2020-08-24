package go.gg.common.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.apache.commons.lang3.CharEncoding.UTF_8;

/**
 * DEEP.FINE 공통 암복호화 유틸리티
 * @author jm.lee (DEEP.FINE)
 * @since 2020.08.08
 * @version 1.0.0
 */
public class SecurityUtils {

	private final static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
	private static String secretKey = "";

	public static void setSecretKey (String key) {
		logger.debug("Set Secret Key: {}", key);
		secretKey = key;
	}

	/**
	 * @apiNote AES-256 암호화
	 * @param str 암호화 대상 문자열
	 * @return 암호화 값
	 */
	public static String encrypt(String str) {
		String encStr = "";

		try {
			String IV = secretKey.substring(0, 16);

			byte[] keyData = secretKey.getBytes();
			SecretKey secureKey = new SecretKeySpec(keyData, "AES");

			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));

			byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
			encStr = new String(Base64.encodeBase64(encrypted));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Encrypt Error: {}", e.getMessage());
		}
		return encStr;
	}

	/**
	 * @apiNote AES-256 복호화
	 * @param str 복호화 대상 문자열
	 * @return 복호화 값
	 */
	public static String decrypt(String str) {
		String decStr = "";

		try {
			String IV = secretKey.substring(0, 16);

			byte[] keyData = secretKey.getBytes();
			SecretKey secureKey = new SecretKeySpec(keyData, "AES");
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes("UTF-8")));

			byte[] byteStr = Base64.decodeBase64(str.getBytes());

			decStr = new String(c.doFinal(byteStr), "UTF-8");

		} catch (Exception e) {
//		} catch (UnsupportedEncodingException
//				| NoSuchAlgorithmException
//				| NoSuchPaddingException
//				| InvalidKeyException
//				| InvalidAlgorithmParameterException
//				| IllegalBlockSizeException
//				| BadPaddingException e) {
			e.printStackTrace();
			logger.error("Decrypt Error: {}", e.getMessage());
		}
		return decStr;
	}

	/**
	 * @apiNote SHA-512 암호화 (with salt)
	 * @return 암호화 값
	 */
	public static String sha512BySalt (String str, String salt) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(salt.getBytes(UTF_8), "HmacSHA512");
			Mac mac = Mac.getInstance("HmacSHA512");
			mac.init(secretKeySpec);
			byte[] bytes = mac.doFinal(str.getBytes(UTF_8));

			return new BigInteger(1, bytes).toString(16);

		} catch (Exception e) {
//		} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("SHA-512 Encrypt Error: {}", e.getMessage());
			return "";
		}
	}

}
