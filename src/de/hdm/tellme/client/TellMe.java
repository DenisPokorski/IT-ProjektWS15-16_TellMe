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
	private Label untertitel = new Label("todo");
	private Label ueberschrift2 = new Label("Profil bearbeiten");
	
	//Kommentar nachtragen(TODO)
	private TextBox vornameTextBox = new TextBox(); 
	private TextBox nachnameTextBox = new TextBox(); 
	private TextBox emailTextBox = new TextBox(); 

	//Kommentar nachtragen(TODO)
	private Button aenderungenSpeichernButton = new Button();
	private Button profilAbmeldenButton = new Button();
	private Button profilloeschenButton = new Button();




	public void onModuleLoad() {
		
		//Kommentar nachtragen(TODO)
		profilPanel.add(ueberschrift1);
		profilPanel.add(untertitel);
		profilPanel.add(ueberschrift2);
		
		profilPanel.add(vornameTextBox);
		profilPanel.add(emailTextBox);
		profilPanel.add(aenderungenSpeichernButton);
		profilPanel.add(profilAbmeldenButton);
		profilPanel.add(profilloeschenButton);

		//Kommentar nachtragen(TODO)
		ueberschrift1.addStyleName("ueberschrift1"); 
		ueberschrift1.addStyleName("untertitel"); 
		ueberschrift1.addStyleName("ueberschrift2"); 
		ueberschrift1.addStyleName("vornameTextBox"); 
		ueberschrift1.addStyleName("emailTextBox"); 
		ueberschrift1.addStyleName("aenderungenSpeichernButton"); 
		ueberschrift1.addStyleName("profilAbmeldenButton"); 
		ueberschrift1.addStyleName("profilloeschenButton"); 

		
		//Kommentar nachtragen(TODO)
		RootPanel.get("content").add(profilPanel);
		

	}
}
