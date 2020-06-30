package app.model;

import app.dao.phone_region.IPhoneRegion;
import app.dao.phone_region.PhoneRegionDAO;
import app.services.database.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Phone extends Model {
	private long id;
    private long user_id;
    private long region_id;
    private String number;
    private String created_at;

	private PhoneRegion region;
	private User user;

    public Phone(){
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

	public long getRegion_id() {
		return region_id;
	}

	public void setRegion_id(long region_id) {
		this.region_id = region_id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public PhoneRegion getRegion(){
		if (region == null){
			IPhoneRegion phoneRegion = new PhoneRegionDAO();
			region = phoneRegion.findById(region_id);
		}
    	return region;
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
			region_id = data.getLong("region_id");
			number = data.getString("number");
			created_at = data.getString("created_at");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
