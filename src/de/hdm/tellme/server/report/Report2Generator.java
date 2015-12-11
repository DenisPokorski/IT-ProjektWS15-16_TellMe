package de.hdm.tellme.server.report;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.ReportService;
import de.hdm.tellme.shared.ReportServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.NutzerAbonnement;

public class Report2Generator {
	private final ReportServiceAsync asyncObj = GWT.create(ReportService.class);
	private Nutzer n = new Nutzer();
	
	public Vector<Nutzer> report2Generieren() {
		  Vector<Nutzer> report2GenerierenListe = new Vector<Nutzer>();

		  int tempId = 7;
		asyncObj.report2GenerierenListe(tempId, new AsyncCallback < Vector<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
				// TODO Auto-generated method stub
				
			}
		 

		  
		   });
		  return report2GenerierenListe;
		  
	
}
}

 