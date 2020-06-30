package app.dao.contact;

import app.model.Contact;
import app.model.Message;
import app.services.database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements IContact {
	private static final String SQL_INSERT = "INSERT INTO `contacts` (`type`, `created_at`) VALUE (?, ?);";
	private static final String SQL_SINGLE = "SELECT contacts.* FROM users\n" +
			"LEFT JOIN my_contacts ON my_contacts.user_id = users.id\n" +
			"LEFT JOIN contacts    ON contacts.id = my_contacts.contact_id\n" +
			"WHERE users.id = ? AND contacts.type = 0 AND EXISTS\n" +
			"    (SELECT id FROM my_contacts WHERE my_contacts.user_id = ? AND my_contacts.contact_id = contacts.id);";

	@Override
	public Contact findSingle(long user_id_1, long user_id_2) {
		Connection connection = DatabaseManager.getInstance().getConnection();

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_SINGLE);
			rpstm.setLong(1, user_id_1);
			rpstm.setLong(2, user_id_2);
			ResultSet result = rpstm.executeQuery();
			if (result.next()) {
				Contact contact = new Contact();
				contact.setData(result);
				return contact;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Contact contact) {
		Connection connection = DatabaseManager.getInstance().getConnection();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String time = timestamp.toString();

		contact.setCreated_at(time);
		contact.setType(0);

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			rpstm.setInt(1, 0);
			rpstm.setString(2, time);
			rpstm.executeUpdate();
			ResultSet rs = rpstm.getGeneratedKeys();

			if (rs.next()) {
				contact.setId(rs.getLong(1));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
