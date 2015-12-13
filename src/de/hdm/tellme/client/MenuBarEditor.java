package de.hdm.tellme.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.tellme.client.gui.report.Report1;
import de.hdm.tellme.client.gui.report.Report2;
import de.hdm.tellme.client.gui.report.Report3;
import de.hdm.tellme.shared.LoginInfo;

/**
 * 
 * @author Zimmermann
 * @version 1.1
 * @since 26.11.2015
 * 
 */

public class MenuBarEditor extends HorizontalPanel {

	private LoginInfo loginInfo;

	/**
	 * Definition von leerem Konstruktor
	 */
	public MenuBarEditor() {

	}

	/**
	 * An dieser Stelle wird das LoginInfo von der Methode setLoginInfo(),
	 * übergeben und gesetzt.
	 */
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public void onLoad() {
		/**
		 * Es werden 6 MenuBar mit dem new-Operator instanziert und mit 'true'
		 * sichtbar geschaltet. Folgend wird jeweils ein Neues Item der Menubar
		 * hinzugefügt: MeinProfil, Aboverwaltung, Nachrichten und Report 1-3.
		 * Mit der 'public void execute()'-Methode wird definiert was bei Klick
		 * darauf passieren soll. In unserem Fall laden, leeren wir erst den
		 * Cotent-Bereich mit dem Befehl RootPanel.get("content").clear(); Im
		 * Anschluss werden wir die GUI-Klassen dem 'RootPanel.get("content")'
		 * -Bereich zuordnen.
		 */
		MenuBar HauptMenue = new MenuBar(false);
		HauptMenue.setAutoOpen(true);
		HauptMenue.setAnimationEnabled(true);
		

		final String LogoLink = "<img src='/logo.png'/>";
		HauptMenue.addItem(new MenuItem(LogoLink,true,new Command() {
			@Override
			public void execute() {
				//Nichts laden wenn Benutzer Logo auswählt
			}
		}));
		
		HauptMenue.addItem("Unterhaltungen", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				NachrichtenverwaltungEditor nvE = new NachrichtenverwaltungEditor();
				// nvE.setLoginInfo(loginInfo);
				RootPanel.get("content").add(nvE); // Leiste wird oben gesetzt
			}
		});

		HauptMenue.addItem("Neuigkeiten", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				// Wird später hinzugefügt
				// RootPanel.get("content").add(nvE); // Leiste wird oben
				// gesetzt
			}
		});

		MenuBar EinstellungenMenu = new MenuBar(true);
		EinstellungenMenu.setAnimationEnabled(true);
		HauptMenue.addItem(new MenuItem("Einstellungen", EinstellungenMenu));

		EinstellungenMenu.addItem("Aboverwaltung", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				AboverwaltungEditor aE = new AboverwaltungEditor();
				aE.setLoginInfo(loginInfo);
				RootPanel.get("content").add(aE); // Leiste wird oben gesetzt
			}
		});

		EinstellungenMenu.addItem("Benutzereinstellungen", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				NutzerBearbeiten nB = new NutzerBearbeiten();
				nB.setLoginInfo(loginInfo);
				RootPanel.get("content").add(nB); // Leiste wird oben gesetzt
			}
		});

		EinstellungenMenu.addItem("Hashtagverwaltung(?)", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
//				HashtagAbo hA = HashtagAbo();
//				hA.setLoginInfo(loginInfo);
//				RootPanel.get("content").add(hA); // Leiste wird oben gesetzt
			}
		});

		
		
		//TEMPORÄRES MENÜ

		MenuBar tempReports = new MenuBar(true);
		tempReports.setAnimationEnabled(true);
		HauptMenue.addItem(new MenuItem("Reports", tempReports));

		tempReports.addItem("Report 1", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				Report1 r1 = new Report1();
				r1.setLoginInfo(loginInfo);
				RootPanel.get("content").add(r1);
			}
		});

		tempReports.addItem("Report 2", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				Report2 r2 = new Report2();
				r2.setLoginInfo(loginInfo);
				RootPanel.get("content").add(r2);
			}
		});

		tempReports.addItem("Report 3", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				Report3 r3 = new Report3();
				r3.setLoginInfo(loginInfo);
				RootPanel.get("content").add(r3);
			}
		});
		
		
		RootPanel.get("header").add(HauptMenue);

	}
}