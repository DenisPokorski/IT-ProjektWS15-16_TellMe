package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.tellme.shared.bo.BusinessObject.eSichtbarkeit;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Nutzer.eStatus;

/**
 * Mapper-Klasse, die Nachricht-Objekte in der Datenbank abbildet. Diese enth�lt
 * Methoden zum Anlegen, Aktualisieren und Entfernen von Objekten.
 * 
 * @author Nicole Reum
 */

public class NachrichtMapper {

	/**
	 * Damit die Klasse NachrichtMapper nur einmal während der Laufzeit des
	 * Programms bestehen kann, muss man sie als Singleton darstellen, dies
	 * geschieht durch die Referenz <code>static</code>.
	 */
	private static NachrichtMapper nachrichtMapper = null;

	/**
	 * Damit der NachrichtMapper nicht durch <code>new</code> neue Instanzen in
	 * der Klasse erzeugen kann, wird der Konstruktor mit <code>protected</code>
	 * geschützt.
	 */
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
	 * 
	 * @param n
	 * @return boolean vom wert false, wenn die Anzahl der betroffenen Zeilen <0
	 *         ist, und true wenn Zeilen bearbeitet wurden.
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
	 * 
	 * 
	 * Da Nachrichten nicht gelöscht werden, sondern nur die Sichtbarkeit von
	 * Nachrichten beim entfernen umgestellt wird, wird nur die Sichtbarkeit
	 * einer Nachricht auf 0 geupdatet.
	 * 
	 * @param nachrichtenID
	 * @return boolean, false, wenn die Sichtbarkeit nicht geupdatet wurde.
	 *         True, wenn die Sichtbarkeit geupdatet wurde.
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

	/**
	 * In dieser Methode wird die Zuordnung eines Hashtags an eine Nachricht
	 * gelöscht. Das bedeuted, dass fals ein Hashtag von einer Nachricht
	 * entfernt werden soll, wird dies über diesen Mapper ausgeführt. Hierzu
	 * werden sowohl die HashtagId, als auch die NachrichtId übergeben und die
	 * jeweilige Verbindung in der NachrichtHashtagTabelle gelöscht.
	 * 
	 * @param hashtagid
	 * @param nachrichtid
	 */
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

	/**
	 * Diese Methode löscht alle Hashtag-Zuordnungen eines Hashtags.
	 * 
	 * @param hashtagid
	 */
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

	/**
	 * Die Nachricht wird einer Unterhaltung zugeordnet
	 * 
	 * @param NachrichtenID
	 * @param UnterhaltungsID
	 * @return
	 */
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

	/**
	 * Der Hashtag wird einer NAchricht zugeordnet
	 * 
	 * @param HashtagID
	 * @param NachrichtID
	 * @return
	 */
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

	/**
	 * Es werden alle Nachrichten für eine Unterhaltung ausgelesen
	 * 
	 * @param unterhaltungsID
	 * @return
	 */
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
	
	public Vector<Nachricht> gibAlleSichtbarenNachrichten() {
		Connection con = DatenbankVerbindung.connection();
		Vector<Nachricht> Nachrichten = new Vector<Nachricht>();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM Nachricht WHERE Nachricht.Sichtbarkeit = 1 ";
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

	public Vector<Nachricht> gibAlleNachrichtenVonUnterhaltungReportMitZeitraum(
			int unterhaltungsID, Timestamp vonDate, Timestamp bisDate) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Nachricht> Nachrichten = new Vector<Nachricht>();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM NachrichtUnterhaltung JOIN Nachricht ON NachrichtUnterhaltung.NachrichtId = Nachricht.Id JOIN Nutzer ON Nachricht.AutorId = Nutzer.Id WHERE NachrichtUnterhaltung.UnterhaltungId = '"
					+ unterhaltungsID
					+ "' AND Nutzer.Status = '"
					+ eStatus.aktiv.ordinal()
					+ "' AND Nachricht.ErstellungsDatum BETWEEN '"
					+ vonDate
					+ "' AND '"
					+ bisDate
					+ "' ORDER BY Nachricht.ErstellungsDatum DESC";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nutzer n = new Nutzer();
				Nachricht nA = new Nachricht();
				n.setVorname(rs.getString("Nutzer.Vorname"));
				n.setNachname(rs.getString("Nutzer.Nachname"));
				n.setId(rs.getInt("Nutzer.Id"));
				nA.setId(rs.getInt("Nachricht.Id"));
				nA.setText(rs.getString("Nachricht.Text"));
				nA.setErstellungsDatum(rs
						.getTimestamp("Nachricht.ErstellungsDatum"));
				nA.setSichtbarkeit(rs.getInt("Nachricht.Sichtbarkeit"));
				nA.setSenderId(rs.getInt("Nachricht.AutorId"));
				nA.setSender(n);
				Nachrichten.add(nA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Nachrichten;
	}

	public Vector<Nachricht> gibAlleNachrichtenVonUnterhaltungReport(
			int unterhaltungsID) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Nachricht> Nachrichten = new Vector<Nachricht>();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM NachrichtUnterhaltung JOIN Nachricht ON NachrichtUnterhaltung.NachrichtId = Nachricht.Id JOIN Nutzer ON Nachricht.AutorId = Nutzer.Id WHERE NachrichtUnterhaltung.UnterhaltungId = '"
					+ unterhaltungsID
					+ "' AND Nutzer.Status = '"
					+ eStatus.aktiv.ordinal()
					+ "' ORDER BY Nachricht.ErstellungsDatum DESC";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Nachricht.Id"));
				nA.setText(rs.getString("Nachricht.Text"));
				nA.setErstellungsDatum(rs
						.getTimestamp("Nachricht.ErstellungsDatum"));
				nA.setSichtbarkeit(rs.getInt("Nachricht.Sichtbarkeit"));
				nA.setSenderId(rs.getInt("Nachricht.AutorId"));

				Nachrichten.add(nA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Nachrichten;
	}

	/**
	 * Es werden alle Nachrichten mit einem bestimmten Hashtag asugegeben
	 * 
	 * @param integer
	 * @return
	 */
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

	/**
	 * Gibt alle NAchrchten in einem bestimmten Zeitraum eines bestimmten Nutzers aus.
	 * 
	 * @param vonDatum
	 * @param bisDatum
	 * @param AutorId
	 * @return
	 */
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

	public Vector<Nachricht> gibAlleNachrichtenVonAlleNutzern(int AutorId,
			int NachrichtId, int HashtagId, Timestamp vonDatum,
			Timestamp bisDatum) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Nachricht> alleNachrichtenalleNutzer = new Vector<Nachricht>();
		try {
			Statement state = con.createStatement();
			String sqlquery = "";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Id"));
				nA.setText("Text");
				nA.setErstellungsDatum(rs.getTimestamp("Erstellungsdatum"));
				nA.setSenderId(rs.getInt("AutoId"));
				nA.setSichtbarkeit(rs.getInt("Sichtbarkeit"));

				// nA.setVerknuepfteHashtags(verknuepfteHashtags);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleNachrichtenalleNutzer;
	}

}