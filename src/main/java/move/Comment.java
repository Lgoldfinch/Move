package move;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Comment{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/movedb?serverTimezone=UTC"; // Will need the time changed here.

	static final String USER = "root";
	static final String Pass = "root";

	private User AdminUser, GuestUser;
	private String comment; //Might be better to be use them as objects not strings?
	private int commentID, accountID, userID;
	
	public Comment(int userID, String comment) {
		this.comment = comment;
		this.userID = userID;
	}

	public String leaveComment(int userID, String comment) {  // Could use AdminUser and GuestUser as objects in this potentially? Need to generate a comment ID.

		try( Connection conn = DriverManager.getConnection(DB_URL,USER,Pass);
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");
			String sql = "SELECT comment_id FROM comment ORDER BY comment_id DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()) {
				int commentID = rs.getInt("comment_id");
				commentID = commentID + 1;
				String sql2 = "INSERT INTO Comment VALUES ('"+commentID+"','"+userID+"', '"+comment+"')";  // Add in CommentID, remember that timestamp was meant to be in the SQL table.
				stmt.executeUpdate(sql2);
				System.out.println("User successfully left a comment.");
			}
			rs.close(); 
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return "__";
	}

	public String deleteOwnComment(int userID, int commentID) {

		try(Connection conn = DriverManager.getConnection(DB_URL,USER,Pass);
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");
			String sql = "DELETE FROM Comment WHERE USER_ID = '"+userID+"' && Comment_ID = '"+commentID+"'";
			stmt.executeUpdate(sql);
			System.out.println("Comment deleted.");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		
		return "__"; 
	}
	public String adminDeleteComment(int userID, int commentID, User AdminUser) {
		
		try( Connection conn = DriverManager.getConnection(DB_URL,USER,Pass);
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");

			String sql = "Delete FROM Comment WHERE comment_id = '"+commentID+"'";	
			stmt.executeUpdate(sql);
			System.out.println("Comment deleted");
		
//			String sql = "SELECT account_id FROM Comment WHERE User_ID = '"+userID+"' && Comment_ID = '"+commentID+"'";
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				int accountID = rs.getInt("account_id");
//				if (accountID == 2) {
//					String sql2 = "Delete FROM Comment WHERE comment_id = '"+commentID+"'";	
//					stmt.executeUpdate(sql2);
//					System.out.println("Comment deleted");
//					return true;
//				}
//				else {
//					System.out.println("Comment not deleted");
//					return false;
				
			
//			rs.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();	
		}
		return "__";
	}
	
	public String editComment(int userID, int commentID, String updatedComment) {
		
		try( Connection conn = DriverManager.getConnection(DB_URL,USER,Pass);
				Statement stmt = conn.createStatement();) {
			
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");
			String sql = "UPDATE comment SET text = '"+updatedComment+"' WHERE comment_ID = '"+commentID+"' && user_ID = '"+userID+"'";
			stmt.executeUpdate(sql);
			System.out.println("Comment successfully updated");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		return "__";
	}
	
//	String sql2 = "SELECT account_id FROM comment WHERE account_id = '"+userID+"'";
//	ResultSet rs2 = stmt.executeQuery(sql2);
//	if (rs2.next()) {
//		rs2.first(); 
//	}
//	accountID = rs2.getInt("account_id");
//	rs2.close(); // Deleted accountID from the table.
}