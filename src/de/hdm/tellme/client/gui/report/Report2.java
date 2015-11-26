package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * Dieser Report zeigt alle Nutzerabonnements von einem bestimmten Nutzer an. 
 * @author Zimmermann
 * @version 1.0
 * 
 */

/* TODO Kommentare nachtragen*/

public class Report2 {
	
	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift1 =  new Label("Report2: Nutzerabonnements abfragen");
	private Button nutzerAnzeigen = new Button("Report 2 generieren");
	private ListBox nutzerAboDropDown = new ListBox();
 	
	private void onLoad()  {
		reportPanel.add(ueberSchrift1);
		reportPanel.add(nutzerAboDropDown );
		reportPanel.add( nutzerAnzeigen);
		RootPanel.get("content").clear();
		RootPanel.get("content").add(reportPanel);
	}

}
