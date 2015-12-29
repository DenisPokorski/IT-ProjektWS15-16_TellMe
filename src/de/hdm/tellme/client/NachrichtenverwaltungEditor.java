package de.hdm.tellme.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.UnterhaltungPanel;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;

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
