package move;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import simple.LoginAttempt;
@Path("/login")
public class Login {

	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost/movedb?serverTimezone=UTC";

	String USER = "root";
	String PASS = "root";

	private String email, password, emailAttempt, passwordAttempt;
	private boolean passwordSuccess, emailSuccess;
	private int accountID, teamID;

	public Login() {
	}

	@POST
	@Produces("Application/json")
	@Consumes("Application/json")
	@Path("/loginAttempt")
	public String loginAttempt(LoginAttempt la) { 
		try( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();) {

			System.out.println("Connecting to database..."); 
			System.out.println("Creating statement...");
			String sql = "SELECT Email FROM User WHERE Email ='"+la.getEmail()+"'"; 
			ResultSet rs = stmt.executeQuery(sql); ////// Checking email
			while (rs.next()) {
				email = rs.getString("email");
			}

			if (email == null) {
				System.out.println("failure"); 
				return "{\"login\":\"failure\"}";

			}
			else if (email.equals(la.getEmail())) {
				emailSuccess = true;
			}
			else {
				emailSuccess = true;
			}
			rs.close();

			sql = "SELECT Password FROM user WHERE Password ='"+la.getPassword()+"' AND Email = '"+la.getEmail()+"'";
			rs = stmt.executeQuery(sql); ///////// Checking password
			if (rs.next()) {
				rs.first();
			}

			password = rs.getString("password"); 			
			if (password.length() == 0) {
				passwordSuccess = false;
			}
			
			else if (password.equals(la.getPassword())) { 
				passwordSuccess = true;
			}
			
			else {
				passwordSuccess = false;
			}
			rs.close();

			if (passwordSuccess && emailSuccess) {
			     sql = "SELECT account_id from user WHERE email = '"+la.getEmail()+"'";
				rs = stmt.executeQuery(sql); // Getting accountID so that browser can use webstorage.
				if (rs.next()) {
					rs.first();
				}
				accountID = rs.getInt("account_id");
				rs.close();
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		if (passwordSuccess && emailSuccess) {
			System.out.println("success"); 
			return "{\"login\":\"success\",\"accountType\":\""+accountID+"\",\"teamID\":\""+teamID+"\"}"; 
		}
		else {
			System.out.println("failure"); 
			return "{\"login\":\"failure\"}";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}