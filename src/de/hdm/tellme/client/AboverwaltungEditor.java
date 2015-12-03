package de.hdm.tellme.client;


import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.server.db.NutzerAbonnementMapper;
 
/*
 * Diese Klasse stellt die Aboverwaltung für den Editor dar.
 * @author: Alex Homann
 * @version: 1.1
 * nicht fertiggestellt
 * 
 */
 
public class AboverwaltungEditor extends VerticalPanel {
	
 	private LoginInfo loginInfo;

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	
	//Vertical Panel für den gesamten AboverwaltungsEditor
	
	private VerticalPanel aboPanel = new VerticalPanel();//Panel wird erstellt
		
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);//RPC Methode, die auf Client in einer bestimmten Runtime ausgeführt zur Transaktion von Daten mit dem Server
	private Label ueberschrift1 = new Label("Aboverwaltung");//Überschrift eingefügt
	
	private Label untertitel = new Label(
			"Hier kannst du deine Abonnements verwalten.");//Beschreibung eingefügt
	
	
		//Vertical Panel für NutzerAbo
		private HorizontalPanel nutzerAboPanel = new HorizontalPanel();//Panel für Nutzerabos
		
		private Label ueberschriftNutzerAbo = new Label("Nutzerabo");
		
				//Nutzerabo hinzufügen
				private VerticalPanel nutzerAboPanel1 = new VerticalPanel();//Panel wird erstellt
				
				private Label untertitelNutzerAbonnieren = new Label (
				"Hier kannst du die Nutzer auswählen, die du abonnieren möchtest");
				
				//Einfügen der Dropdown-Liste um einen Nutzer auszuwählen, den man auswählen möchte.
				private Label nutzerText1= new Label("Nutzer auswählen: ");
				
				private ListBox dropDownNutzerNichtAbonniert = new ListBox();
				
		/*		// DropDownListe mit Nutzern, die noch nicht abonniert sind
				  private void ladeAlleNichtAbonnierteNutzer() {
					  dropDownNutzerNichtAbonniert.addItem(" ");
					  
					int userId = get 
				    asyncObj.ladeAlleNichtAbonnierteNutzer(int userId, new AsyncCallback <Vector<Nutzer>>(){ //Brauch hier noch die Mapper-Klasse
				    	
				      @Override
				      public void onFailure(Throwable caught) {
				        // TODO Auto-generated method stub

				      }

				      @Override
				      public void onSuccess(Vector<Nutzer> result) {
				        for (int i = 0; i < result.size(); i++) {
				        	dropDownNutzerNichtAbonniert.addItem(" - " + result.get(i).getId() + " : "
				              + result.get(i).getVorname() 
				              +" "
				              + result.get(i).getNachname());
				        }
				      }
				    }
				    );
				    }
*/
				
				private Button nutzerAbonnieren = new Button("Nutzerabo hinzufügen");
				
				//Nutzerabo löschen
				private VerticalPanel nutzerAboPanel2 = new VerticalPanel();//Panel wird erstellt
				
				private Label untertitelNutzerAboLoeschen = new Label (
						"Hier kannst du die Nutzer auswählen, für die du dein Abo löschen möchtest");
				
				private Label nutzerText2= new Label("Nutzer auswählen: ");

				private ListBox dropDownNutzerAbonniert = new ListBox();
				/*
				
				Vector<Nutzer>  NutzerListe = new Vector<Nutzer>;
				
				
				N = Nutzer.getVorname() + Nutzer.getNachname();//FEHLT API zur Implementierung dieser DropDownListe
				for (int i= 0; i < Nutzer.length; i++){
					dropBoxNutzer.addItem(Vector[i]);
				}*/
			
				private Button nutzerNichtAbonnieren = new Button("Nutzerabo löschen");
		
		//Vertical Panel für HashtagAbo
		private HorizontalPanel hashtagAboPanel = new HorizontalPanel();//Panel für Hashtagabos
		
		private Label ueberschriftHashtagAbo = new Label("Hashtagabo");
				//Hashtagabo hinzufügen
				private VerticalPanel hashtagAboPanel1 = new VerticalPanel();//Panel wird erstellt
				
				private Label untertitelHashtagAbonnieren = new Label (
						"Hier kannst du die Hashtags auswählen, die du abonnieren möchtest");
				
				private Label hashtagText1= new Label("Hashtag auswählen: ");
				

				private ListBox dropDownHashtagsNichtAbonniert = new ListBox();
				/*
				
				Vector<Hashtag>  HashtagListe = new Vector<Hashtag>;
				
				
				Vector<Hashtag> = Hashtag.getSchlagwort();
				for (int i= 0; i < Hashtag.length; i++){//vermutlich falsche umsetzung -> Prinzip stimmt aber
					dropBoxHashtag.addItem(Vector[i]);
				}*/
				private Button hashtagAbonnieren = new Button("Hashtagabo hinzufügen");
				
				//Hashtagabo löschen
				private VerticalPanel hashtagAboPanel2 = new VerticalPanel();//Panel wird erstellt
				
				private Label untertitelHashtagAboLoeschen = new Label (
						"Hier kannst du die Hashtags auswählen, für die du dein Abo löschen möchtest");
				
				private Label hashtagText2= new Label("Hashtag auswählen: ");
				
				private ListBox dropDownHashtagsAbonniert = new ListBox();
