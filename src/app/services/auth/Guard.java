package app.services.auth;

import app.model.User;

public interface Guard {
	void login(User user);
	void logout();
}
