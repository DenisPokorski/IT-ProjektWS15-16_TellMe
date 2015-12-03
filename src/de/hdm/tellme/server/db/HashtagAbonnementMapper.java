package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.Statement;

import de.hdm.tellme.server.db.DatenbankVerbindung;
import de.hdm.tellme.shared.bo.HashtagAbonnement;

/**
 * Mapper-Klasse, die HashtagAbonnement-Objekte in der Datenbank abbildet. Diese
 * enth�lt Methoden zum Anlegen, Aktualisieren, Entfernen und Suchen von
 * Objekten.
 * 
 * @author Nicole Reum
 */

//public class HashtagAbonnementMapper {
//<<<<<<< HEAD
//	
//	private static HashtagAbonnementMapper hastagAbonnementMapper = null; 
//	
////	protected HashtagAbonnementMapper () {
////		
////	}
////
////	public static HashtagAbonnementMapper hashtagAbonnementMapper() {
////		if (hashtagAbonnementMapper == null) {
////			hashtagAbonnementMapper = new HashtagAbonnementMapper();
////	}
////	return hashtagAbonnementMapper;
////	}
////	
////	public void anlegen (HashtagAbonnementMapper h) {
////		Connection con = DatenbankVerbindung.connection();
////		try {
////			Statement state = con.createStatement();
////			String sqlquery ("INSERT INTO Hashtag ("") VALUES ( )
////		}
////		
////	}
////	
//}
// 
//	private static HashtagAbonnementMapper hashtagAbonnementMapper = null;
//
//	protected HashtagAbonnementMapper() {
//
//	}
//
//	/**
//	 * Die statische Methode wird �ber HashtagAbonnementMapper
//	 * hashtagAbonnementMapper () aufgerufen. Diese �berpr�ft, dass nur eine
//	 * Instanz von HashtagAbonnementMapper besteht.
//	 */
//
//	public static HashtagAbonnementMapper hashtagAbonnementMapper() {
//		if (hashtagAbonnementMapper == null) {
//			hashtagAbonnementMapper = new HashtagAbonnementMapper();
//
//		}
//		return hashtagAbonnementMapper;
//	}
//
//	public void anlegen(HashtagAbonnement h) {
//		Connection con = DatenbankVerbindung.connection();
//		try {
//			Statement state = con.createStatement();
//			String sqlquery = "INSERT INTO NutzerHashtag (NutzerId, HashtagId) VALUES ("
//					+ "'"
//					+ h.getAbonnementErsteller().getId()
//					+ "','"
//					+ h.getHashtag().getHashtagId()
//					+ "') ;";
//			state.executeUpdate(sqlquery);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void entfernen(HashtagAbonnement h) {
//		Connection con = DatenbankVerbindung.connection();
//		try {
//		Statement state = con.createStatement();
//		String sqlquery = "DELETE FROM NutzerHashtag (NutzerId, HashtagId) VALUES ("
//				+ "'" + h.getAbonnementErsteller().getId() + "'" + h.getHashtag().getHashtagId() + "');";
//			state.executeUpdate(sqlquery);
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//}
//>>>>>>> origin/master
