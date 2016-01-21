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
 * Die Klasse <class>ReportFormular7</class> beinhaltet den
 * Report-Generierungs-Button "Report 7 erstellen", sowie eine Methode um den
 * Report zurückzugeben.
 * 
 * @author danathuering
 *
 */
public class ReportFormular7 extends Composite {
	private static Nutzer nutzer = null;
	private Button report7GenerierenButton = new Button("Report 7 erstellen");

	/**
	 * Im Konstruktor der Klasse werden die beiden Root-Panels gesäubert. Zudem
	 * wird die Methode <code>report7Generieren(Nutzer n)</code> aufgerufen.
	 * 
	 */
	public ReportFormular7() {

		report7GenerierenButton.setStylePrimaryName("reportBtn");
		report7GenerierenButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				RootPanel.get("content_left").clear();
				RootPanel.get("content_right").clear();

				NutzerDataProvider.gib(1).report7Generieren(nutzer);

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

		NutzerDataProvider.gib(1).report7Generieren(nutzer);

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

		vpForm.add(report7GenerierenButton);
		return vpForm;
	}

	/**
	 * Die Methode <code>report7Generieren</code> übergibt das Nutzer-Objekt aus
	 * der NutzerZelle.
	 * 
	 * @param ZellenObjekt
	 */
	public void report7Generieren(NutzerZelle.ZellenObjekt ZellenObjekt) {
		ReportFormular7.nutzer = ZellenObjekt.nutzer;

	}

}
