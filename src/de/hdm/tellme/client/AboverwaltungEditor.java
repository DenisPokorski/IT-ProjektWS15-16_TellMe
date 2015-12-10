package de.hdm.tellme.client;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.server.db.NutzerAbonnementMapper;

/*
 * Diese Klasse stellt die Aboverwaltung für den Editor dar.
 * @author: thuering
 * @version: 1.2
 * 
 */

public class AboverwaltungEditor extends VerticalPanel {

	private LoginInfo loginInfo;

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	// Vertical Panel für den gesamten AboverwaltungsEditor

	private VerticalPanel aboPanel = new VerticalPanel();// Panel wird erstellt

	private Label ueberschrift1 = new Label("Aboverwaltung");// Überschrift
																// eingefügt

	private Label untertitel = new Label(
			"Hier kannst du deine Abonnements verwalten.");// Beschreibung
															// eingefügt

	// Vertical Panel für NutzerAbo
	private HorizontalPanel nutzerAboPanel = new HorizontalPanel();// Panel für
																	// Nutzerabos

	private Label ueberschriftNutzerAbo = new Label("Nutzerabo");

	// Nutzerabo hinzufügen
	private VerticalPanel nutzerAboPanel1 = new VerticalPanel();// Panel wird
																// erstellt

	private Label untertitelNutzerAbonnieren = new Label(
			"Hier kannst du die Nutzer auswählen, die du abonnieren möchtest");

	// Einfügen der Dropdown-Liste um einen Nutzer auszuwählen, den man
	// auswählen möchte.
	private Label nutzerText1 = new Label("Nutzer auswählen: ");

	// Button hinzufügen
	private Button nutzerAbonnieren = new Button("Nutzerabo hinzufügen");

	// Nutzerabo löschen
	private VerticalPanel nutzerAboPanel2 = new VerticalPanel();// Panel wird
																// erstellt

	private Label untertitelNutzerAboLoeschen = new Label(
			"Hier kannst du die Nutzer auswählen, für die du dein Abo löschen möchtest");

	private Label nutzerText2 = new Label("Nutzer auswählen: ");

	private ListBox dropDownNutzerAbonnieren = new ListBox();

	private Button nutzerNichtAbonnieren = new Button("Nutzerabo löschen");

	private HorizontalPanel hashtagAboPanel = new HorizontalPanel();// Panel für
																	// Hashtagabos

	private Label ueberschriftHashtagAbo = new Label("Hashtagabo");

	private VerticalPanel hashtagAboPanel1 = new VerticalPanel();// Panel wird
																	// erstellt

	private Label untertitelHashtagAbonnieren = new Label(
			"Hier kannst du die Hashtags auswählen, die du abonnieren möchtest");

	private Label hashtagText1 = new Label("Hashtag auswählen: ");

	private ListBox dropDownHashtagsNichtAbonniert = new ListBox();

	private NutzerAbo nA = new NutzerAbo();

	private Button hashtagAbonnieren = new Button("Hashtagabo hinzufügen");

	private VerticalPanel hashtagAboPanel2 = new VerticalPanel();// Panel wird
																	// erstellt

	private Label untertitelHashtagAboLoeschen = new Label(
			"Hier kannst du die Hashtags auswählen, für die du dein Abo löschen möchtest");

	private Label hashtagText2 = new Label("Hashtag auswählen: ");

	private ListBox dropDownHashtagsAbonniert = new ListBox();

	private Button hashtagNichtAbonnieren = new Button("Hashtagabo löschen");

	public void onLoad() { // Aufbau der Seite
	// loadListBoxAbonnierteLoeschenNutzer();
		/*
		 * Buttons und Labels werden den jeweiligen Panels zugeordnet.
		 */
		aboPanel.add(ueberschrift1);// Überschrift für AboPanel
		aboPanel.add(untertitel);// Untertitel für AboPanel
		aboPanel.add(ueberschriftNutzerAbo);
		aboPanel.add(nutzerAboPanel);

		// Nutzeraboverwaltung zuweisen
		aboPanel.add(ueberschriftHashtagAbo);

		// Nutzerabo hinzufügen
		nutzerAboPanel.add(nutzerAboPanel1);
		nutzerAboPanel1.add(untertitelNutzerAbonnieren);
		nutzerAboPanel1.add(nutzerText1);

		// Dropdownliste&Button
		nutzerAboPanel1.add(nA.getZuAbonnierndeNutzerHinzufügenListe());
		nutzerAboPanel1.add(nA.AboErstellenButton());

		// Nutzerabo löschen
		nutzerAboPanel.add(nutzerAboPanel2);
		nutzerAboPanel2.add(untertitelNutzerAboLoeschen);
		nutzerAboPanel2.add(nutzerText2);
		// Dropdownliste&Button
		nutzerAboPanel2.add(nA.getAbonnierteNutzerLoeschenListe());
		nutzerAboPanel2.add(nA.AboLoeschenButton());

		aboPanel.add(ueberschriftHashtagAbo);
		aboPanel.add(hashtagAboPanel);
		// Hashtagaboverwaltung zuweisen

		// Hashtagabo hinzufügen
		hashtagAboPanel.add(hashtagAboPanel1);
		hashtagAboPanel1.add(untertitelHashtagAbonnieren);
		hashtagAboPanel1.add(hashtagText1);
		// Dropdownliste&Button
		hashtagAboPanel1.add(dropDownNutzerAbonnieren);
		hashtagAboPanel1.add(hashtagAbonnieren);

		// Hashtagabo löschen
		hashtagAboPanel.add(hashtagAboPanel2);
		hashtagAboPanel2.add(untertitelHashtagAboLoeschen);
		hashtagAboPanel2.add(hashtagText2);
		// Dropdownliste&Button
		hashtagAboPanel2.add(dropDownHashtagsAbonniert);
		hashtagAboPanel2.add(hashtagNichtAbonnieren);

		RootPanel.get("content").add(aboPanel);

	}

}