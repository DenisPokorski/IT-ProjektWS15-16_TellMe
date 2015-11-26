package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 * 
 * Dieser Report zeigt alle Hashtagabonnements von einem bestimmten Nutzer an. 
 * @author Zimmermann
 * @version 1.0
 * 
 */

/* TODO Kommentare nachtragen*/

public class Report3  {
	
	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift1 =  new Label("Report3: Hashtagabonnements abfragen");
	private Button hashtagAnzeigen = new Button("Report 3 generieren");
	private ListBox hashtagAboDropDown = new ListBox();
 	
 	private void onLoad()  {
		
		reportPanel.add(ueberSchrift1);
		reportPanel.add(hashtagAboDropDown);
		reportPanel.add(hashtagAnzeigen);
		RootPanel.get("content").clear();
		RootPanel.get("content").add(reportPanel);
	}

}