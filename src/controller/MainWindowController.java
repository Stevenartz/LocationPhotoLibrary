package controller;

import java.io.IOException;

import custom.Alerts;
import dao.BesitzerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Besitzer;

public class MainWindowController {

	private Stage primaryStage;

	private BesitzerDAO bDAO;
	
	public MainWindowController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

    @FXML
    void initialize() {
    	bDAO = new BesitzerDAO();
    	updateUserList();
    }
	
    @FXML
    private ListView<Besitzer> userList;

    @FXML
    void btnOnActionAddUser(ActionEvent event) {
    	Stage createBesitzerStage = new Stage();
    	
    	createBesitzerStage.initModality(Modality.WINDOW_MODAL);
    	createBesitzerStage.initOwner(primaryStage);
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/CreateBesitzer.fxml"));
    	loader.setController(new CreateBesitzerController(createBesitzerStage));
    	
    	try {
			createBesitzerStage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	createBesitzerStage.setTitle("Besitzer erstellen");
    	createBesitzerStage.showAndWait();
    	updateUserList();
    }
    
    @FXML
    void btnOnActionDeleteUser(ActionEvent event) {
    	Besitzer b = userList.getSelectionModel().getSelectedItem();
    	if (b != null) {
    		if (Alerts.showWarningDialog("Willst du den Besitzer mit der Firmennummer '" + b.getFirma() + "' wirklich löschen?")) {
    			bDAO.delete(b.getId());
    			updateUserList();
    		}
    	}
    }
    
    private void updateUserList() {
    	userList.getItems().clear();
    	for (Besitzer b : bDAO.findAll()) {
    		userList.getItems().add(b);
    	}
    }

	
}
