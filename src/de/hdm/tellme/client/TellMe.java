package de.hdm.tellme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import de.hdm.tellme.shared.LoginInfo;

public class TellMe implements EntryPoint {
	private LoginInfo loginInfo = null;
	public void ladeMenuBarEditor(){
		 
		//Startseite anzeigen
		MenuBarEditor menuBar = new MenuBarEditor();
		menuBar.setLoginInfo(loginInfo);
		RootPanel.get("header").clear();
		RootPanel.get("header").add(menuBar);
	}

	@Override
	public void onModuleLoad() {
		ladeMenuBarEditor();
		//ladeNachrichten();
		
	}
}
