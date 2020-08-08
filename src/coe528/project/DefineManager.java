package coe528.project;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *  The Class DefineManager that assures all customers are 
 *  defined. This class is responsible for the 
 *  remove/search/retrieve operations
 *
 *  Since it contains no properties, it is an immutable class.
 *  
 */
public class DefineManager implements Define, Observer {

	@Override
	public void addCustomer(String username, String password, Observer ...obs) throws DefineException {

		// Begin by performing a check if username is already used.
		boolean exists = Files.exists(Paths.get(username + ".cst"));

		if (exists) {
			throw new DefineException("Sorry, the username entered already exists.");
		}

		Account account = new Customer(username, password);
		Customer cust = (Customer)account;		

		cust.addObserver(this); // Update based on withdraws/deposits.
		
		// Addition of other observers.
		for (Observer o: obs) {
			cust.addObserver(o);
		}
		
		account.deposit(100); // Deposit of $100 into the account.

		addCustomer(cust);
	}
	
	/**
	 * Addition the customer.
	 *
	 * @param cust the customer being added.
	 * @throws DefineException the exception when there are issues
	 *         with file storage.
	 */
	public void addCustomer(Customer cust) throws DefineException {
		try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(new File(cust.getUsername() + ".cst")))) {
			encoder.writeObject(cust);
		} catch (FileNotFoundException e) { // catch file storage problems.

			throw new DefineException("Customer with username <" + cust.getUsername() +
					"> and password <" + cust.getPassword() + "> cannot be stored: " + e.getMessage());
		} 
	}
	

	@Override
	public void deleteCustomer(String username) throws DefineException {
		File cst = new File(username + ".cst");

		if (!cst.delete()) {
			throw new DefineException("Customer with username <" + username +
					"> does not exist");
		}
	}

	/**
	 * This method comes from the Observer interface and
	 * updates file based on change in balance.
	 */
	@Override
	public void update(Observable obs, Object obj) {
		Customer customer = (Customer)obs;
		try {
			if (searchCustomer(customer.getUsername()) != null) { // Assure it is not a new customer being added.
				
				deleteCustomer(customer.getUsername()); // Delete current customer.
				addCustomer(customer); // Add the updated customer.
			}			
		} catch (DefineException e) {
			System.out.println(e);
		}		
	}

	@Override
	public Customer searchCustomer(String username) throws DefineException {
		boolean exists = Files.exists(Paths.get(username + ".cst"));

		if (exists) {
			try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(new File(username + ".cst")))) {
				return (Customer) decoder.readObject();
			} catch (FileNotFoundException e) {
				throw new DefineException("File " + username + ".cst cannot be obtained: " + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public List<Customer> allCustomers() throws DefineException {
		List<Customer> res = new ArrayList<>();

		try {
			Files.walk(Paths.get(".")).filter(p -> p.toString().endsWith(".cst")).forEach(p -> {
				try {
					String filename = p.getFileName().toString();
					res.add(searchCustomer(filename.substring(0, filename.indexOf("."))));
				} catch (DefineException e) {
					System.out.println(e);
				}
			});
		} catch (IOException e) {
			System.out.println(e);
		}

		return res;
	}

}
