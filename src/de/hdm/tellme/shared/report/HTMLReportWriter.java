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

	private StringBuffer buffer = new StringBuffer(); 
	private Vector<Hashtag> HashtagListe = new Vector<Hashtag>();

		public void generateReport3(Vector <Hashtag> result, Nutzer n){
			RootPanel.get("content").clear();

			String ueberrschrift1= "report1";
			erstelleKopfdatenReport3(n);
			erstelleKoerperDatenReport3( result);
			
			this.reportText = buffer.toString();
			RootPanel.get("content").add(new HTML(reportText));
		}
		
		public void erstelleKopfdatenReport3(Nutzer n){
			String ausgangsnutzer = 	"Ausgangsnutzer:<div></div>" + n.getVorname() + " " + n.getNachname();
			Date currentTime = new Date(System.currentTimeMillis());
			HTML Erstellungsdatum = new HTML(""+new Timestamp(currentTime.getTime()));
			// Nutzerabo Klasse Format
			buffer.append("<table class='reportTabelle'>");
			buffer.append("<th colspan='2' class='reportKopfzeile'> Report 3 </th>");

		 	buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"+ausgangsnutzer  +"</div><div> Datum: "+Erstellungsdatum + "</div></td><td class='kopfdatenbox_rechts'>"+new Impressum().getHtmlImpressum()+"</td></tr>"); 
			buffer.append("</table>");
		}

		public void erstelleKoerperDatenReport3(  Vector<Hashtag> result){
			buffer.append("<table class='reportkoerper'>");
			buffer.append("<tr class='kopfZeileKoerper'> <th>Hashtag</th><th>Erstellungsdatum</th></tr>");
			for(int i= 0; i< result.size(); i++){
				buffer.append("<tr class='ergebnisZeileReport'><td>  #"+result.get(i).getSchlagwort()  +"</td><td>"+result.get(i).getErstellungsDatum()  +"</td></tr>"); 
			}
			buffer.append("</table>");
		}
		
		
		// Abschnitt Report 3
		
//		public void erstelleKopfDatenReport2(Vector <Nutzer> result, Nutzer n){
//			String ueberschrift1 = "Report 2";
//			erstelleKopfDatenReport2(n);
//			erstelleKoerperDatenReport2(result);
//			this.reportText = buffer.toString();
//			RootPanel.get("content").add(new HTML(reportText));
//		}
//		
////		public void erstelleKopfDatenReport2(Nutzer n) {
////		
////			String ausgangsnutzer = "Ausgangsnutzer: "+ n.getVorname() + " " + n.getNachname();
////			Date currentTime = new Date(System.currentTimeMillis());
////			HTML ErstellungsDatum = new HTML (""+ new Timestamp(currentTime.getTime()));
////			
////			buffer.append("<table style=\"width:400px;border:1px solid silver\">");
////			buffer.append("<tr><td class='kopfdatenbox_links'> "+ausgangsnutzer +" Datum '<br />' "+ErstellungsDatum+ "</td><td class 'kopfdatenbox_rechts' >"new Impressum().getHtmlImpressum()+"</td><\tr>");
////		}
}

	