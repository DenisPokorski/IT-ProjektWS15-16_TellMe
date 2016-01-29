package de.hdm.tellme.server;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.HashtagAbonnementMapper;
import de.hdm.tellme.server.db.HashtagMapper;
import de.hdm.tellme.server.db.NachrichtMapper;
import de.hdm.tellme.server.db.NutzerAbonnementMapper;
import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.server.db.UnterhaltungMapper;
import de.hdm.tellme.shared.ReportService;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * 
 * Die Klasse <class>ReportServiceImpl </class> implementiert das Interface
 * <code> ReportService </code>. Die Realisierung für ein GWT RPC erfolgt analog
 * zu @link {EditorServiceImpl}. Für Details muss man es dort überprüfen.
 * 
 * 
 * 
 * @author denispokorski
 *
 */
@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements
		ReportService {

	/**
	 * Es ist wichtig, dass die <code>ReportServiceImpl</code> einen
	 * vollständigen Satz an Mappern besitzt, damit sie vollständig mit der
	 * Datenbank kommunizieren kann.
	 */

	public void init() throws IllegalArgumentException {
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.nutzeraboMapper = NutzerAbonnementMapper.nutzerAbonnementMapper();
		this.nachrichtMapper = NachrichtMapper.nachrichtMapper();
		this.unterhaltungMapper = UnterhaltungMapper.unterhaltungMapper();
		this.hashtagAboMapper = HashtagAbonnementMapper.hashtagAbonnementMapper();
		this.hashtagMapper = HashtagMapper.hashtagMapper();

	}

	// ################### MAPPER #####################

	/**
	 * Referenz auf den NutzerAbonnementMapper, der NutzeraAbonnementobjekte mit
	 * der Datenbank abgleicht.
	 */
	private NutzerAbonnementMapper nutzeraboMapper = null;

	/**
	 * Referenz auf den UnterhaltungMapper, der Unterhaltungsobjekte mit der
	 * Datenbank abgleicht.
	 */
	private UnterhaltungMapper unterhaltungMapper = null;

	/**
	 * Referenz auf den NachrichtMapper, der Nachrichtobjekte mit der Datenbank
	 * abgleicht.
	 */
	private NachrichtMapper nachrichtMapper = null;

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

	/**
	 * Ein Vektor namens alleUser wird auf Null gesetzt.
	 */
	private static Vector<Nutzer> alleUser = null;

	/**
	 * Referenz auf den NutzerMapper, der Nutzerobjekte mit der Datenbank
	 * abgleicht.
	 */
	private NutzerMapper nutzerMapper = null;

	/**
	 * Der Report 5 wird generiert
	 * 
	 * @param i
	 *            - Die Nutzer-ID wird übergeben
	 * 
	 * @return report5 - Die Liste der aboonierten Nutzer wird zurückgegeben
	 */
	@Override
	public Vector<Nutzer> report5GenerierenListe(int i) {
		Vector<Nutzer> report5 = nutzeraboMapper.ladeAbonnierendeNutzerListe(i);
		return report5;
	}

	/**
	 * 
	 * Der Report 6 wird generiert
	 * 
	 * @param nutzerId
	 *            - Die Nutzer-ID wird übergeben
	 * @return report6 - Die Liste aller abonnierten Hashtag wird zurückgegeben
	 * 
	 */
	@Override
	public Vector<Hashtag> report6Generieren(int nutzerId) {
		Vector<Hashtag> report6 = hashtagAboMapper.alleHashtagsEinesNutzers(nutzerId);
		return report6;
	}

	/**
	 * Der Report 3 zeigt  alle sichtbaren Unterhaltung vom Ersteller 
	 * 
	 * @param AutorId
	 *            - Die jeweilige Autor-ID wird übergeben
	 * 
	 * @return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten - Ein Vektor
	 *         von Unterhaltung der alle sichtbaren Unterhaltung mit sichtbaren
	 *         Nachrichten zurückgibt
	 * 
	 */
	@Override
	public Vector<Unterhaltung> alleUnterhaltungenFuerAutor(int AutorId) {
		Vector<Unterhaltung> alleSichtbarenUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Unterhaltung> alleSichtbarenUnterhaltungenMitSichtbarenNachrichten = new Vector<Unterhaltung>();

		alleSichtbarenUnterhaltungen = unterhaltungMapper.alleUnterhaltungenFuerAutorOhneNachrichten(AutorId);

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
				Helper.LogWarnung("getAlleSichtbarenUnterhaltungenFuerTeilnehmer - sichtbare Unterhaltung ohne Sichtbare Nachricht entdeckt. UnterhaltungsID: "+ unterhaltung.getId());

			// Teilnehmer
			Vector<Nutzer> alleTeilnehmer = new Vector<Nutzer>();
			Vector<Integer> alleTeilnehmerIDs = unterhaltungMapper.gibTeilnehmerFuerUnterhaltung(unterhaltung.getId());
			for (Integer teilnehmerID : alleTeilnehmerIDs) {
				alleTeilnehmer.add(getNutzerAnhandID(teilnehmerID));
			}
			unterhaltung.setTeilnehmer(alleTeilnehmer);
		}
		return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten;
	}

	/**
	 * Der Report 1 zeigt alle sichtbaren Unterhaltung von einem Autor in einem gewissen
	 * Zeitraum
	 * 
	 * @param AutorId
	 *            - Die jeweilige Autor-ID wird übergeben
	 * @param vonDate
	 *            - Das StartDatum wird übergeben
	 * @param bisDate
	 *            - Das Enddatum wird übergeben
	 * 
	 * @return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten - Es werden
	 *         alle sichtbaren Unterhaltungen und Nachrichten von einem Autor in
	 *         einem bestimmten Zeitraum zurückgegeben
	 */
	@Override
	public Vector<Unterhaltung> alleUnterhaltungenFuerAutorMitZeitraum(
			int AutorId, Timestamp vonDate, Timestamp bisDate) {
		Vector<Unterhaltung> alleSichtbarenUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Unterhaltung> alleSichtbarenUnterhaltungenMitSichtbarenNachrichten = new Vector<Unterhaltung>();
		alleSichtbarenUnterhaltungen = unterhaltungMapper.alleUnterhaltungenFuerAutorOhneNachrichten(AutorId);

		// lade Nachrichten und Teilnehmer zu Unterhaltungen
		for (Unterhaltung unterhaltung : alleSichtbarenUnterhaltungen) {

			// Nachrichten
			Vector<Nachricht> alleNachrichten = ladeAlleNachrichtenZuUnterhaltungMitZeitraum(unterhaltung.getId(), vonDate, bisDate);
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
				Helper.LogWarnung("getAlleSichtbarenUnterhaltungenFuerTeilnehmer - sichtbare Unterhaltung ohne Sichtbare Nachricht entdeckt. UnterhaltungsID: "+ unterhaltung.getId());

			// Teilnehmer
			Vector<Nutzer> alleTeilnehmer = new Vector<Nutzer>();
			Vector<Integer> alleTeilnehmerIDs = unterhaltungMapper.gibTeilnehmerFuerUnterhaltung(unterhaltung.getId());
			
			for (Integer teilnehmerID : alleTeilnehmerIDs) {
				alleTeilnehmer.add(getNutzerAnhandID(teilnehmerID));
			}
			unterhaltung.setTeilnehmer(alleTeilnehmer);
		}
		return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten;
	}

	/**
	 * 
	 * Report 2 zeigt alle Nachrichten ein einem bestimmten Zeitraum
	 * 
	 * @param vonDate
	 *            - Das StartDatum wird übergeben
	 * @param bisDate
	 *            - Das Enddatum wird übergeben
	 * 
	 * @return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten - Es werden
	 *         alle sichtbaren Unterhaltungen und Nachrichten zurückgegeben in
	 *         einem bestimmten Zeitraum zurückgegeben
	 * 
	 */
	@Override
	public Vector<Unterhaltung> alleUnterhaltungenMitZeitraum(
			Timestamp vonDate, Timestamp bisDate) {
		Vector<Unterhaltung> alleSichtbarenUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Unterhaltung> alleSichtbarenUnterhaltungenMitSichtbarenNachrichten = new Vector<Unterhaltung>();
		alleSichtbarenUnterhaltungen = unterhaltungMapper
				.alleUnterhaltungenOhneNachrichtenNachDatumLetzterNachricht();

		// lade Nachrichten und Teilnehmer zu Unterhaltungen
		for (Unterhaltung unterhaltung : alleSichtbarenUnterhaltungen) {

			// Nachrichten
			Vector<Nachricht> alleNachrichten = ladeAlleNachrichtenZuUnterhaltungMitZeitraum(
					unterhaltung.getId(), vonDate, bisDate);
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
					alleSichtbarenUnterhaltungenMitSichtbarenNachrichten
							.add(unterhaltung);
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
		}
		return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten;
	}

	/**
	 * Der Report 4 zeigt alle Nachrichten 
	 * 
	 * @return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten - Es werden
	 *         alle sichtbaren Unterhaltungen und Nachrichten zurückgegeben
	 */

	@Override
	public Vector<Unterhaltung> alleUnterhaltungen() {
		Vector<Unterhaltung> alleSichtbarenUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Unterhaltung> alleSichtbarenUnterhaltungenMitSichtbarenNachrichten = new Vector<Unterhaltung>();
		alleSichtbarenUnterhaltungen = unterhaltungMapper.alleUnterhaltungenOhneNachrichtenNachDatumLetzterNachricht();

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
		}
		return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten;
	}

	/**
	 * Hole alle Nachrichten einer bestimmten Unterhaltung
	 * 
	 * @return alleNachrichten - Es werden alle Nachrichtne einer bestimmten
	 *         Unterhaltung-ID zurückgegeben
	 */

	@Override
	public Vector<Nachricht> ladeAlleNachrichtenZuUnterhaltung(
			int UnterhaltungsID) {
		Vector<Nachricht> alleNachrichten = nachrichtMapper.gibAlleNachrichtenVonUnterhaltungReport(UnterhaltungsID);

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
	 * 
	 * 
	 * Hole alle Nachrichten einer bestimmten Unterhaltung in einem gewissen
	 * Zeitraum
	 * @param UnterhaltungsID
	 *            - Die jeweilige Unterhaltungs-ID wird übergeben
	 * @param vonDate
	 *            - Das StartDatum wird übergeben
	 * @param bisDate
	 *            - Das Enddatum wird übergeben return alleNachrichten - Es
	 *            werden alle Nachrichtne einer bestimmten Unterhaltung-ID in
	 *            einem bestimmten Zeitraum zurückgegeben
	 */

	@Override
	public Vector<Nachricht> ladeAlleNachrichtenZuUnterhaltungMitZeitraum(
			int UnterhaltungsID, Timestamp vonDate, Timestamp bisDate) {
		Vector<Nachricht> alleNachrichten = nachrichtMapper.gibAlleNachrichtenVonUnterhaltungReportMitZeitraum(
						UnterhaltungsID, vonDate, bisDate);

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
	 * 
	 * Hole den Nutzer anhand der Nuzter-ID
	 * 
	 * @param nutzerID
	 *            - Die jeweilge Nutzer-ID wird übergeben
	 * @return gesuchterNutzer - Der gesuchte Nutzer wird zurückgegeben
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
	 * Hole alle Nutzer
	 * 
	 * @param zwingeNeuladen
	 * 
	 * @return alleUser - Es werden alle Nutzer zurückgegeben
	 * 
	 */
	@Override
	public Vector<Nutzer> getAlleNutzer(boolean zwingeNeuladen) {
		if (alleUser == null || zwingeNeuladen)
			alleUser = nutzerMapper.alleNutzer(0);
		return alleUser;
	}

	/**
	 * Report 8 generieren
	 * 
	 * @param i
	 *            - Nutzer-ID wird übergeben
	 * @return alleFollowerEinesHashtagsListe - Eine List aller Follower eines
	 *         Hashtags werden zurückgegeben
	 */

	@Override
	public Vector<Nutzer> report8Generieren(int i) {
		Vector<Nutzer> alleFollowerEinesHashtagsListe = new Vector<Nutzer>();
		alleFollowerEinesHashtagsListe = hashtagAboMapper.alleFollowerEinesHashtags(i);
		return alleFollowerEinesHashtagsListe;
	}

	/**
	 * Report 7 generieren
	 * 
	 * @param i
	 *            - Nutzer-ID wird übergeben
	 * 
	 * @return alleFollowerEinesNutzersListe - Eine Liste allre Follower eines
	 *         Nutzer werden zurückgegeben
	 */

	@Override
	public Vector<Nutzer> report7Generieren(int i) {
		Vector<Nutzer> alleFollowerEinesNutzersListe = new Vector<Nutzer>();
		alleFollowerEinesNutzersListe = nutzeraboMapper.followerEinesNutzers(i);
		return alleFollowerEinesNutzersListe;
	}

}
