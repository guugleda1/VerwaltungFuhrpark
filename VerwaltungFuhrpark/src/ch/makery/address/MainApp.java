package ch.makery.address;

import java.io.IOException;
import ch.makery.address.model.Fahrzeug;
import ch.makery.address.model.Mitarbeiter;
import ch.makery.address.view.FahrzeugBuchenDialogController;
import ch.makery.address.view.FahrzeugEditDialogController;
import ch.makery.address.view.MitarbeiterBuchenDialogController;
import ch.makery.address.view.MitarbeiterEditDialogController;
import ch.makery.address.view.MitarbeiterOverviewController;
import ch.makery.address.view.RootLayoutController;
import ch.makery.address.view.StatisticsController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {


	Verwaltung verwaltung;
	private Stage primaryStage;
	private BorderPane rootLayout;


	public MainApp() {
		verwaltung = new Verwaltung();
	}

	public void showAbout() {
		try {
			// Lade das FXML und erzeuge eine neue Stage
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/About.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("About");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent e){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("EXTREMELY IMPORTANT");
					alert.setHeaderText("FOR YOUR INFORMATION");
					alert.setContentText("Steffen did the amazing EasterEgg!");
					alert.show();
				}
			});
			dialogStage.showAndWait();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showStatistics() {
		try {
			// Lade das FXML und erzeuge eine neue Stage
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Statistics.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Statistics");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			StatisticsController controller = loader.getController();
			controller.setMainApp(this, verwaltung);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		initRootLayout();
		showPersonOverview();
	}

	public void initRootLayout() {
		try {
			// Lade RootLayout.fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Verknüpfe die ControllerKlasse mit dem Fenster
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this,verwaltung);

			// Zeige das RootLayout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showPersonOverview() {
		try {
			// Lade MitarbeiterOverview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MitarbeiterOverview2.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			// Setze das MAOverview ins Center des rootLayout
			rootLayout.setCenter(personOverview);
			// Verknüpfe die ControllerKlasse mit dem Fenster
			MitarbeiterOverviewController controller = loader.getController();
			controller.setMainApp(this,verwaltung);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showFahrzeugEditDialog(Fahrzeug fahrzeug) {
		try {
			// Lade das FXML und erzeuge eine neue Stage
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/FahrzeugEditDialog.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Fahrzeug");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			// Verknüpfe Fenster mit Controller und setze FZ des Controllers auf
			// übergebenes FZ
			FahrzeugEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setFahrzeug(fahrzeug);
			dialogStage.showAndWait();
			return controller.isOkClicked();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showMitarbeiterEditDialog(Mitarbeiter mitarbeiter) {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/MitarbeiterEditDialog.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			MitarbeiterEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(mitarbeiter);

			dialogStage.showAndWait();

			return controller.isOkClicked();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showMitarbeiterBuchenDialog(Mitarbeiter mitarbeiter) {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/MitarbeiterBuchenDialog.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Buchen");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			MitarbeiterBuchenDialogController controller = loader.getController();
			controller.setMainApp(this,verwaltung, mitarbeiter);
			controller.setDialogStage(dialogStage);
			controller.setPerson(mitarbeiter);

			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showFahrzeugBuchenDialog(Fahrzeug fahrzeug) {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/FahrzeugBuchenDialog.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Buchen");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			FahrzeugBuchenDialogController controller = loader.getController();
			controller.setMainApp(this,verwaltung, fahrzeug);
			controller.setDialogStage(dialogStage);

			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void closePrimaryStage() {
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}
}