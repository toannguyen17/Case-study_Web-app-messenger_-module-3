package app.services.view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public interface View {
	void setLayout(String url);
	void setContentType(String contentType);
	void setEncoding(String encoding);
	void get();
}
