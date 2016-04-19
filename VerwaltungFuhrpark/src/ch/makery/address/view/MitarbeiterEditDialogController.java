package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Mitarbeiter;

public class MitarbeiterEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField driverlicenseField;
	@FXML
	private TextField personalnumberField;
	@FXML
	private TextField banField;
	@FXML
	private TextField birthdayField;

	private Stage dialogStage;
	private Mitarbeiter mitarbeiter;
	private boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPerson(Mitarbeiter person) { // Setzt die Daten der Person in
												// die TextFields
		this.mitarbeiter = person;
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		driverlicenseField.setText(person.getDriverslicense());
		personalnumberField.setText(person.getPersonalnumber());
		banField.setText(Boolean.toString(person.getBan()));
		birthdayField.setText(person.getBirthday());
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() { // Daten des Dummys/MA werden auf Eingabe geändert
		mitarbeiter.setFirstName(firstNameField.getText());
		mitarbeiter.setLastName(lastNameField.getText());
		mitarbeiter.setDriverslicense(driverlicenseField.getText());
		mitarbeiter.setPersonalnumber(personalnumberField.getText());
		mitarbeiter.setBan(Boolean.valueOf(banField.getText()));
		mitarbeiter.setBirthday(birthdayField.getText());

		okClicked = true;
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}