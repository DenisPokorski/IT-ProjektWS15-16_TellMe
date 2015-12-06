package de.hdm.tellme.server.report;
/**
 * 
 * 
 * 
 * @author Thüring 
 * @version 1.0
 * @since 03.12.2015
 * 
 */
import java.util.Vector;

import com.google.gwt.core.client.GWT;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.bo.Hashtag;

public class Report3Generator {
	
	
	private final ReportServiceAsync asyncObj = GWT.create(ReportService.class);	
	
	public Vector<Hashtag> report3Generieren() {
		  Vector<Hashtag> report3GenerierenListe= new Vector<Hashtag>();

		  asyncObj.getZuAbonnieredeNutzerListe(Hashtag.getHashtag().getId(), new AsyncCallback < Vector<Hashtag>>() {
		   public void onFailure(Throwable caught) {
		    // TODO Auto-generated method stub
		   }

		   public void onSuccess(Vector<Hashtag> result) {
		     result = report3GenerierenListe;
		   }
		   });
		  return report3GenerierenListe;
		 }

}
