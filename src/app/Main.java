package app;

import config.JPAUtil;
import controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainWindow.fxml"));
		loader.setController(new MainWindowController(primaryStage));
		primaryStage.setScene(new Scene(loader.load()));
		primaryStage.setOnCloseRequest(e -> JPAUtil.closeEntityManagerFactory());
		primaryStage.setTitle("Location Photo Library");
		primaryStage.show();
	}
	
}
