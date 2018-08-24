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
import javax.ws.rs.Produces;

import simple.registerAttempt;
@Path("/register")
public class Register {

	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost/movedb?serverTimezone=UTC";

	String USER = "root";
	String PASS = "root";

	private String email, password, name;
	int userID;

	public Register() {
	}
	
	@POST
@Produces("Application/json")
@Consumes("Application/json")
@Path("/registerGuestAttempt")
	public String registerGuestAttempt(registerAttempt ra) { 
		
		try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); 
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");

			String sql = "SELECT email FROM user WHERE email = '"+ra.getEmail()+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				email = rs.getString("email");
			}
			if (email == null) {
			}
			else if ((email.equals(ra.getEmail()))) {
				return "{\"register\":\"failure\"}";
			}
			else {
				return "{\"register\":\"failure\"}";
			}
		
			sql = "SELECT User_id FROM User ORDER BY User_id DESC LIMIT 1";
			rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				int userID = rs.getInt("User_id");
				this.userID = userID + 1;
			}
			rs.close();
			sql = "INSERT INTO user " + "Values('"+userID+"','"+ra.getFirstName()+"','"+ra.getEmail()+"','"+ra.getPassword()+"','1','0', '"+ra.getLastName()+"')"; //
			stmt.executeUpdate(sql);
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		System.out.println("success"); 
		return "{\"register\":\"success\"}"; 
	}
	
	@POST
	@Produces("Application/json")
	@Consumes("Application/json")
	@Path("/registerAdminAttempt")
public String registerAdminAttempt(registerAttempt ra) { 
		
		try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); 
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");

			String sql = "SELECT email FROM user WHERE email = '"+ra.getEmail()+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				email = rs.getString("email");
			}
			if (email == null) {
			}
			else if ((email.equals(ra.getEmail()))) {
				return "{\"register\":\"failure\"}";
			}
			else {
				return "{\"register\":\"failure\"}";
			}
		
			sql = "SELECT User_id FROM User ORDER BY User_id DESC LIMIT 1";
			rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				int userID = rs.getInt("User_id");
				this.userID = userID + 1;
			}
			rs.close();
			sql = "INSERT INTO user " + "Values('"+userID+"','"+ra.getFirstName()+"','"+ra.getEmail()+"','"+ra.getPassword()+"','2','0','"+ra.getLastName()+"')";
			stmt.executeUpdate(sql);
			
		}
		catch(SQLException e) {
			e.printStackTrace();

		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return "{\"register\":\"success\"}";
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}