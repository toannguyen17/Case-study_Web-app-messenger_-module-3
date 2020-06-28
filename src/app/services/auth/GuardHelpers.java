package app.services.auth;

import app.model.User;
import app.services.database.UserProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class GuardHelpers {
	protected User user;
	protected UserProvider provider;

	protected HttpSession session;
	protected Cookie[] cookies;

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public GuardHelpers() {
		provider = new UserProvider();
	}

	public GuardHelpers(HttpSession session) {
		this.session = session;
		provider = new UserProvider();
	}

	public GuardHelpers(Cookie[] cookies) {
		this.cookies = cookies;
		provider = new UserProvider();
	}

	public GuardHelpers(HttpSession session, Cookie[] cookies) {
		this.session = session;
		this.cookies = cookies;
		provider = new UserProvider();
	}

	public GuardHelpers(HttpServletRequest req) {
		this.request = req;
		this.setSessionCookieREQ(req);
		provider = new UserProvider();
	}

	public GuardHelpers(HttpServletRequest req, HttpServletResponse resp) {
		this.request  = req;
		this.response = resp;
		this.setSessionCookieREQ(req);
		provider = new UserProvider();
	}

	public void setSessionCookieREQ(HttpServletRequest req) {
		this.session = req.getSession(true);
		this.cookies = req.getCookies();
	}
}
