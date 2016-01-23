package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.TellMeReport;
import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * 
 * 
 * 
 * 
 * Dieser Report zeigt alle Nachrichten an. Deshalb benötigt die GUI von Report
 * 4 keine Elemente um Bedingungen zu erfüllen, sondern hat nur einen Button zum
 * erstellen des Reports.
 * 
 * @author Zimmerman & Alex Homann
 * @version 1.1
 * 
 */

public class Report4Gui extends VerticalPanel {
	/**
	 * Es werden in einem Vertical Panel die verschiedenen benötigten Elemente
	 * dargestellt. Dies ist ein Button der die Funktion ReportGenerieren
	 * beinhaltet. Außerdem gibt es eine Überschrift und eine Beschreibung für
	 * den Report 4.
	 */
	private static Nutzer nutzer = null;
	private VerticalPanel reportPanel1_right = new VerticalPanel();
	private Button report4Generieren = new Button("Report 4 generieren");
	private HTML beschreibung1 = new HTML("<ul><b>Der Report 4 gibt alle Nachrichten aus</b>" + "<li>Um einen Report auszugeben, der  alle Nachrichten darstellt, musst du den Button drücken</li></ul>");

	/**
	 * Die onLoad-Methode wird verwendet um in der Seite die verschiedenen
	 * Panels anzuordnen. Sie startet beim Ausführen der Seite, da sie das
	 * Pendant zur <code>main()</code>-Methode einer normalen Java Applikation
	 * darstellt
	 */
	public void onLoad() {

		final HTML headline = new HTML(" <div class='"+ "subline"+ "'><h2>Reportgenerator 4: Alle Nachrichten des Systems anzeigen</h2></div> ");
		final HTML subtext = new HTML(" <div class='"+ "subtext"+ "'><h4> Der Report 4 gibt alle Nachrichten des gesamten System aus  </h4></div> ");

		report4Generieren.setStylePrimaryName("neueNchrichtBtn");

		/**
		 * Die Panels werden anschaulich angeordnet.
		 */
		reportPanel1_right.add(headline);
		reportPanel1_right.add(subtext);
		reportPanel1_right.add(beschreibung1);

		/**
		 * Das RootPanel wird gesäubert und die verschiedenen Elemente für
		 * Report 4 zugeordnet.
		 */
	
		
		RootPanel.get("content").clear();
 		RootPanel.get("content_right").clear();
		RootPanel.get("content_right").add(reportPanel1_right);
		RootPanel.get("content_right").add(report4Generieren);

		/**
		 * Der Button <code>report3Generieren</code> bekommt eine Funktion,
		 * damit der Report 4 generiert werden kann.
		 */

		report4Generieren.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
 				RootPanel.get("content_right").clear();
 				
 				
				VerticalPanel ladenPanel = new VerticalPanel();
				ladenPanel.setStylePrimaryName("ladenPanel");

				Image ladenImg = new Image("laden.gif");
				ladenImg.setStylePrimaryName("ladenImg");
				ladenPanel.add(ladenImg);

				HTML ladenLabel = new HTML("<h1> Bitte warten <h1><br /><h3>Bitte warte einen Augenblick bis der Report generiert wurde. Vielen Dank.</h3>");
				ladenPanel.add(ladenLabel);
				
				RootPanel.get("content").add(ladenPanel);
				

 				
				nutzer = TellMeReport.gibEingeloggterBenutzer().getUser();
				NutzerDataProvider.gib(1).report4Generieren(nutzer);

			}
		});
	}

}
