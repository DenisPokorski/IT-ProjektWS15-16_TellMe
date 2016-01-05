package de.hdm.tellme.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.Schaukasten.NeuigkeitenEditor;



/**



* 



* @author ….



* @version 1.0



*/



public class Impressum extends HTML {

	
	private static NeuigkeitenEditor ansichtNeuigkeiten = null;

	public static NeuigkeitenEditor gibansichtNeuigkeiten() {
		ansichtNeuigkeiten = new NeuigkeitenEditor();
		return ansichtNeuigkeiten;
	}
	
	HTML impressumString = new HTML( "<h2>Impressum</h2>"
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



	"</tr></table><p>");


	
	 
	public HTML getHtmlImpressum(){
		
		return impressumString;
	}
	
	
	
	public Button impessumButton(){
		
		
		final Button impressum = new Button("Imressum"); 
		impressum.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
		
			RootPanel.get("footer").add(getHtmlImpressum());	
			impressum.setEnabled(false);
			Button zurueckBtn = new Button("zurück zu Neuigkeiten"); 
			zurueckBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
 					VerticalPanel n = NeuigkeitenEditor.gibFilterPanel();
					RootPanel.get("content_left").add(n);
  
					impressum.setEnabled(true);

					RootPanel.get("footer").clear();	
					RootPanel.get("footer").add(impressum);	


					 
				}
				});
			}
		});
			
	return impressum;
		
		
	}

public void onLoad(){



Button zurueckButton = new  Button ("zurück");


zurueckButton.setStylePrimaryName( "zurueckButton");

 
 



RootPanel.get("Impressum").add(zurueckButton);



}



}