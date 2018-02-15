package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.garage.modele.dao.DatabaseTable;
import com.garage.modele.dao.HsqldbConnection;

import fr.ocr.sql.DAOTableFactory;

/**
 * Classe permettant de g�rer l'affichage des diff�rentes tables de la BDD en
 * fonction du menu cliqu�
 * 
 * @author cysboy
 */
public class ViewMenuListener implements ActionListener {
	DatabaseTable table;
	JFrame frame;

	public ViewMenuListener(JFrame f, DatabaseTable tab) {
		table = tab;
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(
				new JScrollPane(DAOTableFactory.getTable(
						HsqldbConnection.getInstance(), table)),
				BorderLayout.CENTER);
		frame.getContentPane().revalidate();

	}
}
