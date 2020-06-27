package app.services.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IncludeView extends ViewImpl {
	public IncludeView(HttpServletRequest req, HttpServletResponse resp) {
		super(req, resp);
	}

	public IncludeView(HttpServletRequest req, HttpServletResponse resp, String layout) {
		super(req, resp, layout);
	}

	@Override
	public void get() {
		response.setContentType(contentType);
		response.setCharacterEncoding(encoding);

		RequestDispatcher dispatcher = request.getRequestDispatcher(layout);
		try {
			dispatcher.include(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void include(){
		get();
	}
}
