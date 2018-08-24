package move;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GuestUser extends User {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/movedb?serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "root";
	
	private String email;
	private boolean paid;
	private int userID; 
	private int accountID = 1;

	public GuestUser(String email, int userID, int accountID) { // Do I need to do - this.email...etc when Ive already made a constructor.
		super(email, userID, accountID);
	
	}

	public String adminToGuestConversion() {
		return adminToGuestConversion(isPaid(), email);

	} 
	public boolean guestToAdminConversion() {
		return guestToAdminConversion(isPaid(), email);
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getEmail() { 
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
