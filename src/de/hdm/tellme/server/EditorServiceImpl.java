package de.hdm.tellme.server;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.HashtagAbonnementMapper;
import de.hdm.tellme.server.db.HashtagMapper;
import de.hdm.tellme.server.db.NachrichtMapper;
import de.hdm.tellme.server.db.NutzerAbonnementMapper;
import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.server.db.UnterhaltungMapper;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;
import de.hdm.tellme.shared.bo.Unterhaltung.eUnterhaltungsTyp;

/**
 * <p>
 * Implementierungsklasse des Interface <code>EditorService</code>. Diese Klasse
 * ist <em>die</em> Klasse, die neben {@link ReportServiceImpl} sämtliche
 * Applikationslogik (oder engl. Business Logic) aggregiert. Sie ist wie eine
 * Spinne, die sämtliche Zusammenhänge in ihrem Netz (in unserem Fall die Daten
 * der Applikation) überblickt und für einen geordneten Ablauf und dauerhafte
 * Konsistenz der Daten und Abläufe sorgt.
 * </p>
 * <p>
 * Die Applikationslogik findet sich in den Methoden dieser Klasse. Jede dieser
 * Methoden kann als <em>Transaction Script</em> bezeichnet werden. Dieser Name
 * lässt schon vermuten, dass hier analog zu Datenbanktransaktion pro
 * Transaktion gleiche mehrere Teilaktionen durchgeführt werden, die das System
 * von einem konsistenten Zustand in einen anderen, auch wieder konsistenten
 * Zustand überführen. Wenn dies zwischenzeitig scheitern sollte, dann ist das
 * jeweilige Transaction Script dafür verwantwortlich, eine Fehlerbehandlung
 * durchzuführen.
 * </p>
 * <p>
 * Diese Klasse steht mit einer Reihe weiterer Datentypen in Verbindung. Dies
 * sind:
 * <ol>
 * <li>{@link EditorService}: Dies ist das <em>lokale</em> - also Server-seitige
 * - Interface, das die im System zur Verfügung gestellten Funktionen
 * deklariert.</li>
 * <li>{@link EditorServiceAsync}: <code>EditorServiceImpl</code> und
 * <code>EditorService</code> bilden nur die Server-seitige Sicht der
 * Applikationslogik ab. Diese basiert vollständig auf synchronen
 * Funktionsaufrufen. Wir müssen jedoch in der Lage sein, Client-seitige
 * asynchrone Aufrufe zu bedienen. Dies bedingt ein weiteres Interface, das in
 * der Regel genauso benannt wird, wie das synchrone Interface, jedoch mit dem
 * zusätzlichen Suffix "Async". Es steht nur mittelbar mit dieser Klasse in
 * Verbindung. Die Erstellung und Pflege der Async Interfaces wird durch das
 * Google Plugin semiautomatisch unterstützt. Weitere Informationen unter
 * {@link EditorServiceAsync}.</li>
 * <li> {@link RemoteServiceServlet}: Jede Server-seitig instantiierbare und
 * Client-seitig über GWT RPC nutzbare Klasse muss die Klasse
 * <code>RemoteServiceServlet</code> implementieren. Sie legt die funktionale
 * Basis für die Anbindung von <code>EditorServiceImpl</code> an die Runtime des
 * GWT RPC-Mechanismus.</li>
 * </ol>
 * </p>
 * <p>
 * <b>Wichtiger Hinweis:</b> Diese Klasse bedient sich sogenannter
 * Mapper-Klassen. Sie gehören der Datenbank-Schicht an und bilden die
 * objektorientierte Sicht der Applikationslogik auf die relationale
 * organisierte Datenbank ab. Zuweilen kommen "kreative" Zeitgenossen auf die
 * Idee, in diesen Mappern auch Applikationslogik zu realisieren. Einzig
 * nachvollziehbares Argument für einen solchen Ansatz ist die Steigerung der
 * Performance umfangreicher Datenbankoperationen. Doch auch dieses Argument
 * zieht nur dann, wenn wirklich große Datenmengen zu handhaben sind. In einem
 * solchen Fall würde man jedoch eine entsprechend erweiterte Architektur
 * realisieren, die wiederum sämtliche Applikationslogik in der
 * Applikationsschicht isolieren würde. Also, keine Applikationslogik in die
 * Mapper-Klassen "stecken" sondern dies auf die Applikationsschicht
 * konzentrieren!
 * </p>
 * <p>
 * Beachten Sie, dass sämtliche Methoden, die mittels GWT RPC aufgerufen werden
 * können ein <code>throws IllegalArgumentException</code> in der
 * Methodendeklaration aufweisen. Diese Methoden dürfen also Instanzen von
 * {@link IllegalArgumentException} auswerfen. Mit diesen Exceptions können z.B.
 * Probleme auf der Server-Seite in einfacher Weise auf die Client-Seite
 * transportiert und dort systematisch in einem Catch-Block abgearbeitet werden.
 * </p>
 * <p>
 * Es gibt sicherlich noch viel mehr über diese Klasse zu schreiben. Weitere
 * Infos erhalten Sie in der Lehrveranstaltung.
 * </p>
 * 
 * @see EditorService
 * @see EditorServiceAsync
 * @see RemoteServiceServlet
 * @author Thies, Alex Homann, Denis Pokorski
 */
