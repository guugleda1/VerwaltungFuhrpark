package ch.makery.address.view;

import javafx.fxml.FXML;
import ch.makery.address.MainApp;
import ch.makery.address.Verwaltung;
import javafx.event.ActionEvent;;

public class RootLayoutController {

	// Referenz auf MainApp
	private Verwaltung verwaltung;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp, Verwaltung verwaltung) {
		this.mainApp = mainApp; // Referenz auf MainApp erstellen
		this.verwaltung = verwaltung;
	}

	@FXML
	private void handleSpeichern() {
		verwaltung.speichern();
	}

	@FXML public void handleSchlieﬂen(ActionEvent event) {
		mainApp.closePrimaryStage();
	}

	@FXML public void showAbout() {
		mainApp.showAbout();
	}

	@FXML public void showStatistics() {
		mainApp.showStatistics();
	}
}
