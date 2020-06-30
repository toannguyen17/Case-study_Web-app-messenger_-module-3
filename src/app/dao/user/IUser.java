package app.dao.user;

import app.model.User;

public interface IUser {
	User findByUserId(long id);
	void update(User user);
}
