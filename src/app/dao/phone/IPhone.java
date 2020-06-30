package app.dao.phone;

import app.model.Phone;

public interface IPhone {
	Phone findByUserId(long user_id);
	Phone findByPhone(String phone);
	Phone findByPhoneNotMe(String number, long user_id);
}
