package custom;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {

	public static void showErrorDialog(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehler");
		alert.setHeaderText("Fehler");
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
	public static boolean showWarningDialog(String msg) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warnung");
		alert.setHeaderText("Warnung");
		alert.setContentText(msg);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
			return false;
		}
	}
	
}
