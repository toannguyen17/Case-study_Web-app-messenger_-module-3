package app.services.websocket;

import org.json.JSONObject;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ClientEntry {
	private long id;
	private Set<Client> clients = new CopyOnWriteArraySet<>();

	public ClientEntry(){
	}

	public ClientEntry(Client client) {
		this.id = client.auth.user().getId();
		add(client);
	}

	public int size(){
		return  clients.size();
	}

	public void add(Client client){
		clients.add(client);
	}

	public void remove(Client client){
		clients.remove(client);
	}

	public void destroy(){
		id = 0;
		clients = null;
	}

	public void send(JSONObject json) {
		for (Client client: clients) {
			client.send(json);
		}
	}
}
