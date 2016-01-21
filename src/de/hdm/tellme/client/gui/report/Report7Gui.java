package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.NutzerCellList;


/**
 * Diese Klasse wird durch Klick auf den Button Report 7 auf der
 * Willkommen-Seite geöffnet. Dieser Report gibt alle
 * Follower eines bestimmten Nutzers aus. Die Generierung des Reports
 * erfolgt in der Klasse HTMLReportWriter.
 * 
 * 
 * @author danathuering
 *
 */

public class Report7Gui extends VerticalPanel {

	HTML subline = new HTML("<div class='" + "subline_report"
			+ "'><b>Bitte wähle einen Nutzer aus:</b></div>");
	VerticalPanel vP = new VerticalPanel();

	/**
	 * Die onLoad-Methode wird verwendet um in der Seite die verschiedenen
	 * Panels anzuordnen. Sie startet beim Ausführen der Seite, da sie das
	 * Pendant zur <code>main()</code>-Methode einer normalen Java Applikation
	 * darstellt
	 */

	public void onLoad() {

		final HTML headline = new HTML(
				" <div class='"
						+ "subline"
						+ "'><h2>Reportgenerator 7: Alle Follower eines Nutzers</h2></div> ");
		final HTML subtext = new HTML(
				" <div class='"
						+ "subtext"
						+ "'><h4> Der Report 7 gibt alle Follower eines Nutzers zurück.   </h4></div> ");

		vP.add(headline);
		vP.add(subtext);

		RootPanel.get("content_right").add(vP);
		RootPanel.get("content_left").add(
				new NutzerCellList().generiereCellList(
						CellListModus.Report7_AlleNutzerDieDemAusgewaehltenNutzerFolgen, 1));

	}
}
