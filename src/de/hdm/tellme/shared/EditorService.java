package de.hdm.tellme.shared;

import java.util.Vector;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Das ist das synchrone Interface für eine RPC-fähige Klasse um die Applikation
 * zu verwalten.
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
	 *            , Nutzer-Objekt, das aktualisiert werden soll.
	 */
	void nutzerAktualisieren(Nutzer n);

	/**
	 * Einen Nutzer löschen
	 * 
	 * @param n
	 *            , Nutzer-Objekt, das gelöscht werden soll.
	 */
	void nutzerLoeschen(Nutzer n);

	/**
	 * Alle Nutzer außer der eigenen auslesen
	 * 
	 * @param meineId
	 *            , NutzerId des eingeloggten Nutzers
	 * @return, Vektor mit Nutzer-Objekten, die alle Nutzer enthält außer den
	 *          eingeloggten.
	 */
	Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId);

	/**
	 * Alle abonnierten Hashtags für den Abonehmer auslesen
	 * 
	 * @param meineId
	 *            , die NutzerId, des Nutzers der die abonnierten Hashtags
	 *            anzeigen lassen möchte.
	 * @return Vektor mit HashtagId's.
	 */
	Vector<Integer> getAlleAbonniertenHashtagsfuerAbonehmer(int meineId);

	/**
	 * Ein Hashtagabo erstellen
	 * 
	 * @param nutzerId
	 *            , des Nutzers, der ein Hashtagabonnement erstellen möchte.
	 * @param hashtagId
	 *            , des Hashtags, das abonniert werden soll.
	 */
	void hashtagAboErstellen(int nutzerId, int hashtagId);

	/**
	 * Einen Nutzerabonnement erstellen
	 * 
	 * @param i
	 *            , NutzerId des Nutzers, der das Abonnement erstellen möchte.
	 * @param _nutzer
	 *            , Nutzer-Objekt des Nutzers der abonniert werden soll.
	 */
	void nutzerAbonnementErstellen(int i, Nutzer _nutzer);

	/**
	 * Alle abnonnierten Nutzer auslesen
	 * 
	 * @param meineId
	 * @return Vektor mit NutzerId's der abonnierten Nutzer.
	 */
	Vector<Integer> holeAlleAbonniertenNutzer(int meineId);

	/**
	 * Einen Nutzerabonnement löschen
	 * 
	 * @param id
	 *            , NutzerId des nutzers, der das Nutzerabonnement löschen
	 *            möchte.
	 * @param _nutzerDeabonieren
	 *            , der Nutzer der nichtmehr abonniert sein soll.
	 */
	void nutzerAbonnementLoeschen(int id, Nutzer _nutzerDeabonieren);

	/**
	 * Ein Hashtag erstellen
	 * 
	 * @param hashtag
	 *            , das Hashtag-Objekt, dass erstellt werden soll
	 */
	void hashtagErstellen(Hashtag hashtag);

	/**
	 * Ein Hashtag löschen
	 * 
	 * @param hashtag
	 *            , das Hashtag-Objekt, das entfernt werden soll
	 */
	void hashtagEntfernen(Hashtag hashtag);

	/**
	 * Ein Hashtagabo entfernen
	 * 
	 * @param nutzerId
	 *            , des Nutzers, der das Hashtagabonnement löschen möchte.
	 * @param hashtagId
	 *            , des Hashtags, das der Nutzer nichtmehr abonnieren möchte.
	 */
	void hashtagAboEntfernen(int nutzerId, int hashtagId);

	/**
	 * Die Sichtbarkeit einer Unterhaltung wird auf 0 gesetzt.
	 * 
	 * @param unterhaltungsID
	 *            , UnterhaltungsId der Unterhaltung die gelöscht werden soll
	 *            bzw deren Sichtbarkeit auf 0 gesetzt werden soll.
	 * @return boolean zur Überprüfung
	 */
	boolean unterhaltung_loeschen(int unterhaltungsID);

	/**
	 * Die Sichtbarkeit von einer erstellen Unterhaltung wird auf 1 gesetzt.
	 * 
	 * @param ersteNachricht
	 *            , das erste Nachricht-Objekt der Unterhaltung
	 * @param teilnehmer
	 *            , ein Vektor mit Nutzer-Objekten, die zur Unterhaltung
	 *            gehören.
	 * @return boolean, zur Überprüfung.
	 */

	boolean unterhaltungStarten(Nachricht ersteNachricht,
			Vector<Nutzer> teilnehmer);

	/**
	 * 
	 * Alle Nachrichten von jeweilgen Unterhaltung auslesen
	 * 
	 * @param UnterhaltungsID
	 *            , damit alle Nachrichten die zu dieser UnterhaltungsId gehören
	 *            gefunden werden können.
	 * @return Vektor mit Nachricht-Objekten.
	 */
	Vector<Nachricht> ladeAlleNachrichtenZuUnterhaltung(int UnterhaltungsID);

	/**
	 * Alle öffentlichen Unternhaltungen von den vom eingeloggten Nutzer
	 * abonnierten Nutzern auslesen
	 * 
	 * @param meineId
	 *            , die NutzerId des Nutzers für den die öffentlichen
	 *            Unterhaltungen, seiner Nutzerabonnements angezeigt werden
	 *            sollen
	 * @return Vektor mit Unterhaltung-Objekten.
	 */
	Vector<Unterhaltung> oeffentlicheUnterhaltungenAbonnierterNutzer(int meineId);

	/**
	 * Alle Unterhaltungen von abonniertem Hashtag des jeweiligen Nutzers
	 * auslesen
	 * 
	 * @param nutzerId
	 *            , die NutzerId des Nutzers für den die Unterhaltungen mit
	 *            bestimmten Hasthags ausgewählt werden.
	 * @return Vektor mit Unterhaltung-Objekten.
	 */
	Vector<Unterhaltung> alleUnterhaltungenVonAbonniertemHashtagUeberNutzerId(
			int nutzerId);

	/**
	 * Die Sichtbarkeit der Nachricht wird auf 0 gesetzt.
	 * 
	 * @param n
	 *            , das Nachricht-Objekt, dessen Sichtbarkeit auf 0 gesetzt
	 *            wird.
	 * @return boolean zur Überprüfung
	 */
	boolean NachrichtLoeschen(Nachricht n);

	/**
	 * Alle Unterhaltungen für den Nutzer relevant sind auslesen
	 * 
	 * @param UserID
	 *            , die NutzerId, des Nutzers für den die relevanten
	 *            Unterhaltungen ausgelesen werden.
	 * @return ein Vektor mit für den Nutzer relevanten Unterhaltung-Objekten.
	 */

	Vector<Unterhaltung> getAlleRelevantenUnterhaltungen(int UserID);

	/**
	 * Alle Hashtags auslesen
	 * 
	 * @return Vektor mit allen Hashtag-Objekten
	 */
	Vector<Hashtag> gibAlleHashtags();

	/**
	 * Ein Hashtag aktualisieren
	 * 
	 * @param hashtag
	 *            , das Hashtag-Objekt, das aktualisiert wird.
	 */
	void hashtagAktualisieren(Hashtag hashtag);

	/**
	 * HashtagAbonnement wird erstellt
	 * 
	 * @param NutzerId
	 *            , die NutzerId des Nutzers der ein Hashtag abonniert.
	 * @param HashtagId
	 *            , die HashtagId des Hashtags, das abonniert wird.
	 */
	void erstellenHashtagAbo(int NutzerId, int HashtagId);

	/**
	 * Alle vom Nutzer abnonnierten Hashtags auslesen
	 * 
	 * @param aboNehmerID
	 *            , die Nutzer-Id, des AboNehmers.
	 * @return, Vektor mit Hashtag-Objekten.
	 */
	Vector<Hashtag> getAbonnierteHashtags(int aboNehmerID);

	/**
	 * Sichtbarkeit der beantworteten Unterhaltungen setzen
	 * 
	 * @param antwortNachricht
	 * @param unterhaltung
	 * @return boolean zur Überprüfung.
	 */
	boolean unterhaltungBeantworten(Nachricht antwortNachricht,
			Unterhaltung unterhaltung);

	/**
	 * 
	 * Alle Nutzer auslesen
	 * 
	 * @param zwingeNeuladen
	 * @return, Vektor der alle Nutzer-Objekte enthält.
	 */
	Vector<Nutzer> getAlleNutzer(boolean zwingeNeuladen);

	/**
	 * Sichtbarkeit einer verlassenen Unterhaltung setzten
	 * 
	 * @param u
	 *            , Unterhaltung die verlassen werden soll
	 * @param nutzerId
	 *            , NutzerId des Nutzers, der die Unterhaltung verlässt.
	 * @return boolean zur Überprüfung.
	 */
	boolean UnterhaltungVerlassen(Unterhaltung u, int nutzerId);

	/**
	 * Eine Unterhaltung wird aktualisiert.
	 * 
	 * @param original
	 *            , altes Unterhaltungs-Objekt
	 * @param neu
	 *            , aktualisiertes Unterhaltungs-Objekt
	 * @return boolean zur Überprüfung.
	 */
	boolean UnterhaltungAktualisieren(Unterhaltung original, Unterhaltung neu);

	/**
	 * Eine Nachricht wird aktualisiert.
	 * 
	 * @param original
	 *            , altes Nachricht-Objekt
	 * @param neu
	 *            , aktualisiertes Nachricht-Objekt.
	 * @return boolean zur Überprüfung.
	 */
	boolean NachrichtAktualisieren(Nachricht original, Nachricht neu);
}
