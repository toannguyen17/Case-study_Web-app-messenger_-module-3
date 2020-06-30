package app.services.helpers;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CookieHelpers {
	Cookie[] cookies;
	public CookieHelpers() {
	}

	public CookieHelpers(List<String> list) {
		list.forEach(cookie -> {
			String[] cookieMat2 = cookie.split("; ");

			cookies = new Cookie[cookieMat2.length];

			for (int i = 0; i < cookieMat2.length; i++) {
				String cok = cookieMat2[i];
				Pattern pattern = Pattern.compile("^(.+?)=(.+)$");

				Matcher matcher = pattern.matcher(cok);
				if (matcher.matches()) {
					String cKey   = matcher.group(1);
					String cValue = matcher.group(2);
					Cookie ck = new Cookie(cKey, cValue);
					cookies[i] = ck;
				}
			}
		});
	}

	public Cookie[] getCookies() {
		return cookies;
	}
}
