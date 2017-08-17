package by.epam.bohnat.provider.service.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An utility class for hashing purposes. Currently offers only md5 hash.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class Hasher {

	private static final Logger logger = LogManager.getLogger(Hasher.class.getName());

	/**
	 * Calculates a hash value of a given string with md5 algorithm
	 * 
	 * @param value
	 *            String to hash
	 * @return md5 hash value of the string
	 */
	public static String hashMd5(String value) {
		String HashValue = null;
		if (value != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(value.getBytes());
				byte[] bytesData = md.digest();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bytesData.length; i++) {
					sb.append(String.format("%02x", bytesData[i] & 0xff));
				}
				HashValue = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				logger.error("Error in hashing value " + value);
			}
		}
		return HashValue;
	}

}
