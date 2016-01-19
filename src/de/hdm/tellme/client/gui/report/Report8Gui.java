package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.HashtagCellList;
import de.hdm.tellme.client.gui.editor.NutzerCellList;

public class Report8Gui extends VerticalPanel{

	HTML subline = new HTML("<div class='" + "subline_report"
			+ "'><b>Bitte wählen Sie ein Hashtag aus: </b></div>");
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
						+ "'><h2>Reportgenerator 8: Alle Follower eines Hashtags</h2></div> ");
		final HTML subtext = new HTML(
				" <div class='"
						+ "subtext"
						+ "'><h4> Der Report 8 gibt alle Follower eines Hashtags zurück.   </h4></div> ");

		vP.add(headline);
		vP.add(subtext);

		RootPanel.get("content_right").add(vP);
		RootPanel.get("content_left").add(
				new HashtagCellList().generiereCellList(CellListModus.Report8_AlleNutzerDieDemAusgewaehltenHashtagFolgen));

	}

}
