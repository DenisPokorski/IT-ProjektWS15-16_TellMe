package Archiv;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.LoginInfo;

/*
 * @author: DenisPokorski
 * datum: 6.12.15 
 * version: 1.2
 */

public class NachrichtenSenden extends VerticalPanel {
	
	public void setLoginInfo(LoginInfo loginInfo) {
		 }
	
	//Panels werden erstellt
	private VerticalPanel mainPanel = new VerticalPanel();
		//Überschrift und Beschreibung einfügen
		private Label ueberschrift1 = new Label("Nachrichtenverwaltung");
		private Label untertitel = new Label(
				"Hier kannst du deine Nachrichten eingeben, verändern und löschen. Wenn du keinen Empfänger auswählst, dann wird deine Nachricht öffentlich gepostet");
		//Horizontal Panel zum Auswählen der Empfänger 
			private HorizontalPanel empfaengerPanel = new HorizontalPanel();
				//Label für Empfänger auswählen wird erstellt
				private Label empfaengerAuswaehlen = new Label("Empfänger auswählen:");
				//DropDownListe zum Auswählen der Empfänger
				//Vorerst nur ein Empfänger, hier müssen wir noch schauen wie wir es mit mehreren Nutzern machen
				private ListBox dropDownNutzer = new ListBox();
				//Button zum Hinzufügen der Nutzer
				private Button nutzerHinzufuegenButton = new Button("Nutzer hinzufügen");
			//VerticalPanel für die Textbox 
			private VerticalPanel textBoxPanel = new VerticalPanel();
				//TextBox wird erstellt, in dem Nachricht eingetragen wird 
				private TextBox nachrichtErstellen = new TextBox();
			//HorizontalPanel zum Hinzufügen von Hashtags wird erstellt
			private HorizontalPanel hashtagPanel = new HorizontalPanel();
				//Label zum Einfügen von Hashtags
				private Label hashtagHinzufuegen = new Label("Hashtag hinzufügen: ");
				//DropDownListe zum Auswählen von Hashtag
				private ListBox dropDownHashtag = new ListBox();
				//Button zum Hinzufügen des Hashtags
				private Button hashtagHinzufuegenButton = new Button("Hashtag hinzufügen");
			//HorizontalPanel zum Senden und zum Abbrechen des Sendens
			private HorizontalPanel buttonPanel = new HorizontalPanel();
				//Button zum Senden
				private Button sendenButton = new Button("Senden");
				//Button zum Abbrechen --> Alles wir geleert, Seite bleibt aber offen
				private Button abbrechenButton = new Button("Abbrechen");
				
	//Jedes GWT Widget implementiert diese Methode, welche zeigt was passiert wenn eine Widget-Instanz zur Anzeige gebracht wird.
 	public void onLoad(){
 		
 		
 		//Buttons und Labels werden den Panels zugeordnet 
 		mainPanel.add(ueberschrift1);
 		mainPanel.add(untertitel);
 		mainPanel.add(empfaengerPanel);
 			empfaengerPanel.add(empfaengerAuswaehlen);
 			empfaengerPanel.add(dropDownNutzer);
 			empfaengerPanel.add(nutzerHinzufuegenButton);
 		mainPanel.add(textBoxPanel);
 			textBoxPanel.add(nachrichtErstellen);
 		mainPanel.add(hashtagPanel);
 			hashtagPanel.add(hashtagHinzufuegen);
 			hashtagPanel.add(dropDownHashtag);
 			hashtagPanel.add(hashtagHinzufuegenButton);
 		mainPanel.add(buttonPanel);
 			buttonPanel.add(sendenButton);
 			buttonPanel.add(abbrechenButton);
 			
 			
 			
	
		
		//Panels werden dem RootPanel zugeordnet 
		RootPanel.get("content").clear();
		RootPanel.get("content").add(mainPanel);
		RootPanel.get("content").add(empfaengerPanel);
		RootPanel.get("content").add(textBoxPanel);
		RootPanel.get("content").add(hashtagPanel);
		RootPanel.get("content").add(buttonPanel);
		
	
		ueberschrift1.addStyleName("ueberschrift1");
		untertitel.addStyleName("untertitel");
		empfaengerAuswaehlen.addStyleName("empfaengerAuswählen");
		dropDownNutzer.addStyleName("dropDownNutzer");
		nutzerHinzufuegenButton.addStyleName("nutzerHinzufuegenButton");
		nachrichtErstellen.addStyleName("nachrichtErstellen");
		hashtagHinzufuegen.addStyleName("hashtagHinzufuegen");
		dropDownHashtag.addStyleName("dropDownHashtag");
		hashtagHinzufuegenButton.addStyleName("hashtagHinzufuegenButton");
		sendenButton.addStyleName("sendenButton");
		abbrechenButton.addStyleName("abbrechenButton");

	}
	
 	

}
