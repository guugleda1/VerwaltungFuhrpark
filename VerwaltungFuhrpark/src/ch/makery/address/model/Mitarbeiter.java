package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mitarbeiter {

	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty driverslicense;
	private StringProperty personalnumber;
	private BooleanProperty ban;
	private StringProperty birthday;
	private BooleanProperty hatFahrzeug;
	private StringProperty rentedWhat;
	private StringProperty rentedWhatLicensePlate;
	private LocalDate von;
	private StringProperty vonSP;
	private LocalDate bis;
	private StringProperty bisSP;
	private static int count = 100;

	// Konstruktor für Erzeugung neuer Mitarbeiter
	public Mitarbeiter() {
		this.firstName = new SimpleStringProperty(null);
		this.lastName = new SimpleStringProperty(null);
		this.personalnumber = new SimpleStringProperty(Integer.toString(count++));
		this.driverslicense = new SimpleStringProperty(null);
		this.ban = new SimpleBooleanProperty(false);
		this.birthday = new SimpleStringProperty(null);
		this.hatFahrzeug = new SimpleBooleanProperty(false);
		this.rentedWhat = new SimpleStringProperty("-");
		this.rentedWhatLicensePlate = new SimpleStringProperty("-");
		this.von = LocalDate.now();
		this.vonSP = new SimpleStringProperty(null);
		this.bis = LocalDate.now();
		this.bisSP = new SimpleStringProperty(null);
	}

	// Konstruktor für Erzeugung von Mitarbeiter aus XML-Datei
	public Mitarbeiter(String firstName, String lastName, String personalnumber, String driverslicense, Boolean ban,
			String birthday, Boolean hatFahrzeug, String rentedWhat, String rentedWhatLicensePlate, LocalDate von,
			LocalDate bis) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.personalnumber = new SimpleStringProperty(personalnumber);
		this.driverslicense = new SimpleStringProperty(driverslicense);
		this.ban = new SimpleBooleanProperty(ban);
		this.birthday = new SimpleStringProperty(birthday);
		this.hatFahrzeug = new SimpleBooleanProperty(hatFahrzeug);
		this.rentedWhat = new SimpleStringProperty(rentedWhat);
		this.rentedWhatLicensePlate = new SimpleStringProperty(rentedWhatLicensePlate);
		this.von = von;
		this.vonSP = new SimpleStringProperty(von.toString());
		this.bis = bis;
		this.bisSP = new SimpleStringProperty(bis.toString());
		count++;
	}

	//needed for cancel "add new employee" because count++ in line 31 has to be reverted
	public static void decreaseCountByOne() {
		count--;
	}

	//Getter & Setter
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getDriverslicense() {
		return driverslicense.get();
	}
	public void setDriverslicense(String driverslicense) {
		this.driverslicense.set(driverslicense);
	}
	public StringProperty driverslicenseProperty() {
		return driverslicense;
	}

	public String getPersonalnumber() {
		return personalnumber.get();
	}
	public void setPersonalnumber(String personalnumber) {
		this.personalnumber.set(personalnumber);
	}
	public StringProperty personalnumberProperty() {
		return personalnumber;
	}

	public Boolean getBan() {
		return ban.get();
	}
	public void setBan(Boolean ban) {
		this.ban.set(ban);
	}
	public BooleanProperty banProperty() {
		return ban;
	}

	public String getBirthday() {
		return birthday.get();
	}
	public void setBirthday(String birthday) {
		this.birthday.set(birthday);
	}
	public StringProperty birthdayProperty() {
		return birthday;
	}

	public Boolean getHatFahrzeug() {
		return hatFahrzeug.get();
	}
	public void setHatFahrzeug(Boolean hatFahrzeug) {
		this.hatFahrzeug.set(hatFahrzeug);
	}
	public BooleanProperty hatFahrzeugProperty() {
		return hatFahrzeug;
	}

	public String getRentedWhat() {
		return rentedWhat.get();
	}
	public void setRentedWhat(Fahrzeug fahrzeug) {
		this.rentedWhat.set(fahrzeug.getModel());
	}
	public StringProperty rentedWhatProperty() {
		return rentedWhat;
	}

	public String getRentedWhatLicensePlate() {
		return rentedWhatLicensePlate.get();
	}
	public void setRentedWhatLicensePlate(Fahrzeug fahrzeug) {
		this.rentedWhatLicensePlate.set(fahrzeug.getLicensePlate());
	}
	public StringProperty rentedWhatLicensePlate() {
		return rentedWhatLicensePlate;
	}

	public LocalDate getVon() {
		return von;
	}
	public void setVon(LocalDate von) {
		this.von = von;
	}
	public StringProperty vonProperty() {
		vonSP = new SimpleStringProperty(von.toString());
		return vonSP;
	}

	public LocalDate getBis() {
		return bis;
	}
	public void setBis(LocalDate bis) {
		this.bis = bis;
	}
	public StringProperty bisProperty() {
		bisSP = new SimpleStringProperty(bis.toString());
		return bisSP;
	}
}