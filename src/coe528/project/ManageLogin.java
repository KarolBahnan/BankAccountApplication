package coe528.project;

import java.util.Observable;
import coe528.project.PresentCustomer;
import coe528.project.PresentManager;

/**
 * ManageLogin, an observable class that notifies
 PresentCustomer and PresentManager that a new user has logged in
 
 This is mutable class since it is possible to be
 null, customer, or manager.
 */
public class ManageLogin extends Observable {

	/** The user logging in */
	private User loggedIn;
	
	/**
	 * @modifies this loggedIn property
	 *
	 * @param user the given user
	 */
	public void logIn(User user) {
		loggedIn = user;
		if (user != null) {
			setChanged();
			notifyObservers();
		}
		
	}
	
	/**
	 * Logs out the current user
	 */
	public void logOut() {
		loggedIn = null;
	}
	
	/**
	 * Gets the logged in user.
	 *
	 * @return loggedIn
	 */
	public User getLoggedIn() {
		return loggedIn;
	}
	
}