@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements EditorService {

	// ################### MAPPER #####################

	/**
	 * Referenz auf den NutzerMapper, der Nutzerobjekte mit der Datenbank
	 * abgleicht.
	 */
	private NutzerMapper nutzerMapper = null;

	/**
	 * Referenz auf den NutzerAbonnementMapper, der NutzeraAbonnementobjekte mit
	 * der Datenbank abgleicht.
	 */
	private NutzerAbonnementMapper nutzeraboMapper = null;

	/**
	 * Referenz auf den NachrichtMapper, der Nachrichtobjekte mit der Datenbank
	 * abgleicht.
	 */
	private NachrichtMapper nachrichtMapper = null;

	/**
	 * Referenz auf den UnterhaltungMapper, der Unterhaltungsobjekte mit der
	 * Datenbank abgleicht.
	 */
	private UnterhaltungMapper unterhaltungMapper = null;

	/**
	 * Referenz auf den HashtagAbonnementtMapper, der Hashtagabonnementobjekte
	 * mit der Datenbank abgleicht.
	 */
	private HashtagAbonnementMapper hashtagAboMapper = null;

	/**
	 * Referenz auf den HashtagMapper, der Hashtagobjekte mit der Datenbank
	 * abgleicht.
	 */
	private HashtagMapper hashtagMapper = null;

	// ################### LISTEN #####################

	/*
	 * Da diese Klasse ein gewisse Größe besitzt - dies ist eigentlich ein
	 * Hinweise, dass hier eine weitere Gliederung sinnvoll ist - haben wir zur
	 * besseren Übersicht Abschnittskomentare eingefügt. Sie leiten ein Cluster
	 * in irgeneinerweise zusammengehöriger Methoden ein. Ein entsprechender
	 * Kommentar steht am Ende eines solchen Clusters.
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Initialisierung
	 * ***************************************
	 * ************************************
	 */
	/**
	 * <p>
	 * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	 * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	 * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines
	 * anderen Konstruktors ist durch die Client-seitige Instantiierung durch
	 * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
	 * möglich.
	 * </p>
	 * <p>
	 * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	 * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	 * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	 * </p>
	 * 
	 * @see #init()
	 */

	public EditorServiceImpl() throws IllegalArgumentException {
		/*
		 * Eine weitergehende Funktion muss der No-Argument-Constructor nicht
		 * haben. Er muss einfach vorhanden sein.
		 */
	}

	public void init() throws IllegalArgumentException {
		if (Helper.debugModus)
			Helper.LogDebug("HELLO TELLME! - DEBUG MODE ON");

		/**
		 * Es ist wichtig, dass die <code>EditorServiceImpl</code> einen
		 * vollständigen Satz an Mappern besitzt, damit sie vollständig mit der
		 * Datenbank kommunizieren kann.
		 */
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.nutzeraboMapper = NutzerAbonnementMapper.nutzerAbonnementMapper();
		this.nachrichtMapper = NachrichtMapper.nachrichtMapper();
		this.unterhaltungMapper = UnterhaltungMapper.unterhaltungMapper();
		this.hashtagAboMapper = HashtagAbonnementMapper.hashtagAbonnementMapper();
		this.hashtagMapper = HashtagMapper.hashtagMapper();

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Initialisierung
	 * *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	 * ***************************
	 * ************************************************
	 */
	/**
	 * Anlegen eines Nutzes.
	 * 
	 * @param nutzer
	 * @return int Wert, der die Id von angelegtem Nutzer darstellt.
	 */
	public int nutzerAnlegen(Nutzer nutzer) {
		int ergebnis = nutzerMapper.anlegen(nutzer);
		return ergebnis;
	}

	/**
	 * Der Nutzer wird aktualisiert
	 * 
	 */
	public void nutzerAktualisieren(Nutzer nutzer) {
		nutzerMapper.aktualisieren(nutzer);
	}

	/**
	 * Der Nutzer wird aktualisiert
	 * 
	 */
	public void nutzerLoeschen(Nutzer nutzer) {
		nutzerMapper.entfernen(nutzer);
	}

	/**
	 * Den Nutzer anhand der ID ausfindig machen
	 * 
	 * @param nutzerID
	 * @return Nutzer-Objekt, dass durch die Id gefunden wurde.
	 */
	public Nutzer getNutzerAnhandID(int nutzerID) {

		Vector<Nutzer> alleNutzer = getAlleNutzer(false);
		Nutzer gesuchterNutzer = null;
		for (Nutzer nutzer : alleNutzer) {
			if (nutzer.getId() == nutzerID) {
				gesuchterNutzer = nutzer;
				break;
			}
		}

		if (gesuchterNutzer == null) {
			// Nutzer konnte nicht gefunden werden, stattdessen unknown user
			// erstellen und zurückgeben
			// Erstelle Unknown User und ordne diesen zu
			Nutzer n = new Nutzer();
			n.setId(-1);
			n.setMailadresse("unknown@unknown.de");
			n.setVorname("Gelöschter");
			n.setNachname("Nutzer");
			return n;

		}

		return gesuchterNutzer;

	}

	/**
	 * Den Nutzer anhand der ID ausfindig machen
	 * 
	 * @param nutzerID
	 * @return Vektor mit Nutzer-Objekten, der alle Nutzer außer den
	 *         eingeloggten Nutzer zurück gibt.
	 */
	@Override
	public Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId) {

		Vector<Nutzer> alleNutzeAuserMir = new Vector<Nutzer>();

		for (Nutzer nutzer : this.getAlleNutzer(false)) {
			if (nutzer.getId() != meineId)
				alleNutzeAuserMir.add(nutzer);
		}

		return alleNutzeAuserMir;
	}

	/**
	 * Auslesen aller Nutzer
	 */

	@Override
	public Vector<Nutzer> getAlleNutzer(boolean zwingeNeuladen) {
		return nutzerMapper.alleNutzer(0);

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Nutzer-Objekte
	 * *****************************
	 * **********************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für NutzerAbonnement-Objekte
	 * *****************
	 * **********************************************************
	 */

	/**
	 * Löschen eines Nutzer-Abonnements
	 */
	@Override
	public void nutzerAbonnementLoeschen(int aboNehmerID, Nutzer aboGeber) {
		nutzeraboMapper.loescheNutzeraboById(aboNehmerID, aboGeber);
	}

	/**
	 * Erstellen eines Nutzer-Abonnements
	 */
	@Override
	public void nutzerAbonnementErstellen(int aboNehmerID, Nutzer aboGeber) {
		nutzeraboMapper.nutzerAboErstellen(aboNehmerID, aboGeber);

	}

	/**
	 * Hole alle abonnierten Nutzer
	 */
	@Override
	public Vector<Integer> holeAlleAbonniertenNutzer(int meineId) {
		Vector<Integer> alleAbonniertenNutzer = nutzerMapper.alleAbonniertenNutzer(meineId);

		return alleAbonniertenNutzer;
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Nutzerabonnement-Objekte
	 * *******************
	 * ********************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Hashtag-Objekte
	 * **************************
	 * *************************************************
	 */

	/**
	 * Auslesen aller Hashtags
	 */
	@Override
	public Vector<Hashtag> gibAlleHashtags() {
		Vector<Hashtag> alleHashtags = hashtagAboMapper.gibALleHashtags();
		return alleHashtags;
	}

	/**
	 * Das jeweilige Hashtag entfernen
	 */

	@Override
	public void hashtagEntfernen(Hashtag hashtag) {
		nachrichtMapper.alleHashtagZuordnungLoeschen(hashtag.getId());
		nutzerMapper.alleHashtagZuordnungLoeschen(hashtag);
		hashtagMapper.entfernen(hashtag);
	}

	/**
	 * Es wird ein neues Hashtag erstellt
	 */
	@Override
	public void hashtagAktualisieren(Hashtag hashtag) {
		hashtagMapper.aktualisieren(hashtag);
	}

	/**
	 * Es wird ein neues Hashtag erstellt
	 */
	@Override
	public void hashtagErstellen(Hashtag hashtag) {
		hashtagMapper.erstellen(hashtag);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Hashtag-Objekte
	 * ****************************
	 * ***********************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für HashtagAbonnement-Objekte
	 * ****************
	 * ***********************************************************
	 */

	/**
	 * Auslesen aller abonnierten Hashtags
	 */
	@Override
	public Vector<Integer> getAlleAbonniertenHashtagsfuerAbonehmer(int aboNehmerId) {
		Vector<Integer> alleAbboniertenNutzer = hashtagAboMapper.ladeAbonnierteHashtagListe(aboNehmerId);
		return alleAbboniertenNutzer;
	}

	/**
	 * Ein HashtagAbonnement wird erstellt
	 */
	@Override
	public void erstellenHashtagAbo(int NutzerId, int HashtagId) {
		hashtagAboMapper.hashtagAboErstellen(NutzerId, HashtagId);
	}

	/**
	 * Auslesen aller bereits abonnierten Hashtags
	 */
	@Override
	public Vector<Hashtag> getAbonnierteHashtags(int aboNehmerID) {
		Vector<Hashtag> alleHashtags = hashtagAboMapper.alleHashtagsEinesNutzers(aboNehmerID);
		return alleHashtags;
	}

	/**
	 * Ein Hashtag-Abonnement wird erstellt
	 */
	@Override
	public void hashtagAboErstellen(int nutzerId, int hashtagId) {
		hashtagAboMapper.hashtagAboErstellen(nutzerId, hashtagId);
	}

	/**
	 * Das jeweilige Hashtag-Abonnement wird gelöscht
	 */
	@Override
	public void hashtagAboEntfernen(int nutzerId, int hashtagId) {
		hashtagAboMapper.HashtagAboEntfernen(nutzerId, hashtagId);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für HashtagAbonnement-Objekte
	 * ******************
	 * *********************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nachricht-Objekte
	 * ************************
	 * ***************************************************
	 */

	/**
	 * Auslesen aller Nachrichten die zu einer Unterhaltung gehören
	 */
	@Override
	public Vector<Nachricht> ladeAlleNachrichtenZuUnterhaltung(int UnterhaltungsID) {
		Vector<Nachricht> alleNachrichten = nachrichtMapper.gibAlleNachrichtenVonUnterhaltung(UnterhaltungsID);

		// lade zu jeder Nachricht den Sender und die Hashtags
		if (alleNachrichten.size() > 0) {
			for (Nachricht nachricht : alleNachrichten) {
				Vector<Hashtag> alleHashtagsZuNachricht = hashtagMapper.alleHashtagsZuNachrichtenID(nachricht.getId());
				nachricht.setVerknuepfteHashtags(alleHashtagsZuNachricht);

				Nutzer sender = null;
				sender = getNutzerAnhandID(nachricht.getSenderId());
				nachricht.setSender(sender);

			}
		}
		return alleNachrichten;
	}

	/**
	 * Auslesen aller Unterhaltungen über ein Hashtag-Abonnement
	 */
	@Override
	public Vector<Unterhaltung> alleUnterhaltungenVonAbonniertemHashtagUeberNutzerId(int nutzerId) {
		Vector<Unterhaltung> alleOeffentlichenUnterhaltungenAbonnierterHashtags = new Vector<Unterhaltung>();

		// Lade alle abonierten HashtagIDs
		Vector<Integer> abonierteHashtagIDs = hashtagAboMapper.selektiereAlleHashtagsNachAbonehmer(nutzerId);
		Vector<Integer> alleNachrichtenIDsMiteinemAboniertemHashtag = new Vector<Integer>();

		for (Integer abonierteHashtagID : abonierteHashtagIDs) {

			Helper.LogDebug("Abonierte HastagID: " + abonierteHashtagID);
			Vector<Nachricht> NachrichtenMitAboniertemHashtag = new Vector<Nachricht>();

			// Lade alle Nachrichten zu dem abonierten Hashtag
			NachrichtenMitAboniertemHashtag = nachrichtMapper.gibNachrichtenVonHashtagId(abonierteHashtagID);

			for (Nachricht nachrichtMitAboniertemHashtag : NachrichtenMitAboniertemHashtag) {

				// Überprüfe ob Nachricht bereits in Liste
				// alleNachrichtenIDsMiteinemAboniertemHashtag hinzugeügt wurde
				// beispielsweise wenn Nachricht zwei Hashtags hat und beide
				// aboniert wurden
				boolean bereitsInNachrichtenIDListe = false;
				for (Integer NachrichtenID : alleNachrichtenIDsMiteinemAboniertemHashtag) {
					if (NachrichtenID == nachrichtMitAboniertemHashtag.getId()) {
						bereitsInNachrichtenIDListe = true;
						continue;
					}
				}
				// Wenn NachrichtenID noch nicht durch ein anderes Hashtagabo
				// hinzugefügt wurde jetzt ID hinzufügen
				if (bereitsInNachrichtenIDListe == false)
					alleNachrichtenIDsMiteinemAboniertemHashtag.add(nachrichtMitAboniertemHashtag.getId());

				// lade zu jeder Nachricht die Unterhaltung
				Unterhaltung UnterhaltungMitAboniertemHashtag = unterhaltungMapper.selektiereUnterhaltungenVonNachrichtId(nachrichtMitAboniertemHashtag);

				if (UnterhaltungMitAboniertemHashtag.getUnterhaltungstyp() == eUnterhaltungsTyp.oeffentlich) {
					// Überprüfe ob Unterhaltung bereits in Liste ist
					boolean bereitsInListe = false;

					for (Unterhaltung unterhaltungInListe : alleOeffentlichenUnterhaltungenAbonnierterHashtags) {
						if (unterhaltungInListe.getId() == UnterhaltungMitAboniertemHashtag.getId()) {
							bereitsInListe = true;
							continue;
						}
					}

					if (bereitsInListe == false) {
						// Wenn Unterhaltung öffentlich und noch nicht
						// hinzugefügt, lade alle Nachrichten zu
						// Unterhaltung
						Vector<Nachricht> alleNachrichtenZuUnterhaltung = new Vector<Nachricht>();
						alleNachrichtenZuUnterhaltung = ladeAlleNachrichtenZuUnterhaltung(UnterhaltungMitAboniertemHashtag.getId());

						UnterhaltungMitAboniertemHashtag.setAlleNachrichten(alleNachrichtenZuUnterhaltung);

						// lade alle teilnehmer zu Unterhaltung
						Vector<Nutzer> alleTeilnehmer = new Vector<Nutzer>();
						Vector<Integer> alleTeilnehmerIDs = unterhaltungMapper.gibTeilnehmerFuerUnterhaltung(UnterhaltungMitAboniertemHashtag.getId());
						for (Integer teilnehmerID : alleTeilnehmerIDs) {
							alleTeilnehmer.add(getNutzerAnhandID(teilnehmerID));
						}

						UnterhaltungMitAboniertemHashtag.setTeilnehmer(alleTeilnehmer);

						UnterhaltungMitAboniertemHashtag.setAnzeigeHerkunft("Hashtagabonement");

						// füge Unterhaltung der Liste hinzu
						alleOeffentlichenUnterhaltungenAbonnierterHashtags.add(UnterhaltungMitAboniertemHashtag);

					}

				}
			}
		}

		// Bevor Unterhaltungen zuückgegeben werden, werden alle Nachrichten aus
		// den Unterhaltungen, die kein aboniertes Hashtag enthalten

		for (Unterhaltung unterhaltungMitAbonierterHashtagnachricht : alleOeffentlichenUnterhaltungenAbonnierterHashtags) {
			Vector<Nachricht> alleNachrichtenDerUnterhaltunMitMindestensEinemAbonniertemHashtag = new Vector<Nachricht>();

			for (Nachricht nachrichtInUnterhaltung : unterhaltungMitAbonierterHashtagnachricht.getAlleNachrichten()) {
				boolean nachrichtIstInListe = false;
				for (Integer integer : alleNachrichtenIDsMiteinemAboniertemHashtag) {
					if (nachrichtInUnterhaltung.getId() == integer) {
						nachrichtIstInListe = true;
						continue;
					}
				}

				if (nachrichtIstInListe == true) {
					alleNachrichtenDerUnterhaltunMitMindestensEinemAbonniertemHashtag.add(nachrichtInUnterhaltung);
				}
			}

			unterhaltungMitAbonierterHashtagnachricht.setAlleNachrichten(alleNachrichtenDerUnterhaltunMitMindestensEinemAbonniertemHashtag);
		}

		return alleOeffentlichenUnterhaltungenAbonnierterHashtags;

	}

	/**
	 * Eine neue Nachricht wird erstellt
	 * 
	 * @param n
	 * @return int Wert mit mit NachrichtId
	 */
	private int nachricht_erstellen(Nachricht n) {
		int neueNachrichtID = -1;
		neueNachrichtID = nachrichtMapper.anlegen(n);
		if (neueNachrichtID != -1) {
			for (Hashtag hashtag : n.getVerknuepfteHashtags()) {
				nachrichtMapper.hashtagEinerNachrichtZuordnen(hashtag.getId(), neueNachrichtID);

			}
		}
		return neueNachrichtID;
	}

	/**
	 * Nachricht wurde aktualisiert
	 * 
	 * @return boolean zur Überprüfung
	 */
	@Override
	public boolean NachrichtAktualisieren(Nachricht original, Nachricht neu) {
		boolean erfolgreich = true;

		// welche hashtags muessen geloescht werden
		Vector<Hashtag> ZuLoeschendeHashtags = new Vector<Hashtag>();
		for (Hashtag hashtagOriginal : original.getVerknuepfteHashtags()) {
			boolean existiertNoch = false;
			for (Hashtag hashtagNeu : neu.getVerknuepfteHashtags()) {
				if (hashtagOriginal.getId() == hashtagNeu.getId()) {
					existiertNoch = true;
					break;
				}
			}

			if (existiertNoch == false)
				ZuLoeschendeHashtags.add(hashtagOriginal);
		}

		// welche hashtags muessen hinzugefuegt werden
		Vector<Hashtag> ZuHinzuzufuegendeHashtags = new Vector<Hashtag>();
		for (Hashtag hashtagNeu : neu.getVerknuepfteHashtags()) {
			boolean istNeu = true;
			for (Hashtag hashtagOriginal : original.getVerknuepfteHashtags()) {
				if (hashtagNeu.getId() == hashtagOriginal.getId()) {
					istNeu = false;
					break;
				}
			}

			if (istNeu)
				ZuHinzuzufuegendeHashtags.add(hashtagNeu);
		}

		erfolgreich = nachrichtMapper.aktualisieren(neu);

		if (erfolgreich) {
			for (Hashtag hashtag : ZuLoeschendeHashtags) {
				nachrichtMapper.hashtagZuordnungLoeschen(hashtag.getId(), neu.getId());
			}
			for (Hashtag hashtag : ZuHinzuzufuegendeHashtags) {
				nachrichtMapper.hashtagEinerNachrichtZuordnen(hashtag.getId(), neu.getId());
			}

		}

		return erfolgreich;
	}

	/**
	 * Nachricht wurde gelöscht
	 * 
	 * @return boolean zur Überprüfung
	 */
	@Override
	public boolean NachrichtLoeschen(Nachricht n) {
		// TODO: Check ob Nachricht letzte der Unterhaltung, wenn ja ->
		// unterhaltung deaktivieren!
		boolean erfolgreich = true;
		erfolgreich = nachrichtMapper.entfernen(n.getId());
		return erfolgreich;
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Nachricht-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Unterhaltung-Objekte
	 * *********************
	 * ******************************************************
	 */

	/**
	 * Unterhaltung wurde gelöscht.
	 * 
	 * @return boolean zur Überprüfung
	 */
	@Override
	public boolean unterhaltung_loeschen(int unterhaltungsID) {
		boolean ergebnis = false;
		ergebnis = unterhaltungMapper.loescheUnterhaltungAnhandID(unterhaltungsID);
		return ergebnis;
	}

	/**
	 * Auslesen aller Unterhaltungen für einen Teilnehmer, der noch keine
	 * Nachrichten besitzt.
	 * 
	 */
	@Override
	public Vector<Unterhaltung> alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(int teilnehmerID) {
		// TODO: REMOVE
		return null;
	}

	/**
	 * Unterhaltung starten.
	 * 
	 * @return boolean zur Überprüfung
	 */
	@Override
	public boolean unterhaltungStarten(Nachricht ersteNachricht, Vector<Nutzer> teilnehmer) {
		boolean erfolgreich = true;
		int unterhaltungsID = -1;

		eUnterhaltungsTyp UnterhaltungsTyp;
		if (teilnehmer.size() > 1)
			UnterhaltungsTyp = eUnterhaltungsTyp.privat;
		else
			UnterhaltungsTyp = eUnterhaltungsTyp.oeffentlich;

		unterhaltungsID = unterhaltungMapper.anlegen(UnterhaltungsTyp);
		if (unterhaltungsID != -1) {
			for (Nutzer nutzer : teilnehmer) {
				unterhaltungMapper.teilnehmerHinzufuegen(unterhaltungsID, nutzer.getId());
			}

			int nachrichtenID = -1;
			nachrichtenID = nachricht_erstellen(ersteNachricht);
			if (nachrichtenID != -1) {
				nachrichtMapper.nachrichtEinerUnterhaltungZuordnen(nachrichtenID, unterhaltungsID);

			} else {
				erfolgreich = false;
			}
		} else {
			erfolgreich = false;
		}

		return erfolgreich;
	}

	/**
	 * Unterhaltung beantworten.
	 * 
	 * @return boolean zur Überprüfung
	 */
	@Override
	public boolean unterhaltungBeantworten(Nachricht antwortNachricht, Unterhaltung unterhaltung) {
		boolean erfolgreich = true;

		int nachrichtenID = -1;
		nachrichtenID = nachricht_erstellen(antwortNachricht);
		if (nachrichtenID != -1) {
			if (nachrichtMapper.nachrichtEinerUnterhaltungZuordnen(nachrichtenID, unterhaltung.getId())) {
				// Nachricht wurde erfolgreich zugeordnet
				nutzerEinerUnterhaltungZuordnen(antwortNachricht.getSenderId(), unterhaltung.getId());
			}

		} else {
			erfolgreich = false;
		}

		return erfolgreich;
	}

	/**
	 * Anzeigen der öffentlichen Unterhaltungen der Nutzer, die der eingeloggte
	 * Nutzer abonniert hat.
	 */
	@Override
	@Deprecated
	public Vector<Unterhaltung> oeffentlicheUnterhaltungenAbonnierterNutzer(int meineId) {
		return null;
	}

	/**
	 * Unterhaltung verlassen.
	 * 
	 * @return boolean zur Überprüfung
	 */
	@Override
	public boolean UnterhaltungVerlassen(Unterhaltung u, int nutzerId) {
		// TODO: letzter Telnehmer? -> Unterhaltung als inaktiv makieren
		boolean erfolgreich = true;
		erfolgreich = unterhaltungMapper.teilnehmerAktualisieren(u.getId(), nutzerId, 0);
		return erfolgreich;
	}

	/**
	 * Auslesen aller sichtbaren Unterhaltung für Teilnehmer
	 */
	@Override
	@Deprecated
	public Vector<Unterhaltung> getAlleSichtbarenUnterhaltungenFuerTeilnehmerOhneNachrichten(int aktiverTeilnehmerID) {
		Vector<Unterhaltung> alleSichtbarenUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Unterhaltung> alleSichtbarenUnterhaltungenMitSichtbarenNachrichten = new Vector<Unterhaltung>();

		alleSichtbarenUnterhaltungen = unterhaltungMapper.alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(aktiverTeilnehmerID);

		// lade Nachrichten und Teilnehmer zu Unterhaltungen
		for (Unterhaltung unterhaltung : alleSichtbarenUnterhaltungen) {

			// Nachrichten
			Vector<Nachricht> alleNachrichten = ladeAlleNachrichtenZuUnterhaltung(unterhaltung.getId());
			unterhaltung.setAlleNachrichten(alleNachrichten);

			// fuege nur Unterhaltungen mit mind. 1 Nachricht hinzu.
			if (alleNachrichten.isEmpty() == false) {
				boolean bereitsHinzugefuegt = false;
				// Pruefe ob Unterhaltung bereits der Listehinzugefügt wurde
				for (Unterhaltung unterhaltungInEntgueltigerListe : alleSichtbarenUnterhaltungenMitSichtbarenNachrichten) {
					if (unterhaltungInEntgueltigerListe.getId() == unterhaltung.getId())
						bereitsHinzugefuegt = true;
				}
				// Fuege nur Unterhaltungen hinzu, die nicht bereits zur liste
				// hinzuegfügt wurden
				if (bereitsHinzugefuegt == false)
					alleSichtbarenUnterhaltungenMitSichtbarenNachrichten.add(unterhaltung);
			} else
				Helper.LogWarnung("getAlleSichtbarenUnterhaltungenFuerTeilnehmer - sichtbare Unterhaltung ohne Sichtbare Nachricht entdeckt. UnterhaltungsID: "
						+ unterhaltung.getId());

			// Teilnehmer
			Vector<Nutzer> alleTeilnehmer = new Vector<Nutzer>();
			Vector<Integer> alleTeilnehmerIDs = unterhaltungMapper.gibTeilnehmerFuerUnterhaltung(unterhaltung.getId());
			for (Integer teilnehmerID : alleTeilnehmerIDs) {
				alleTeilnehmer.add(getNutzerAnhandID(teilnehmerID));
			}

			unterhaltung.setTeilnehmer(alleTeilnehmer);

			unterhaltung.setAnzeigeHerkunft("Unterhaltungsteilnehmer");
		}

		return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten;
	}

	
	Vector<Unterhaltung> alleUnterhaltungen;
	Vector<Nachricht> alleNachrichten;

	/**
	 * Lade erst alle Nachrichten und Alle Unterhaltungen Erstelle Vector mit
	 * allen Unterhaltungen in denen die Objektatribute Teilnehmer und
	 * Nachrichten bereits befüllt sind
	 */
	@Override
	public Vector<Unterhaltung> getAlleRelevantenUnterhaltungen(int UserID) {
		Vector<Unterhaltung> alleRelevantenUnterhaltungen = new Vector<Unterhaltung>();
		
		Helper.LogInformation("getAlleRelevantenUnterhaltungen - Start");

		long startZeit = System.currentTimeMillis();
		long startZeitTotal = System.currentTimeMillis();

		// Lade alle Unterhaltungen und Nachrichten
		alleUnterhaltungen = unterhaltungMapper.alleUnterhaltungenOhneNachrichten();
		alleNachrichten = nachrichtMapper.gibAlleSichtbarenNachrichten();

		Vector<Nutzer> alleNutzer = getAlleNutzer(true);

		// Dummylisten um NachrichtUnterhaltung und NutzerUnterhaltung
		// zwischentabellen aufzulösen
		// Für jeden teilnehmer einer Unterhaltung wird ein UnterhaltungsDummy
		// mit Teilnehmerdummy erstellt
		Vector<Unterhaltung> alleDummyUnterhaltungenMitTeilnehmer = unterhaltungMapper.gibAlleDummyUnterhaltungenMitDummyTeilnehmer();

		// zwischenliste der Teilnehmer auflösen
		for (Unterhaltung dummyUnterhaltung : alleDummyUnterhaltungenMitTeilnehmer) {
			int UnterhaltungsID = dummyUnterhaltung.getId();
			int TeilnehmerID = dummyUnterhaltung.getTeilnehmer().get(0).getId();

			for (Unterhaltung unterhaltung : alleUnterhaltungen) {
				if (unterhaltung.getId() == UnterhaltungsID) {
					for (Nutzer nutzer : alleNutzer) {
						if (nutzer.getId() == TeilnehmerID) {
							unterhaltung.getTeilnehmer().add(nutzer);
							break;// Nutzer, der der Unterhaltung zugeordnet
									// werden muss wurde gefunden
							// foreach hier beeenden
						}

					}
					break; // Unterhaltung, der der Teilnehmer zugeordnet werden
							// muss wurde gefunden
					// foreach hier beeenden
				}
			}
		}

		// Für jede Nachricht einer Unterhaltung wird ein UnterhaltungsDummy mit
		// Nachricht erstellt
		Vector<Unterhaltung> alleDummyUnterhaltungenMitNachrichten = unterhaltungMapper.gibAlleDummyUnterhaltungenMitDummyNachrichten();
		// zwischenliste der Nachrichten auflösen

		for (Unterhaltung dummyUnterhaltung : alleDummyUnterhaltungenMitNachrichten) {
			int UnterhaltungsID = dummyUnterhaltung.getId();
			int NachrichtenID = dummyUnterhaltung.getAlleNachrichten().get(0).getId();

			for (Unterhaltung unterhaltung : alleUnterhaltungen) {
				if (unterhaltung.getId() == UnterhaltungsID) {
					for (Nachricht nachricht : alleNachrichten) {
						if (nachricht.getId() == NachrichtenID) {
//							nachricht.setVerknuepfteHashtags(hashtagMapper.alleHashtagsZuNachrichtenID(nachricht.getId()));
							unterhaltung.getAlleNachrichten().add(nachricht);
							break;// Nutzer, der der Unterhaltung zugeordnet
									// werden muss wurde gefunden
							// foreach hier beeenden
						}

					}
					break; // Unterhaltung, der der Teilnehmer zugeordnet werden
							// muss wurde gefunden
					// foreach hier beeenden
				}
			}

		}

		// Aussortieren der Unterhaltungen ohne Nachricht
		Vector<Unterhaltung> alleUnterhaltungenVorAussortierung = alleUnterhaltungen;
		alleUnterhaltungen = new Vector<Unterhaltung>();
		for (Unterhaltung unterhaltung : alleUnterhaltungenVorAussortierung) {
			if (unterhaltung.getAlleNachrichten().size() > 0)
				alleUnterhaltungen.add(unterhaltung);
			else
				Helper.LogWarnung("Unterhaltung ohne Nachricht gefunden! UnterhaltungsID:" + unterhaltung.getId());
		}

		Helper.LogDebug("Alle Nachrichten und Unterhaltungen mit Nachrichten und Teilnehmerauflösung " + alleUnterhaltungen.size() + " "
				+ alleNachrichten.size() + " " + getVerstricheneZeitAsString(System.currentTimeMillis() - startZeit));

		// ++++++++++++++++++++++++ Lade Unterhaltungen in denen User Teilnehmer istr  ++++++++++++++++++++++++ 
		for (Unterhaltung unterhaltung : alleUnterhaltungen) {
			boolean istTeilnehmer = false;
			for (Nutzer teilnehmer : unterhaltung.getTeilnehmer()) {
				if (teilnehmer.getId() == UserID) {
					istTeilnehmer = true;
					break;
				}
			}
			
			if(istTeilnehmer){
				unterhaltung.setAnzeigeHerkunft("Unterhaltungsteilnehmer");
				alleRelevantenUnterhaltungen.add(unterhaltung);
			}
		}
		
		// ++++++++++++++++++++++++ Lade öffentliche Unterhaltungen Abonierter Nutzer  ++++++++++++++++++++++++ 

		// Lade ID's abonierter Nutzer
		Vector<Integer> abonnierteNutzerIDs = nutzeraboMapper.vonMirabonnierteNutzerIds(UserID);
		Vector<Unterhaltung> alleOeffentlichenUnterhaltungenAbonnierterNutzer = new Vector<Unterhaltung>();

		for (Unterhaltung u : alleUnterhaltungen) {
			if(u.getUnterhaltungstyp() ==eUnterhaltungsTyp.privat )
				continue;
			
			boolean UnterhaltungEnthaeltMindestensEineNachrichtEinesAboniertenNutzers = false;
			for (Nachricht n : u.getAlleNachrichten()) {
				boolean istNachrichtensenderAbonierterNutzer = false;
				for (Integer abonnierterNutzerID : abonnierteNutzerIDs) {
					if(n.getSenderId() == abonnierterNutzerID){
						istNachrichtensenderAbonierterNutzer = true;
						break;
					}
				}
				
				if(istNachrichtensenderAbonierterNutzer == true){
					UnterhaltungEnthaeltMindestensEineNachrichtEinesAboniertenNutzers = true;
					break;
				}				
			}
			

			if(UnterhaltungEnthaeltMindestensEineNachrichtEinesAboniertenNutzers == true)
				alleOeffentlichenUnterhaltungenAbonnierterNutzer.add(u);
		}		
		
		for (Unterhaltung hinzuZuFuegendeUnterhaltung : alleOeffentlichenUnterhaltungenAbonnierterNutzer) {
			boolean bereitsHinzugefuegt = false;
			for (Unterhaltung unterhaltung : alleRelevantenUnterhaltungen) {
				if(unterhaltung.getId() == hinzuZuFuegendeUnterhaltung.getId()){
					bereitsHinzugefuegt = true;
					break;
				}
			}
			
			if(bereitsHinzugefuegt == false){
				hinzuZuFuegendeUnterhaltung.setAnzeigeHerkunft("Teilnehmerabonement");
				alleRelevantenUnterhaltungen.add(hinzuZuFuegendeUnterhaltung);
			}
			
		}

		// ++++++++++++++++++++++++ Lade unterhaltungen mit aboniertem Hashtag  ++++++++++++++++++++++++ 
		//TODO
//		startZeit = System.currentTimeMillis();
//		Vector<Unterhaltung> OeffentlicheUnterhaltungenAbonierterHashtags = alleUnterhaltungenVonAbonniertemHashtagUeberNutzerId(UserID);
//		Helper.LogInformation("getAlleRelevantenUnterhaltungen - OeffentlicheUnterhaltungenAbonierterHashtags: "
//				+ OeffentlicheUnterhaltungenAbonierterHashtags.size());
//
//		// Füge unterhaltungen mit aboniertem Hashtag dem Vector
//		// alleUnterhaltungen zu (sofern nicht bereits geschehen)
//		for (Unterhaltung unterhaltungAusHashtagAbo : OeffentlicheUnterhaltungenAbonierterHashtags) {
//			boolean bereitsHinzugefuegt = false;
//
//			for (Unterhaltung unterhaltungAusAllen : alleRelevantenUnterhaltungen) {
//				if (unterhaltungAusHashtagAbo.getId() == unterhaltungAusAllen.getId()) {
//					bereitsHinzugefuegt = true;
//					continue;
//				}
//			}
//
//			if (bereitsHinzugefuegt == false)
//				alleRelevantenUnterhaltungen.add(unterhaltungAusHashtagAbo);
//		}
//		Helper.LogDebug(getVerstricheneZeitAsString(System.currentTimeMillis() - startZeit));

		// Sortiere Unterhaltungen nach dem Datum der letzten Nachricht
		// die Sortierlogik dazu ist in
		// de.hdm.tellme.shared.bo.Unterhaltung.java implementiert
		Collections.sort(alleRelevantenUnterhaltungen);

		// Lade Sender-User anhand ID
		// Und weise die Sende-User-Objekte den Nachrichten zu
		Vector<Nutzer> alleUser = getAlleNutzer(false);
		for (Unterhaltung unterhaltung : alleRelevantenUnterhaltungen) {
			for (Nachricht nachricht : unterhaltung.getAlleNachrichten()) {
				for (Nutzer nutzer : alleUser) {
					if (nachricht.getSenderId() == nutzer.getId()) {
						nachricht.setSender(nutzer);
						continue;
					}
				}

				// Wenn kein Nutzer der Nachricht als Sender zugeordnet werden
				// konnte, lade Alle Nutzer erneut aus DB und versuche die
				// Zuordnung erneut
				if (nachricht.getSender() == null) {
					alleUser = getAlleNutzer(true);
					for (Nutzer nutzer : alleUser) {
						if (nachricht.getSenderId() == nutzer.getId()) {
							nachricht.setSender(nutzer);
							continue;
						}
					}

					// Konnte die NutzerID immer noch nicht zugeordnet werden,
					// Erstelle Unknown User (mit abfrage -1) und ordne diesen
					// zu
					nachricht.setSender(getNutzerAnhandID(-1));

				}
			}

		}

		Helper.LogInformation("getAlleRelevantenUnterhaltungen - Fertig, " + alleRelevantenUnterhaltungen.size() + " Unterhaltung(en) gefunden.");

		Helper.LogDebug(getVerstricheneZeitAsString(System.currentTimeMillis() - startZeitTotal));
		return alleRelevantenUnterhaltungen;
	}

	/**
	 * 
	 * Nutzer zu der Unterhaltung hinzufügen
	 * 
	 * @param nutzerID
	 * @param unterhaltungsID
	 * @return boolean zur Überprüfung
	 */
	private boolean nutzerEinerUnterhaltungZuordnen(int nutzerID, int unterhaltungsID) {
		boolean erfolgreich = true;
		if (unterhaltungMapper.istNutzerTeilnehmer(unterhaltungsID, nutzerID))
			unterhaltungMapper.teilnehmerAktualisieren(unterhaltungsID, nutzerID, 1);
		else
			unterhaltungMapper.teilnehmerHinzufuegen(unterhaltungsID, nutzerID);
		return erfolgreich;
	}

	/**
	 * Unterhaltung aktualisieren.
	 * 
	 * @return boolean zur Überprüfung
	 */
	@Override
	public boolean UnterhaltungAktualisieren(Unterhaltung original, Unterhaltung neu) {
		boolean erfolgreich = true;

		// Welche teilnehmer müssen deaktiviert werden?
		Vector<Nutzer> zuLoeschendeTeilnehmer = new Vector<Nutzer>();

		for (Nutzer teilnehmerOriginal : original.getTeilnehmer()) {
			boolean existiertNoch = false;
			for (Nutzer teilnehmerNeu : neu.getTeilnehmer()) {
				if (teilnehmerOriginal.getId() == teilnehmerNeu.getId()) {
					existiertNoch = true;
					break;
				}
			}
			if (existiertNoch == false)
				zuLoeschendeTeilnehmer.add(teilnehmerOriginal);
		}

		// Welche Teilnehmer müssen aktiviert/hinzugefuegt werden?
		Vector<Nutzer> zuHinzuzuguegendeTeilnehmer = new Vector<Nutzer>();

		for (Nutzer teilnehmerNeu : neu.getTeilnehmer()) {
			boolean istNeu = true;
			for (Nutzer teilnehmerOriginal : original.getTeilnehmer()) {
				if (teilnehmerNeu.getId() == teilnehmerOriginal.getId()) {
					istNeu = false;
					break;
				}
			}
			if (istNeu)
				zuHinzuzuguegendeTeilnehmer.add(teilnehmerNeu);
		}

		erfolgreich = unterhaltungMapper.aktualisieren(neu);
		if (erfolgreich) {
			for (Nutzer nutzer : zuHinzuzuguegendeTeilnehmer) {
				nutzerEinerUnterhaltungZuordnen(nutzer.getId(), neu.getId());
			}

			for (Nutzer nutzer : zuLoeschendeTeilnehmer) {
				unterhaltungMapper.teilnehmerAktualisieren(neu.getId(), nutzer.getId(), 0);
			}
		}

		return erfolgreich;
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Unterhaltung-Objekte
	 * ***********************
	 * ****************************************************
	 */

	private String getVerstricheneZeitAsString(long verstricheneZeit) {
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(verstricheneZeit), TimeUnit.MILLISECONDS.toMinutes(verstricheneZeit)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(verstricheneZeit)), TimeUnit.MILLISECONDS.toSeconds(verstricheneZeit)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(verstricheneZeit)));
		return "Benörigte Zeit: " + hms;

	}

}
