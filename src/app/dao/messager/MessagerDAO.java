package app.dao.messager;

import app.model.Message;
import app.services.database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagerDAO implements IMessager {
	private static final String SQL_INSERT = "INSERT INTO `messages` (`contact_id`, `user_id`, `delete`, `created_at`) VALUE (?,?,?,?);";

	public MessagerDAO(){}

	public List<Message> getMessager(long contact_id, int start, int limit, String sort){
		String SQL_GET_LIST_MESSAGER = "SELECT * FROM `messages` WHERE `contact_id` = ? " +
				"ORDER BY `id` " + sort + " LIMIT " + start + ", " + limit;

		List<Message> list = new ArrayList<>();
		Connection connection = DatabaseManager.getInstance().getConnection();

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_GET_LIST_MESSAGER);
			rpstm.setLong(1, contact_id);
			System.out.println(rpstm);
			ResultSet result = rpstm.executeQuery();
			while (result.next()) {
				Message message = new Message();
				message.setData(result);
				list.add(message);
			}
			return list;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public Message insert(long id, long user_id) {
		Message message = new Message();
		Connection connection = DatabaseManager.getInstance().getConnection();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String time = timestamp.toString();

		message.setCreated_at(time);
		message.setDelete(0);
		message.setContact_id(id);
		message.setUser_id(user_id);

		try {
			PreparedStatement rpstm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			rpstm.setLong(1, id);
			rpstm.setLong(2, user_id);
			rpstm.setInt(3, 0);
			rpstm.setString(4, time);
			rpstm.executeUpdate();
			ResultSet rs = rpstm.getGeneratedKeys();

			if (rs.next()) {
				message.setId(rs.getLong(1));
			}
			return message;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}
}
