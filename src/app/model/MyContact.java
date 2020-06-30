package app.model;

import app.dao.user_info.IUserInfo;
import app.dao.user_info.UserInfoDAO;
import app.services.database.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyContact extends Model {
	private long   id;
	private long   user_id;
    private long   contact_id;
	private int    delete;
	private String created_at;

	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getContact_id() {
		return contact_id;
	}

	public void setContact_id(long contact_id) {
		this.contact_id = contact_id;
	}

	public int getDelete() {
		return delete;
	}

	public void setDelete(int delete) {
		this.delete = delete;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public User getUser(){
		if (user == null){
			user = new User();
			user.find(user_id);
		}
		return user;
	};

	@Override
	public void setData(ResultSet data) {
		try {
			id = data.getLong("id");
			user_id = data.getLong("user_id");
			contact_id = data.getLong("contact_id");
			delete = data.getInt("delete");
			created_at = data.getString("created_at");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
