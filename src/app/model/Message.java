package app.model;

import app.dao.messenger_text.IMessengerText;
import app.dao.messenger_text.MessengerTextDAO;
import app.services.database.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Message extends Model {
	private long id;
	private long contact_id;
	private long user_id;
	private int delete;
	private String created_at;

	private MessageText messageText;

	public Message(){
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public MessageText getMessageText() {
		return messageText;
	}

	public void setMessageText(MessageText messageText) {
		this.messageText = messageText;
	}

	public MessageText getText(){
		if(messageText == null){
			IMessengerText iMessengerText = new MessengerTextDAO();
			messageText = iMessengerText.findByMessegerId(id);
		}
		return messageText;
	}

	@Override
	public void setData(ResultSet data) {
		try {
			id = data.getLong("id");
			contact_id = data.getLong("contact_id");
			user_id = data.getLong("user_id");
			delete = data.getInt("delete");

			created_at = data.getString("created_at");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
