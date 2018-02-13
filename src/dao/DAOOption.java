package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import voiture.option.Option;

/**
 * DAO associé au JavaBean Marque
 * @author Oltenos
 * @version 1.0
 */
public class DAOOption extends DAO<Option> {

	public DAOOption(Connection conn) {
		super(conn);
	}

	@Override
	public void ajouter(Option obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimer(Option obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Option initT() {
		return new Option();
	}

	@Override
	protected Option getT(ResultSet result) throws SQLException {
		return new Option(result.getInt("id"), result.getString("description"),result.getDouble("prix"));
	}

};
