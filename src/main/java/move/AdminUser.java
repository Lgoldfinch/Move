package move;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminUser extends User {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/movedb?serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "root";	

	private int userID;
	private String email;
	private int accountID = 2;
	

	public AdminUser(String email, int userID, int accountID) {
		super(email, userID, accountID);
	}

	public String adminToGuestConversion() {
		return adminToGuestConversion(isPaid(), email);
	}
	public boolean guestToAdminConversion() {
		return guestToAdminConversion(isPaid(), email);
	}
	}