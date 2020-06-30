package app.model;

import app.services.database.Model;

import java.sql.ResultSet;

public class PhoneRegion extends Model {
	private long id;
	private String region;
	private String crated_at;
	public PhoneRegion(){

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCrated_at() {
		return crated_at;
	}

	public void setCrated_at(String crated_at) {
		this.crated_at = crated_at;
	}

	@Override
	public void setData(ResultSet data) {
	}
}
