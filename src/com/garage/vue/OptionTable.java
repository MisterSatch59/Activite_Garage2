package com.garage.vue;

import com.garage.modele.voiture.option.Option;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class OptionTable extends Table <Option> {

	
	@FXML
	private TableColumn<Option, String> id;
	@FXML
	private TableColumn<Option, String> description;
	@FXML
	private TableColumn<Option, String> prix;
	@FXML
	private TableColumn<Option, Boolean> action;

	@FXML
	private void initialize() {
		id.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getId()));
		description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
		prix.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getPrix()));

		action.setCellFactory(cellData -> new SupprButtonCell());
	}

}
