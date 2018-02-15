package com.garage.vue;

import java.util.List;

import com.garage.modele.voiture.Vehicule;
import com.garage.modele.voiture.option.Option;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class OptionsFenetre {
	@FXML
	Label titre;

	@FXML
	private TableView<Option> table;
	@FXML
	private TableColumn<Option, String> id;
	@FXML
	private TableColumn<Option, String> description;
	@FXML
	private TableColumn<Option, String> prix;

	private Stage stageDialogue;

	public void quitter() {
		stageDialogue.close();
	}

	@FXML
	protected void initialize() {
		id.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getId()));
		description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
		prix.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getPrix()));
	}

	public void setVehicule(Vehicule vehicule) {

		titre.setText("Option de " + vehicule.getNom());

		ObservableList<Option> list = FXCollections.observableArrayList();
		list.addAll((List<Option>) (vehicule.getOptions()));

		table.setItems(list);
	}

	public void setStage(Stage stageDialogue) {
		this.stageDialogue = stageDialogue;

	}

}
