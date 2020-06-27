package app.services.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TextView extends ViewImpl {
	public TextView(HttpServletRequest req, HttpServletResponse resp) {
		super(req, resp);
	}
	public TextView(HttpServletRequest req, HttpServletResponse resp, String layout) {
		super(req, resp, layout);
	}

	@Override
	public void get() {
		pushAttribute();

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(layout);
		out.flush();
	}
}
