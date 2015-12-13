package de.hdm.tellme.client;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Hashtag;

public class HashtagAbo {

	/**
	 * 
	 * @author Denis Pokorski
	 * @version 1.1
	 * @since 26.11.2015
	 * 
	 */

	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	private ListBox dropDownHashtagBereitsAbonniert = new ListBox();
	private ListBox dropDownHashtagNochNichtAbooniert = new ListBox();

	private int auswahlIdHashtagAboLoeschen;

	private LoginInfo loginInfo;

	public Button HashtagAboLoeschenButton() {

		Button HashtagAboLoeschenBtn = new Button("Hashtagabnonnement löschen");
		final int meineId = 7;

		HashtagAboLoeschenBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if ((auswahlIdHashtagAboLoeschen != 0)
						&& (dropDownHashtagBereitsAbonniert.getSelectedIndex() > 0)) {
					HashtagAboLoeschenByIds(meineId,
							auswahlIdHashtagAboLoeschen);
				} else {
					Window.alert("Bitte wählne Sie ein Hashtagabonnement in der Liste aus, dass gelöscht werden soll.");
				}
			}
		});
		return HashtagAboLoeschenBtn;
	}

	public void HashtagAboLoeschenByIds(int NutzerId, int HashtagId) {
		asyncObj.loescheNutzeraboById(NutzerId, HashtagId,
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Das Hashtagabo wurde nicht erfolgreich gelöscht.");

					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Das Hashtagabonnement wurde erfolgreich gelöscht.");
						RootPanel.get("content").clear();
						AboverwaltungEditor aB = new AboverwaltungEditor();
						RootPanel.get("content").add(aB);
					}
				});

	}

	public void HashtagAboErstellenByIds(int NutzerId, int HashtagId) {
		asyncObj.erstellenHashtagAboById(NutzerId, HashtagId,
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Das Hashtagabo wurde nicht erstellt.");

					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Das Hashtagabonnement wurde erfolgreich erstellt.");
						RootPanel.get("content").clear();
						AboverwaltungEditor aE = new AboverwaltungEditor();
						RootPanel.get("content").add(aE);
					}
				});
	}

	public ListBox getAbonnerteHashtagAboLoeschenListe() {

		final int meineId = 7;

		asyncObj.getZuAbonnierendeLoeschenHashtagAboListe(meineId,
				new AsyncCallback<Vector<Hashtag>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Vector<Hashtag> resultListe) {
						// TODO Auto-generated method stub

					}

				});
		return dropDownHashtagBereitsAbonniert;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
}
