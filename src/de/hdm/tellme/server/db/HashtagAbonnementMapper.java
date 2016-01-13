package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.HashtagAbonnement;

/**
 * Mapper-Klasse, die HashtagAbonnement-Objekte in der relationalen Datenbank
 * abbildet. Diese enthält Methoden zum Anlegen, Aktualisieren, Entfernen und
 * Suchen von Objekten. Durch die Mapper-Klassen können Objekte in
 * Datenbankstrukturen und Datenbankstrukturen in Objekte umgewandelt.
 * 
 * @author Nicole Reum
 */

public class HashtagAbonnementMapper {
	/**
	 * Damit die Klasse HashtagAbonnementMapper nur einmal während der Laufzeit
	 * des Programms bestehen kann, muss man sie als Singleton darstellen, dies
	 * geschieht durch die Referenz <code>static</code>.
	 */
	private static HashtagAbonnementMapper hashtagAbonnementMapper = null;

	/**
	 * Damit der NutzerAbonnementMapper nicht durch <code>new</code> neue
	 * Instanzen in der Klasse erzeugen kann, wird der Konstruktor mit
	 * <code>protected</code> geschützt.
	 */
	protected HashtagAbonnementMapper() {

	}

	/**
	 * Die statische Methode wird über HashtagAbonnementMapper
	 * hashtagAbonnementMapper () aufgerufen. Diese überprüft, dass nur eine
	 * Instanz von HashtagAbonnementMapper besteht.
	 */

	public static HashtagAbonnementMapper hashtagAbonnementMapper() {
		if (hashtagAbonnementMapper == null) {
			hashtagAbonnementMapper = new HashtagAbonnementMapper();

		}
		return hashtagAbonnementMapper;
	}

	/**
	 * Laden der Hashtags, die für einen Nutzer bereits abonniert sind. Hierfür
	 * wird die NutzerId in der NutzerHashtag-Tabelle mit der HashtagId
	 * verglichen um zu erkennen, welche Hashtags bereits abonniert sind.
	 * 
	 * @param hashtag
	 * @return Ein Vektor mit Hashtag-Objekten, dass TODO
	 */
	public Vector<Hashtag> ladeAbonnierendeHashtagListe(int hashtag) {
		Connection con = DatenbankVerbindung.connection();

		Vector<Hashtag> HashtagListe = new Vector<Hashtag>();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT NutzerHashtag.HashtagId, Hashtag.Id, Hashtag.Schlagwort, Hashtag.ErstellungsDatum FROM NutzerHashtag LEFT JOIN Hashtag ON NutzerHashtag.HashtagId = Hashtag.Id Where NutzerHashtag.NutzerId = '"
							+ hashtag + "';");

			while (rs.next()) {
				Hashtag ha = new Hashtag();
				ha.setId(rs.getInt("Id"));
				ha.setSchlagwort(rs.getString("Schlagwort"));
				ha.setErstellungsDatum(rs.getTimestamp("ErstellungsDatum"));
				HashtagListe.add(ha);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("hashtag size liste: " + HashtagListe.size());
		return HashtagListe;
	}

