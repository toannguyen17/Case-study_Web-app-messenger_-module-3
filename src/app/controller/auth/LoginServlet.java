package app.controller.auth;

import app.services.auth.Auth;
import app.services.auth.SessionGuard;
import app.services.ui.auth.LoginUsers;
import app.services.view.ForwardView;
import app.services.view.View;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Auth auth = new SessionGuard(req, resp);
		if (!auth.check()){
			LoginUsers loginUsers = new LoginUsers();
			loginUsers.login(req, resp);
		}else resp.sendRedirect("/");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/");
	}
}
