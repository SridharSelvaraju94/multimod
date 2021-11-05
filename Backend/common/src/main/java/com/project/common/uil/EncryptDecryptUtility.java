
package com.project.common.uil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.project.common.exception.BusinessException;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EncryptDecryptUtility {

	/** The logger. */
	private static final Logger LOGGER = LogManager
			.getLogger(EncryptDecryptUtility.class);

	/** The Constant BLOWFISH. */
	private static final String BLOWFISH = "Blowfish";
	/** The Constant secretKey. */
	private static final String SECRETKEY = "TheBestSecretKey";

	/**
	 * Instantiates a new m2 m encrypt decrypt utilities.
	 */
	private EncryptDecryptUtility() {

	}

	/**
	 * Encrypt.
	 * 
	 * @param data
	 *            the data
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String encrypt(String data) {

		byte[] encVal = null;
		try {
			final Key key = generateKey();
			final Cipher c = Cipher.getInstance(BLOWFISH);
			c.init(1, key);

			encVal = c.doFinal(data.getBytes("UTF-8"));
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return Base64.encodeBase64String(encVal);
	}

	/**
	 * Decrypt.
	 * 
	 * @param encryptedData
	 *            the encrypted data
	 * @return the string
	 * @throws InvalidKeyException
	 * @throws Exception
	 *             the exception
	 */
	public static String decrypt(String encryptedData)
			throws BusinessException {
		final String encryptedValue = removePaddingData(encryptedData);
		String decryptValue = null;
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(BLOWFISH);
			c.init(2, key);
			byte[] decordedValue = Base64.decodeBase64(encryptedValue);
			byte[] decValue = c.doFinal(decordedValue);
			decryptValue = new String(decValue, "US-ASCII");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			LOGGER.error(e);
		} catch (InvalidKeyException e) {
			LOGGER.error(e);
		}
		return decryptValue;
	}

	/**
	 * Removes the padding data.
	 * 
	 * @param encryptedData
	 *            the encrypted data
	 * @return the string
	 */
	private static String removePaddingData(String encryptedData) {
		String d = "";
		if (encryptedData.length() != 0) {
			final String a = encryptedData.replace("%2B", "+");
			final String b = a.replace("%2F", "/");
			d = b.replace("%3D", "=");
		}
		return d;
	}

	/**
	 * Generate key.
	 * 
	 * @return the key
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 *             the exception
	 */
	private static Key generateKey() throws UnsupportedEncodingException {
		return new SecretKeySpec(SECRETKEY.getBytes("UTF-8"), BLOWFISH);
	}

}
