package ch.makery.address.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import ch.makery.address.MainApp;
import ch.makery.address.Verwaltung;
import ch.makery.address.model.Fahrzeug;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatisticsController {



	int wieOftGeliehenCity = 0;
	int wieLangeGeliehenCity = 0;
	int wieOftGeliehenLimo = 0;
	int wieLangeGeliehenLimo = 0;
	int wieOftGeliehenGesamt = 0;
	int wieLangeGeliehenGesamt = 0;
	double cityDurchschnitt = 0;
	double limoDurchschnitt = 0;
	double gesamtDurchschnitt = 0;

	@FXML
	private Label dLeihzeitGesamtLabel;
	@FXML
	private Label dLeihzeitCityLabel;
	@FXML
	private Label dLeihzeitLimoLabel;
	@FXML
	private Label mostRented1Label;
	@FXML
	private Label mostRented2Label;
	@FXML
	private Label mostRented3Label;

	public void setMainApp(MainApp mainApp, Verwaltung verwaltung) {

		ObservableList<Fahrzeug> fzList = verwaltung.getFahrzeugData();

		NumberFormat formatter = new DecimalFormat("#0.00");

		for (Fahrzeug fahrzeug : fzList) {
			if (fahrzeug.getTyp().equals("Cityflitzer")) {
				wieOftGeliehenCity += fahrzeug.getWieOftGeliehen();
				wieLangeGeliehenCity += fahrzeug.getWieLangeGeliehen();
			}
			if (fahrzeug.getTyp().equals("Limousine")) {
				wieOftGeliehenLimo += fahrzeug.getWieOftGeliehen();
				wieLangeGeliehenLimo += fahrzeug.getWieLangeGeliehen();
			}
		}
		wieOftGeliehenGesamt = wieOftGeliehenCity + wieOftGeliehenLimo;
		wieLangeGeliehenGesamt = wieLangeGeliehenCity + wieLangeGeliehenLimo;

		if (wieOftGeliehenCity > 0) {
			cityDurchschnitt = (double) wieLangeGeliehenCity / (double) wieOftGeliehenCity;
		}

		if (wieOftGeliehenLimo > 0) {
			limoDurchschnitt = (double) wieLangeGeliehenLimo / (double) wieOftGeliehenLimo;
		}

		if (wieOftGeliehenGesamt > 0) {
			gesamtDurchschnitt = (double) wieLangeGeliehenGesamt / (double) wieOftGeliehenGesamt;

		}

		dLeihzeitGesamtLabel.setText(formatter.format(gesamtDurchschnitt) + " Tage");
		dLeihzeitCityLabel.setText(formatter.format(cityDurchschnitt) + " Tage");
		dLeihzeitLimoLabel.setText(formatter.format(limoDurchschnitt) + " Tage");

		for (int i = 0; i < fzList.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < fzList.size(); j++) {
				if (fzList.get(j).getWieOftGeliehen() > fzList.get(index).getWieOftGeliehen()) {
					index = j;
				}
			}
			Fahrzeug tempFahrzeug = fzList.get(index);
			fzList.set(index, fzList.get(i));
			fzList.set(i, tempFahrzeug);
		}

		mostRented1Label.setText("1. " + fzList.get(0).getModel() + " | " + fzList.get(0).getLicensePlate() + " | "
				+ fzList.get(0).getWieOftGeliehen() + "x geliehen");
		mostRented2Label.setText("2. " + fzList.get(1).getModel() + " | " + fzList.get(1).getLicensePlate() + " | "
				+ fzList.get(1).getWieOftGeliehen() + "x geliehen");
		mostRented3Label.setText("3. " + fzList.get(2).getModel() + " | " + fzList.get(2).getLicensePlate() + " | "
				+ fzList.get(2).getWieOftGeliehen() + "x geliehen");
	}
}
