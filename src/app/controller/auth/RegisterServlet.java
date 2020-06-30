package app.controller.auth;

import app.services.auth.Auth;
import app.services.auth.SessionGuard;
import app.services.ui.auth.RegistersUsers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Resigter", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Auth auth = new SessionGuard(req, resp);
		if (!auth.check()) {
			RegistersUsers register = new RegistersUsers();
			register.register(req, resp);
		} else resp.sendRedirect("/");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/");
	}
}
