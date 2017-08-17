package by.epam.bohnat.provider.dao.impl;

/**
 * Defines a set of String constants that describe SQL queries.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
class DBHelper {

	static final String SQL_SELECT_USER_BY_ID = "SELECT user.u_id,user.u_name,user.u_surname,user.u_login,user.u_password,user.u_email,user.u_birth_date,user.u_reg_time,user.u_phone,user.u_role_id FROM user JOIN role ON role.r_id=user.u_role_id WHERE user.u_id=?";
	static final String SQL_SELECT_USER_BY_LOGIN = "SELECT user.u_id,user.u_name,user.u_surname,user.u_login,user.u_password,user.u_email,user.u_birth_date,user.u_reg_time,user.u_phone,user.u_role_id FROM user JOIN role ON role.r_id=user.u_role_id WHERE user.u_login=?";
	static final String SQL_SELECT_USER_LIST = "SELECT user.u_id,user.u_name,user.u_surname,user.u_login,user.u_password,user.u_email,user.u_birth_date,user.u_reg_time,user.u_phone,user.u_role_id FROM user JOIN role ON role.r_id=user.u_role_id WHERE user.u_role_id=1";
	static final String SQL_SELECT_ADMIN_LIST = "SELECT user.u_id,user.u_name,user.u_surname,user.u_login,user.u_password,user.u_email,user.u_birth_date,user.u_reg_time,user.u_phone,user.u_role_id FROM user JOIN role ON role.r_id=user.u_role_id WHERE user.u_role_id=2";
	static final String SQL_SELECT_COUNT_USERS = "SELECT COUNT(*) FROM user WHERE user.u_role_id=1";
	static final String SQL_SELECT_COUNT_ADMINS = "SELECT COUNT(*) FROM user WHERE user.u_role_id=2";
	static final String SQL_SELECT_USERS_LIST_PART = "SELECT * FROM user WHERE user.u_role_id=1 ORDER BY user.u_surname ASC LIMIT ?, ?";
	static final String SQL_SELECT_ADMINS_LIST_PART = "SELECT * FROM user WHERE user.u_role_id=2 ORDER BY user.u_surname ASC LIMIT ?, ?";
	static final String SQL_SELECT_ALL_TARIFFS = "SELECT tariff.t_id,tariff.t_name,tariff.t_tariff_type_id,tariff.t_rec_speed,tariff.t_trans_speed,tariff.t_subscription_fee,tariff.t_traffic_volume,tariff.t_overdraft_amount FROM tariff";
	static final String SQL_SELECT_UNLIM_TARIFFS = "SELECT tariff.t_id,tariff.t_name,tariff.t_tariff_type_id,tariff.t_rec_speed,tariff.t_trans_speed,tariff.t_subscription_fee FROM tariff JOIN tariff_type ON tariff_type.tt_id=tariff.t_tariff_type_id WHERE tariff_type.tt_id=1";
	static final String SQL_SELECT_LIM_TARIFFS = "SELECT tariff.t_id,tariff.t_name,tariff.t_tariff_type_id,tariff.t_rec_speed,tariff.t_trans_speed,tariff.t_subscription_fee,tariff.t_traffic_volume,tariff.t_overdraft_amount FROM tariff JOIN tariff_type ON tariff_type.tt_id=tariff.t_tariff_type_id WHERE tariff_type.tt_id=2";
	static final String SQL_SELECT_TARIFF_BY_ID = "SELECT tariff.t_id,tariff.t_name,tariff.t_tariff_type_id,tariff.t_rec_speed,tariff.t_trans_speed,tariff.t_subscription_fee,tariff.t_traffic_volume,tariff.t_overdraft_amount FROM tariff JOIN tariff_type ON tariff_type.tt_id=tariff.t_tariff_type_id WHERE tariff.t_id=?";
	static final String SQL_SELECT_PASSWORD_BY_LOGIN = "SELECT user.u_password FROM user WHERE user.u_login=?";
	static final String SQL_SELECT_ACCOUNT_BY_USER = "SELECT account.a_id,account.a_tariff_id,account.a_block_id,account.a_account_number,account.a_amount,account.a_payment_date,account.a_spent_traffic FROM account WHERE account.a_user_id=?";
	static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT account.a_user_id,account.a_tariff_id,account.a_block_id,account.a_account_number,account.a_amount,account.a_payment_date,account.a_spent_traffic FROM account WHERE account.a_id=?";
	static final String SQL_SELECT_COUNT_NON_PAYERS = "SELECT COUNT(*) FROM account WHERE account.a_amount<0 AND account.a_block_id=1";
	static final String SQL_SELECT_NON_PAYERS_LIST_PART = "SELECT * FROM account WHERE account.a_amount<0 AND account.a_block_id=1 ORDER BY account.a_id ASC LIMIT ?, ?";
	static final String SQL_SELECT_ALL_REQUESTS = "SELECT user_request.ur_id,user_request.ur_user_id,user_request.ur_tariff_id,user_request.ur_description,user_request.ur_req_date FROM user_request";
	static final String SQL_SELECT_ALL_NON_PAYERS = "SELECT account.a_id,account.a_user_id,account.a_tariff_id,account.a_block_id,account.a_account_number,account.a_amount,account.a_payment_date,account.a_spent_traffic FROM account WHERE account.a_amount<0 AND account.a_block_id=1";
	static final String SQL_SELECT_REQUEST_BY_USER = "SELECT user_request.ur_id,user_request.ur_tariff_id,user_request.ur_description,user_request.ur_req_date FROM user_request WHERE user_request.ur_user_id=?";
	static final String SQL_SELECT_REQUEST_BY_ID = "SELECT user_request.ur_user_id,user_request.ur_tariff_id,user_request.ur_description,user_request.ur_req_date FROM user_request WHERE user_request.ur_id=?";
	static final String SQL_SELECT_COUNT_REQUESTS = "SELECT COUNT(*) FROM user_request";
	static final String SQL_SELECT_REQUESTS_LIST_PART = "SELECT * FROM user_request ORDER BY user_request.ur_req_date DESC LIMIT ?, ?";
	static final String SQL_INSERT_TARIFF = "INSERT INTO tariff(t_tariff_type_id, t_name, t_rec_speed,t_trans_speed,t_subscription_fee,t_traffic_volume,t_overdraft_amount ) VALUES(?,?,?,?,?,?,?)";
	static final String SQL_INSERT_USER = "INSERT INTO user(user.u_name,user.u_surname,user.u_login,user.u_password,user.u_email,user.u_birth_date,user.u_reg_time,user.u_phone,user.u_role_id) VALUES(?,?,?,?,?,?,?,?,?)";
	static final String SQL_INSERT_REQUEST = "INSERT INTO user_request(user_request.ur_user_id,user_request.ur_tariff_id,user_request.ur_description,user_request.ur_req_date) VALUES(?,?,?,?)";
	static final String SQL_INSERT_ACCOUNT = "INSERT INTO account(account.a_user_id,account.a_tariff_id,account.a_block_id,account.a_account_number,account.a_amount,account.a_payment_date,account.a_spent_traffic) VALUES(?,?,?,?,?,?,?)";
	static final String SQL_UPDATE_USER_BY_ID = "UPDATE user SET user.u_name=?,user.u_surname=?,user.u_login=?,user.u_password=?,user.u_email=?,user.u_birth_date=?,user.u_reg_time=?,user.u_phone=?,user.u_role_id=? WHERE user.u_id=?";
	static final String SQL_UPDATE_TARIFF = "UPDATE tariff SET tariff.t_tariff_type_id=?, tariff.t_name=?, tariff.t_rec_speed=?, tariff.t_trans_speed=?, tariff.t_subscription_fee=?, tariff.t_traffic_volume=?, tariff.t_overdraft_amount=? WHERE tariff.t_id=?";
	static final String SQL_UPDATE_ACCOUNT = "UPDATE account SET account.a_user_id=?, account.a_tariff_id=?, account.a_block_id=?, account.a_account_number=?, account.a_amount=?, account.a_payment_date=?, account.a_spent_traffic=? WHERE account.a_id=?";
	static final String SQL_DELETE_TARIFF_BY_ID = "DELETE FROM tariff WHERE t_id=?";
	static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE user.u_id=?";
	static final String SQL_DELETE_REQUEST_BY_ID = "DELETE FROM user_request WHERE user_request.ur_id=?";
}
