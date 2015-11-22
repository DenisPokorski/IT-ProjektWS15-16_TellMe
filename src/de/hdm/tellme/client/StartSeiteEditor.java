package de.hdm.tellme.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.LoginInfo;

public class StartSeiteEditor extends VerticalPanel{
	
	private VerticalPanel menuePanel = new VerticalPanel();
	
	private LoginInfo loginInfo;
	
	public void StartSeiteEditor(){
		
	}
	
	
	//LoginInformationen werden gesetzt 
	public void setLoginInfo(LoginInfo loginInfo){
		this.loginInfo = loginInfo;
		
	}
	
	
	// Beim Laden passiert folgendes:
	
	public void onLoad() {
		
		//MenueBar wird gestartet (GWT Klasse - MenuBar schon vorgegeben mit genutzten Methoden)
		
		MenuBar menu = new MenuBar(true);
		menu.setAutoOpen(true);
		menu.setWidth("200px");
		menu.setAnimationEnabled(true);
		
		//MenuePunkte werden angelegt:
		
		MenuBar meinProfil = new MenuBar(true);
		MenuBar nachrichten = new MenuBar(true);
		MenuBar aboVerwaltung = new MenuBar(true);
		MenuBar abmelden = new MenuBar(true);
		
		//Erstellen der unterschiedlichen Befehle
		
		//Erstellen für mein Profil
		
		menu.addItem("Mein Profil", new Command(){
			@Override 
			public void execute() {
				
				RootPanel.get("content").clear();
				NutzerBearbeiten nB = new NutzerBearbeiten();
				nB.setLoginInfo(loginInfo);
				RootPanel.get("content").add(nB); //Leiste wird oben gesetzt
				
			}
		});
		menuePanel.add(menu);
		RootPanel.get("header").add(menuePanel);
		
	}

	
}
