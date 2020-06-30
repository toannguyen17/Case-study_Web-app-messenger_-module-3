package app.services.helpers;

import app.config.ValidPattern;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {
	public Helpers(){
	}

	public Map<String, String> crackPhone(String phone) {
		Pattern pattern = Pattern.compile(ValidPattern.PHONE_PATTERN);
		Matcher matcher = pattern.matcher(phone);

		if (matcher.matches()) {
			String region = matcher.group(1);
			String number = matcher.group(2);
			Map<String, String> map = new HashMap<>();
			map.put("region", region);
			map.put("number",  number);
			return map;
		}
		return null;
	}
}
