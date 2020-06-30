package app.model;

import app.services.database.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Contact extends Model {
	private long   id;
    private int    type;
	private String created_at;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
			id = data.getLong("id");
			type = data.getInt("type");
			created_at = data.getString("created_at");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
