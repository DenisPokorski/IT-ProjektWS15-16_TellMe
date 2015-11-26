package de.hdm.tellme.client;

import java.util.logging.Level;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
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
	LoginServiceAsync loginService = GWT.create(LoginService.class);
	private LoginInfo loginInfo = null;
	private Anchor signInLink = new Anchor("Anmelden");
	private Anchor signOutLink = new Anchor("Abmelden");
	private HorizontalPanel loginPanel = new HorizontalPanel();

	public void ladeMenuBarEditor() {

		// Startseite anzeigen
		MenuBarEditor menuBar = new MenuBarEditor();
		menuBar.setLoginInfo(loginInfo);
		RootPanel.get("header").clear();
		RootPanel.get("header").add(menuBar);
	}

	public Widget loadLogin() {
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(new Label("Bitte anmelden"));
		loginPanel.add(signInLink);

		RootPanel.get("header").add(loginPanel);
		return loginPanel;
	}

	@Override
	public void onModuleLoad() {

		loginService.getNutzerInfo(
				com.google.gwt.core.client.GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {

					@Override
					public void onSuccess(LoginInfo result) {
						loginInfo = result;

						if (loginInfo.isLoggedIn()) {
			
							ladeMenuBarEditor();

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
