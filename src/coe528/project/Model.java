package coe528.project;

import java.util.List;
import java.util.Observer;

/**
 * The Class Model which contains all user management options.
 * 
 * This is immutable class, because its properties cannot be changed.
 */
public class Model implements Account {
	
	// The define manager.
	private final Define DefineManager;
	
	// The level manager deals with changes in the level of customer according to State design pattern.
	private final Observer ManageLevel;
	
	// The manager to deal with searching/adding/deleting users.
	private final Persist manager;
	
	/** The login manager. */
	private final ManageLogin ManageLogin;

	/**
	 * Creates a new model.
	 */
	public Model() {
		DefineManager = new DefineManager();
		manager = new Persist("admin", "admin", DefineManager);
		ManageLevel = new ManageLevel();	
		ManageLogin = new ManageLogin();
	}
	
	/**
	 * logs in using the given username and password.
	 * 
	 * @requires username != null and password != null
	 *           and !username.isEmpty and !password.isEmpty
	 *
	 * @param username the specific username
	 * @param password the specific password
	 * @return the user found by these credentials; can be null if not found
	 * @throws DefineException the define exception for issues with the file.
	 *   
	 */
	public User logIn(String username, String password) throws DefineException {
		
		// Begin by checking for manager credentials.
		if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
			ManageLogin.logIn(manager);
			return manager;
		}
		
		Customer cust = manager.searchCustomer(username);
		if (cust != null) { // If user is found in the system.
			if (cust.getPassword().equals(password)) { // Perform a password check.
				ManageLogin.logIn(cust);
				cust.addObserver(ManageLevel);
				cust.addObserver((Observer) DefineManager);
				return cust;
			}
		}
		return null;
	}
	
	/**
	 * logs out by using ManageLogin
	 */
	public void logout() {
		ManageLogin.logOut();
	}
	
	/**
	 * Addition of customer and return list of all customers including added one.
	 *
	 * @requires username != null and password != null
	 *           and !username.isEmpty and !password.isEmpty
	 * @param username the given username
	 * @param password the given password
	 * @return the list of all currently registered customers
         * @throws DefineException the define exception for issues with the file.
	 */
	public List<Customer> addCustomer(String username, String password) throws DefineException {
		manager.addCustomer(username, password, ManageLevel);
		return manager.allCustomers();
	}
	
	/**
	 * Returns all current registered customers.
	 *
	 * @return the list of customers
	 * @throws DefineException the define exception for issues with the file.
	 */
	public List<Customer> allCustomers() throws DefineException {
		return manager.allCustomers();
	}
	
	/**
	 * Deletes customer by the respective username.
	 * 
	 * @requires username != null
	 *
	 * @param username the given username
	 * @throws DefineException the define exception for issues with the file.
	 */
	public void deleteCustomer(String username) throws DefineException {
		manager.deleteCustomer(username);
	}

	/**
	 * Gets the logged in user.
	 *
	 * @return the logged in user
	 */
	public User getLoggedIn() {
		return ManageLogin.getLoggedIn();
	}

	/**
	 * Gets the login manager.
	 *
	 * @return the login manager
	 */
	public ManageLogin getManageLogin() {
		return ManageLogin;
	}
	
	/**
	 * @requires value > 0
	 */
	@Override
	public boolean deposit(double value) {
		Account acc = (Account) ManageLogin.getLoggedIn();
		return acc.deposit(value);
	}
	
	/**
	 * @requires value > 0
	 */
	@Override
	public boolean withdraw(double value) {
		Account acc = (Account) ManageLogin.getLoggedIn();
		return acc.withdraw(value);
	}
	
	/**
	 * @requires value > 0
	 */
	@Override
	public boolean doOnlinePurchase(double value) {
		Account acc = (Account) ManageLogin.getLoggedIn();
		return acc.doOnlinePurchase(value);
	}
	
	@Override
	public double getBalance() {
		Account acc = (Account) ManageLogin.getLoggedIn();
		return acc.getBalance();
	}
}