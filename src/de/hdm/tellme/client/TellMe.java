package de.hdm.tellme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.tellme.client.Schaukasten.NeuigkeitenEditor;
import de.hdm.tellme.client.Schaukasten.NutzerBearbeitenEditor;
import de.hdm.tellme.client.gui.editor.MenuBarEditor;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.LoginService;
import de.hdm.tellme.shared.LoginServiceAsync;
/**
 * Das ist der Eingangspunkt-Klasse des Projekts <b>TellMe</b>
 * TODO
 * @author denispokorski
 *
 */
public class TellMe implements EntryPoint {
	
	private static NeuigkeitenEditor ansichtNeuigkeiten = null;

	public static NeuigkeitenEditor gibansichtNeuigkeiten() {
		ansichtNeuigkeiten = new NeuigkeitenEditor();
		return ansichtNeuigkeiten;
	}
	
	  /**
	   * Die Klasse implementiert das Interface <code> EntryPoint</code>.
	   * Dafür wird die Methode <code> pubic void onModuleLoad()</code>.
	   * Das ist das GWT-Gegenstück der <code>main()</code> einer normalen 
	   * Java-Applikation
	   * 
	   *
	   */
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

			
			
			
			RootPanel.get("ButtonBar").clear();
			RootPanel.get("content_left").clear();
			RootPanel.get("content_right").clear();
			RootPanel.get("content").clear();
			
			RootPanel.get("header").add(menuBar);
			RootPanel.get("content_left").add( NeuigkeitenEditor.gibFilterPanel());
			RootPanel.get("content_right").add(	gibansichtNeuigkeiten());
 

			MenuBarEditor.setzeInhalt(NeuigkeitenEditor.gibFilterPanel(),MenuBarEditor.gibansichtNeuigkeiten());
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