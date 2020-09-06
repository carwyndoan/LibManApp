package lib.man.view;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lib.man.MainApp;


public class LoginDialogController {
    // Reference to the main application
    private MainApp mainApp;
    // Is called by the main application to give a reference back to itself.
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;

    private Stage dialogStage;
    private boolean okClicked = false;

    private String userName;
    private String password;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

	// Returns true if the user clicked OK, false otherwise.
    public boolean isOkClicked() {
        return okClicked;
    }

	// Called when the user clicks ok.
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	userName = txtUserName.getText().trim();
            password = txtPassword.getText();
            // Update return value
            if (userName.compareToIgnoreCase("librarian") == 0)
            	mainApp.setSystemRoles(1); 
            else if (userName.compareToIgnoreCase("admin") == 0)
            	mainApp.setSystemRoles(2); 
            else if (  userName.compareToIgnoreCase("adminlib") == 0 
            		|| userName.compareToIgnoreCase("libadmin") == 0)
            	mainApp.setSystemRoles(3); 
            	
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (txtUserName.getText() == null || txtUserName.getText().length() == 0) {
            errorMessage += "Invalid User Name!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}