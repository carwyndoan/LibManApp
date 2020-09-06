package lib.man.controller;

import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lib.man.dataaccess.DataAccessFacade;
import lib.man.model.LibraryMember;

public class SystemController {
	public ObservableList<LibraryMember> getLibraryMembers(){
		// Add some sample data
		ObservableList<LibraryMember> listLibrabryMember = FXCollections.observableArrayList();
		DataAccessFacade data = new DataAccessFacade();
		List<LibraryMember> lst = data.findAllMembers();
		lst.forEach(member -> {
			listLibrabryMember.add(member);
		});
    	return listLibrabryMember;
	}
	
	public long addNewLibraryMember(LibraryMember libraryMember) {
		DataAccessFacade data = new DataAccessFacade();
		data.addNewLibraryMember(libraryMember);
		// TODO Return actual id
		Random rand = new Random(1);
		return rand.nextLong();
	}
	
	public boolean updateLibraryMember(LibraryMember libraryMember) {
		DataAccessFacade data = new DataAccessFacade();
		data.updateLibraryMember(libraryMember);
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean deleteLibraryMember(LibraryMember libmem) {
		return true;
	}
}
