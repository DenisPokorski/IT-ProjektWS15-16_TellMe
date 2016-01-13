package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Report 1_3 gibt den alle Nachrichten eines Nutzers aus, ohne den Zeitraumm zu
 * beschränken
 * 
 * @author Alex
 *
 */
public class ReportFormular3 extends Composite {
	/**
	 * Wir benötigen ein Nutzer-Objekt, dass anfangs auf null gesetzt wird
	 */
	private Nutzer nutzer = null;

	Button report3GenerierenButton = new Button("Report 3 erstellen");

	/**
	 * Methode ReportFormular3 wird aufgerufen um den CLickhandler in ihr zu
	 * instanziieren.
	 */
	public ReportFormular3() {
		report3GenerierenButton.setStylePrimaryName("reportBtn");
		report3GenerierenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				NutzerDataProvider.gib(1).report3Generieren(nutzer);
			}
		});
	}

	public void zeigeReportFormular() {
		RootPanel.get("content_right").clear();

		NutzerDataProvider.gib(1).report3Generieren(nutzer); // TODO KLASSE
																// BESCHREIBEN

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
	 * TODO Kommentar nachtragen
	 * @param ZellenObjekt
	 */
		public void report3Generieren(NutzerZelle.ZellenObjekt ZellenObjekt){
			this.nutzer = ZellenObjekt.nutzer;
		}
		
}