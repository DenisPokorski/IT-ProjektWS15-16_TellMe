package de.hdm.tellme.server;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.HashtagAbonnementMapper;
import de.hdm.tellme.server.db.NachrichtMapper;
import de.hdm.tellme.server.db.NutzerAbonnementMapper;
import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.server.db.UnterhaltungMapper;
import de.hdm.tellme.shared.ReportService;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;

@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements
		ReportService {
	
	public void init() throws IllegalArgumentException {
		NutzerMapper.nutzerMapper();
		this.nutzeraboMapper = NutzerAbonnementMapper.nutzerAbonnementMapper();
		this.nachrichtMapper = NachrichtMapper.nachrichtMapper();
		UnterhaltungMapper.unterhaltungMapper();
		this.hashtagAboMapper = HashtagAbonnementMapper.hashtagAbonnementMapper();

	}
	
	private NutzerAbonnementMapper nutzeraboMapper = null;

	private NachrichtMapper nachrichtMapper = null;
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
		Vector<Nutzer> report2 = nutzeraboMapper.ladeAbonnierendeNutzerListe(i);
		return report2;
	}

	@Override
	public Vector<Nachricht> report1_1Generieren(int nutzerId,Timestamp vonDatum, Timestamp bisDatum ){
		Vector<Nachricht> report1_1 = nachrichtMapper.report1_1Mapper(nutzerId, vonDatum, bisDatum);
		return report1_1;
	}
	@Override
	public Vector<Nachricht> report1_2Generieren(Timestamp vonDatum, Timestamp bisDatum){
		Vector<Nachricht> report1_2 = nachrichtMapper.report1_2Mapper(vonDatum, bisDatum);
		return report1_2;
	}


//	@Override
////	public Vector<Nachricht> report1_3Generieren(int nutzerId) {
//		Vector<Nachricht> nachrichtListe = nachrichtMapper.report1_3Mapper(nutzerId);
//		Vector<Hashtag> hashtagListe = hashtagMapper.report1_3Mapper(nutzerId); //Alle Hashtags zu nachrichten id 
//
////		for(int i=0; i <report1_3.size(); i++ ){
////			report1_3.get(i).getVerknuepfteHashtags()
////		}
//		//return report1_3;
//	}



	@Override
	public Vector<Nachricht> report1_4Generieren() {
		Vector<Nachricht> report1_4 = nachrichtMapper.report1_4Mapper();
		return report1_4;
	}



	@Override
	public Vector<Nachricht> report1_3Generieren(int i) {
		// TODO Auto-generated method stub
		return null;
	}

//	public class UnterhaltungNachrichtHashtagBinder(Vector<Nachricht> n, Vector<Unterhaltung> u){
//		
//	}
}
