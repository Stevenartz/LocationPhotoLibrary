package controller;

import java.math.BigDecimal;

import custom.Alerts;
import dao.AdresseDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Adresse;
import model.Besitzer;

public class CreateAdresseController {

	private Stage createAdresseStage;
	private Besitzer selectedBesitzer;
	
	private AdresseDAO aDAO;
	
	public CreateAdresseController(Stage createAdresseStage, Besitzer selectedBesitzer) {
		this.createAdresseStage = createAdresseStage;
		this.selectedBesitzer = selectedBesitzer;
	}
	
	@FXML
	void initialize() {
		aDAO = new AdresseDAO();
	}

    @FXML
    private TextField tfStrasse;

    @FXML
    private TextField tfPLZ;

    @FXML
    private TextField tfOrt;

    @FXML
    private TextField tfGebaeude;

    @FXML
    void btnOnActionAbbrechen(ActionEvent event) {
    	createAdresseStage.close();
    }

    @FXML
    void btnOnActionBestaetigen(ActionEvent event) {
    	if (checkIfInputValid()) {
    		Adresse a = new Adresse();
    		a.setStrasse(tfStrasse.getText());
    		a.setPlz(tfPLZ.getText());
    		a.setOrt(tfOrt.getText());
    		a.setGebaeude(tfGebaeude.getText());
    		a.setGpsbreite(new BigDecimal(1));
    		a.setGpslaenge(new BigDecimal(1));
    		a.setEast((byte) 1);
    		a.setNorth((byte) 1);
    		a.setBesitzer(this.selectedBesitzer);
    		aDAO.create(a);
    		createAdresseStage.close();
    	}
    }
    
    private boolean checkIfInputValid() {
    	boolean status = true;
    	if (tfStrasse.getText().trim().equals("")
    			|| tfPLZ.getText().trim().equals("")
    			|| tfOrt.getText().trim().equals("")
    			|| tfGebaeude.getText().trim().equals("")) {
    		status = false;
    		Alerts.showErrorDialog("Stellen Sie sicher, dass alle angegebenen Felder ausgefüllt wurden!");
    	}
    	return status;
    }

}
