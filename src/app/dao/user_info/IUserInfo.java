package app.dao.user_info;

import app.model.User_Info;

public interface IUserInfo {
	User_Info findByUserId(long user_id);
	void update(User_Info user_info);
}
