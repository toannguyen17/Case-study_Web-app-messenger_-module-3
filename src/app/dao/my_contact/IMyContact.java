package app.dao.my_contact;

import app.model.MyContact;

import java.util.List;

public interface IMyContact {
	MyContact findById(long id);
	List<MyContact> findByUserId(long user_id);
	List<MyContact> findByUserId(long user_id, int start);

	List<MyContact> findByContactId(long contact_id);
	List<MyContact> findByContactIdNotUser(long contact_id, long user_id);
	void insert(MyContact myContact);

	int countByUserId(long id);
}
