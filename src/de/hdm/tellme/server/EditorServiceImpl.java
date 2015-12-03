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
	public Vector<Nutzer> getZuAbonnieredeNutzerListe  (int n) {
		Vector<Nutzer> alleNutzerListe = naMapper.ladeZuAbonnierendeNutzerListe(n);
		Vector<NutzerAbonnement> alleNutzerAbonnementListe = naMapper.ladeNutzerAboListe(n);
		
		Vector<Nutzer> zuAboonierendeNutzer = new Vector<Nutzer>();

		for(int i = 0; i < alleNutzerListe.size(); i++){
			if(	alleNutzerListe.get(i).getId() == alleNutzerAbonnementListe.get(i).getAbonnementErsteller().getId() ){
				i++;
			} else{
				Nutzer nutzer = new Nutzer();
				nutzer.setId(alleNutzerListe.get(i).getId());
				nutzer.setVorname(alleNutzerListe.get(i).getVorname());
				nutzer.setNachname(alleNutzerListe.get(i).getNachname());
				nutzer.setMailadresse(alleNutzerListe.get(i).getMailadresse());
				zuAboonierendeNutzer.add(nutzer);
			}
		}
		
	
		return zuAboonierendeNutzer;
		
	}

	@Override
	public Vector<Nutzer> getZuAbonnieredeLoeschenNutzerListe(int i) {
		Vector<Nutzer> alleAbboniertenNutzer = naMapper.ladeZuAbonnierendeNutzerListe(i);
		return alleAbboniertenNutzer;
	}

}
