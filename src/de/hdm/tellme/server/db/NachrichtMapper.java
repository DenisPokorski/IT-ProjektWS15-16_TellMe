package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import javax.persistence.Result;

import com.google.appengine.api.search.query.ExpressionParser.negation_return;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Mapper-Klasse, die Nachricht-Objekte in der Datenbank abbildet. Diese enth�lt
 * Methoden zum Anlegen, Aktualisieren und Entfernen von Objekten.
 * 
 * @author Nicole Reum
 */

public class NachrichtMapper {
	private static NachrichtMapper nachrichtMapper = null;

	protected NachrichtMapper() {

	}

	/**
	 * Die statische Methode wird über NachrichtMapper nachrichtMapper
	 * aufgerufen. Sie überprüft, dass nur eine Instanz von NachrichtMapper
	 * besteht.
	 */

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
			String sqlquery = "INSERT INTO Nachricht (Text, Sichtbarkeit, ErstellungsDatum, AutorId) VALUES (" + "'" + n.getText() + "','" + 1 + "','"
					+ n.getErstellungsDatum() + "','" + n.getSenderId() + "')";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Die Methode aktualisieren stellt eine Verbindung zur Datenbank her. Dazu
	 * wird die Methode "connection()" aus der Klasse DatenbankVerbindung dem
	 * Objekt con übergeben. Im Anschluss wird im "try-Block" ein Statement
	 * erstellt. Nun legen wir einen neuen String an, der das SQL-Statement mit
	 * dynamischen NNachrichtdaten beinhaltet. Jetzt wird über die Methode
	 * "state.executeUpdate(sqlquery);" ausgeführt und der SQL String an die
	 * Datenbank übergeben. Sollte der "try-Block" Fehler aufweisen, wird der
	 * "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgeführt.
	 */

	public void aktualisieren(Nachricht n) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE Nachricht SET (" + "'" + n.getText() + "','" + n.getSichtbarkeit() + "','" + n.getErstellungsDatum() + "','";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Die Methode entfernen stellt eine Verbindung zur Datenbank her. Dazu wird
	 * die Methode "connection()" aus der Klasse DatenbankVerbindung dem Objekt
	 * con übergeben. Im Anschluss wird im "try-Block" ein Statement erstellt.
	 * Nun legen wir einen neuen String an, der das SQL-Statement mit
	 * dynamischen Nachrichtdaten beinhaltet. In diesem Fall wird die
	 * Sichtbarkeit auf 0 (nicht sichtbar) gesetzt. Jetzt wird über die Methode
	 * "state.executeUpdate(sqlquery);" ausgeführt und der SQL String an die
	 * Datenbank übergeben. Sollte der "try-Block" Fehler aufweisen, wird der
	 * "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgeführt.
	 */
	public void entfernen(Nachricht n) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE Nachricht SET Sichtbarkeit= '0' WHERE Sichtbarkeit='" + n.getSichtbarkeit() + "';";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int nachrichtSelektieren(Timestamp ts, String text) {
		Connection con = DatenbankVerbindung.connection();
		int nachrichtId = 0;
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Nachricht WHERE Erstellungsdatum='" + ts + "'");
			System.out.println(ts + " 1-  na - " + text + " -  na - " + nachrichtId);

			if (rs.next()) {
				nachrichtId = rs.getInt("Id");
				System.out.println(ts + " 2-  na - " + text + " -  na - " + nachrichtId);

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ts + " 3-  na - " + text + " -  na - " + nachrichtId);

		return nachrichtId;
	}

