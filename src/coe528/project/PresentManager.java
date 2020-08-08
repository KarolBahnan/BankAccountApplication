package coe528.project;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import coe528.project.Customer;
import coe528.project.Model;
import coe528.project.DefineException;
import coe528.project.View;

/**
 * The Class PresentManager controls the manager window
 * It observes all registered users and allows manager
 * to modify it.
 */
public class PresentManager implements Observer {
	
	// The reference to model.
	private Model model;
	
	// The login scene.
	private Scene loginScene;
	
	// The manager parent to obtain stage.
	@FXML
	private AnchorPane managerParent;
	
	// Table with all customers. 
	@FXML
	private TableView<Customer> tblCustomers;
	
	// Text box to enter username.
	@FXML
	private TextField username;
	
	// Text box to enter password.
	@FXML
	private TextField password;
	
	// Viewable list of all customers.
	private final ObservableList<Customer> customers = FXCollections.observableArrayList();

	/**
	 * Manages the logout button.
	 */
	@FXML
	void handleLogout() {
		model.logout();
		Stage stage = (Stage) managerParent.getScene().getWindow();
		stage.setScene(loginScene); // Go to the login screen.
	}
	
	/**
	 * Manages the add customer button.
	 */
	@FXML
	void handleAddCustomer() {
		String login = username.getText().trim();
		String pswrd = password.getText().trim();
		if (login.isEmpty()) {
			username.setStyle("-fx-border-color: red;"); // If login box is empty.
			return;
		}
		if (pswrd.isEmpty()) {
			password.setStyle("-fx-border-color: red;"); // If password box is empty.
			return;
		}
		
		// Return to initial color of borders.
		username.setStyle("-fx-border-color: lightgray;");
		password.setStyle("-fx-border-color: lightgray;");
		username.setText("");
		password.setText("");
		try {
			List<Customer> custs = model.addCustomer(login, pswrd);
			customers.setAll(custs);
		} catch (DefineException e) {
			View.showMessage(e.getMessage());
		}
	}
	
	/**
	 * Manages the delete customer button.
	 */
	@FXML
	void handleDeleteCustomer() {
		Customer cust = tblCustomers.getSelectionModel().getSelectedItem();
		if (cust == null) { // In case of no selected customer.
			return; 
		}
		try {
			model.deleteCustomer(cust.getUsername());
			customers.setAll(model.allCustomers());
		} catch (DefineException e) {
			View.showMessage(e.getMessage());
		}
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(Model model) {
		this.model = model;
		try {
			customers.setAll(model.allCustomers());
		} catch (DefineException e) {
			System.out.println(e);
		}
	}

	/**
	 * Sets the login scene.
	 *
	 * @param loginScene the new login scene
	 */
	public void setLoginScene(Scene loginScene) {
		this.loginScene = loginScene;
	}

	/**
	 * Gets the customers.
	 *
	 * @return the customers
	 */
	public ObservableList<Customer> getCustomers() {
		return customers;
	}

	/**
	 * Method to update table of customers following addition/deletion/ and changing of customers.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			customers.setAll(model.allCustomers());
		} catch (DefineException e) {
			View.showMessage(e.getMessage());
		}
	
	}

}
