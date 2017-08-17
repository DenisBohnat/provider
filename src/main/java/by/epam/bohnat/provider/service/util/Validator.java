package by.epam.bohnat.provider.service.util;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Defines methods that help service classes to validate input parameters
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public final class Validator {

	/**
	 * Regular expression to validate the user login
	 */
	private static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9]{4,14}$";

	/**
	 * Regular expression to validate the user password
	 */
	private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9_-]{4,30}$";

	/**
	 * Regular expression to validate the user e-mail
	 */
	private static final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

	/**
	 * Regular expression to validate the user name or surname
	 */
	private static final String USER_NAME_SURNAME_PATTERN = "^[a-zA-Zа-яА-ЯёЁ]+$";

	/**
	 * The minimum length of the user's login
	 */
	private static final int MIN_LENGTH_LOGIN = 4;

	/**
	 * The maximum length of the user's login
	 */
	private static final int MAX_LENGTH_LOGIN = 14;

	/**
	 * The minimum length of the user's password
	 */
	private static final int MIN_LENGTH_PASSWORD = 6;

	/**
	 * The maximum length of the user's password
	 */
	private static final int MAX_LENGTH_PASSWORD = 30;

	/**
	 * The minimum length of the user's name or surname
	 */
	private static final int MIN_LENGTH_USER_NAME_SURNAME = 1;

	/**
	 * The maximum length of the user's name or surname
	 */
	private static final int MAX_LENGTH_USER_NAME_SURNAME = 40;

	/**
	 * The length of the user's phone
	 */
	private static final int PHONE_LENGTH = 9;

	/**
	 * The minimum value of the validate date
	 */
	private static final int MIN_YEAR = 1940;

	/**
	 * Validates integer value which must have positive value
	 * 
	 * @param i
	 *            integer value
	 * @return true if the value is positive, false otherwise
	 */
	public static boolean validateInt(int i) {
		return i > 0;
	}

	/**
	 * Validates input String object
	 * 
	 * @param str
	 *            String object
	 * @return true if input string is not null or empty
	 */
	public static boolean validateString(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Validates input Date object
	 * 
	 * @param date
	 *            Date object
	 * @return true if input date is not null and less than current date
	 */
	public static boolean validateDate(Date date) {
		if (date == null) {
			return false;
		}
		Date currentDate = Date.valueOf(LocalDate.now());

		if (date.after(currentDate)) {
			return false;
		}
		return true;
	}

	/**
	 * Validates input object
	 * 
	 * @param obj
	 *            input odject
	 * @return true if it is not null, false otherwise
	 */
	public static boolean validateObject(Object obj) {
		return obj == null ? false : true;
	}

	/**
	 * Validates id which must have positive value
	 * 
	 * @param id
	 *            integer value
	 * @return true if the value is positive, false otherwise
	 */
	public static boolean validateId(int id) {
		return id > 0;
	}

	/**
	 * Validates input user login
	 * 
	 * @param login
	 *            user login
	 * @return true if input string is not null or empty and matches the
	 *         pattern, false otherwise
	 */
	public static boolean vaidateLogin(String login) {
		if (login == null || login.isEmpty()) {
			return false;
		}
		if (login.length() < MIN_LENGTH_LOGIN || login.length() > MAX_LENGTH_LOGIN) {
			return false;
		}
		Pattern pattern = Pattern.compile(LOGIN_PATTERN);
		Matcher matcher = pattern.matcher(login);

		return matcher.matches();
	}

	/**
	 * Validates input user password
	 * 
	 * @param password
	 *            user password
	 * @return true if input string is not null or empty and matches the
	 *         pattern, false otherwise
	 */
	public static boolean vaidatePassword(String password) {
		if (password == null || password.isEmpty()) {
			return false;
		}
		if (password.length() < MIN_LENGTH_PASSWORD || password.length() > MAX_LENGTH_PASSWORD) {
			return false;
		}
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);

		return matcher.matches();
	}

	/**
	 * Validates input user e-mail
	 * 
	 * @param eMail
	 *            user e-mail
	 * @return true if input string is not null or empty and matches the
	 *         pattern, false otherwise
	 */
	public static boolean validateEmail(String eMail) {
		if (eMail == null || eMail.isEmpty()) {
			return false;
		}
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(eMail);

		return matcher.matches();
	}

	/**
	 * Validates input user name and surname
	 * 
	 * @param str
	 *            user name or surname
	 * @return true if input string is not null or empty and matches the
	 *         pattern, false otherwise
	 */
	public static boolean validateUserNameSurname(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		if (str.length() < MIN_LENGTH_USER_NAME_SURNAME || str.length() > MAX_LENGTH_USER_NAME_SURNAME) {
			return false;
		}
		Pattern pattern = Pattern.compile(USER_NAME_SURNAME_PATTERN);
		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}

	/**
	 * Validates input user phone
	 * 
	 * @param phone
	 *            user phone
	 * @return true if input string is not null or empty and matches the
	 *         pattern, false otherwise
	 */
	public static boolean validatePhone(String phone) {
		if (phone == null || phone.isEmpty()) {
			return false;
		}
		if (phone.length() != PHONE_LENGTH) {
			return false;
		}
		for (int i = 0; i < phone.length(); i++) {
			if (!Character.isDigit(phone.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Validates input user birth date
	 * 
	 * @param date
	 *            user birth date
	 * @return true if input date is not null and less than current date
	 */
	public static boolean validateBirtDate(Date date) {
		Date currentDate = Date.valueOf(LocalDate.now());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if (date.after(currentDate) || calendar.get(Calendar.YEAR) <= MIN_YEAR) {
			return false;
		}

		return true;
	}
}
