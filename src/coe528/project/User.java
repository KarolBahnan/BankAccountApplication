package coe528.project;

import java.util.Observable;

/**
 * User is an abstract class that lists common 
 * properties for users eg(username and password)
 * 
 * Since properties can be changed, the class is mutable.
 * 
 */
public abstract class User extends Observable {

	/** Username. */
	private String username;
	
	/** Password. */
	private String password;

	/**
	 * Creates a new user.
	 *
	 * @param username Username
	 * @param pass Password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Creation of a new user.
	 */
	public User() {		
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public abstract Roles getRoles();

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
