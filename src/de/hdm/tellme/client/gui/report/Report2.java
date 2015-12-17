package de.hdm.tellme.client.gui.report;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.ReportService;
import de.hdm.tellme.shared.ReportServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;

public class Report2 extends VerticalPanel {
	
	private LoginInfo loginInfo;
	
	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift1 = new Label("Report 2: Nutzerabonnements eines Nutzers abfragen");
	private ListBox nutzerAboDropDown = new ListBox();
	private Button report2Generieren = new Button("Report 2 generieren");
	
	private final ReportServiceAsync asyncObj = GWT.create(ReportService.class);

	private int auswahlIdReport2;
	
	public ListBox nutzerAboDropDownListe(){
		

			
			
			asyncObj.report2GenerierenListe(loginInfo.getUser().getId(), new AsyncCallback<Vector<Nutzer>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("FEHLER");
				}

				@Override
				public void onSuccess(Vector<Nutzer> alleNutzerListe) {
					
					nutzerAboDropDown.clear();
					nutzerAboDropDown.addItem("---");
					
					for (int i=0; i<alleNutzerListe.size(); i++){
						nutzerAboDropDown.addItem(alleNutzerListe.get(i).getId()
								+"-"
								+alleNutzerListe.get(i).getVorname()
								+"-"
								+alleNutzerListe.get(i).getNachname());
					}
					
				}
			
		});
		
		nutzerAboDropDown.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				int i = nutzerAboDropDown.getSelectedIndex();
				String s = nutzerAboDropDown.getValue(i);
				s = s.substring(0, s.indexOf('-'));
				auswahlIdReport2 = Integer.parseInt(s);
				
			}
		});
		
		return nutzerAboDropDown;
		
	}
	
	
	
	public void onLoad(){
		
		reportPanel.add(ueberSchrift1);
		reportPanel.add(nutzerAboDropDown);
		reportPanel.add(report2Generieren);
		
		RootPanel.get("content").clear();
		RootPanel.get("content").add(reportPanel);
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

}
