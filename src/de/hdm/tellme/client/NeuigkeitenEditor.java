package de.hdm.tellme.client;

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

import de.hdm.tellme.shared.LoginInfo;

public class NeuigkeitenEditor extends VerticalPanel {

	public NeuigkeitenEditor(){
	}
	
	
	public void onLoad() {	
			
		
		HorizontalPanel hpFilter = new HorizontalPanel();
		hpFilter.setWidth("50%");

		hpFilter.add(new Label("Filter:"));	
		
		ListBox lbFilter = new ListBox();
		
		lbFilter.addItem("Alle öffentliche Nachrichten");
	    lbFilter.addItem("Nur abonnierte Nutzer");
	    lbFilter.addItem("Nur abonnierte Hashtags");
	    lbFilter.addItem("Nur meine öffentliche Nachrichten");
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
			MenuBarEditor.setzeInhalt(MenuBarEditor.gibansichtEinstellungenAboverwatung());
		}
	};

}
