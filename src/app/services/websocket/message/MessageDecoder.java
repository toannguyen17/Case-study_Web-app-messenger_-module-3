package app.services.websocket.message;

import org.json.JSONException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<MessageJSON> {
	@Override
	public MessageJSON decode(String message) throws DecodeException {
		MessageJSON messageJSON = null;
		try {
			messageJSON = new MessageJSON(message);
		} catch (JSONException e) {
			try {
				messageJSON = new MessageJSON("{}");
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
