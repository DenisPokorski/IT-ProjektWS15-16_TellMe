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
	
	
	//public Vector<Nachricht> report1Generieren() {
	//	Vector<Nachricht> report1GenererierenListe = new Vector<Nachricht>();
		
		
		/*asyncObj.report1GenerierenListe(Nutzer, new AsyncCallback < Vector<Nachricht>>()	{
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(Vector<Nachricht> result) {
 				result = report1Generieren();
			}
			});
			return report1GenererierenListe;

	}*/
	
	//}
}
