package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.NutzerCellList;
import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
/**
 * 
 * 
 * 
 * Diese Klasse wird bei uns durch Click auf den entsprechendne
 * MenüBar-Report-Button instanziiert. Dieser Report gibt alle
 * Nutzerabonnements eines bestimmten Nutzers aus. Die Generierung des Reports
 * erfolgt in der Klasse HTMLReportWriter.
 * 
 * 
 * @author Pokorski
 * @version 1.0
 * 
 */


public class Report5Gui  extends VerticalPanel {
	
	private Button report5Generieren = new Button("Report 5 generieren");

	/**
	 * Bei klick auf den entsprchenden MenüBar-Button wird die Klasse aufgerufen
	 * und die <code>onLoad()</code> abgearbeitet. In dieser fügen wir dem
	 * linken Bereich des Root-Panels mit der Methode
	 * <code>generiereCellListModus</code> hinzu. In der Methode wird eine
	 * scrollbare CellList erstellt und ein spezifisches selectionModel
	 * <code>Report6_NutzerHashtagAbonnement</code> hinzugefügt. Die Angabe des
	 * Enum-Parameters <code>Report5_NutzerNutzerAbonnement</code> ermöglicht
	 * uns die CellList an mehreren Stellen,mit einem spezifischen
	 * Selektionsverhalten im System zu verwenden.
	 */
	
	HTML subline = new HTML ("<div class='"+"subline_report"+"'><b>Bitte wählen Sie einen Nutzer aus:</b></div>");
 	VerticalPanel vP =  new VerticalPanel(); 
 	
 	
	/**
	 * Die onLoad-Methode wird verwendet um in der Seite die verschiedenen
	 * Panels anzuordnen. Sie startet beim Ausführen der Seite, da sie das
	 * Pendant zur <code>main()</code>-Methode einer normalen Java Applikation
	 * darstellt
	 */
 	public void onLoad()  {
 		
 		report5Generieren.setStylePrimaryName("neueNchrichtBtn");

 		final HTML headline = new HTML(" <div class='" + "subline"
				+ "'><h2>Reportgenerator 5: Alle Nachrichten je Nutzer anzeigen</h2></div> ");
 		final HTML subtext = new HTML(
				" <div class='"
						+ "subtext"
						+ "'><h4> Der Report 5 gibt alle Nutzerabonnements aus </h4></div> ");


		vP.add(headline);
		vP.add(subtext);

		
 		
		RootPanel
		.get("content_right")
		.add(vP);
		
		RootPanel
		.get("content_left")
		.add(new NutzerCellList()
				.generiereCellList(CellListModus.Report5_NutzerNutzerAbonnement, 1));
		
		RootPanel
		.get("content_left")
		.add(report5Generieren);
		
		report5Generieren.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				RootPanel.get("content_left").clear();
				RootPanel.get("content_right").clear();

				RootPanel.get("content_right").add(headline);

			//	NutzerDataProvider.gib(1).report4Generieren(nutzer);
				
				
			}
		});
 		
		
 	}


}