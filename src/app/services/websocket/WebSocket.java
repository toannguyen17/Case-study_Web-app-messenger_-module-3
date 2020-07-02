package app.services.websocket;

import app.model.User;
import org.json.JSONObject;

import java.util.*;

public class WebSocket {
	private static WebSocket instance;

	private static final Map<Long, ClientEntry> clients = new HashMap<>();

	private WebSocket(){
	}

	public static WebSocket getInstance() {
		if (instance == null) {
			synchronized (WebSocket.class) {
				instance = new WebSocket();
			}
		}
		return instance;
	}

	public List<ClientEntry> getListClients() {
		return new ArrayList<>(clients.values());
	}

	// add client
	public void add(Client client){
		long id = client.id;
		ClientEntry clientEntry = clients.get(id);

		if (clientEntry == null){
			ClientEntry newEntry = new ClientEntry(client);
			clients.put(id, newEntry);
		}else{
			clientEntry.add(client);
		}
	}

	// remove client
	public void remove(Client client){
		if (client != null && client.auth != null){
			User user = client.auth.user();
			if (user == null)
				return;

			long id = client.id;
			ClientEntry clientEntry = clients.get(id);
			if (clientEntry != null){
				clientEntry.remove(client);
				if (clientEntry.size() == 0){
					clients.remove(id);
					clientEntry.destroy();
				}
			}
		}
	}

	public void send(long id, JSONObject json) {
		ClientEntry clientEntry = clients.get(id);
		if (clientEntry != null){
			clientEntry.send(json);
		}
	}

	public void send(Client client, JSONObject json) {
		long id = client.id;
		ClientEntry clientEntry = clients.get(id);
		if (clientEntry != null){
			clientEntry.send(json);
		}
	}

	public void broadcast(JSONObject json) {
		List<ClientEntry> lists = new ArrayList<>(clients.values());
		for (ClientEntry clientEntry: lists) {
			clientEntry.send(json);
		}
	}
}
