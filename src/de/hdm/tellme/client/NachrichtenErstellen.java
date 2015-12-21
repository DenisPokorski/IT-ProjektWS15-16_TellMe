package de.hdm.tellme.client;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;

/*
 * @author: DenisPokorski
 * datum: 6.12.15 
 * version: 1.2
 */

public class NachrichtenErstellen extends VerticalPanel {

	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	public void oeffentlicheNachrichtErstellen(Timestamp date, String text) {

		int meineId = 7;
		Nachricht n = new Nachricht();
		n.setErstellungsDatum(date);
		n.setSichtbarkeit(1);
		n.setText(text);
		n.setSenderId(meineId);

		asyncObj.NachrichtErstellen(n, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail1");
			}

			@Override
			public void onSuccess(Void result) {
			}
		});
	}

	void privateNachrichtErstellen() {
	}

	void antwortNachrichtErstellen() {
	}

	public void oeffentlicheNachrichtErstellenUnterhaltungZuweisen(Timestamp ts, String text, int uid) {

		int meineId = 7;
		Nachricht n = new Nachricht();
		n.setErstellungsDatum(ts);
		n.setSichtbarkeit(1);
		n.setText(text);
		n.setSenderId(meineId);

		asyncObj.NachrichtErstellenUnnterhaltungZuweisen(n, uid, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail1");
			}

			@Override
			public void onSuccess(Void result) {
			}
		});

	}

	// public void oeffentlicheNachrichtUnterhaltungZuweisen(Timestamp
	// timestamp,
	// String text, int uid) {
	//
	// final String txt = text;
	// final int uId = uid;
	// final Timestamp ts = timestamp;
	//
	// asyncObj.nachrichtUnterhaltungZuweisen(txt, uId, ts, new AsyncCallback
	// <Void>() {
	// @Override
	// public void onFailure(Throwable caught) {
	// Window.alert( "fail1");
	// }
	//
	// @Override
	// public void onSuccess(Void result) {
	// }
	// } );
	//
	//
	//
	// }

}
