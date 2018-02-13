package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import fr.ocr.sql.DatabaseTable;
import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;

/**
 * DAO associé au JavaBean Marque
 * 
 * @author Oltenos
 * @version 1.0
 */
public class DAOVehicule extends DAO<Vehicule> {

	public DAOVehicule(Connection conn) {
		super(conn);
	}

	@Override
	public void ajouter(Vehicule obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer(Vehicule obj) {
		// TODO Auto-generated method stub

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

		try {
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result2 = state.executeQuery(
					"SELECT id, description, prix FROM " + DatabaseTable.OPTION + " AS O " + " INNER JOIN "
							+ DatabaseTable.VEHICULE_OPTION + " AS V_O ON O.id = V_O.id_option" + " INNER JOIN "
							+ DatabaseTable.VEHICULE + " AS V ON V.id = V_O.id_vehicule" + " WHERE V.id = " + id);

			while (result2.next()) {
				v.addOption(DAOFactory.getDAOOption().getT(result2));
			}

			result2.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return v;
	}

};
