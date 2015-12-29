package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.tellme.shared.LoginInfo;

/**
 * 
 * @author Zimmermann
 * @version 1.0
 * @since 26.11.2015
 * 
 * Die Klasse MenuBarReport implementiert den EntryPoint für das Modul "Report" der Applikation.
 * Hier wird sowohl der Login, als auch das Menü im HTML-div "head_wrap" initialisiert.
 */


public class MenuBarReport extends HorizontalPanel{
	
	private HorizontalPanel menuePanel = new HorizontalPanel();
	/**
	 * Definition von leerem Konstruktor
	 */
	public MenuBarReport(){
		
	}
	
	/**
	 * An dieser Stelle wird das LoginInfo von der Methode setLoginInfo(), übergeben und gesetzt. 
	 */
	public void setLoginInfo(LoginInfo loginInfo){
	}
	
	 /**
	   * Bei erfolgreichem Login wird folgende Methode geladen. 
	   * Dem Benutzer wird das Menü angezeigt.
	   */
	public void onLoad() {
		
		/**
		 * An dieser Stelle wird ein Image-Objekt instanziiert. 
		 * Anschließend wird ein String, in dem die URL der Logo-Bilddatei gespeichert wird, definiert. 
		 * Nun wird der String (url) dem instanziiert Image-Objekt zugewiesen.
		 */
	    Image logo = new Image();
		final String url = "http://127.0.0.1:8888/logo.png";
		logo.setUrl(url);
 
		
		/**
		 * Es werden drei MenuBar mit dem new-Operator instanziert und mit 'true' sichtbar geschaltet. 
		 * Folgend wird jeweils ein Neues Item der Menubar hinzugefügt: MeinProfil, Aboverwaltung und Nachrichten.
		 * Mit der 'public void execute()'-Methode wird definiert was bei Klick darauf passieren soll. 
		 * In unserem Fall laden, leeren wir erst den Cotent-Bereich mit dem Befehl RootPanel.get("content").clear();
		 * Im Anschluss werden wir die GUI-Klassen dem 'RootPanel.get("content")' -Bereich zuordnen. 
		 */
		MenuBar report1 = new MenuBar(true);
		MenuBar report2 = new MenuBar(true);
		MenuBar report3 = new MenuBar(true);
 		 
		 /**
		   * Die folgenden "Commands" definieren was beim jeweiligen Aufruf der Menü-Punkte passieren soll.
		   */
		report1.addItem("Report 1", new Command(){
			@Override 
			public void execute() {
				 /*TODO*/
			}
		});
		
		report2.addItem("Aboverwaltung", new Command(){
			@Override 
			public void execute() {
				 /*TODO*/
			}
		});
		
		report3.addItem("Nachrichten", new Command(){
			@Override 
			public void execute() {
				 /*TODO*/
			}
		});
	 
		/**
		 * Als nächstes Weisen wir das Image-Objekt: Logo und die einzelnen MenuBars dem HorizontalPanel und als letztes dem
		 * Rootpanel mit 'RootPanel.get("header").add(menuePanel)' zu.
		 */
		menuePanel.add(logo);
		menuePanel.add(report1);
		menuePanel.add(report2);
		menuePanel.add(report3);

		RootPanel.get("header").add(menuePanel);
		
	}
}
