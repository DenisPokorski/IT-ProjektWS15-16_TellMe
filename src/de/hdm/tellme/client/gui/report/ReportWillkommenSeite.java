package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Die Klasse <class>ReportWillkommenSeite </class> zeigt die Übersicht aller
 * Reports an und verweist daraufhin auf den entsprechenden Report.
 * 
 * @author Dana Thuering
 *
 */
public class ReportWillkommenSeite extends VerticalPanel {
	/*
	 * Es werden verschiedene Panels erstellt um die die Überschrift,
	 * Reportbeschreibungen und Buttons anzuordnen.
	 */
	VerticalPanel willkommenPanel = new VerticalPanel();

	HorizontalPanel anordnungsPanelOben = new HorizontalPanel();
	HorizontalPanel anordnungsPanelMitte = new HorizontalPanel();
	HorizontalPanel anordnungsPanelUnten = new HorizontalPanel();

	VerticalPanel left_Panel = new VerticalPanel();
	VerticalPanel right_Panel = new VerticalPanel();
	HorizontalPanel report1Panel = new HorizontalPanel();
	HorizontalPanel report2Panel = new HorizontalPanel();
	HorizontalPanel report3Panel = new HorizontalPanel();
	HorizontalPanel report4Panel = new HorizontalPanel();

	/*
	 * Die verschiedenen Reportuntertitel werden als Labels erstellt.
	 */
	Label report1Untertitel = new Label("Report 1");
	Label report2Untertitel = new Label("Report 2");
	Label report3Untertitel = new Label("Report 3");
	Label report4Untertitel = new Label("Report 4");
	Label report5Untertitel = new Label("Report 5");
	Label report6Untertitel = new Label("Report 6");
	Label report7Untertitel = new Label("Report 7");
	Label report8Untertitel = new Label("Report 8");

	/*
	 * Die verschiedenen Reportbeschreibungen werden als Labels erstellt.
	 */
	Label report1Beschreibung = new Label("Report 1 gibt alle Nachrichten eines Nutzers in einen bestimmten Zeitraum aus");
	Label report2Beschreibung = new Label("Report 2 gibt alle Nachrichten in einen bestimmten Zeitraum \n");
	Label report3Beschreibung = new Label("Report 3 gibt alle Nachrichten eines Nutzers aus");
	Label report4Beschreibung = new Label("Report 4 gibt die gesamten Nachrichten des Systems aus");
	Label report5Beschreibung = new Label("Report 5 gibt alle Nutzerabonnemonts eines Nutzers aus");
	Label report6Beschreibung = new Label("Report 6 gibt alle Hashtagabonnements eines Nutzers aus");
	Label report7Beschreibung = new Label("Report 7 gibt alle Follower eines Nutzers aus.");
	Label report8Beschreibung = new Label("Report 8 gibt alle Follower eines Hashtags aus.");

	/**
	 * Die onLoad-Methode wird verwendet um in der Seite die verschiedenen
	 * Panels anzuordnen. Sie startet beim Ausführen der Seite.
	 */

