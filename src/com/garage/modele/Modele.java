package com.garage.modele;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.garage.modele.dao.DAO;
import com.garage.modele.dao.DAOFactory;
import com.garage.modele.dao.DatabaseTable;
import com.garage.modele.voiture.Marque;
import com.garage.modele.voiture.Vehicule;
import com.garage.modele.voiture.moteur.Moteur;
import com.garage.modele.voiture.moteur.TypeMoteur;
import com.garage.modele.voiture.option.Option;
import com.garage.observer.Observable;
import com.garage.observer.Observer;

public class Modele implements Observable {

	private List<Observer> listObs = new ArrayList<Observer>();

	private DAO<Moteur> daoMoteur = DAOFactory.getDAOMoteur();
	private DAO<TypeMoteur> daoTypeMoteur = DAOFactory.getDAOTypeMoteur();
	private DAO<Option> daoOption = DAOFactory.getDAOOption();
	private DAO<Marque> daoMarque = DAOFactory.getDAOMarque();
	private DAO<Vehicule> daoVehicule = DAOFactory.getDAOVehicule();

	private String messageErreur = "";

	/**
	 * Retourne une liste d'objet contenant l'ensemble des éléments de la table
	 * demandé
	 * 
	 * @param table
	 *            table demandé
	 * @return liste d'objet contenant l'ensemble des éléments
	 */
	@SuppressWarnings("rawtypes")
	public List getTable(DatabaseTable table) {
		List list = null;
		try {
			switch (table) {
			case MOTEUR:
				list = daoMoteur.afficher();
				break;
			case TYPEMOTEUR:
				list = daoTypeMoteur.afficher();
				break;
			case OPTION:
				list = daoOption.afficher();
				break;
			case MARQUE:
				list = daoMarque.afficher();
				break;
			case VEHICULE:
				list = daoVehicule.afficher();
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			this.setErreur("Erreur de lecture dans la base de données");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Retourne un objet demandé de la table
	 * 
	 * @param table
	 *            table
	 * @param id
	 *            id de l'objet demandé
	 * @return objet demandé
	 */
	public Object info(DatabaseTable table, int id) {
		Object o = null;
		try {
			switch (table) {
			case MOTEUR:
				o = daoMoteur.info(id);
				break;
			case TYPEMOTEUR:
				o = daoTypeMoteur.info(id);
				break;
			case OPTION:
				o = daoOption.info(id);
				break;
			case MARQUE:
				o = daoMarque.info(id);
				break;
			case VEHICULE:
				o = daoVehicule.info(id);
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			this.setErreur("Erreur de lecture dans la base de données");
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * Ajoute un objet à une table
	 * 
	 * @param table
	 *            table où ajouter l'objet
	 * @param obj
	 *            objet à ajouter
	 */
	public void ajouter(DatabaseTable table, Object obj) {
		try {
			switch (table) {
			case MOTEUR:
				daoMoteur.ajouter((Moteur) obj);
				break;
			case TYPEMOTEUR:
				daoTypeMoteur.ajouter((TypeMoteur) obj);
				break;
			case OPTION:
				daoOption.ajouter((Option) obj);
				break;
			case MARQUE:
				daoMarque.ajouter((Marque) obj);
				break;
			case VEHICULE:
				daoVehicule.ajouter((Vehicule) obj);
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			this.setErreur("Erreur de la base de données lors de la création");
			e.printStackTrace();
		}

		updateObserver();
	}

	/**
	 * Supprime l'élement de la table
	 * 
	 * @param table
	 *            table
	 * @param id
	 *            id de l'élément à supprimer
	 */
	public void supprimer(DatabaseTable table, int id) {
		try {
			switch (table) {
			case MOTEUR:
				daoMoteur.supprimer(id);
				break;
			case TYPEMOTEUR:
				daoTypeMoteur.supprimer(id);
				break;
			case OPTION:
				daoOption.supprimer(id);
				break;
			case MARQUE:
				daoMarque.supprimer(id);
				break;
			case VEHICULE:
				daoVehicule.supprimer(id);
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			if (e.getClass() == SQLIntegrityConstraintViolationException.class) {
				this.setErreur("Cette objet ne peut être supprimé car il est utilisé par un autre");
				
			} else {
				this.setErreur("Erreur de la base de données lors de la suppression");
				e.printStackTrace();
			}
		}
		updateObserver();

	}

	/**
	 * Mise à jour des observer suite à une erreur (affichage d'un message d'erreur)
	 */
	@Override
	public void updateObserverWithErrorMessage() {
		for (Observer observer : listObs) {
			observer.updateWithErrorMessage(this.messageErreur);
		}

	}

	/**
	 * Mise à jour des observer
	 */
	@Override
	public void updateObserver() {
		for (Observer observer : listObs) {
			observer.update();
		}

	}

	/**
	 * Ajouter un observer
	 */
	@Override
	public void addObserver(Observer obs) {
		listObs.add(obs);

	}

	/**
	 * supprimer les observer
	 */
	@Override
	public void delObserver() {
		listObs = new ArrayList<Observer>();

	}

	/**
	 * Génére un message d'erreur
	 * 
	 * @param message
	 */
	public void setErreur(String message) {
		this.messageErreur = message;
		updateObserverWithErrorMessage();
	}

}
