package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;


/**
 * 
 * Dieser Report zeigt alle Nachrichten je Nutzer und Zeitraum an. 
 * @author Zimmermann
 * @version 1.0
 * 
 */

public class Report1 {

	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift1 = new Label("Report1: Nachrichten abfragen");
	private Label subSchrift1 = new Label("Nutzer auswählen");
	private Label subSchrift2 = new Label("Zeitraum auswählen");
	
	private HorizontalPanel reportPanel1 = new HorizontalPanel();
	private VerticalPanel reportPanel1_left = new VerticalPanel();
	private VerticalPanel reportPanel1_right = new VerticalPanel();

	private ListBox nutzerDropDown = new ListBox();
	private DateBox vonDateBox = new DateBox( ); 
	private DateBox bisDateBox = new DateBox( ); 
	private Button nutzerAbosAnzeigen = new Button("Nutzerabos anzeigen");
	
	private void onLoad(){
		reportPanel.add(ueberSchrift1);
		reportPanel1.add(reportPanel1_left);
		reportPanel1.add(reportPanel1_right);
		reportPanel1_left.add(subSchrift1);
		reportPanel1_left.add(nutzerDropDown);
		reportPanel1_right.add(subSchrift2);
		reportPanel1_right.add(vonDateBox);
		reportPanel1_right.add(bisDateBox);
		reportPanel1_right.add(nutzerAbosAnzeigen);
		reportPanel.add(reportPanel1);
	}
}
