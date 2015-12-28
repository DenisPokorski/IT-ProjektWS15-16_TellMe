package de.hdm.tellme.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.tellme.client.gui.editor.HashtagZelle;
import de.hdm.tellme.client.gui.editor.NeuigkeitenJaNeinDialogbox;
import de.hdm.tellme.client.gui.editor.NeuigkeitenNachrichtDialogbox;
import de.hdm.tellme.client.gui.editor.NeuigkeitenNachrichtenBaumModel;
import de.hdm.tellme.client.gui.editor.NeuigkeitenTeilnehmerBearbeitenDialogbox;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class NeuigkeitenEditor extends VerticalPanel {

	private static Unterhaltung ausgewaehlteUnterhaltung;
	private static  Nachricht ausgewaehlteNachricht;
	
	public NeuigkeitenEditor() {
	}

	public void FilterNachBenutzer(NutzerZelle.ZellenObjekt nah) {
		Window.alert("Nachrichten werden nach Benutzer " + nah.nutzer.getVorname());
	}

	public void FilterNachHashtag(HashtagZelle.ZellenObjekt nah) {
		Window.alert("Nachrichten werden nach Benutzer " + nah.hashtag.getSchlagwort());
	}

	public void onLoad() {

		TreeViewModel model = new NeuigkeitenNachrichtenBaumModel();
		/*
		 * Create the tree using the model. We use <code>null</code> as the
		 * default value of the root node. The default value will be passed to
		 * CustomTreeModel#getNodeInfo();
		 */
		CellTree tree = new CellTree(model, null);
		tree.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		ScrollPanel scPanel = new ScrollPanel();
		scPanel.setHeight("800px");
		scPanel.add(tree);

		RootPanel.get("content_right").add(scPanel);

		setzeOptionenButton(null, null);
	}

	public static void showDialogBox(DialogBox anzuzeigendeBox) {
		anzuzeigendeBox.center();
		anzuzeigendeBox.show();
	}

	public static void setzeOptionenButton(Unterhaltung _ausgewaehlteUnterhaltung, Nachricht _ausgewaehlteNachricht) {
		RootPanel.get("ButtonBar").clear();

		HorizontalPanel hpUnterhaltungsOptionen = new HorizontalPanel();
		HorizontalPanel hpNachrichtenOptionen = new HorizontalPanel();

		Button btnAntworten = new Button("Unterhaltung beantworten");
		btnAntworten.addClickHandler(btnAntwortenClickHandler);
		hpUnterhaltungsOptionen.add(btnAntworten);

		Button btnTeilnehmerBearbeiten = new Button("Teilnehmer bearbeiten");
		btnTeilnehmerBearbeiten.addClickHandler(btnTeilnehmerBearbeitenClickHandler);
		hpUnterhaltungsOptionen.add(btnTeilnehmerBearbeiten);

		Button btnUnterhaltungVerlassen = new Button("Unterhaltung verlassen");
		btnUnterhaltungVerlassen.addClickHandler(btnUnterhaltungVerlassenClickHandler);
		hpUnterhaltungsOptionen.add(btnUnterhaltungVerlassen);

		Button btnNeueNachricht = new Button("Neue Nachricht verfassen");
		btnNeueNachricht.addClickHandler(btnNeueNachrichtClickHandler);
		hpNachrichtenOptionen.add(btnNeueNachricht);
		
		Button btnNachrichtBearbeiten = new Button("Nachricht bearbeiten");
		btnNachrichtBearbeiten.addClickHandler(btnNachrichtBearbeitenClickHandler);
		hpNachrichtenOptionen.add(btnNachrichtBearbeiten);
		
		Button btnNachrichtLoeaschen = new Button("Nachricht löschen");
		btnNachrichtLoeaschen.addClickHandler(btnNachrichtLoeschenClickHandler);
		hpNachrichtenOptionen.add(btnNachrichtLoeaschen);

		if (_ausgewaehlteUnterhaltung == null) {
			ausgewaehlteUnterhaltung = null;
			btnAntworten.setEnabled(false);
			btnTeilnehmerBearbeiten.setEnabled(false);
			btnUnterhaltungVerlassen.setEnabled(false);
			

		} else {
			ausgewaehlteUnterhaltung = _ausgewaehlteUnterhaltung;
			btnAntworten.setEnabled(true);
			btnTeilnehmerBearbeiten.setEnabled(true);
			btnUnterhaltungVerlassen.setEnabled(true);

		}

		if (_ausgewaehlteNachricht == null) {
			ausgewaehlteNachricht = null;
			btnNachrichtBearbeiten.setEnabled(false);
			btnNachrichtLoeaschen.setEnabled(false);
		} else {
			ausgewaehlteNachricht = _ausgewaehlteNachricht;
			btnNachrichtBearbeiten.setEnabled(true);
			btnNachrichtLoeaschen.setEnabled(true);
		}

		RootPanel.get("ButtonBar").add(hpNachrichtenOptionen);
		RootPanel.get("ButtonBar").add(hpUnterhaltungsOptionen);
	}

	static ClickHandler btnNeueNachrichtClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox().getNeueNachrichtDialogbox());
		}
	};
	static ClickHandler btnAntwortenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox().getAntwortNachrichtDialogbox(ausgewaehlteUnterhaltung));
		}
	};
	static ClickHandler btnNachrichtBearbeitenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox().getNachrichtBearbeitenDialogbox(ausgewaehlteNachricht));
		}
	};
	static ClickHandler btnNachrichtLoeschenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenJaNeinDialogbox().getNachrichtLoeschenDialogBox(ausgewaehlteNachricht));
		}
	};
	static ClickHandler btnTeilnehmerBearbeitenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenTeilnehmerBearbeitenDialogbox().getTeilnehmerBearbeiten(ausgewaehlteUnterhaltung));
		}
	};
	static ClickHandler btnUnterhaltungVerlassenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenJaNeinDialogbox().getUnterhaltungVerlassenDialogBox(ausgewaehlteUnterhaltung));
		}
	};

}
