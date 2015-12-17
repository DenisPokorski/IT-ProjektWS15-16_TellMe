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
 * Diese Klasse stellt die Aboverwaltung f�r den Editor dar.
 * @author: thuering
 * @version: 1.2
 * 
 */

public class AboverwaltungEditor extends VerticalPanel {

	// Vertical Panel f�r den gesamten AboverwaltungsEditor

	private VerticalPanel aboPanel = new VerticalPanel();// Panel wird erstellt

	private Label ueberschrift1 = new Label("Aboverwaltung");// �berschrift
																// eingef�gt

	private Label untertitel = new Label(
			"Hier kannst du deine Abonnements verwalten.");// Beschreibung
															// eingef�gt

	// Vertical Panel f�r NutzerAbo
	private HorizontalPanel nutzerAboPanel = new HorizontalPanel();// Panel f�r
																	// Nutzerabos

	private Label ueberschriftNutzerAbo = new Label("Nutzerabo");

	// Nutzerabo hinzuf�gen
	private VerticalPanel nutzerAboPanel1 = new VerticalPanel();// Panel wird
																// erstellt

	private Label untertitelNutzerAbonnieren = new Label(
			"Hier kannst du die Nutzer ausw�hlen, die du abonnieren m�chtest");

	// Einf�gen der Dropdown-Liste um einen Nutzer auszuw�hlen, den man
	// ausw�hlen m�chte.
	private Label nutzerText1 = new Label("Nutzer ausw�hlen: ");

	// Button hinzuf�gen
	private Button nutzerAbonnieren = new Button("Nutzerabo hinzuf�gen");

	// Nutzerabo l�schen
	private VerticalPanel nutzerAboPanel2 = new VerticalPanel();// Panel wird
																// erstellt

	private Label untertitelNutzerAboLoeschen = new Label(
			"Hier kannst du die Nutzer ausw�hlen, f�r die du dein Abo l�schen m�chtest");

	private Label nutzerText2 = new Label("Nutzer ausw�hlen: ");

	private ListBox dropDownNutzerAbonnieren = new ListBox();

	private Button nutzerNichtAbonnieren = new Button("Nutzerabo l�schen");

	private HorizontalPanel hashtagAboPanel = new HorizontalPanel();// Panel f�r
																	// Hashtagabos

	private Label ueberschriftHashtagAbo = new Label("Hashtagabo");

	private VerticalPanel hashtagAboPanel1 = new VerticalPanel();// Panel wird
																	// erstellt

	private Label untertitelHashtagAbonnieren = new Label(
			"Hier kannst du die Hashtags ausw�hlen, die du abonnieren m�chtest");

	private Label hashtagText1 = new Label("Hashtag ausw�hlen: ");

	private ListBox getAbonnierteHashtagAboHinzufuegenListe = new ListBox();

	private NutzerAbo nA = new NutzerAbo();
	private HashtagAbo hA = new HashtagAbo();

	private Button hashtagAbonnieren = new Button("Hashtagabo hinzuf�gen");

	private VerticalPanel hashtagAboPanel2 = new VerticalPanel();// Panel wird
																	// erstellt

	private Label untertitelHashtagAboLoeschen = new Label(
			"Hier kannst du die Hashtags ausw�hlen, f�r die du dein Abo l�schen m�chtest");

	private Label hashtagText2 = new Label("Hashtag ausw�hlen: ");

	private ListBox dropDownHashtagsAbonniert = new ListBox();

	private Button hashtagNichtAbonnieren = new Button("Hashtagabo l�schen");

	public void onLoad() {
		nA.setLoginInfo(TellMe.eingeloggterBenutzer);
		hA.setLoginInfo(TellMe.eingeloggterBenutzer);
		// Aufbau der Seite
		// loadListBoxAbonnierteLoeschenNutzer();
		/*
		 * Buttons und Labels werden den jeweiligen Panels zugeordnet.
		 */
		aboPanel.add(ueberschrift1);// �berschrift f�r AboPanel
		aboPanel.add(untertitel);// Untertitel f�r AboPanel
		aboPanel.add(ueberschriftNutzerAbo);
		aboPanel.add(nutzerAboPanel);

		// Nutzeraboverwaltung zuweisen
		aboPanel.add(ueberschriftHashtagAbo);

		// Nutzerabo hinzuf�gen
		nutzerAboPanel.add(nutzerAboPanel1);
		nutzerAboPanel1.add(untertitelNutzerAbonnieren);
		nutzerAboPanel1.add(nutzerText1);

		// Dropdownliste&Button
		nutzerAboPanel1.add(nA.getZuAbonnierndeNutzerHinzufügenListe());
		nutzerAboPanel1.add(nA.AboErstellenButton());

		// Nutzerabo l�schen
		nutzerAboPanel.add(nutzerAboPanel2);
		nutzerAboPanel2.add(untertitelNutzerAboLoeschen);
		nutzerAboPanel2.add(nutzerText2);
		// Dropdownliste&Button
		nutzerAboPanel2.add(nA.getAbonnierteNutzerLoeschenListe());
		nutzerAboPanel2.add(nA.AboLoeschenButton());

		aboPanel.add(ueberschriftHashtagAbo);
		aboPanel.add(hashtagAboPanel);
		// Hashtagaboverwaltung zuweisen

		// Hashtagabo hinzuf�gen
		hashtagAboPanel.add(hashtagAboPanel1);
		hashtagAboPanel1.add(untertitelHashtagAbonnieren);
		hashtagAboPanel1.add(hashtagText1);
		// Dropdownliste&Button
		hashtagAboPanel1.add(hA.getAbonnierteHashtagAboHinzufuegenListe());
		hashtagAboPanel1.add(hA.HashtagAboErstellen());

		// Hashtagabo l�schen
		hashtagAboPanel.add(hashtagAboPanel2);
		hashtagAboPanel2.add(untertitelHashtagAboLoeschen);
		hashtagAboPanel2.add(hashtagText2);
		// Dropdownliste&Button
		hashtagAboPanel2.add(hA.getAbonnerteHashtagAboLoeschenListe());
		hashtagAboPanel2.add(hA.HashtagAboLoeschenButton());

		RootPanel.get("content").add(aboPanel);

	}

}