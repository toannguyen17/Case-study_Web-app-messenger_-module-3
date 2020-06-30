package app.services.websocket;

import app.controller.websocket.API;
import app.services.auth.Auth;
import app.services.auth.SessionGuard;
import app.services.helpers.CookieHelpers;
import app.services.websocket.message.*;
import org.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/websocket", encoders = MessageEncoder.class, decoders = MessageDecoder.class, configurator = EndpointConfigurator.class)
public class Client {

	public Auth auth;

	public Session session;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		Map<String, Object> properties = config.getUserProperties();
		this.session = session;
		HttpSession httpSession = (HttpSession) properties.get(HttpSession.class.getName());

		List<String> cookies = (List<String>) properties.get(Cookie.class.getName());

		Cookie[] resultCookie = null;

		if (cookies != null) {
			CookieHelpers cookieString = new CookieHelpers(cookies);
			resultCookie = cookieString.getCookies();
		}

		auth = new SessionGuard(httpSession, resultCookie);

		if (auth.check()) {
			WebSocket webSocket = WebSocket.getInstance();
			webSocket.add(this);
			JSONObject json = new JSONObject();
			json.put("action", "me_id");
			json.put("me",      auth.user().getId());
			send(json);
		} else {
			auth = null;
		}
	}

	@OnClose
	public void onClose() {
		if (auth != null){
			WebSocket webSocket = WebSocket.getInstance();
			webSocket.remove(this);
			auth = null;
		}
	}

	@OnMessage
	public void onMessage(JSONObject message) {
		if (auth == null)
			return;

		System.out.println("on messenger");
		if (!auth.check() || !session.isOpen()){
			System.out.println("No auth");
		}else{
			System.out.println("Auth");
			API controller = API.getInstance();
			controller.request(message, this);
		}
	}

	@OnError
	public void onError(Throwable t) {
		System.out.println("Chat Error: " + t.toString());
	}

	public void send(JSONObject json){
		if (auth == null || !session.isOpen())
			return;

		System.out.println(session.isOpen());

		try {
			synchronized (Client.class) {
				session.getBasicRemote().sendObject(json);
			}
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
			WebSocket webSocket = WebSocket.getInstance();
			webSocket.remove(this);
			try {
				session.close();
			} catch (IOException e1) {
			}
		}
	}
}
