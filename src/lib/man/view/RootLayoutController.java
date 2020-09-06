package lib.man.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import lib.man.MainApp;

public class RootLayoutController {
    // Reference to the main application
    private MainApp mainApp;
    // Is called by the main application to give a reference back to itself.
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
 
    @FXML
    private Menu mnuLibrarian;
    @FXML
    private Menu mnuAdmin;

    // Handle File Login.
    @FXML
    private void handleFileLogin() {
    	if (mainApp.showLoginDialog() == true)	
    	{
    		// Load SystemRoles
    		LoadSystemRoles(mainApp.getSystemRoles());
    	}
    }  
    // Handle File Exit.
    @FXML
    private void handleFileExit() {
    	System.exit(0);
    }  
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Library Management Application");
    	alert.setHeaderText("About");
    	alert.setContentText("Author: ...!");

    	alert.showAndWait();
    }    
    // Handle Lib Check out Book.
    @FXML
    private void handleLibCheckoutBook() {
    	mainApp.showCheckoutBook();
    }
    // Handle Member Man.
    @FXML
    private void handleAdminMemberManagement() {
    	mainApp.showLibraryMemberOverview();
    }
    // Handle BookMan.
    @FXML
    private void handleAdminBookManagement() {
    	mainApp.showBookOverview();
    }

    // Load System Roles
	public void LoadSystemRoles(int nRoles) {
		switch (nRoles) {
		case 1:
			mnuLibrarian.setDisable(false);
			break;
		case 2:
			mnuAdmin.setDisable(false);
			break;
		case 3:
			mnuLibrarian.setDisable(false);
			mnuAdmin.setDisable(false);
			break;
		}
	}
    
}
