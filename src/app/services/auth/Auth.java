package app.services.auth;

import app.model.User;

public interface Auth {
	long id();
	boolean check();
	User user();
}
