package com.garage.vue;

import com.garage.controleur.Controleur;
import com.garage.modele.voiture.option.Option;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AjouterOptionDialogue {

	@FXML
	private TextField nom;
	@FXML
	private TextField prix;


	private Stage stageDialogue;

	private Controleur controleur;

	public void annuler() {
		stageDialogue.close();
	}

	public void valider() {
		try {
			if(!nom.getText().equalsIgnoreCase("")) {
				controleur.ajouter(new Option(0, nom.getText(), Double.valueOf(prix.getText())));
				stageDialogue.close();
			}else {
				Alert probleme = new Alert(AlertType.ERROR);
				probleme.setTitle("Erreur");
				probleme.setHeaderText("Veuillez entrer le nom de l'option");
				probleme.showAndWait();
			}
		}catch (NumberFormatException e) {
			Alert probleme = new Alert(AlertType.ERROR);
			probleme.setTitle("Erreur");
			probleme.setHeaderText("Le prix de l'option est incorrect : merci d'entrer un prix en euros");
			probleme.showAndWait();
		}
	}

	public void setStage(Stage stageDialogue) {
		this.stageDialogue = stageDialogue;
	}

	public void setControleur(Controleur controleur) {
		this.controleur=controleur;
		
	}

}
