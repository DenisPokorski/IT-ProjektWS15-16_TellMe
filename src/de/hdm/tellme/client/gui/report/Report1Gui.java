package de.hdm.tellme.client.gui.report;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.NutzerCellList;
import de.hdm.tellme.shared.LoginInfo;

/**
 * TODO
 */
/**
 * 
 * 
 * 
 * 
 * Dieser Report zeigt alle Nachrichten je Nutzer und Zeitraum an. 
 * @author Zimmerman & Alex Homann
 * @version 1.1
 * 
 */

public class Report1Gui extends VerticalPanel{
	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift1 = new Label("Report1: Nachrichten abfragen");
	private Label subSchrift1 = new Label("Nutzer auswählen");
	private Label subSchrift2 = new Label("Zeitraum auswählen");
	
	private HorizontalPanel reportPanel1 = new HorizontalPanel();
	private VerticalPanel reportPanel1_left = new VerticalPanel();
	private VerticalPanel reportPanel1_right = new VerticalPanel();

	private DateBox vonDateBox = new DateBox( ); 
	private DateBox bisDateBox = new DateBox( ); 
	private Button report1Generieren = new Button("Report 1 generieren");
	
	private HTML beschreibung1 = new HTML("<ul><b>Der Report 1 gibt alle Nachrichten eines Nutzers in einen bestimmten Zeitraum, "
			+ "alle Nachrichten eines Nutzers, alle Nachrichten in einem bestimmten Zeitraum"
			+ " oder alle Nachrichten aus</b>"
			+ "<li>Um einen Report auszugeben, der  alle Nachrichten <b>eines Nutzers</b>"
			+ "in <b>einem bestimmten Zeitraum</b> darstellt, musst du einen Nutzer<b> UND</b> einen Zeitraum auswählen</li>"
			+ "<li>Um einen Report auszugeben, der alle Nachrichten in <b>einem bestimmten Zeitraum</b>"
			+ " darstellt, darfst du <b>KEINEN Nutzer</b> auswählen und <b>musst einen Zeitraum </b>auswählen.</li> "
			+ "<li>Um einen Report auszugeben, der alle Nachrichten von<b> einem bestimmten Nutzer</b>"
			+ " darstellt, musst du <b>einen Nutzer </b>auswählen und darfst <b>keinen Zeitraum </b>auswählen. </li>"
			+ "<li>Um einen Report auszugeben, der alle Nachrichten "
			+ " darstellt, darfst du<b> keinen Nutzer</b> und <b>keinen Zeitraum</b> auswählen.</li></ul>");
	
	public void onLoad(){
		reportPanel.add(ueberSchrift1);
		reportPanel1.add(reportPanel1_left);
		reportPanel1.add(reportPanel1_right);
		reportPanel1_left.add(subSchrift1);
		reportPanel1_left.add(new NutzerCellList()
		.generiereCellList(CellListModus.Report1_NachrichtNutzerZeitraum));
		reportPanel1_left.add(subSchrift2);
		reportPanel1_left.add(vonDateBox);
		reportPanel1_left.add(bisDateBox);
		reportPanel1_left.add(report1Generieren);
		
		reportPanel1_right.add(beschreibung1);
		
		
		
		reportPanel.add(reportPanel1);
		
		
		
		RootPanel.get("content").clear();
		RootPanel.get("content_left").clear();
		RootPanel.get("content_right").clear();
		RootPanel.get("content_right").add(reportPanel1_right);
		RootPanel.get("content_left").add(reportPanel1_left);


		vonDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bisDateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		report1Generieren.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				
			}
		});
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		
	}

 
}
