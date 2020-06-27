package app.services.auth;

import app.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionGuard extends GuestGuard implements Auth, Guard {
	public SessionGuard() {
	}

	public SessionGuard(HttpSession session) {
		super(session);
	}

	public SessionGuard(Cookie[] cookies) {
		super(cookies);
	}

	public SessionGuard(HttpServletRequest request) {
		super(request);
	}

	public SessionGuard(HttpSession session, Cookie[] cookies) {
		super(session, cookies);
	}

	@Override
	public User user() {
		return null;
	}

	@Override
	public boolean check() {
		String name = (String) session.getAttribute("name");
		System.out.println(name);
		return false;
	}

}
