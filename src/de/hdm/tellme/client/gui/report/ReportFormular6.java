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
 * Die Klasse ReportForumlar3 beeinhaltet den Report-Generierungs-Button "Report 3 erstellen", sowie eine Methode,
 * um den Button zurückzugeben.
 *
 * 
 * @author denispokorski
 *
 */
public class ReportFormular6 extends Composite{
	private Nutzer nutzer = null;
	private Button report6GenerierenButton = new Button("Report 3 erstellen");
	/**
	 * 
	 * Im Konstruktor der Klasse werden als erstes die beiden Bereiche des Root-Panels "content-left", "content-right" gesäubert.
	 * Zudem wird die die Mehtode <code>report3Generieren(Nutzer n)</code> aufgerufen. 
	 */
	public ReportFormular6(){
		
		report6GenerierenButton.setStylePrimaryName("reportBtn");
		report6GenerierenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				RootPanel.get("content_left").clear();
				RootPanel.get("content_right").clear();

 				NutzerDataProvider.gib(1).report6Generieren(nutzer ); // TODO KLASSE BESCHREIBEN

				}		
			
		});
	}
	
	
	
	public void zeigeReportFormular () {
		RootPanel.get("content_right").clear();

			NutzerDataProvider.gib(1).report6Generieren(nutzer ); // TODO KLASSE BESCHREIBEN
		
	}
	
	/**
	 * 
	 * Die Methode <code>gibFormular()</code> instanziert ein VerticalPanel und fügt diesem dem Button hinzu. Als Rückgabe Wert gibt die Methode das VerticalPanel zurück.  
	 */
	public VerticalPanel gibFormular(){
		VerticalPanel vpForm = new VerticalPanel();
		
		Label label =new Label(nutzer.getVorname() + nutzer.getNachname());
		label.setStylePrimaryName("selectionLabel");
		vpForm.add(label);
		
		vpForm.add(report6GenerierenButton);
		return vpForm;
	}
	
	
/**
 * TODO Kommentar nachtragen
 * @param ZellenObjekt
 */
	public void report6Generieren(NutzerZelle.ZellenObjekt ZellenObjekt){
		this.nutzer = ZellenObjekt.nutzer;

	}


}

