package project_SE2_EzNote_implement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project_SE2_EzNote_gui.Driver;

public class ClockThread extends Thread {
	/* Da ich absolut keine Idee hatte, wie man einen Thread in ein eigentlich "simples" Notizprogramm einbauen sollte,
	 * finden Sie an dieser Stelle Fremdcode bzw. die Idee eines Kommilitonen vor. Die Kommentare in dieser Klasse sollen
	 * jedoch zeigen, dass ich den Code verstehe.
	 */
	
	
	private static Logger log = LogManager.getLogger(Driver.class);
		// Initialisiert den Logger für diese Klasse
	
	
	
	protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	protected LocalDateTime currentTime = LocalDateTime.now();
	protected String time = currentTime.format(formatter);
		// Initialisiert die Variablen formatter, currentTime und time.
		// formatter gibt das Format an, in welchem nachher die Uhrzeit angezeigt wird ("HH:mm:ss")
		// currentTime gibt über die importierte Klasse "LocalDateTime" die aktuelle Zeit aus, welche dann in den String time umgewandelt wird
	
	@FXML private Label clock;
		// Realisiert die Uhr in der GUI
	
	public ClockThread (Label clock){
		this.clock = clock;
	}
		
	
	public String getTime(){
		return time;
	}
	
	@Override
	public void run() {
		// Beginnt den Thread-Code
		while (true){
			currentTime = LocalDateTime.now();
			time = currentTime.format(formatter);
			// Diese Schleife erfasst lediglich die aktuelle Zeit und gibt diese dann über den String weiter
			if (clock != null){
	            Platform.runLater(new Runnable() {
	            	// Die Runnable wird benötigt um einen GUI-Komponenten durch einen nicht GUI-Thread zu updaten
	                @Override
	                public void run() {
	    				clock.setText(time);
	                }
	            });
	            
	            try {
	                sleep(TimeUnit.SECONDS.toMillis(1));
	            } catch (InterruptedException ex) {
	            	log.trace(ex);
	            }
	            
			}
		}
	}
}
