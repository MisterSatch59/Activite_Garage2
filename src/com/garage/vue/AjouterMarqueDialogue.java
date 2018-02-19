package com.garage.vue;

import com.garage.controleur.Controleur;
import com.garage.modele.voiture.Marque;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AjouterMarqueDialogue {

	@FXML
	private TextField nom;

	private Stage stageDialogue;

	private Controleur controleur;

	public void annuler() {
		stageDialogue.close();
	}

	public void valider() {
		if (!nom.getText().equalsIgnoreCase("")) {
			controleur.ajouter(new Marque(0, nom.getText()));
			stageDialogue.close();
		} else {
			Alert probleme = new Alert(AlertType.ERROR);
			probleme.setTitle("Erreur");
			probleme.setHeaderText("Veuillez entrer le nom de la marque");
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
