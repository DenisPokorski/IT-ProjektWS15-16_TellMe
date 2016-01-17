package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.tellme.shared.LoginInfo;

/**
 * Dies ist die Klasse <class>MenuBarReport</class>, sie wird verwendet um die
 * MenuBar darzustellen. Sie wird durch ein HorizontalPanel erweitert, damit
 * einzelnen Elemente nebeneinander angeordnet werden. Mit Hilfe der MenuBar ist
 * es möglich jeden Menüpunkt im Report zu erreichen.
 * 
 * 
 * @author Zimmermann
 * @version 1.0
 * @since 26.11.2015
 * 
 *        Die Klasse MenuBarReport implementiert den EntryPoint für das Modul
 *        "Report" der Applikation. Hier wird sowohl der Login, als auch das
 *        Menü im HTML-div "head_wrap" initialisiert.
 */

public class MenuBarReport extends HorizontalPanel {

	  HorizontalPanel menuePanel = new HorizontalPanel();

	/**
	 * Definition von leerem Konstruktor
	 * TODO
	 */
	public MenuBarReport() {

	}

	/**
	 * An dieser Stelle wird das LoginInfo von der Methode setLoginInfo(),
	 * übergeben und gesetzt.
	 */
	public void setLoginInfo(LoginInfo loginInfo) {
	}

	/**
	 * Bei erfolgreichem Login wird folgende Methode geladen. Dem Benutzer wird
	 * das Menü angezeigt.
	 */
	public void onLoad() {
		
		/**
		 * Es werden 2 MenuBar mit dem new-Operator instanziert und mit 'true'
		 * sichtbar geschaltet. Folgend wird jeweils ein Neues Item der Menubar
		 * hinzugefügt: Neuigkeiten und Einstellungen mit den Unterpunkten:
		 * Nutzeraboverwaltung, Hashtagaboverwaltung,
		 * Hashtagverwaltung,Benutzereinstellungen, Impressum und Log Out. Mit
		 * der 'public void execute()'-Methode wird definiert was bei Klick
		 * darauf passieren soll. In unserem Fall leeren wir erst den
		 * Cotent-Bereich mit dem Befehl clearContent(); Im Anschluss werden wir
		 * die GUI-Klassen dem 'RootPanel.get("content")' -Bereich zuordnen um
		 * den ausgewählten Menu-Punkt anzuzeigen.
		 */
		MenuBar HauptMenue = new MenuBar(false);
		HauptMenue.setAutoOpen(true);
		HauptMenue.setAnimationEnabled(true);
		/**
		 * An dieser Stelle wird ein Image-Objekt instanziiert. Anschließend
		 * wird ein String, in dem die URL der Logo-Bilddatei gespeichert wird,
		 * definiert. Nun wird der String (url) dem instanziiert Image-Objekt
		 * zugewiesen.
		 */
		Image logo = new Image();
		final String url = "http://127.0.0.1:8888/logo.png";
		logo.setUrl(url);

		/**
		 * Es werden 6 MenuBar mit dem new-Operator instanziert und mit
		 * 'true' sichtbar geschaltet. Folgend wird jeweils ein Neues Item der
		 * Menubar hinzugefügt: MeinProfil, Aboverwaltung und Nachrichten. Mit
		 * der 'public void execute()'-Methode wird definiert was bei Klick
		 * darauf passieren soll. In unserem Fall laden, leeren wir erst den
		 * Cotent-Bereich mit dem Befehl RootPanel.get("content").clear(); Im
		 * Anschluss werden wir die GUI-Klassen dem 'RootPanel.get("content")'
		 * -Bereich zuordnen.
		 */
		MenuBar reports = new MenuBar(true);
		reports.setAnimationEnabled(true);
		HauptMenue.addItem(new MenuItem("Reports", reports));

		/**
		 * Die folgenden "Commands" definieren was beim jeweiligen Aufruf der
		 * Menü-Punkte passieren soll.
		 */
		reports.addItem("Report Startseite", new Command() {

			@Override
			public void execute() {
				setzeInhalt(new ReportWillkommenSeite());

			}
		});
		
		/**
		 * Bei Klick auf den Untermenüpunkt Report 1 wird die Report1Gui als
		 * neuer Inhalt gesetzt.
		 */
		reports.addItem("Report 1", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report1Gui());
			}
		});

		/**
		 * Bei Klick auf den Untermenüpunkt Report 2 wird die Report2Gui als
		 * neuer Inhalt gesetzt.
		 */
		reports.addItem("Report 2", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report2Gui());

			}
		});

		/**
		 * Bei Klick auf den Untermenüpunkt Report 3 wird die Report3Gui als
		 * neuer Inhalt gesetzt.
		 */
		reports.addItem("Report 3", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report3Gui());
			}
		});

		/**
		 * Bei Klick auf den Untermenüpunkt Report 5 wird die Report5Gui als
		 * neuer Inhalt gesetzt.
		 */
		reports.addItem("Report 4", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report4Gui());

			}
		});

		/**
		 * Bei Klick auf den Untermenüpunkt Report 5 wird die Report5Gui als
		 * neuer Inhalt gesetzt.
		 */
		reports.addItem("Report 5", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report5Gui());

			}
		});
		
		/**
		 * Bei Klick auf den Untermenüpunkt Report 6 wird die Report6Gui als
		 * neuer Inhalt gesetzt.
		 */
		reports.addItem("Report 6", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report6Gui());

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
	public static void setzeInhalt(Widget ZuSetzendesPanelLinks,
			Widget ZuSetzendesPanelRechts) {

		RootPanel.get("ButtonBar").clear();
		RootPanel.get("content_left").clear();
		RootPanel.get("content_right").clear();
		RootPanel.get("content").clear();
		RootPanel.get("content_left").add(ZuSetzendesPanelLinks);
		RootPanel.get("content_right").add(ZuSetzendesPanelRechts);
	}

}
