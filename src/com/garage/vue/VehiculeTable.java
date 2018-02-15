package com.garage.vue;

import java.io.IOException;

import com.garage.Garage2;
import com.garage.modele.voiture.Vehicule;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VehiculeTable extends Table<Vehicule> {

	@FXML
	private TableColumn<Vehicule, String> id;
	@FXML
	private TableColumn<Vehicule, String> marque;
	@FXML
	private TableColumn<Vehicule, String> moteur;
	@FXML
	private TableColumn<Vehicule, String> prix;
	@FXML
	private TableColumn<Vehicule, String> nom;
	@FXML
	private TableColumn<Vehicule, Boolean> action1;
	@FXML
	private TableColumn<Vehicule, Boolean> action2;

	@FXML
	protected void initialize() {
		id.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getId()));
		marque.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarque().getNom()));
		moteur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMoteur().toString()));
		prix.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getPrixTotal()));

		nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));

		action1.setCellFactory(cellData -> new DetailButtonCell());
		action2.setCellFactory(cellData -> new SupprButtonCell());
	}

	private class DetailButtonCell extends TableCell<Vehicule, Boolean> {

		Button cellButton = new Button("Détails des options");

		DetailButtonCell() {

			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					Vehicule v = (Vehicule) getTableRow().getItem();

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Garage2.class.getResource("vue/OptionsFenetre.fxml"));
					try {
						BorderPane page = (BorderPane) loader.load();

						Stage stageDialogue = new Stage();
						stageDialogue.setTitle("Options");
						stageDialogue.initModality(Modality.WINDOW_MODAL);

						stageDialogue.initOwner(garage.getStage());
						Scene scene = new Scene(page);
						stageDialogue.setScene(scene);

						OptionsFenetre controller = loader.getController();
						controller.setVehicule(v);
						controller.setStage(stageDialogue);

						stageDialogue.showAndWait();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}

		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			}
		}
	}

}
