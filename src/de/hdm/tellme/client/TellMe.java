package de.hdm.tellme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TellMe implements EntryPoint {
	
	//Kommentar nachtragen(TODO)
	private VerticalPanel profilPanel = new VerticalPanel(); 
	 
	private Label ueberschrift1 = new Label("Mein Profil");
	private Label untertitel = new Label("Hier siehst du dein Profil und kannst es bearbeiten, löschen und dich abmelden.");
	private Label ueberschrift2 = new Label("Profil bearbeiten");
	
	//Kommentar nachtragen(TODO)
	private Label bezeichnungVornameTextBox = new Label("Vorname"); 
	private TextBox vornameTextBox = new TextBox(); 
	private Label bezeichnungNachnameTextBox = new Label("Nachname"); 
	private TextBox nachnameTextBox = new TextBox(); 
	private Label bezeichnungEmailTextBox = new Label("Emailadresse"); 
	private TextBox emailTextBox = new TextBox(); 

	//Kommentar nachtragen(TODO)
	private Button aenderungenSpeichernButton = new Button("Änderungen speichern");
	private Button profilAbmeldenButton = new Button("Abmelden");
	private Button profilloeschenButton = new Button("Profil löschen");




	public void onModuleLoad() {
		
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
		
		profilPanel.add(aenderungenSpeichernButton);
		profilPanel.add(profilAbmeldenButton);
		profilPanel.add(profilloeschenButton);

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
		RootPanel.get("content").add(profilPanel);
		

	}
}
