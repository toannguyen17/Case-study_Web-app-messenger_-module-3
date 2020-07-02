package app.services.websocket.enc;

import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.nio.ByteBuffer;

public class MessageDecoder implements Decoder.Binary<JSONObject> {

	@Override
	public void init(EndpointConfig endpointConfig) {
	}

	@Override
	public void destroy() {
	}

	// Byte decode
	@Override
	public JSONObject decode(ByteBuffer byteBuffer) {
		byte[] bytes = byteBuffer.array();

		String message = new String(bytes);
		JSONObject messageJSON = null;
		try {
			messageJSON = new JSONObject(message);
		} catch (JSONException e) {
			try {
				messageJSON = new JSONObject();
			} catch (JSONException e2) {
				e2.printStackTrace();
			}
		}

		return messageJSON;
	}

	@Override
	public boolean willDecode(ByteBuffer byteBuffer) {
		return true;
	}
}
