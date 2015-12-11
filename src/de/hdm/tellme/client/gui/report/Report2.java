package de.hdm.tellme.client.gui.report;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.ReportServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * 
 * Dieser Report zeigt alle Nutzerabonnements von einem bestimmten Nutzer an. 
 * @author Zimmermann
 * @version 1.0
 * 
 */

/* TODO Kommentare nachtragen*/

public class Report2   extends VerticalPanel {
	
	
	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift1 = new Label(
			"Report2: Nutzerabonnements abfragen");
	private Button report2Generieren = new Button("Report 2 generieren");
	private ListBox nutzerAboDropDown = new ListBox();
	private LoginInfo loginInfo;

	private final ReportServiceAsync asyncObj = GWT.create(EditorService.class);

	private int auswahlIdReport2;
	
	
	public ListBox getNutzerAboDropDownListe(){
		
		
		final int tempId = loginInfo.getUser().getId();
		asyncObj.report2GenerierenListe( tempId,
			new AsyncCallback<Vector<Nutzer>>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vector<Nutzer> resultListe) {
					nutzerAboDropDown.clear();
					nutzerAboDropDown.addItem("---");
					
					for (int i=0; i<resultListe.size(); i++){
						nutzerAboDropDown.addItem(resultListe.get(i).getId()
								+"-"
								+resultListe.get(i).getVorname()
								+"-"
								+resultListe.get(i).getNachname());
					}
					
				}
			
		});
		
		nutzerAboDropDown.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				int i = nutzerAboDropDown.getSelectedIndex();
				String s = nutzerAboDropDown.getValue(i);
				s = s.substring(0, s.indexOf("-"));
				auswahlIdReport2 = Integer.parseInt(s);
				
			}
		});
		
		return nutzerAboDropDown;
		
	}
	
 	
	public void onLoad()  {
		reportPanel.add(ueberSchrift1);
		reportPanel.add(nutzerAboDropDown );
		reportPanel.add(report2Generieren);
		RootPanel.get("content").clear();
		RootPanel.get("content").add(reportPanel);
	
		
report2Generieren.addClickHandler(new ClickHandler() {

			
			@Override
			public void onClick(ClickEvent event) {
				if((auswahlIdReport2 != 0)
						&&(nutzerAboDropDown.getSelectedIndex() > 0)){
					
				}
				
			}
		});}
	

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

}
