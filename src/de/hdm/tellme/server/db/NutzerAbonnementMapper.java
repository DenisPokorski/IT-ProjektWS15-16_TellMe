package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.tellme.shared.bo.Nutzer;

public class NutzerAbonnementMapper {
	private static NutzerAbonnementMapper nutzerAbonnementMapper = null;

	protected NutzerAbonnementMapper() {

	}

	public static NutzerAbonnementMapper nutzerAbonnementMapper() {
		if (nutzerAbonnementMapper == null) {
			nutzerAbonnementMapper = new NutzerAbonnementMapper();
		}
		return nutzerAbonnementMapper;
	}

	public Vector<Nutzer> ladeAbonnierendeNutzerListe(int nutzer) {
		Connection con = DatenbankVerbindung.connection();

		Vector<Nutzer> NutzerListe = new Vector<Nutzer>();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT AbonnentBenutzer.NachId, Nutzer.Id, Nutzer.Vorname, Nutzer.Nachname, Nutzer.Mailadresse FROM AbonnentBenutzer LEFT JOIN Nutzer ON AbonnentBenutzer.NachId = Nutzer.Id Where AbonnentBenutzer.VonId = '"
							+ nutzer + "';");

			while (rs.next()) {
				Nutzer na = new Nutzer();
				na.setId(rs.getInt("Id"));
				na.setVorname(rs.getString("Vorname"));
				na.setNachname(rs.getString("Nachname"));
				na.setMailadresse(rs.getString("Mailadresse"));
				NutzerListe.add(na);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return NutzerListe;
	}

	public void loescheNutzeraboById(int vonId, int nachId) {

		Connection con = DatenbankVerbindung.connection();

		try {

			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM AbonnentBenutzer WHERE VonId=' "
					+ vonId + " ' AND  NachId='" + nachId + "';");
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector<Nutzer> alleNochNichtAbonnierteNutzerSelektieren(int id) {

		Vector<Nutzer> alleNutzerAuﬂerMeinNutzerListe = new Vector<Nutzer>();

		Connection con = DatenbankVerbindung.connection();
		int meineid = id;
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * From Nutzer LEFT JOIN  (SELECT * FROM AbonnentBenutzer WHERE AbonnentBenutzer.VonId = '"
							+ meineid + "' )AS A  ON Nutzer.Id  = A.NachId");

			while (rs.next()) {

				if ((rs.getInt("VonId")) == meineid) {

				} else if ((rs.getInt("Id")) == meineid) {

				} else {
					Nutzer n = new Nutzer();
					n.setId(rs.getInt("Id"));
					n.setVorname(rs.getString("Vorname"));
					n.setNachname(rs.getString("Nachname"));
					n.setMailadresse(rs.getString("Mailadresse"));
					alleNutzerAuﬂerMeinNutzerListe.addElement(n);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zur¸ckgeben
		return alleNutzerAuﬂerMeinNutzerListe;
	}

	public void nutzerAboErstellen(int vonId, int nachId) {

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO AbonnentBenutzer(VonId, NachId) VALUES ('"
					+ vonId + "','" + nachId + "');");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}