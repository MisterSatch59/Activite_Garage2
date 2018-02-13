package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import voiture.moteur.TypeMoteur;

/**
 * DAO associé au JavaBean Marque
 * @author Oltenos
 * @version 1.0
 */
public class DAOTypeMoteur extends DAO<TypeMoteur> {

	public DAOTypeMoteur(Connection conn) {
		super(conn);
	}

	@Override
	public void ajouter(TypeMoteur obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimer(TypeMoteur obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected TypeMoteur initT() {
		return new TypeMoteur();
	}

	@Override
	protected TypeMoteur getT(ResultSet result) throws SQLException {
		return new TypeMoteur(result.getInt("id"), result.getString("description"));
	}
	
};
