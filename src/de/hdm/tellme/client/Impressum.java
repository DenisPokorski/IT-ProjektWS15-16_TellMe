package de.hdm.tellme.client;

import com.google.gwt.user.client.ui.HTML;
import de.hdm.tellme.client.Schaukasten.NeuigkeitenEditor;

/**
 * Das Impressum gibt laut Telemediengesetzt §5 alle benötigten Informationen
 * zum Inhaber aus und muss innerhalb von 2 Klicks erreichbar sein.
 * 
 *
 * @author Denis Pokorski
 * 
 * 
 * 
 * @version 1.0
 */

public class Impressum extends HTML {
	/**
	 * Damit die Klasse NeuigkeitenEditor nur einmal während der Laufzeit des
	 * Programms bestehen kann, muss man sie als Singleton darstellen, dies
	 * geschieht durch die Referenz <code>static</code>.
	 */

	private static NeuigkeitenEditor ansichtNeuigkeiten = null;

	/**
	 * Die statische Methode wird über NeuigkeitenEditor gibansichtNeugikeiten
	 * aufgerufen. Sie überprüft, dass nur eine Instanz von NeuigkeitenEditor
	 * besteht.
	 */

	public static NeuigkeitenEditor gibansichtNeuigkeiten() {
		ansichtNeuigkeiten = new NeuigkeitenEditor();
		return ansichtNeuigkeiten;
	}

	/**
	 * Es wird ein HTML-Fragment erstellt, der das Impressum statisch ausgibt,
	 * die Daten sind also nicht veränderbar.
	 */
	HTML impressumString = new HTML("<div class='" + "Impressum"
			+ "'><h2>Impressum</h2>" + "<h2>Angaben gemäß § 5 TMG:</h2>" +

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
	 * Die Methode soll das Impressum als String zurückgeben.
	 * 
	 * @return ein String, der das Impressum enthält.
	 */
	public HTML getHtmlImpressum() {

		return impressumString;
	}

}