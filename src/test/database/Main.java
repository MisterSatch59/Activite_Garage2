package test.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import com.garage.modele.dao.DAO;
import com.garage.modele.dao.DAOFactory;
import com.garage.modele.voiture.Marque;
import com.garage.modele.voiture.Vehicule;
import com.garage.modele.voiture.moteur.Moteur;
import com.garage.modele.voiture.option.Option;

public class Main {
	public static void main(String[] args) {
		
		try {

			DAO<Moteur> dao = DAOFactory.getDAOMoteur();
			Moteur moteur;

			moteur = dao.info(4);

			DAO<Marque> dao2 = DAOFactory.getDAOMarque();
			Marque marque = dao2.info(1);

			Vehicule v = new Vehicule(0, "208", marque, moteur, 45000d);

			DAO<Option> dao4 = DAOFactory.getDAOOption();

			for (int i = 0; i < 5; i++) {
				v.addOption(dao4.info(i));
			}

			System.out.println(v);

			DAO<Vehicule> dao3 = DAOFactory.getDAOVehicule();
			dao3.ajouter(v);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------\n");

		String path = "";

		try {
			path = new File(".").getCanonicalPath();
			path += "/hsqldb/database/";
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		System.out.println("Chemin d'accés à HSQLDB : " + path);

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (Exception e) {
			System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
			e.printStackTrace();
			return;
		}

		try {
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:" + path + "VEHICULE", "SA", "");
			String[] tablesnames = { "marque", "type_moteur", "moteur", "option", "vehicule_option", "vehicule" };

			for (String table : tablesnames) {
				Statement state = conn.createStatement();
				System.out.println(("\nContenu de la table : " + table).toUpperCase());
				ResultSet result = state.executeQuery("SELECT * FROM " + table);
				ResultSetMetaData resultMeta = result.getMetaData();

				String columnSeparator = "", rowSeparator = "";
				for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
					columnSeparator += "********************";
					rowSeparator += "--------------------";
				}
				System.out.println("\n" + columnSeparator);
				for (int i = 1; i <= resultMeta.getColumnCount(); i++)
					System.out.print(StringUtils.center(resultMeta.getColumnName(i).toUpperCase(), 19) + "*");

				System.out.println("\n" + columnSeparator);

				while (result.next()) {
					for (int i = 1; i <= resultMeta.getColumnCount(); i++)
						System.out.print(StringUtils.center(result.getObject(i).toString(), 19) + "|");

					System.out.println("\n" + rowSeparator);
				}

				result.close();
				state.close();
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
