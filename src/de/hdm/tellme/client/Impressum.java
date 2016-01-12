package de.hdm.tellme.client;

import com.google.gwt.user.client.ui.HTML;
import de.hdm.tellme.client.Schaukasten.NeuigkeitenEditor;



/**
Das Impressum gibt


* 



* @author Denis Pokorski



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



	"<p>Dana Thüring<br />"+



	"Nobelstraße 10<br />"+



	"70569 Stuttgart"+



	"</p>" +



	"<h2>Kontakt:</h2>"+



	"<table><tr>"+



	"<td>Telefon:</td>"+



	"<td>0711 8923 10</td></tr>"+



	"<tr><td>E-Mail:</td>"+



	"<td>dt018@hdm-stuttgart.de</td>"+



	"</tr></table><p>");


	
	 
	public HTML getHtmlImpressum(){
		
		return impressumString;
	}
	
	
	
 



}