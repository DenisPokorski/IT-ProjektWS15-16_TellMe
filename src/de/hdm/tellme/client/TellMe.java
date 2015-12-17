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

import de.hdm.tellme.shared.LoginService;
import de.hdm.tellme.shared.LoginServiceAsync;
import de.hdm.tellme.shared.LoginInfo;

public class TellMe implements EntryPoint {

	public static LoginInfo eingeloggterBenutzer = null;

	private HorizontalPanel loginPanel = new HorizontalPanel();
	LoginServiceAsync loginService = GWT.create(LoginService.class);
	private Anchor signInLink = new Anchor("Anmelden");
	private Anchor signOutLink = new Anchor("Abmelden");

	public static LoginInfo gibEingeloggterBenutzer() {
		return eingeloggterBenutzer;
	}

	public void ladeTellMe() {
		// Startseite anzeigen
		MenuBarEditor menuBar = new MenuBarEditor();

		RootPanel.get("header").add(menuBar);
		
		menuBar.setzeInhalt(menuBar.gibansichtUnterhaltungen());
		
	}

	public Widget loadLogin() {
		signInLink.setHref(eingeloggterBenutzer.getLoginUrl());
		loginPanel.add(new Label("Bitte anmelden"));
		loginPanel.add(signInLink);

		RootPanel.get("header").add(loginPanel);
		return loginPanel;
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
					loadLogin();
				}
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});

	}

}