package de.hdm.tellme.client;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import de.hdm.tellme.shared.bo.Nutzer;

public class HashtagAbo {
	
	/**
	 * 
	 * @author Denis Pokorski
	 * @version 1.1
	 * @since 26.11.2015
	 * 
	 */
	private LoginInfo loginInfo;
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	private ListBox dropDownHashtagBereitsAbonniert = new ListBox();
	private ListBox dropDownHashtagNochNichtAbooniert = new ListBox();

	private int auswahlIdHashtagAboLoeschen;
	private int auswahlIdHashtagAboHinzufuegen;

	public Button HashtagAboLoeschenButton() {

		Button HashtagAboLoeschenBtn = new Button("Hashtagabnonnement löschen");
		final int meineId = loginInfo.getUser().getId();

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
	
	public ListBox getAbonnierteHashtagAboHinzufuegenListe(){
		
		asyncObj.getAlleNochNichtAbonnierteHashtagAboListe(loginInfo.getUser().getId(), new AsyncCallback<Vector<Nutzer>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO
				Window.alert("Fehler");

			}

			@Override
			public void onSuccess(Vector<Nutzer> resultListe) {

				dropDownHashtagNochNichtAbooniert.clear();
				dropDownHashtagNochNichtAbooniert.addItem("---");

				for (int i = 0; i <= resultListe.size(); i++) {
					dropDownHashtagNochNichtAbooniert.addItem(resultListe
							.get(i).getId()
							+ "-"
							+ resultListe.get(i).getSichtbarkeit()
							+ "  "
							+ resultListe.get(i).getErstellungsDatum());
				}
			}
		});

		dropDownHashtagNochNichtAbooniert.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int i = dropDownHashtagNochNichtAbooniert.getSelectedIndex();
				String s = dropDownHashtagNochNichtAbooniert.getValue(i);
				s = s.substring(0, s.indexOf('-'));
				auswahlIdHashtagAboHinzufuegen = Integer.parseInt(s);
			}
		});

		return dropDownHashtagNochNichtAbooniert;
	
		
	}
	
	public ListBox getAbonnerteHashtagAboLoeschenListe() {
		final int meineId = loginInfo.getUser().getId();

		asyncObj.getZuAbonnierendeLoeschenHashtagAboListe(meineId,
				new AsyncCallback<Vector<Hashtag>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");;

					}

					@Override
					public void onSuccess(Vector<Hashtag> resultListe) {
						dropDownHashtagBereitsAbonniert.clear();
						dropDownHashtagBereitsAbonniert.addItem("----");
						
						for (int i=0; i<=resultListe.size();i++){
							dropDownHashtagBereitsAbonniert.addItem(resultListe
									.get(i).getId()
									+"-"
									+resultListe.get(i).getSchlagwort()
									+" "
									+resultListe.get(i).getErstellungsDatum());
						
						}
					}

				});
		
		dropDownHashtagBereitsAbonniert.addChangeHandler(new ChangeHandler(){
			@Override
			public void onChange(ChangeEvent event) {
				int i = dropDownHashtagBereitsAbonniert.getSelectedIndex();
				String s = dropDownHashtagBereitsAbonniert.getValue(i);
				s = s.substring(0, s.indexOf('-'));
				auswahlIdHashtagAboHinzufuegen = Integer.parseInt(s);
			}
		});
		return dropDownHashtagBereitsAbonniert;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

}
