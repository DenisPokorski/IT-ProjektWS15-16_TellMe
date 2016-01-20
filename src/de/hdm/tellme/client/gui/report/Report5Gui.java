package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.ui.Button;
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


public class Report5Gui  extends VerticalPanel {

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
	
	private VerticalPanel vP =  new VerticalPanel(); 
 	
	/**
	 * Die onLoad-Methode wird verwendet um in der Seite die verschiedenen
	 * Panels anzuordnen. Sie startet beim Ausführen der Seite, da sie das
	 * Pendant zur <code>main()</code>-Methode einer normalen Java Applikation
	 * darstellt
	 */
 	public void onLoad()  {

 		final HTML headline = new HTML(" <div class='" + "subline"+ "'><h2>Reportgenerator 5: Alle Nachrichten je Nutzer anzeigen</h2></div> ");
 		final HTML subtext = new HTML(" <div class='"+ "subtext"+ "'><h4> Der Report 5 gibt alle Nutzerabonnements aus </h4></div> ");

		vP.add(headline);
		vP.add(subtext);
 		
		RootPanel.get("content_right").add(vP);
		RootPanel.get("content_left").add(new NutzerCellList().generiereCellList(CellListModus.Report5_NutzerNutzerAbonnement, 1));
		
 	}


}