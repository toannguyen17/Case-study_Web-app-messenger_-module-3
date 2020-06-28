package app.controller;

import app.config.ViewConfig;
import app.services.auth.Auth;
import app.services.auth.SessionGuard;
import app.services.database.DatabaseManager;
import app.services.helpers.Helpers;
import app.services.helpers.Str;
import app.services.view.ForwardView;
import app.services.view.View;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Home", urlPatterns = "/index.jsp")
 public class HoneController extends HttpServlet {

	@Override
	public void init() throws ServletException {
		DatabaseManager database = DatabaseManager.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Auth auth = new SessionGuard(req, resp);
		View view = new ForwardView(req, resp);

		String layout = null;
		if (auth.check()){
			layout = ViewConfig.PATH + ViewConfig.HOME_INDEX_USERS;
		}else{
			layout = ViewConfig.PATH + ViewConfig.HOME_INDEX_GUEST;
		}
		view.setLayout(layout);
		view.get();
	}
}
