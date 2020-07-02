package app.controller;

import app.config.PageConfig;
import app.config.ViewConfig;
import app.model.User;
import app.model.User_Info;
import app.services.auth.Auth;
import app.services.auth.SessionGuard;
import app.services.database.DatabaseManager;
import app.services.helpers.Helpers;
import app.services.helpers.Str;
import app.services.view.ForwardView;
import app.services.view.Parameter;
import app.services.view.View;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Home", urlPatterns = "/index.jsp")
 public class HomeController extends HttpServlet {

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
			User_Info user_info = auth.user().getInfo();
			Parameter<User_Info> parameter = new Parameter(user_info);
			view.setAttribute("user", parameter);
			layout = ViewConfig.PATH + ViewConfig.HOME_INDEX_USERS;
		}else{
			layout = ViewConfig.PATH + ViewConfig.HOME_INDEX_GUEST;
		}
		view.setLayout(layout);
		view.get();
	}
}
