package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import de.hdm.tellme.shared.bo.BusinessObject.eSichtbarkeit;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer.eStatus;

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
	public int anlegen(Nachricht n) {
		int ergebnis = -1;

		Connection con = DatenbankVerbindung.connection();
		try {
			PreparedStatement prepState = con
					.prepareStatement(
							"INSERT INTO Nachricht (`AutorId`, `Text`, `Sichtbarkeit`, `ErstellungsDatum`) VALUES (?, ?, ?, CURRENT_TIMESTAMP);",
							Statement.RETURN_GENERATED_KEYS);
			prepState.setInt(1, n.getSenderId());
			prepState.setString(2, n.getText());
			prepState.setInt(3, eSichtbarkeit.Sichtbar.ordinal());

			prepState.executeUpdate();

			ResultSet rs = prepState.getGeneratedKeys();
			if (rs.next()) {
				ergebnis = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ergebnis;
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

	public boolean aktualisieren(Nachricht n) {
		boolean erfolgreich = false;

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE Nachricht SET `Text`='" + n.getText()
					+ "', `Sichtbarkeit`='" + n.getSichtbarkeit()
					+ "' WHERE `Id`='" + n.getId() + "';";
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
	public boolean entfernen(int nachrichtenID) {
		boolean erfolgreich = false;

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE `Nachricht` SET `Sichtbarkeit`='0' WHERE `Id`='"
					+ nachrichtenID + "';";
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

	public void hashtagZuordnungLoeschen(int hashtagid, int nachrichtid) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			state.execute("DELETE FROM NachrichtHashtag WHERE HashtagId = '"
					+ hashtagid + "' AND NachrichtId = '" + nachrichtid + "';");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void alleHashtagZuordnungLoeschen(int hashtagid) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			state.execute("DELETE FROM NachrichtHashtag WHERE HashtagId = '"
					+ hashtagid + "';");
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
					.executeQuery(("SELECT * FROM Nachricht JOIN Nutzer ON Nachricht.AutorId = Nutzer.Id WHERE Nutzer.Status = '"
							+ eStatus.aktiv.ordinal()
							+ "'AND AutorId = '"
							+ meineId + "' AND Sichtbarkeit = 1 ORDER BY ErstellungsDatum DESC;"));
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Id"));
				nA.setText(rs.getString("Text"));
				nA.setSenderId(rs.getInt("AutorId"));
				nA.setErstellungsDatum(rs.getTimestamp("Erstellungsdatum"));
				nA.setSichtbarkeit(1);
				meineNachrichten.add(nA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return meineNachrichten;
	}

	public Vector<Nachricht> report1_1Mapper(int meineId, Date vonDatum,
			Date bisDatum) {
		Vector<Nachricht> alleNachrichtenVonBestimmtenNutzerInBestimmtemZeitraum = new Vector<Nachricht>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery(("SELECT * FROM Nachricht WHERE AutorId = '"
							+ meineId
							+ "' AND WHERE ErstellungsDatum BETWEEN '"
							+ vonDatum + "' AND '" + bisDatum + "' ORDER BY ErstellungsDatum DESC;"));
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

	public Vector<Nachricht> report1_2Mapper(Timestamp vonDatum,
			Timestamp bisDatum) {
		Vector<Nachricht> alleNachrichtenInBestimmtemZeitraum = new Vector<Nachricht>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery(("SELECT * FROM Nachricht  WHERE ErstellungsDatum BETWEEN '"
							+ vonDatum + "' AND '" + bisDatum + "' ORDER BY ErstellungsDatum DESC;"));
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
			ResultSet rs = state
					.executeQuery(("SELECT * FROM Nachricht WHERE AutorId = '"
							+ meineId + "' ORDER BY ErstellungsDatum DESC;"));
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
			ResultSet rs = state
					.executeQuery(("SELECT * FROM Nachricht ORDER BY ErstellungsDatum DESC;"));
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

	public boolean nachrichtEinerUnterhaltungZuordnen(int NachrichtenID,
			int UnterhaltungsID) {
		boolean erfolgreich = true;
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO NachrichtUnterhaltung (`UnterhaltungId`, `NachrichtId`) VALUES ('"
					+ UnterhaltungsID + "', '" + NachrichtenID + "');";
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
			String sqlquery = "INSERT INTO NachrichtHashtag (`NachrichtId`, `HashtagId`) VALUES ('"
					+ NachrichtID + "', '" + HashtagID + "');";
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

	public Vector<Nachricht> gibAlleNachrichtenVonUnterhaltung(
			int unterhaltungsID) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Nachricht> Nachrichten = new Vector<Nachricht>();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM NachrichtUnterhaltung JOIN Nachricht ON NachrichtUnterhaltung.NachrichtId = Nachricht.Id JOIN Nutzer ON Nachricht.AutorId = Nutzer.Id WHERE NachrichtUnterhaltung.UnterhaltungId = '"
					+ unterhaltungsID
					+ "' AND Nutzer.Status = '"
					+ eStatus.aktiv.ordinal()
					+ "' AND Nachricht.Sichtbarkeit = 1 ORDER BY Nachricht.ErstellungsDatum DESC";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Nachricht.Id"));
				nA.setText(rs.getString("Nachricht.Text"));
				nA.setErstellungsDatum(rs
						.getTimestamp("Nachricht.ErstellungsDatum"));
				nA.setSichtbarkeit(1);
				nA.setSenderId(rs.getInt("AutorId"));

				Nachrichten.add(nA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Nachrichten;
	}

	public Vector<Nachricht> gibNachrichtenVonHashtagId(Integer integer) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Nachricht> meineNachrichten = new Vector<Nachricht>();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM NachrichtHashtag INNER JOIN Nachricht ON NachrichtHashtag.NachrichtId = Nachricht.Id JOIN Nutzer ON Nachricht.AutorId = Nutzer.Id WHERE HashtagId = '"
					+ integer
					+ "' AND Nachricht.Sichtbarkeit = 1 AND Nutzer.Status = '"
					+ eStatus.aktiv.ordinal() + "'";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Nachricht.Id"));
				nA.setSenderId(rs.getInt("Nachricht.Id"));
				nA.setText(rs.getString("Nachricht.Text"));
				nA.setErstellungsDatum(rs
						.getTimestamp("Nachricht.ErstellungsDatum"));
				meineNachrichten.add(nA);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meineNachrichten;
	}

	public Vector<Nachricht> gibNachrichtVonNutzerIdMitDatum(
			Timestamp vonDatum, Timestamp bisDatum, int AutorId) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Nachricht> nachrichtenNachNutzerNachDatum = new Vector<Nachricht>();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM Nachricht WHERE ErstellungsDatum  BETWEEN '"
					+ vonDatum
					+ "' AND '"
					+ bisDatum
					+ "' AND AutorId ='"
					+ AutorId + "' ORDER BY ErstellungsDatum DESC";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Id"));
				nA.setText(rs.getString("Text"));
				nA.setSenderId(rs.getInt("AutoId"));
				nA.setErstellungsDatum(rs.getTimestamp("Erstellungsdatum"));
				nachrichtenNachNutzerNachDatum.add(nA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nachrichtenNachNutzerNachDatum;
	}
}