package de.hdm.tellme.client;
/**
 *Klasse mit Übersicht aller Nachrichten bzw. Unterhaltungen. 
 * 
 * @author thuering
 * @version 1.0
 * @since 26.11.2015
 * 
 */
import java.util.ArrayList;

/*
 * Diese KLasse stellt die Übersicht aller Nachrichten bzw. Unterhaltungen dar. 
 * @author: Dana Thüring 
 * @version: 1.0
 * 
 * 
 */




import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;


public class NachrichteUebersicht extends VerticalPanel {

	//Panels erstellen 
	private HorizontalPanel uebersicht1Panel = new HorizontalPanel(); 
	private VerticalPanel uebersicht2Panel = new VerticalPanel(); 
	
	
	
	//RPC Methode, die auf Client in einer bestimmten Runtime ausgefÃ¼hrt wird um Daten mit dem Server auszutauschen
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
		
	
	//Überschrift und Beschreibung einfügen
	private Label nachrichtenUebersicht = new Label("Nachrichtenübersicht"); 
	private Label beschreibung = new Label("Hier kannst du deine öffentlichen und privaten"
			+ "Nachrichten sehen. Durch einen Klick auf eine Unterhaltung oder eine Nachricht im linken Feld "
			+ "öffnen sich die einzelnen Nachrichten im rechten Feld. Durch Anklicken des Stifts kann eine "
			+ "Nachricht bearbeitet werden. Durch Anklicken des x kann im rechten Feld eine einzelen Nachricht "
			+ "gelöscht werden. Beim Klicken des x im linken Feld werden Kommunikationsverläufe gelöscht.");
	private Button neueNachricht = new Button("Neue Nachricht erstellen"); 
	private Button nachrichtAuswaehlen = new Button();
	
	 //Es wird eine Zelle  erstellt, um die Nachrichten darzustellen 
	  TextCell textCell = new TextCell();
	  
	  
	//die liste der Nachrichten, um sie anzuzeigen
	//warum final?
	 private static final ArrayList<String> Nachrichten = new ArrayList<String>();

	 
	//Jedes GWT Widget implementiert diese Methode, welche zeigt was passiert wenn eine Widget-Instanz zur Anzeige gebracht wird.
	 	public void onLoad(){
	 		
	 		
	 		

	 			    // Es wird eine cellliste erstellt
	 			    CellList<String> nachrichtenListe = new CellList<String>(textCell);
	 			    nachrichtenListe.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

	 			    // Selection modeler wird hinzugefügt, um die Auswahl zu benutzen
	 			    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
	 			    nachrichtenListe.setSelectionModel(selectionModel);
	 			    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	 			    
	 			    public void onSelectionChange(SelectionChangeEvent event) {
	 			        String selected = selectionModel.getSelectedObject();
	 			        if (selected != null) {
	 			        	//Hier kommt die Methode rein, die dafür sorgt, dass die Details  der Unterhaltung angezeeigt wird 
	 			          
	 			        }
	 			      }
	 			    });
	 			    // Die Daten werden ins Wdget gepusht 
	 			    nachrichtenListe.setRowData(0, Nachrichten);

	 			   

	 			
		
	 				
 	//Buttons und Labels werden den Panels zugeordnet 
	uebersicht1Panel.add(uebersicht2Panel);
 	uebersicht1Panel.add(nachrichtenUebersicht);
 	uebersicht1Panel.add(neueNachricht); 
	uebersicht1Panel.add(beschreibung);
	uebersicht1Panel.add(nachrichtenListe);
	
	
	//Panels werden dem RootPanel zugeordnet 
	RootPanel.get("content").clear();
	RootPanel.get("content").add(uebersicht1Panel);
	RootPanel.get("content").add(uebersicht2Panel);
	RootPanel.get("content").add(nachrichtenListe);


	nachrichtenUebersicht.addStyleName("nachrichtenUebersicht"); 
	beschreibung.addStyleName("beschreibung"); 
	 	}
	
 	}
	 	


