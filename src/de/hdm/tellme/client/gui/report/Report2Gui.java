package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.NutzerCellList;
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


public class Report2Gui  extends VerticalPanel {
	
	/**
	 * Bei klick auf den entsprchenden MenüBar-Button wird die Klasse aufgerufen
	 * und die <code>onLoad()</code> abgearbeitet. In dieser fügen wir dem
	 * linken Bereich des Root-Panels mit der Methode
	 * <code>generiereCellListModus</code> hinzu. In der Methode wird eine
	 * scrollbare CellList erstellt und ein spezifisches selectionModel
	 * <code>Report3_NutzerHashtagAbonnement</code> hinzugefügt. Die Angabe des
	 * Enum-Parameters <code>Report2_NutzerNutzerAbonnement</code> ermöglicht
	 * uns die CellList an mehreren Stellen,mit einem spezifischen
	 * Selektionsverhalten im System zu verwenden.
	 */
	
	HTML subline = new HTML ("<div class='"+"subline_report"+"'><b>Bitte wählen Sie einen Nutzer aus:</b></div>");
 	
 	public void onLoad()  {

		RootPanel
		.get("content_left")
		.add(subline);
 		
		RootPanel
		.get("content_left")
		.add(new NutzerCellList()
				.generiereCellList(CellListModus.Report2_NutzerNutzerAbonnement, 1));
		
		
 	}


}