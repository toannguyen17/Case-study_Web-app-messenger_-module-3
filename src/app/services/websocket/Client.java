package app.services.websocket;

import app.services.auth.Auth;
import app.services.auth.SessionGuard;
import app.services.helpers.CookieHelpers;
import app.services.websocket.message.*;
import org.json.JSONException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/websocket", encoders = MessageEncoder.class, decoders = MessageDecoder.class, configurator = EndpointConfigurator.class)
public class Client {
	public static WebSocket webSocket = WebSocket.getInstance();

	private Auth auth;

	private Session session;

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
		if (auth.check()){
			//
		}else{
			auth = null;
			try {
				session.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
