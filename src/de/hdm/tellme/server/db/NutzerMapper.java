package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.tellme.server.db.DatenbankVerbindung;
import de.hdm.tellme.shared.bo.Nutzer;

	/** Mapper-Klasse, die Nutzer-Objekte in der Datenbank abbildet. Diese enth�lt Methoden zum
	 * Anlegen, Aktualisieren, Entfernen und Suchen von Objekten.
	 * @author Nicole Reum */

public class NutzerMapper {
	private static NutzerMapper nutzerMapper = null;

	protected NutzerMapper() {

	}

	/** Die statische Methode wird �ber NutzerMapper nutzerMapper aufgerufen. Diese �berpr�ft, dass nur eine Instanz von NutzerMapper besteht.*/
	
	public static NutzerMapper nutzerMapper() {
		if (nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}
		return nutzerMapper;
	}
	
	/** Die Methode anlegen stellt eine Verbindung zur Datenbank her. Dazu wird die Methode "connection()" aus der Klasse DatenbankVerbindung
	 * dem Objekt con �bergeben.  Im Anschluss wird im "try-Block" ein Statement erstellt. 
	 * Nun legen wir einen neuen String an, der das SQL-Statement mit dynamischen Nutzerdaten beinhaltet. Jetzt wird �ber die Methode "state.executeUpdate(sqlquery);"
	 * ausgef�hrt und der SQL String an die Datenbank �bergeben. Sollte der "try-Block" Fehler aufweisen, wird der "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgef�hrt. 
	 */
	
	public void anlegen(Nutzer n) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Nutzer (Vorname, Nachname, Mailadresse) VALUES ("
					+ "'"
					+ n.getVorname()
					+ "','"
					+ n.getNachname()
					+ "','"
					+ n.getMailadresse() + "') ;";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Die Methode aktualisieren stellt eine Verbindung zur Datenbank her. Dazu wird die Methode "connection()" aus der Klasse DatenbankVerbindung
	 * dem Objekt con �bergeben.  Im Anschluss wird im "try-Block" ein Statement erstellt. 
	 * Nun legen wir einen neuen String an, der das SQL-Statement mit dynamischen Nutzerdaten beinhaltet. Jetzt wird �ber die Methode "state.executeUpdate(sqlquery);"
	 * ausgef�hrt und der SQL String an die Datenbank �bergeben. Sollte der "try-Block" Fehler aufweisen, wird der "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgef�hrt. 
	 */

	public void aktualisieren(Nutzer n) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();

			String sqlquery = "UPDATE Nutzer SET Vorname = '"
					+ n.getVorname() + "', Nachname= '" + n.getNachname()
					+ "', " + "Mailadresse= '" + n.getMailadresse() + "' "
					+ "WHERE Id = '" + n.getId() + "';";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	/** Die Methode entfernen stellt eine Verbindung zur Datenbank her. Dazu wird die Methode "connection()" aus der Klasse DatenbankVerbindung
	 * dem Objekt con �bergeben.  Im Anschluss wird im "try-Block" ein Statement erstellt. 
	 * Nun legen wir einen neuen String an, der das SQL-Statement mit dynamischen Nutzerdaten beinhaltet. Jetzt wird �ber die Methode "state.executeUpdate(sqlquery);"
	 * ausgef�hrt und der SQL String an die Datenbank �bergeben. Sollte der "try-Block" Fehler aufweisen, wird der "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgef�hrt. 
	 */

	public void entfernen(Nutzer n) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();

			String sqlquery = "DELETE FROM Nutzer WHERE Id='" + n.getId()
					 + "';";
					
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/** Die Methode suchenNutzerIdMitMailadresse stellt eine Verbindung zur Datenbank her. Dazu wird die Methode "connection()" aus der Klasse DatenbankVerbindung
	 * dem Objekt con �bergeben. Anschlie�end wird ein neues Objekt von dem Typ Nutzer erstellt (na). Im Anschluss wird im "try-Block" ein Statement erstellt.
	 * Nun legen wir �ber ResultSet fest, dass der Nutzer �ber die Mailadresse gesucht werden soll. Danach wird f�r jeder Eintrag in das Objekt na geschrieben. 
	 * Jetzt wird �ber die Methode "state.executeUpdate(sqlquery);" ausgef�hrt und der SQL String an die Datenbank �bergeben. 
	 * Sollte der "try-Block" Fehler aufweisen, wird der "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgef�hrt. �ber "return" wird der Nutzer mit allen Attributen ausgegeben. 
	 */

	public Nutzer suchenNutzerIdMitMailadresse(String eMailAdress) {
		
		Connection con = DatenbankVerbindung.connection();
		Nutzer na = new Nutzer();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM Nutzer WHERE Mailadresse='"
							+ eMailAdress + "';");

			while (rs.next()) {

				na.setId(rs.getInt("Id"));
				na.setVorname(rs.getString("Vorname"));
				na.setNachname(rs.getString("Nachname"));
				na.setMailadresse(rs.getString("Mailadresse"));
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return na;
	}
	
	/** Die Methode suchenNutzerMitId stellt eine Verbindung zur Datenbank her. Dazu wird die Methode "connection()" aus der Klasse DatenbankVerbindung
	 * dem Objekt con �bergeben. Anschlie�end wird ein neues Objekt von dem Typ Nutzer erstellt (na). Im Anschluss wird im "try-Block" ein Statement erstellt.
	 * Nun legen wir �ber ResultSet fest, dass der Nutzer �ber die Id gesucht werden soll. Danach wird f�r jeder Eintrag in das Objekt na geschrieben. 
	 * Jetzt wird �ber die Methode "state.executeUpdate(sqlquery);" ausgef�hrt und der SQL String an die Datenbank �bergeben. 
	 * Sollte der "try-Block" Fehler aufweisen, wird der "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgef�hrt. �ber "return" wird der Nutzer mit allen Attributen ausgegeben. 
	 */

	public Nutzer suchenNutzerMitId(Nutzer n) {

		Connection con = DatenbankVerbindung.connection();

		Nutzer na = new Nutzer();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Nutzer WHERE Id='"
					+ n.getId() + "';");

			while (rs.next()) {
				na.setId(rs.getInt("Id"));
				na.setVorname(rs.getString("Vorname"));
				na.setNachname(rs.getString("Nachname"));
				na.setMailadresse(rs.getString("Mailadresse"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return na;
	}

}