package app.services.websocket.enc;

import org.json.JSONObject;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.nio.ByteBuffer;

public class MessageEncoder implements Encoder.Binary<JSONObject>{
	@Override
	public void init(EndpointConfig endpointConfig) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public ByteBuffer encode(JSONObject messageJSON) {
		String message    = messageJSON.toString();
		byte[] bytes      = message.getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		return buffer;
	}
}
