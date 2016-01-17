package de.hdm.tellme.client.Schaukasten;

import com.google.gwt.user.client.ui.HTML;

/**
 * Das Impressum gibt laut Telemediengesetzt §5 alle benötigten Informationen
 * zum Inhaber aus und muss innerhalb von 2 Klicks erreichbar sein.
 *
 * @author Denis Pokorski
 * @version 1.0
 */

public class Impressum extends HTML {
 
	/**
	 * Es wird ein HTML-Fragment erstellt, das die Impressumsdaten statisch ausgibt,
	 * die Daten sind also nicht veränderbar.
	 */
	private HTML impressumString = new HTML("<div class='" + 
			"Impressum"+ 
			"'><h2>Impressum</h2>" + 
			"<h2>Angaben gemäß § 5 TMG:</h2>" +
			"<p>Dana Thüring<br />" +
			"Nobelstraße 10<br />" +
			"70569 Stuttgart" +
			"</p>" +
			"<h2>Kontakt:</h2>" +
			"<table><tr>" +
			"<td>Telefon:</td>" +
			"<td>0711 8923 10</td></tr>" +
			"<tr><td>E-Mail:</td>" +
			"<td>dt018@hdm-stuttgart.de</td>" +
			"</tr></table><p></div>");
	

	/**
	 * Die Methode soll das Impressum als HTML-Element zurückgeben.
	 * 
	 * @return ein privates HTML-Element, der das Impressum enthält.
	 */
	public HTML getHtmlImpressum() {
		return impressumString;
	}

}