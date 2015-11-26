package de.hdm.tellme.client;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;

public class NachrichtenSenden extends VerticalPanel {
	
	
	//Panels werden erstellt
	private VerticalPanel nachrichtenPanel = new VerticalPanel();
	private HorizontalPanel empfaengerPanel = new HorizontalPanel();
	
	
	//RPC Methode, die auf Client in einer bestimmten Runtime ausgefÃ¼hrt wird um Daten mit dem Server auszutauschen
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
	
	
	//Überschrift und Beschreibung einfügen
	private Label ueberschrift1 = new Label("Nachrichtenverwaltung");
	private Label untertitel = new Label(
			"Hier kannst du deine Nachrichten eingeben, verändern und löschen.");
	
	
	//Label für Empfänger auswählen wird erstellt
	private Label empfaengerAuswaehlen = new Label("Empfänger auswählen");
	
	
	//Dropdown Liste einfügen, in der man den Empfänger auswählen kann
	private Label nachrichtenText= new Label("Nutzer auswählen: ");
	final ListBox dropBoxNutzer = new ListBox(false);
	/*
	Vector<Nutzer>  NutzerListe = new Vector<Nutzer>;
	N = Nutzer.getVorname() + Nutzer.getNachname();
	for (int i= 0; i < Nutzer.length; i++){
		dropBoxNutzer.addItem(Vector[i]);
	}*/
	
	
	//TextBox wird erstellt, in dem Nachricht eingetragen wird 
	private TextBox nachrichtErstellen = new TextBox();
	
		
	//Label für Hashtag wird erstellt 
	private Label hashtagHinzufuegen = new Label("Hashtag hinzufügen");
	
	
	//Drop Down Liste, um ein Hashtag auszuwählen
	final ListBox dropBoxHashtag = new ListBox(false);
	/*Vector<Hashtag>  HashtagListe = new Vector<Hashtag>;
	N = Hashtag.getSchlagwort();
	for (int i= 0; i < Hashtag.length; i++){
		dropBoxHashtag.addItem(Vector[i]);
	*/
	
	
	//Button wird erstellt, um Änderungen zu speichern 
	private Button nachrichtSenden = new Button("Nachricht senden"); 
	
	
	//Jedes GWT Widget implementiert diese Methode, welche zeigt was passiert wenn eine Widget-Instanz zur Anzeige gebracht wird.
 	public void onLoad(){
 		
 		
 		//Buttons und Labels werden den Panels zugeordnet 
 		nachrichtenPanel.add(empfaengerPanel);
 		nachrichtenPanel.add(ueberschrift1);
 		nachrichtenPanel.add(untertitel);
 		empfaengerPanel.add(empfaengerAuswaehlen);
 		empfaengerPanel.add(nachrichtenText);
 		empfaengerPanel.add(hashtagHinzufuegen);
 		nachrichtenPanel.add(nachrichtErstellen);
		nachrichtenPanel.add(nachrichtSenden);
	
		
		//Panels werden dem RootPanel zugeordnet 
		RootPanel.get("content").clear();
		RootPanel.get("content").add(nachrichtenPanel);
		RootPanel.get("content").add(empfaengerPanel);
		
	
		ueberschrift1.addStyleName("ueberschrift1");
		untertitel.addStyleName("untertitel");
		empfaengerAuswaehlen.addStyleName("empfaengerAuswählen");
		nachrichtenText.addStyleName("nachrichtenText");
		hashtagHinzufuegen.addStyleName("hashtagHinzufuegen");
		nachrichtErstellen.addStyleName("nachrichtErstellen");
		nachrichtSenden.addStyleName("nachrichtSenden"); 

	}
	
 	

}
