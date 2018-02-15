package com.garage.vue;

import java.util.List;

import com.garage.Garage2;
import com.garage.modele.dao.DatabaseTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

public abstract class Table<T> {

	protected Garage2 garage;
	protected DatabaseTable tableBDD;

	@FXML
	protected TableView<T> table;

	@SuppressWarnings("unchecked")
	public void setMain(Garage2 garage, DatabaseTable tableBDD) {
		this.garage = garage;
		this.tableBDD = tableBDD;

		ObservableList<T> list = FXCollections.observableArrayList();
		list.addAll((List<T>) (this.garage.getModele().getTable(this.tableBDD)));

		table.setItems(list);
	}

	protected class SupprButtonCell extends TableCell<T, Boolean> {

		Button cellButton = new Button("Supprimer");

		SupprButtonCell() {

			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					@SuppressWarnings("unchecked")
					T t1 = (T) getTableRow().getItem();
					garage.getControleur().supprimer(t1);
				}
			});
		}

		@Override
		protected void updateItem(Boolean b, boolean empty) {
			super.updateItem(b, empty);
			if (!empty) {
				setGraphic(cellButton);
			}
		}
	}

}
