package app.controller.websocket;

import app.dao.contact.ContactDAO;
import app.dao.contact.IContact;
import app.dao.messager.IMessager;
import app.dao.messager.MessagerDAO;
import app.dao.messenger_text.IMessengerText;
import app.dao.messenger_text.MessengerTextDAO;
import app.dao.my_contact.IMyContact;
import app.dao.my_contact.MyContactDAO;
import app.messager.ChatMessager;
import app.model.*;
import app.services.websocket.Client;
import app.services.websocket.WebSocket;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ChatAPI implements SocketAPI {
	private static ChatAPI instance;
	private ChatAPI(){
	}

	public static ChatAPI getInstance() {
		if (instance == null){
			synchronized (ChatAPI.class){
				instance = new ChatAPI();
			}
		}
		return instance;
	}

	@Override
	public void request(JSONObject mesg, Client client) {
		boolean checkData = mesg.isNull("user_id");
		if (checkData != true) {
			try {
				long id        = client.auth.user().getId();
				long user_id   = mesg.getLong("user_id");

				IContact iContact = new ContactDAO();

				Contact contact = iContact.findSingle(id, user_id);

				if (!mesg.isNull("text")){
					String text = mesg.getString("text");
					sendMessenger(client, user_id, text, contact);
				}else if (!mesg.isNull("last_id")){
					String last_id = mesg.getString("last_id");
					viewMessenger(client, last_id, contact);
				}else{
					newContact(client, user_id, contact);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	private void newContact(Client client, long user_id, Contact contact) {
		User user = new User();
		user.find(user_id);
		if (user.getId() != 0){
			User_Info user_info = user.getInfo();
			ChatMessager chatMessager = new ChatMessager(user_info);

			if (contact != null){

				IMessager iMessager = new MessagerDAO();
				List<Message> list = iMessager.getMessager(contact.getId(), 0, 20, "DESC");

				long id = client.auth.user().getId();

				list.forEach(e -> {
					MessageText messageText = e.getText();
					if (messageText != null){
						JSONObject jsonMess = new JSONObject();
						jsonMess.put("id", messageText.getId());
						jsonMess.put("text", messageText.getText());
						jsonMess.put("time", e.getCreated_at());
						jsonMess.put("user_id", e.getUser_id());

						if (e.getUser_id() != id){
							jsonMess.put("avatar", user_info.getUrlAvatar());
						}
						chatMessager.append("chats", jsonMess);
					}
				});
			}
			client.send(chatMessager);
		}
	}

	private void sendMessenger(Client client, long to_id, String text, Contact contact) {
		long id = client.auth.user().getId();
		User user = new User();
		user.find(to_id);

		if (user.getId() != 0) {
			boolean add_contact = false;
			User_Info user_info = client.auth.user().getInfo();

			ChatMessager chatMessager_to = null;

			ChatMessager chatMessager = new ChatMessager(user_info);
			chatMessager.put("to",   to_id);
			chatMessager.put("from", id);

			if (contact == null) {
				// Tạo liên hệ
				add_contact = true;

				User_Info user_info_to = user.getInfo();
				chatMessager_to = new ChatMessager(user_info_to);

				chatMessager.put("to",   to_id);
				chatMessager.put("from", id);

				chatMessager_to.put("add_contact", true);
				chatMessager.put("add_contact", true);
				contact = new Contact();

				IContact iContact = new ContactDAO();

				iContact.insert(contact);
				if (contact.getId() > 0) {

					MyContact myContact1 = new MyContact();
					myContact1.setUser_id(id);
					myContact1.setContact_id(contact.getId());

					IMyContact iMyContact = new MyContactDAO();
					iMyContact.insert(myContact1);

					MyContact myContact2 = new MyContact();
					myContact2.setUser_id(to_id);
					myContact2.setContact_id(contact.getId());
					iMyContact.insert(myContact2);
				} else {
					return;
				}
			}

			IMessager iMessager = new MessagerDAO();
			Message message = iMessager.insert(contact.getId(), id);

			MessageText messageText = new MessageText();
			messageText.setMessage_id(message.getId());
			messageText.setText(text);

			IMessengerText iMessagerText = new MessengerTextDAO();
			iMessagerText.insert(messageText);

			JSONObject jsonText = new JSONObject();
			jsonText.put("id", messageText.getMessage_id());
			jsonText.put("time", message.getCreated_at());
			jsonText.put("text", text);
			jsonText.put("user_id", id);
			jsonText.put("avatar", user_info.getUrlAvatar());

			chatMessager.put("messenger", jsonText);

			WebSocket webSocket = WebSocket.getInstance();

			webSocket.send(to_id, chatMessager);

			if (add_contact){
				chatMessager_to.put("messenger", jsonText);
				webSocket.send(id, chatMessager_to);
			}else{
				webSocket.send(id, chatMessager);
			}
		}
	}

	private void viewMessenger(Client client, String last_id, Contact contact){

	}
}
