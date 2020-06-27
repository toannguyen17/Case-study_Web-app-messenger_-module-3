package app.config;

public interface Database {
	String DRIVE         = "jdbc";
	String DB_CONNECTION = "mysql";
	String HOST          = "127.0.0.1";
	String PORT          = "3306";
	String DATABASE      = "messager";
	String USERNAME      = "root";
	String PASSWORD      = "";
	String[] PARAMETER   = {"useSSl=false", "serverTimezone=UTC"};
}
