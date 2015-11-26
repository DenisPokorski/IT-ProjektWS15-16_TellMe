package de.hdm.tellme.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.tellme.shared.LoginInfo;

/**
 * Klasse mit Eigenschaften und Diensten, die für alle Client-seitigen Klassen
 * relevant sind.
 * 
 * @author Zimmermann
 * @version 1.0
 * @since 26.11.2015
 * 
 */

public class MenuBarEditor extends HorizontalPanel{
	
	private HorizontalPanel menuePanel = new HorizontalPanel();
	private LoginInfo loginInfo;
	
	/**
	 * Definition von leerem Konstruktor
	 */
	public MenuBarEditor(){
		
	}
	
	/**
	 * An dieser Stelle wird das LoginInfo von der Methode setLoginInfo(), übergeben und gesetzt. 
	 */
	public void setLoginInfo(LoginInfo loginInfo){
		this.loginInfo = loginInfo;
	}
	
	
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
		MenuBar meinProfil = new MenuBar(true);
		MenuBar nachrichten = new MenuBar(true);
		MenuBar aboVerwaltung = new MenuBar(true);
 		 
		
		meinProfil.addItem("Mein Profil", new Command(){
			@Override 
			public void execute() {
				RootPanel.get("content").clear();
				NutzerBearbeiten nB = new NutzerBearbeiten();
				nB.setLoginInfo(loginInfo);
				RootPanel.get("content").add(nB); //Leiste wird oben gesetzt
			}
		});
		
		aboVerwaltung.addItem("Aboverwaltung", new Command(){
			@Override 
			public void execute() {
				RootPanel.get("content").clear();
			}
		});
		
		nachrichten.addItem("Nachrichten", new Command(){
			@Override 
			public void execute() {
				RootPanel.get("content").clear();
			}
		});
	 
		/**
		 * Als nächstes Weisen wir das Image-Objekt: Logo und die einzelnen MenuBars dem HorizontalPanel und als letztes dem
		 * Rootpanel mit 'RootPanel.get("header").add(menuePanel)' zu.
		 */
		menuePanel.add(logo);
		menuePanel.add(meinProfil);
		menuePanel.add(aboVerwaltung);
		menuePanel.add(nachrichten);

		RootPanel.get("header").add(menuePanel);
		
	}
}