	public void hashtagZuordnungLoeschen(Hashtag hashtag) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			state.execute("DELETE FROM NachrichtHashtag WHERE HashtagId = '" + hashtag.getId() + "';");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Vector<Nachricht> selektiereNachrichtenVonId(int meineId) {
		Vector<Nachricht> meineNachrichten = new Vector<Nachricht>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery(("SELECT * FROM Nachricht WHERE AutorId = '" + meineId + "' AND Sichtbarkeit = 1 ORDER BY ErstellungsDatum DESC;"));
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Id"));
				nA.setText(rs.getString("Text"));
				nA.setSenderId(rs.getInt("AutorId"));
				nA.setErstellungsDatum(rs.getTimestamp("Erstellungsdatum"));
				meineNachrichten.add(nA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return meineNachrichten;
	}

	public Vector<Nachricht> report1_1Mapper(int meineId, Timestamp vonDatum, Timestamp bisDatum) {
		Vector<Nachricht> alleNachrichtenVonBestimmtenNutzerInBestimmtemZeitraum = new Vector<Nachricht>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(("SELECT * FROM Nachricht WHERE AutorId = '" + meineId + "' AND WHERE ErstellungsDatum BETWEEN '" + vonDatum
					+ "' AND '" + bisDatum + "' ORDER BY ErstellungsDatum DESC;"));
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Id"));
				nA.setText(rs.getString("Text"));
				nA.setSenderId(rs.getInt("AutorId"));
				nA.setErstellungsDatum(rs.getTimestamp("Erstellungsdatum"));
				alleNachrichtenVonBestimmtenNutzerInBestimmtemZeitraum.add(nA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleNachrichtenVonBestimmtenNutzerInBestimmtemZeitraum;

	}

	public Vector<Nachricht> report1_2Mapper(Timestamp vonDatum, Timestamp bisDatum) {
		Vector<Nachricht> alleNachrichtenInBestimmtemZeitraum = new Vector<Nachricht>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery(("SELECT * FROM Nachricht  WHERE ErstellungsDatum BETWEEN '" + vonDatum + "' AND '" + bisDatum + "' ORDER BY ErstellungsDatum DESC;"));
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Id"));
				nA.setText(rs.getString("Text"));
				nA.setSenderId(rs.getInt("AutorId"));
				nA.setErstellungsDatum(rs.getTimestamp("Erstellungsdatum"));
				alleNachrichtenInBestimmtemZeitraum.add(nA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleNachrichtenInBestimmtemZeitraum;
	}

	public Vector<Nachricht> report1_3Mapper(int meineId) {
		Vector<Nachricht> alleNachrichteneinesNutzers = new Vector<Nachricht>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(("SELECT * FROM Nachricht WHERE AutorId = '" + meineId + "' ORDER BY ErstellungsDatum DESC;"));
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Id"));
				nA.setText(rs.getString("Text"));
				nA.setSenderId(rs.getInt("AutoId"));
				nA.setErstellungsDatum(rs.getTimestamp("Erstellungsdatum"));
				alleNachrichteneinesNutzers.add(nA);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleNachrichteneinesNutzers;
	}

	public Vector<Nachricht> report1_4Mapper() {
		Vector<Nachricht> alleNachrichten = new Vector<Nachricht>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(("SELECT * FROM Nachricht ORDER BY ErstellungsDatum DESC;"));
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Id"));
				nA.setText(rs.getString("Text"));
				nA.setSenderId(rs.getInt("AutoId"));
				nA.setErstellungsDatum(rs.getTimestamp("Erstellungsdatum"));
				alleNachrichten.add(nA);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleNachrichten;
	}

	public boolean nachrichtEinerUnterhaltungZuordnen(int NachrichtenID, int UnterhaltungsID) {
		boolean erfolgreich = true;
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO NachrichtUnterhaltung (`UnterhaltungId`, `NachrichtId`) VALUES ('" + UnterhaltungsID + "', '" + NachrichtenID
					+ "');";
			int anzahlBetroffenerZeilen = state.executeUpdate(sqlquery);
			if (anzahlBetroffenerZeilen > 0)
				erfolgreich = true;
			else
				erfolgreich = false;
		} catch (Exception e) {
			e.printStackTrace();
			erfolgreich = false;
		}
		return erfolgreich;
	}

	public boolean hashtagEinerNachrichtZuordnen(int HashtagID, int NachrichtID) {
		boolean erfolgreich = true;
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO NachrichtHashtag (`NachrichtId`, `HashtagId`) VALUES ('" + NachrichtID + "', '" + HashtagID + "');";
			int anzahlBetroffenerZeilen = state.executeUpdate(sqlquery);
			if (anzahlBetroffenerZeilen > 0)
				erfolgreich = true;
			else
				erfolgreich = false;
		} catch (Exception e) {
			e.printStackTrace();
			erfolgreich = false;
		}
		return erfolgreich;
	}
}