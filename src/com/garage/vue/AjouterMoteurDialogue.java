package com.garage.vue;

import java.util.List;

import com.garage.Garage2;
import com.garage.controleur.Controleur;
import com.garage.modele.dao.DatabaseTable;
import com.garage.modele.voiture.moteur.Moteur;
import com.garage.modele.voiture.moteur.TypeMoteur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AjouterMoteurDialogue {

	@FXML
	private TextField cylindre;
	@FXML
	private ChoiceBox<TypeMoteur> type;
	@FXML
	private TextField prix;

	private Garage2 garage;

	private Stage stageDialogue;

	private Controleur controleur;

	public void annuler() {
		stageDialogue.close();
	}

	public void valider() {
		try {
			if (!cylindre.getText().equalsIgnoreCase("")) {
				if(type.getValue()!=null) {
					controleur.ajouter(new Moteur(0, type.getValue(), cylindre.getText(), Double.valueOf(prix.getText())));
					stageDialogue.close();
				}else {
					Alert probleme = new Alert(AlertType.ERROR);
					probleme.setTitle("Erreur");
					probleme.setHeaderText("Veuillez choisir le type de moteur");
					probleme.showAndWait();
				}
			}else {
				Alert probleme = new Alert(AlertType.ERROR);
				probleme.setTitle("Erreur");
				probleme.setHeaderText("Veuillez entrer la cylindré du moteur");
				probleme.showAndWait();
			}
		} catch (NumberFormatException e) {
			Alert probleme = new Alert(AlertType.ERROR);
			probleme.setTitle("Erreur");
			probleme.setHeaderText("Le prix du moteur est incorrect : merci d'entrer un prix en euros");
			probleme.showAndWait();
		}
	}

	public void setStage(Stage stageDialogue) {
		this.stageDialogue = stageDialogue;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;

	}

	@SuppressWarnings("unchecked")
	public void setMain(Garage2 garage) {
		this.garage = garage;
		ObservableList<TypeMoteur> list = FXCollections.observableArrayList();
		list.addAll((List<TypeMoteur>) (this.garage.getModele().getTable(DatabaseTable.TYPEMOTEUR)));

		type.setItems(list);
	}

}
