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
import lib.man.model.Book;
import lib.man.model.LibraryMember;
import lib.man.view.BookEditDialogController;
import lib.man.view.BookOverviewController;
import lib.man.view.LibraryMemberEditDialogController;
import lib.man.view.LibraryMemberOverviewController;
import lib.man.view.LoginDialogController;
import lib.man.view.RootLayoutController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private int systemRoles = 0;
    private RootLayoutController rootController = null;

    // System Roles
    public void setSystemRoles(int systemRoles) {
    	this.systemRoles = systemRoles;
    }
    public int getSystemRoles() {
    	return systemRoles;
    }
    // Root Controller
    public RootLayoutController getRootController() {
    	return rootController;
    }
    
// Begin ---- Using for LibraryMember    
    private ObservableList<LibraryMember> listLibrabryMember = FXCollections.observableArrayList();
    private ObservableList<Book> listBook = FXCollections.observableArrayList();
    
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

    	// Book Add some sample data
    	for (int i = 1; i <= 10; i++)
    	{
    		String strIsbn 	= String.format("%010d", i);
    		String strAuthor = String.format("Author %d", i);
    		String strTitle = String.format("Title %d", i);
    		int 	maxCheckout = i + 5;    		
    		listBook.add(new Book(strIsbn, strAuthor, strTitle, maxCheckout));
    	}
    }
    
    // Returns the data as an observable list of LibraryMembers. 
	public ObservableList<LibraryMember> getLirabryMembers() {
		return listLibrabryMember;
	}	
	// Returns the data as an observable list of LibraryMembers. 
	public ObservableList<Book> getBooks() {
		return listBook;
	}
// End ---- Using for LibraryMember    	
	

	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Library Management Application");

        initRootLayout();
        // Show Login Dlg
        if (showLoginDialog() == true)
    	{
        	// Load Roles
        	rootController.LoadSystemRoles(systemRoles);
    	}
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

            // Give the controller access to the main app.
            rootController = loader.getController();
            rootController.setMainApp(this);

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
    
    // Shows the LibraryMember overview inside the root layout.
    public void showBookOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookOverview.fxml"));
            AnchorPane bookOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(bookOverview);

            // Give the controller access to the main app.
            BookOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Shows the LibraryMember overview inside the root layout.
    public void showCheckoutBook() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CheckoutBook.fxml"));
            AnchorPane checkoutBook = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(checkoutBook);

            // Give the controller access to the main app.
            // CheckoutBookController checkoutBook = loader.getController();
            // checkoutBook.setMainApp(this);

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
    
    public boolean showBookEditDialog(Book book) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the book into the controller.
            BookEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBook(book);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Show Login Dialog
    public boolean showLoginDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoginDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the User into the controller.
            LoginDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
        
        
}
