package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer.eStatus;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Mapper-Klasse, die Hashtag-Objekte in der Datenbank abbildet. Diese enthält
 * Methoden zum Anlegen, Aktualisieren, Entfernen und Suchen von Objekten. Durch
 * die Mapper-Klassen können Objekte in Datenbankstrukturen und
 * Datenbankstrukturen in Objekte umgewandelt.
 * 
 * 
 * @author Nicole Reum
 */

public class HashtagMapper {

	/**
	 * Damit die Klasse HashtagMapper nur einmal während der Laufzeit des
	 * Programms bestehen kann, muss man sie als Singleton darstellen, dies
	 * geschieht durch die Referenz <code>static</code>.
	 */

	private static HashtagMapper hashtagMapper = null;

	/**
	 * Damit der HashtagMapper nicht durch <code>new</code> neue Instanzen in
	 * der Klasse erzeugen kann, wird der Konstruktor mit <code>protected</code>
	 * geschützt.
	 */

	protected HashtagMapper() {

	}

	/**
	 * Die statische Methode wird über HashtagMapper hashtagMapper aufgerufen.
	 * Sie überprüft, dass nur eine Instanz von HashtagMapper besteht.
	 */

	public static HashtagMapper hashtagMapper() {
		if (hashtagMapper == null) {
			hashtagMapper = new HashtagMapper();
		}
		return hashtagMapper;
	}

	/**
	 * 
	 * Die Mapper-Methode <code>erstellen</code> wird verwendet, um ein neues
	 * Hashtag zu erstellen. Hierfür wird die Id das Schlagwort, was das Hashtag
	 * an sich darstellt, und das Erstellungsdatum in der Hashtag-Tabelle in die
	 * Datenbank geschrieben.
	 * 
	 * @param hashtag
	 */
	public void erstellen(Hashtag hashtag) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Hashtag (Schlagwort, Erstellungsdatum) VALUES ("
					+ "'"
					+ hashtag.getSchlagwort()
					+ "','"
					+ DateHelperClass.getCurrentTime() + "') ;";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Die Methode entfernen stellt eine Verbindung zur Datenbank her. Dazu wird
	 * die Methode "connection()" aus der Klasse DatenbankVerbindung dem Objekt
	 * con übergeben. Im Anschluss wird im "try-Block" ein Statement erstellt.
	 * Nun legen wir einen neuen String an, der das SQL-Statement mit
	 * dynamischen Hashtagdaten beinhaltet (HashtagId). Jetzt wird über die
	 * Methode "state.executeUpdate(sqlquery);" ausgeführt und der SQL String an
	 * die Datenbank übergeben. Sollte der "try-Block" Fehler aufweisen, wird
	 * der "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgeführt.
	 * 
	 */

	/**
	 * Der Mapper <code>entfernen</code> soll ein Hashtag aus der Datenbank
	 * löschen, hierfür wird der Methode das zu löschende Hashtag übergeben.
	 * 
	 * @param hashtag
	 */
	public void entfernen(Hashtag hashtag) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			state.execute("DELETE FROM NachrichtHashtag WHERE HashtagId = '"
					+ hashtag.getId() + "';");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Statement state = con.createStatement();
			state.execute("DELETE FROM NutzerHashtag WHERE HashtagId = '"
					+ hashtag.getId() + "';");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Statement state = con.createStatement();
			String sqlquery = "DELETE FROM Hashtag WHERE Id = '"
					+ hashtag.getId() + "';";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Die Methode aktualisieren stellt eine Verbindung zur Datenbank her. Dazu
	 * wird die Methode "connection()" aus der Klasse DatenbankVerbindung dem
	 * Objekt con übergeben. Im Anschluss wird im "try-Block" ein Statement
	 * erstellt. Nun legen wir einen neuen String an, der das SQL-Statement mit
	 * dynamischen Hashtagdaten beinhaltet. Jetzt wird über die Methode
	 * "state.executeUpdate(sqlquery);" ausgeführt und der SQL String an die
	 * Datenbank übergeben. Sollte der "try-Block" Fehler aufweisen, wird der
	 * "catch-Block" mit einer entsprechenden Fehlermeldung (Exception)
	 * ausgeführt.
	 * 
	 */

	/**
	 * Dieser Mapper wird benötigt um ein Hashtag zu aktualisieren, hierfür wird
	 * das Schlagwort verändert, während die Id des Hashtags gleich bleibt.
	 * 
	 * @param hashtag
	 */
	public void aktualisieren(Hashtag hashtag) {
		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "UPDATE Hashtag SET Schlagwort='"
					+ hashtag.getSchlagwort() + "', Erstellungsdatum='"
					+ DateHelperClass.getCurrentTime() + "' WHERE Id='"
					+ hashtag.getId() + "';";
			state.executeUpdate(sqlquery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dieser Mapper soll alle Hashtags in einem Vektor anzeigen, die an einer
	 * Nachricht angehängt sind, bzw. zu einer Nachricht gehören. Dies wird über
	 * die NachrichtenId verwirklicht.
	 * 
	 * @param nachrichtId
	 * @return Ein Vektor mit Hashtag-Objekten, die zu einer bestimmten
	 *         Nachricht gehören, bzw. dieser angehängt sind.
	 */
	public Vector<Hashtag> alleHashtagsZuNachrichtenID(int nachrichtId) {
		Vector<Hashtag> alleHashtags = new Vector<Hashtag>();

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM NachrichtHashtag JOIN Hashtag ON NachrichtHashtag.HashtagId = Hashtag.Id JOIN Nachricht ON NachrichtId = Nachricht.Id JOIN Nutzer ON Nachricht.AutorId = Nutzer.Id WHERE NachrichtId ='"
							+ nachrichtId
							+ "' AND Nutzer.Status = '"
							+ eStatus.aktiv.ordinal() + "';");

			while (rs.next()) {
				Hashtag h = new Hashtag();
				h.setId(rs.getInt("Id"));
				h.setSchlagwort(rs.getString("Schlagwort"));
				h.setErstellungsDatum(rs.getTimestamp("ErstellungsDatum"));
				alleHashtags.add(h);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alleHashtags;
	}

	public Vector<Nachricht> gibAlleDummyNachrichtenMitDummyHashtags() {
		Vector<Nachricht> alleDummyNachrichten = new Vector<Nachricht>();

		Connection con = DatenbankVerbindung.connection();
		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM NachrichtHashtag;");

			while (rs.next()) {
				Nachricht n = new Nachricht();
				n.setId(rs.getInt("NachrichtId"));
				
				Vector<Hashtag> hV = new Vector<Hashtag>();
				
				Hashtag h = new Hashtag();
				h.setId(rs.getInt("HashtagId"));
				
				hV.add(h);

				n.setVerknuepfteHashtags(hV);
				alleDummyNachrichten.add(n);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alleDummyNachrichten;
	}
}
