package de.hdm.tellme.shared.report;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.tellme.client.Impressum;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Der ReportWriter formatiert den Report mit HTML.
 * 
 * @author Nicole Reum
 *
 */

public class HTMLReportWriter  {
	
	private String reportText = "";
	 
 

	 public void resetReportText() {
	  this.reportText = "";
	 }
	
	Impressum impressum = new Impressum();
	
	public void generateReport3(Vector<Hashtag> result, Nutzer n){
		Window.alert("Erfolgreich Hallo");

		Date currentTime = new Date(System.currentTimeMillis());
		HTML a = new HTML(""+new Timestamp(currentTime.getTime()));
		Window.alert("Erfolgreich Hoooo");

	//	RootPanel.get("header_left").add(new HTML("TEST")); 
	//	RootPanel.get("header_left").add(new HTML("Ausgangsnutzer: " + n.getVorname() + " " + n.getNachname())); 
	//	RootPanel.get("header_right").add(new Impressum() ); 
	//	RootPanel.get("header_right").add(a); 
		generateBodyFromArray(result); 
	}

		
	public void generateBodyFromArray(Vector<Hashtag> result){
		Window.alert("Erfolgreich Hallo2");

		StringBuffer buffer = new StringBuffer(); 
		buffer.append("<table style=\"width:400px;border:1px solid silver\">");
		
		for(int i= 0; i< result.size(); i++){
			buffer.append("<tr><td> #"+result.get(i).getSchlagwort()  +"</td><td>"+result.get(i).getErstellungsDatum()  +"</td></tr>"); 
		}
		buffer.append("</table>");
		this.reportText = buffer.toString();
		RootPanel.get("content").add(new HTML(reportText)); 

	}
	
	
 
	
}

	