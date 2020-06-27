package app.services.ui.auth;

import app.config.PageConfig;
import app.config.SQL;
import app.config.ValidPattern;
import app.model.User;
import app.services.auth.Guard;
import app.services.auth.SessionGuard;
import app.services.database.DatabaseManager;
import app.services.validate.PhoneValid;
import app.services.validate.Validation;
import app.services.view.TextView;
import app.services.view.View;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistersUsers {
	public RegistersUsers(){
	}

	public void register(HttpServletRequest req, HttpServletResponse resp) {

		List<String> errors = validator(req);

		View view = new TextView(req, resp);
		view.setContentType(PageConfig.TYPE_CONTENT_JSON);

		String json = null;
		JSONObject jsonResponse = new JSONObject();

		if (errors.size() > 0){
			JSONArray jsonArray = new JSONArray(errors);
			jsonResponse.put("ststus", 2);
			jsonResponse.put("errors", jsonArray);
		}else{
			User user = create(req);
			if (user == null) {
				jsonResponse.put("ststus", 2);
				jsonResponse.append("errors", "Lỗi bất ngờ, vui lòng thử lại.");
			}else{
				jsonResponse.put("ststus", 1);
				Guard guard = new SessionGuard();
				guard.login(user);
			}
		}
		json = jsonResponse.toString();
		view.setLayout(json);
		view.get();
	}

	private User create(HttpServletRequest req) {
		String last_name  = req.getParameter("last_name");
		String first_name = req.getParameter("first_name");
		String phone      = req.getParameter("phone");
		String password   = req.getParameter("password");

		Pattern pattern = Pattern.compile(ValidPattern.PHONE_PATTERN);
		Matcher matcher = pattern.matcher(phone);

		if (matcher.matches()) {
			String region = matcher.group(1);
			String number = matcher.group(2);

			password = BCrypt.withDefaults().hashToString(12, password.toCharArray());

			DatabaseManager databaseManager = DatabaseManager.getInstance();
			Connection connection = databaseManager.getConnection();
			try {
				connection.setAutoCommit(false);

				// tạo người dùng
				PreparedStatement pstmt = connection.prepareStatement(SQL.CREATE_USERS, Statement.RETURN_GENERATED_KEYS);

				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String time         = timestamp.toString();

				pstmt.setString(1, password);
				pstmt.setString(2, time);

				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();

				long users_id = 0;
				if(rs.next()) {
					users_id = rs.getLong(1);
				}

				// Kiểm tra vùng của số điện thoại
				int region_id = 0;
				try {
					pstmt = databaseManager.getConnection().prepareStatement(SQL.ID_TAB_P_REGION_FROM_REGION);
					pstmt.setString(1, region);
					ResultSet result = pstmt.executeQuery();
					if (result.next()) {
						region_id = result.getInt("id");
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

				if(region_id == 0){
					// Tạo mã Khu vực
					pstmt = connection.prepareStatement(SQL.CREATE_REGIONS, Statement.RETURN_GENERATED_KEYS);

					pstmt.setString(1, region);
					pstmt.setString(2, time);

					pstmt.execute();
					rs = pstmt.getGeneratedKeys();

					if(rs.next()) {
						region_id = rs.getInt(1);
					}
				}

				// Số điện thoại
				pstmt = connection.prepareStatement(SQL.CREATE_PHONES);

				pstmt.setLong(1,   users_id);
				pstmt.setLong(2,   region_id);
				pstmt.setString(3, number);
				pstmt.setString(4, time);
				pstmt.execute();

				// tạo thông tin
				pstmt = connection.prepareStatement(SQL.CREATE_USERS_INFO);
				pstmt.setLong(1,   users_id);
				pstmt.setString(2, last_name);
				pstmt.setString(3, first_name);
				pstmt.setString(4, time);
				pstmt.execute();

				connection.commit();

				User user = new User();
				user.find(users_id);

				System.out.println(user.getId());
				System.out.println(user.getPassword());

				return user;

			} catch (SQLException throwables) {
				try {
					connection.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				throwables.printStackTrace();
			}
		}

		return null;
	}

	private List<String> validator(HttpServletRequest req) {
		List<String> errors     = new ArrayList<String>();

		String last_name        = req.getParameter("last_name");
		String first_name       = req.getParameter("first_name");
		String phone            = req.getParameter("phone");
		String password         = req.getParameter("password");
		String password_confirm = req.getParameter("password-confirm");

		// Kiểm tra các trường nhập đầy đủ
		if (last_name == null || first_name == null || phone == null || password == null || password_confirm == null){
			errors.add("Vui lòng nhập đầy đủ các thông tin.");
		}else{
			// Kiểm tra Mật khẩu trùng khớp.
			if (password .length() < 5 || !password.equals(password_confirm)){
				errors.add("Nhập lại mật khẩu không đúng");
			}else{
				// Kiểm tra số điện thoại hợp lệ?
				Validation valid = new PhoneValid();
				if (valid.check(phone) == false){
					errors.add("Số điện thoại không hợp lệ.");
				}else{
					// Kiểm tra tên hợp lệ
					if (last_name.length() < 2 || last_name.length() > 40 || first_name.length() < 2 || first_name.length() > 40){
						errors.add("Họ và Tên không hợp lệ.");
					}else{
						// Kiểm tra số điện thoại đã tồn lại hay chưa?
						DatabaseManager databaseManager = DatabaseManager.getInstance();
						try {
							PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(SQL.ID_TAB_PHONES_FROM_PHONE);
							preparedStatement.setString(1, phone);
							ResultSet result = preparedStatement.executeQuery();
							if (result.next()) {
								System.out.println(result.getLong("id"));
								errors.add("Số điện thoại đã tồn tại.");
							}
						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}
					}
				}
			}
		}
		return errors;
	}
}
