package de.hdm.tellme.client;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.core.java.util.Arrays;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nachricht;

public class NachrichteUebersicht extends VerticalPanel {

	//Panels erstellen 
	private HorizontalPanel uebersicht1Panel = new HorizontalPanel(); 
	private VerticalPanel uebersicht2Panel = new VerticalPanel(); 
	
	
	
	//RPC Methode, die auf Client in einer bestimmten Runtime ausgeführt wird um Daten mit dem Server auszutauschen
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
		
	
	//�berschrift und Beschreibung einf�gen
	private Label nachrichtenUebersicht = new Label("Nachrichten�bersicht"); 
	private Label beschreibung = new Label("Hier kannst du deine �ffentlichen und privaten"
			+ "Nachrichten sehen. Durch einen Klick auf eine Unterhaltung oder eine Nachricht im linken Feld "
			+ "�ffnen sich die einzelnen Nachrichten im rechten Feld. Durch Anklicken des Stifts kann eine "
			+ "Nachricht bearbeitet werden. Durch Anklicken des x kann im rechten Feld eine einzelen Nachricht "
			+ "gel�scht werden. Beim Klicken des x im linken Feld werden Kommunikationsverl�ufe gel�scht.");
	

	 private static final Nachricht<String> Nachrichten = Arrays.asList(Nachricht);

	 
	//Jedes GWT Widget implementiert diese Methode, welche zeigt was passiert wenn eine Widget-Instanz zur Anzeige gebracht wird.
	 	public void onLoad(){
	 		
	 		
		    // Erstellt eine Celllist, die alle Nachrichten enth�lt
		    CellList<String> nachrichtenListe = new CellList<String>(null);
		    nachrichtenListe.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		    
		    // Das Objekt wird ausgew�hlt
		    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
		    nachrichtenListe.setSelectionModel(selectionModel);
		    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		      public void onSelectionChange(SelectionChangeEvent event) {
		        String ausgewaehlt = selectionModel.getSelectedObject();
		        if (ausgewaehlt != null) {
		          Window.alert("Sie haben " + ausgewaehlt);
		        }
		      }
		    });

		
		    // Die Daten werden dem Widget hinzugef�gt
		    nachrichtenListe.setRowData(0, nachrichtenListe);

		  
	
 		
 		
 	//Buttons und Labels werden den Panels zugeordnet 
	uebersicht1Panel.add(uebersicht2Panel);
 	uebersicht1Panel.add(nachrichtenUebersicht);
	uebersicht1Panel.add(beschreibung);
	uebersicht1Panel.add(nachrichtenListe);
	
	
	//Panels werden dem RootPanel zugeordnet 
	RootPanel.get("content").clear();
	RootPanel.get("content").add(uebersicht1Panel);
	RootPanel.get("content").add(uebersicht2Panel);


	nachrichtenUebersicht.addStyleName("nachrichtenUebersicht"); 
	beschreibung.addStyleName("beschreibung"); 
	
 	}

}