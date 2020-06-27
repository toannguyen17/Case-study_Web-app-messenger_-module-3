package app.config;

public interface SQL {

	String GET_BY_ID_SQL = "SELECT * FROM ? WHERE `id` = ?;";

	//
	String ID_TAB_PHONES_FROM_PHONE = "SELECT `id` FROM `phones` WHERE `number` = ?";

	//
	String ID_TAB_P_REGION_FROM_REGION = "SELECT `id` FROM `phone_regions` WHERE `region` = ?";

	//
	String CREATE_USERS      = "INSERT INTO `users` (`password`, `created_at`) VALUE (?, ?)";
	String CREATE_PHONES     = "INSERT INTO `phones` (`user_id`, `region_id`, `number`, `created_at`) VALUE (?, ?, ?, ?)";
	String CREATE_USERS_INFO = "INSERT INTO `user_infos` (`user_id`, `last_name`, `first_name`, `updated_at`) VALUE (?, ?, ?, ?)";
	String CREATE_REGIONS    = "INSERT INTO `phone_regions` (`region`, `created_at`) VALUE (?, ?)";



}
