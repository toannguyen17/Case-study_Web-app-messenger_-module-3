package app.services.websocket;

import app.services.websocket.message.*;
import org.json.JSONException;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket", encoders = MessageEncoder.class, decoders = MessageDecoder.class, configurator = EndpointConfigurator.class)
public class Client {
	public static WebSocket webSocket = WebSocket.getInstance();

	public HttpSession httpSession;

	private Session session;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		this.session     = session;
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		String chatRoom  = (String) httpSession.getAttribute("chatRoom");
		System.out.println("----------OPEN---------");
	}

	@OnClose
	public void onClose() {
		System.out.println("----------END---------");
		webSocket.remove(this);
	}

	@OnMessage
	public void onMessage(MessageJSON message) {
		System.out.println("----------OnMessage---------");

		boolean checkAction = message.isNull("action");
		if (checkAction == true) {
			try {
				String action = message.getString("action");
			} catch (JSONException e) {
			}
		}
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("Chat Error: " + t.toString());
	}

	public Session getSession() {
		return session;
	}
}
