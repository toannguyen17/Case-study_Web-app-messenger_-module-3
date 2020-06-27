package app.services.websocket;

import app.services.websocket.message.MessageJSON;

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
		ClientEntry clientEntry = clients.get(client);
		if (clientEntry == null){
			ClientEntry newEntry = new ClientEntry();
			//clients.put(client, newEntry)
		}
	}

	// remove client
	public void remove(Client client){
		//clients.remove(client);
	}

	public void broadcast(MessageJSON msg) {
		List<ClientEntry> lists = new ArrayList<>(clients.values());
		for (ClientEntry clientEntry : lists) {
			clientEntry.broadcast(msg);
		}
	}
}
