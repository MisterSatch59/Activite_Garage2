package com.garage.modele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.garage.modele.voiture.option.Option;

/**
 * DAO associé au JavaBean Option
 * 
 * @author Oltenos
 * @version 1.0
 */
public class DAOOption extends DAO<Option> {

	public DAOOption(Connection conn) {
		super(conn);
	}

	@Override
	protected Option initT() {
		return new Option();
	}

	@Override
	protected Option getT(ResultSet result) throws SQLException {
		return new Option(result.getInt("id"), result.getString("description"), result.getDouble("prix"));
	}

	@Override
	protected String getValeur(Option obj) {
		return "'" + obj.getNom() + "', '" + obj.getPrix() + "'";
	}

	@Override
	protected String getColonnes() {
		return "description, prix";
	}

};
