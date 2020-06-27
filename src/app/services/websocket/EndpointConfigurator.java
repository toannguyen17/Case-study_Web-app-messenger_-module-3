package app.services.websocket;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;
import java.util.List;
import java.util.Map;

public class EndpointConfigurator extends Configurator {
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession) request.getHttpSession();
		sec.getUserProperties().put(HttpSession.class.getName(), httpSession);

		Map<String, List<String>> headers = request.getHeaders();
		sec.getUserProperties().put(Cookie.class.getName(), headers.get("cookie"));
	}
}
