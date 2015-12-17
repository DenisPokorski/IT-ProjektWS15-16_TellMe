package de.hdm.tellme.server;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.NachrichtMapper;
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
	}

	private NutzerMapper nutzerMapper = null;
	private NutzerAbonnementMapper nutzeraboMapper = null;

	private NachrichtMapper nachrichtMapper = null;
	private UnterhaltungMapper unterhaltungMapper = null;


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

	public Nutzer getNutzerVonMailadresse(String eMailAdress) {
		Nutzer n = new Nutzer();
		n = nutzerMapper.suchenNutzerIdMitMailadresse(eMailAdress);
		return n;
	}


	@Override
	public Vector<Nutzer> getZuAbonnieredeLoeschenNutzerListe(int i) {
		Vector<Nutzer> alleAbboniertenNutzer = nutzeraboMapper.ladeAbonnierendeNutzerListe(i);
		return alleAbboniertenNutzer;
	}

	@Override
	public void loescheNutzeraboById(int vonId, int nachId) {
		nutzeraboMapper.loescheNutzeraboById(vonId,nachId);
	}

	@Override
	public Vector<Nutzer> getNochNichtAbonnenteNutzerListe(int meineId) {
		return null; 
	}

	@Override
	public Vector<Nutzer> getAlleNochNichtAbonnierteNutzerListe(int id) {
		//int id =7;
		Vector<Nutzer> alleNutzer = nutzeraboMapper.alleNochNichtAbonnierteNutzerSelektieren(id );
		return alleNutzer;
	}

	@Override
	public void erstellenNutzeraboById(int vonId, int nachId) {
		nutzeraboMapper.nutzerAboErstellen(vonId,nachId);
		
	}

	@Override
	public Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId) {
		Vector<Nutzer> alleNutzer = nutzerMapper.alleNutzerAusserMeineId(meineId );
		return alleNutzer;
	}

	@Override
	public void NachrichtErstellen(Nachricht n) {
		nachrichtMapper.anlegen(n);
	}

	@Override
	public int UnterhaltungErstellen(Timestamp ts, String text) {
		int unterhaltungsTyp = 1; 
		unterhaltungMapper.anlegen(ts,unterhaltungsTyp); 
		int nachrichtenId = nachrichtMapper.nachrichtSelektieren(ts,text);
		int unterhaltungsId = unterhaltungMapper.unterhaltungSelektieren(ts);
 		unterhaltungMapper.UnterhaltungNachrichtZuweisen(unterhaltungsId,nachrichtenId); 
		return unterhaltungsId;
	}

	@Override
	public Vector<Nachricht> alleNachrichtenVonUnterhaltungListe(int uId) {
		 Vector<Nachricht> nachrichtListe = unterhaltungMapper.alleNachrichtenEinerUnterhaltung(uId); 
		 return nachrichtListe;
	}

	@Override
	public void nachrichtUnterhaltungZuweisen(String txt, int uId, Timestamp ts) {
	

		
	}

	@Override
	public void NachrichtErstellenUnnterhaltungZuweisen(Nachricht n, int uId) {
		nachrichtMapper.anlegen(n);
		int nachrichtenId = nachrichtMapper.nachrichtSelektieren(n.getErstellungsDatum(),n.getText());
 		unterhaltungMapper.UnterhaltungNachrichtZuweisen(uId,nachrichtenId); 
	}

	@Override
	public Vector<Unterhaltung> ladeAlleOeffentlichenUnterhaltungen() {
		Vector<Unterhaltung> unterhaltungListe = unterhaltungMapper.alleUnterhaltungen(); 
		return unterhaltungListe;
	}

	@Override
	public Vector<Hashtag> getZuAbonnierendeLoeschenHashtagAboListe(int meineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void erstellenHashtagAboById(int NutzerId, int HashtagId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Nutzer> getAlleNochNichtAbonnierteHashtagAboListe(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
