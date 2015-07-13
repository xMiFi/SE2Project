package project_SE2_EzNote_implement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import project_SE2_EzNote_interfaces.IntListObject;

public abstract class ListObject implements IntListObject {
	
	protected String title;
	protected String text;
	
	
	protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
	protected LocalDateTime currentTime = LocalDateTime.now();
	protected String creationDate = currentTime.format(formatter);
	
	protected Boolean editable = true;

	
	public String tagSetToString(Set<String> set) {
	    String result = "";
	    for (String setListObject: set) {
	        result += setListObject + " ";
	    }
	    if (result.length() > 0){
	    	result = result.substring(0, result.length()-1);
	    }
	    return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	
	public void setCreationDate(String creationDate){
		this.creationDate = creationDate;
	}
	
	public String getCreationDate(){
		if(editable){
			return creationDate;
		}
		else {
			return "DD.MM.YYYY";
		}
	}
	
	public void setEditable(Boolean editable){
		this.editable = editable;
	}
	
	public Boolean getEditable() {
		return editable;
	}

	


	


}