	public void onLoad() {

		/*
		 * Die verschiedene Buttons um auf die Reportseite zu gelangen werden
		 * erstellt.
		 */
		Button report1Button = new Button("Zu Report 1");
		Button report2Button = new Button("Zu Report 2");
		Button report3Button = new Button("Zu Report 3");
		Button report4Button = new Button("Zu Report 4");
		Button report5Button = new Button("Zu Report 5");
		Button report6Button = new Button("Zu Report 6");
		Button report7Button = new Button("Zu Report 7");
		Button report8Button = new Button("Zu Report 8");

		/*
		 * CSS-Styles werden angebunden
		 */
		report1Button.setStylePrimaryName("neueNchrichtBtn");
		report2Button.setStylePrimaryName("neueNchrichtBtn");
		report3Button.setStylePrimaryName("neueNchrichtBtn");
		report4Button.setStylePrimaryName("neueNchrichtBtn");
		report5Button.setStylePrimaryName("neueNchrichtBtn");
		report6Button.setStylePrimaryName("neueNchrichtBtn");
		report7Button.setStylePrimaryName("neueNchrichtBtn");
		report8Button.setStylePrimaryName("neueNchrichtBtn");

		HorizontalPanel hpHeadline = new HorizontalPanel();
		hpHeadline.setStylePrimaryName("hpHeadline");
		
		/*
		 * Die Überschrift wird als HTML-Code deklariert und beschrieben.
		 */
		HTML headline = new HTML(" <div class='" + "reportUebersichtPanel" + "'><h2>Reportgenerator </h2></div> ");

		/*
		 * Dem VerticalPanel wird die Überschrift und die beiden
		 * Anordnungspanels hinzugefügt. CSS-Style wird angebunden.
		 */
		willkommenPanel.add(headline);
		willkommenPanel.add(anordnungsPanelOben);
		willkommenPanel.add(anordnungsPanelUnten);
		willkommenPanel.setStylePrimaryName("reportUebersichtPanel");

		anordnungsPanelOben.add(left_Panel);
		anordnungsPanelOben.add(right_Panel);

		anordnungsPanelMitte.add(left_Panel);
		anordnungsPanelMitte.add(right_Panel);

		anordnungsPanelUnten.add(left_Panel);
		anordnungsPanelUnten.add(right_Panel);

		/*
		 * Den unterschiedlichen Anordnungspanel für die vier Bereiche werden
		 * die jeweiligen Report-Elemente hinzugefügt.
		 */
		left_Panel.add(report1Untertitel);
		left_Panel.add(report1Beschreibung);
		left_Panel.add(report1Button);

		left_Panel.add(report3Untertitel);
		left_Panel.add(report3Beschreibung);
		left_Panel.add(report3Button);

		left_Panel.add(report5Untertitel);
		left_Panel.add(report5Beschreibung);
		left_Panel.add(report5Button);

		left_Panel.add(report7Untertitel);
		left_Panel.add(report7Beschreibung);
		left_Panel.add(report7Button);

		right_Panel.add(report2Untertitel);
		right_Panel.add(report2Beschreibung);
		right_Panel.add(report2Button);

		right_Panel.add(report4Untertitel);
		right_Panel.add(report4Beschreibung);
		right_Panel.add(report4Button);

		right_Panel.add(report6Untertitel);
		right_Panel.add(report6Beschreibung);
		right_Panel.add(report6Button);

		right_Panel.add(report8Untertitel);
		right_Panel.add(report8Beschreibung);
		right_Panel.add(report8Button);

		/*
		 * Der RootPanel wird gesäubert und das WillkommenPanel wird
		 * hinzugefügt.
		 */
		RootPanel.get("content").clear();
		RootPanel.get("content").add(willkommenPanel);

		/**
		 * Führt zu Report1.
		 */
		report1Button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report1Gui());
			}
		});
		
		/**
		 * Führt zu Report2.
		 */
		report2Button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report2Gui());
			}
		});
		
		/**
		 * Führt zu Report3.
		 */
		report3Button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report3Gui());
			}
		});
		
		/**
		 * Führt zu Report4.
		 */
		report4Button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report4Gui());
			}
		});
		
		/**
		 * Führt zu Report5.
		 */
		report5Button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report5Gui());
			}
		});
		
		/**
		 * Führt zu Report6.
		 */
		report6Button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report6Gui());
			}
		});
		
		/**
		 * Führt zu Report7.
		 */
		report7Button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report7Gui());
			}
		});
		
		/**
		 * Führt zu Report8.
		 */
		report8Button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report8Gui());
			}
		});
	}

	/**
	 * Die Methode <code>setzeInhalt</code> wird den ClickHandlern aufgerufen um
	 * den Inhalt der Seite zu säubern und auf die neue Seite
	 * 
	 * @param ZuSetzendesPanel
	 */
	public static void setzeInhalt(Widget ZuSetzendesPanel) {
		RootPanel.get("content").clear();
		RootPanel.get("content").add(ZuSetzendesPanel);
	}
}