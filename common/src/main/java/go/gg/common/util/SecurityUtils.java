package go.gg.common.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.commons.lang3.CharEncoding.UTF_8;

/**
 * DEEP.FINE 공통 암복호화 유틸리티
 * @author jm.lee (DEEP.FINE)
 * @since 2020.08.08
 * @version 1.0.0
 */
public class SecurityUtils {

	private final static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

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

		} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
			logger.error("[SecurityUtils] error on sha512BySalt(): {}", e.getMessage());
			return "";
		}
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException
	{
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if(paddingLength > 0)
		{
			return String.format("%0"  +paddingLength + "d", 0) + hex;
		}else{
			return hex;
		}
	}
}
