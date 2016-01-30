package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Report 3 gibt den alle Nachrichten eines Nutzers aus, ohne den Zeitraum zu
 * beschränken
 * 
 * @author Alex
 *
 */
public class ReportFormular3 extends Composite {
	/**
	 * Wir benötigen ein Nutzer-Objekt, das anfangs auf null gesetzt wird
	 */
	private Nutzer nutzer = null;
	private Button report3GenerierenButton = new Button("Report 3 generieren");

	/**
	 * Methode ReportFormular3 wird aufgerufen um den CLickhandler in ihr zu
	 * instanziieren.
	 */
	public ReportFormular3() {
		report3GenerierenButton.setStylePrimaryName("reportBtn");
		report3GenerierenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content_left").clear();
				RootPanel.get("content_right").clear();
				
				/**
				 * Die folgenden Zeilen beschreiben die Ladeanzeige. Diese wird bei der Betätgiung ders Clickhandlers ausgeführt.
				 * Ziel ist es dem Nutzer Informationen über das aktuelle Programmverhalten zu liefern.
				 * */
				VerticalPanel ladenPanel = new VerticalPanel();
				ladenPanel.setStylePrimaryName("ladenPanel");
				
				Image ladenImg = new Image("laden.gif");
				ladenImg.setStylePrimaryName("ladenImg");
				ladenPanel.add(ladenImg);

				HTML ladenLabel = new HTML("<h1> Bitte warten <h1><br /><h3>Bitte warte einen Augenblick bis der Report generiert wurde. Vielen Dank.</h3>");
				ladenPanel.add(ladenLabel);
				
				RootPanel.get("content").add(ladenPanel);
				NutzerDataProvider.gib(1).report3Generieren(nutzer);
			}
		});
	}
/**
 * 
 * Die Methode <code>zeigeReportFormular</code> säubert die rechte Hälfte des rootPanels. Danach wird der Report 
 * auf der rechten Seite generiert und angezeigt. 
 */
	public void zeigeReportFormular() {
		RootPanel.get("content_right").clear();
		NutzerDataProvider.gib(1).report3Generieren(nutzer); 
	}

	/**
	 * 
	 * Die Methode <code>gibFormular()</code> instanziert ein VerticalPanel und
	 * fügt diesem dem Button hinzu. Als Rückgabe Wert gibt die Methode das
	 * VerticalPanel zurück.
	 */
	public VerticalPanel gibFormular() {
		VerticalPanel vpForm = new VerticalPanel();

		Label label = new Label(nutzer.getVorname() + nutzer.getNachname());
		label.setStylePrimaryName("selectionLabel");
		vpForm.add(label);

		vpForm.add(report3GenerierenButton);
		return vpForm;
	}
	
	/**
	 * Die Methode <code>report3Generieren</code> übergibt das Nutzer-Objekt aus
	 * der NutzerZelle.
	 * 
	 * @param ZellenObjekt
	 */
		public void report3Generieren(NutzerZelle.ZellenObjekt ZellenObjekt){
			this.nutzer = ZellenObjekt.nutzer;
		}
		
}