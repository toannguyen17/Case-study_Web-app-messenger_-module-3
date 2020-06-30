package app.controller.websocket;

import app.services.websocket.Client;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class API implements SocketAPI {
	private static API instance;

	private API(){
	}

	public static API getInstance() {
		if (instance == null){
			synchronized (API.class){
				instance = new API();
			}
		}
		return instance;
	}

	@Override
	public void request(JSONObject message, Client client) {
		System.out.println("Controller");
		System.out.println(message);
		boolean checkAction = message.isNull("action");
		if (checkAction != true) {
			try {
				String action = message.getString("action");
				System.out.println("Action: " +action);

				switch (action){
					case "search":
						SocketAPI searchAPI = SearchAPI.getInstance();
						searchAPI.request(message, client);
						break;

					case "contact":
						SocketAPI contactAPI = ContactAPI.getInstance();
						contactAPI.request(message, client);
						break;

					case "chat":
						System.out.println("user_id C");
						SocketAPI chatAPI = ChatAPI.getInstance();
						chatAPI.request(message, client);
						break;

					case "logout":
						logout(client);
						break;
				}
			} catch (JSONException e) {
			}
		}
	}
	public void logout(Client client){
		client.auth.logout();

		JSONObject json = new JSONObject();
		json.put("action", "logout");

		client.send(json);
		try {
			client.session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
