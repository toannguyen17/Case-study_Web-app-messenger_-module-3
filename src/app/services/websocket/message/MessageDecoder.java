package app.services.websocket.message;

import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<JSONObject> {
	@Override
	public JSONObject decode(String message) throws DecodeException {
		System.out.println("decode: " + message);
		JSONObject messageJSON = null;
		try {
			messageJSON = new JSONObject(message);
		} catch (JSONException e) {
			try {
				messageJSON = new JSONObject();
			} catch (JSONException d) {
			}
		}
		return messageJSON;
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
	}

	@Override
	public void destroy() {
	}
}
