package app.model;

import app.config.PageConfig;
import app.services.database.Model;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_Info extends Model {
	private long id;
    private long user_id;
	private String last_name;
	private String first_name;
	private String avatar_extension;
	private String updated_at;

	public User_Info(){
	}

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

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getAvatar_extension() {
		return avatar_extension;
	}

	public void setAvatar_extension(String avatar_extension) {
		this.avatar_extension = avatar_extension;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getUrlAvatar() {
		String url = null;
		if (avatar_extension != null && avatar_extension.length() > 1) {
			url = File.separator + PageConfig.IMAGES_PATH + File.separator + PageConfig.AVATAR_PATH + File.separator + user_id + "." + avatar_extension;
		}else{
			url = File.separator + PageConfig.IMAGES_PATH + File.separator + "default/user.jpg";
		}
		return url;
	}

	@Override
	public void setData(ResultSet data) {
		try {
			id               = data.getLong("id");
			user_id          = data.getLong("user_id");
			last_name        = data.getString("last_name");
			first_name       = data.getString("first_name");
			avatar_extension = data.getString("avatar_extension");
			updated_at       = data.getString("updated_at");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
