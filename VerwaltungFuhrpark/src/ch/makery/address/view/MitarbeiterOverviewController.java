package ch.makery.address.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import ch.makery.address.MainApp;
import ch.makery.address.Verwaltung;
import ch.makery.address.model.Fahrzeug;
import ch.makery.address.model.Mitarbeiter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MitarbeiterOverviewController {
	// UI-Elemente für Mitarbeiter
	@FXML
	private TableView<Mitarbeiter> maTable;
	@FXML
	private TableColumn<Mitarbeiter, String> firstNameColumn;
	@FXML
	private TableColumn<Mitarbeiter, String> lastNameColumn;
	@FXML
	private TableColumn<Mitarbeiter, String> personalnumberColumn;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label driverlicenseLabel;
	@FXML
	private Label personalnumberLabel;
	@FXML
	private Label banLabel;
	@FXML
	private Label birthdayLabel;
	@FXML
	private TextField field;
	@FXML
	private TextField field2;
	// UI-Elemente für Fahrzeuge
	@FXML
	private TableView<Fahrzeug> fzTable;
	@FXML
	private TableColumn<Fahrzeug, String> typColumn;
	@FXML
	private TableColumn<Fahrzeug, String> modelColumn;
	@FXML
	private TableColumn<Fahrzeug, String> licensePlateColumn;
	@FXML
	private Label typLabel;
	@FXML
	private Label modelLabel;
	@FXML
	private Label licensePlateLabel;
	@FXML
	private Label essentialDriverLicenseLabel;
	@FXML
	private Label availableLabel;
	@FXML
	private Label if_hasCarLabel;
	@FXML
	private Label rentedWhatLabel;
	@FXML
	private Label rentedWhatLicensePlateLabel;
	@FXML
	private Label if_fromLabel;
	@FXML
	private Label fromLabel;
	@FXML
	private Label if_toLabel;
	@FXML
	private Label toLabel;
	@FXML
	private Label rentedByLabel;
	@FXML
	private Label rentedByPNOLabel;
	@FXML
	private Label vonLabel;
	@FXML
	private Label bisLabel;
	@FXML
	private Label durchsNutzungLabel;
	@FXML
	private Button aendernButtonM;
	@FXML
	private Button loeschenButtonM;
	@FXML
	private Button buchenButtonM;
	@FXML
	private Button zurueckgebenButtonM;
	@FXML
	private Button aendernButtonF;
	@FXML
	private Button loeschenButtonF;
	@FXML
	private Button buchenButtonF;
	@FXML
	private Button zurueckgebenButtonF;
	@FXML
	private TabPane tabPane;

	// Referenz auf MainApp
	private Verwaltung verwaltung;
	private MainApp mainApp;

	public MitarbeiterOverviewController() {
	}

	public void setMainApp(MainApp mainApp, Verwaltung verwaltung) {
		this.mainApp = mainApp; // Referenz auf MainApp erstellen
		this.verwaltung = verwaltung;
		// Füge den Tables die jeweiligen ObservableLists hinzu
		maTable.setItems(verwaltung.getPersonData());
		fzTable.setItems(verwaltung.getFahrzeugData());
		ObservableList<Mitarbeiter> masterData = verwaltung.getPersonData();
		FilteredList<Mitarbeiter> filteredData = new FilteredList<>(masterData, p -> true);

		// Listenser für filterField setzen
		field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// Zeige alle Mitarbeiter wenn feld leer ist
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Vergleiche eingabe mit Vor-/Nachname
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getFirstName().toLowerCase().startsWith(lowerCaseFilter)) {
					return true;
				} else if (person.getLastName().toLowerCase().startsWith(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
		});

		// FilteredList in SortedList packen
		SortedList<Mitarbeiter> sortedData = new SortedList<>(filteredData);

		// Binde sortedData Comparator an maTable Comparator
		sortedData.comparatorProperty().bind(maTable.comparatorProperty());

		// Setze Daten des maTables auf gefilterte Daten
		maTable.setItems(sortedData);
		ObservableList<Fahrzeug> masterData2 = verwaltung.getFahrzeugData();
		FilteredList<Fahrzeug> filteredData2 = new FilteredList<>(masterData2, p -> true);

		field2.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData2.setPredicate(fahrzeug -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (fahrzeug.getModel().toLowerCase().startsWith(lowerCaseFilter)) {
					return true;
				} else if (fahrzeug.getLicensePlate().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
		});
		SortedList<Fahrzeug> sortedData2 = new SortedList<>(filteredData2);
		sortedData2.comparatorProperty().bind(fzTable.comparatorProperty());
		fzTable.setItems(sortedData2);
	}

	private void showPersonDetails(Mitarbeiter mitarbeiter) {
		if (mitarbeiter != null) {
			// Fülle die Labels mit MA Infos
			firstNameLabel.setText(mitarbeiter.getFirstName());
			lastNameLabel.setText(mitarbeiter.getLastName());
			driverlicenseLabel.setText(mitarbeiter.getDriverslicense());
			personalnumberLabel.setText(mitarbeiter.getPersonalnumber());
			banLabel.setText(Boolean.toString(mitarbeiter.getBan()));
			birthdayLabel.setText(mitarbeiter.getBirthday());
			aendernButtonM.setDisable(false);
			loeschenButtonM.setDisable(false);
			// falls Fahrzeug geliehen
			if (mitarbeiter.getHatFahrzeug()) {
				rentedWhatLabel.setText(mitarbeiter.getRentedWhat());
				rentedWhatLicensePlateLabel.setText(mitarbeiter.getRentedWhatLicensePlate());
				fromLabel.setText(mitarbeiter.getVon().toString());
				toLabel.setText(mitarbeiter.getBis().toString());
				buchenButtonM.setDisable(true);
				zurueckgebenButtonM.setDisable(false);
			} else { // falls Fahrzeug nicht geliehen
				buchenButtonM.setDisable(false);
				zurueckgebenButtonM.setDisable(true);
				rentedWhatLabel.setText("-");
				rentedWhatLicensePlateLabel.setText("-");
				fromLabel.setText("-");
				toLabel.setText("-");
			}
		} else {
			// MA ist null, entferne den Text und deaktiviere die Buttons
			aendernButtonM.setDisable(true);
			loeschenButtonM.setDisable(true);
			buchenButtonM.setDisable(true);
			zurueckgebenButtonM.setDisable(true);
			firstNameLabel.setText("-");
			lastNameLabel.setText("-");
			driverlicenseLabel.setText("-");
			personalnumberLabel.setText("-");
			banLabel.setText("-");
			birthdayLabel.setText("-");
			rentedWhatLabel.setText("-");
			rentedWhatLicensePlateLabel.setText("-");
			fromLabel.setText("-");
			toLabel.setText("-");
		}
	}

	private void showFahrzeugDetails(Fahrzeug fahrzeug) {
		double durchsNutzung = 0;
		NumberFormat formatter = new DecimalFormat("#0.00");
		if (fahrzeug != null) {
			modelLabel.setText(fahrzeug.getModel());
			typLabel.setText(fahrzeug.getTyp());
			licensePlateLabel.setText(fahrzeug.getLicensePlate());
			essentialDriverLicenseLabel.setText(fahrzeug.getEssentialDriverslicense());
			availableLabel.setText(Boolean.toString(fahrzeug.getAvailability()));
			aendernButtonF.setDisable(false);
			loeschenButtonF.setDisable(false);
			if (fahrzeug.getAvailability()) {
				rentedByLabel.setText("-");
				rentedByPNOLabel.setText("-");
				vonLabel.setText("-");
				bisLabel.setText("-");
				buchenButtonF.setDisable(false);
				zurueckgebenButtonF.setDisable(true);
			} else {
				rentedByLabel.setText(fahrzeug.getRentedBy());
				rentedByPNOLabel.setText(fahrzeug.getRentedByPNO());
				vonLabel.setText(fahrzeug.getVon().toString());
				bisLabel.setText(fahrzeug.getBis().toString());
				buchenButtonF.setDisable(true);
				zurueckgebenButtonF.setDisable(false);
			}
			if (fahrzeug.getWieOftGeliehen() > 0) {
				durchsNutzung = (double) fahrzeug.getWieLangeGeliehen() / (double) fahrzeug.getWieOftGeliehen();
			}
			durchsNutzungLabel.setText(formatter.format(durchsNutzung) + " Tage");
		} else {
			aendernButtonF.setDisable(true);
			buchenButtonF.setDisable(true);
			zurueckgebenButtonF.setDisable(true);
			loeschenButtonF.setDisable(true);
			modelLabel.setText("-");
			typLabel.setText("-");
			licensePlateLabel.setText("-");
			essentialDriverLicenseLabel.setText("-");
			availableLabel.setText("-");
			rentedByLabel.setText("-");
			rentedByPNOLabel.setText("-");
			vonLabel.setText("-");
			bisLabel.setText("-");
			durchsNutzungLabel.setText("-");
		}
	}

	@FXML
	private void handleDeletePerson() {
		int selectedIndex = maTable.getSelectionModel().getSelectedIndex();// Welcher
																			// MA
																			// wurde
																			// gewählt
		if (selectedIndex >= 0) {
			for (int i = 0; i < verwaltung.getPersonData().size(); i++) {
				if (maTable.getItems().get(selectedIndex).equals(verwaltung.getPersonData().get(i))) {
					verwaltung.getPersonData().remove(i); // Lösche
															// MA
					break;
				}
			}
		}
	}

	@FXML
	private void handleDeleteFahrzeug() {
		int selectedIndex = fzTable.getSelectionModel().getSelectedIndex();// Welches
		// FZ
		// wurde
		// gewählt
		if (selectedIndex >= 0) {
			for (int i = 0; i < verwaltung.getFahrzeugData().size(); i++) {
				if (fzTable.getItems().get(selectedIndex).equals(verwaltung.getFahrzeugData().get(i))) {
					verwaltung.getFahrzeugData().remove(i); // Lösche
					// FZ
					break;
				}
			}
		}
	}

	@FXML
	private void initialize() {
		// Initialisiere den MA-Table
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		personalnumberColumn.setCellValueFactory(cellData -> cellData.getValue().personalnumberProperty());
		// Initialisiere den FZ-Table
		typColumn.setCellValueFactory(cellData -> cellData.getValue().typProperty());
		modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
		licensePlateColumn.setCellValueFactory(cellData -> cellData.getValue().licensePlateProperty());
		// Lösche MA/FZ-Details
		showPersonDetails(null);
		showFahrzeugDetails(null);

		// Listener für Auswahländerung
		maTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
		fzTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showFahrzeugDetails(newValue));
	}

	@FXML
	private void handleNewPerson() {
		Mitarbeiter tempPerson = new Mitarbeiter(); // erzeuge MA Dummy
		boolean okClicked = mainApp.showMitarbeiterEditDialog(tempPerson); // Führe
																			// MAEditDialog
																			// aus,
																			// übergebe
																			// Dummy
		if (okClicked) {
			verwaltung.getPersonData().add(tempPerson); // Füge neuen MA zu
														// PersonData hinzu
		} else {
			Mitarbeiter.decreaseCountByOne();
		}
	}

	@FXML
	private void handleNewFahrzeug() {
		Fahrzeug tempFahrzeug = new Fahrzeug();
		boolean okClicked = mainApp.showFahrzeugEditDialog(tempFahrzeug);
		if (okClicked) {
			verwaltung.getFahrzeugData().add(tempFahrzeug);
		} else {
			Fahrzeug.decreaseCarNumberCounterByOne();
		}
	}

	@FXML
	private void handleEditPerson() {
		Mitarbeiter selectedPerson = maTable.getSelectionModel().getSelectedItem();// welcher
																					// MA
																					// wurde
																					// gewählt?
		if (selectedPerson != null) { // Wenn gewählt, dann
			boolean okClicked = mainApp.showMitarbeiterEditDialog(selectedPerson); // Zeige
																					// MAEditDialog
			if (okClicked) {
				// refreshPersonTable(); wirklich notwendig?
				showPersonDetails(selectedPerson); // Aktualisiert die
													// Details des angewählten
													// Mitarbeiters
			}
		}
	}

	@FXML
	private void handleEditFahrzeug() {
		Fahrzeug selectedFahrzeug = fzTable.getSelectionModel().getSelectedItem();
		if (selectedFahrzeug != null) {
			boolean okClicked = mainApp.showFahrzeugEditDialog(selectedFahrzeug);
			if (okClicked) {
				refreshFahrzeugTable();
				showFahrzeugDetails(selectedFahrzeug);
			}
		}
	}

	// RefreshTableMethoden
	public void refreshPersonTable() {
		int selectedIndex = maTable.getSelectionModel().getSelectedIndex(); // Hole
																			// aktuelle
																			// Auswahl
		maTable.setItems(null); // leere personTable
		maTable.layout();
		maTable.setItems(verwaltung.getPersonData()); // fülle aktuelle Daten in
														// PersonTable
		maTable.sort();
		maTable.getSelectionModel().select(selectedIndex - 1);
	}

	public void refreshFahrzeugTable() {
		int selectedIndex = fzTable.getSelectionModel().getSelectedIndex();
		fzTable.setItems(null);
		fzTable.layout();
		fzTable.setItems(verwaltung.getFahrzeugData());
		fzTable.getSelectionModel().select(selectedIndex);
	}

	// BuchenMethoden
	@FXML
	private void handleMitarbeiterBuchen() {
		Mitarbeiter mitarbeiter = maTable.getSelectionModel().getSelectedItem();// Welcher
																				// MA
																				// wurde
																				// gewählt?

		if (!mitarbeiter.getHatFahrzeug()) {
			Fahrzeug fahrzeug = fzTable.getSelectionModel().getSelectedItem(); // wird
			// benötigt,
			// um
			// FahrzeugDetails
			// sofort zu
			// aktualisieren,
			// falls
			// im
			// FahrzeugTab
			// bereits
			// angewählt

			mainApp.showMitarbeiterBuchenDialog(mitarbeiter); // Zeige
																// MitarbeiterDialog
																// mit MA
			showPersonDetails(mitarbeiter);
			showFahrzeugDetails(fahrzeug);
		}
	}

	@FXML
	private void handleFahrzeugBuchen() {
		Fahrzeug fahrzeug = fzTable.getSelectionModel().getSelectedItem();
		if (fahrzeug.getAvailability()) {
			Mitarbeiter mitarbeiter = maTable.getSelectionModel().getSelectedItem();
			mainApp.showFahrzeugBuchenDialog(fahrzeug);
			showPersonDetails(mitarbeiter);
			showFahrzeugDetails(fahrzeug);
		}
	}

	@FXML
	private void onEingabe() {
		for (Mitarbeiter mitarbeiter : verwaltung.getPersonData()) {
			if (!(lastNameColumn.getCellData(mitarbeiter).startsWith(field.getText()))) {

			}
		}

	}

	@FXML
	public void handleGiveBack(ActionEvent event) {
		int selectedIndex = maTable.getSelectionModel().getSelectedIndex(); // Welcher
																			// MA
																			// wurde
																			// gewählt

		int selectedIndexfz = fzTable.getSelectionModel().getSelectedIndex(); // wird
																				// benötigt,
																				// um
																				// FahrzeugDetails
																				// sicherheitshalber
																				// zu
																				// aktualisieren,
																				// falls
																				// im
																				// FahrzeugTab
																				// bereits
																				// angewählt

		// int selectedPerson =
		// Integer.parseInt(personalnumberColumn.getCellData(selectedIndex)) -
		// 100; seeeehr ungeschickt. - was passiert wenn es eine maNummer nicht
		// gibt?!
		if (selectedIndex >= 0) {
			verwaltung.getPersonData().get(selectedIndex).setHatFahrzeug(false); // Mitarbeiter
																					// gibt
																					// Fahrzeug
																					// zurück
			for (int i = 0; i < verwaltung.getFahrzeugData().size(); i++) {
				if (verwaltung.getPersonData().get(selectedIndex).getRentedWhatLicensePlate()
						.equals(verwaltung.getFahrzeugData().get(i).getLicensePlate())) {
					verwaltung.getFahrzeugData().get(i).setAvailability(true); // Fahrzeug
																				// availability
																				// wird
				} // wieder auf true gesetzt
			}
			showPersonDetails(maTable.getItems().get(selectedIndex));
			if (selectedIndexfz >= 0) {
				showFahrzeugDetails(fzTable.getItems().get(selectedIndexfz));
			}
		}
	}
}
