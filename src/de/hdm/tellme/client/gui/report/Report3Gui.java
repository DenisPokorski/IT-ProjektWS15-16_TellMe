package de.hdm.tellme.client.gui.report;

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

public class Report3Gui extends VerticalPanel {

	/**
	 * Bei klick auf den entsprchenden MenüBar-Button wird die Klasse aufgerufen
	 * und die <code>onLoad()</code> abgearbeitet. In dieser fügen wir dem
	 * linken Bereich des Root-Panels mit der Methode
	 * <code>generiereCellListModus</code> hinzu. In der Methode wird eine
	 * scrollbare CellList erstellt und ein spezifisches selectionModel
	 * <code>Report3_NutzerHashtagAbonnement</code> hinzugefügt. Die Angabe des
	 * Enum-Parameters <code>Report3_NutzerHashtagAbonnement</code> ermöglicht
	 * uns die CellList an mehreren Stellen,mit einem spezifischen
	 * Selektionsverhalten im System zu verwenden.
	 */
	public void onLoad() {

		RootPanel
				.get("content_left")
				.add(new NutzerCellList()
						.generiereCellList(CellListModus.Report3_NutzerHashtagAbonnement));
	}

}