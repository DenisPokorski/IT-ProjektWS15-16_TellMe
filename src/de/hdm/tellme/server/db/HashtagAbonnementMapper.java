package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.tellme.server.db.DatenbankVerbindung;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.HashtagAbonnement;

/**
 * Mapper-Klasse, die HashtagAbonnement-Objekte in der Datenbank abbildet. Diese
 * enth�lt Methoden zum Anlegen, Aktualisieren, Entfernen und Suchen von
 * Objekten.
 * 
 * @author Nicole Reum
 */

public class HashtagAbonnementMapper {

	private static HashtagAbonnementMapper hashtagAbonnementMapper = null;

	protected HashtagAbonnementMapper() {

	}

	/**
	 * Die statische Methode wird �ber HashtagAbonnementMapper
	 * hashtagAbonnementMapper () aufgerufen. Diese �berpr�ft, dass nur eine
	 * Instanz von HashtagAbonnementMapper besteht.
	 */

	public static HashtagAbonnementMapper hashtagAbonnementMapper() {
		if (hashtagAbonnementMapper == null) {
			hashtagAbonnementMapper = new HashtagAbonnementMapper();

		}
		return hashtagAbonnementMapper;
	}

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

		System.out.println("hasttag size liste: " + HashtagListe.size());
		return HashtagListe;
	}
	
	public Vector<Hashtag> alleNochNichtAboonierteHashtagsSelektieren (int i) {
		
		Vector<Hashtag> alleHashtagAusserMeinHashtagListe =  new Vector<Hashtag>();
		
		Connection con = DatenbankVerbindung.connection();
		int meineid = i;
		try{
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * From Nutzer LEFT JOIN (SELECT * FROM NutzerHashtag WHERE NutzerHashtag.NutzerId = '"
							+ meineid + "') AS A ON Nutzer.Id =  A.HashtagId");
			
			while (rs.next()) {
				
				Hashtag h = new Hashtag();
				h.setId(rs.getInt("Id"));
				h.setErstellungsDatum(rs.getTimestamp("Erstellungsdatum"));
				h.setSchlagwort(rs.getString("Schlagwort"));
				alleHashtagAusserMeinHashtagListe.addElement(h);
				
			}
		} catch (Exception e)	{
			e.printStackTrace();
		}
		return alleHashtagAusserMeinHashtagListe;
	}
	
	public void hashtagAboErstellen(int NutzerId, int HashtagId)	{
		Connection con = DatenbankVerbindung.connection();
		try{
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO NutzerHashtag(NutzerId, HashtagId) VALUES ('"
					+ NutzerId + "','" + HashtagId +"');  ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	public void HashtagAboEntfernen(int NutzerId, int HashtagId ) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "DELETE FROM NutzerHashtag WHERE NutzerId='"
			+ NutzerId + "' AND HashtagId='"+HashtagId + "';";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector<Hashtag> alleHashtagsEinesNutzers(int NutzerId) {
		Vector<Hashtag> alleHashtagsEinesNutzers = new Vector<Hashtag>();
		Connection con = DatenbankVerbindung.connection();
		int tempId = NutzerId;
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM 'NutzerHashtag' LEFT JOIN Hashtag ON NutzerHashtag.HashtagId = Hashtag.Id WHERE NutzerId '"+NutzerId + "';");
			while (rs.next()) {

				if ((rs.getInt("HashtagId")) == tempId) {

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zur�ckgeben
		return alleHashtagsEinesNutzers;

	}
	
	
	public Vector<Integer> ladeAbonnierteHashtagListe(int nutzerId) {
		Connection con = DatenbankVerbindung.connection();

		Vector<Integer> HashtagListe = new Vector<Integer>();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * From NutzerHashtag Where NutzerId='" + nutzerId + "';");

			while (rs.next()) {
				HashtagListe.add(rs.getInt("HashtagId"));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return HashtagListe;
	}
	
	

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
		// Ergebnisvektor zur�ckgeben
 		
		return alleHashtagsEinesNutzers;
	}
}