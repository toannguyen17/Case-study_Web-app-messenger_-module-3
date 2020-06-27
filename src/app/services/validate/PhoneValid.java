package app.services.validate;

import app.config.ValidPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValid implements Validation {
	public PhoneValid(){
	}
	@Override
	public boolean check(String phone) {
		if (phone == null) return false;

		Pattern pattern = Pattern.compile(ValidPattern.PHONE_PATTERN);
		Matcher matcher = pattern.matcher(phone);
		return matcher.find();
	}
}
