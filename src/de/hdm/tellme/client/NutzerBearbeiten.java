package de.hdm.tellme.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.LoginInfo;

public class NutzerBearbeiten extends VerticalPanel {
	
	public NutzerBearbeiten(){
		
	}
	
	//Kommentar nachtragen(TODO)
	private VerticalPanel profilPanel = new VerticalPanel(); 
	 
	private Label ueberschrift1 = new Label("Mein Profil");
	private Label untertitel = new Label("Hier siehst du dein Profil und kannst es bearbeiten, l�schen und dich abmelden.");
	private Label ueberschrift2 = new Label("Profil bearbeiten");
	
	private LoginInfo loginInfo;
	
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}




	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	//Kommentar nachtragen(TODO)
	private Label bezeichnungVornameTextBox = new Label("Vorname"); 
	private TextBox vornameTextBox = new TextBox(); 
	private Label bezeichnungNachnameTextBox = new Label("Nachname"); 
	private TextBox nachnameTextBox = new TextBox(); 
	private Label bezeichnungEmailTextBox = new Label("Emailadresse"); 
	private TextBox emailTextBox = new TextBox(); 

	//Kommentar nachtragen(TODO)
	private HorizontalPanel ButtonPanel = new HorizontalPanel();
	private Button aenderungenSpeichernButton = new Button("�nderungen speichern");
	private Button profilAbmeldenButton = new Button("Abmelden");
	private Button profilloeschenButton = new Button("Profil l�schen");




	public void onLoad() {
		
		//Kommentar nachtragen(TODO)
		profilPanel.add(ueberschrift1);
		profilPanel.add(untertitel);
		profilPanel.add(ueberschrift2);
		
		profilPanel.add(bezeichnungVornameTextBox);
		profilPanel.add(vornameTextBox);
		profilPanel.add(bezeichnungNachnameTextBox);
		profilPanel.add(nachnameTextBox);
		profilPanel.add(bezeichnungEmailTextBox);
		profilPanel.add(emailTextBox);
		
		ButtonPanel.add(aenderungenSpeichernButton);
		ButtonPanel.add(profilAbmeldenButton);
		ButtonPanel.add(profilloeschenButton);

		//Kommentar nachtragen(TODO)
		ueberschrift1.addStyleName("ueberschrift1"); 
		untertitel.addStyleName("untertitel"); 
		ueberschrift2.addStyleName("ueberschrift2"); 
		
		bezeichnungVornameTextBox.addStyleName("bezeichnungVornameTextBox");
		vornameTextBox.addStyleName("vornameTextBox");
		bezeichnungNachnameTextBox.addStyleName("bezeichnungNachnameTextBox");
		nachnameTextBox.addStyleName("nachnameTextBox");
		bezeichnungEmailTextBox.addStyleName("bezeichnungEmailTextBox");
		emailTextBox.addStyleName("emailTextBox"); 
		
		aenderungenSpeichernButton.addStyleName("aenderungenSpeichernButton"); 
		profilAbmeldenButton.addStyleName("profilAbmeldenButton"); 
		profilloeschenButton.addStyleName("profilloeschenButton"); 

		
		
		
		//Kommentar nachtragen(TODO)
		RootPanel.get("content").clear();
		RootPanel.get("content").add(profilPanel);
		RootPanel.get("content").add(ButtonPanel);
		

	}

}

