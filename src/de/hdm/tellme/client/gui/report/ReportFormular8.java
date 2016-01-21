package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.HashtagCellList;
import de.hdm.tellme.client.gui.editor.HashtagDataProvider;
import de.hdm.tellme.client.gui.editor.HashtagZelle;
import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Die Klasse <class>ReportFormular8</class> beinhaltet den
 * Report-Generierungs-Button "Report 8 erstellen", sowie eine Methode um den
 * Report zurückzugeben.
 * 
 * @author denispokorski
 *
 */
public class ReportFormular8 {
	
	private static Hashtag hashtag = null;
	private Button report8GenerierenButton = new Button("Report 8 erstellen");

	/**
	 * Im Konstruktor der Klasse werden die beiden Root-Panels gesäubert. Zudem
	 * wird die Methode <code>report7Generieren(Nutzer n)</code> aufgerufen.
	 * 
	 */
	public ReportFormular8() {

		report8GenerierenButton.setStylePrimaryName("reportBtn");
		report8GenerierenButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				RootPanel.get("content_left").clear();
				RootPanel.get("content_right").clear();

				HashtagDataProvider.gib().report8Generieren(hashtag);

			}
		});

	}
	/**
	 * 
	 * Die Methode <code>zeigeReportFormular</code> säubert die rechte Hälfte des rootPanels. 
	 * Danach wird der Report auf der rechten Seite generiert und angezeigt. 
	 */
	public void zeigeReportFormular() {
		RootPanel.get("content_right").clear();

		HashtagDataProvider.gib().report8Generieren(hashtag);

	}

	/**
	 * 
	 * Die Methode <code>gibFormular()</code> instanziert ein VerticalPanel und
	 * fügt diesem dem Button hinzu. Als Rückgabe Wert gibt die Methode das
	 * VerticalPanel zurück.
	 */
	public VerticalPanel gibFormular() {
		VerticalPanel vpForm = new VerticalPanel();

		Label label = new Label(hashtag.getSchlagwort());
		label.setStylePrimaryName("selectionLabel");
		vpForm.add(label);

		vpForm.add(report8GenerierenButton);
		return vpForm;
	}

	/**
	 * Die Methode <code>report8Generieren</code> übergibt das Hashtag-Objekt aus
	 * der NutzerZelle.
	 * 
	 * @param ZellenObjekt
	 */
	public void report8Generieren(HashtagZelle.ZellenObjekt ZellenObjekt) {
		ReportFormular8.hashtag = ZellenObjekt.hashtag;

	}

}
