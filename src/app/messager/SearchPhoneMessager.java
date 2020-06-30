package app.messager;

import app.model.Phone;
import app.model.User_Info;
import org.json.JSONObject;

public class SearchPhoneMessager extends JSONObject {
	public SearchPhoneMessager(Phone phone){
		super();
		this.put("action",  "search");
		this.put("type",    "number");

		User_Info user_info = phone.getUser().getInfo();

		JSONObject userJSON = new JSONObject();
		userJSON.put("id",         phone.getUser_id());
		userJSON.put("last_name",  user_info.getLast_name());
		userJSON.put("first_name", user_info.getFirst_name());
		userJSON.put("avatar",     user_info.getUrlAvatar());

		this.append("data", userJSON);
	}
}
