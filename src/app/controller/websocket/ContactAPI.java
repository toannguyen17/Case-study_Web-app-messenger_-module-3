package app.controller.websocket;

import app.dao.contact.ContactDAO;
import app.dao.contact.IContact;
import app.dao.my_contact.IMyContact;
import app.dao.my_contact.MyContactDAO;
import app.messager.PaginationMessenger;
import app.model.Contact;
import app.model.MyContact;
import app.model.User_Info;
import app.services.websocket.Client;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ContactAPI implements SocketAPI {
	private static ContactAPI instance;
	private ContactAPI(){
	}

	public static ContactAPI getInstance() {
		if (instance == null){
			synchronized (ContactAPI.class){
				instance = new ContactAPI();
			}
		}
		return instance;
	}

	@Override
	public void request(JSONObject mesg, Client client) {
		boolean checkType = mesg.isNull("type");
		if (checkType != true) {
			try {
				long id     = client.auth.user().getId();
				String type = mesg.getString("type");
				switch (type){
					case "get":
						boolean checkStartNumber = mesg.isNull("type");
						if (checkStartNumber == false){
							int start = mesg.getInt("start");

							get(client, start);
						}
						break;
				}
			}catch (JSONException e){
			}
		}
	}

	private void get(Client client, int start){
		IMyContact iMyContact = new MyContactDAO();
		long id = client.auth.user().getId();

		JSONObject json = new JSONObject();
		json.put("action", "contacts");
		json.put("type", "get");

		List<MyContact> lists = iMyContact.findByUserId(id, start);
		int total = iMyContact.countByUserId(id);

		PaginationMessenger pagination = new PaginationMessenger(start, total);

		json.put("pagination", pagination);

		lists.forEach(myContract -> {
			JSONObject JContact = new JSONObject();

			List<MyContact> listsToContact = iMyContact.findByContactIdNotUser(myContract.getContact_id(), id);

			if (listsToContact.size() > 1){
				JContact.put("group", true);
			}else{
				MyContact myContact = listsToContact.get(0);
				User_Info user_info = myContact.getUser().getInfo();
				JContact.put("id",         myContact.getUser_id());
				JContact.put("last_name",  user_info.getLast_name());
				JContact.put("first_name", user_info.getFirst_name());
				JContact.put("avatar",     user_info.getUrlAvatar());
			}

			json.append("contacts", JContact);
		});

		client.send(json);
	}
}
