package de.hdm.tellme.client;

import java.util.logging.Level;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.MenuBarEditor;
import de.hdm.tellme.client.gui.editor.NutzerBearbeitenEditor;
import de.hdm.tellme.client.gui.editor.NutzerCellList;

import de.hdm.tellme.shared.LoginService;
import de.hdm.tellme.shared.LoginServiceAsync;
import de.hdm.tellme.shared.LoginInfo;

public class TellMe implements EntryPoint {
	public TellMe() {

	}

	public static LoginInfo eingeloggterBenutzer = null;

	LoginServiceAsync loginService = GWT.create(LoginService.class);

	public static LoginInfo gibEingeloggterBenutzer() {
		return eingeloggterBenutzer;
	}

	public Widget ladeTellMe() {
		if (eingeloggterBenutzer.getUser().getVorname() == "undefined"
				|| eingeloggterBenutzer.getUser().getNachname() == "undefined") {
			NutzerBearbeitenEditor nE = new NutzerBearbeitenEditor();
			RootPanel.get("content").add(nE);

		} else {

			// Startseite anzeigen
			MenuBarEditor menuBar = new MenuBarEditor();

			RootPanel.get("header").add(menuBar);

			menuBar.setzeInhalt(new NutzerCellList().generiereCellList(CellListModus.Nachrichtenuebersicht),menuBar.gibansichtNeuigkeiten());
		}
		return null;
	}

	@Override
	public void onModuleLoad() {
		loginService.getNutzerInfo(new AsyncCallback<LoginInfo>() {

			@Override
			public void onSuccess(LoginInfo result) {
				eingeloggterBenutzer = result;

				if (eingeloggterBenutzer.isLoggedIn()) {

					ladeTellMe();
				} else {
					Window.Location.assign(eingeloggterBenutzer.getLoginUrl());
				}

			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});

	}

}