package de.hdm.tellme.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Das ist das synchrone Interface für eine RPC-fähige Klasse um die Applikation zu verwalten.
 * 
 * 
 * <p>
 * <b>Frage:</b> Warum werden diese Methoden nicht als Teil der Klassen
 * {@link Hashtag}, {@link Nachricht}, {@link Nutzer} oder {@link Unterhaltung}
 * implementiert?<br>
 * <b>Antwort:</b> Z.B. das Löschen eines Kunden erfordert Kenntnisse über die
 * Verflechtung eines Kunden mit Konto-Objekten. Um die Klasse <code>Bank</code>
 * bzw. <code>Customer</code> nicht zu stark an andere Klassen zu koppeln, wird
 * das Wissen darüber, wie einzelne "Daten"-Objekte koexistieren, in der
 * vorliegenden Klasse gekapselt.
 * </p>
 * <p>
 * Natürlich existieren Frameworks wie etwa Hibernate, die dies auf eine andere
 * Weise realisieren. Wir haben jedoch ganz bewusst auf deren Nutzung
 * verzichtet, um in diesem kleinen Demoprojekt den Blick auf das Wesentliche
 * nicht unnötig zu verstellen.
 * </p>
 * <p>
 * <code>@RemoteServiceRelativePath("editorservice")</code> ist bei der
 * Adressierung des aus der zugehörigen Impl-Klasse entstehenden
 * Servlet-Kompilats behilflich. Es gibt im Wesentlichen einen Teil der URL des
 * Servlets an.
 * </p>
 * 
 * 
 * 
 * @author Thies, denispokorski
 *
 */
@RemoteServiceRelativePath("editorservice")
public interface EditorService extends RemoteService {

	/**
	 * Einen Nutzer aktualiseren
	 * 
	 * @param n
	 */

	void nutzerAktualisieren(Nutzer n);

	/**
	 * Einen Nutzer löschen
	 * 
	 * @param n
	 */
	void nutzerLoeschen(Nutzer n);

