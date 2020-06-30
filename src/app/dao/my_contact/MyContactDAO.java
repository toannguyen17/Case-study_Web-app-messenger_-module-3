package app.dao.my_contact;

import app.model.Message;
import app.model.MyContact;
import app.services.database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyContactDAO implements IMyContact {
	private static final String SQL_INSERT = "INSERT INTO `my_contacts` (`user_id`, `contact_id`, `delete`, `created_at`) VALUE (?,?,?,?);";


	private static final String SQL_SELECT_BY_ID         = "SELECT * FROM `my_contacts` WHERE `id` = ?;";
	private static final String SQL_SELECT_BY_USER_ID    = "SELECT * FROM `my_contacts` WHERE `user_id` = ?;";
	private static final String SQL_SELECT_BY_CONTACT_ID = "SELECT * FROM `my_contacts` WHERE `contact_id` = ?;";
	@Override
	public MyContact findById(long id) {
		Connection connection = DatabaseManager.getInstance().getConnection();

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_SELECT_BY_ID);
			rpstm.setLong(1, id);
			ResultSet result = rpstm.executeQuery();
			if (result.next()) {
				MyContact myContact = new MyContact();
				myContact.setData(result);
				return myContact;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MyContact> findByUserId(long user_id) {
		List<MyContact> list = new ArrayList<>();
		Connection connection = DatabaseManager.getInstance().getConnection();

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_SELECT_BY_USER_ID);
			rpstm.setLong(1, user_id);
			ResultSet result = rpstm.executeQuery();
			while (result.next()) {
				MyContact myContact = new MyContact();
				myContact.setData(result);
				list.add(myContact);
			}
			return list;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MyContact> findByContactId(long contact_id) {
		List<MyContact> list = new ArrayList<>();
		Connection connection = DatabaseManager.getInstance().getConnection();

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_SELECT_BY_CONTACT_ID);
			rpstm.setLong(1, contact_id);
			ResultSet result = rpstm.executeQuery();
			while (result.next()) {
				MyContact myContact = new MyContact();
				myContact.setData(result);
				list.add(myContact);
			}
			return list;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(MyContact myContact) {
		Connection connection = DatabaseManager.getInstance().getConnection();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String time = timestamp.toString();

		myContact.setCreated_at(time);
		myContact.setDelete(0);

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			rpstm.setLong(1, myContact.getUser_id());
			rpstm.setLong(2, myContact.getContact_id());
			rpstm.setInt(3, 0);
			rpstm.setString(4, time);
			rpstm.executeUpdate();
			ResultSet rs = rpstm.getGeneratedKeys();

			if (rs.next()) {
				myContact.setId(rs.getLong(1));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
