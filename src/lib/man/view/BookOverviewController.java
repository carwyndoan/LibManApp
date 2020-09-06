package lib.man.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lib.man.MainApp;
import lib.man.model.Book;

public class BookOverviewController {
    @FXML
    private TableView<Book> tblBook;
    @FXML
    private TableColumn<Book, String> colIsbn;
    @FXML
    private TableColumn<Book, String> colAuthor;
    @FXML
    private TableColumn<Book, String> colTitle;
    @FXML
    private TableColumn<Book, Integer> colMaxCheckout;

    @FXML
    private Label labelIsbn;
    @FXML
    private Label labelAuthor;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelMaxCheckout;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public BookOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
	colIsbn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
	colAuthor.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
	colTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
	colMaxCheckout.setCellValueFactory(cellData -> cellData.getValue().maxCheckoutProperty().asObject());
	// Clear person details.
    showBookDetails(null);

    // Listen for selection changes and show the person details when changed.
    tblBook.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showBookDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        tblBook.setItems(mainApp.getBooks());
    }
    
    /**
     * Fills all text fields to show details about the Book.
     * If the specified Book is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showBookDetails(Book book) {
        if (book != null) {
            // Fill the labels with info from the person object.
            labelIsbn.setText(book.getIsbn());
            labelAuthor.setText(book.getAuthor());
            labelTitle.setText(book.getTitle());
            labelMaxCheckout.setText(Integer.toString(book.getMaxCheckout()));

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
        	labelIsbn.setText("");
        	labelAuthor.setText("");
        	labelTitle.setText("");
        	labelMaxCheckout.setText("");
        }
    } 
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteBook() {
            int selectedIndex = tblBook.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
            	tblBook.getItems().remove(selectedIndex);
            } else {
                // Nothing selected.
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Book Selected");
                alert.setContentText("Please select a Book in the table.");

                alert.showAndWait();
            }
    } 
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new Book.
     */
    @FXML
    private void handleNewBook() {
    	Book tempBook = new Book();
        boolean okClicked = mainApp.showBookEditDialog(tempBook);
        if (okClicked) {
            mainApp.getBooks().add(tempBook);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected Book.
     */
    @FXML
    private void handleEditBook() {
    	Book selectedBook = tblBook.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean okClicked = mainApp.showBookEditDialog(selectedBook);
            if (okClicked) {
                showBookDetails(selectedBook);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a Book in the table.");

            alert.showAndWait();
        }
    }    
}