package coe528.project;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import coe528.project.Customer;
import coe528.project.Model;
import coe528.project.DefineException;
import coe528.project.Roles;
import coe528.project.User;
import coe528.project.View;

/**
 * The Class PresentLogin that controls the window for login
 */
public class PresentLogin {

	// Model reference.
	private Model model;

	// Manager scene.
	private Scene managerScene;

	// Customer scene.
	private Scene customerScene;

	// Parent pane.
	@FXML
	private AnchorPane loginParent;

	// Text box to enter login.
	@FXML
	private TextField login;

	// Text box to enter password.
	@FXML
	private PasswordField pswrd;

	// Label that displaus error message if login or password are not valid. 
	@FXML
	private Label invLogPswrd;

	/**
	 * Manages login option.
	 */
	@FXML
	void handleLogin() {
		try {
			User user = model.logIn(login.getText().trim(), pswrd.getText().trim());
			if (user != null) {
				Stage stage = (Stage) loginParent.getScene().getWindow();
				pswrd.setText(""); // Clear password field.
				if (user.getRoles() == Roles.MANAGER) { // If a manager is logging in.
					stage.setScene(managerScene);
				} else if (user.getRoles() == Roles.CUSTOMER) { // If a customer is logging in.
					stage.setScene(customerScene);
				}
				
			} else {
				invLogPswrd.setVisible(true); // Display error message if invalid login or password.
			}
		} catch (DefineException e) { // If some problems within the files.
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
		init();
	}

	/**
	 * Initializes the graphical components for the boxes.
	 */
	private void init() {
		login.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, 
				Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						login.setText(""); // Clear username box once it has been clicked.
						invLogPswrd.setVisible(false);
					}
				});
		pswrd.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, 
				Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						pswrd.setText(""); // Clear password box once it has been clicked.
						invLogPswrd.setVisible(false);
					}
				});
	}

	/**
	 * Sets the manager scene.
	 *
	 * @param managerScene the new manager scene
	 */
	public void setManagerScene(Scene managerScene) {
		this.managerScene = managerScene;
	}

	/**
	 * Sets the customer scene.
	 *
	 * @param customerScene the new customer scene
	 */
	public void setCustomerScene(Scene customerScene) {
		this.customerScene = customerScene;
	}
	
}