package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;

/**
 * DAO associé au JavaBean Marque
 * @author Oltenos
 * @version 1.0
 */
public class DAOMoteur extends DAO<Moteur> {

	public DAOMoteur(Connection conn) {
		super(conn);
	}

	@Override
	public void ajouter(Moteur obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimer(Moteur obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Moteur initT() {
		return new Moteur();
	}

	@Override
	protected Moteur getT(ResultSet result) throws SQLException {
		TypeMoteur tm = new TypeMoteur();
		DAOTypeMoteur daoTM = DAOFactory.getDAOTypeMoteur();
		tm = daoTM.info(result.getInt("moteur"));
		
		Moteur m = new Moteur(result.getInt("id"),tm,result.getString("cylindre"),result.getDouble("prix"));
		
		return m;
	}

};
