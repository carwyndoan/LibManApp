package lib.man;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lib.man.model.LibraryMember;
import lib.man.view.LibraryMemberEditDialogController;
import lib.man.view.LibraryMemberOverviewController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
// Begin ---- Using for LibraryMember    
    private ObservableList<LibraryMember> listLibrabryMember = FXCollections.observableArrayList();
    public MainApp() {
    	// Add some sample data
    	for (int i = 1; i <= 10; i++)
    	{
    		String strID 	= String.format("%05d", i);
    		String strFName = String.format("FName %d", i);
    		String strLName = String.format("Last Name %d", i);
    		String strPhone = String.format("%010d", i);    		
    		listLibrabryMember.add(new LibraryMember(strID, strFName, strLName, strPhone));
    	}
    }
    // Returns the data as an observable list of LibraryMembers. 
	public ObservableList<LibraryMember> getLirabryMembers() {
		return listLibrabryMember;
	}
// End ---- Using for LibraryMember    	
	
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Library Management Application");

        initRootLayout();
        showLibraryMemberOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Shows the LibraryMember overview inside the root layout.
    public void showLibraryMemberOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LibraryMemberOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            LibraryMemberOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Opens a dialog to edit details for the specified LibraryMember. If the user
     * clicks OK, the changes are saved into the provided LibraryMember object and true
     * is returned.
     * 
     * @param libraryMember the LibraryMember object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showLibraryMemberEditDialog(LibraryMember libraryMember) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LibraryMemberEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Library Member");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            LibraryMemberEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLibraryMember(libraryMember);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }    
}
