package com.garage.vue;

import com.garage.modele.voiture.Marque;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class MarqueTable extends Table<Marque> {

	@FXML
	private TableColumn<Marque, String> id;
	@FXML
	private TableColumn<Marque, String> nom;
	@FXML
	private TableColumn<Marque, Boolean> action;

	@FXML
	private void initialize() {
		id.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getId()));
		nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));

		action.setCellFactory(cellData -> new SupprButtonCell());
	}

}
