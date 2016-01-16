package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.tellme.shared.bo.BusinessObject.eSichtbarkeit;
import de.hdm.tellme.shared.bo.Nutzer.eStatus;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Mapper-Klasse, die Nutzer-Objekte in der Datenbank abbildet. Diese enthält
 * Methoden zum Anlegen, Aktualisieren, Entfernen und Suchen von Objekten.
 * 
 * @author Nicole Reum
 */

public class UnterhaltungMapper {

	/**
	 * TODO
	 */
	private static UnterhaltungMapper unterhaltungMapper = null;

	/**
	 * TODO
	 */
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
	/**
	 * TODO
	 * 
	 * @param unterHaltungsTyp
	 * @return
	 */
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

	/**
	 * TODO
	 * 
	 * @param u
	 * @return
	 */
	public boolean aktualisieren(Unterhaltung u) {
		boolean erfolgreich = false;

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE `db_tellme`.`Unterhaltung` SET `Sichtbarkeit`='"
					+ u.getSichtbarkeit()
					+ "', `Typ`='"
					+ u.getUnterhaltungstyp().ordinal()
					+ "' WHERE `Id`='"
					+ u.getId() + "';";
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
	 * TODO
	 * 
	 * @param unterhaltungsID
	 * @return
	 */
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
	/**
	 * TODO
	 * 
	 * @param teilnehmerID
	 * @return
	 */
	public Vector<Unterhaltung> alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(
			int teilnehmerID) {
		Vector<Unterhaltung> alleUnterhaltungen = new Vector<Unterhaltung>();

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM NutzerUnterhaltung INNER JOIN Unterhaltung ON NutzerUnterhaltung.UnterhaltungId = Unterhaltung.Id JOIN Nutzer ON NutzerId = Nutzer.Id WHERE NutzerId = '"
							+ teilnehmerID
							+ "' AND NutzerUnterhaltung.Sichtbarkeit = 1 AND Unterhaltung.Sichtbarkeit = 1 AND Nutzer.Status = '"
							+ eStatus.aktiv.ordinal() + "'");

			while (rs.next()) {
				Unterhaltung u = new Unterhaltung();
				u.setId(rs.getInt("Unterhaltung.Id"));
				u.setSichtbarkeit(1);
				int typ = rs.getInt("Unterhaltung.Typ");
				u.setUnterhaltungstyp(Unterhaltung.eUnterhaltungsTyp.values()[typ]);
				u.setErstellungsDatum(rs
						.getTimestamp("Unterhaltung.ErstellungsDatum"));
				alleUnterhaltungen.add(u);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alleUnterhaltungen;
	}

	/**
	 * TODO
	 * 
	 * @param UnterhaltungsID
	 * @param TeilnehmerID
	 * @return
	 */
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

	/**
	 * TODO
	 * 
	 * @param UnterhaltungsID
	 * @param TeilnehmerID
	 * @param Sichtbarkeit
	 * @return
	 */
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
					+ "' AND `UnterhaltungId`='" + UnterhaltungsID + "';";
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
	 * TODO
	 * 
	 * @param nachricht
	 * @return
	 */
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
				uH.setSichtbarkeit(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uH;
	}

	// @Deprecated: name... use alleUnterhaltungenFuerAktivenTeilnehmer
	@Deprecated
	/**
	 * TODO Deprecated weil nichtmehr verwendet. Kann man löschen??
	 * @param meineId
	 * @return
	 */
	public Vector<Integer> meineUnterhaltungen(int meineId) {
		Vector<Integer> meineUnterhaltungen = new Vector<Integer>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NutzerUnterhaltung WHERE NutzerId = '"
					+ meineId + "' AND Sichtbarkeit = 1";
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				meineUnterhaltungen.add(rs.getInt("UnterhaltungId"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meineUnterhaltungen;
	}

	/**
	 * TODO
	 * 
	 * @param unterhaltungsId
	 * @return
	 */
	public Unterhaltung gibNachrichtenIdsZuUnterhaltungsId(
			Integer unterhaltungsId) {
		Unterhaltung u = new Unterhaltung();
		Vector<Nachricht> meineNachrichten = new Vector<Nachricht>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NachrichtUnterhaltung INNER JOIN Unterhaltung ON NachrichtUnterhaltung.UnterhaltungId = Unterhaltung.Id INNER JOIN Nachricht ON NachrichtUnterhaltung.NachrichtId = Nachricht.Id WHERE NachrichtUnterhaltung.UnterhaltungId = '"
					+ unterhaltungsId
					+ "' AND Unterhaltung.Sichtbarkeit = 1 ORDER BY Nachricht.ErstellungsDatum DESC;";
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				u.setId(rs.getInt("NachrichtUnterhaltung.UnterhaltungId"));
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("NachrichtUnterhaltung.NachrichtId"));
				nA.setSenderId(rs.getInt("Nachricht.AutorId"));
				nA.setErstellungsDatum(rs
						.getTimestamp("Nachricht.ErstellungsDatum"));
				nA.setText(rs.getString("Nachricht.Text"));
				nA.setSichtbarkeit(1);
				meineNachrichten.add(nA);

			}
			u.setAlleNachrichten(meineNachrichten);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	/**
	 * TODO
	 * 
	 * @param unterhaltungsId
	 * @return
	 */
	public Vector<Integer> gibTeilnehmerFuerUnterhaltung(int unterhaltungsId) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Integer> teilnehmer = new Vector<Integer>();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NutzerUnterhaltung JOIN Nutzer ON NutzerUnterhaltung.NutzerId = Nutzer.Id WHERE UnterhaltungId = '"
					+ unterhaltungsId
					+ "' AND Nutzer.Status = '"
					+ eStatus.aktiv.ordinal() + "' AND Sichtbarkeit = 1;";
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				teilnehmer.add(rs.getInt("NutzerId"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teilnehmer;
	}

	/**
	 * TODO
	 * 
	 * @param unterhaltungsId
	 * @param teilnehmerId
	 * @return
	 */
	public boolean ueberpruefeObTeilnehmerInaktivInUnterhaltung(
			int unterhaltungsId, int teilnehmerId) {
		boolean vorhanden = false;
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NutzerUnterhaltung JOIN Nutzer ON NutzerUnterhaltung.NutzerId = Nutzer.Id WHERE UnterhaltungId = '"
					+ unterhaltungsId
					+ "' AND NutzerId = '"
					+ teilnehmerId
					+ "' AND Nutzer.Status = '"
					+ eStatus.aktiv.ordinal()
					+ "';";

			int anzahlBetroffenerZeilen = state.executeUpdate(sql);
			if (anzahlBetroffenerZeilen >= 0)
				vorhanden = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vorhanden;
	}

	/**
	 * TODO
	 * 
	 * @param unterhaltungsID
	 * @param nutzerID
	 * @return
	 */
	public boolean istNutzerTeilnehmer(int unterhaltungsID, int nutzerID) {
		boolean vorhanden = false;
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT count(*) as Anzahl FROM NutzerUnterhaltung JOIN Nutzer ON NutzerUnterhaltung.NutzerId = Nutzer.Id WHERE UnterhaltungId = "
					+ unterhaltungsID
					+ " AND NutzerId = "
					+ nutzerID
					+ " AND Nutzer.Status = '" + eStatus.aktiv.ordinal() + "';";

			ResultSet rs = state.executeQuery(sql);
			int ergebnis = -1;
			while (rs.next()) {
				ergebnis = (rs.getInt("Anzahl"));
			}

			if (ergebnis > 0)
				vorhanden = true;
			else
				vorhanden = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vorhanden;
	}

}