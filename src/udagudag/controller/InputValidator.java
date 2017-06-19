package udagudag.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

	public boolean isDateValid(String date, String format) {
		if (date.length() != format.length()) {
			return false;
		}
		try {
			DateFormat df = new SimpleDateFormat(format);
			df.setLenient(false);
			
			// Exception is not thrown here if day and month is
			// correct AND the first char of year is a digit.
			df.parse(date);

			// So if we have correct day and correct month
			// and we know the year has 4 chars we can try to parse it.
			Integer year = Integer.parseInt(date.substring(6, 10));

			// Here we know that the year is 4 digit number and we can limit it.
			if (year >= 1900 && year <= 2015) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isPasswordValid(String password) {
		if (password.length() > 50) {
			return false;
		}
		String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{5,10}";
		Pattern pattern = Pattern.compile(passwordPattern);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public boolean isEmailValid(final String email) {
		if (email.length() > 50) {
			return false;
		}
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public boolean isNameValid(String firstName) {
		if (firstName.length() < 2 || firstName.length() > 50) {
			return false;
		} else {
			char[] array = firstName.toCharArray();
			if (!(array[0] >= 'A' && array[0] <= 'Z'))
				return false;
			for (int i = 1; i < array.length; i++)
				if (!(array[i] >= 'a' && array[i] <= 'z'))
					return false;
			return true;
		}
	}
}