package app.services.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardView extends ViewImpl {
	public ForwardView(HttpServletRequest req, HttpServletResponse resp) {
		super(req, resp);
	}
	public ForwardView(HttpServletRequest req, HttpServletResponse resp, String layout) {
		super(req, resp, layout);
	}

	@Override
	public void get() {
		pushAttribute();

		RequestDispatcher dispatcher = request.getRequestDispatcher(layout);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
