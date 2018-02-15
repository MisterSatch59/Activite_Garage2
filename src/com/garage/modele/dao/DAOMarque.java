package com.garage.modele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.garage.modele.voiture.Marque;

/**
 * DAO associé au JavaBean Marque
 * 
 * @author Oltenos
 * @version 1.0
 */
public class DAOMarque extends DAO<Marque> {

	public DAOMarque(Connection conn) {
		super(conn);
	}

	@Override
	protected Marque getT(ResultSet result) throws SQLException {
		return new Marque(result.getInt("id"), result.getString("nom"));
	}

	@Override
	protected Marque initT() {
		return new Marque();
	}

	@Override
	protected String getValeur(Marque obj) {
		return "'" + obj.getNom().toUpperCase() + "'";
	}

	@Override
	protected String getColonnes() {
		return "nom";
	}

};
