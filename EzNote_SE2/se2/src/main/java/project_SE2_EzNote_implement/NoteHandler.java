package project_SE2_EzNote_implement;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project_SE2_EzNote_interfaces.IntListObject;
import project_SE2_EzNote_gui.Driver;
import project_SE2_EzNote_plugins.Note;




public class NoteHandler {
	private static Logger log = LogManager.getLogger(Driver.class);
	
	@FXML private static ObservableList<IntListObject> noteList = FXCollections.observableArrayList();
    @FXML private static ObservableList<IntListObject> filteredList = FXCollections.observableArrayList();
    
    public static void addNote() {
    	noteList.add(new Note());
    	log.debug("Adding New Note");
    	filteredList.setAll(noteList);
    	log.debug("filteredList has been updated");
    	
	}
    public static void removeNote(IntListObject x) {
		noteList.remove(x);
    	log.debug("Note removed!");
		filteredList.setAll(noteList);
    	log.debug("noteList Overview updated");
		
	}
    public static void searchNote(String searchedWord) {
    	filteredList.clear();
        	if (searchedWord.isEmpty()){
            	filteredList.setAll(noteList);
            	log.debug("filteredList updated [No searchedWord]");
            }
        	else{
        		noteList
        			.stream()
        			.filter(x -> x.getTitle().toLowerCase().contains(searchedWord.toLowerCase()))
        			.forEach(lo -> filteredList.add(lo));
        			log.trace("Added searched Note to observableFilteredList");
        	}
        }
    public static ObservableList<IntListObject> getObservableNoteList() {
		return noteList;
	}

	public static void setObservableNoteList(ObservableList<IntListObject> noteList) {
		NoteHandler.noteList = noteList;
	}
	
	public static ObservableList<IntListObject> getObservableFilteredList() {
		return filteredList;
	}

	public static void setObservableFilteredList(ObservableList<IntListObject> filteredList) {
		NoteHandler.filteredList = filteredList;
		}
	}

