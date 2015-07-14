package project_SE2_EzNote_gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import project_SE2_EzNote_implement.ClockThread;
import project_SE2_EzNote_implement.NoteHandler;
import project_SE2_EzNote_interfaces.IntListObject;
import project_SE2_EzNote_exceptions.SelectionException;
import project_SE2_EzNote_plugins.Note;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class GUIController {
	@FXML private ListView<IntListObject> noteListView;
	@FXML private TextField noteTitleField;
	@FXML private TextArea noteTextArea;
	@FXML private TextField searchField;
	@FXML private Button addNoteButton;
	@FXML private Button searchButton;
	@FXML private Button removeNoteButton;
	@FXML private Label dateLabel;
	@FXML private Label clock;


	
    private static Logger log = LogManager.getLogger(Driver.class);
    
   

      
    
	@FXML
	private void initialize() {
		
		ClockThread thread1 = new ClockThread(clock);
		thread1.setDaemon(true);
		thread1.start();
		
		
    	noteListView.setItems(NoteHandler.getObservableNoteList());
    	NoteHandler.getObservableFilteredList().setAll(NoteHandler.getObservableNoteList());
    	
		noteListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IntListObject>() {
			@Override
			public void changed(ObservableValue<? extends IntListObject> observable,
					IntListObject LOold, IntListObject LOnew) {
							if (LOnew != null){
							noteTitleField.setText(LOnew.getTitle());
							
							//An dieser Stelle ist wieder ein Thread, welcher per Runnable einen Focus erfragt
							Platform.runLater(new Runnable() {
							     @Override
							     public void run() {
							    	 if(noteTitleField.getText().contains("<enter name>")){
							    		 noteTitleField.requestFocus();
							    	 }
							     }
							});
							
							noteTextArea.setText(LOnew.getText());
							
							dateLabel.setText(LOnew.getCreationDate());
						}
			}
		});
		
		noteTitleField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    			try{
						    IntListObject tmpNote = noteListView.getSelectionModel().getSelectedItem();
							if (tmpNote == null){
							     throw new SelectionException();
							}
						    if (tmpNote.getEditable()){
							    tmpNote.setTitle(newValue);
								log.trace("Title Attribute of Note is changed");
							 
							    Note dummyNote = new Note();
							    NoteHandler.getObservableFilteredList().add(dummyNote);
							    NoteHandler.getObservableFilteredList().remove(dummyNote);
							    noteListView.setItems(NoteHandler.getObservableFilteredList());
							}
							else {
								noteTitleField.setText(tmpNote.getTitle());
							}
		    			}
		    			catch (SelectionException ne) {
		    				noteTitleField.setText("Selection Error [No Note Selected]");
							Platform.runLater(new Runnable() {
							     @Override
							     public void run() {
							    	 addNoteButton.requestFocus();
							         log.error("EXCEPTION: SelectionException on editing unselected Note " + ne.toString(),ne);
							     }
							});
		    			}
		    		}
		});
		
		
		noteTextArea.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue){
		    			try{
						    IntListObject tmpNote = noteListView.getSelectionModel().getSelectedItem();
							if (tmpNote == null){
							     throw new SelectionException();
							}
						    if (tmpNote.getEditable()){
								tmpNote.setText(newValue);
								log.trace("Text Attribute of Note is changed");
							}
							else {
								noteTextArea.setText(tmpNote.getText());
							}
		    			}
		    			catch (SelectionException ne) {
		    				noteTextArea.setText("Selection Error [No Note Selected]");
							Platform.runLater(new Runnable() {
							     @Override
							     public void run() {
							    	 addNoteButton.requestFocus();
							    	 log.error("EXCEPTION: SelectionException on editing unselected Note " + ne.toString(), ne);
							     }
							});
		    			}
		    		}
		});
		
		
	
	}
	
	
    @FXML protected void handleaddNoteButton() {
		log.info("'addNoteButton' pressed!");
    	NoteHandler.addNote();
    	noteListView.setItems(NoteHandler.getObservableFilteredList());
		log.debug("List-View updated with filteredList!");
    }
    
    @FXML protected void handleRemoveNoteButton() {
		log.info("'RemoveNoteButton' pressed!");
    	if (noteListView.getSelectionModel().getSelectedItem() != null){
			IntListObject tmpNote = noteListView.getSelectionModel().getSelectedItem();
			if (tmpNote.getEditable()){
				NoteHandler.removeNote(tmpNote);
				noteListView.setItems(NoteHandler.getObservableFilteredList());
				log.debug("List-View updated with filteredList!");
			}
    	}
    }
    
    @FXML protected void handleSearchButton() {
		log.info("'handleSearchButton' pressed!");
    	NoteHandler.searchNote(searchField.getText());
    	noteListView.setItems(NoteHandler.getObservableFilteredList());
		log.debug("List-View updated with filteredList!");
    	searchField.clear();
    }
    
    
    
    
    @FXML protected void handleEnterOnSearch(KeyEvent key) {
    	if (key.getCode().equals(KeyCode.ENTER)){
    		handleSearchButton();
    	}
    }
}
