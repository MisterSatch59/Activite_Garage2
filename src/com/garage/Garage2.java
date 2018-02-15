package com.garage;

import java.io.IOException;

import com.garage.controleur.Controleur;
import com.garage.modele.Modele;
import com.garage.modele.dao.DatabaseTable;
import com.garage.observer.Observer;
import com.garage.vue.Fenetre;
import com.garage.vue.MarqueTable;
import com.garage.vue.MoteurTable;
import com.garage.vue.OptionTable;
import com.garage.vue.TypeMoteurTable;
import com.garage.vue.VehiculeTable;
import com.garage.vue.VueActive;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Garage2 extends Application implements Observer {

	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;
	private Modele modele = new Modele();
	private Controleur controleur = new Controleur(modele);

	private VueActive vueActive = VueActive.VEHICULE;

	@Override
	public void start(Stage primaryStage) {
		modele.addObserver(this);
		this.stagePrincipal = primaryStage;
		stagePrincipal.setTitle("Garage");

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Garage2.class.getResource("vue/Fenetre.fxml"));
		try {
			conteneurPrincipal = (BorderPane) loader.load();
			Scene scene = new Scene(conteneurPrincipal);
			stagePrincipal.setScene(scene);
			stagePrincipal.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Fenetre controller = loader.getController();
		controller.setMain(this);

		this.update();

	}

	public Modele getModele() {
		return modele;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Controleur getControleur() {
		return controleur;
	}

	@Override
	public void update() {
		FXMLLoader load = new FXMLLoader();
		load.setLocation(Garage2.class.getResource(vueActive.getAdresse()));
		try {
			AnchorPane conteneur = (AnchorPane) load.load();
			conteneurPrincipal.setCenter(conteneur);

		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (vueActive) {
		case MARQUE:
			MarqueTable control1 = load.getController();
			control1.setMain(this, DatabaseTable.MARQUE);
			break;
		case TYPEMOTEUR:
			TypeMoteurTable control2 = load.getController();
			control2.setMain(this, DatabaseTable.TYPEMOTEUR);
			break;

		case MOTEUR:
			MoteurTable control3 = load.getController();
			control3.setMain(this, DatabaseTable.MOTEUR);
			break;

		case OPTION:
			OptionTable control4 = load.getController();
			control4.setMain(this, DatabaseTable.OPTION);
			break;

		case VEHICULE:
			VehiculeTable control5 = load.getController();
			control5.setMain(this, DatabaseTable.VEHICULE);
			break;
		default:
			modele.setErreur("Erreur de chargement de la page demandé");
			break;
		}

	}

	@Override
	public void updateWithErrorMessage(String message) {
		Alert probleme = new Alert(AlertType.ERROR);
		probleme.setTitle("Erreur");
		probleme.setHeaderText(message);
		probleme.showAndWait();

	}

	public void setVueActive(VueActive nouvelleVue) {
		this.vueActive = nouvelleVue;
		this.update();

	}

	public Window getStage() {
		return stagePrincipal;
	}
}
