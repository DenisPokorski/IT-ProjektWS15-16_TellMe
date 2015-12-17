package de.hdm.tellme.server;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.NutzerAbonnementMapper;
import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.ReportService;
import de.hdm.tellme.shared.bo.*;

@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements
		ReportService {
	
	
	
	public ReportServiceImpl() throws IllegalArgumentException {

	}
	
	//Für Report2 benötigen wir den NutzerAbonnementMapper und NutzerMapper
	private NutzerMapper nMapper = null;
	private NutzerAbonnementMapper naMapper = null;

	@Override
	public Vector<Nutzer> report2GenerierenListe(int i) {
		
		Vector<Nutzer> alleNutzer = nMapper.alleNutzer(i);// Hier müssen wir noch einen Mapper mit ALLEN Nutzern erstellen, probeweise aber mit alle außer ich selber
		return alleNutzer;
	}


}
