package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.tellme.client.TellMeReport;

/**
 * Dies ist die Klasse <class>MenuBarEditor</class>, sie wird verwendet um die
 * MenuBar darzustellen. Sie wird durch ein HorizontalPanel erweitert, damit die
 * einzelnen Elemente nebeneinander angeordnet werden. Mit Hilfe der MenuBar ist
 * es möglich jeden Menüpunkt im Editor zu erreichen.
 * 
 * 
 * @author Zimmermann
 * @version 1.1
 * @since 26.11.2015
 * 
 */

public class MenuBarReport extends HorizontalPanel {

	/**
	 * Konstruktor 
	 */
	public MenuBarReport() {

	}

	public void onLoad() {
		MenuBar HauptMenue = new MenuBar(false);
		HauptMenue.setAutoOpen(true);
		HauptMenue.setAnimationEnabled(true);

		MenuBar ReportMenu = new MenuBar(true);
		ReportMenu.setAnimationEnabled(true);

		HauptMenue.addItem("Startseite", new Command() {

				@Override
				public void execute() {
					setzeInhalt(new ReportWillkommenSeite());
				}
			});
			
			HauptMenue.addItem("Report 1", new Command() {
				@Override
				public void execute() {
					setzeInhalt(new Report1Gui());
				}
			});
	
			HauptMenue.addItem("Report 2", new Command() {
				@Override
				public void execute() {
					setzeInhalt(new Report2Gui());
				}
			});
	
			HauptMenue.addItem("Report 3", new Command() {
				@Override
				public void execute() {
					setzeInhalt(new Report3Gui());
				}
			});
	
			HauptMenue.addItem("Report 4", new Command() {
				@Override
				public void execute() {
					setzeInhalt(new Report4Gui());
				}
			});
	
			HauptMenue.addItem("Report 5", new Command() {
				@Override
				public void execute() {
					setzeInhalt(new Report5Gui());
				}
			});
	
			HauptMenue.addItem("Report 6", new Command() {
				@Override
				public void execute() {
					setzeInhalt(new Report6Gui());
				}
			});
	
			HauptMenue.addItem("Report 7", new Command() {
				@Override
				public void execute() {
					setzeInhalt(new Report7Gui());
				}
			});
	
			HauptMenue.addItem("Report 8", new Command() {
				@Override
				public void execute() {
					setzeInhalt(new Report8Gui());
				}
			});
			
			HauptMenue.addItem("Logout", new Command() {
				@Override
				public void execute() {
					Window.Location.assign(TellMeReport.gibEingeloggterBenutzer().getLogoutUrl());
				}
			});

		RootPanel.get("header").add(HauptMenue);
	}

	/**
	 * Es gibt zwei verschiedene Arten von der Methode setzeInhalt. Bei einer
	 * wird das zu setzende Panel im zum content hinzugefügt, bei der anderen
	 * werden die zu setzenden Panels dem content_left, also der linken Seite
	 * der Applikation und dem content_right, also der rechten Seite der
	 * Applikation hinzugefügt. So können die unterschiedlichen Arten der zu
	 * setzenden Panels verwirklicht werden.
	 */

	/**
	 * Mit der Methode setzeInhalt wird das Panel das gesetzt werden soll im
	 * Content gesetzt. Es wird der Inhalt gesäubert. Genauer gesagt wird der
	 * linke Content, der rechte Content und der Content gesäubert und es wird
	 * dem Content das zu setztendePanel hinzugefügt.
	 * 
	 * @param ZuSetzendesPanel
	 */
	public static void setzeInhalt(Widget ZuSetzendesPanel) {

		RootPanel.get("ButtonBar").clear();
		RootPanel.get("content_left").clear();
		RootPanel.get("content_right").clear();
		RootPanel.get("content").clear();
		RootPanel.get("content").add(ZuSetzendesPanel);
	}

	/**
	 * Mit der Methode setzeInhalt werden die Panels die gesetzt werden soll im
	 * Content gesetzt. Es wird der Inhalt gesäubert. Genauer gesagt wird der
	 * linke Content, der rechte Content und der Content gesäubert. Daraufhin
	 * wird dem content_left und dem content_right das zu setzendesPanellinks
	 * oder das ZuSetzendesPanel rechts hinzugefügt.
	 * 
	 * @param ZuSetzendesPanelLinks
	 * @param ZuSetzendesPanelRechts
	 */
	public static void setzeInhalt(Widget ZuSetzendesPanelLinks,Widget ZuSetzendesPanelRechts) {

		RootPanel.get("ButtonBar").clear();
		RootPanel.get("content_left").clear();
		RootPanel.get("content_right").clear();
		RootPanel.get("content").clear();
		RootPanel.get("content_left").add(ZuSetzendesPanelLinks);
		RootPanel.get("content_right").add(ZuSetzendesPanelRechts);
	}

}