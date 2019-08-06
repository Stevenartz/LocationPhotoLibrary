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
import model.Adresse;
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
    	setupUserList();
    	updateUserList();
    }
	
    @FXML
    private ListView<Besitzer> userList;

    @FXML
    private ListView<Adresse> addressList;

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
    		if (b.getAdresses().size() == 0) {
	    		if (Alerts.showWarningDialog("Willst du den Besitzer mit der Firmennummer '" + b.getFirma() + "' wirklich löschen?")) {
	    			bDAO.delete(b.getId());
	    			updateUserList();
	    		}
	    	} else {
	    		Alerts.showErrorDialog("Sie können diesen Benutzer nicht löschen, da dieser noch über Adressen verfügt.");
	    	}
    	}
    }
    
    @FXML
    void btnOnActionAddAddress(ActionEvent event) {
    	System.out.println(">>> Add Address");
    }
    
    @FXML
    void btnOnActionDeleteAddress(ActionEvent event) {
    	System.out.println(">>> Delete Address");
    }
    
    private void setupUserList() {
    	userList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
    		if (newVal != null) {
    			updateAddressList(newVal);
    		}
    	});
    }
    
    private void updateUserList() {
    	userList.getItems().clear();
    	for (Besitzer b : bDAO.findAll()) {
    		userList.getItems().add(b);
    	}
    }
    
    private void updateAddressList(Besitzer besitzer) {
    	addressList.getItems().clear();
    	for (Adresse adresse : besitzer.getAdresses()) {
    		addressList.getItems().add(adresse);
    	}
    }

	
}
