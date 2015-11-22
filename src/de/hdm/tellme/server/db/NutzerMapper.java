package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.Statement;

import de.hdm.tellme.server.db.DatenbankVerbindung;
import de.hdm.tellme.shared.bo.Nutzer;

public class NutzerMapper {
	private static NutzerMapper nutzerMapper = null;

	protected NutzerMapper() {

	}

	public static NutzerMapper nutzermapper() {
		if (nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}
		return nutzerMapper;
	}

	public void anlegen(Nutzer n) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Nutzer (Vornname, Nachname, Mailadresse, GoogleId) VALUES ("
					+ "'"
					+ n.getVorname()
					+ "','"
					+ n.getNachname()
					+ "','"
					+ n.getMailadresse() + "','" + n.getGoogleId() + "') ;";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void aktualisieren(Nutzer n) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();

			state.executeUpdate("UPDATE Nutzer SET Vorname = '"
					+ n.getVorname() + "', Nachname= '" + n.getNachname()
					+ "', " + "Mailadresse= '" + n.getMailadresse() + "' "
					+ "WHERE Id = '" + n.getId() + "';");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}