package Archiv;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NachrichtenverwaltungEditor extends VerticalPanel {

	public void onLoad() {
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
