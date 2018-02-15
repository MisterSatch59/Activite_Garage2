package com.garage.vue;

import com.garage.Garage2;
import javafx.fxml.FXML;

public class Fenetre {

	private Garage2 garage;

	public void setMain(Garage2 garage) {
		this.garage = garage;
	}

	@FXML
	private void voirVehicules() {
		garage.setVueActive(VueActive.VEHICULE);
	}

	@FXML
	private void voirMarques() {
		garage.setVueActive(VueActive.MARQUE);
	}

	@FXML
	private void voirOptions() {
		garage.setVueActive(VueActive.OPTION);
	}

	@FXML
	private void voirMoteurs() {
		garage.setVueActive(VueActive.MOTEUR);
	}

	@FXML
	private void voirTypesMoteur() {
		garage.setVueActive(VueActive.TYPEMOTEUR);
	}

	@FXML
	private void ajouterVehicules() {
		// TODO
	}

	@FXML
	private void ajouterMarques() {
		// TODO
	}

	@FXML
	private void ajouterOptions() {
		// TODO
	}

	@FXML
	private void ajouterMoteurs() {
		// TODO
	}

	@FXML
	private void ajouterTypesMoteur() {
		// TODO
	}

}
