package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Fahrzeug {
	private StringProperty model;
	private StringProperty licensePlate;
	private StringProperty essentialDriverslicense;
	private StringProperty typ;
	private BooleanProperty availability; // Verfügbarkeit
	private StringProperty rentedBy;
	private StringProperty rentedByPNO = new SimpleStringProperty("-");
	private StringProperty carNumber;
	private LocalDate von;
	private StringProperty vonSP;
	private LocalDate bis;
	private StringProperty bisSP;
	private int wieOftGeliehen = 0; // Statistik
	private int wieLangeGeliehen = 0; // Statistik
	private static int carNumbercounter = 1;

	// Konstruktoren 1)für editdialog 3) für xml-parser
	public Fahrzeug() {
		this.model = new SimpleStringProperty(null);
		this.licensePlate = new SimpleStringProperty(null);
		this.carNumber = new SimpleStringProperty(Integer.toString(carNumbercounter++));
		this.essentialDriverslicense = new SimpleStringProperty(null);
		this.typ = new SimpleStringProperty(null);
		this.availability = new SimpleBooleanProperty(true);
		this.von = LocalDate.now();
		this.bis = LocalDate.now();
	}

	public Fahrzeug(String model, String licensePlate, String carNumber, String essentialDrivericense, String typ,
			Boolean availability, String rentedBy, String rentedByPNO, LocalDate von, LocalDate bis, int wieOftGeliehen,
			int wieLangeGeliehen) {
		this.model = new SimpleStringProperty(model);
		this.licensePlate = new SimpleStringProperty(licensePlate);
		this.carNumber = new SimpleStringProperty(carNumber);
		this.essentialDriverslicense = new SimpleStringProperty(essentialDrivericense);
		this.typ = new SimpleStringProperty(typ);
		// Keine Automatische Rückgabe mehr!!! Also Abfrage redundant
		this.availability = new SimpleBooleanProperty(availability);
		this.rentedBy = new SimpleStringProperty(rentedBy);
		this.rentedByPNO = new SimpleStringProperty(rentedByPNO);
		this.von = von;
		this.bis = bis;
		this.setWieOftGeliehen(wieOftGeliehen);
		this.setWieLangeGeliehen(wieLangeGeliehen);
	}

	public static void decreaseCarNumberCounterByOne() {
		carNumbercounter--;
	}

	public LocalDate getVon() {
		return von;
	}

	public void setVon(LocalDate date) {
		this.von = date;
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

	public String getRentedBy() {
		return rentedBy.get();
	}

	public void setRentedBy(Mitarbeiter mitarbeiter) {
		this.rentedBy.set(mitarbeiter.getFirstName() + " " + mitarbeiter.getLastName());
	}

	public StringProperty rentedByProperty() {
		return rentedBy;
	}

	public String getCarNumber() {
		return carNumber.get();
	}

	public void setCarNumber(String carNumber) {
		this.carNumber.set(carNumber);
	}

	public StringProperty carNumberProperty() {
		return carNumber;
	}

	public String getModel() {
		return model.get();
	}

	public void setModel(String model) {
		this.model.set(model);
	}

	public StringProperty modelProperty() {
		return model;
	}

	public String getLicensePlate() {
		return licensePlate.get();
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate.set(licensePlate);
	}

	public StringProperty licensePlateProperty() {
		return licensePlate;
	}

	public String getEssentialDriverslicense() {
		return essentialDriverslicense.get();
	}

	public void setEssentialDriverslicense(String essentialDriverslicense) {
		this.essentialDriverslicense.set(essentialDriverslicense);
	}

	public StringProperty essentialDriverslicenseProperty() {
		return essentialDriverslicense;
	}

	public String getTyp() {
		return typ.get();
	}

	public void setTyp(String typ) {
		this.typ.set(typ);
	}

	public StringProperty typProperty() {
		return typ;
	}

	public Boolean getAvailability() {
		return availability.get();
	}

	public void setAvailability(Boolean availability) {
		this.availability.set(availability);
	}

	public BooleanProperty availabilityProperty() {
		return availability;
	}

	public int getWieOftGeliehen() {
		return wieOftGeliehen;
	}

	public void setWieOftGeliehen(int wieOftGeliehen) {
		this.wieOftGeliehen = wieOftGeliehen;
	}

	public void increaseWieOftGeliehenByOne() {
		this.wieOftGeliehen++;
	}

	public int getWieLangeGeliehen() {
		return wieLangeGeliehen;
	}

	public void setWieLangeGeliehen(int wieLangeGeliehen) {
		this.wieLangeGeliehen = wieLangeGeliehen;
	}

	public void increaseWieLangeGeliehenByDays(int days) {
		this.wieLangeGeliehen += days;
	}

	public String getRentedByPNO() {
		return rentedByPNO.get();
	}

	public void setRentedByPNO(Mitarbeiter mitarbeiter) {
		this.rentedByPNO.set(mitarbeiter.getPersonalnumber());
	}

	public StringProperty rentedByPNO() {
		return rentedByPNO;
	}

}
