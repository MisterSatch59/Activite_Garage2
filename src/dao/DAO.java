package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.ocr.sql.DatabaseTable;
import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;
import voiture.option.Option;

/**
 * Superclasse DAO
 * 
 * @author Oltenos
 * @version 1.0
 */
public abstract class DAO<T> {
	/**
	 * connection à la Base de données
	 */
	protected Connection conn;

	/**
	 * Nom de la table associé
	 */
	protected String table;

	/**
	 * Constructeur
	 * @param conn connection à  la base de données
	 */
	public DAO(Connection conn) {
		this.conn = conn;
		T t = initT();
		
		if (t.getClass() == Marque.class) {
			table = DatabaseTable.MARQUE.toString();
		} else if (t.getClass() == Moteur.class) {
			table = DatabaseTable.MOTEUR.toString();
		} else if (t.getClass() == TypeMoteur.class) {
			table = DatabaseTable.TYPEMOTEUR.toString();
		} else if (t.getClass() == Option.class) {
			table = DatabaseTable.OPTION.toString();
		} else if (t.getClass() == Vehicule.class) {
			table = DatabaseTable.VEHICULE.toString();
		}

	}

	/**
	 * Ajouter un objet à la base de données
	 * 
	 * @param obj
	 *            objet à ajouter
	 */
	public abstract void ajouter(T obj);

	/**
	 * Supprimer un objet à la base de données
	 * 
	 * @param obj
	 *            objet à supprimer
	 */
	public abstract void supprimer(T obj);

	/**
	 * Obtenir la liste des Objet de type T de la base de données
	 * 
	 * @param listObj
	 */
	public final List<T> afficher() {
		List<T> list = new ArrayList<T>();
		try {
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = state.executeQuery("SELECT * FROM " + table);
			
			while (result.next()) {
				T obj = getT(result);
				list.add(obj);
			}

			result.close();
			state.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Obtenir les détails de l'objet avec son identifiant
	 * 
	 * @param id
	 *            identifiant de l'objet
	 */
	public final T info(int id) {
		T obj = initT();
		try {
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = state.executeQuery("SELECT * FROM " + table + " WHERE id = " + id);

			if (result.first())
				obj = getT(result);

			result.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * Initialise un objet T
	 * 
	 * @return objet initialisé (avec constructeur par défaut)
	 */
	protected abstract T initT();

	/**
	 * retourne un objet T avec la ligne active du résultat
	 * 
	 * @param result
	 *            resultat à utiliser
	 * @return objet T créé
	 * @throws SQLException
	 */
	protected abstract T getT(ResultSet result) throws SQLException;
};
