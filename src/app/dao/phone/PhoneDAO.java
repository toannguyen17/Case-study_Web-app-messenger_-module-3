package app.dao.phone;

import app.model.Phone;
import app.services.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneDAO implements IPhone {
	static final String FIND_BY_USER_ID       = "SELECT * FROM `phones` WHERE `user_id` = ?";
	static final String FIND_BY_NUMBER        = "SELECT * FROM `phones` WHERE `number` = ?";
	static final String FIND_BY_NUMBER_NOT_ME = "SELECT * FROM `phones` USE INDEX (`number`) WHERE" +
			" `number` = ? AND user_id != ?;";

	public PhoneDAO(){}

	@Override
	public Phone findByUserId(long user_id) {
		Connection connection = DatabaseManager.getInstance().getConnection();
		try {
			PreparedStatement rpst = connection.prepareStatement(FIND_BY_USER_ID);
			rpst.setLong(1, user_id);
			ResultSet result = rpst.executeQuery();
			if (result.next()){
				Phone phone = new Phone();
				phone.setData(result);
				return phone;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public Phone findByPhone(String number) {
		Connection connection = DatabaseManager.getInstance().getConnection();
		try {
			PreparedStatement rpst = connection.prepareStatement(FIND_BY_NUMBER);
			rpst.setString(1, number);

			ResultSet result = rpst.executeQuery();
			if (result.next()){
				Phone phone = new Phone();
				phone.setData(result);
				return phone;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public Phone findByPhoneNotMe(String number, long user_id) {
		Connection connection = DatabaseManager.getInstance().getConnection();
		try {
			PreparedStatement rpst = connection.prepareStatement(FIND_BY_NUMBER_NOT_ME);
			rpst.setString(1, number);
			rpst.setLong(2, user_id);

			ResultSet result = rpst.executeQuery();
			if (result.next()){
				Phone phone = new Phone();
				phone.setData(result);
				return phone;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}
}
