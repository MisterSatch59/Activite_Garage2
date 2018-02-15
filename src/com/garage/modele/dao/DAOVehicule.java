package com.garage.modele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import com.garage.modele.voiture.Marque;
import com.garage.modele.voiture.Vehicule;
import com.garage.modele.voiture.moteur.Moteur;
import com.garage.modele.voiture.option.Option;

/**
 * DAO associé au JavaBean Vehicule
 * 
 * @author Oltenos
 * @version 1.0
 */
public class DAOVehicule extends DAO<Vehicule> {

	public DAOVehicule(Connection conn) {
		super(conn);
	}

	@Override
	public void supprimer(int id) throws SQLException {
		Statement state;

		state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		state.executeUpdate("DELETE FROM " + DatabaseTable.VEHICULE_OPTION + " WHERE id_vehicule = " + id);
		state.executeUpdate("DELETE FROM " + table + " WHERE id = " + id);

		state.close();

	}

	@Override
	protected Vehicule initT() {
		return new Vehicule();
	}

	@Override
	protected Vehicule getT(ResultSet result) throws SQLException {
		Marque marque;
		DAOMarque daoMarque = DAOFactory.getDAOMarque();
		marque = daoMarque.info(result.getInt("marque"));

		Moteur moteur;
		DAOMoteur daoMoteur = DAOFactory.getDAOMoteur();
		moteur = daoMoteur.info(result.getInt("moteur"));

		int id = result.getInt("id");

		Vehicule v = new Vehicule(id, result.getString("nom"), marque, moteur, result.getDouble("prix"));

		// Ajout des options :

		Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet result2 = state.executeQuery("SELECT id, description, prix FROM " + DatabaseTable.OPTION + " AS O "
				+ " INNER JOIN " + DatabaseTable.VEHICULE_OPTION + " AS V_O ON O.id = V_O.id_option" + " INNER JOIN "
				+ DatabaseTable.VEHICULE + " AS V ON V.id = V_O.id_vehicule" + " WHERE V.id = " + id);

		while (result2.next()) {
			v.addOption(DAOFactory.getDAOOption().getT(result2));
		}

		result2.close();
		state.close();

		return v;
	}

	@Override
	public void ajouter(Vehicule obj) throws SQLException {
		super.ajouter(obj);

		// Ajout des options dans la BDD vehicule_option
		List<Option> opt = obj.getOptions();

		Statement state;
		state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		ResultSet nextID = conn.prepareStatement("CALL NEXT VALUE FOR seq_vehicule_id").executeQuery();
		int ID = -1;
		if (nextID.next()) {
			ID = nextID.getInt(1) - 1;
		}
		for (Iterator<Option> iterator = opt.iterator(); iterator.hasNext();) {
			Option option = iterator.next();
			state.executeUpdate("INSERT INTO " + DatabaseTable.VEHICULE_OPTION + " (id_vehicule, id_option)  VALUES ( "
					+ ID + ", " + option.getId() + " )");
		}
		state.close();
	}

	@Override
	protected String getValeur(Vehicule obj) {
		return "'" + obj.getMarque().getId() + "', '" + obj.getMoteur().getId() + "', '" + obj.getPrix() + "', '"
				+ obj.getNom() + "'";
	}

	@Override
	protected String getColonnes() {
		return "marque, moteur, prix, nom";
	}

};