	/**
	 * Alle Nutzer außer der eigenen auslesen
	 * 
	 * @param meineId
	 * @return
	 */

	Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId);

	/**
	 * Alle abonnierten Hashtags für den Abonehmer auslesen
	 * 
	 * @param meineId
	 * @return
	 */

	Vector<Integer> getAlleAbonniertenHashtagsfuerAbonehmer(int meineId);

	/**
	 * Ein Hashtagabo erstellen
	 * 
	 * @param n
	 */

	void hashtagAboErstellen(int nutzerId, int hashtagId);

	/**
	 * Einen Nutzerabonnement erstellen
	 * 
	 * @param n
	 */

	void nutzerAbonnementErstellen(int i, Nutzer _nutzer);

	/**
	 * Alle abnonnierten Nutzer auslesen
	 * 
	 * @param meineId
	 * @return
	 */

	Vector<Integer> holeAlleAbonniertenNutzer(int meineId);

	/**
	 * Einen Nutzerabonnement löschen
	 * 
	 * @param n
	 */

	void nutzerAbonnementLoeschen(int id, Nutzer _nutzerDeabonieren);

	/**
	 * Ein Hashtag erstellen
	 * 
	 * @param n
	 */

	void hashtagErstellen(Hashtag hashtag);

	/**
	 * Ein Hashtag löschen
	 * 
	 * @param n
	 */

	void hashtagEntfernen(Hashtag hashtag);

	/**
	 * Ein Hashtagabo entfernen
	 * 
	 * @param n
	 */

	void hashtagAboEntfernen(int nutzerId, int hashtagId);

	/**
	 * Die Sichtbarkeit einer Unterhaltung wird auf 0 gesetzt.
	 * 
	 * @param unterhaltungsID
	 * @return
	 */

	boolean unterhaltung_loeschen(int unterhaltungsID);

	/**
	 * Alle Unterhaltungen für einen aktiven Teilnehmer der keine Nachrichten
	 * hat werden ausgelesen
	 * 
	 * @param teilnehmerID
	 * @return TODO wird noch verwendet???
	 */

	Vector<Unterhaltung> alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(
			int teilnehmerID);

	/**
	 * Die Sichtbarkeit von einer erstellen Unterhaltung wird auf 1 gesetzt.
	 * 
	 * @param ersteNachricht
	 * @param teilnehmer
	 * @return TODO
	 */

	boolean unterhaltungStarten(Nachricht ersteNachricht,
			Vector<Nutzer> teilnehmer);

	/**
	 * 
	 * Alle Nachrichten von jeweilgen Unterhaltung auslesen
	 * 
	 * @param UnterhaltungsID
	 * @return
	 */

	Vector<Nachricht> ladeAlleNachrichtenZuUnterhaltung(int UnterhaltungsID);

	/**
	 * Alle öffentlichen Unternhaltungen vom eingeloggten Nutzer auslesen
	 * 
	 * @param meineId
	 * @return
	 */

	Vector<Unterhaltung> oeffentlicheUnterhaltungenAbonnierterNutzer(int meineId);

	/**
	 * Alle Unterhaltungen von abonniertem Hashtag des jweiligen Nutzers
	 * auslesen
	 * 
	 * @param nutzerId
	 * @return
	 */

	Vector<Unterhaltung> alleUnterhaltungenVonAbonniertemHashtagUeberNutzerId(
			int nutzerId);

	/**
	 * Die Sichtbarkeit der Nachricht wird auf 0 gesetzt. TODO
	 * 
	 * @param n
	 * @return
	 */
	boolean NachrichtLoeschen(Nachricht n);

	/**
	 * Alle Unterhaltungen für den Nutzer relevant sind auslesen
	 * 
	 * @param UserID
	 * @return
	 */

	Vector<Unterhaltung> getAlleRelevantenUnterhaltungen(int UserID);

	/**
	 * Alle Hashtags auslesen
	 * 
	 * @return
	 */

	Vector<Hashtag> gibAlleHashtags();

	/**
	 * Ein Hashtag aktualisieren
	 * 
	 * @param n
	 */

	void hashtagAktualisieren(Hashtag hashtag);

	/**
	 * Ein Hashtagtagabo erstellen
	 * 
	 * @param n
	 */

	void erstellenHashtagAbo(int NutzerId, int HashtagId);

	/**
	 * Alle vom Nutzer abnonnierten Hashtags auslesen
	 * 
	 */

	Vector<Hashtag> getAbonnierteHashtags(int aboNehmerID);

	/**
	 * 
	 * Alle Unterhaltungen auslesen für einen Teilnehmer der in den
	 * Unterhaltungen aktiv ist
	 * 
	 * @param aktiverTeilnehmerID
	 * @return
	 */

	Vector<Unterhaltung> getAlleSichtbarenUnterhaltungenFuerTeilnehmer(
			int aktiverTeilnehmerID);

	/**
	 * 
	 * Sichtbarkeit der aktualisierten Nachricht setzen TODO
	 * 
	 * @param original
	 * @param neu
	 * @return
	 */

	boolean NachrichtAktualisieren(Nachricht original, Nachricht neu);

	/**
	 * Sichtbarkeit der beantworteten Unterhaltungen setzen
	 * 
	 * @param antwortNachricht
	 * @param unterhaltung
	 * @return
	 */

	boolean unterhaltungBeantworten(Nachricht antwortNachricht,
			Unterhaltung unterhaltung);

	/**
	 * 
	 * Alle Nutzer auslesen
	 * 
	 * @param zwingeNeuladen
	 * @return
	 */

	Vector<Nutzer> getAlleNutzer(boolean zwingeNeuladen);

	/**
	 * Sichtbarkeit einer verlassenen Unterhaltung setzten TODO
	 * 
	 * @param u
	 * @param nutzerId
	 * @return
	 */

	boolean UnterhaltungVerlassen(Unterhaltung u, int nutzerId);

	boolean UnterhaltungAktualisieren(Unterhaltung original, Unterhaltung neu);

}
