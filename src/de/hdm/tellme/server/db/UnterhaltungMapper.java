package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import com.google.appengine.api.prospectivesearch.ProspectiveSearchPb.SubscriptionRecord.State;

import de.hdm.tellme.server.db.DatenbankVerbindung;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

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

	public void anlegen(Timestamp ts, int unterhaltungsTyp) {

		int sichtbarkeit = 1;
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Unterhaltung (Sichtbarkeit, ErstellungsDatum, Typ) VALUES ("
					+ "'"
					+ sichtbarkeit
					+ "','"
					+ ts
					+ "','"
					+ unterhaltungsTyp + "')";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int unterhaltungSelektieren(Timestamp ts) {

		Connection con = DatenbankVerbindung.connection();
		int unterhaltungId = 0;
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM Unterhaltung WHERE Erstellungsdatum ='"
							+ ts + "'");

			if (rs.next()) {
				unterhaltungId = rs.getInt("Id");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return unterhaltungId;
	}

	public void UnterhaltungNachrichtZuweisen(int unterhaltungsId,
			int nachrichtenId) {

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO `NachrichtUnterhaltung` (UnterhaltungId, NachrichtId) VALUES ('"
					+ unterhaltungsId + "','" + nachrichtenId + "')";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("antowrt zuweisen nid/uid" + nachrichtenId + " "
				+ unterhaltungsId);

	}

	public Vector<Nachricht> alleNachrichtenEinerUnterhaltung(int uId) {
		Connection con = DatenbankVerbindung.connection();

		Vector<Nachricht> nachrichtListe = new Vector<Nachricht>();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT N.Id, N.AutorId, N.ErstellungsDatum, N.Text, Nutzer.Vorname, Nutzer.Nachname FROM Nachricht AS N RIGHT JOIN (SELECT * FROM `NachrichtUnterhaltung` WHERE UnterhaltungId ='"
							+ 24
							+ "')AS A ON N.Id = A.NachrichtId LEFT JOIN Nutzer ON Nutzer.Id = N.AutorId");

			while (rs.next()) {
				Nachricht n = new Nachricht();
				n.setId(rs.getInt("Id"));
				// n.setSenderVorname(rs.getString("Vorname"));
				// n.setSenderNachname(rs.getString("Nachname"));
				n.setText(rs.getString("Text"));
				n.setErstellungsDatum(rs.getTimestamp("ErstellungsDatum"));
				nachrichtListe.add(n);
				System.out.println(rs.getString("Nachname"));

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" uid--> " + uId);

		return nachrichtListe;
	}

	public Vector<Unterhaltung> alleUnterhaltungen() {
		Connection con = DatenbankVerbindung.connection();

		Vector<Unterhaltung> unterhaltungListe = new Vector<Unterhaltung>();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM Unterhaltung WHERE Typ = 1"); // AND
																				// Sichtbarkeit
																				// =
																				// 1

			while (rs.next()) {
				Unterhaltung u = new Unterhaltung();
				u.setId(rs.getInt("Id"));
				// u.setTyp(rs.getInt("Typ"));
				u.setSichtbarkeit(rs.getInt("Sichtbarkeit"));

				unterhaltungListe.add(u);

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" unterhaltungsliste size--> "
				+ unterhaltungListe.size());

		return unterhaltungListe;
	}

	public Unterhaltung meineUnterhaltungen(int meineId) {
		Connection con = DatenbankVerbindung.connection();
		Unterhaltung u = new Unterhaltung();
		Vector<Nachricht> meineNachrichten = new Vector<Nachricht>();

		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM NachrichtUnterhaltung JOIN Nachricht ON NachrichtUnterhaltung.NachrichtId=Nachricht.Id WHERE Nachricht.AutorId = '"
					+ meineId
					+ "' AND Nachricht.Sichtbarkeit = 1 ORDER BY Nachricht.ErstellungsDatum DESC";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Nachricht.Id"));
				nA.setText(rs.getString("Nachricht.Text"));
				nA.setSenderId(rs.getInt("Nachricht.AutorId"));
				nA.setErstellungsDatum(rs
						.getTimestamp("Nachricht.ErstellungsDatum"));
				meineNachrichten.add(nA);

			}
			u.setAlleNachrichten(meineNachrichten);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public Unterhaltung oeffentlicheNachrichtenVonBenutzer(int id) {
		Connection con = DatenbankVerbindung.connection();
		Vector<Nachricht> OeffentlichenNachrichtenVonBenutzer = new Vector<Nachricht>();
		Unterhaltung u = new Unterhaltung();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM NachrichtUnterhaltung JOIN Nachricht ON NachrichtUnterhaltung.NachrichtId = Nachricht.Id JOIN Unterhaltung ON NachrichtUnterhaltung.UnterhaltungId = Unterhaltung.Id WHERE Nachricht.Sichtbarkeit = 1 AND Unterhaltung.Typ = 1 AND Nachricht.AutorId = '"
					+ id + "' ORDER BY Nachricht.ErstellungsDatum DESC";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nachricht nA = new Nachricht();
				nA.setId(rs.getInt("Nachricht.Id"));
				nA.setText(rs.getString("Nachricht.Text"));
				nA.setErstellungsDatum(rs
						.getTimestamp("Nachricht.ErstellungsDatum"));
				OeffentlichenNachrichtenVonBenutzer.add(nA);

			}
			u.setAlleNachrichten(OeffentlichenNachrichtenVonBenutzer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
}