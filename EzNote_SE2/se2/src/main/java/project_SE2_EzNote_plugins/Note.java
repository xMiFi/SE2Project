package project_SE2_EzNote_plugins;

import java.util.Set;

import project_SE2_EzNote_implement.ListObject;

import project_SE2_EzNote_interfaces.IntNote;

public class Note extends ListObject implements IntNote {
	
	public String tagSetToString(Set<String> set) {
	    String result = "";
	    for (String setNote: set) {
	        result += setNote + " ";
	    }
	    if (result.length() > 0){
	    	result = result.substring(0, result.length()-1);
	    }
	    return result;
	}

	public Note() {
		this.title 	= "<enter name>";
		this.text 	= null;
	}

	

}
