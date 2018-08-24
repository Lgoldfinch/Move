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
				int teamID = rs.getInt("Team_id");
				teamID = teamID + 1;
				String sql3 = "INSERT INTO Team VALUES ('"+teamID+"','"+cta.getTeamName()+"', '"+cta.getPassword()+"')";
				stmt.executeQuery(sql);
			}
			System.out.println("Team created, Name: '"+cta.getTeamName()+"', Password: '"+cta.getPassword()+"'");
			rs.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return "{\"teamCreate\":\"success\"}"; 
	}

	public boolean addPlayer2(int userID, String passwordAttempt, String teamName) {
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

	public boolean deleteTeam(int teamID, boolean confirmDecision, String email, User AdminUser) {
		try( Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); 
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");
			if (confirmDecision) {
				String sql = "DELETE FROM Team WHERE Team_id ='"+teamID+"'"; // Might need a different input to teamID.
				stmt.executeUpdate(sql); 
				return true;
			}
			else {
				return false;
			}
		}

		//			String sql; 
		//			sql = "SELECT Account_id FROM User Where email ='"+email+"'";
		//			stmt.executeQuery(sql);
		//			ResultSet rs = stmt.executeQuery(sql); 
		//			while(rs.next()) {
		//				int accountID = rs.getInt("account_id");
		//				this.accountID = accountID;
		//
		//				if (accountID == 2) {
		//					String sql2 = "DELETE FROM Team WHERE Team_id ='"+teamID+"'"; // Might need a different input to teamID.
		//					stmt.executeUpdate(sql2); 
		//					return true;
		//				}
		//				else {
		//					return false; 
		//				}
		//			}

		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return false;
	}



			//		String sql; 
			//		sql = "SELECT Account_id FROM User Where email ='"+email+"'"; // I created a field for this, not sure if this will work.
			//		stmt.executeQuery(sql);
			//		ResultSet rs = stmt.executeQuery(sql); 
			//		while(rs.next()) {
			//			int accountID = rs.getInt("account_id");                    // User's must be in team surely? 
			//			this.accountID = accountID;
			//			if (accountID == 2) { 
			//				String sql2 = "UPDATE User SET team_id = '"+teamID+"' WHERE user_ID = '"+userID+"'";
			//				return true;
			//			}
			//			else {
			//				return false; 
			//			}
			//		}
			//		rs.close();
	
	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

}
// Potentially add a function so that if there are no admins that all the features become locked.
