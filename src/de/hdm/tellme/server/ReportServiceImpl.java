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

	@Override
	public Vector<Nachricht> report1GenerierenListe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Nutzer> report2GenerierenListe(Nutzer n) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Vector<Nutzer> report2GenerierenListe() {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
