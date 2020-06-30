package app.dao.messenger_text;

import app.model.MessageText;

public interface IMessengerText {
	void insert(MessageText messageText);
	MessageText findByMessegerId(long id);
}
