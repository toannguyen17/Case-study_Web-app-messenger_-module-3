package app.services.auth;

import app.model.User;

public interface Auth {
	boolean check();
	User user();
}
