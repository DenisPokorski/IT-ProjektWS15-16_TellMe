package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ReportWillkommenSeite extends VerticalPanel {
	VerticalPanel willkommenPanel = new VerticalPanel();
	Label reportUeberschrift = new Label("REPORT");
	HorizontalPanel anordnungsPanel = new HorizontalPanel();
	VerticalPanel left_Panel = new VerticalPanel();
	VerticalPanel center_Panel = new VerticalPanel();
	VerticalPanel right_Panel = new VerticalPanel();
	HorizontalPanel report1Panel = new HorizontalPanel();
	HorizontalPanel report2Panel = new HorizontalPanel();
	HorizontalPanel report3Panel = new HorizontalPanel();
	Label report1Untertitel = new Label("Report 1");
	Label report2Untertitel = new Label("Report 2");
	Label report3Untertitel = new Label("Report 3");
	Label report1Beschreibung = new Label(
			"Der Report 1 gibt alle Nachrichten eines Nutzers in einen bestimmten Zeitraum, \n"
					+ "alle Nachrichten eines Nutzers,\n alle Nachrichten in einem bestimmten Zeitraum"
					+ " \n oder alle Nachrichten aus");
	Label report2Beschreibung = new Label(
			"Der Report 2 gibt alle du bist ne kleine missge eines Nutzers aus");
	Label report3Beschreibung = new Label(
			"Der Report 3 gibt alle Hashtagabonnements eines Nutzers aus");
	


	public void onLoad() {
		
		

		Button report1Button = new Button("Zu Report 1");
		Button report2Button = new Button("Zu Report 2");
		Button report3Button = new Button("Zu Report 3");
		
		report3Button.setStylePrimaryName("neueNchrichtBtn");
		report2Button.setStylePrimaryName("neueNchrichtBtn");
		report1Button.setStylePrimaryName("neueNchrichtBtn");
		
		HorizontalPanel hpHeadline = new HorizontalPanel();
		hpHeadline.setStylePrimaryName("hpHeadline");

		HTML headline = new HTML(" <div class='" + "reportUebersichtPanel"
				+ "'><h2>Reportgenerator </h2></div> ");
		willkommenPanel.add(headline);

		willkommenPanel.add(anordnungsPanel);
		willkommenPanel.setStylePrimaryName("reportUebersichtPanel");

		anordnungsPanel.add(left_Panel);
		anordnungsPanel.add(center_Panel);
		anordnungsPanel.add(right_Panel);

		left_Panel.add(report1Untertitel);
		left_Panel.add(report1Beschreibung);
		left_Panel.add(report1Button);

		center_Panel.add(report2Untertitel);
		center_Panel.add(report2Beschreibung);
		center_Panel.add(report2Button);

		right_Panel.add(report3Untertitel);
		right_Panel.add(report3Beschreibung);
		right_Panel.add(report3Button);

		RootPanel.get("content").clear();
		RootPanel.get("content").add(willkommenPanel);

		report1Button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report1Gui());

			}

		});

		report2Button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report2Gui());

			}

		});

		report3Button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setzeInhalt(new Report3Gui());

			}

		});

	}

	public static void setzeInhalt(Widget ZuSetzendesPanel) {

		RootPanel.get("content").clear();
		RootPanel.get("content").add(ZuSetzendesPanel);

	}

}