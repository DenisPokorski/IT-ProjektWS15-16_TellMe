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
 * Die Klasse ReportForumlar2 beeinhaltet den Report-Generierungs-Button "Report 2 erstellen", sowie eine Methode,
 * um den Button zurückzugeben.
 *
 * 
 * @author denispokorski
 *
 */
public class ReportFormular2 extends Composite {
	private Nutzer nutzer = null;
	Button report2GenerierenButton = new Button("Report 2 erstellen");

	public ReportFormular2() {
		report2GenerierenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				RootPanel.get("content_left").clear();
				RootPanel.get("content_right").clear();
				
				NutzerDataProvider.gib(1).report2Generieren(nutzer);
			}
		});
	}

	/**
	 * 
	 * Die Methode <code>gibFormular()</code> instanziert ein VerticalPanel und fügt diesem dem Button hinzu. Als Rückgabe Wert gibt die Methode das VerticalPanel zurück.  
	 */
	public VerticalPanel gibFormular() {
		VerticalPanel vpForm = new VerticalPanel();

		vpForm.add(new Label(nutzer.getVorname() + nutzer.getNachname()));
		vpForm.add(report2GenerierenButton);
		return vpForm;
	}
	
	/**
	 * TODO Kommentar nachtragen
	 * @param ZellenObjekt
	 */	

	public void report2Generieren(NutzerZelle.ZellenObjekt ZellenObjekt) {
		this.nutzer = ZellenObjekt.nutzer;

	}

}
