package coe528.project;

import java.util.List;
import java.util.Observer;

/**
 * Define, an interface that
 * explains all methods relating
 * to the customers.
 */
public interface Define {

	/**
	 * Addition of customer
         * 
	 * @requires username != null and !username.isEmpty and password != null
	 *           and !password.isEmpty
	 * @param username the username
	 * @param password the password
	 * @param obs the possible Observable objects which can be added
	 *        to the created Customer (e.g., {@link LevelManage})
	 * @throws DefineException when issues with files occur
	 *       
	 */
	public void addCustomer(String username, String password, Observer ...obs) throws DefineException;
	
	/**
	 * Deletion of customer based on username
	 * 
	 * @requires username != null and !username.isEmpty
	 *
	 * @param username the given username username
	 * @throws DefineException the persistence exception when there are
	 *         problem with files
	 */
	public void deleteCustomer(String username) throws DefineException;
	
	/**
	 * Searches for customer based on username.
	 * 
	 * @requires username != null and !username.isEmpty
	 *
	 * @param username the given username
	 * @return the customer
	 * @throws DefineException when issues with files occur
	 *   
	 */
	public Customer searchCustomer(String username) throws DefineException;
	
	/**
	 * Returns all customers.
	 * 
	 * @requires username != null and !username.isEmpty
	 *
	 * @return the list of all customers
	 * @throws DefineException when issues with files occur
	 *    
	 */
	public List<Customer> allCustomers() throws DefineException;
}