	/**
	 * Dieser Mapper wird genutzt, um ein Hashtag-Abonnement in die Datenbank zu
	 * schreiben. Hierfür werden sowohl die NutzerId, als auch die HashtagId
	 * übergeben.
	 * 
	 * 
	 * @param NutzerId
	 * @param HashtagId
	 */
	public void hashtagAboErstellen(int NutzerId, int HashtagId) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO NutzerHashtag(NutzerId, HashtagId) VALUES ('"
					+ NutzerId + "','" + HashtagId + "');  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dieser Mapper wird genutzt, um ein Hashtag-Abonnement in die Datenbank zu
	 * schreiben. Hierfür werden sowohl die NutzerId, als auch die HashtagId
	 * übergeben.
	 * 
	 * 
	 * @param NutzerId
	 * @param HashtagId
	 */

	/**
	 * Dieser Mapper wird genutzt, um ein Hashtag-Abonnement in die Datenbank zu
	 * schreiben. Hierfür werden sowohl die NutzerId, als auch die HashtagId
	 * übergeben.
	 * 
	 * @param h
	 */
	public void anlegen(HashtagAbonnement h) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO NutzerHashtag (NutzerId, HashtagId) VALUES ("
					+ "'"
					+ h.getAbonnementErsteller().getId()
					+ "','"
					+ h.getHashtag().getId() + "') ;";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dieser Mapper wird genutzt, um ein Hashtag-Abonnement aus der Datenbank
	 * zu löschen. Hierfür werden sowohl die NutzerId, als auch die HashtagId
	 * übergeben.
	 * 
	 * 
	 * @param NutzerId
	 * @param HashtagId
	 */

	public void HashtagAboEntfernen(int NutzerId, int HashtagId) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "DELETE FROM NutzerHashtag WHERE NutzerId='"
					+ NutzerId + "' AND HashtagId='" + HashtagId + "';";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dieser Mapper zeigt alle abonnierten Hashtags eines bestimmten Nutzers an
	 * und gibt diese in einem Vektor wieder. Hierfür wird die NutzerId benötigt
	 * und übergeben.
	 * 
	 * @param NutzerId
	 * @return Ein Vektor mit Hashtag-Objekten, dass die Hashtags beinhaltet,
	 *         die ein Nutzer abonniert hat.
	 */
	public Vector<Hashtag> alleHashtagsEinesNutzers(int NutzerId) {
		Vector<Hashtag> alleHashtagsEinesNutzers = new Vector<Hashtag>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM NutzerHashtag LEFT JOIN Hashtag ON NutzerHashtag.HashtagId = Hashtag.Id WHERE NutzerId='"
							+ NutzerId + "'  ;");
			while (rs.next()) {
				Hashtag h = new Hashtag();
				h.setId(rs.getInt("Id"));
				h.setErstellungsDatum(rs.getTimestamp("ErstellungsDatum"));
				h.setSchlagwort(rs.getString("Schlagwort"));

				System.out.println(h.getSchlagwort());
				alleHashtagsEinesNutzers.add(h);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zur�ckgeben
		return alleHashtagsEinesNutzers;

	}

	/**
	 * Dieser Mapper soll alle bereits abonnierten Hashtags vom angemeldeten
	 * Nutzer anzeigen. TODO wird der Mapper noch verwendet?
	 * 
	 * @param nutzerId
	 * @return Ein Vektor mit Integer-Objekten, dass die HashtagId's beinhaltet,
	 *         die der Nutzer abonniert hat.
	 */
	public Vector<Integer> ladeAbonnierteHashtagListe(int nutzerId) {
		Connection con = DatenbankVerbindung.connection();

		Vector<Integer> HashtagListe = new Vector<Integer>();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * From NutzerHashtag Where NutzerId='"
							+ nutzerId + "';");

			while (rs.next()) {
				HashtagListe.add(rs.getInt("HashtagId"));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return HashtagListe;
	}

	/**
	 * Dieser Mapper soll alle Hashtags in einem Vektor anzeigen.
	 * 
	 * @return Ein Vektor mit allen Hashtag-Objekten, die es in der Datenbank
	 *         gibt
	 */
	public Vector<Hashtag> gibALleHashtags() {
		Vector<Hashtag> alleHashtagsEinesNutzers = new Vector<Hashtag>();
		Connection con = DatenbankVerbindung.connection();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Hashtag");

			while (rs.next()) {
				Hashtag ha = new Hashtag();
				ha.setId(rs.getInt("Id"));
				ha.setSchlagwort(rs.getString("Schlagwort"));
				ha.setErstellungsDatum(rs.getTimestamp("ErstellungsDatum"));
				alleHashtagsEinesNutzers.addElement(ha);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zurückgeben

		return alleHashtagsEinesNutzers;
	}

	/**
	 * Dieser Mapper soll alle nach einem Abonehmer selektieren und einen Vektor
	 * mit den HashtagId's zurückgeben. .
	 * 
	 * @param nutzerId
	 * @return Ein Vektor mit Hashtag-Objekten, die nach einem Abonehmer
	 *         selektiert wurden
	 * 
	 *         TODO funktion sicher so?
	 */
	public Vector<Integer> selektiereAlleHashtagsNachAbonehmer(int nutzerId) {
		Vector<Integer> hashtagIds = new Vector<Integer>();
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM NutzerHashtag WHERE NutzerId = '"
							+ nutzerId + "';");
			while (rs.next()) {
				hashtagIds.add(rs.getInt("HashtagId"));
			}
		} catch (Exception e) {

		}
		return hashtagIds;
	}
}