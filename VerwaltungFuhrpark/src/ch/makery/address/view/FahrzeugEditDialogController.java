package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Fahrzeug;

public class FahrzeugEditDialogController {

	@FXML
	private TextField modelField;
	@FXML
	private TextField licensePlateField;
	@FXML
	private TextField essentialDriverlicenseField;
	@FXML
	private TextField typField;
	@FXML
	private TextField availabiltiyField;

	private Stage dialogStage;
	private Fahrzeug fahrzeug;
	private boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setFahrzeug(Fahrzeug fahrzeug) {
		this.fahrzeug = fahrzeug;
		modelField.setText(fahrzeug.getModel());
		licensePlateField.setText(fahrzeug.getLicensePlate());
		essentialDriverlicenseField.setText(fahrzeug.getEssentialDriverslicense());
		typField.setText(fahrzeug.getTyp());
		availabiltiyField.setText(Boolean.toString(fahrzeug.getAvailability()));
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
			fahrzeug.setModel(modelField.getText());
			fahrzeug.setLicensePlate(licensePlateField.getText());
			fahrzeug.setEssentialDriverslicense(essentialDriverlicenseField.getText());
			fahrzeug.setTyp(typField.getText());
			fahrzeug.setAvailability(Boolean.valueOf(availabiltiyField.getText()));

			okClicked = true;
			dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}