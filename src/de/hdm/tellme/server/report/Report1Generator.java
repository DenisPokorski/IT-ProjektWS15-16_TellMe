package de.hdm.tellme.server.report;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.ReportService;
import de.hdm.tellme.shared.ReportServiceAsync;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;



public class Report1Generator {
	private final ReportServiceAsync asyncObj = GWT.create(ReportService.class);
	
	
	public Vector<Nachricht> report1Generieren() {
		Vector<Nachricht> report1GenererierenListe = new Vector<Nachricht>();
		return report1GenererierenListe;


	}
	

}
