package de.hdm.tellme.client.gui.report;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.TellMeReport;
import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Diese Klasse wird durch einen Click auf den entsprechendne
 * MenüBar-Report-Button instanziiert. Dieser Report gibt alle
 * Nacchrichten in einem bestimmten Zeitraum aus. Die Generierung des Reports
 * erfolgt in der Klasse HTMLReportWriter.
 * 
 * 
 * @author Zimmerman & Alex Homann
 * @version 1.1
 * 
 */

public class Report2Gui extends VerticalPanel {

	/**
	 * Es werden in einem Vertical Panel die verschiedenen benötigten Elemente
	 * dargestellt. Dies sind die 2 DatePicker um den Zeitraum auszuwählen und
	 * ein Button der die Funktion ReportGenerieren beinhaltet. Außerdem gibt es
	 * eine Überschrift und eine Beschreibung für den Report 2.
	 */

	private static Nutzer nutzer = null;

	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift2 = new Label("Report2: Nachrichten abfragen");
	HTML subSchrift2 = new HTML("<div class='" + "subline_report"+ "'><b>Bitte wähle den Zeitraum aus:</b></div>");

	private HorizontalPanel reportPanel2 = new HorizontalPanel();
	private VerticalPanel reportPanel2_left = new VerticalPanel();
	private VerticalPanel reportPanel2_right = new VerticalPanel();

	private DateBox vonDateBox = new DateBox();
	private DateBox bisDateBox = new DateBox();
	private Button report2Generieren = new Button("Report 2 generieren");
	DateTimeFormat dF = DateTimeFormat.getFormat("dd.MM.yyyy");

	private HTML beschreibung2 = new HTML("<ul><b>Der Report 2 gibt alle Nachrichten in einen bestimmten Zeitraum aus</b>"+ "<li>Um einen Report auszugeben, der alle Nachrichten in <b>einem bestimmten Zeitraum</b>"+ " darstellt <b>musst einen Zeitraum </b>auswählen.</li></ul>");


	/**
	 * Die onLoad-Methode wird verwendet um in der Seite die verschiedenen
	 * Panels anzuordnen. Sie startet beim Ausführen der Seite, da sie das
	 * Pendant zur <code>main()</code>-Methode einer normalen Java Applikation
	 * darstellt
	 */
	public void onLoad() {
		vonDateBox.setFormat(new DateBox.DefaultFormat(dF));
		bisDateBox.setFormat(new DateBox.DefaultFormat(dF));
		final HTML headline = new HTML(" <div class='"+ "subline"+ "'><h2>Reportgenerator 2: Alle Nachrichten je Zeitraum anzeigen </h2></div> ");
		final HTML subtext = new HTML(" <div class='"+ "subtext"+ "'><h4> Der Report 2 gibt alle Nachrichten in einen bestimmten Zeitraum aus  </h4></div> ");

		/*
		 * Die Panels werden anschaulich angeordnet.
		 */

		reportPanel.add(ueberSchrift2);
		reportPanel2.add(reportPanel2_left);
		reportPanel2.add(reportPanel2_right);
		reportPanel2_left.add(subSchrift2);
		reportPanel2_left.add(vonDateBox);
		reportPanel2_left.add(bisDateBox);
		reportPanel2_left.add(report2Generieren);
		report2Generieren.setStylePrimaryName("neueNchrichtBtn");

		reportPanel2_right.add(headline);
		reportPanel2_right.add(subtext);

		reportPanel2_right.add(beschreibung2);

		reportPanel.add(reportPanel2);

		/*
		 * Das RootPanel wird gesäubert und die verschiedenen Elemente für
		 * Report 2 zugeordnet.
		 */

		RootPanel.get("content").clear();
		RootPanel.get("content_left").clear();
		RootPanel.get("content_right").clear();
		RootPanel.get("content_right").add(reportPanel2_right);
		RootPanel.get("content_left").add(reportPanel2_left);

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
		 * Der Button <code>report2Generieren</code> bekommt eine Funktion,
		 * damit der Report 2 generiert werden kann.
		 */
		report2Generieren.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (vonDateBox.getValue() == null
						|| bisDateBox.getValue() == null) {
					Window.alert("Bitte beide Datumfelder befüllen");
				} else {
					
					RootPanel.get("content_left").clear();
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
					NutzerDataProvider.gib(1).report2Generieren(nutzer,
							new Timestamp(vonDateBox.getValue().getTime()),
							new Timestamp(bisDateBox.getValue().getTime()));
				}

			}
		});
	}

}
