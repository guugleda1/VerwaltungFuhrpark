package ch.makery.address.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ch.makery.address.MainApp;
import ch.makery.address.Verwaltung;
import ch.makery.address.model.Fahrzeug;
import ch.makery.address.model.Mitarbeiter;

public class MitarbeiterBuchenDialogController {
	@FXML
	Label name;
	@FXML
	TableView<Fahrzeug> fzTable;
	@FXML
	TableColumn<Fahrzeug, String> modelColumn;
	@FXML
	TableColumn<Fahrzeug, String> licensePlateColumn;
	@FXML
	Button buchen;
	@FXML
	Button cancel;
	@FXML
	DatePicker vonDate;
	@FXML
	DatePicker bisDate;

	MainApp m;
	Verwaltung verwaltung;
	Stage dialogStage;
	Fahrzeug fahrzeug;
	Mitarbeiter mitarbeiter;
	Boolean date1 = false;
	Boolean date2 = false;
	Boolean fzSelected = false;

	@FXML
	private void handleOk() {
		fahrzeug = fzTable.getSelectionModel().getSelectedItem();
		fahrzeug.setAvailability(Boolean.valueOf(false));
		fahrzeug.setVon(vonDate.getValue());
		fahrzeug.setBis(bisDate.getValue());
		fahrzeug.setRentedBy(mitarbeiter);
		fahrzeug.setRentedByPNO(mitarbeiter);
		mitarbeiter.setHatFahrzeug(true);
		mitarbeiter.setRentedWhat(fahrzeug);
		mitarbeiter.setRentedWhatLicensePlate(fahrzeug);
		mitarbeiter.setVon(vonDate.getValue());
		mitarbeiter.setBis(bisDate.getValue());
		dialogStage.close();
		// Statistik
		fahrzeug.increaseWieOftGeliehenByOne();
		int dauerInTage;
		dauerInTage = (int) ChronoUnit.DAYS.between(vonDate.getValue(), bisDate.getValue());
		fahrzeug.increaseWieLangeGeliehenByDays(dauerInTage);
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	@FXML
	private void initialize() { // fülle zellen mit daten
		modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
		licensePlateColumn.setCellValueFactory(cellData -> cellData.getValue().licensePlateProperty());
	}

	public void dateCorrect() {
		if (((vonDate.getValue().isBefore(bisDate.getValue())) || (vonDate.getValue().isEqual(bisDate.getValue())))
				&& (fzTable.getSelectionModel().getSelectedIndex() != -1) && !vonDate.getValue().isBefore(LocalDate.now())) {
			buchen.setDisable(false);
		} else {
			buchen.setDisable(true);
		}
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPerson(Mitarbeiter person) {
		this.mitarbeiter = person;
		name.setText(mitarbeiter.getFirstName() + " " + mitarbeiter.getLastName());
	}

	public void setMainApp(MainApp mainApp, Verwaltung verwaltung, Mitarbeiter mitarbeiter) {
		this.m = mainApp;
		this.verwaltung = verwaltung;
		setPerson(mitarbeiter);
		fzTable.setItems(verwaltung.testFahrzeugData(mitarbeiter));
		vonDate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				date1 = true;
				if (date2 && fzSelected) {
					dateCorrect();
				}
			}
		});
		bisDate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				date2 = true;
				if (date1 && fzSelected) {
					dateCorrect();
				}
			}
		});
		fzTable.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				fzSelected = true;
				if (date1 && date2) {
					dateCorrect();
				}
			}
		});
	}
}
