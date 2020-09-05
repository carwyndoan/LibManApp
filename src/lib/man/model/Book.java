package lib.man.model;

import javafx.beans.property.*;

public class Book {
	private StringProperty isbn;
	private StringProperty author;
	private StringProperty title;
	private IntegerProperty maxCheckout;

	public Book() {
		super();
		this.isbn 			= new SimpleStringProperty("");
		this.author			= new SimpleStringProperty("");
		this.title 			= new SimpleStringProperty("");
		this.maxCheckout	= new SimpleIntegerProperty(0);
	}
	
	public Book(String isbn, String author, String title, int maxCheckout) {
		super();
		this.isbn 			= new SimpleStringProperty(isbn);
		this.author 		= new SimpleStringProperty(author);
		this.title 			= new SimpleStringProperty(title);
		this.maxCheckout	= new SimpleIntegerProperty(maxCheckout);
	}
	public String getIsbn() {
		return isbn.get();
	}
	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}
	public String  getAuthor() {
		return author.get();
	}
	public void setAuthor(String author) {
		this.author.set(author);
	}
	public String getTitle() {
		return title.get();
	}
	public void setTitle(String title) {
		this.title.set(title);
	}
	public int getMaxCheckout() {
		return maxCheckout.get();
	}
	public void setMaxCheckout(int maxCheckout) {
		this.maxCheckout.set(maxCheckout);
	}
	
	public StringProperty isbnProperty() {
		return isbn;
	}
	public StringProperty authorProperty() {
		return author;
	}
	public StringProperty titleProperty() {
		return title;
	}
	public IntegerProperty maxCheckoutProperty() {
		return maxCheckout;
	}
	
}
