package app.controller;

import app.config.ViewConfig;
import app.services.auth.Auth;
import app.services.auth.SessionGuard;
import app.services.view.ForwardView;
import app.services.view.View;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Home", urlPatterns = "/index.jsp")
 public class HoneController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Auth auth = new SessionGuard(req);

		if (auth.check()){

		}else{

		}

		String layout = ViewConfig.PATH + ViewConfig.HOME_INDEX_GUEST;
		View view = new ForwardView(req, resp, layout);
		view.get();
	}
}
