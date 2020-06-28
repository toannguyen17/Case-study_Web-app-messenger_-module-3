package app.model;

import app.services.database.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Model {
	private long id;
	private String password;
	private String remember_token;
	private String created_at;

	public User(){
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemember_token() {
		return remember_token;
	}

	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public void setData(ResultSet data) {
		try {
			id             = data.getLong("id");
			password       = data.getString("password");
			remember_token = data.getString("remember_token");
			created_at     = data.getString("created_at");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
