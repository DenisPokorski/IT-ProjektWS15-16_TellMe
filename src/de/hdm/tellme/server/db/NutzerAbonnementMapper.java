package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Nutzer.eStatus;

/**
 * Mapper-Klasse, die NutzerAbonnement-Objekte in der relationalen Datenbank
 * abbildet. Diese enthält Methoden zum Anlegen, Aktualisieren, Entfernen und
 * Suchen von Objekten. Durch die Mapper-Klassen können Objekte in
 * Datenbankstrukturen und Datenbankstrukturen in Objekte umgewandelt.
 * 
 * @author Nicole Reum
 */

public class NutzerAbonnementMapper {
	/**
	 * Damit die Klasse NutzerAbonnementMapper nur einmal während der Laufzeit
	 * des Programms bestehen kann, muss man sie als Singleton darstellen, dies
	 * geschieht durch die Referenz <code>static</code>.
	 */

	private static NutzerAbonnementMapper nutzerAbonnementMapper = null;

	/**
	 * Damit der NutzerAbonnementMapper nicht durch <code>new</code> neue
	 * Instanzen in der Klasse erzeugen kann, wird der Konstruktor mit
	 * <code>protected</code> geschützt.
	 */
	protected NutzerAbonnementMapper() {

	}

	/**
	 * Die statische Methode wird über NutzerAbonnementMapper
	 * nutzerAbonnementMapper() aufgerufen. Diese überprüft, dass nur eine
	 * Instanz von NutzerAbonnementMapper besteht.
	 */

	public static NutzerAbonnementMapper nutzerAbonnementMapper() {
		if (nutzerAbonnementMapper == null) {
			nutzerAbonnementMapper = new NutzerAbonnementMapper();
		}
		return nutzerAbonnementMapper;
	}

	/**
	 * Laden der Nutzer, die für einen Nutzer bereits abonniert sind. Hierfür
	 * wird die Abonnement.NachId in der NutzerAbonnement-Tabelle mit der
	 * AbonnentBenutzer.VonId verglichen um zu erkennen, welche Nutzer bereits
	 * abonniert sind.
	 * 
	 * @param nutzer
	 * @return Ein Vektor mit Nutzer-Objekten, dass alle abonnierten Nutzer
	 *         eines Nutzers anzeigt.
	 */

	public Vector<Nutzer> ladeAbonnierendeNutzerListe(int nutzer) {
		Connection con = DatenbankVerbindung.connection();

		Vector<Nutzer> NutzerListe = new Vector<Nutzer>();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT AbonnentBenutzer.NachId, Nutzer.Id, Nutzer.Vorname, Nutzer.Nachname, Nutzer.Mailadresse, Nutzer.Status, AbonnentBenutzer.ErstellungsDatum FROM AbonnentBenutzer LEFT JOIN Nutzer ON AbonnentBenutzer.NachId = Nutzer.Id Where AbonnentBenutzer.VonId = '"
							+ nutzer
							+ "' AND Nutzer.Status = '"
							+ eStatus.aktiv.ordinal() + "' ORDER BY AbonnentBenutzer.ErstellungsDatum;");

			while (rs.next()) {
				Nutzer na = new Nutzer();
				na.setId(rs.getInt("Id"));
				na.setVorname(rs.getString("Vorname"));
				na.setNachname(rs.getString("Nachname"));
				na.setMailadresse(rs.getString("Mailadresse"));
				na.setErstellungsDatum(rs
						.getTimestamp("AbonnentBenutzer.ErstellungsDatum"));
				NutzerListe.add(na);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return NutzerListe;
	}

	/**
	 * Dieser Mapper soll ein Nutzerabonnement löschen. Dies wird realisiert,
	 * indem die Id vom ausgehenden Nutzer mit dem mitgegebenen Nutzer-Objekt
	 * abgeglichen wird und das jeweilige NutzerAbonnement-Objekt aus der
	 * Datenbank gelöscht wird.
	 * 
	 * @param _vonId
	 * @param _nutzerDeabonieren
	 */

	public void loescheNutzeraboById(int _vonId, Nutzer _nutzerDeabonieren) {

		Connection con = DatenbankVerbindung.connection();

		try {
			Statement state = con.createStatement();
			state.executeUpdate("DELETE FROM AbonnentBenutzer WHERE VonId=' "
			+ _vonId + " ' AND  NachId='" + _nutzerDeabonieren.getId() + "';");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dieser Mapper soll ein Nutzerabonnement erstellen. Dies wird realisiert,
	 * indem die Id vom ausgehenden Nutzer (vonId) mit dem mitgegebenen
	 * Nutzer-Objekt (der Nutzer, der abonniert werden soll) abgeglichen wird
	 * und das jeweilige NutzerAbonnement-Objekt in der Datenbank erstellt wird.
	 * 
	 * @param vonId
	 * @param _nutzer
	 */
	public void nutzerAboErstellen(int vonId, Nutzer _nutzer) {

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO AbonnentBenutzer(VonId, NachId, ErstellungsDatum) VALUES ('"
					+ vonId
					+ "','"
					+ _nutzer.getId()
					+ "', '"
					+ DateHelperClass.getCurrentTime() + "');");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dieser Mapper soll alle bereits abonnierten Nutzer vom angemeldeten
	 * Nutzer anzeigen. Es wird der Parameter <code>meineId</code> mitgegeben
	 * und ein Vektor aus Integer-Werten zurückgegeben.
	 * 
	 * @param meineId
	 * @return Ein Vektor mit Integer-Objekten, dass die NutzerId's beinhaltet,
	 *         die zu den Nutzern gehören, die der angemeldete Nutzer bereits
	 *         abonniert hat.
	 */
	public Vector<Integer> vonMirabonnierteNutzerIds(int meineId) {
		Vector<Integer> abonnierteNutzerIds = new Vector<Integer>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM AbonnentBenutzer JOIN Nutzer ON AbonnentBenutzer.VonId = Nutzer.Id WHERE VonId = '"
					+ meineId
					+ "' AND Nutzer.Status = '"
					+ eStatus.aktiv.ordinal() + "';";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				abonnierteNutzerIds.add(rs.getInt("NachId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return abonnierteNutzerIds;
	}

	public Vector<Nutzer> followerEinesNutzers(int nutzerId) {
		Vector<Nutzer> followerEinesNutzersListe = new Vector<Nutzer>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "SELECT * FROM AbonnentBenutzer JOIN Nutzer ON VonId = Nutzer.Id WHERE NachId = '"
					+ nutzerId
					+ "' AND Nutzer.Status = '"
					+ eStatus.aktiv.ordinal()
					+ "' ORDER BY AbonnentBenutzer.ErstellungsDatum DESC;";
			ResultSet rs = state.executeQuery(sqlquery);
			while (rs.next()) {
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("Nutzer.Id"));
				n.setVorname(rs.getString("Nutzer.Vorname"));
				n.setNachname(rs.getString("Nutzer.Nachname"));
				n.setMailadresse(rs.getString("Nutzer.Mailadresse"));
				n.setErstellungsDatum(rs.getTimestamp("AbonnentBenutzer.ErstellungsDatum"));
				followerEinesNutzersListe.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return followerEinesNutzersListe;
	}
}
