package de.hdm.tellme.server;

import java.util.Collections;
import java.util.Vector;

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

@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements
		EditorService {
	public EditorServiceImpl() throws IllegalArgumentException {

	}

	public void init() throws IllegalArgumentException {
		if (Helper.debugModus)
			Helper.LogDebug("HELLO TELLME! - DEBUG MODE ON");
		else
			Helper.LogDebug("HELLO TELLME! - DEBUG MODE OFF");

		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.nutzeraboMapper = NutzerAbonnementMapper.nutzerAbonnementMapper();
		this.nachrichtMapper = NachrichtMapper.nachrichtMapper();
		this.unterhaltungMapper = UnterhaltungMapper.unterhaltungMapper();
		this.hashtagAboMapper = HashtagAbonnementMapper
				.hashtagAbonnementMapper();
		this.hashtagMapper = HashtagMapper.hashtagMapper();

	}

	// ################### MAPPER #####################
	private NutzerMapper nutzerMapper = null;
	private NutzerAbonnementMapper nutzeraboMapper = null;

	private NachrichtMapper nachrichtMapper = null;
	private UnterhaltungMapper unterhaltungMapper = null;

	private HashtagAbonnementMapper hashtagAboMapper = null;
	private HashtagMapper hashtagMapper = null;

	// ################### LISTEN #####################

	private static Vector<Nutzer> alleUser = null;

	// ################### NUTZER #####################

	public int nutzerAnlegen(Nutzer nutzer) {
		int ergebnis = nutzerMapper.anlegen(nutzer);
		return ergebnis;
	}

	public void nutzerAktualisieren(Nutzer nutzer) {
		nutzerMapper.aktualisieren(nutzer);
	}

	public void nutzerLoeschen(Nutzer nutzer) {
		nutzerMapper.entfernen(nutzer);
	}

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
			n.setVorname("Unknown");
			n.setNachname("User");
			return n;

		}

		return gesuchterNutzer;

	}

	@Override
	public Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId) {

		Vector<Nutzer> alleNutzeAuserMir = new Vector<Nutzer>();

		for (Nutzer nutzer : this.getAlleNutzer(false)) {
			if (nutzer.getId() != meineId)
				alleNutzeAuserMir.add(nutzer);
		}

		return alleNutzeAuserMir;
	}

	@Override
	public Vector<Nutzer> getAlleNutzer(boolean zwingeNeuladen) {
		if (alleUser == null || zwingeNeuladen)
			alleUser = nutzerMapper.alleNutzer(0);

		return alleUser;

	}

	// ################### NutzerABO #####################

	@Override
	public void nutzerAbonnementLoeschen(int aboNehmerID, Nutzer aboGeber) {
		nutzeraboMapper.loescheNutzeraboById(aboNehmerID, aboGeber);
	}

	@Override
	public void nutzerAbonnementErstellen(int aboNehmerID, Nutzer aboGeber) {
		nutzeraboMapper.nutzerAboErstellen(aboNehmerID, aboGeber);

	}

	/**
	 * @Deprecated bitte benutzen!
	 */
	@Override
	public Vector<Integer> holeAlleAbonniertenNutzer(int meineId) {
		Vector<Integer> alleAbonniertenNutzer = nutzerMapper
				.alleAbonniertenNutzer(meineId);

		return alleAbonniertenNutzer;
	}

	public Vector<Nutzer> getAbonierteNutzer(int aboNehmerID) {
		// TODO: Implementieren - aus DB liste an ID's laden und anschließend
		// auf user der alleUse liste mappen
		return null;
	}

	public Vector<Nutzer> getNichtAbonierteNutzer(int aboNehmerID) {
		// TODO: Implementieren, einfach getAlleNutzer und getAbonierteNutzer
		// ausführen und voneinander abziehen
		return null;
	}

	// ################### HASHTAG #####################

	@Override
	public Vector<Hashtag> gibAlleHashtags() {
		Vector<Hashtag> alleHashtags = hashtagAboMapper.gibALleHashtags();
		return alleHashtags;
	}

	// TODO: Ovverride
	@Override
	public void hashtagEntfernen(Hashtag hashtag) {
		nachrichtMapper.alleHashtagZuordnungLoeschen(hashtag.getId());
		nutzerMapper.alleHashtagZuordnungLoeschen(hashtag);
		hashtagMapper.entfernen(hashtag);
	}

	@Override
	public void hashtagAktualisieren(Hashtag hashtag) {
		hashtagMapper.aktualisieren(hashtag);
	}

	@Override
	public void hashtagErstellen(Hashtag hashtag) {
		hashtagMapper.erstellen(hashtag);
	}

	// ################### HashtagABO #####################

	@Override
	public Vector<Integer> getAlleAbonniertenHashtagsfuerAbonehmer(
			int aboNehmerId) {
		Vector<Integer> alleAbboniertenNutzer = hashtagAboMapper
				.ladeAbonnierteHashtagListe(aboNehmerId);
		return alleAbboniertenNutzer;
	}

	@Override
	public void erstellenHashtagAbo(int NutzerId, int HashtagId) {
		hashtagAboMapper.hashtagAboErstellen(NutzerId, HashtagId);
	}

	@Override
	public Vector<Hashtag> getAbonnierteHashtags(int aboNehmerID) {
		Vector<Hashtag> alleHashtags = hashtagAboMapper
				.alleHashtagsEinesNutzers(aboNehmerID);
		return alleHashtags;
	}

	@Override
	public void hashtagAboErstellen(int nutzerId, int hashtagId) {
		hashtagAboMapper.hashtagAboErstellen(nutzerId, hashtagId);
	}

	@Override
	public void hashtagAboEntfernen(int nutzerId, int hashtagId) {
		hashtagAboMapper.HashtagAboEntfernen(nutzerId, hashtagId);
	}

	// ################### NACHRICHT #####################

	@Override
	public Vector<Nachricht> ladeAlleNachrichtenZuUnterhaltung(
			int UnterhaltungsID) {
		Vector<Nachricht> alleNachrichten = nachrichtMapper
				.gibAlleNachrichtenVonUnterhaltung(UnterhaltungsID);

		// lade zu jeder Nachricht den Sender und die Hashtags
		if (alleNachrichten.size() > 0) {
			for (Nachricht nachricht : alleNachrichten) {
				Vector<Hashtag> alleHashtagsZuNachricht = hashtagMapper
						.alleHashtagsZuNachrichtenID(nachricht.getId());
				nachricht.setVerknuepfteHashtags(alleHashtagsZuNachricht);

				Nutzer sender = null;
				sender = getNutzerAnhandID(nachricht.getSenderId());
				nachricht.setSender(sender);

			}
		}
		return alleNachrichten;
	}

	@Override
	public Vector<Unterhaltung> alleUnterhaltungenVonAbonniertemHashtagUeberNutzerId(
			int nutzerId) {
		Vector<Nachricht> nachrichtenListe = new Vector<Nachricht>();
		Vector<Unterhaltung> unterhaltungsListe = new Vector<Unterhaltung>();
		Vector<Integer> hashtagIds = hashtagAboMapper
				.selektiereAlleHashtagsNachAbonehmer(nutzerId);
		for (Integer integer : hashtagIds) {
			nachrichtenListe = nachrichtMapper
					.gibNachrichtenVonHashtagId(integer);
		}

		for (Nachricht nachricht : nachrichtenListe) {
			Unterhaltung uH = new Unterhaltung();
			uH = unterhaltungMapper
					.selektiereUnterhaltungenVonNachrichtId(nachricht);

			if (uH.getUnterhaltungstyp() == eUnterhaltungsTyp.oeffentlich) {
				Unterhaltung unterHaltungInListe = null;

				for (Unterhaltung unterhaltung : unterhaltungsListe) {
					if (unterhaltung.getId() == uH.getId()) {
						unterHaltungInListe = unterhaltung;
						continue;
					}
				}

				if (unterHaltungInListe != null) {
					// ist in Unterhaltungsliste
					unterHaltungInListe.getAlleNachrichten().add(nachricht);
				} else {
					// existiert noch nicht
					Vector<Nachricht> alleNachrichtenDerUnterhaltungMitHashtag = new Vector<Nachricht>();
					alleNachrichtenDerUnterhaltungMitHashtag.add(nachricht);
					uH.setAlleNachrichten(alleNachrichtenDerUnterhaltungMitHashtag);
					unterhaltungsListe.add(uH);

				}

			}

		}

		return unterhaltungsListe;
	}

	private int nachricht_erstellen(Nachricht n) {
		int neueNachrichtID = -1;
		neueNachrichtID = nachrichtMapper.anlegen(n);
		if (neueNachrichtID != -1) {
			for (Hashtag hashtag : n.getVerknuepfteHashtags()) {
				nachrichtMapper.hashtagEinerNachrichtZuordnen(hashtag.getId(),
						neueNachrichtID);

			}
		}
		return neueNachrichtID;
	}

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
				nachrichtMapper.hashtagZuordnungLoeschen(hashtag.getId(),
						neu.getId());
			}
			for (Hashtag hashtag : ZuHinzuzufuegendeHashtags) {
				nachrichtMapper.hashtagEinerNachrichtZuordnen(hashtag.getId(),
						neu.getId());
			}

		}

		return erfolgreich;
	}

	@Override
	public boolean NachrichtLoeschen(Nachricht n) {
		boolean erfolgreich = true;
		erfolgreich = nachrichtMapper.entfernen(n.getId());
		return erfolgreich;
	}

	// ################### UNTERHALTUNG #####################

	@Override
	public boolean unterhaltung_loeschen(int unterhaltungsID) {
		boolean ergebnis = false;
		ergebnis = unterhaltungMapper
				.loescheUnterhaltungAnhandID(unterhaltungsID);
		return ergebnis;
	}

	/**
	 * @Deprecated namensgeb?!
	 */
	@Deprecated
	@Override
	public Vector<Unterhaltung> meineUnterhaltungenMitSichtbarkeit(int meineId) {
		Vector<Unterhaltung> meineUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Integer> meineUnterhaltungenIds = unterhaltungMapper
				.meineUnterhaltungen(meineId);

		for (Integer unterhaltungsId : meineUnterhaltungenIds) {
			System.out
					.println("meineUnterhaltungenMitSichtbarkeit - geladene Unterhaltung: "
							+ unterhaltungsId);
			meineUnterhaltungen.add(unterhaltungMapper
					.gibNachrichtenIdsZuUnterhaltungsId(unterhaltungsId));
		}

		return meineUnterhaltungen;
	}

	@Override
	public Vector<Unterhaltung> alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(
			int teilnehmerID) {
		Vector<Unterhaltung> Unterhaltungen = null;
		Unterhaltungen = unterhaltungMapper
				.alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(teilnehmerID);
		return Unterhaltungen;
	}

	@Override
	public boolean unterhaltungStarten(Nachricht ersteNachricht,
			Vector<Nutzer> teilnehmer) {
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
				unterhaltungMapper.teilnehmerHinzufuegen(unterhaltungsID,
						nutzer.getId());
			}

			int nachrichtenID = -1;
			nachrichtenID = nachricht_erstellen(ersteNachricht);
			if (nachrichtenID != -1) {
				nachrichtMapper.nachrichtEinerUnterhaltungZuordnen(
						nachrichtenID, unterhaltungsID);

			} else {
				erfolgreich = false;
			}
		} else {
			erfolgreich = false;
		}

		return erfolgreich;
	}

	@Override
	public boolean unterhaltungBeantworten(Nachricht antwortNachricht,
			Unterhaltung unterhaltung) {
		boolean erfolgreich = true;

		int nachrichtenID = -1;
		nachrichtenID = nachricht_erstellen(antwortNachricht);
		if (nachrichtenID != -1) {
			if (nachrichtMapper.nachrichtEinerUnterhaltungZuordnen(
					nachrichtenID, unterhaltung.getId())) {
				// Nachricht wurde erfolgreich zugeordnet
				nutzerEinerUnterhaltungZuordnen(antwortNachricht.getSenderId(),
						unterhaltung.getId());
			}

		} else {
			erfolgreich = false;
		}

		return erfolgreich;
	}

	@Override
	public Vector<Unterhaltung> oeffentlicheUnterhaltungenAbonnierterNutzer(
			int meineId) {
		Unterhaltung uH = new Unterhaltung();
		Vector<Nachricht> nachrichtenListex = new Vector<Nachricht>();
		Vector<Nachricht> NachrichtenListe = new Vector<Nachricht>();
		Vector<Integer> abonnierteNutzer = nutzeraboMapper
				.vonMirabonnierteNutzerIds(meineId);
		Vector<Unterhaltung> unterhaltungsListe = new Vector<Unterhaltung>();

		for (Integer integer : abonnierteNutzer) {
			NachrichtenListe = nachrichtMapper
					.selektiereNachrichtenVonId(integer);

		}

		for (Nachricht nachricht : NachrichtenListe) {

			uH = unterhaltungMapper
					.selektiereUnterhaltungenVonNachrichtId(nachricht);
			if (!unterhaltungsListe.contains(uH.getId()))
				if (uH.getUnterhaltungstyp() == eUnterhaltungsTyp.oeffentlich) {

					nachrichtenListex = nachrichtMapper
							.gibAlleNachrichtenVonUnterhaltung(uH.getId());
					for (Nachricht nachricht2 : nachrichtenListex) {
						Vector<Hashtag> meineHashtags = new Vector<Hashtag>();
						meineHashtags = hashtagMapper
								.alleHashtagsZuNachrichtenID(nachricht2.getId());
						nachricht2.setVerknuepfteHashtags(meineHashtags);

					}

				}
			uH.setAlleNachrichten(nachrichtenListex);
			unterhaltungsListe.add(uH);
		}

		return unterhaltungsListe;
	}

	@Override
	public boolean UnterhaltungVerlassen(Unterhaltung u, int nutzerId) {
		// TODO: letzter Telnehmer? -> Unterhaltung als inaktiv makieren
		boolean erfolgreich = true;
		erfolgreich = unterhaltungMapper.teilnehmerAktualisieren(u.getId(),
				nutzerId, 0);
		return erfolgreich;
	}

	@Override
	public Vector<Unterhaltung> getAlleSichtbarenUnterhaltungenFuerTeilnehmer(
			int aktiverTeilnehmerID) {
		Vector<Unterhaltung> alleSichtbarenUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Unterhaltung> alleSichtbarenUnterhaltungenMitSichtbarenNachrichten = new Vector<Unterhaltung>();

		alleSichtbarenUnterhaltungen = unterhaltungMapper
				.alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(aktiverTeilnehmerID);

		// lade Nachrichten und Teilnehmer zu Unterhaltungen
		for (Unterhaltung unterhaltung : alleSichtbarenUnterhaltungen) {

			// Nachrichten
			Vector<Nachricht> alleNachrichten = ladeAlleNachrichtenZuUnterhaltung(unterhaltung
					.getId());
			unterhaltung.setAlleNachrichten(alleNachrichten);

			// fuege nur Unterhaltungen mit mind. 1 Nachricht hinzu.
			if (alleNachrichten.isEmpty() == false) {
				boolean bereitsHinzugefuegt = false;
				// Pruefe ob Unterhaltung bereits der Listehinzugefügt wurde
				for (Unterhaltung unterhaltungInEntgueltigerListe : alleSichtbarenUnterhaltungenMitSichtbarenNachrichten) {
					if (unterhaltungInEntgueltigerListe.getId() == unterhaltung
							.getId())
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
			Vector<Integer> alleTeilnehmerIDs = unterhaltungMapper
					.gibTeilnehmerFuerUnterhaltung(unterhaltung.getId());
			for (Integer teilnehmerID : alleTeilnehmerIDs) {
				alleTeilnehmer.add(getNutzerAnhandID(teilnehmerID));
			}

			unterhaltung.setTeilnehmer(alleTeilnehmer);

		}

		return alleSichtbarenUnterhaltungenMitSichtbarenNachrichten;
	}

	@Override
	public Vector<Unterhaltung> getAlleRelevantenUnterhaltungen(int UserID) {
		Helper.LogInformation("getAlleRelevantenUnterhaltungen - Start");
		Vector<Unterhaltung> alleUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Unterhaltung> TeilnehmendeUnterhaltungen = getAlleSichtbarenUnterhaltungenFuerTeilnehmer(UserID);

		Helper.LogInformation("getAlleRelevantenUnterhaltungen - TeilnehmendeUnterhaltungen: "
				+ TeilnehmendeUnterhaltungen.size());
		alleUnterhaltungen = TeilnehmendeUnterhaltungen;

		Vector<Unterhaltung> OeffentlicheUnterhaltungenAbonierterNutzer = oeffentlicheUnterhaltungenAbonnierterNutzer(UserID);
		for (Unterhaltung nutzerabo : OeffentlicheUnterhaltungenAbonierterNutzer) {
			boolean bereitsHinzugefuegt = false;

			for (Unterhaltung alle : alleUnterhaltungen) {
				if (nutzerabo.getId() == alle.getId())
					bereitsHinzugefuegt = true;
			}

			if (bereitsHinzugefuegt == false)
				alleUnterhaltungen.add(nutzerabo);
		}

		Vector<Unterhaltung> OeffentlicheUnterhaltungenAbonierterHashtags = alleUnterhaltungenVonAbonniertemHashtagUeberNutzerId(UserID);
		for (Unterhaltung hashtagabo : OeffentlicheUnterhaltungenAbonierterHashtags) {
			boolean bereitsHinzugefuegt = false;

			for (Unterhaltung alle : alleUnterhaltungen) {
				if (hashtagabo.getId() == alle.getId())
					bereitsHinzugefuegt = true;
			}

			if (bereitsHinzugefuegt == false)
				alleUnterhaltungen.add(hashtagabo);
		}

		Collections.sort(alleUnterhaltungen);

		// Lade Sender-User anhand ID'S

		Vector<Nutzer> alleUser = getAlleNutzer(false);
		for (Unterhaltung unterhaltung : alleUnterhaltungen) {
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
					// Erstelle Unknown User und ordne diesen zu
					nachricht.setSender(getNutzerAnhandID(-1));

				}
			}

		}

		Helper.LogInformation("getAlleRelevantenUnterhaltungen - Fertig, "
				+ alleUnterhaltungen.size() + " Unterhaltung(en) gefunden.");
		return alleUnterhaltungen;
	}

	private boolean nutzerEinerUnterhaltungZuordnen(int nutzerID,
			int unterhaltungsID) {
		boolean erfolgreich = true;
		if (unterhaltungMapper.istNutzerTeilnehmer(unterhaltungsID, nutzerID))
			unterhaltungMapper.teilnehmerAktualisieren(unterhaltungsID,
					nutzerID, 1);
		else
			unterhaltungMapper.teilnehmerHinzufuegen(unterhaltungsID, nutzerID);
		return erfolgreich;
	}

	public boolean UnterhaltungAktualisieren(Unterhaltung original,
			Unterhaltung neu) {
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
				unterhaltungMapper.teilnehmerAktualisieren(neu.getId(),
						nutzer.getId(), 0);
			}
		}

		return erfolgreich;
	}

	// ################### NEU #####################
}
