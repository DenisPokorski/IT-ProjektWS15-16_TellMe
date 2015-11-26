package de.hdm.tellme.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nutzer;

public class NutzerBearbeiten extends VerticalPanel {

	// Kommentar nachtragen(TODO)
	private VerticalPanel profilPanel = new VerticalPanel();
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
	private Label ueberschrift1 = new Label("Mein Profil");
	private Label untertitel = new Label(
			"Hier siehst du dein Profil und kannst es bearbeiten, lï¿½schen und dich abmelden.");
	private Label ueberschrift2 = new Label("Profil bearbeiten");

	private LoginInfo loginInfo;

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	// Kommentar nachtragen(TODO)
	private Label bezeichnungVornameTextBox = new Label("Vorname");
	private TextBox vornameTextBox = new TextBox();
	private Label bezeichnungNachnameTextBox = new Label("Nachname");
	private TextBox nachnameTextBox = new TextBox();
	private Label bezeichnungEmailTextBox = new Label("Emailadresse");
	private TextBox emailTextBox = new TextBox();
	private Nutzer n = new Nutzer();
	// Kommentar nachtragen(TODO)
	private HorizontalPanel ButtonPanel = new HorizontalPanel();
	private Button aenderungenSpeichernButton = new Button(
			"ï¿½nderungen speichern");

	private Button profilloeschenButton = new Button("Profil lï¿½schen");

	public Nutzer getNutzerVonMailAdresse(String eMailAdress) {

		asyncObj.getNutzerVonMailadresse(eMailAdress,
				new AsyncCallback<Nutzer>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Nutzer result) {

						n.setId(result.getId());
						n.setVorname(result.getVorname());
						n.setNachname(result.getNachname());
						n.setMailadresse(result.getMailadresse());

						vornameTextBox.setText(n.getVorname());
						nachnameTextBox.setText(n.getNachname());
						emailTextBox.setText(n.getMailadresse());

					}

				});
		return n;

	}

	public void nutzerBearbeiten(Nutzer n) {
		asyncObj.nutzerAktualisieren(n, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void nutzerLoeschen(Nutzer n) {
		asyncObj.nutzerLoeschen(n, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void onLoad() {
		String test = "dfeltrin.hdm@gmail.com";
		getNutzerVonMailAdresse(test);

		// Kommentar nachtragen(TODO)
		profilPanel.add(ueberschrift1);
		profilPanel.add(untertitel);
		profilPanel.add(ueberschrift2);

		profilPanel.add(bezeichnungVornameTextBox);
		profilPanel.add(vornameTextBox);
		profilPanel.add(bezeichnungNachnameTextBox);
		profilPanel.add(nachnameTextBox);
		profilPanel.add(bezeichnungEmailTextBox);
		profilPanel.add(emailTextBox);
		emailTextBox.setReadOnly(true);

		ButtonPanel.add(aenderungenSpeichernButton);

		ButtonPanel.add(profilloeschenButton);

		// Kommentar nachtragen(TODO)
		ueberschrift1.addStyleName("ueberschrift1");
		untertitel.addStyleName("untertitel");
		ueberschrift2.addStyleName("ueberschrift2");

		bezeichnungVornameTextBox.addStyleName("bezeichnungVornameTextBox");
		vornameTextBox.addStyleName("vornameTextBox");
		bezeichnungNachnameTextBox.addStyleName("bezeichnungNachnameTextBox");
		nachnameTextBox.addStyleName("nachnameTextBox");
		bezeichnungEmailTextBox.addStyleName("bezeichnungEmailTextBox");
		emailTextBox.addStyleName("emailTextBox");

		aenderungenSpeichernButton.addStyleName("aenderungenSpeichernButton");

		profilloeschenButton.addStyleName("profilloeschenButton");

		// Kommentar nachtragen(TODO)
		RootPanel.get("content").clear();
		RootPanel.get("content").add(profilPanel);
		RootPanel.get("content").add(ButtonPanel);
		aenderungenSpeichernButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (vornameTextBox.getValue().isEmpty()
						|| nachnameTextBox.getValue().isEmpty()) {
					Window.alert("Bitte alle Felder befüllen");
				} else {
					Nutzer na = new Nutzer();
					na.setId(n.getId());
					na.setMailadresse(emailTextBox.getText());
					na.setVorname(vornameTextBox.getText());
					na.setNachname(nachnameTextBox.getText());
					nutzerBearbeiten(na);
					Window.alert("Profil erfolgreich editiert.");
					RootPanel.get("content").clear();

				}

			}
		});
		profilloeschenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				nutzerLoeschen(n);
				Window.alert("Profil gelöscht");
				RootPanel.get("content").clear();

			}
		});
	}

}
