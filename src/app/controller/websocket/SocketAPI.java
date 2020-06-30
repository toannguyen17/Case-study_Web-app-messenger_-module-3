package app.controller.websocket;

import app.services.websocket.Client;
import org.json.JSONObject;

public interface SocketAPI {
	void request(JSONObject message, Client client);
}
