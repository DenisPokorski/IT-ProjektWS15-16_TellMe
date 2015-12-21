package de.hdm.tellme.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sun.java.swing.plaf.windows.resources.windows;

import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nachricht;

public class NeuigkeitenEditor extends VerticalPanel {


	//RPC Methode, die auf Client in einer bestimmten Runtime ausgeführt wird um Daten mit dem Server auszutauschen
	private final EditorServiceAsync _asyncObj = GWT.create(EditorService.class);
	

	ListBox lbFilter = new ListBox();
	
	private ArrayList<Nachricht> _AlleNachrichten = new ArrayList<Nachricht>();
	
	public NeuigkeitenEditor(){
	}
	
	
	public void FilterNachBenutzer(NutzerZelle.ZellenObjekt nah){
		Window.alert("Nachrichten werden nach Benutzer " + nah.nutzer.getVorname());
	}
	
	public void onLoad() {	
		
		HorizontalPanel hpFilter = new HorizontalPanel();
		hpFilter.setWidth("50%");

		hpFilter.add(new Label("Filter:"));	
		
		
		lbFilter.addItem("Alle öffentliche Nachrichten");
	    lbFilter.addItem("Nur abonnierte Nutzer");
	    lbFilter.addItem("Nur abonnierte Hashtags");
	    lbFilter.addItem("Nur meine öffentliche Nachrichten");
	    lbFilter.addItem("Hallo", "Welt");
	    hpFilter.add(lbFilter);	    
	    
	    RootPanel.get("content_left").add(hpFilter);
	    

		HorizontalPanel hpOptionen = new HorizontalPanel();
		hpOptionen.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hpOptionen.setWidth("50%");

		Button btnAbosVerwalten = new Button("Verwalten");
		btnAbosVerwalten.addClickHandler(btnAbosVerwaltenClickHandler);
		hpOptionen.add(btnAbosVerwalten);

		Button btnNeu = new Button("Neue Nachricht");
		hpOptionen.add(btnNeu);
		
		
	    RootPanel.get("content_right").add(hpOptionen);
		
		
	}
	
	 ClickHandler btnAbosVerwaltenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
//			MenuBarEditor.setzeInhalt(MenuBarEditor.gibansichtEinstellungenAboverwatung());
			Window.alert(lbFilter.getValue(lbFilter.getSelectedIndex()));
		}
	};

}
