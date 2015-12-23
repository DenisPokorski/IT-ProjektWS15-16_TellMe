package de.hdm.tellme.server;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.HashtagAbonnementMapper;
import de.hdm.tellme.server.db.NachrichtMapper;
import de.hdm.tellme.server.db.NutzerAbonnementMapper;
import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.server.db.UnterhaltungMapper;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.ReportService;
import de.hdm.tellme.shared.bo.*;

@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements
		ReportService {
	
	public void init() throws IllegalArgumentException {
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.nutzeraboMapper = NutzerAbonnementMapper.nutzerAbonnementMapper();
		this.nachrichtMapper = NachrichtMapper.nachrichtMapper();
		this.unterhaltungMapper = UnterhaltungMapper.unterhaltungMapper();
		this.hashtagAboMapper = HashtagAbonnementMapper.hashtagAbonnementMapper();

	}
	
	//Für Report2 benötigen wir den NutzerAbonnementMapper und NutzerMapper
	private NutzerMapper nutzerMapper = null;
	private NutzerAbonnementMapper nutzeraboMapper = null;

	private NachrichtMapper nachrichtMapper = null;
	private UnterhaltungMapper unterhaltungMapper = null;

	
	private NutzerAbonnementMapper naMapper = null;
	private HashtagAbonnementMapper hashtagAboMapper = null;
	


//	@Override
//	public Vector<Nutzer> report2GenerierenListe(int i) {
//		
//		Vector<Nutzer> alleNutzer = nutzeraboMapper.alleNutzer(i);// Hier müssen wir noch einen Mapper mit ALLEN Nutzern erstellen, probeweise aber mit alle außer ich selber
//		return alleNutzer;
//	}
	
	
	@Override 
	public Vector<Hashtag> report3Generieren(int nutzerId) {   
		Vector<Hashtag> report3 = hashtagAboMapper.alleHashtagsEinesNutzers(nutzerId);// Hier müssen wir noch einen Mapper mit ALLEN Nutzern erstellen, probeweise aber mit alle außer ich selber
		return report3;
	}



	@Override
	public Vector<Nutzer> report2GenerierenListe(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
