package app.dao.user_info;

import app.model.User_Info;
import app.services.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDAO implements IUserInfo{
	private final String UPDATE_BY_USER_ID = "UPDATE `user_infos` SET `last_name` = ?," +
			" `first_name` = ?, `avatar_extension` = ? WHERE `id` = ?";

	private final String SELECT_BY_USER_ID = "SELECT * FROM `user_infos` WHERE `user_id` = ?;";
	@Override
	public User_Info findByUserId(long user_id) {
		Connection connection = DatabaseManager.getInstance().getConnection();
		try {
			User_Info user_info = new User_Info();
			PreparedStatement rpsm = connection.prepareStatement(SELECT_BY_USER_ID);
			rpsm.setLong(1, user_id);
			ResultSet result = rpsm.executeQuery();
			if (result.next()){
				user_info.setData(result);
			}
			return user_info;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(User_Info user_info) {
		Connection connection = DatabaseManager.getInstance().getConnection();
		try {
			PreparedStatement rpsm = connection.prepareStatement(UPDATE_BY_USER_ID);
			rpsm.setString(1, user_info.getLast_name());
			rpsm.setString(2, user_info.getFirst_name());
			rpsm.setString(3, user_info.getAvatar_extension());
			rpsm.setLong(4, user_info.getId());
			rpsm.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
