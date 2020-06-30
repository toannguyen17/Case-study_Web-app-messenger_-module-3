package app.dao.messager;

import app.model.Message;

import java.util.List;

public interface IMessager {
	List<Message> getMessager(long contact_id, int start, int limit, String sort);

	Message insert(long id, long user_id);
}
