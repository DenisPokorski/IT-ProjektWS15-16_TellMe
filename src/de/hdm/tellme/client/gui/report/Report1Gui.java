package de.hdm.tellme.client.gui.report;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.NutzerCellList;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.client.gui.editor.NutzerZelle.ZellenObjekt;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * 
 * 
 * 
 * 
 * Dieser Report zeigt alle Nachrichten eines Nutzer in einem bestimmten
 * Zeitraum an.
 * 
 * @author Zimmerman & Alex Homann
 * @version 1.1
 * 
 */

public class Report1Gui extends VerticalPanel {

	private Nutzer nutzer = null;
	/*
	 * Es werden in einem Vertical Panel die verschiedenen benötigten Elemente
	 * dargestellt. Dies sind die NutzerCellList um den Nutzer zu wählen, 2
	 * DatePicker um den Zeitraum auszuwählen und ein Button der die Funktion
	 * ReportGenerieren beinhaltet. Außerdem gibt es eine Überschrift und eine
	 * Beschreibung für den Report 1.
	 */

	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift1 = new Label("Report1: Nachrichten abfragen");
	HTML subSchrift1 = new HTML("<div class='" + "subline_report"
			+ "'><b>Bitte wählen Sie einen Nutzer aus:</b></div>");
	HTML subSchrift2 = new HTML("<div class='" + "subline_report"
			+ "'><b>Bitte wählen Sie den Zeitraum aus:</b></div>");

	private HorizontalPanel reportPanel1 = new HorizontalPanel();
	private VerticalPanel reportPanel1_left = new VerticalPanel();
	private VerticalPanel reportPanel1_right = new VerticalPanel();

	private DateBox vonDateBox = new DateBox();
	private DateBox bisDateBox = new DateBox();
	private Button report1Generieren = new Button("Report 1 generieren");

	private HTML beschreibung1 = new HTML(
			"<ul><b></b>"
					+ "<li>Um einen Report auszugeben, der  alle Nachrichten <b>eines Nutzers</b> "
					+ "in <b>einem bestimmten Zeitraum</b> darstellt, musst du einen Nutzer<b> UND</b> einen Zeitraum auswählen</li>");

	public void report1Generieren(NutzerZelle.ZellenObjekt ZellenObjekt) {
		this.nutzer = ZellenObjekt.nutzer;

	}

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
						+ "'><h2>Reportgenerator 1: Alle Nachrichten je Nutzer anzeigen</h2></div> ");
		final HTML subtext = new HTML(
				" <div class='"
						+ "subtext"
						+ "'><h4> Der Report 1 gibt alle Nachrichten eines Nutzers in einen bestimmten Zeitraum aus  </h4></div> ");

		/*
		 * Die Panels werden anschaulich angeordnet.
		 */
		reportPanel.add(ueberSchrift1);
		reportPanel1.add(reportPanel1_left);
		reportPanel1.add(reportPanel1_right);
		reportPanel1_left.add(subSchrift1);

		/**
		 * Dem <code>reportPanel1_left</code> wird die
		 * <code>NutzerCellList</code> im modi Report1_NachrichtNutzerZeitraum
		 * zugewiesen, dies geschieht im Modus 1, da 1 den Report darstellt.
		 */

		reportPanel1_left.add(new NutzerCellList().generiereCellList(
				CellListModus.Report1_NachrichtNutzerZeitraum, 1));
		reportPanel1_left.add(subSchrift2);
		reportPanel1_left.add(vonDateBox);
		reportPanel1_left.add(bisDateBox);
		reportPanel1_left.add(report1Generieren);
		report1Generieren.setStylePrimaryName("neueNchrichtBtn");

		reportPanel1_right.add(headline);
		reportPanel1_right.add(subtext);

		reportPanel1_right.add(beschreibung1);

		reportPanel.add(reportPanel1);
		/*
		 * Das RootPanel wird gesäubert und die verschiedenen Elemente für
		 * Report 1 zugeordnet.
		 */
		RootPanel.get("content").clear();
		RootPanel.get("content_left").clear();
		RootPanel.get("content_right").clear();
		RootPanel.get("content_right").add(reportPanel1_right);
		RootPanel.get("content_left").add(reportPanel1_left);
		/*
		 * Der jeweilige Zeitraum wird ausgewählt.
		 */
		vonDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub

			}
		});

		bisDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub

			}

		});
		/*
		 * Der Button <code>report1Generieren</code> bekommt eine Funktion,
		 * damit der Report 1 generiert werden kann.
		 */
		report1Generieren.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				RootPanel.get("content_left").clear();
				RootPanel.get("content_right").clear();

				RootPanel.get("content_right").add(headline);
				//	NutzerDataProvider.gib(1).report1Generieren(nutzer);

			}
		});
	}

}
