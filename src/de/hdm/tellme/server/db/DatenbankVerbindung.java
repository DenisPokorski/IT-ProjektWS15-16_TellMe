package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Aufbau und Vewarltung einer Datebankverbindung.
 * 
 * @author Denis Pokorski
 *
 */

public class DatenbankVerbindung {

	/**
	 * Die Klasse DatenbankVerbindung wird instantiiert. Mit dem Bezeichner
	 * <code>static</code> wird die Variable als sogenannter "Singleton"
	 * gekennzeichnet, dies bedeutet, dass die Variable für alle Instanzen der
	 * Klasse nur einmal vorhanden ist.
	 */
	private static Connection con = null;
	private static boolean UseLocalCon = true;

	/**
	 * Durch den Paramater static kann die DBConnection.connection() nur einmal
	 * instanziiert werden. Dadurch wird die Single-Ton Eigenschaft
	 * sichergestellt.
	 * 
	 * 
	 * 
	 * @author Denis Pokorski
	 */

	public static Connection connection() {

		if (con == null) {
			
			try {
				/**
				 * 
				 * Dem Connection-Objekt con wird, durch die Methode
				 * <code>getConnection()</code> der entpsrechende JDBC
				 * Datenbanktreiber, Datenbank-URL und Zugangsdaten übergeben.
				 */
				if (UseLocalCon) {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://feltrin-immobilien.de:3306/db_tellme","tellme", "M22azd0!");
				} else {
					Class.forName("com.mysql.jdbc.GoogleDriver");
					con = DriverManager.getConnection("jdbc:google:mysql://it-projekt-1168:tell-me/db_tellme?user=root");
				}
			}
			
			/**
			 * Bei einer fehlerhaften Verbindungsaufbau wird im "catch-Block"
			 * eine Exception ausgeführt.
			 */
			catch (Exception e1) {
				con = null;
				e1.printStackTrace();
			}
		}
		
		/**
		 * Verbindung zurückgeben
		 */
		return con;
	}

}