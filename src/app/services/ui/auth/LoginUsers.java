package app.services.ui.auth;

import app.services.validate.PhoneValid;
import app.services.validate.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class LoginUsers {
	public LoginUsers() {
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) {
		List<String> errors = validator(req);

		if (errors.size() > 0) {

		}else{

		}
	}

	private List<String> validator(HttpServletRequest req) {
		List<String> errors = new ArrayList<String>();

		String phone = req.getParameter("phone");
		String password = req.getParameter("password");

		String fail       = "Tài khoản hoặc mật khẩu không chính sác.";
		String fail_phone = "Số điện thoại không hợp lệ.";

		// Kiểm tra các trường nhập đầy đủ
		if (phone == null || password == null) {
			errors.add("Vui lòng nhập đầy đủ các thông tin.");
		} else {
			// Kiểm tra số điện thoại hợp lệ?
			Validation valid = new PhoneValid();
			if (valid.check(phone) == false) {
				errors.add(fail_phone);
			} else {
				if (password.length() < 6) {
					errors.add(fail);
				}
			}
		}
		return errors;
	}
}
