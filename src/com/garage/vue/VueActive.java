package com.garage.vue;

public enum VueActive {
	MARQUE("vue/MarqueTable.fxml"), TYPEMOTEUR("vue/TypeMoteurTable.fxml"), MOTEUR("vue/MoteurTable.fxml"), VEHICULE(
			"vue/VehiculeTable.fxml"), OPTION("vue/OptionTable.fxml");

	String adresse;

	VueActive(String adresse) {
		this.adresse = adresse;
	}

	public String getAdresse() {
		return adresse;
	}
}
