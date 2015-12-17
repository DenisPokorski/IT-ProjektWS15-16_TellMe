package de.hdm.tellme.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.report.Report1;
import de.hdm.tellme.client.gui.report.Report2;
import de.hdm.tellme.client.gui.report.Report3;

/**
 * 
 * @author Zimmermann
 * @version 1.1
 * @since 26.11.2015
 * 
 */

public class MenuBarEditor extends HorizontalPanel {
	/**
	 * Alle Ansichten sollen aus Performancegründen nur einmal erstellt werden.
	 * 
	 */
	private static NachrichtenverwaltungEditor ansichtUnterhaltungen = null;

	public static NachrichtenverwaltungEditor gibansichtUnterhaltungen() {
		if (ansichtUnterhaltungen == null)
			ansichtUnterhaltungen = new NachrichtenverwaltungEditor();
		return ansichtUnterhaltungen;
	}

	private static NeuigkeitenEditor ansichtNeuigkeiten = null;

	public static NeuigkeitenEditor gibansichtNeuigkeiten() {
		if (ansichtNeuigkeiten == null)
			ansichtNeuigkeiten = new NeuigkeitenEditor();
		return ansichtNeuigkeiten;
	}

	private static AboverwaltungEditor ansichtEinstellungenAboverwatung = null;

	public static AboverwaltungEditor gibansichtEinstellungenAboverwatung() {
		// lade jedes mal neu um Listen aktuell zu halten
		ansichtEinstellungenAboverwatung = new AboverwaltungEditor();

		return ansichtEinstellungenAboverwatung;
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

		HauptMenue.addItem("Unterhaltungen", new Command() {
			@Override
			public void execute() {
				setzeInhalt(gibansichtUnterhaltungen());
			}
		});

		HauptMenue.addItem("Neuigkeiten", new Command() {
			@Override
			public void execute() {
				setzeInhalt(gibansichtNeuigkeiten());
			}
		});

		MenuBar EinstellungenMenu = new MenuBar(true);
		EinstellungenMenu.setAnimationEnabled(true);
		HauptMenue.addItem(new MenuItem("Einstellungen", EinstellungenMenu));

		EinstellungenMenu.addItem("Aboverwaltung", new Command() {
			@Override
			public void execute() {
				setzeInhalt(gibansichtEinstellungenAboverwatung());
			}
		});

		EinstellungenMenu.addItem("Benutzereinstellungen", new Command() {
			@Override
			public void execute() {
				setzeInhalt(gibansichtEinstellungenBenutzereinstellungen());
			}
		});

		EinstellungenMenu.addItem("Hashtagverwaltung(?)", new Command() {
			@Override
			public void execute() {
				// TODO:really?!
			}
		});

		// TODO: TEMPORÄRES MENÜ LÖSCHEN VOR ABGABE
		MenuBar tempReports = new MenuBar(true);
		tempReports.setAnimationEnabled(true);
		HauptMenue.addItem(new MenuItem("Reports", tempReports));

		tempReports.addItem("Report 1", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report1());
			}
		});

		tempReports.addItem("Report 2", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report2());
			}
		});

		tempReports.addItem("Report 3", new Command() {
			@Override
			public void execute() {
				setzeInhalt(new Report3());
			}
		});

		RootPanel.get("header").add(HauptMenue);

	}

	public static void setzeInhalt(VerticalPanel ZuSetzendesPanel) {

		RootPanel.get("content_left").clear();
		RootPanel.get("content_right").clear();
		RootPanel.get("content").clear();
		RootPanel.get("content").add(ZuSetzendesPanel);
	}

}