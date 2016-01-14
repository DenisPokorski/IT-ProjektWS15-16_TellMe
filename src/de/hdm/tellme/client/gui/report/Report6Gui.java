package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.NutzerCellList;

/**
 * Diese Klasse wird bei uns durch Click auf den entsprechendne
 * MenüBar-Report-Button instanziiert. Dieser Report gibt alle
 * Hashtagabonnements eines bestimmten Nutzers aus. Die Generierung des Reports
 * erfolgt in der Klasse HTMLReportWriter.
 * 
 * @author Zimmermann
 * @version 1.0
 * 
 */

public class Report6Gui extends VerticalPanel {


	
	/**
	 * Bei klick auf den entsprchenden MenüBar-Button wird die Klasse aufgerufen
	 * und die <code>onLoad()</code> abgearbeitet. In dieser fügen wir dem
	 * linken Bereich des Root-Panels mit der Methode
	 * <code>generiereCellListModus</code> hinzu. In der Methode wird eine
	 * scrollbare CellList erstellt und ein spezifisches selectionModel
	 * <code>Report6_NutzerHashtagAbonnement</code> hinzugefügt. Die Angabe des
	 * Enum-Parameters <code>Report6_NutzerHashtagAbonnement</code> ermöglicht
	 * uns die CellList an mehreren Stellen,mit einem spezifischen
	 * Selektionsverhalten im System zu verwenden.
	 */
	
	HTML subline = new HTML ("<div class='"+"subline_report"+"'><b>Bitte wählen Sie einen Nutzer aus:</b></div>");
 	VerticalPanel vP = new VerticalPanel(); 
	
	public void onLoad() {
		

		final HTML headline = new HTML(" <div class='" + "subline"
				+ "'><h2>Reportgenerator 6: Alle Hashtagabos je anzeigen</h2></div> ");
		final HTML subtext = new HTML(
				" <div class='"
						+ "subtext"
						+ "'><h4> Der Report 6 gibt alle Hashtagabonnoments eines Nutzers in einen bestimmten Zeitraum zurück.   </h4></div> ");


		vP.add(headline);
		vP.add(subtext);

		
 		
		RootPanel
		.get("content_right")
		.add(vP);
		
		RootPanel
				.get("content_left")
				.add(new NutzerCellList()
						.generiereCellList(CellListModus.Report6_NutzerHashtagAbonnement, 1));
		
		
		 
	}

}