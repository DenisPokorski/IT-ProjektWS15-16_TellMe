package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.tellme.server.db.DatenbankVerbindung;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;
import de.hdm.tellme.shared.bo.BusinessObject.eSichtbarkeit;

/**
 * Mapper-Klasse, die Nutzer-Objekte in der Datenbank abbildet. Diese enth�lt
 * Methoden zum Anlegen, Aktualisieren, Entfernen und Suchen von Objekten.
 * 
 * @author Nicole Reum
 */

public class UnterhaltungMapper {
	private static UnterhaltungMapper unterhaltungMapper = null;

	protected UnterhaltungMapper() {
	}

	/**
	 * Die statische Methode wird �ber NutzerMapper nutzerMapper aufgerufen.
	 * Diese �berpr�ft, dass nur eine Instanz von NutzerMapper besteht.
	 */

	public static UnterhaltungMapper unterhaltungMapper() {
		if (unterhaltungMapper == null) {
			unterhaltungMapper = new UnterhaltungMapper();
		}
		return unterhaltungMapper;
	}

	// legt unterhaltung an, gibt id neuer unterhaltung zurück oder -1 wenn
	// nicht erfolgreich
	public int anlegen(Unterhaltung.eUnterhaltungsTyp unterHaltungsTyp) {
		int sichtbarkeit = 1;
		int ergebnis = -1;

		Connection con = DatenbankVerbindung.connection();
		try {
			PreparedStatement prepState = con
					.prepareStatement(
							"INSERT INTO Unterhaltung (Sichtbarkeit, ErstellungsDatum, Typ) VALUES (?,CURRENT_TIMESTAMP,?)",
							Statement.RETURN_GENERATED_KEYS);
			prepState.setInt(1, sichtbarkeit);
			prepState.setInt(2, unterHaltungsTyp.ordinal());

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

	public boolean loescheUnterhaltungAnhandID(int unterhaltungsID) {
		boolean erfolgreich = false;

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE `Unterhaltung` SET `Sichtbarkeit`='0' WHERE `Id`='"
					+ unterhaltungsID + "';";
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

	// Gibt eine Liste an Unterhaltungen zurück, bei dem der Nutzer mit der
	// Nutzerid <teilnehmerID>
	// als aktiver Teilnehmer (Eintrag in NutzerUnterhaltung ist Vorhanden und
	// Sichtbar) agiert
	public Vector<Unterhaltung> alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(
			int teilnehmerID) {
		Vector<Unterhaltung> alleUnterhaltungen = new Vector<Unterhaltung>();

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM NutzerUnterhaltung INNER JOIN Unterhaltung ON"
							+ " NutzerUnterhaltung.UnterhaltungId = Unterhaltung.Id Where NutzerID = "
							+ teilnehmerID
							+ " AND NutzerUnterhaltung.Sichtbarkeit = 1;");

			while (rs.next()) {
				Unterhaltung u = new Unterhaltung();
				u.setId(rs.getInt("Id"));
				u.setSichtbarkeit(1);
				int typ = rs.getInt("Typ");
				u.setUnterhaltungstyp(Unterhaltung.eUnterhaltungsTyp.values()[typ]);
				u.setErstellungsDatum(rs.getTimestamp("ErstellungsDatum"));
				alleUnterhaltungen.add(u);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alleUnterhaltungen;
	}

	public boolean teilnehmerHinzufuegen(int UnterhaltungsID, int TeilnehmerID) {
		boolean erfolgreich = true;
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO NutzerUnterhaltung (`NutzerId`, `UnterhaltungId`, `Sichtbarkeit`) VALUES ('"
					+ TeilnehmerID
					+ "', '"
					+ UnterhaltungsID
					+ "', '"
					+ eSichtbarkeit.Sichtbar.ordinal() + "');";
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

	public boolean teilnehmerAktualisieren(int UnterhaltungsID,
			int TeilnehmerID, int Sichtbarkeit) {
		boolean erfolgreich = true;
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE `NutzerUnterhaltung` SET `Sichtbarkeit`='"
					+ Sichtbarkeit
					+ "' WHERE `NutzerId`='"
					+ TeilnehmerID
					+ "' and`UnterhaltungId`='" + UnterhaltungsID + "';";
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

	public Unterhaltung selektiereUnterhaltungenVonNachrichtId(
			Nachricht nachricht) {
		Unterhaltung uH = new Unterhaltung();
		Connection con = DatenbankVerbindung.connection();

		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NachrichtUnterhaltung JOIN Unterhaltung ON NachrichtUnterhaltung.UnterhaltungId = Unterhaltung.Id WHERE NachrichtUnterhaltung.NachrichtId = '"
					+ nachricht.getId() + "' AND Sichtbarkeit = 1;";
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				int typ = rs.getInt("Unterhaltung.Typ");
				uH.setId(rs.getInt("Unterhaltung.Id"));
				uH.setErstellungsDatum(rs
						.getTimestamp("Unterhaltung.Erstellungsdatum"));
				uH.setUnterhaltungstyp(Unterhaltung.eUnterhaltungsTyp.values()[typ]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uH;
	}

	public Vector<Integer> meineUnterhaltungen(int meineId) {
		Vector<Integer> meineUnterhaltungen = new Vector<Integer>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NutzerUnterhaltung WHERE NutzerId = '"
					+ meineId + "' AND Sichtbarkeit = 1";
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				meineUnterhaltungen.add(rs.getInt("NutzerId"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meineUnterhaltungen;
	}

	public Unterhaltung gibNachrichtenIdsZuUnterhaltungsId(
			Integer unterhaltungsId) {
		Unterhaltung u = new Unterhaltung();
		Vector<Nachricht> meineNachrichten = new Vector<Nachricht>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NachrichtUnterhaltung INNER JOIN Unterhaltung ON NachrichtUnterhaltung.UnterhaltungId = Unterhaltung.Id INNER JOIN Nachricht ON NachrichtUnterhaltung.NachrichtId = Nachricht.Id WHERE NachrichtUnterhaltung.UnterhaltungId = '"
					+ unterhaltungsId
					+ "' AND Unterhaltung.Sichtbarkeit = 1 AND Nachricht.Sichtbarkeit = 1 ORDER BY Nachricht.ErstellungsDatum DESC;";
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				u.setId(rs.getInt("NachrichtUnterhaltung.UnterhaltungId"));
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("NachrichtUnterhaltung.NachrichtId"));
				nA.setSenderId(rs.getInt("Nachricht.AutorId"));
				nA.setErstellungsDatum(rs
						.getTimestamp("Nachricht.ErstellungsDatum"));
				nA.setText(rs.getString("Nachricht.Text"));
				meineNachrichten.add(nA);

			}
			u.setAlleNachrichten(meineNachrichten);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public Vector<Integer> gibTeilnehmerFuerUnterhaltung(int unterhaltungsId) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Integer> teilnehmer = new Vector<Integer>();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NutzerUnterhaltung WHERE UnterhaltungId = '"
					+ unterhaltungsId + "';";
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				teilnehmer.add(rs.getInt("NutzerId"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teilnehmer;
	}

}