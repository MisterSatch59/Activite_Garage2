package com.garage.controleur;

import java.util.List;

import com.garage.modele.Modele;
import com.garage.modele.dao.DatabaseTable;
import com.garage.modele.voiture.Marque;
import com.garage.modele.voiture.Vehicule;
import com.garage.modele.voiture.moteur.Moteur;
import com.garage.modele.voiture.moteur.TypeMoteur;
import com.garage.modele.voiture.option.Option;

public class Controleur {

	Modele modele;

	/**
	 * Création du controleur associé au modele
	 * 
	 * @param modele
	 */
	public Controleur(Modele modele) {
		this.modele = modele;
	}

	/**
	 * Supprimer un objet de la base de donné correspondante
	 * 
	 * @param o objet à supprimer
	 */
	public void supprimer(Object o) {
		if (o.getClass() == Marque.class) {
			modele.supprimer(DatabaseTable.MARQUE, ((Marque) o).getId());
		} else if (o.getClass() == Moteur.class) {
			modele.supprimer(DatabaseTable.MOTEUR, ((Moteur) o).getId());
		} else if (o.getClass() == TypeMoteur.class) {
			modele.supprimer(DatabaseTable.TYPEMOTEUR, ((TypeMoteur) o).getId());
		} else if (o.getClass() == Vehicule.class) {
			modele.supprimer(DatabaseTable.VEHICULE, ((Vehicule) o).getId());
		} else if (o.getClass() == Option.class) {
			modele.supprimer(DatabaseTable.OPTION, ((Option) o).getId());
		} else {
			modele.setErreur("Erreur lors de la suppression d'un élément, l'élément n'a pas été supprimé");
		}
	}

	/**
	 * Ajouter un objet dans la base de données correspondante
	 * 
	 * @param o objet à créer
	 */
	public void ajouter(Object o) {
		if (o.getClass() == Marque.class) {
			if (!existeDansBdd(o))
				modele.ajouter(DatabaseTable.MARQUE, o);
			else
				modele.setErreur("Cette marque existe déjà");
		} else if (o.getClass() == Moteur.class) {
			if (!existeDansBdd(o))
				modele.ajouter(DatabaseTable.MOTEUR, o);
			else
				modele.setErreur("Ce moteur existe déjà");
		} else if (o.getClass() == TypeMoteur.class) {
			if (!existeDansBdd(o))
				modele.ajouter(DatabaseTable.TYPEMOTEUR, o);
			else
				modele.setErreur("Ce type de moteur existe déjà");
		} else if (o.getClass() == Vehicule.class) {
				modele.ajouter(DatabaseTable.VEHICULE, o);

		} else if (o.getClass() == Option.class) {
			if (!existeDansBdd(o))
				modele.ajouter(DatabaseTable.OPTION, o);
			else
				modele.setErreur("Cette option existe déjà");
		} else {
			modele.setErreur("Erreur lors de la création d'un élément, l'élément n'a pas été créé");
		}
	}
	/**
	 * Test si l'objet existe déja dans la base de données correspondante
	 * @param o
	 * @return
	 */
	private boolean existeDansBdd(Object o) {
		boolean existeDeja = false;
		List list = null;
		
		if (o.getClass() == Marque.class) {
			list = modele.getTable(DatabaseTable.MARQUE);
		} else if (o.getClass() == Moteur.class) {
			list = modele.getTable(DatabaseTable.MOTEUR);
		} else if (o.getClass() == TypeMoteur.class) {
			list = modele.getTable(DatabaseTable.TYPEMOTEUR);
		} else if (o.getClass() == Option.class) {
			list = modele.getTable(DatabaseTable.OPTION);
		}
		
		for (Object obj : list) {
			if (obj.toString().equalsIgnoreCase((o.toString())))
				existeDeja = true;
		}

		return existeDeja;

	}
}

