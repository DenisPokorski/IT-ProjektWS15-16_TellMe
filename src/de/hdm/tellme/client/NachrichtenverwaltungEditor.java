package de.hdm.tellme.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.client.gui.editor.AsyncDataProviderExample;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;

import java.util.Arrays;
import java.util.List;;

public class NachrichtenverwaltungEditor extends VerticalPanel {

	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
  	
 	private VerticalPanel  NachrichtenÜbersichtMasterLeft = new VerticalPanel(); 
 	
  

 
	  public void onLoad() {
			//RootPanel.get("content").clear();
		   // NachrichtenÜbersichtMasterLeft.add( new UnterhaltungPanel().oeffenlticheUnterhaltungenAnzeigen());
		  	UnterhaltungPanel uP =  new UnterhaltungPanel();
			RootPanel.get("content_left").add( new UnterhaltungPanel().unterhaltungStarten());
			//RootPanel.get("content_left").add( new UnterhaltungErstellen().ladeAlleOeffentlichenUnterhaltungen()   );
		
			//RootPanel.get("content_left").add( new UnterhaltungErstellen().ladeAlleOeffentlichenUnterhaltungen()   );
			//RootPanel.get("content_left").add( new UnterhaltungPanel().oeffenlticheUnterhaltungenAnzeigen()   );

			//new AsyncDataProviderExample();
			
			//RootPanel.get("content_left").add(  );
			//	NachrichtenÜbersichtMasterLeft.add(UnterhaltungStarten);
			//	RootPanel.get("content_left").add(NachrichtenÜbersichtMasterLeft);
 		
 			

		  
		 
		 

		  
 	   }
	
	  int counter = -1;
	 
 
	  
	
	
}
