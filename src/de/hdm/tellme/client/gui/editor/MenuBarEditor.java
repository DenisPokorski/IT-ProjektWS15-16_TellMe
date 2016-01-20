package de.hdm.tellme.client.gui.editor;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.Schaukasten.Impressum;
import de.hdm.tellme.client.Schaukasten.NeuigkeitenEditor;
import de.hdm.tellme.client.Schaukasten.NutzerBearbeitenEditor;
import de.hdm.tellme.client.gui.report.Report1Gui;
import de.hdm.tellme.client.gui.report.Report2Gui;
import de.hdm.tellme.client.gui.report.Report3Gui;
import de.hdm.tellme.client.gui.report.Report4Gui;
import de.hdm.tellme.client.gui.report.Report5Gui;
import de.hdm.tellme.client.gui.report.Report6Gui;
import de.hdm.tellme.client.gui.report.Report7Gui;
import de.hdm.tellme.client.gui.report.Report8Gui;
import de.hdm.tellme.client.gui.report.ReportWillkommenSeite;

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

public class MenuBarEditor extends HorizontalPanel {


	

	/**
	 * Definition von Konstruktor TODO LEERER KONSTRUKTOR BESCHREIBUNG
	 */
	public MenuBarEditor() {

	}

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
		 * Hier wird das TellMe logo implementiert. Bei Klick auf das
		 * TellMe-Logo wird, ähnlich wie auf sozialen Netzwerken, der
		 * NeugikeitenEditor aufgerufen.
		 */
		final String LogoLink = "<img src='/logo.png'/>";
		HauptMenue.addItem(new MenuItem(LogoLink, true, new Command() {
			@Override
			public void execute() {
				NeuigkeitenEditor nE = new NeuigkeitenEditor();
				MenuBarEditor.setzeInhalt(nE.gibFilterPanel(),nE);
			}
		}));
		/**
		 * Wenn man auf den Menupunkt Neuigkeiten Klickt gelangt man zu den
		 * Neuigkeiten, weil der Inhalt neu gesetzt wird.
		 */
		HauptMenue.addItem("Neuigkeiten", new Command() {
			@Override
			public void execute() {
				NeuigkeitenEditor nE = new NeuigkeitenEditor();
				MenuBarEditor.setzeInhalt(nE.gibFilterPanel(),nE);
			}
		});

		/**
		 * Wenn man über den Menupunkt Einstellung mit der Maus fährt, öffnet
		 * sich eine Liste mit den Untermenüpunkten: Nutzeraboverwaltung,
		 * Hashtagaboverwaltung, Hashtagverwaltung,Benutzereinstellungen,
		 * Impressum und Log Out.
		 */
		MenuBar EinstellungenMenu = new MenuBar(true);
		EinstellungenMenu.setAnimationEnabled(true);
		HauptMenue.addItem(new MenuItem("Einstellungen", EinstellungenMenu));

		/**
		 * Bei Klick auf den Untermenüpunkt Nutzeraboverwaltung wird die
		 * Nutzeraboverwaltung als neuer Inhalt gesetzt.
		 */
		EinstellungenMenu.addItem("Nutzeraboverwaltung", new Command() {
			@Override
			public void execute() {

				setzeInhalt(new NutzerCellList().generiereCellList(
						CellListModus.Einstellungen, 0), new NutzerFormular()
						.gibBeschreibung());
			}
		});

		/**
		 * Bei Klick auf den Untermenüpunkt Hashtagaboverwaltung wird die
		 * Hashtagaboverwaltung als neuer Inhalt gesetzt.
		 */
		EinstellungenMenu.addItem("Hashtagaboverwaltung", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new HashtagCellList()
						.generiereCellList(CellListModus.Einstellungen),
						new HashtagFormular().gibBeschreibungHtAbo());
			}
		});

		/**
		 * Bei Klick auf den Untermenüpunkt Hashtagverwaltung wird die
		 * Hashtagverwaltung als neuer Inhalt gesetzt.
		 */
		EinstellungenMenu.addItem("Hashtagverwaltung", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new HashtagCellList()
						.generiereCellList(CellListModus.HashtagVerwaltung),
						new HashtagFormular().gibBeschreibungHtVerwaltung());
				RootPanel.get("content_right").add(
						new HashtagFormular().gibAnlegenFormular());

			}
		});
		/**
		 * Bei Klick auf den Untermenüpunkt Benutzereinstellungen werden die
		 * Benutzereinstellungen als neuer Inhalt gesetzt.
		 */
		EinstellungenMenu.addItem("Benutzereinstellungen", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new NutzerBearbeitenEditor().gibNutzerBearbeitenFormular());
			}
		});
		/**
		 * Bei Klick auf den Untermenüpunkt Impressum wird das Impressum als
		 * neuer Inhalt gesetzt.
		 */
		EinstellungenMenu.addItem("Impressum", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Impressum().getHtmlImpressum());

			}
		});
		/**
		 * Bei Klick auf den Untermenüpunkt Log out wird das Log out Menu als
		 * neuer Inhalt gesetzt.
		 */
		EinstellungenMenu.addItem("Log out", new Command() {
			@Override
			public void execute() {
				Window.Location.assign(TellMe.gibEingeloggterBenutzer()
						.getLogoutUrl());

			}
		});

		// TODO: TEMPORÄRES MENÜ LÖSCHEN VOR ABGABE
		MenuBar tempReports = new MenuBar(true);
		tempReports.setAnimationEnabled(true);
		HauptMenue.addItem(new MenuItem("Reports", tempReports));

		tempReports.addItem("Report Startseite", new Command() {

			@Override
			public void execute() {
				setzeInhalt(new ReportWillkommenSeite());

			}
		});
		tempReports.addItem("Report 1", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report1Gui());
			}
		});

		tempReports.addItem("Report 2", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report2Gui());

			}
		});

		tempReports.addItem("Report 3", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report3Gui());
			}
		});

		tempReports.addItem("Report 4", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report4Gui());

			}
		});

		tempReports.addItem("Report 5", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report5Gui());

			}
		});

		tempReports.addItem("Report 6", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report6Gui());

			}
		});
		
		tempReports.addItem("Report 7", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report7Gui());

			}
		});
		
		tempReports.addItem("Report 8", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report8Gui());

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