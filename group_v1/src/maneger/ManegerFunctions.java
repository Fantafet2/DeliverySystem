package maneger;

public class ManegerFunctions {
	public void createUserAccount(String username, String password, String role) {
	    // add user to system (Clerk, Driver, Manager)
	    System.out.println("Created account for " + role + ": " + username);
	}

	public void updateUserAccount(String username, String newPassword, String newRole) {
	    // modify user information
	    System.out.println("Updated account for " + username);
	}

	public void deleteUserAccount(String username) {
	    // remove user from system
	    System.out.println("Deleted account for " + username);
	}

	public void viewAllUserAccounts() {
	    // display list of all users
	    System.out.println("Listing all user accounts...");
	}


}
