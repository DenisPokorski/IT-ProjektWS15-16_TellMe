package de.hdm.tellme.client;

import com.google.gwt.event.dom.client.ClickEvent;



import com.google.gwt.event.dom.client.ClickHandler;



import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;



import com.google.gwt.user.client.ui.HTML;



import com.google.gwt.user.client.ui.RootPanel;



import com.google.gwt.user.client.ui.VerticalPanel;



/**



* 



* @author ….



* @version 1.0



*/



public class Impressum extends HTML {

	String impressumString =
			
			"<h2>Impressum</h2>"
			+ "<h2>Angaben gemäß § 5 TMG:</h2>"+



	"<p>Testperson<br />"+



	"Nobelstraße 10<br />"+



	"70569 Stuttgart"+



	"</p>" +



	"<h2>Kontakt:</h2>"+



	"<table><tr>"+



	"<td>Telefon:</td>"+



	"<td>0711 8923 10</td></tr>"+



	"<tr><td>E-Mail:</td>"+



	"<td>info@hdm-stuttgart.de</td>"+



	"</tr></table><p>";


	
	 
	public String getHtmlImpressum(){
		
		return impressumString;
	}

public void onLoad(){



Button zurueckButton = new  Button ("zurück");


zurueckButton.setStylePrimaryName( "zurueckButton");



final HTML html = new HTML("");



html.setHTML( impressumString



);



zurueckButton.addClickHandler(new ClickHandler() {



public void onClick(ClickEvent event) { 



RootPanel.get("content_wrap").clear();



RootPanel.get("content_wrap").add(html);



}



});



RootPanel.get("Impressum").add(zurueckButton);



}



}