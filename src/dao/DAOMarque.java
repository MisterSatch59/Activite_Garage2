package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import voiture.Marque;

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
	public void ajouter(Marque obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer(Marque obj) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected Marque getT(ResultSet result) throws SQLException {
		return new Marque(result.getInt("id"),result.getString("nom"));
	}

	@Override
	protected Marque initT() {
		return new Marque();
	}

};
