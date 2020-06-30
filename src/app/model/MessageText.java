package app.model;

import app.services.database.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageText extends Model {
	private long id;
	private long message_id;
	private String text;

	public MessageText(){
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void setData(ResultSet data) {
		try {
			id = data.getLong("id");
			message_id = data.getLong("message_id");;
			text = data.getString("text");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
