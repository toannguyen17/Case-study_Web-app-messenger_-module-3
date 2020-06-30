package app.services.ui.auth;

import app.config.PageConfig;
import app.config.SQL;
import app.config.ValidPattern;
import app.model.User;
import app.services.auth.Guard;
import app.services.auth.SessionGuard;
import app.services.database.DatabaseManager;
import app.services.helpers.Helpers;
import app.services.validate.PhoneValid;
import app.services.validate.Validation;
import app.services.view.TextView;
import app.services.view.View;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginUsers {
	public LoginUsers() {
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) {
		List<String> errors = validator(req);

		View view = new TextView(req, resp);
		view.setContentType(PageConfig.TYPE_CONTENT_JSON);

		String json = null;
		JSONObject jsonResponse = new JSONObject();

		if (errors.size() > 0) {
			JSONArray jsonArray = new JSONArray(errors);
			jsonResponse.put("status", 2);
			jsonResponse.put("errors", jsonArray);
		}else{
			User user = attemptLogin(req);
			if(user != null) {
				jsonResponse.put("status", 1);
				Guard guard = new SessionGuard(req, resp);
				guard.login(user);
			}else{
				jsonResponse.put("status", 2);
				jsonResponse.append("errors", "Tài khoản hoặc mật khẩu không chính xác.");
			}
		}
		json = jsonResponse.toString();
		view.setLayout(json);
		view.get();
	}

	private User attemptLogin(HttpServletRequest req){
		String phone    = req.getParameter("phone");
		String password = req.getParameter("password");

		Pattern pattern = Pattern.compile(ValidPattern.PHONE_PATTERN);
		Matcher matcher = pattern.matcher(phone);

		if (matcher.matches()) {
			String region = matcher.group(1);
			String number = matcher.group(2);

			DatabaseManager databaseManager = DatabaseManager.getInstance();
			Connection connection = databaseManager.getConnection();

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(SQL.USER_ID_TAB_PHONES_FROM_PHONE);
				preparedStatement.setString(1, number);
				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					long user_id = result.getLong("user_id");
					User user    = new User();
					user.find(user_id);
					String password_user = user.getPassword();

					if(password_user != null){
						BCrypt.Result checkPass = BCrypt.verifyer().verify(password.toCharArray(), password_user);
						if (checkPass.verified){
							return user;
						}
					}
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}

		return null;
	}

	private List<String> validator(HttpServletRequest req) {
		List<String> errors = new ArrayList<String>();

		String phone = req.getParameter("phone");
		String password = req.getParameter("password");

		String fail       = "Tài khoản hoặc mật khẩu không chính xác.";
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
