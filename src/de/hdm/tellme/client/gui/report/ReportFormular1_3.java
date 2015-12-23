package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;

import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Report 1_3 gibt den alle Nachrichten eines Nutzers aus, ohne
 * den Zeitraumm zu beschränken
 * @author Alex
 *
 */
public class ReportFormular1_3 {
	/**
	 * Wir benötigen ein Nutzer-Objekt, dass anfangs auf null gesetzt wird
	 */
	private Nutzer nutzer = null;
	
	Button report1_3GenerierenButton = new Button ("Report 1_3 erstellen");
	/**
	 * Methode ReportFormular1_3 wird aufgerufen um den CLickhandler in ihr zu instanziieren. 
	 */
	public ReportFormular1_3(){
		
		report1_3GenerierenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				NutzerDataProvider.gib().report1_3Generieren(nutzer);
			}
		});
	}
	

}
