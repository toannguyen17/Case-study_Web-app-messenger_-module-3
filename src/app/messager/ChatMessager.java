package app.messager;

import app.model.User_Info;
import org.json.JSONObject;

public class ChatMessager extends JSONObject {
	public ChatMessager(User_Info user_info){
		super();
		this.put("action",  "chat");

		JSONObject userJSON = new JSONObject();
		userJSON.put("id",         user_info.getUser_id());
		userJSON.put("last_name",  user_info.getLast_name());
		userJSON.put("first_name", user_info.getFirst_name());
		userJSON.put("avatar",     user_info.getUrlAvatar());

		this.put("user", userJSON);
	}

	public void setText(String text){
		this.put("text", text);
	}
}