/*
				
				Vector<Hashtag>  HashtagListe = new Vector<Hashtag>;
				
				
				Vector<Hashtag> = Hashtag.getSchlagwort();
				for (int i= 0; i < Hashtag.length; i++){//vermutlich falsche umsetzung -> Prinzip stimmt aber
					dropBoxHashtag.addItem(Vector[i]);
				}*/
				private Button hashtagNichtAbonnieren = new Button("Hashtagabo löschen");
				
					
	public void onLoad(){  //Aufbau der Seite
		
		/*
		 * Buttons und Labels werden den jeweiligen Panels zugeordnet.
		 */
		aboPanel.add(ueberschrift1);//Überschrift für AboPanel
			aboPanel.add(untertitel);//Untertitel für AboPanel
			aboPanel.add(ueberschriftNutzerAbo);
				aboPanel.add(nutzerAboPanel);
						//Nutzeraboverwaltung zuweisen
						
						aboPanel.add(ueberschriftHashtagAbo);
						//Nutzerabo hinzufügen
						nutzerAboPanel.add(nutzerAboPanel1);
						nutzerAboPanel1.add(untertitelNutzerAbonnieren);
						nutzerAboPanel1.add(nutzerText1);
						//Dropdownliste&Button 
						nutzerAboPanel1.add(dropDownNutzerNichtAbonniert);
						nutzerAboPanel1.add(nutzerAbonnieren);
						
						//Nutzerabo löschen
						nutzerAboPanel.add(nutzerAboPanel2);
						nutzerAboPanel2.add(untertitelNutzerAboLoeschen);
						nutzerAboPanel2.add(nutzerText2);
						//Dropdownliste&Button
						nutzerAboPanel2.add(dropDownNutzerAbonniert);
						nutzerAboPanel2.add(nutzerNichtAbonnieren);
						
				aboPanel.add(ueberschriftHashtagAbo);		
				aboPanel.add(hashtagAboPanel);
						//Hashtagaboverwaltung zuweisen
						
						//Hashtagabo hinzufügen
						hashtagAboPanel.add(hashtagAboPanel1);
						hashtagAboPanel1.add(untertitelHashtagAbonnieren);
						hashtagAboPanel1.add(hashtagText1);
						//Dropdownliste&Button 
						hashtagAboPanel1.add(dropDownHashtagsNichtAbonniert);
						hashtagAboPanel1.add(hashtagAbonnieren);
						
						//Hashtagabo löschen
						hashtagAboPanel.add(hashtagAboPanel2);
						hashtagAboPanel2.add(untertitelHashtagAboLoeschen);
						hashtagAboPanel2.add(hashtagText2);
						//Dropdownliste&Button
						hashtagAboPanel2.add(dropDownHashtagsAbonniert);
						hashtagAboPanel2.add(hashtagNichtAbonnieren);
						
						
		RootPanel.get("content").add(aboPanel);
		
		
		
		
	

}

}