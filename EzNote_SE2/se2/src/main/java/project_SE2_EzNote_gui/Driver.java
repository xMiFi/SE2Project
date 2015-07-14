package project_SE2_EzNote_gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Driver extends Application {

	private static Logger log = LogManager.getLogger(Driver.class);
	
	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		log.info("Starting EzNote[root frame]");
		Parent root = FXMLLoader.load(getClass().getResource("EzNote_FXML.fxml"));
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setTitle("SE2 - EzNote");
		primaryStage.setScene(scene);
		primaryStage.show();
		String style = "Style.css";
		scene.getStylesheets().add(style);
	}
}
