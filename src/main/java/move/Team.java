package move;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import simple.createTeamAttempt;

@Path("/team")
public class Team {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/movedb?serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "root";

	private int accountID, teamID, userID;
	private String teamName, email, password;
	private User AdminUser, GuestUser;

	public Team() {}

	@POST
	@Produces("Application/json")
	@Consumes("Application/json")
	@Path("/createTeam")
	public String createTeam(createTeamAttempt cta) {
		try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); 
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");

			String sql = "SELECT Team_id FROM Team ORDER BY Team_id DESC LIMIT 1"; // this was all user but I changed it to team...did i make a mistake?
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				this.teamID = rs.getInt("Team_id");
				teamID = teamID + 1;
			}
			rs.close();
			sql = "INSERT INTO Team VALUES ('"+teamID+"','"+cta.getTeamName()+"', '"+cta.getPassword()+"')";
			System.out.println("Team created, Name: '"+cta.getTeamName()+"', Password: '"+cta.getPassword()+"'");
			stmt.executeUpdate(sql);
			return "{\"teamID\":\""+teamID+"\"}"; 
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return "{\"teamCreate\":\"fail\"}"; 
	}

	public boolean addPlayer(int userID, String passwordAttempt, String teamName) { // Did not implement this.
		try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); 
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");

			String sql = "SELECT password FROM team WHERE password = '"+passwordAttempt+"' AND team_name = '"+teamName+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				rs.first();
			}
			password = rs.getString("password");
			rs.close();

			String sql2 = "SELECT team_id FROM team WHERE team_name = '"+teamName+"'";
			ResultSet rs2 = stmt.executeQuery(sql2);
			if (rs.next()) {
				rs.first();
			}
            teamID = rs2.getInt("team_id");
            rs2.close();
			if (password.equals(passwordAttempt)) {
				String sql3 = "UPDATE user SET team_id = '"+teamID+"' WHERE user_id = '"+userID+"'";
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteTeam(int teamID, boolean confirmDecision, String email, User AdminUser) { // Did not implement this.
		try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); 
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");
			if (confirmDecision) {
				String sql = "DELETE FROM Team WHERE Team_id ='"+teamID+"'";
				stmt.executeUpdate(sql); 
				return true;
			}
			else {
				return false;
			}
		}

		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return false;
	}
	
	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

}
