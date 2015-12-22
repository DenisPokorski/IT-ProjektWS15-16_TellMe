package de.hdm.tellme.server;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.NachrichtMapper;
import de.hdm.tellme.server.db.HashtagAbonnementMapper;
import de.hdm.tellme.server.db.HashtagMapper;
import de.hdm.tellme.server.db.NutzerAbonnementMapper;
import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.server.db.UnterhaltungMapper;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.bo.*;

@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements
		EditorService {
	public EditorServiceImpl() throws IllegalArgumentException {

	}

	public void init() throws IllegalArgumentException {
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.nutzeraboMapper = NutzerAbonnementMapper.nutzerAbonnementMapper();
		this.nachrichtMapper = NachrichtMapper.nachrichtMapper();
		this.unterhaltungMapper = UnterhaltungMapper.unterhaltungMapper();
		this.hashtagAboMapper = HashtagAbonnementMapper
				.hashtagAbonnementMapper();
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
		Vector<Nutzer> alleAbboniertenNutzer = nutzeraboMapper
				.ladeAbonnierendeNutzerListe(i);
		return alleAbboniertenNutzer;
	}

	@Override
	public Vector<Integer> ladeAbonnierendeHashtagListe(int i) {
		Vector<Integer> alleAbboniertenNutzer = hashtagAboMapper
				.ladeAbonnierteHashtagListe(i);
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
		Vector<Nutzer> alleNutzer = nutzeraboMapper
				.alleNochNichtAbonnierteNutzerSelektieren(id);
		return alleNutzer;
	}

	@Override
	public Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId) {
		Vector<Nutzer> alleNutzer = nutzerMapper
				.alleNutzerAusserMeineId(meineId);
		return alleNutzer;
	}

	@Override
	public void NachrichtErstellen(Nachricht n) {
		nachrichtMapper.anlegen(n);
	}

	@Override
	public int UnterhaltungErstellen(Timestamp ts, String text) {
		int unterhaltungsTyp = 1;
		unterhaltungMapper.anlegen(ts, unterhaltungsTyp);
		int nachrichtenId = nachrichtMapper.nachrichtSelektieren(ts, text);
		int unterhaltungsId = unterhaltungMapper.unterhaltungSelektieren(ts);
		unterhaltungMapper.UnterhaltungNachrichtZuweisen(unterhaltungsId,
				nachrichtenId);
		return unterhaltungsId;
	}

	@Override
	public Vector<Nachricht> alleNachrichtenVonUnterhaltungListe(int uId) {
		Vector<Nachricht> nachrichtListe = unterhaltungMapper
				.alleNachrichtenEinerUnterhaltung(uId);
		return nachrichtListe;
	}

	@Override
	public void nachrichtUnterhaltungZuweisen(String txt, int uId, Timestamp ts) {

	}

	@Override
	public void NachrichtErstellenUnnterhaltungZuweisen(Nachricht n, int uId) {
		nachrichtMapper.anlegen(n);
		int nachrichtenId = nachrichtMapper.nachrichtSelektieren(
				n.getErstellungsDatum(), n.getText());
		unterhaltungMapper.UnterhaltungNachrichtZuweisen(uId, nachrichtenId);
	}

	@Override
	public Vector<Unterhaltung> ladeAlleOeffentlichenUnterhaltungen() {
		Vector<Unterhaltung> unterhaltungListe = unterhaltungMapper
				.alleUnterhaltungen();
		return unterhaltungListe;
	}

	@Override
	public Vector<Hashtag> getZuAbonnierendeLoeschenHashtagAboListe(int meineId) {
		Vector<Hashtag> alleAbboniertenHashtags = hashtagAboMapper
				.ladeAbonnierendeHashtagListe(meineId);
		return alleAbboniertenHashtags;
	}

	@Override
	public void erstellenHashtagAboById(int NutzerId, int HashtagId) {
		hashtagAboMapper.hashtagAboErstellen(NutzerId, HashtagId);
	}

	@Override
	public Vector<Hashtag> getAlleNochNichtAbonnierteHashtagAboListe(int i) {
		Vector<Hashtag> alleHashtags = hashtagAboMapper
				.alleNochNichtAboonierteHashtagsSelektieren(i);
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
		Vector<Integer> alleAbonniertenNutzer = nutzerMapper
				.alleAbonniertenNutzer(meineId);

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
	public Unterhaltung meineUnterhaltungenMitSichtbarkeit(int meineId) {
		Unterhaltung meineUnterhaltungen = unterhaltungMapper
				.meineUnterhaltungen(meineId);
		return meineUnterhaltungen;
	}

	@Override
	public Unterhaltung oeffentlicheNachrichtenVonBenutzer(int id) {
		Unterhaltung oeffentlicheNachrichtenVonBenutzer = unterhaltungMapper
				.oeffentlicheNachrichtenVonBenutzer(id);
		return oeffentlicheNachrichtenVonBenutzer;
	}

	@Override
	public Unterhaltung oeffentlicheNachrichtenNachHashtag(int id) {
		Unterhaltung oeffentlicheNachrichtenNachHashtag = hashtagMapper
				.oeffentlicheNachrichtenNachHashtag(id);
		return oeffentlicheNachrichtenNachHashtag;
	}

}
