package controller;


import custom.Alerts;
import dao.BesitzerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Besitzer;

public class CreateBesitzerController {

	private Stage createBesitzerStage;
	
	private BesitzerDAO bDAO;
	
	public CreateBesitzerController(Stage createBesitzerStage) {
		this.createBesitzerStage = createBesitzerStage;
	}
	
    @FXML
    void initialize() {
    	bDAO = new BesitzerDAO();
    }
	
	
    @FXML
    private TextField tfFirma;

    @FXML
    private TextField tfAnsprechpartner;

    @FXML
    private TextField tfTelefon;

    @FXML
    private TextField tfEmail;

    @FXML
    void btnOnActionAbbrechen(ActionEvent event) {
    	createBesitzerStage.close();
    }

    @FXML
    void btnOnActionBestaetigen(ActionEvent event) {
    	if (checkIfInputValid()) {
    		Besitzer b = new Besitzer();
    		b.setFirma(Integer.parseInt(tfFirma.getText()));
    		b.setAnsprechpartner(tfAnsprechpartner.getText());
    		b.setTelefon(tfTelefon.getText());
    		b.setEmail(tfEmail.getText());
    		bDAO.create(b);
    		createBesitzerStage.close();
    	}
    }
    
    public boolean checkIfInputValid() {
    	boolean status = false;
    	if (!tfFirma.getText().trim().equals("")) {
    		try {
    			int firmaNummer = Integer.parseInt(tfFirma.getText());
    			boolean firmaAlreadyExists = false;
    			for (Besitzer b : bDAO.findAll()) {
    				if (firmaNummer == b.getFirma()) {
    					Alerts.showErrorDialog("Eine Firma mit der Zahl '" + firmaNummer + "' existiert bereits!");
    					firmaAlreadyExists = true;
    				}
    			}
    			if (!firmaAlreadyExists) {
    				status = true;
    			}
    		} catch (NumberFormatException nfe) {
    			Alerts.showErrorDialog("Im Feld 'Firma' wird eine Zahl erwartet!");
    		}
    	} else {
    		Alerts.showErrorDialog("Das Feld 'Firma' ist obligatorisch!");
    	}
    	return status;
    }
}
