package app.config;

public interface SQL {

	String GET_BY_ID_SQL = "SELECT * FROM ? WHERE `id` = ?;";

	// Phone
	String ID_TAB_PHONES_FROM_PHONE     = "SELECT `id` FROM `phones` WHERE `number` = ?";
	String USER_ID_TAB_PHONES_FROM_PHONE = "SELECT `user_id` FROM `phones` WHERE `number` = ?";
	String ALL_TAB_PHONES_FROM_PHONE     = "SELECT * FROM `phones` WHERE `number` = ?";

	// Region
	String ID_TAB_P_REGION_FROM_REGION = "SELECT `id` FROM `phone_regions` WHERE `region` = ?";

	// Insert users
	String CREATE_USERS      = "INSERT INTO `users` (`password`, `created_at`) VALUE (?, ?)";
	String CREATE_PHONES     = "INSERT INTO `phones` (`user_id`, `region_id`, `number`, `created_at`) VALUE (?, ?, ?, ?)";
	String CREATE_USERS_INFO = "INSERT INTO `user_infos` (`user_id`, `last_name`, `first_name`, `updated_at`) VALUE (?, ?, ?, ?)";
	String CREATE_REGIONS    = "INSERT INTO `phone_regions` (`region`, `created_at`) VALUE (?, ?)";

	// get user by token
	String USER_BY_ID_TOKEN  = "SELECT * FROM `users` WHERE `id` = ? AND `remember_token` = ?;";
	String USER_UPDATE_TOKEN = "UPDATE `users` SET `remember_token` = ? WHERE `id` = ?;";


}
