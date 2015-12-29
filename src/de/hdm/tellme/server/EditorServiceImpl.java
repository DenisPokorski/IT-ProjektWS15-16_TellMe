package de.hdm.tellme.server;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.server.db.NachrichtMapper;
import de.hdm.tellme.server.db.HashtagAbonnementMapper;
import de.hdm.tellme.server.db.HashtagMapper;
import de.hdm.tellme.server.db.NutzerAbonnementMapper;
import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.server.db.UnterhaltungMapper;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.bo.*;
import de.hdm.tellme.shared.bo.Unterhaltung.eUnterhaltungsTyp;

@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements EditorService {
	public EditorServiceImpl() throws IllegalArgumentException {

	}

	public void init() throws IllegalArgumentException {
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.nutzeraboMapper = NutzerAbonnementMapper.nutzerAbonnementMapper();
		this.nachrichtMapper = NachrichtMapper.nachrichtMapper();
		this.unterhaltungMapper = UnterhaltungMapper.unterhaltungMapper();
		this.hashtagAboMapper = HashtagAbonnementMapper.hashtagAbonnementMapper();
		this.hashtagMapper = HashtagMapper.hashtagMapper();

	}

	private NutzerMapper nutzerMapper = null;
	private NutzerAbonnementMapper nutzeraboMapper = null;

	private NachrichtMapper nachrichtMapper = null;
	private UnterhaltungMapper unterhaltungMapper = null;

	private NutzerAbonnementMapper naMapper = null;
	private HashtagAbonnementMapper hashtagAboMapper = null;
	private HashtagMapper hashtagMapper = null;

	public void nutzerAnlegen(Nutzer na) {
		Nutzer n = new Nutzer();
		n.setVorname(na.getVorname());
		n.setNachname(na.getNachname());
		n.setMailadresse(na.getMailadresse());
		nutzerMapper.anlegen(n);
	}

	public void nutzerAktualisieren(Nutzer n) {
		nutzerMapper.aktualisieren(n);
	}

	public void nutzerLoeschen(Nutzer n) {
		nutzerMapper.entfernen(n);
	}

	public Nutzer getNutzerVonId(Nutzer na) {
		Nutzer n = new Nutzer();
		n = nutzerMapper.suchenNutzerMitId(n);
		return n;
	}

	@Override
	public Vector<Nutzer> getZuAbonnieredeLoeschenNutzerListe(int i) {
		Vector<Nutzer> alleAbboniertenNutzer = nutzeraboMapper.ladeAbonnierendeNutzerListe(i);
		return alleAbboniertenNutzer;
	}

	@Override
	public Vector<Integer> ladeAbonnierendeHashtagListe(int i) {
		Vector<Integer> alleAbboniertenNutzer = hashtagAboMapper.ladeAbonnierteHashtagListe(i);
		return alleAbboniertenNutzer;
	}

	@Override
	public Vector<Nutzer> getNochNichtAbonnenteNutzerListe(int meineId) {
		return null;
	}

	// death

	@Override
	public Vector<Nutzer> getAlleNochNichtAbonnierteNutzerListe(int id) {
		// int id =7;
		Vector<Nutzer> alleNutzer = nutzeraboMapper.alleNochNichtAbonnierteNutzerSelektieren(id);
		return alleNutzer;
	}

	@Override
	public Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId) {
		Vector<Nutzer> alleNutzer = nutzerMapper.alleNutzerAusserMeineId(meineId);
		return alleNutzer;
	}

	@Deprecated
	@Override
	public void NachrichtErstellen(Nachricht n) {
		nachrichtMapper.anlegen(n);
	}

	@Deprecated
	@Override
	public void nachrichtUnterhaltungZuweisen(String txt, int uId, Timestamp ts) {

	}

	@Override
	public Vector<Hashtag> getZuAbonnierendeLoeschenHashtagAboListe(int meineId) {
		Vector<Hashtag> alleAbboniertenHashtags = hashtagAboMapper.ladeAbonnierendeHashtagListe(meineId);
		return alleAbboniertenHashtags;
	}

	@Override
	public void erstellenHashtagAboById(int NutzerId, int HashtagId) {
		hashtagAboMapper.hashtagAboErstellen(NutzerId, HashtagId);
	}

	@Override
	public Vector<Hashtag> getAlleNochNichtAbonnierteHashtagAboListe(int i) {
		Vector<Hashtag> alleHashtags = hashtagAboMapper.alleNochNichtAboonierteHashtagsSelektieren(i);
		naMapper.alleNochNichtAbonnierteNutzerSelektieren(i);
		return alleHashtags;
	}

	@Override
	public Vector<Hashtag> gibHashtagListe() {
		Vector<Hashtag> alleHashtags = hashtagAboMapper.gibALleHashtags();
		return alleHashtags;
	}

	@Override
	public void hashtagAboErstellen(int nutzerId, int hashtagId) {
		hashtagAboMapper.hashtagAboErstellen(nutzerId, hashtagId);
	}

	@Override
	public Vector<Integer> holeAlleAbonniertenNutzer(int meineId) {
		Vector<Integer> alleAbonniertenNutzer = nutzerMapper.alleAbonniertenNutzer(meineId);

		return alleAbonniertenNutzer;
	}

	@Override
	public void nutzerAbonnementLoeschen(int id, Nutzer _nutzerDeabonieren) {
		nutzeraboMapper.loescheNutzeraboById(id, _nutzerDeabonieren);

	}

	@Override
	public void nutzerAbonnementErstellen(int vonId, Nutzer _nutzer) {
		nutzeraboMapper.nutzerAboErstellen(vonId, _nutzer);

	}

	@Override
	public void hashtagEntfernen(Hashtag hashtag) {
		nachrichtMapper.hashtagZuordnungLoeschen(hashtag);
		nutzerMapper.hashtagZuordnungLoeschen(hashtag);
		hashtagMapper.entfernen(hashtag);
	}

	@Override
	public void hashtagSpeichern(Hashtag hashtag) {
		hashtagMapper.aktualisieren(hashtag);
	}

	@Override
	public void hashtagErstellen(Hashtag hashtag) {
		hashtagMapper.erstellen(hashtag);
	}

	public void hashtagAboEntfernen(int nutzerId, int hashtagId) {
		hashtagAboMapper.HashtagAboEntfernen(nutzerId, hashtagId);
	}

	@Override
	public boolean unterhaltung_loeschen(int unterhaltungsID) {
		boolean ergebnis = false;
		ergebnis = unterhaltungMapper.loescheUnterhaltungAnhandID(unterhaltungsID);
		return ergebnis;
	}

	@Override
	public Vector<Unterhaltung> alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(int teilnehmerID) {
		Vector<Unterhaltung> Unterhaltungen = null;
		Unterhaltungen = unterhaltungMapper.alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(teilnehmerID);
		return Unterhaltungen;
	}

	@Override
	public boolean unterhaltungStarten( Nachricht ersteNachricht, Vector<Nutzer> teilnehmer) {
		boolean erfolgreich = true;
		int unterhaltungsID = -1;
		unterhaltungsID = unterhaltungMapper.anlegen(eUnterhaltungsTyp.oeffentlich);
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

	@Override
	public Vector<Unterhaltung> meineUnterhaltungenMitSichtbarkeit(int meineId) {
		Vector<Unterhaltung> meineUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Integer> meineUnterhaltungenIds = unterhaltungMapper.meineUnterhaltungen(meineId);

		for (Integer unterhaltungsId : meineUnterhaltungenIds) {
			System.out.println(unterhaltungsId);
			meineUnterhaltungen.add(unterhaltungMapper.gibNachrichtenIdsZuUnterhaltungsId(unterhaltungsId));
		}

		return meineUnterhaltungen;
	}

	@Override
	public Vector<Unterhaltung> oeffentlicheUnterhaltungenAbonnierterNutzer(int meineId) {

		Vector<Nachricht> NachrichtenListe = new Vector<Nachricht>();
		Vector<Integer> abonnierteNutzer = nutzeraboMapper.vonMirabonnierteNutzerIds(meineId);
		Vector<Unterhaltung> unterhaltungsListe = new Vector<Unterhaltung>();

		for (Integer integer : abonnierteNutzer) {
			NachrichtenListe = nachrichtMapper.selektiereNachrichtenVonId(integer);
		}

		for (Nachricht nachricht : NachrichtenListe) {
			Unterhaltung uH = new Unterhaltung();
			uH = unterhaltungMapper.selektiereUnterhaltungenVonNachrichtId(nachricht);
			if (!unterhaltungsListe.contains(uH.getId()))
				if (uH.getUnterhaltungstyp() == eUnterhaltungsTyp.oeffentlich)
					uH.setAlleNachrichten(nachrichtMapper.gibAlleNachrichtenVonUnterhaltung(uH.getId()));
			unterhaltungsListe.add(uH);
		}

		return unterhaltungsListe;
	}

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

	@Override
	public Vector<Unterhaltung> alleAbonniertenHashtagNachrichtenVonId(int nutzerId) {
		Vector<Nachricht> nachrichtenListe = new Vector<Nachricht>();
		Vector<Unterhaltung> unterhaltungsListe = new Vector<Unterhaltung>();
		Vector<Integer> hashtagIds = hashtagAboMapper.selektiereAlleHashtagsNachAbonehmer(nutzerId);
		for (Integer integer : hashtagIds) {
			nachrichtenListe = nachrichtMapper.gibNachrichtenVonHashtagId(integer);
		}

		for (Nachricht nachricht : nachrichtenListe) {
			Unterhaltung uH = new Unterhaltung();
			uH = unterhaltungMapper.selektiereUnterhaltungenVonNachrichtId(nachricht);

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

	@Override
	public Vector<Unterhaltung> ladeAlleNachrichtenZuUnterhaltung(int meineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean NachrichtAktualisieren(Nachricht n) {
		boolean erfolgreich = true;
		erfolgreich = nachrichtMapper.aktualisieren(n);
		return erfolgreich;
	}

	@Override
	public boolean NachrichtLoeschen(Nachricht n) {
		boolean erfolgreich = true;
		erfolgreich = nachrichtMapper.entfernen(n.getId());
		return erfolgreich;
	}

	@Override
	public boolean UnterhaltungVerlassen(Unterhaltung u) {
		boolean erfolgreich = true;
		erfolgreich = unterhaltungMapper.teilnehmerAktualisieren(u.getId(), TellMe.gibEingeloggterBenutzer().getUser().getId(), 0);
		return erfolgreich;
	}

	@Override
	public Vector<Unterhaltung> getAlleRelevantenUnterhaltungen(int UserID) {
		Vector<Unterhaltung> alleUnterhaltungen = new Vector<Unterhaltung>();
		Vector<Unterhaltung> TeilnehmendeUnterhaltungen = meineUnterhaltungenMitSichtbarkeit(UserID);
		alleUnterhaltungen = TeilnehmendeUnterhaltungen;

//		Vector<Unterhaltung> OeffentlicheUnterhaltungenAbonierterNutzer = oeffentlicheUnterhaltungenAbonnierterNutzer(UserID);
//		for (Unterhaltung nutzerabo : OeffentlicheUnterhaltungenAbonierterNutzer) {
//			boolean bereitsHinzugefuegt = false;
//
//			for (Unterhaltung alle : alleUnterhaltungen) {
//				if (nutzerabo.getId() == alle.getId())
//					bereitsHinzugefuegt = true;
//			}
//
//			if (bereitsHinzugefuegt == false)
//				alleUnterhaltungen.add(nutzerabo);
//		}

//		Vector<Unterhaltung> OeffentlicheUnterhaltungenAbonierterHashtags = alleAbonniertenHashtagNachrichtenVonId(UserID);
//		for (Unterhaltung hashtagabo : OeffentlicheUnterhaltungenAbonierterHashtags) {
//			boolean bereitsHinzugefuegt = false;
//
//			for (Unterhaltung alle : alleUnterhaltungen) {
//				if (hashtagabo.getId() == alle.getId())
//					bereitsHinzugefuegt = true;
//			}
//
//			if (bereitsHinzugefuegt == false)
//				alleUnterhaltungen.add(hashtagabo);
//		}

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
					if (nachricht.getSender() == null) {
						Nutzer n = new Nutzer();
						n.setId(-1);
						n.setMailadresse("unknown@unknown.de");
						n.setVorname("Unknown");
						n.setNachname("User");
						nachricht.setSender(n);
					}

				}
			}

		}

		return alleUnterhaltungen;
	}

	private static Vector<Nutzer> alleUser = null;

	private Vector<Nutzer> getAlleNutzer(boolean zwingeNeuladen) {
		if (alleUser == null || zwingeNeuladen)
			alleUser = nutzerMapper.alleNutzer(0);

		return alleUser;

	}

}
