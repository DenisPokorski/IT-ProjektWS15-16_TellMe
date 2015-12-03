package de.hdm.tellme.client;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nutzer;



public class NutzerAbo {
	
	
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
	private LoginInfo loginInfo;
	
	
	public Vector<Nutzer> getZuAbonnieredeNutzerLoeschenListe() {
		Vector<Nutzer> zuAbonnieredeNutzerLoeschenListe = new Vector<Nutzer>();

		asyncObj.getZuAbonnieredeNutzerListe(loginInfo.getUser().getId(), new AsyncCallback < Vector<Nutzer>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
 				result = zuAbonnieredeNutzerLoeschenListe;
			}
			});
		return zuAbonnieredeNutzerLoeschenListe;
	}
	

	
	
	
	public Vector<Nutzer>  getZuAbonnieredeNutzerListe( ) {
		Vector<Nutzer> zuAbonnieredeNutzerListe = new Vector<Nutzer>();

		
		asyncObj.getZuAbonnieredeNutzerListe(loginInfo.getUser().getId(), new AsyncCallback <Vector<Nutzer>>() 
				{
			@Override
			public void onFailure(Throwable caught) {

				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {

				result = zuAbonnieredeNutzerListe;
			}
		});
		return zuAbonnieredeNutzerListe;

	}




	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	

}
