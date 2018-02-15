package com.garage.vue;

import com.garage.modele.voiture.moteur.TypeMoteur;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class TypeMoteurTable extends Table<TypeMoteur> {

	@FXML
	private TableColumn<TypeMoteur, String> id;
	@FXML
	private TableColumn<TypeMoteur, String> description;
	@FXML
	private TableColumn<TypeMoteur, Boolean> action;

	@FXML
	private void initialize() {
		id.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getId()));
		description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));

		action.setCellFactory(cellData -> new SupprButtonCell());
	}

}
