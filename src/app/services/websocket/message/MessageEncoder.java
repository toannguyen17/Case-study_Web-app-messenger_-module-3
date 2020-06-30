package app.services.websocket.message;

import org.json.JSONObject;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<JSONObject>{
	@Override
	public String encode(JSONObject messageJSON) {
		String encode = messageJSON.toString();
		System.out.println("encode: " + messageJSON);
		System.out.println("encode: " + encode);
		return encode;
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
	}

	@Override
	public void destroy() {
	}
}
