package de.hdm.tellme.server.db;

import java.sql.Timestamp;
import java.util.Date;

//Klasse zum Erstellen des heutigen Datums
/**
 * Diese Klasse wird benötigt um das heutige Datum zu erstellen
 * 
 * @author Alex
 *
 */
public class DateHelperClass {
	/**
	 * Die statische Methode <code>getCurrentTime</code> wird benötigt um das
	 * heutige Datum zu erstellen.
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTime() {
		Date currentTime = new Date(System.currentTimeMillis());
		return new Timestamp(currentTime.getTime());
	}

}