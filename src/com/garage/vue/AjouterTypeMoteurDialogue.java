package com.garage.vue;

import com.garage.controleur.Controleur;
import com.garage.modele.voiture.moteur.TypeMoteur;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterTypeMoteurDialogue {

	@FXML
	private TextField nom;

	private Stage stageDialogue;

	private Controleur controleur;

	public void annuler() {
		stageDialogue.close();
	}

	public void valider() {
		controleur.ajouter(new TypeMoteur(0, nom.getText()));
		stageDialogue.close();
	}

	public void setStage(Stage stageDialogue) {
		this.stageDialogue = stageDialogue;
	}

	public void setControleur(Controleur controleur) {
		this.controleur=controleur;
		
	}

}
