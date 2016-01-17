package de.hdm.tellme.shared;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Ausgabe des Reports.
 * </p>
 * TODO
 * 
 ** <code>@RemoteServiceRelativePath("reportservice")</code> ist bei der
 * Adressierung des aus der zugehörigen Impl-Klasse entstehenden
 * Servlet-Kompilats behilflich. Es gibt im Wesentlichen einen Teil der URL des
 * Servlets an. </p>
 * 
 * @author Thies, denispokorski
 */

@RemoteServiceRelativePath("reportservice")
public interface ReportService extends RemoteService {

	/**
	 * Den Report 1 erstellen
	 * 
	 * @param nutzerId
	 * @param vonDatum
	 * @param bisDatum
	 * @return
	 */
	Vector<Nachricht> report1Generieren(int nutzerId, Date vonDatum,
			Date bisDatum);

	/**
	 * Den Report 2 erstellen
	 * 
	 * @param vonDatum
	 * @param bisDatum
	 * @return
	 */

	Vector<Nachricht> report2Generieren(Timestamp vonDatum, Timestamp bisDatum);

	/**
	 * Den Report 3 erstellen
	 * 
	 * @param i
	 * @return
	 */

	Vector<Nachricht> report3Generieren(int i);

	/**
	 * Den Report4 erstellen
	 * 
	 * @return
	 */

	Vector<Nachricht> report4Generieren();

	/**
	 * Den Report 5 erstellen
	 * 
	 * @param i
	 * @return
	 */

	Vector<Nutzer> report5GenerierenListe(int i);

	/**
	 * Den Report 6 erstellen
	 * 
	 * @param i
	 * @return
	 */

	Vector<Hashtag> report6Generieren(int i);

	Vector<Unterhaltung> alleUnterhaltungenFuerAutor(
			int aktiverTeilnehmerID);

	Vector<Nachricht> ladeAlleNachrichtenZuUnterhaltung(int UnterhaltungsID);

	Vector<Nutzer> getAlleNutzer(boolean zwingeNeuladen);

	Vector<Unterhaltung> alleUnterhaltungenFuerAutorMitZeitraum(int AutorId,
			Timestamp vonDate, Timestamp bisDate);

	Vector<Nachricht> ladeAlleNachrichtenZuUnterhaltungMitZeitraum(
			int UnterhaltungsID, Timestamp vonDate, Timestamp bisDate);

	Vector<Unterhaltung> alleUnterhaltungenMitZeitraum(Timestamp vonDate,
			Timestamp bisDate);

	Vector<Unterhaltung> alleUnterhaltungen();


}
