package de.hdm.tellme.client;


import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;

/*
 * @author: Alex Homann
 * Version: 1.0
 * nicht fertiggestellt
 * 
 */
 
public class AboverwaltungEditor {
	
	private VerticalPanel aboPanel = new VerticalPanel();//Panel wird erstellt
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);//RPC Methode, die auf Client in einer bestimmten Runtime ausgeführt zur Transaktion von Daten mit dem Server
	private Label ueberschrift1 = new Label("Aboverwaltung");//Überschrift eingefügt
	private Label untertitel = new Label(
			"Hier kannst du deine Abonnements verwalten.");//Beschreibung eingefügt
	private Label ueberschrift2 = new Label("Nutzerabo");
	
	//Einfügen der Dropdown-Liste um einen Nutzer auszuwählen, den man abonnieren oder nichtmehr abonnieren möchte.
	private Label text= new Label("Nutzer auswählen: ");
	final ListBox dropBoxNutzer = new ListBox(false);
	
	
	Vector<Nutzer>  NutzerListe = new Vector<Nutzer>;
	
	
	N = Nutzer.getVorname() + Nutzer.getNachname();//FEHLT API zur Implementierung dieser DropDownListe
	for (int i= 0; i < Nutzer.length; i++){
		dropBoxNutzer.addItem(Vector[i]);
	}
	/*
	 * Die Buttons zum Erstellen der Nutzerabos werden erstellt
	 */
	private HorizontalPanel ButtonPanelNutzer = new HorizontalPanel();
	private Button nutzerAboErstellenButton = new Button ("Abo erstellen");
	private Button nutzerAboLöschenButton = new Button ("Abo löschen");
	
	private Label ueberschrift3 = new Label("Hashtagabo");
	
	//Einfügen der Dropdown-Liste um einen Hashtag auszuwählen, den man abonnieren oder nichtmehr abonnieren möchte.
	private Label text1= new Label("Hashtag auswählen: ");
	final ListBox dropBoxHashtag = new ListBox(false);
	
	
	Vector<Hashtag>  HashtagListe = new Vector<Hashtag>;
	
	
	N = Hashtag.getSchlagwort();
	for (int i= 0; i < Hashtag.length; i++){//vermutlich falsche umsetzung -> Prinzip stimmt aber
		dropBoxHashtag.addItem(Vector[i]);
	}
	/*
	 * Die Buttons zum Erstellen der Nutzerabos werden erstellt
	 */
	private HorizontalPanel ButtonPanelHashtag = new HorizontalPanel();
	private Button hashtagAboErstellenButton = new Button ("Abo erstellen");
	private Button hashtagAboLöschenButton = new Button ("Abo löschen");
	
	
	
	

}
}
