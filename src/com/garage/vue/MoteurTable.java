package com.garage.vue;

import com.garage.modele.voiture.moteur.Moteur;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class MoteurTable extends Table<Moteur> {

	@FXML
	private TableColumn<Moteur, String> id;
	@FXML
	private TableColumn<Moteur, String> cylindre;
	@FXML
	private TableColumn<Moteur, String> moteur;
	@FXML
	private TableColumn<Moteur, String> prix;
	@FXML
	private TableColumn<Moteur, Boolean> action;

	@FXML
	private void initialize() {
		id.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getId()));
		cylindre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCylindre()));
		moteur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().getNom()));
		prix.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getPrix()));

		action.setCellFactory(cellData -> new SupprButtonCell());
	}

}
