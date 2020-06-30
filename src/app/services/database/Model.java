package app.services.database;

import app.config.SQL;
import app.model.User_Info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Model {

	protected static String table;
	protected static String[] fillable;

	protected String getTable(){
		if (table != null){
			return table;
		}
		String[] names = this.getClass().getName().split("\\.");
		table = names[names.length-1];
		table = table.toLowerCase() + "s";

		return table;
	}

	// Find by ID
	public void find(long id){
		String table = getTable();
		if (table == null) return;

		Connection connection = DatabaseManager.getInstance().getConnection();
		ResultSet result = null;
		try {
			String SQL_QUERY = SQL.GET_BY_ID_SQL.replaceFirst("\\?", table);
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
			preparedStatement.setLong(1, id);

			result = preparedStatement.executeQuery();
			if (result.next()){
				setData(result);
			}
		} catch (SQLException throwables) {
		}
	}

	abstract public void setData(ResultSet data);
}
