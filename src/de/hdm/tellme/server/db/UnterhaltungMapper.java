package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.tellme.shared.bo.BusinessObject.eSichtbarkeit;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Nutzer.eStatus;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Mapper-Klasse, die Nutzer-Objekte in der Datenbank abbildet. Diese enthält
 * Methoden zum Anlegen, Aktualisieren, Entfernen und Suchen von Objekten.
 * 
 * @author Nicole Reum
 */

public class UnterhaltungMapper {

	/**
	 * Damit die Klasse UnterhaltunMapper nur einmal während der Laufzeit des
	 * Programms bestehen kann, muss man sie als Singleton darstellen, dies
	 * geschieht durch die Referenz <code>static</code>.
	 */
	private static UnterhaltungMapper unterhaltungMapper = null;

	/**
	 * Damit der NutzerAbonnementMapper nicht durch <code>new</code> neue
	 * Instanzen in der Klasse erzeugen kann, wird der Konstruktor mit
	 * <code>protected</code> geschützt.
	 */

	protected UnterhaltungMapper() {
	}

	/**
	 * Die statische Methode wird über UnterhaltungMapper unterhaltungMapper
	 * aufgerufen. Diese überprüft, dass nur eine Instanz von UnterhaltungMapper
	 * besteht.
	 */

	public static UnterhaltungMapper unterhaltungMapper() {
		if (unterhaltungMapper == null) {
			unterhaltungMapper = new UnterhaltungMapper();
		}
		return unterhaltungMapper;
	}

