package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.Statement;
import de.hdm.tellme.shared.bo.Nachricht;

public class NachrichtMapper {
	private static NachrichtMapper nachrichtMapper = null;

	protected NachrichtMapper() {

	}

	public static NachrichtMapper nachrichtMapper() {
		if (nachrichtMapper == null) {
			nachrichtMapper = new NachrichtMapper();
		}
		return nachrichtMapper;
	}

	/**
	 * Die Methode anlegen stellt eine Verbindung zur Datenbank her. Dazu wird
	 * die Methode "connection()" aus der Klasse DatenbankVerbindung dem Object
	 * con übergeben. Danach wird im "try-Block" ein Statement erstellt. Es wird
	 * ein neuer String angelegt, der das SQL-Statement mit dynamischen
	 * Narichtdaten beinhaltet. Danach wird über die Methode
	 * state.executeUpdate(sqlquery); ausgeführt und der SQL String an die
	 * Datenbank übergeben. Sollte im "try-Block" ein Fehler aufkommen, wird
	 * eine entsprechende Fehlermeldung ausgeführt (Exception).
	 * 
	 * @author Denis Pokorski
	 */
	public void anlegen(Nachricht n) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Nachricht (Text, Sichtbarkeit, ErstellungsDatum, AutorId) ("
					+ "'"
					+ n.getText()
					+ "','"
					+ n.getSichtbarkeit()
					+ "','"
					+ n.getErstellungsDatum() + "','";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void aktualisieren(Nachricht n) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE Nachricht " + "SET Nutzer= "'" + n.getNutzerId()
			          + "'" " + "WHERE id=" + n.getNutzerId();
		
	}
}
