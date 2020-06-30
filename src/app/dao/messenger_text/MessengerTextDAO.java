package app.dao.messenger_text;

import app.model.MessageText;
import app.services.database.DatabaseManager;

import java.sql.*;

public class MessengerTextDAO implements IMessengerText {
	private static final String SQL_SELECT_BY_MESS_ID = "SELECT * FROM `message_texts` WHERE `message_id` = ?;";
	private static final String SQL_INSERT = "INSERT INTO `message_texts` (`message_id`, `text`) VALUE (?,?);";

	@Override
	public void insert(MessageText messageText) {
		Connection connection = DatabaseManager.getInstance().getConnection();

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			rpstm.setLong(1, messageText.getMessage_id());
			rpstm.setString(2, messageText.getText());
			rpstm.executeUpdate();
			ResultSet rs = rpstm.getGeneratedKeys();

			if (rs.next()) {
				messageText.setId(rs.getLong(1));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public MessageText findByMessegerId(long id) {
		Connection connection = DatabaseManager.getInstance().getConnection();

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_SELECT_BY_MESS_ID);
			rpstm.setLong(1, id);
			ResultSet result = rpstm.executeQuery();

			if (result.next()) {
				MessageText messageText = new MessageText();
				messageText.setData(result);
				return messageText;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}
}
