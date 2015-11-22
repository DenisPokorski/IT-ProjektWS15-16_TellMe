package de.hdm.tellme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.LoginInfo;


public class TellMe implements EntryPoint {
	private LoginInfo loginInfo = null;
	public void ladeStartseite(){
		 
		//Startseite anzeigen
		StartSeiteEditor Startseite = new StartSeiteEditor();
		Startseite.setLoginInfo(loginInfo);
		RootPanel.get("header").clear();
		RootPanel.get("header").add(Startseite);
	}

	@Override
	public void onModuleLoad() {
		ladeStartseite();
		
	}
}
