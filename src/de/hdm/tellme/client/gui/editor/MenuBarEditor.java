package de.hdm.tellme.client.gui.editor;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.tellme.client.Impressum;
import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.Schaukasten.NeuigkeitenEditor;
import de.hdm.tellme.client.Schaukasten.NutzerBearbeitenEditor;
import de.hdm.tellme.client.gui.report.Report1Gui;
import de.hdm.tellme.client.gui.report.Report2Gui;
import de.hdm.tellme.client.gui.report.Report3Gui;
import de.hdm.tellme.client.gui.report.Report4Gui;
import de.hdm.tellme.client.gui.report.Report5Gui;
import de.hdm.tellme.client.gui.report.Report6Gui;
import de.hdm.tellme.client.gui.report.ReportWillkommenSeite;

/**
 * Dies ist die Klasse <class>MenuBarEditor</class>, sie wird verwendet um die
 * MenuBar darzustellen. Sie wird durch ein HorizontalPanel erweitert, damit die
 * einzelnen Elemente nebeneinander angeordnet werden. TODO
 * 
 * @author Zimmermann
 * @version 1.1
 * @since 26.11.2015
 * 
 */

public class MenuBarEditor extends HorizontalPanel {

	private static NeuigkeitenEditor ansichtNeuigkeiten = null;

	public static NeuigkeitenEditor gibansichtNeuigkeiten() {
		ansichtNeuigkeiten = new NeuigkeitenEditor();
		return ansichtNeuigkeiten;
	}

	private static NutzerBearbeitenEditor ansichtEinstellungenBenutzereinstellungen = null;

	public static NutzerBearbeitenEditor gibansichtEinstellungenBenutzereinstellungen() {
		// lade jedes mal neu um Listen aktuell zu halten
		ansichtEinstellungenBenutzereinstellungen = new NutzerBearbeitenEditor();
		return ansichtEinstellungenBenutzereinstellungen;
	}

	/**
	 * Definition von Konstruktor
	 */
	public MenuBarEditor() {

	}

	public void onLoad() {
		/**
		 * Es werden 6 MenuBar mit dem new-Operator instanziert und mit 'true'
		 * sichtbar geschaltet. Folgend wird jeweils ein Neues Item der Menubar
		 * hinzugefügt: MeinProfil, Aboverwaltung, Nachrichten und Report 1-3.
		 * Mit der 'public void execute()'-Methode wird definiert was bei Klick
		 * darauf passieren soll. In unserem Fall laden, leeren wir erst den
		 * Cotent-Bereich mit dem Befehl clearContent(); Im Anschluss werden wir
		 * die GUI-Klassen dem 'RootPanel.get("content")' -Bereich zuordnen.
		 */
		MenuBar HauptMenue = new MenuBar(false);
		HauptMenue.setAutoOpen(true);
		HauptMenue.setAnimationEnabled(true);

		final String LogoLink = "<img src='/logo.png'/>";
		HauptMenue.addItem(new MenuItem(LogoLink, true, new Command() {
			@Override
			public void execute() {
				// Nichts laden wenn Benutzer Logo auswählt
			}
		}));

		HauptMenue.addItem("Neuigkeiten", new Command() {
			@Override
			public void execute() {

				setzeInhalt(NeuigkeitenEditor.gibFilterPanel(),
						gibansichtNeuigkeiten());
			}
		});

		MenuBar EinstellungenMenu = new MenuBar(true);
		EinstellungenMenu.setAnimationEnabled(true);
		HauptMenue.addItem(new MenuItem("Einstellungen", EinstellungenMenu));

		EinstellungenMenu.addItem("NutzeraboVerwaltung", new Command() {
			@Override
			public void execute() {

				setzeInhalt(new NutzerCellList().generiereCellList(
						CellListModus.Einstellungen, 0), new NutzerFormular()
						.gibBeschreibung());
			}
		});

		EinstellungenMenu.addItem("HashtagaboVerwaltung", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new HashtagCellList()
						.generiereCellList(CellListModus.Einstellungen),
						new HashtagFormular().gibBeschreibungHtAbo());
			}
		});

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

		EinstellungenMenu.addItem("Benutzereinstellungen", new Command() {
			@Override
			public void execute() {
				setzeInhalt(gibansichtEinstellungenBenutzereinstellungen());
			}
		});

		EinstellungenMenu.addItem("Impressum", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Impressum().getHtmlImpressum());

			}
		});

		EinstellungenMenu.addItem("Log out", new Command() {
			@Override
			public void execute() {
				Window.Location.assign(TellMe.eingeloggterBenutzer
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

		RootPanel.get("header").add(HauptMenue);

	}

	public static void setzeInhalt(Widget ZuSetzendesPanel) {

		RootPanel.get("ButtonBar").clear();
		RootPanel.get("content_left").clear();
		RootPanel.get("content_right").clear();
		RootPanel.get("content").clear();
		RootPanel.get("content").add(ZuSetzendesPanel);
	}

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