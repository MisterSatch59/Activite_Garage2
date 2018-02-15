package com.garage.modele.dao;

import java.sql.Connection;

/**
 * Factory des classes DAO
 * 
 * @author Oltenos
 * @version 1.0
 */
public class DAOFactory {
	/**
	 * connection à la Base de données
	 */
	public static Connection conn = HsqldbConnection.getInstance();

	/**
	 * retourne un nouveau DAOMarque
	 * 
	 * @see DAOMarque
	 * 
	 * @return nouveau objet DAOMarque
	 */
	public static DAOMarque getDAOMarque() {
		return new DAOMarque(conn);
	}

	/**
	 * retourne un nouveau DAOVehicule
	 * 
	 * @see DAOVehicule
	 * 
	 * @return nouveau objet DAOVehicule
	 */
	public static DAOVehicule getDAOVehicule() {
		return new DAOVehicule(conn);
	}

	/**
	 * retourne un nouveau DAOOption
	 * 
	 * @see DAOOption
	 * 
	 * @return nouveau objet DAOOption
	 */
	public static DAOOption getDAOOption() {
		return new DAOOption(conn);
	}

	/**
	 * retourne un nouveau DAOMoteur
	 * 
	 * @see DAOMoteur
	 * 
	 * @return nouveau objet DAOMoteur
	 */
	public static DAOMoteur getDAOMoteur() {
		return new DAOMoteur(conn);
	}

	/**
	 * retourne un nouveau DAOTypeMoteur
	 * 
	 * @see DAOTypeMoteur
	 * 
	 * @return nouveau objet DAOTypeMoteur
	 */
	public static DAOTypeMoteur getDAOTypeMoteur() {
		return new DAOTypeMoteur(conn);
	}
};
