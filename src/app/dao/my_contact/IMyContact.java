package app.dao.my_contact;

import app.model.MyContact;

import java.util.List;

public interface IMyContact {
	MyContact findById(long id);
	List<MyContact> findByUserId(long user_id);
	List<MyContact> findByContactId(long contact_id);
	void insert(MyContact myContact);
}
