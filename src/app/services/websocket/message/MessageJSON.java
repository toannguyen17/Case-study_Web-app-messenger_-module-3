package app.services.websocket.message;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageJSON extends JSONObject {
	public MessageJSON(String message) throws JSONException {
		super(message);
	}

	public void setError(int error){
		try {
			this.put("error", error);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
