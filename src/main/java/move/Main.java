package move;

public class Main {
	public static void main(String[] args) {
		
		//////////////// register - login /////////////////////////
//		Login l = new Login("longjack@gmail.com","hugs");
//		System.out.println(l.loginAttempt("longjck@gmail.com","hugs"));
//		Register r = new Register("jake@gmail.com","password1", "password1", "Jake");
//	    System.out.println(r.registerAttempt("jake@gmail.com","password1", "password1", "Jake"));
	
		
/////////////////////////////////Guest - admin/////////////////////////		
		
	//	GuestUser gu = new GuestUser("jake@gmail.com",9);
//	    System.out.println(gu.adminToGuestConversion(false,"jake@gmail.com"));
	    //System.out.println(gu.guestToAdminConversion(true, "jake@gmail.com"));
	
/////////////////////////////// Comments /////////////////////////
	    Comment c = new Comment(5,"wowaweewa2");
	 //   AdminUser au = new AdminUser;
	  //  System.out.println(c.leaveComment(1,"wow2"));
	    User AdminUser = new AdminUser("johnboy@gmail.com",5, 1);
	    System.out.println(c.editComment(1, 4, "wow3"));
	//	System.out.println(c.adminDeleteComment(1, 4, AdminUser));
	   // System.out.println(c.adminDeleteComment(5,4, au));
	}
}
 