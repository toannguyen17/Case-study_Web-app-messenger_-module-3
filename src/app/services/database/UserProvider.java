package app.services.database;

import app.config.SQL;
import app.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProvider {

	public User retrieveById(long id){
		User user = new User();
		user.find(id);
		return user;
	}

	public User retrieveByToken(String id, String token){
		Connection connection = DatabaseManager.getInstance().getConnection();
		try {
			PreparedStatement prpsm = connection.prepareStatement(SQL.USER_BY_ID_TOKEN);
			prpsm.setString(1, id);
			prpsm.setString(2, token);
			ResultSet result = prpsm.executeQuery();
			if (result.next()){
				User user = new User();
				user.setData(result);
				return user;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public void updateRememberToken(User user, String token){
		Connection connection = DatabaseManager.getInstance().getConnection();
		try {
			PreparedStatement prpsm = connection.prepareStatement(SQL.USER_UPDATE_TOKEN);
			prpsm.setString(1, token);
			prpsm.setLong(2, user.getId());
			prpsm.executeUpdate();
			user.setRemember_token(token);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
