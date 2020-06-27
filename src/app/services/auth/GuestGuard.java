package app.services.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class GuestGuard {
	HttpSession session;
	Cookie[] cookies;

	public GuestGuard() {
	}

	public GuestGuard(HttpSession session) {
		this.session = session;
	}

	public GuestGuard(Cookie[] cookies) {
		this.cookies = cookies;
	}

	public GuestGuard(HttpSession session, Cookie[] cookies) {
		this.session = session;
		this.cookies = cookies;
	}

	public GuestGuard(HttpServletRequest req) {
		this.session = req.getSession(true);
		this.cookies = req.getCookies();
	}
}
