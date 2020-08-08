package coe528.project;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import coe528.project.Customer;
import coe528.project.Model;
import coe528.project.Roles;
import coe528.project.User;
import coe528.project.View;

/**
 * The Class PresentCustomer controls the window of the customer.
 * The customer currently logged in is observed and the
 * balance/level of the customer is kept track of.
 */
public class PresentCustomer implements Observer {

	// Reference to model.
	private Model model;

	// Login scene.
	private Scene loginScene;

	// Label with customer username.
	@FXML
	private Label customer;

	// Label with actual balance.
	@FXML
	private Label balance;

	// Label with actual level.
	@FXML
	private Label level;

	// Box with all possible amounts of money.
	@FXML
	private ComboBox<Double> money;

	// Parent pane.
	@FXML
	private AnchorPane customerParent;

	// Customer currently logged in.
	private Customer loggedIn;

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(Model model) {
		this.model = model;		
		init();
	}

	/**
	 * Initializes the graphical elements.
	 */
	private void init() {
		money.setValue(100.0); // Set initial value to be selected.
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
	 * Manages logout option.
	 */
	@FXML
	void handleLogout() {
		loggedIn = null;
		model.logout();
		Stage stage = (Stage) customerParent.getScene().getWindow();
		stage.setScene(loginScene);
	}

	/**
	 * Handles deposit option.
	 */
	@FXML
	void handleDeposit() {
		double value = money.getSelectionModel().getSelectedItem();
		if (!model.deposit(value)) {
			View.showMessage("Deposit unable to be processed.");
		}
	}

	/**
	 * Handles withdraw option.
	 */
	@FXML
	void handleWithdraw() {
		double value = money.getSelectionModel().getSelectedItem();
		if (!model.withdraw(value)) {
			View.showMessage("Not enough funds available.");
		}
	}

	/**
	 * Handles online purchase option.
	 */
	@FXML
	void handleOnlinePurchase() {
		double value = money.getSelectionModel().getSelectedItem();
		if (!model.doOnlinePurchase(value)) {
			View.showMessage("Not enough funds available for purchase.");
		}
	}

	/**
	 * Method originates from the Observer and keeps 
	 * username/balance/level up to date.
	 */
	@Override
	public void update(Observable obs, Object arg1) {
		
		if (loggedIn == null) { // If method is called for the first time.
			User user = model.getLoggedIn();
			if (user.getRoles() == Roles.MANAGER) { // Notification is for manager.
				return;
			}
			Customer cust = (Customer)user;
			loggedIn = cust;
			cust.addObserver(this);
		}
		balance.setText("$" + loggedIn.getBalance()); // Balance is updated.
		level.setText(loggedIn.getState().toString()); // Level is updated.
		customer.setText(loggedIn.getUsername()); // Username is updated.
	}
	
}