package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.Statement;

import de.hdm.tellme.shared.bo.Hashtag;

	/** Mapper-Klasse, die Hashtag-Objekte in der Datenbank abbildet. Diese enth�lt Methoden zum
	 * Anlegen, Aktualisieren und Entfernen von Objekten.
	 * @author Nicole Reum */

public class HashtagMapper {
	private static HashtagMapper hashtagMapper=null;
	
	protected HashtagMapper() {
		
	}
	/** Die statische Methode wird �ber HashtagMapper hashtagMapper aufgerufen. Sie �berpr�ft, dass nur eine Instanz von HashtagMapper besteht.*/	
	
	public static HashtagMapper hashtagMapper() {
		if (hashtagMapper == null) {
			hashtagMapper = new HashtagMapper ();
	}
	return hashtagMapper;
	}
	
	public void anlegen (Hashtag h) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = " INSERT INTO Hashtag (Id, Schlagwort, Erstellungsdatum) VALUES ("
					+ "'"
					+ h.getId()
					+ "','"
					+ h.getSchlagwort()
					+"','"
					+ h.getErstellungsDatum()
					+ "';";
			state.executeUpdate (sqlquery);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Die Methode aktualisieren stellt eine Verbindung zur Datenbank her. Dazu wird die Methode "connection()" aus der Klasse DatenbankVerbindung
	 * dem Objekt con �bergeben.  Im Anschluss wird im "try-Block" ein Statement erstellt. 
	 * Nun legen wir einen neuen String an, der das SQL-Statement mit dynamischen Hashtagdaten beinhaltet. Jetzt wird �ber die Methode "state.executeUpdate(sqlquery);"
	 * ausgef�hrt und der SQL String an die Datenbank �bergeben. Sollte der "try-Block" Fehler aufweisen, wird der "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgef�hrt. 
	 */
	
	public void aktualisieren (Hashtag h) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE Hashtag SET ("
					+ "'"
					+ h.getSchlagwort()
					+ "','"
					+ h.getErstellungsDatum()
					+ "';";
			state.executeUpdate(sqlquery);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Die Methode entfernen stellt eine Verbindung zur Datenbank her. Dazu wird die Methode "connection()" aus der Klasse DatenbankVerbindung
	 * dem Objekt con �bergeben.  Im Anschluss wird im "try-Block" ein Statement erstellt. 
	 * Nun legen wir einen neuen String an, der das SQL-Statement mit dynamischen Hashtagdaten beinhaltet (HashtagId). Jetzt wird �ber die Methode "state.executeUpdate(sqlquery);"
	 * ausgef�hrt und der SQL String an die Datenbank �bergeben. Sollte der "try-Block" Fehler aufweisen, wird der "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgef�hrt. 
	 */
	
	public void entfernen (Hashtag h) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "DELETE FROM Hashtag WHERE Id = '" + h.getId() + "';";
			state.executeUpdate(sqlquery);		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
