package de.hdm.tellme.client.Schaukasten;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.gui.editor.MenuBarEditor;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Klasse zum Nutzer bearbeiten und löschen mit den zugehörigen Async-Methoden
 * und der GUI
 * 
 * 
 * @author Feltrin, Zimmermann
 * @version 1.0
 * @since 27.11.2015
 * 
 */

public class NutzerBearbeitenEditor extends VerticalPanel {

	/*
	 * Deklarationen der einzelnen Widgets wie beispielsweise den Panels,
	 * Textboxen und Buttons, RPC-Klasse und Überschriften.
	 */

	private Label bezeichnungVornameTextBox = new Label("Vorname");
	private TextBox vornameTextBox = new TextBox();
	private Label bezeichnungNachnameTextBox = new Label("Nachname");
	private TextBox nachnameTextBox = new TextBox();
	private Label bezeichnungEmailTextBox = new Label("E-Mailadresse");
	private TextBox emailTextBox = new TextBox();
	private Nutzer n = new Nutzer();

	private HorizontalPanel ButtonPanel = new HorizontalPanel();
	private Button aenderungenSpeichernButton = new Button(
			"Änderungen speichern");

	private Button profilloeschenButton = new Button("Profil löschen");
	private VerticalPanel profilPanel = new VerticalPanel();
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	private VerticalPanel vP = new VerticalPanel();

	private Label subline = new Label("Profildaten");
	HTML headline = new HTML(" <div class='" + "subline"
			+ "'><h2>Meine Nutzerdaten bearbeiten</h2></div> ");
	HTML subtext = new HTML(
			" <div class='"
					+ "subtext"
					+ "'><h4>  Auf dieser Oberfläche können Sie Ihre Nutzerdaten bearbeiten und speichern sowie diese vollständig löschen.  </h4></div> ");

	/*
	 * Die Methode des AsyncCallbacks, um die Daten zum Nutzer bearbeiten an die
	 * Datenbank zu senden. Nach erfolgreicher Ausführung kommt eine
	 * Bildschirmmeldung.
	 */
	public void nutzerBearbeiten(Nutzer n) {

		asyncObj.nutzerAktualisieren(n, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				RootPanel.get("content").clear();
				Window.alert("Profil erfolgreich editiert.");

			}
		});

	}

	/*
	 * Die Methode des AsyncCallbacks, die die erforderlichen Daten um sein
	 * eigenes Profil zu löschen, an die Datenbank zu senden. Nach erfolgreicher
	 * Ausführung kommt eine Bildschirmmeldung und der Benutzer wird ausgeloggt.
	 * Hier wird die id, die im loginInfo-Nutzerobjekt hinterlegt ist, zum
	 * löschen genutzt.
	 */
	public void nutzerLoeschen() {
		asyncObj.nutzerLoeschen(TellMe.eingeloggterBenutzer.getUser(),
				new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Profil gelöscht");
						Window.Location.assign(TellMe.eingeloggterBenutzer
								.getLogoutUrl());
						RootPanel.get("content").clear();
					}
				});
	}

	/*
	 * Die onLoad Methode wird ausgeführt, wenn ein neues Objekt der Klasse
	 * "NutzerBearbeiten" erstellt wird. Es werden in den Textboxen die Daten
	 * des eingeloggten Nutzers gesetzt. Des Weiteren werden die Widgets den
	 * Paneln zugeordnet. In diesem Bereich werden den Widgets zusätzlich
	 * CSS-Styles zugeordnet.
	 */
	public void onLoad() {

		if (TellMe.eingeloggterBenutzer.getUser().getVorname() == "undefined"
				|| TellMe.eingeloggterBenutzer.getUser().getNachname() == "undefined") {
			vornameTextBox.setText("");
			nachnameTextBox.setText("");
		} else {
			vornameTextBox.setText(TellMe.eingeloggterBenutzer.getUser()
					.getVorname());
			nachnameTextBox.setText(TellMe.eingeloggterBenutzer.getUser()
					.getNachname());
		}

		emailTextBox.setText(TellMe.eingeloggterBenutzer.getUser()
				.getMailadresse());

		profilPanel.add(subline);
		profilPanel.add(bezeichnungEmailTextBox);
		profilPanel.add(new Label(TellMe.gibEingeloggterBenutzer().getUser().getMailadresse()));
		profilPanel.add(bezeichnungVornameTextBox);
		profilPanel.add(vornameTextBox);
		profilPanel.add(bezeichnungNachnameTextBox);
		profilPanel.add(nachnameTextBox);
		
		emailTextBox.setReadOnly(true);
		
		ButtonPanel.add(aenderungenSpeichernButton);
		ButtonPanel.add(profilloeschenButton);

		bezeichnungVornameTextBox.addStyleName("bezeichnungVornameTextBox");
		vornameTextBox.addStyleName("vornameTextBox");
		aenderungenSpeichernButton.setStylePrimaryName("btnPositiv");
		profilloeschenButton.setStylePrimaryName("btnNegativ");
		bezeichnungNachnameTextBox.addStyleName("bezeichnungNachnameTextBox");
		nachnameTextBox.addStyleName("nachnameTextBox");
		bezeichnungEmailTextBox.addStyleName("bezeichnungEmailTextBox");
		emailTextBox.addStyleName("emailTextBox");
		ButtonPanel.setStylePrimaryName("nutzerButtonPanel");
		vP.addStyleName("vertical-box");
		aenderungenSpeichernButton.addStyleName("aenderungenSpeichernButton");
		profilloeschenButton.addStyleName("profilloeschenButton");

		vP.add(headline);
		vP.add(subtext);
		vP.add(profilPanel);
		vP.add(ButtonPanel);
		RootPanel.get("content").add(vP);


		/*
		 * Der ClickHandler des Speichern-Buttons. Zuerst wird eine if-Abfrage
		 * durchgeführt, ob die Textboxen Vorname und Nachname befüllt sind.
		 * Danach wird das Objekt n des Typ Nutzers mit den Daten aus der
		 * TextBox befüllt. Danach wird das Objekt n an die Methode
		 * nutzerBearbeiten übergeben.
		 */
		aenderungenSpeichernButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (vornameTextBox.getValue().isEmpty()
						|| nachnameTextBox.getValue().isEmpty()) {
					Window.alert("Bitte alle Felder richtig befüllen");
				} else {

					n.setId(TellMe.eingeloggterBenutzer.getUser().getId());
					n.setMailadresse(emailTextBox.getText());
					n.setVorname(vornameTextBox.getText());
					n.setNachname(nachnameTextBox.getText());
					TellMe.eingeloggterBenutzer.getUser().setVorname(
							n.getVorname());
					TellMe.eingeloggterBenutzer.getUser().setNachname(
							n.getNachname());
					nutzerBearbeiten(n);
					RootPanel.get("header").clear();
					RootPanel.get("header").add(new MenuBarEditor());

				}

			}
		});
		/*
		 * Der ClickHandler für den Profil-löschen Button. Es wird die Methode
		 * nutzerLoeschen ausgeführt.
		 */
		profilloeschenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				nutzerLoeschen();

			}
		});
	}

}