package app.controller;

import app.config.ViewConfig;
import app.services.auth.Auth;
import app.services.auth.SessionGuard;
import app.services.database.DatabaseManager;
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
		Auth auth = new SessionGuard(req);

		if (auth.check()){

		}else{

		}

//		String password = "1234";
//		String password_err = "1234545454";
//		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
//// $2a$12$US00g/uMhoSBm.HiuieBjeMtoN69SN.GE25fCpldebzkryUyopws6
//
//		System.out.println(bcryptHashString);
//
//		BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
//// result.verified == true
//
//		System.out.println(result.verified);
//		System.out.println(result);
//		System.out.println("---------------------");
//
//
//		BCrypt.Result result2 = BCrypt.verifyer().verify(password_err.toCharArray(), bcryptHashString);
//		System.out.println(result2.verified);
//		System.out.println(result2);
//		System.out.println("---------------------22222222222222222222222");




		String layout = ViewConfig.PATH + ViewConfig.HOME_INDEX_GUEST;
		View view = new ForwardView(req, resp, layout);
		view.get();
	}
}
