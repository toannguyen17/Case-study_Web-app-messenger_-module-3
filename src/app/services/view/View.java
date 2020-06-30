package app.services.view;

public interface View {
	void setLayout(String url);
	void setContentType(String contentType);
	void setCharacterEncoding(String encoding);
	void setHeader(String key, String value);
	void setContentLength(int length);
	void get();
	void setAttribute(String key, Parameter parameter);
}
