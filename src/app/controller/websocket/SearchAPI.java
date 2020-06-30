package app.controller.websocket;

import app.dao.phone.IPhone;
import app.dao.phone.PhoneDAO;
import app.messager.SearchPhoneMessager;
import app.model.Phone;
import app.services.helpers.Helpers;
import app.services.websocket.Client;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class SearchAPI implements SocketAPI {
	private static SearchAPI instance;
	private SearchAPI(){
	}

	public static SearchAPI getInstance() {
		if (instance == null){
			synchronized (SearchAPI.class){
				instance = new SearchAPI();
			}
		}
		return instance;
	}

	@Override
	public void request(JSONObject mesg, Client client) {
		boolean checkData = mesg.isNull("data");
		if (checkData != true) {
			try {
				String data = mesg.getString("data");

				if (data != null){
					Helpers helpers = new Helpers();
					Map<String, String> crackPhone = helpers.crackPhone(data);
					if (crackPhone != null){
						String number = crackPhone.get("number");

						IPhone iPhone = new PhoneDAO();
						Phone phone = iPhone.findByPhoneNotMe(number, client.auth.user().getId());

						if (phone != null){
							SearchPhoneMessager messager = new SearchPhoneMessager(phone);
							client.send(messager);
						}
					}
				}
				System.out.println(data);
			} catch (JSONException e) {
			}
		}
	}
}
