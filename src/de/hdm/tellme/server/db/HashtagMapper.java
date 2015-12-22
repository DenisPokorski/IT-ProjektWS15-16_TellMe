package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Mapper-Klasse, die Hashtag-Objekte in der Datenbank abbildet. Diese enth�lt
 * Methoden zum Anlegen, Aktualisieren und Entfernen von Objekten.
 * 
 * @author Nicole Reum
 */

public class HashtagMapper {
	private static HashtagMapper hashtagMapper = null;

	protected HashtagMapper() {

	}

	/**
	 * Die statische Methode wird �ber HashtagMapper hashtagMapper aufgerufen.
	 * Sie �berpr�ft, dass nur eine Instanz von HashtagMapper besteht.
	 */

	public static HashtagMapper hashtagMapper() {
		if (hashtagMapper == null) {
			hashtagMapper = new HashtagMapper();
		}
		return hashtagMapper;
	}

	public void anlegen(Hashtag h) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = " INSERT INTO Hashtag (Id, Schlagwort, Erstellungsdatum) VALUES ("
					+ "'"
					+ h.getId()
					+ "','"
					+ h.getSchlagwort()
					+ "','"
					+ h.getErstellungsDatum() + "';";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Unterhaltung oeffentlicheNachrichtenNachHashtag(int id) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Nachricht> nachrichtenNachHashtag = new Vector<Nachricht>();
		Unterhaltung u = new Unterhaltung();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM NachrichtHashtag JOIN Nachricht ON NachrichtHashtag.NachrichtId = Nachricht.Id JOIN NachrichtUnterhaltung ON NachrichtHashtag.NachrichtId = NachrichtUnterhaltung.NachrichtId JOIN Hashtag ON NachrichtHashtag.HashtagId = Hashtag.Id JOIN Unterhaltung ON NachrichtUnterhaltung.UnterhaltungId = Unterhaltung.Id WHERE NachrichtHashtag.HashtagId = '"
					+ id
					+ "' AND Nachricht.Sichtbarkeit = 1 AND Unterhaltung.Typ = 1 ORDER BY Nachricht.ErstellungsDatum DESC";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Nachricht.Id"));
				nA.setText(rs.getString("Nachricht.Text"));
				nA.setSenderId(rs.getInt("Nachricht.AutorId"));
				nA.setErstellungsDatum(rs
						.getTimestamp("Nachricht.Erstellungsdatum"));
				nachrichtenNachHashtag.add(nA);

			}
			u.setAlleNachrichten(nachrichtenNachHashtag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public void erstellen(Hashtag hashtag) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Hashtag (Schlagwort, Erstellungsdatum) VALUES ("
					+ "'"
					+ hashtag.getSchlagwort()
					+ "','"
					+ DateHelperClass.getCurrentTime() + "') ;";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Die Methode entfernen stellt eine Verbindung zur Datenbank her. Dazu wird
	 * die Methode "connection()" aus der Klasse DatenbankVerbindung dem Objekt
	 * con �bergeben. Im Anschluss wird im "try-Block" ein Statement erstellt.
	 * Nun legen wir einen neuen String an, der das SQL-Statement mit
	 * dynamischen Hashtagdaten beinhaltet (HashtagId). Jetzt wird �ber die
	 * Methode "state.executeUpdate(sqlquery);" ausgef�hrt und der SQL String an
	 * die Datenbank �bergeben. Sollte der "try-Block" Fehler aufweisen, wird
	 * der "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgef�hrt.
	 */
	public void entfernen(Hashtag hashtag) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			state.execute("DELETE FROM NachrichtHashtag WHERE HashtagId = '"
					+ hashtag.getId() + "';");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Statement state = con.createStatement();
			state.execute("DELETE FROM NutzerHashtag WHERE HashtagId = '"
					+ hashtag.getId() + "';");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Statement state = con.createStatement();
			String sqlquery = "DELETE FROM Hashtag WHERE Id = '"
					+ hashtag.getId() + "';";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Die Methode aktualisieren stellt eine Verbindung zur Datenbank her. Dazu
	 * wird die Methode "connection()" aus der Klasse DatenbankVerbindung dem
	 * Objekt con �bergeben. Im Anschluss wird im "try-Block" ein Statement
	 * erstellt. Nun legen wir einen neuen String an, der das SQL-Statement mit
	 * dynamischen Hashtagdaten beinhaltet. Jetzt wird �ber die Methode
	 * "state.executeUpdate(sqlquery);" ausgef�hrt und der SQL String an die
	 * Datenbank �bergeben. Sollte der "try-Block" Fehler aufweisen, wird der
	 * "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgef�hrt.
	 */
	public void aktualisieren(Hashtag hashtag) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE Hashtag SET Schlagwort='"
					+ hashtag.getSchlagwort() + "', Erstellungsdatum='"
					+ DateHelperClass.getCurrentTime() + "' WHERE Id='"
					+ hashtag.getId() + "';";
			state.executeUpdate(sqlquery);
			System.out.println(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
