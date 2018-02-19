package com.garage.vue;

import java.util.List;

import com.garage.Garage2;
import com.garage.controleur.Controleur;
import com.garage.modele.dao.DatabaseTable;
import com.garage.modele.voiture.Marque;
import com.garage.modele.voiture.Vehicule;
import com.garage.modele.voiture.moteur.Moteur;
import com.garage.modele.voiture.option.Option;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class AjouterVehiculeDialogue {

	@FXML
	private ChoiceBox<Marque> marque;
	@FXML
	private ChoiceBox<Moteur> moteur;
	@FXML
	private TextField nom;
	@FXML
	private TextField prix;
	@FXML
	private FlowPane option;
	
	private CheckBox[] cbOptions;
	private Option[] optionVehicule;
	
	private Garage2 garage;

	private Stage stageDialogue;

	private Controleur controleur;

	public void annuler() {
		stageDialogue.close();
	}

	public void valider() {
		try {
			Vehicule v;
			if (!nom.getText().equalsIgnoreCase("")) {
				if (marque.getValue() != null) {
					if (moteur.getValue() != null) {
						v = new Vehicule(0, nom.getText(), marque.getValue(), moteur.getValue(),Double.valueOf(prix.getText()));
						for (int i = 0; i < cbOptions.length; i++) {
							if (cbOptions[i].isSelected()) {
								v.addOption(optionVehicule[i]);
							}
						}
						controleur.ajouter(v);
						stageDialogue.close();
					} else {
						Alert probleme = new Alert(AlertType.ERROR);
						probleme.setTitle("Erreur");
						probleme.setHeaderText("Veuillez choisir la moteur du véhicule");
						probleme.showAndWait();
					}
				} else {
					Alert probleme = new Alert(AlertType.ERROR);
					probleme.setTitle("Erreur");
					probleme.setHeaderText("Veuillez choisir la marque du véhicule");
					probleme.showAndWait();
				}
			} else {
				Alert probleme = new Alert(AlertType.ERROR);
				probleme.setTitle("Erreur");
				probleme.setHeaderText("Veuillez entrer le nom du véhicule");
				probleme.showAndWait();
			}
		} catch (NumberFormatException e) {
			Alert probleme = new Alert(AlertType.ERROR);
			probleme.setTitle("Erreur");
			probleme.setHeaderText("Le prix du véhicule est incorrect : merci d'entrer un prix en euros");
			probleme.showAndWait();
		}
	}

	public void setStage(Stage stageDialogue) {
		this.stageDialogue = stageDialogue;
	}

	public void setControleur(Controleur controleur) {
		this.controleur=controleur;
		
	}

	@SuppressWarnings("unchecked")
	public void setMain(Garage2 garage) {
		this.garage = garage;
		
		ObservableList<Moteur> listMoteur = FXCollections.observableArrayList();
		listMoteur.addAll((List<Moteur>) (this.garage.getModele().getTable(DatabaseTable.MOTEUR)));
		moteur.setItems(listMoteur);
		
		ObservableList<Marque> listMarque = FXCollections.observableArrayList();
		listMarque.addAll((List<Marque>) (this.garage.getModele().getTable(DatabaseTable.MARQUE)));
		marque.setItems(listMarque);
		
		List<Option> listOption = this.garage.getModele().getTable(DatabaseTable.OPTION);
		cbOptions = new CheckBox[listOption.size()];
		optionVehicule = new Option[listOption.size()];
		
		int i = 0;
		for (Option option : listOption) {
			cbOptions[i] = new CheckBox(option.getNom());
			optionVehicule[i] = option;
			Tooltip tooltip = new Tooltip(option.getPrix() + "€");
			cbOptions[i].setTooltip(tooltip);
			this.option.getChildren().add(cbOptions[i]);
			i++;
		}
	}

}
