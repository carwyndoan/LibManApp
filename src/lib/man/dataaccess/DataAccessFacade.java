package lib.man.dataaccess;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import lib.man.model.Book;
import lib.man.model.CheckoutRecord;
import lib.man.model.CheckoutRecordEntry;
import lib.man.model.LibraryMember;
import lib.man.model.User;
import lib.man.utils.FileUtils;

public class DataAccessFacade implements DataAccess {

	private static HashMap<String, Book> books;
	private static HashMap<Long, LibraryMember> libraryMembers;
	private static HashMap<String, CheckoutRecord> checkoutRecords;
	private static HashMap<String, User> users;
	

	@Override
	public void addNewBook(Book book) {
		@SuppressWarnings("unchecked")
		HashMap<String, Book> bookMap = (HashMap<String, Book>) readDataFromFile(books, Book.STORAGE_TYPE);
		bookMap.put(book.getIsbn(), book);
		FileUtils.writeObject(bookMap, Book.STORAGE_TYPE);
		books = bookMap;
	}

	@Override
	public void updateBook(Book updatedBook) {
		addNewBook(updatedBook);
		
	}

	@Override
	public Book findBookById(String isbn) {
		HashMap<String, Book> bookMap = (HashMap<String, Book>) readDataFromFile(books, Book.STORAGE_TYPE);
		return bookMap.get(isbn);
	}

	@Override
	public Collection<Book> getAllBooks() {
		HashMap<String, Book> bookMap = (HashMap<String, Book>) readDataFromFile(books, Book.STORAGE_TYPE);
		return bookMap.values();
	}

	@Override
	public long addNewLibraryMember(LibraryMember libraryMember) {
		// TODO Auto-generated method stub
		Random rand = new Random(1);
		return rand.nextLong();
	}

	@Override
	public boolean updateLibraryMember(LibraryMember libraryMember) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public LibraryMember findLibraryMemberById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<LibraryMember> findAllMembers() {
		// TODO Auto-generated method stub
		HashMap<Long, LibraryMember> libMap = (HashMap<Long, LibraryMember>) readDataFromFile(libraryMembers, LibraryMember.STORAGE_TYPE);
		return libMap.values();
	}

	@Override
	public void checkout(CheckoutRecord checkoutRecord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CheckoutRecord findCheckOutRecordByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private Object readDataFromFile(Object currentData, String type) {
		//return current book data in session
		if (currentData != null) {
			return currentData;
		}
		
		//read book data from file in the first time
		try {
			
			Object result = FileUtils.readObject(type);
			currentData = result != null ? result : new HashMap();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return currentData;
		
	}
	
	

}
