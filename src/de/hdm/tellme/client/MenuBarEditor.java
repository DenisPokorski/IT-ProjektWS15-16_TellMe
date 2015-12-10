package de.hdm.tellme.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
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

	private HorizontalPanel menuePanel = new HorizontalPanel();
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
		 * Menubar hinzugefügt: MeinProfil, Aboverwaltung, Nachrichten und Report 1-3. Mit
		 * der 'public void execute()'-Methode wird definiert was bei Klick
		 * darauf passieren soll. In unserem Fall laden, leeren wir erst den
		 * Cotent-Bereich mit dem Befehl RootPanel.get("content").clear(); Im
		 * Anschluss werden wir die GUI-Klassen dem 'RootPanel.get("content")'
		 * -Bereich zuordnen.
		 */
		MenuBar meinProfil = new MenuBar(true);
		MenuBar nachrichten = new MenuBar(true);
		MenuBar aboVerwaltung = new MenuBar(true);
		MenuBar report1 = new MenuBar(true); //Nur vorläufig in Editor vorgesehen
		MenuBar report2 = new MenuBar(true); //Nur vorläufig in Editor vorgesehen
		MenuBar report3 = new MenuBar(true); //Nur vorläufig in Editor vorgesehen
		

		meinProfil.addItem("Mein Profil", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				NutzerBearbeiten nB = new NutzerBearbeiten();
				nB.setLoginInfo(loginInfo);
				RootPanel.get("content").add(nB); // Leiste wird oben gesetzt
			}
		});

		aboVerwaltung.addItem("Aboverwaltung", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				AboverwaltungEditor aBE = new AboverwaltungEditor();
				aBE.setLoginInfo(loginInfo);
				RootPanel.get("content").add(aBE); // Leiste wird oben gesetzt
			}
		});

		nachrichten.addItem("Nachrichten", new Command() {
			@Override
			public void execute() {
				RootPanel.get("content").clear();
				NachrichtenverwaltungEditor nvE = new NachrichtenverwaltungEditor();
				// nvE.setLoginInfo(loginInfo);
				RootPanel.get("content").add(nvE); // Leiste wird oben gesetzt
			}
		});
		
		report1.addItem("Report 1", new Command(){

			@Override
			public void execute() {
				RootPanel.get("content").clear();
				Report1 r1 = new Report1();
				r1.setLoginInfo(loginInfo);
				RootPanel.get("content").add(r1); 
				
				
			}
			
		});
		
		
		report2.addItem("Report 2", new Command(){

			@Override
			public void execute() {
				RootPanel.get("content").clear();
				Report2 r2 = new Report2();
				r2.setLoginInfo(loginInfo);
				RootPanel.get("content").add(r2); 
				
				
			}
			
		});
		
		
		report3.addItem("Report 3", new Command(){

			@Override
			public void execute() {
				RootPanel.get("content").clear();
				Report3 r3 = new Report3();
				r3.setLoginInfo(loginInfo);
				RootPanel.get("content").add(r3); 
				
				
			}
			
		});

		/**
		 * Als nächstes Weisen wir das Image-Objekt: Logo und die einzelnen
		 * MenuBars dem HorizontalPanel und als letztes dem Rootpanel mit
		 * 'RootPanel.get("header").add(menuePanel)' zu.
		 */
		menuePanel.add(logo);
		menuePanel.add(meinProfil);
		menuePanel.add(aboVerwaltung);
		menuePanel.add(nachrichten);
		menuePanel.add(report1);//nur temporär
		menuePanel.add(report2);//---""---
		menuePanel.add(report3);//---""----
		

		RootPanel.get("header").add(menuePanel);

	}
}