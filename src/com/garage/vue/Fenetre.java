package com.garage.vue;

import java.io.IOException;

import com.garage.Garage2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Fenetre {

	private Garage2 garage;

	public void setMain(Garage2 garage) {
		this.garage = garage;
	}

	@FXML
	private void voirVehicules() {
		garage.setVueActive(VueActive.VEHICULE);
	}

	@FXML
	private void voirMarques() {
		garage.setVueActive(VueActive.MARQUE);
	}

	@FXML
	private void voirOptions() {
		garage.setVueActive(VueActive.OPTION);
	}

	@FXML
	private void voirMoteurs() {
		garage.setVueActive(VueActive.MOTEUR);
	}

	@FXML
	private void voirTypesMoteur() {
		garage.setVueActive(VueActive.TYPEMOTEUR);
	}

	@FXML
	private void ajouterVehicules() {
		ajouterDialogue("vue/AjouterVehiculeDialogue.fxml", VueActive.VEHICULE);
	}

	@FXML
	private void ajouterMarques() {
		ajouterDialogue("vue/AjouterMarqueDialogue.fxml", VueActive.MARQUE);
	}

	@FXML
	private void ajouterOptions() {
		ajouterDialogue("vue/AjouterOptionDialogue.fxml", VueActive.OPTION);
	}

	@FXML
	private void ajouterMoteurs() {
		ajouterDialogue("vue/AjouterMoteurDialogue.fxml", VueActive.MOTEUR);
	}

	@FXML
	private void ajouterTypesMoteur() {
		ajouterDialogue("vue/AjouterTypeMoteurDialogue.fxml", VueActive.TYPEMOTEUR);
	}

	private void ajouterDialogue(String adresse, VueActive v) {
		garage.setVueActive(v);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Garage2.class.getResource(adresse));
		try {
			BorderPane page = (BorderPane) loader.load();

			Stage stageDialogue = new Stage();

			stageDialogue.initModality(Modality.WINDOW_MODAL);

			stageDialogue.initOwner(garage.getStage());
			Scene scene = new Scene(page);
			stageDialogue.setScene(scene);

			switch (v) {
			case MARQUE:
				stageDialogue.setTitle("Nouvelle marque");
				AjouterMarqueDialogue controller1 = loader.getController();
				controller1.setStage(stageDialogue);
				controller1.setControleur(garage.getControleur());
				break;
			case TYPEMOTEUR:
				stageDialogue.setTitle("Nouveau type de moteur");
				AjouterTypeMoteurDialogue controller2 = loader.getController();
				controller2.setStage(stageDialogue);
				controller2.setControleur(garage.getControleur());
				break;
			case MOTEUR:
				stageDialogue.setTitle("Nouveau moteur");
				AjouterMoteurDialogue controller3 = loader.getController();
				controller3.setStage(stageDialogue);
				controller3.setMain(garage);
				controller3.setControleur(garage.getControleur());
				break;

			case OPTION:
				AjouterOptionDialogue controller4 = loader.getController();
				stageDialogue.setTitle("Nouvelle option");
				controller4.setStage(stageDialogue);
				controller4.setControleur(garage.getControleur());
				break;

			case VEHICULE:
				AjouterVehiculeDialogue controller5 = loader.getController();
				stageDialogue.setTitle("Nouveau véhicule");
				controller5.setStage(stageDialogue);
				controller5.setMain(garage);
				controller5.setControleur(garage.getControleur());
				break;
			default:
				garage.getModele().setErreur("Erreur de chargement de la page demandé");
				break;
			}
			stageDialogue.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
