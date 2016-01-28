package de.hdm.tellme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.tellme.client.gui.editor.MenuBarEditor;
import de.hdm.tellme.client.gui.editor.NutzerBearbeitenEditor;
import de.hdm.tellme.client.gui.report.MenuBarReport;
import de.hdm.tellme.client.gui.report.ReportWillkommenSeite;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.LoginService;
import de.hdm.tellme.shared.LoginServiceAsync;

/**
 * Das ist der Eingangspunkt-Klasse des Projekts <b>TellMe</b> In dieser Klasse
 * wird der Login und die MenuBar erstellt.
 * 
 * @author denispokorski
 *
 */
public class TellMeReport implements EntryPoint {

	/**
	 * Die Klasse implementiert das Interface <code> EntryPoint</code>. Dafür
	 * wird die Methode <code> pubic void onModuleLoad()</code>. Das ist das
	 * GWT-Gegenstück der <code>main()</code> einer normalen Java-Applikation
	 * 
	 *
	 */
	public TellMeReport() {

	}

	/**
	 * Damit die Klasse LoginInfo nur einmal während der Laufzeit des Programms
	 * bestehen kann, muss man sie als Singleton darstellen, dies geschieht
	 * durch die Referenz <code>static</code>.
	 */

	private static LoginInfo eingeloggterBenutzer = null;
	/**
	 * LoginService wird erstellt.
	 */
	LoginServiceAsync loginService = GWT.create(LoginService.class);

	MenuBarReport menuBar = new MenuBarReport();

	public static LoginInfo gibEingeloggterBenutzer() {
		return eingeloggterBenutzer;
	}

	public String getLoginUrl() {
		String a = com.google.gwt.core.client.GWT.getModuleBaseURL();
		String b;
		b = a.substring(0, a.length()-1);
		b += ".html";
		return b;

	}

	public Widget ladeTellMeReport() {

		if (eingeloggterBenutzer.getUser().getVorname() == "undefined"
				|| eingeloggterBenutzer.getUser().getNachname() == "undefined") {

			MenuBarEditor.setzeInhalt(new Label("Bitte lege deinen Vor- und Nachnamen im Editor fest."));

		} else {

			RootPanel.get("header").add(menuBar);
			
			// Startseite/Report-Seite anzeigen
			MenuBarReport.setzeInhalt(new ReportWillkommenSeite());
		}
		return null;
	}

	@Override
	public void onModuleLoad() {
		/**
		 * Der Login-Status wird durch den LoginService überprüft.
		 */
		loginService.getNutzerInfo(
				getLoginUrl(),
				new AsyncCallback<LoginInfo>() {

					@Override
					public void onSuccess(LoginInfo result) {
						eingeloggterBenutzer = result;

						/**
						 * Wenn der Nutzer eingeloggt ist, wird dem Nutzer die
						 * TellMe-Applikation angezeigt.
						 */
						if (eingeloggterBenutzer.isLoggedIn()) {
							ladeTellMeReport();
						} else {
							Window.Location.assign(eingeloggterBenutzer
									.getLoginUrl());
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Login fehlgeschlagen");
					}
				}
		);
	}
}