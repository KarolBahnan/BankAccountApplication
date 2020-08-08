package coe528.project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import coe528.project.Model;
import coe528.project.PresentCustomer;
import coe528.project.PresentLogin;
import coe528.project.PresentManager;

/**
 * Class View is the main class that launches
 * the JavaFX application.
 */
public class View extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Model model = new Model();
		
		FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));		
		Parent loginParent = loginLoader.load();
		
		FXMLLoader managerLoader = new FXMLLoader(getClass().getResource("manager.fxml"));		
		Parent managerParent = managerLoader.load();
		
		FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("customer.fxml"));		
		Parent customerParent = customerLoader.load();

		PresentLogin loginPresenter = loginLoader.getController();
		loginPresenter.setModel(model);
		
		PresentManager managerPresenter = managerLoader.getController();
		managerPresenter.setModel(model);
		
		PresentCustomer customerPresenter = customerLoader.getController();
		customerPresenter.setModel(model);
		
		model.getManageLogin().addObserver(customerPresenter); // Track logging customers to set their username, level, and balance.
                
		model.getManageLogin().addObserver(managerPresenter); // Assures customer table is updated.
		
		Scene loginScene = new Scene(loginParent);
		managerPresenter.setLoginScene(loginScene);
		customerPresenter.setLoginScene(loginScene);
		
		Scene managerScene = new Scene(managerParent);
		
		Scene customerScene = new Scene(customerParent);
		
		loginPresenter.setManagerScene(managerScene);
		loginPresenter.setCustomerScene(customerScene);
		
		stage.setScene(loginScene);
		
		stage.show();
	}
        
        
	/**
	 * Display error message.
	 *
	 * @param msg the specific message to be displayed.
	 */
	public static void showMessage(String msg) {
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Failed");
			alert.setContentText(msg);

			alert.showAndWait();
		});
	}

}
