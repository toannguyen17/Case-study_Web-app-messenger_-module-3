package app.services.database;

import app.config.Database;
import app.services.Helpers.string.Characters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	private static DatabaseManager instance;

	private static Connection connection;

	private DatabaseManager(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("driver mysql not found!");
			System.out.println("Stop App...");
			System.exit(0);
		}

		String url = getURL();
		System.out.println("MySQL connect: " + url);

		try {
			connection = DriverManager.getConnection(url, Database.USERNAME, Database.PASSWORD);
			System.out.println("MySQL connection successful.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Stop App...");
			System.exit(0);
		}
	}

	public static DatabaseManager getInstance() {
		if (instance == null){
			synchronized(DatabaseManager.class){
				instance = new DatabaseManager();
			}
		}
		return instance;
	}

	private String getURL(){
		StringBuilder jdbcURL = new StringBuilder(Database.DRIVE);
		jdbcURL.append(Characters.COLON);
		jdbcURL.append(Database.DB_CONNECTION);
		jdbcURL.append(Characters.COLON);
		jdbcURL.append(Characters.SLASH);
		jdbcURL.append(Characters.SLASH);
		jdbcURL.append(Database.HOST);

		// Add PORT to URL
		if (Database.PORT != null && Database.PORT.length() > 0) {
			jdbcURL.append(Characters.COLON);
			jdbcURL.append(Database.PORT);
		}

		// Add DATABASE NAME to URL
		jdbcURL.append(Characters.SLASH);
		jdbcURL.append(Database.DATABASE);

		// Add param to URL
		if (Database.PARAMETER != null && Database.PARAMETER.length > 0) {
			jdbcURL.append(Characters.QUESTION_MARKS);
			for (String param: Database.PARAMETER){
				jdbcURL.append(param);
				jdbcURL.append(Characters.AMPERSAND);
			}
		}

		return jdbcURL.toString();
	}

	public Connection getConnection() {
		return connection;
	}
}
