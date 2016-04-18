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

import java.time.temporal.ChronoUnit;

import ch.makery.address.MainApp;
import ch.makery.address.Verwaltung;
import ch.makery.address.model.Fahrzeug;
import ch.makery.address.model.Mitarbeiter;

public class FahrzeugBuchenDialogController {
	@FXML
	Label name;
	@FXML
	TableView<Mitarbeiter> maTable;
	@FXML
	TableColumn<Mitarbeiter, String> lastNameColumn;
	@FXML
	TableColumn<Mitarbeiter, String> personalNumberColumn;
	@FXML
	Button buchen;
	@FXML
	Button cancel;
	@FXML
	DatePicker vonDate;
	@FXML
	DatePicker bisDate;

	MainApp mainApp;
	Verwaltung verwaltung;
	Stage dialogStage;
	Fahrzeug fahrzeug;
	Mitarbeiter mitarbeiter;
	Boolean date1 = false;
	Boolean date2 = false;
	Boolean maSelected = false;

	@FXML
	private void handleOk() {
		mitarbeiter = maTable.getSelectionModel().getSelectedItem();
		mitarbeiter.setHatFahrzeug(true);
		mitarbeiter.setRentedWhat(fahrzeug);
		mitarbeiter.setRentedWhatLicensePlate(fahrzeug);
		mitarbeiter.setVon(vonDate.getValue());
		mitarbeiter.setBis(bisDate.getValue());
		fahrzeug.setAvailability(Boolean.valueOf(false));
		fahrzeug.setRentedBy(mitarbeiter);
		fahrzeug.setVon(vonDate.getValue());
		fahrzeug.setBis(bisDate.getValue());
		//Stat
		fahrzeug.increaseWieOftGeliehenByOne();
		int dauerInTage;
		dauerInTage = (int)ChronoUnit.DAYS.between(vonDate.getValue(), bisDate.getValue());
		fahrzeug.increaseWieLangeGeliehenByDays(dauerInTage);
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	@FXML
    private void initialize() {	//fülle zellen mit daten
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		personalNumberColumn.setCellValueFactory(cellData -> cellData.getValue().personalnumberProperty());
    }

	public void dateCorrect(){
		if(((vonDate.getValue().isBefore(bisDate.getValue()))||(vonDate.getValue().isEqual(bisDate.getValue())))&&(maTable.getSelectionModel().getSelectedIndex()!=-1)){
			buchen.setDisable(false);
		}
	}
	
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setFahrzeug(Fahrzeug fahrzeug) {
        this.fahrzeug = fahrzeug;
        name.setText(fahrzeug.getModel()+" "+fahrzeug.getLicensePlate());
    }
    public void setMainApp(MainApp mainApp,Verwaltung verwaltung, Fahrzeug fahrzeug) {
        this.mainApp = mainApp;
        this.verwaltung=verwaltung;
        setFahrzeug(fahrzeug);
        maTable.setItems(verwaltung.testMitarbeiterData(fahrzeug));
vonDate.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				date1 = true;
				if(date2){
					dateCorrect();
				}
			}
		});
        bisDate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				date2 = true;
				if(date1){
					dateCorrect();
				}
			}
        	
		});
        maTable.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				maSelected = true;
				if(date1&&date2){
					dateCorrect();
				}
			}
		});
    }
}
