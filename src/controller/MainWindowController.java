package controller;

import java.io.IOException;

import custom.Alerts;
import dao.AdresseDAO;
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
	private AdresseDAO aDAO;
	
	private Besitzer selectedBesitzer = null;
	
	public MainWindowController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

    @FXML
    void initialize() {
    	bDAO = new BesitzerDAO();
    	aDAO = new AdresseDAO();
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
    	if (selectedBesitzer != null) {
	    	Stage createAdresseStage = new Stage();
	    	
	    	createAdresseStage.initModality(Modality.WINDOW_MODAL);
	    	createAdresseStage.initOwner(primaryStage);
	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/CreateAdresse.fxml"));
	    	loader.setController(new CreateAdresseController(createAdresseStage, this.selectedBesitzer));
	    	
	    	try {
	    		createAdresseStage.setScene(new Scene(loader.load()));
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	createAdresseStage.setTitle("Adresse hinzufügen");
	    	createAdresseStage.showAndWait();
	    	updateAddressList();
    	} else {
    		Alerts.showWarningDialog("Stelle sicher, dass du einen Besitzer ausgewählt hast!");
    	}
    		
    }
    
    @FXML
    void btnOnActionDeleteAddress(ActionEvent event) {
    	Adresse a = addressList.getSelectionModel().getSelectedItem();
    	if (a != null) {
    		if (a.getAdressePhotos().size() == 0) {
    			if (Alerts.showWarningDialog("Willst du die Adresse mit an der '" + a.getStrasse() + "' wirklich löschen?")) {
    				aDAO.delete(a.getId());
    				updateAddressList();
    			}
    		}
    	}
    }
    
    private void setupUserList() {
    	userList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
    		if (newVal != null) {
    			this.selectedBesitzer = newVal;
    			updateAddressList();
    		}
    	});
    }
    
    
    
    private void updateUserList() {
    	userList.getItems().clear();
    	for (Besitzer b : bDAO.findAll()) {
    		userList.getItems().add(b);
    	}
    }
    
    private void updateAddressList() {
    	this.selectedBesitzer = bDAO.findById(this.selectedBesitzer.getId());
    	addressList.getItems().clear();
    	for (Adresse adresse : this.selectedBesitzer.getAdresses()) {
    		addressList.getItems().add(adresse);
    	}
    }

	
}
