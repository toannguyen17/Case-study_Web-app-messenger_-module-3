package app.services.auth;

import app.config.PageConfig;
import app.model.User;
import app.services.database.UserProvider;
import app.services.helpers.string.Str;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionGuard extends GuardHelpers implements Auth, Guard {
	public SessionGuard() {
	}

	public SessionGuard(HttpSession session) {
		super(session);
	}

	public SessionGuard(Cookie[] cookies) {
		super(cookies);
	}

	public SessionGuard(HttpSession session, Cookie[] cookies) {
		super(session, cookies);
	}

	public SessionGuard(HttpServletRequest request) {
		super(request);
	}
	public SessionGuard(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public User user() {
		if (user != null) {
			return user;
		}
		long id = (long) session.getAttribute(PageConfig.SESSION_ID);

		if (id != 0){
			user = provider.retrieveById(id);
		}

		Recaller recaller;
		if (user == null && (recaller = recaller()) != null) {
			user = userFromRecaller(recaller);

			if (user != null){
				updateSession(user.getId());
			}
		}

		return user;
	}

	private Recaller recaller() {
		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			String cName = cookie.getName();
			String cValue = cookie.getValue();

			if (PageConfig.SESSION_RECALLER.equals(cName)){
				return new Recaller(cValue);
			}
		}
		return null;
	}

	private User userFromRecaller(Recaller recaller) {
		if (!recaller.valid()) {
			return null;
		}

		user = provider.retrieveByToken(recaller.id(), recaller.token());
		return user;
	}

	@Override
	public long id() {
		return 0;
	}

	@Override
	public boolean check() {
		String name = (String) session.getAttribute("name");
		System.out.println(name);
		return false;
	}

	@Override
	public void login(User user) {
		updateSession(user.getId());

		//ensureRememberTokenIsSet(user);
		//createRecallerCookie(user);

		System.out.println("--------------123");
		System.out.println(user.getId());
		System.out.println(user.getRemember_token());
	}

	private void updateSession(long id){
		session.setAttribute(PageConfig.SESSION_ID, id);;
	}

	private void ensureRememberTokenIsSet(User user)
	{
		if (user.getRemember_token() == null) {
			cycleRememberToken(user);
		}
	}

	private void cycleRememberToken(User user) {
		Str str = new Str();
		String token = str.random(60);
		provider.updateRememberToken(user, token);
	}

	private void createRecallerCookie(User user) {
		String cookieValue = user.getId() + "|" + user.getRemember_token();
		Cookie cookie = new Cookie(PageConfig.SESSION_RECALLER, cookieValue);
		cookie.setPath("/");
		int max = 3600*24*30;
		cookie.setMaxAge(max);
		response.addCookie(cookie);
	}

	@Override
	public void logout() {
		session.removeAttribute(PageConfig.SESSION_ID);

		if (cookies != null){
			for (Cookie cookie : cookies) {
				String cName = cookie.getName();
				if (PageConfig.SESSION_RECALLER.equals(cName)){
					cookie.setValue("");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}

		if (user != null && user.getRemember_token() != null) {
			cycleRememberToken(user);
		}
		user = null;
	}
}
