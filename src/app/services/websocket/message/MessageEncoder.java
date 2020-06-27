package app.services.websocket.message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<MessageJSON>{
	@Override
	public String encode(MessageJSON messageJSON) throws EncodeException {
		return messageJSON.toString();
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
	}

	@Override
	public void destroy() {
	}
}
