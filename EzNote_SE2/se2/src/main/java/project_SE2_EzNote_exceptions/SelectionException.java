package project_SE2_EzNote_exceptions;

public class SelectionException extends NullPointerException {
	
	private static final long serialVersionUID = -7713858782141901155L;

	public SelectionException(){
		super();
	}
	  
	public SelectionException(String message){
		super(message); 
	}
	  
	public SelectionException(Throwable cause){ 
		super();
	}
}
