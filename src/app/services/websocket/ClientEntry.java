package app.services.websocket;

import app.services.websocket.message.MessageJSON;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ClientEntry {
	private long id;
	private Set<Client> clients = new CopyOnWriteArraySet<>();

	public ClientEntry(){
	}

	public ClientEntry(long id, Client client){
		this.id = id;
		add(client);
	}

	public void add(Client client){
		clients.add(client);
	}

	public void remove(Client client){
		clients.remove(client);

		if (clients.size() == 0){
			destroy();
		}
	}

	private void destroy(){
		id = 0;
		clients = null;
	}

	public void broadcast(MessageJSON msg) {
		for (Client client : clients) {
			try {
				synchronized (client) {
					client.getSession().getBasicRemote().sendText(msg.toString());
				}
			} catch (IOException e) {
				clients.remove(client);
				try {
					client.getSession().close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
