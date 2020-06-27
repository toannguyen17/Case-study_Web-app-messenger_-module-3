package app.services.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Model {

	private static final String GET_BY_ID_SQL = "SELECT * FROM ? WHERE `id` = ?;";

	protected static String table;
	protected static String[] fillable;

	protected String getTable(){
		if (table == null){
			return table;
		}
		String[] names = this.getClass().getName().split("\\.");
		table = names[names.length-1];
		table = table.toLowerCase() + "s";

		return table;
	}

	// Find by ID
	public ResultSet find(long id){
		Connection connection = DatabaseManager.getInstance().getConnection();
		ResultSet result = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL);
			preparedStatement.setString(1, getTable());
			preparedStatement.setLong(2, id);

			System.out.println(preparedStatement);

			result = preparedStatement.executeQuery();
			if (result.first()){

			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return result;
	}
}