	/**
	 * Diese Methode legt eine Unterhaltung an, gibt eine ID neuer Unterhaltung
	 * zurück oder -1 wenn nicht erfolgreich
	 * 
	 * @param unterHaltungsTyp
	 * @return
	 */
	public int anlegen(Unterhaltung.eUnterhaltungsTyp unterHaltungsTyp) {
		int sichtbarkeit = 1;
		int ergebnis = -1;

		Connection con = DatenbankVerbindung.connection();
		try {
			PreparedStatement prepState = con.prepareStatement(
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
	 * Es wird eine Unterhaltung aus sichtbar oder Unsichtbar gesetzt
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
	 * Es wird eine Unterhaltung über eine ID gelöscht
	 * 
	 * @param unterhaltungsID
	 * @return
	 */
	public boolean loescheUnterhaltungAnhandID(int unterhaltungsID) {
		boolean erfolgreich = false;

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE `Unterhaltung` SET `Sichtbarkeit`='0' WHERE `Id`='"+ unterhaltungsID + "';";
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
	 * Diese Methode gibt eine Liste an Unterhaltungen zurück, bei dem der
	 * Nutzer mit der Nutzerid <teilnehmerID> als aktiver Teilnehmer (Eintrag in
	 * NutzerUnterhaltung ist Vorhanden und Sichtbar) agiert
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
			ResultSet rs = state.executeQuery("SELECT * FROM NutzerUnterhaltung INNER JOIN Unterhaltung ON NutzerUnterhaltung.UnterhaltungId = Unterhaltung.Id JOIN Nutzer ON NutzerId = Nutzer.Id WHERE NutzerId = '"
				+ teilnehmerID + "' AND NutzerUnterhaltung.Sichtbarkeit = 1 AND Unterhaltung.Sichtbarkeit = 1 AND Nutzer.Status = '"
				+ eStatus.aktiv.ordinal() + "'");

			while (rs.next()) {
				Unterhaltung u = new Unterhaltung();
				u.setId(rs.getInt("Unterhaltung.Id"));
				u.setSichtbarkeit(1);
				int typ = rs.getInt("Unterhaltung.Typ");
				u.setUnterhaltungstyp(Unterhaltung.eUnterhaltungsTyp.values()[typ]);
				u.setErstellungsDatum(rs.getTimestamp("Unterhaltung.ErstellungsDatum"));
				alleUnterhaltungen.add(u);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleUnterhaltungen;
	}

	public Vector<Unterhaltung> alleUnterhaltungenFuerAutorOhneNachrichten(int AutorId) {
		
		Vector<Unterhaltung> alleUnterhaltungen = new Vector<Unterhaltung>();
		Connection con = DatenbankVerbindung.connection();
		
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Nachricht JOIN NachrichtUnterhaltung ON Nachricht.Id = NachrichtUnterhaltung.NachrichtId JOIN Unterhaltung ON NachrichtUnterhaltung.UnterhaltungId = Unterhaltung.Id WHERE Nachricht.AutorId = '"
					+ AutorId + "' ORDER BY Nachricht.ErstellungsDatum DESC");

			while (rs.next()) {
				Unterhaltung u = new Unterhaltung();
				u.setId(rs.getInt("Unterhaltung.Id"));
				u.setSichtbarkeit(1);
				int typ = rs.getInt("Unterhaltung.Typ");
				u.setUnterhaltungstyp(Unterhaltung.eUnterhaltungsTyp.values()[typ]);
				u.setErstellungsDatum(rs.getTimestamp("Unterhaltung.ErstellungsDatum"));
				alleUnterhaltungen.add(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleUnterhaltungen;
	}

	public Vector<Unterhaltung> alleUnterhaltungenOhneNachrichtenNachDatumLetzterNachricht() {
		Vector<Unterhaltung> alleUnterhaltungen = new Vector<Unterhaltung>();

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Nachricht JOIN NachrichtUnterhaltung ON Nachricht.Id = NachrichtUnterhaltung.NachrichtId JOIN Unterhaltung ON NachrichtUnterhaltung.UnterhaltungId = Unterhaltung.Id ORDER BY Nachricht.ErstellungsDatum DESC");

			while (rs.next()) {
				Unterhaltung u = new Unterhaltung();
				u.setId(rs.getInt("Unterhaltung.Id"));
				u.setSichtbarkeit(1);
				int typ = rs.getInt("Unterhaltung.Typ");
				u.setUnterhaltungstyp(Unterhaltung.eUnterhaltungsTyp.values()[typ]);
				u.setErstellungsDatum(rs.getTimestamp("Unterhaltung.ErstellungsDatum"));
				alleUnterhaltungen.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleUnterhaltungen;
	}

	public Vector<Unterhaltung> alleUnterhaltungenOhneNachrichten() {
		Vector<Unterhaltung> alleUnterhaltungen = new Vector<Unterhaltung>();

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Unterhaltung");

			while (rs.next()) {
				Unterhaltung u = new Unterhaltung();
				u.setId(rs.getInt("Unterhaltung.Id"));
				u.setSichtbarkeit(1);
				int typ = rs.getInt("Unterhaltung.Typ");
				u.setUnterhaltungstyp(Unterhaltung.eUnterhaltungsTyp.values()[typ]);
				u.setErstellungsDatum(rs.getTimestamp("Unterhaltung.ErstellungsDatum"));
				alleUnterhaltungen.add(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alleUnterhaltungen;
	}

	/**
	 * Diese Methode für einer Unterhaltung einen Teilnehmer hinzu.
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
	 * Die Teilnehmer werden für ene Unterhaltung aktualisiert.
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
	 * Diese Methode zeigt von einer jeweilgen NachrichtId die entsprechende
	 * Unterhaltung an
	 * 
	 * @param nachricht
	 * @return
	 */
	public Unterhaltung selektiereUnterhaltungenVonNachrichtId(Nachricht nachricht) {
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
				uH.setErstellungsDatum(rs.getTimestamp("Unterhaltung.Erstellungsdatum"));
				uH.setUnterhaltungstyp(Unterhaltung.eUnterhaltungsTyp.values()[typ]);
				uH.setSichtbarkeit(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uH;
	}

	/**
	 * Diese Methode zeigt alle aktiven Teilnehmer einer Unterhaltung an
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

	public Vector<Unterhaltung> gibAlleDummyUnterhaltungenMitDummyTeilnehmer() {
		Connection con = DatenbankVerbindung.connection();
		Vector<Unterhaltung> unterhaltungen = new Vector<Unterhaltung>();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NutzerUnterhaltung Where Sichtbarkeit = 1;";
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {

				Unterhaltung dummyUnterhaltung = new Unterhaltung();
				dummyUnterhaltung.setId(rs.getInt("UnterhaltungId"));

				Vector<Nutzer> dummyTeilnehmerVector = new Vector<Nutzer>();
				Nutzer dummyTeilnehmer = new Nutzer();
				dummyTeilnehmer.setId(rs.getInt("NutzerId"));
				dummyTeilnehmerVector.add(dummyTeilnehmer);

				dummyUnterhaltung.setTeilnehmer(dummyTeilnehmerVector);
				unterhaltungen.add(dummyUnterhaltung);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unterhaltungen;
	}

	public Vector<Unterhaltung> gibAlleDummyUnterhaltungenMitDummyNachrichten() {
		Connection con = DatenbankVerbindung.connection();
		Vector<Unterhaltung> unterhaltungen = new Vector<Unterhaltung>();
		try {
			Statement state = con.createStatement();
			String sql = "SELECT * FROM NachrichtUnterhaltung ORDER BY NachrichtId DESC";
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {

				Unterhaltung dummyUnterhaltung = new Unterhaltung();
				dummyUnterhaltung.setId(rs.getInt("UnterhaltungId"));

				Vector<Nachricht> dummyNachrichtVector = new Vector<Nachricht>();
				Nachricht dummyNachricht = new Nachricht();
				dummyNachricht.setId(rs.getInt("NachrichtId"));
				dummyNachrichtVector.add(dummyNachricht);
				dummyUnterhaltung.setAlleNachrichten(dummyNachrichtVector);
				unterhaltungen.add(dummyUnterhaltung);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unterhaltungen;
	}

	/**
	 * Diese Methode gibt an, ob ein Nutzer in einer Unterhaltung teilnimmt oder
	 * nicht.
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

	public int anzahlAktiverTeilnehmer(int unterhaltungsID) {
		int anzahl = 0;
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT COUNT(*) AS Anzahl FROM NutzerUnterhaltung WHERE UnterhaltungId = '"
							+ unterhaltungsID
							+ "' AND Sichtbarkeit = '"
							+ eSichtbarkeit.Sichtbar.ordinal() + "'");
			while (rs.next()) {
				anzahl = rs.getInt("Anzahl");
			}
		} catch (Exception e) {
		}
		return anzahl;
	}
}