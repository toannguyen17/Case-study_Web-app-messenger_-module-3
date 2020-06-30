package app.messager;

import org.json.JSONObject;

public class PaginationMessenger extends JSONObject {
	public PaginationMessenger (int start, int total){
		super();
		this.put("start", start);
		this.put("total", total);
		this.put("kmess", 20);
	}
}
