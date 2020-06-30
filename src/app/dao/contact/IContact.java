package app.dao.contact;

import app.model.Contact;

import java.util.List;

public interface IContact {
	Contact findSingle(long user_id_1, long user_id_2);
	void insert(Contact contact);
}
