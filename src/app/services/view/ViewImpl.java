package app.services.view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class ViewImpl implements View {
	protected String contentType = "text/html";
	protected String encoding    = "UTF-8";
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	protected String layout;

	protected ViewImpl() {
	}

	protected ViewImpl(HttpServletRequest req, HttpServletResponse resp){
		request  = req;
		response = resp;
	}

	protected ViewImpl(HttpServletRequest req, HttpServletResponse resp, String layout){
		request  = req;
		response = resp;
		this.layout = layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
