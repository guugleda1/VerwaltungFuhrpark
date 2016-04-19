package ch.makery.address;

import java.util.ArrayList;

import ch.makery.address.model.Fahrzeug;
import ch.makery.address.model.Mitarbeiter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Verwaltung {

	public MainApp mainApp;

	private ObservableList<Mitarbeiter> personData = FXCollections.observableArrayList();
	private ObservableList<Fahrzeug> fahrzeugData = FXCollections.observableArrayList();

	public Verwaltung() { // beim erzeugen xml-dateien lesen
		fahrzeugData = makeObservableListFahrzeug(XmlParser.readFahrzeugXml());
		personData = makeObservableListMitarbeiter(XmlParser.readMitarbeiterXml());
	}

	public ObservableList<Fahrzeug> testFahrzeugData(Mitarbeiter mitarbeiter) { // gibt
																				// liste
																				// aller
																				// für
																				// mitarbeiter
																				// buchbaren
																				// fahrzeuge
																				// zurück
		ObservableList<Fahrzeug> a = FXCollections.observableArrayList();
		for (int i = 0; i < getFahrzeugData().size(); i++) {
			if ((mitarbeiter.getDriverslicense().contains(getFahrzeugData().get(i).getEssentialDriverslicense()))
					&& getFahrzeugData().get(i).getAvailability()) {
				a.add(getFahrzeugData().get(i));
			}
		}
		return a;
	}

	public ObservableList<Mitarbeiter> testMitarbeiterData(Fahrzeug fahrzeug) { // gibt
																				// liste
																				// aller
																				// mitarbeiter
																				// zurück,
																				// die
																				// fahrzeug
																				// fahren
																				// könnten
		ObservableList<Mitarbeiter> a = FXCollections.observableArrayList();
		for (int i = 0; i < getPersonData().size(); i++) {
			if ((getPersonData().get(i).getDriverslicense().contains(fahrzeug.getEssentialDriverslicense()))
					&& !(getPersonData().get(i).getHatFahrzeug())) {
				a.add(getPersonData().get(i));
			}
		}
		return a;
	}

	private ObservableList<Mitarbeiter> makeObservableListMitarbeiter(ArrayList<Mitarbeiter> b) { // wandelt
																									// arraylist
																									// in
																									// observablelist
																									// um
		ObservableList<Mitarbeiter> a = FXCollections.observableArrayList();
		for (int i = 0; i < b.size(); i++) {
			a.add(b.get(i));
		}
		return a;
	}

	private ObservableList<Fahrzeug> makeObservableListFahrzeug(ArrayList<Fahrzeug> b) {
		ObservableList<Fahrzeug> a = FXCollections.observableArrayList();
		for (int i = 0; i < b.size(); i++) {
			a.add(b.get(i));
		}
		return a;
	}

	private ArrayList<Mitarbeiter> makeArrayListMitarbeiter(ObservableList<Mitarbeiter> l) { // wandelt
																								// observablelist
																								// in
																								// arraylist
																								// um
		ArrayList<Mitarbeiter> a = new ArrayList<Mitarbeiter>();
		for (int i = 0; i < l.size(); i++) {
			a.add(l.get(i));
		}
		return a;
	}

	private ArrayList<Fahrzeug> makeArrayListFahrzeug(ObservableList<Fahrzeug> l) {
		ArrayList<Fahrzeug> a = new ArrayList<Fahrzeug>();
		for (int i = 0; i < l.size(); i++) {
			a.add(l.get(i));
		}
		return a;
	}

	public ObservableList<Mitarbeiter> getPersonData() {
		return personData;
	}

	public ObservableList<Fahrzeug> getFahrzeugData() {
		return fahrzeugData;
	}

	public void speichern() { // wird bei programmende aufgerufen um xml-dateien
								// zu überschreiben
		XmlParser.writeFahrzeugXml(makeArrayListFahrzeug(fahrzeugData));
		XmlParser.writeMitarbeiterXml(makeArrayListMitarbeiter(personData));
	}
}
