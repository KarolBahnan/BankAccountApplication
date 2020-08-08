package coe528.project;

import java.util.List;
import java.util.Observer;

/**
 * The Class Persist, a subclass of User
 * and inherits getUsername() and getPassword() properties.
 * 
 * A mutable class since change is possible
 * to change username and password.
 */
public class Persist extends User implements Define {

	/** Reference to Define object to have an access
            to all methods required to keep up to date. */
	private Define persist;

	/**
	 * Creates a new manager.
	 *
	 * @param username the username
	 * @param password the password
	 * @param persist the persist
	 */
	public Persist(String username, String password, Define persist) {
		super(username, password);
		this.persist = persist;
		repOk();
	}

	/**
	 * @effects returns Manager.
	 */
	@Override
	public Roles getRoles() {
		return Roles.MANAGER;
	}

	/**
	 * @requires username != null and !username.isEmpty and password != null and
	 *           !password.isEmpty
	 * @effects calls appropriate method Persist create new user with customer status.
	 */
	@Override
	public void addCustomer(String username, String password, Observer ...obs) throws DefineException {
		repOk();
		persist.addCustomer(username, password, obs);
		repOk();
	}

	/**
	 * @requires username != null
	 * @effects calls appropriate method from Persist to delete certain customer with respective username.
	 */
	@Override
	public void deleteCustomer(String username) throws DefineException {
		repOk();
		persist.deleteCustomer(username);
		repOk();
	}

	/**
	 * @effects returns {@link Customer} object by the given username
	 * @requires username != null
	 */
	@Override
	public Customer searchCustomer(String username) throws DefineException {		
		repOk();
		try {
			return persist.searchCustomer(username);
		} finally {
			repOk();
		}
	}

	/**
	 * @effects returns all registered customers
	 */
	@Override
	public List<Customer> allCustomers() throws DefineException {
		repOk();
		try {
			return persist.allCustomers();
		} finally {
			repOk();
		}
	}

	/**
	 * Checks the rep invariant.
	 * @effects nothing if this satisfies rep invariant, 
	 *          otherwise exception is thrown.
	 */
	private void repOk() {
		if (getUsername() == null && getUsername().isEmpty()) {
			throw new RuntimeException("Username box cannot be empty.");
		}
		if (getPassword() == null && getPassword().isEmpty()) {
			throw new RuntimeException("Password box cannot be empty.");
		}
	}
}
