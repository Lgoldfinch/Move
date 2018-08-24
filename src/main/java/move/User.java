package move;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class User {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/movedb?serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "root";	

	private int userID, accountID;
	private String email;
	private boolean paid;

	public User(String email, int userID, int accountID) {
		this.email = email;
		this.userID = userID;
		this.accountID = accountID;
	}

	public boolean guestToAdminConversion(boolean paid, String email) {
		try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");
			String sql;
			if (paid = true) {
				sql = "UPDATE User " + "SET account_id = 2 WHERE email = '"+email+"'";
				stmt.executeUpdate(sql);
				System.out.println("Changed the account type of the user from guest to admin.");
				return true;
			}
			else if (paid = false) { 
				System.out.println("User is not granted administrator privileges before subscribing.");
				return false;
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
 // is this needed.
		return false;
	}

	public String adminToGuestConversion(boolean paid, String email) {

		try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");
			String sql;
			if (!paid) {
				sql = "UPDATE User " + "SET account_id = 1 where email = '"+email+"'";
				stmt.executeUpdate(sql);
				System.out.println("Changed the account type of the user from admin to guest. Administrative privileges revoked.");
			}
			else if (paid) {
				System.out.println("Admin has paid.");
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return "__";
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
		}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserId() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}