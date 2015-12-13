package de.hdm.tellme.server;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.NutzerAbonnementMapper;
import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.bo.*;
 
@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements
		EditorService {
	/**
	 * 
	 * @author Feltrin, Zimmermann
	 * @version 
	 * @since 26.11.2015
	 * 
	 */
	public EditorServiceImpl() throws IllegalArgumentException {

	}

	public void init() throws IllegalArgumentException {
		this.nMapper = NutzerMapper.nutzerMapper();
		this.naMapper = NutzerAbonnementMapper.nutzerAbonnementMapper();


		
	}

	private NutzerMapper nMapper = null;
	private NutzerAbonnementMapper naMapper = null;


	public void nutzerAnlegen(Nutzer na) {
		Nutzer n = new Nutzer();
		n.setVorname(na.getVorname());
		n.setNachname(na.getNachname());
		n.setMailadresse(na.getMailadresse());

		nMapper.anlegen(n);

	}

	public void nutzerAktualisieren(Nutzer n) {

		nMapper.aktualisieren(n);
	}
	
	
	
	

	public void nutzerLoeschen(Nutzer n) {

		nMapper.entfernen(n);
	}

	public Nutzer getNutzerVonId(Nutzer na) {
		Nutzer n = new Nutzer();
		n = nMapper.suchenNutzerMitId(n);
		return n;

	}

	public Nutzer getNutzerVonMailadresse(String eMailAdress) {
	 
		Nutzer n = new Nutzer();
		n = nMapper.suchenNutzerIdMitMailadresse(eMailAdress);
		return n;
	}


	@Override
	public Vector<Nutzer> getZuAbonnieredeLoeschenNutzerListe(int i) {
		Vector<Nutzer> alleAbboniertenNutzer = naMapper.ladeAbonnierendeNutzerListe(i);
		return alleAbboniertenNutzer;
	}

	@Override
	public void loescheNutzeraboById(int vonId, int nachId) {
		naMapper.loescheNutzeraboById(vonId,nachId);
	}

	@Override
	public Vector<Nutzer> getNochNichtAbonnenteNutzerListe(int meineId) {
		return null; 
		// death
 

}

	@Override
	public Vector<Nutzer> getAlleNochNichtAbonnierteNutzerListe(int id) {
		
		Vector<Nutzer> alleNutzer = naMapper.alleNochNichtAbonnierteNutzerSelektieren(id );
		return alleNutzer;
	}

	@Override
	public void erstellenNutzeraboById(int vonId, int nachId) {
		naMapper.nutzerAboErstellen(vonId, nachId);
		
		
	}

	@Override
	public Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId) {
		Vector<Nutzer> alleNutzer = nMapper.alleNutzerAusserMeineId(meineId );
		
		return alleNutzer;
	}

	@Override
	public Vector<Hashtag> getZuAbonnierendeLoeschenHashtagAboListe(int meineId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
