package de.hdm.tellme.client.Schaukasten;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.HashtagCellList;
import de.hdm.tellme.client.gui.editor.HashtagZelle;
import de.hdm.tellme.client.gui.editor.NeuigkeitenJaNeinDialogbox;
import de.hdm.tellme.client.gui.editor.NeuigkeitenNachrichtDialogbox;
import de.hdm.tellme.client.gui.editor.NeuigkeitenNachrichtenBaumModel;
import de.hdm.tellme.client.gui.editor.NeuigkeitenTeilnehmerBearbeitenDialogbox;
import de.hdm.tellme.client.gui.editor.NutzerCellList;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * 
 * Die Klasse <class> NeuigkeitenEditor </class> zeigt alle aktuellen
 * Unterhaltungen, genauer gesagt Nachrichten, die Nutzer-Celllist, die
 * Hashtag-Celllist und ein ein AuswahlPanel der für die jeweiligen Nachrichten
 * enstprechende Buttons (Unterhaltung beantworten, Teilnehmer bearbeiten,
 * Unterhaltung verlassen, Nachricht bearbeiten, Nachricht löschen) zur
 * Verfügung stellt. Außerdem gibt es gesondert positioniert zwei weitere
 * Buttons (Neue Nachricht, Aktualisieren).
 * 
 * @author denispokorski
 *
 */
public class NeuigkeitenEditor extends VerticalPanel {

	/**
	 * 
	 */
	private static Unterhaltung ausgewaehlteUnterhaltung;
	private static Nachricht ausgewaehlteNachricht;

	private static HashtagCellList hsCL = new HashtagCellList();
	private static NutzerCellList nuCL = new NutzerCellList();

	public NeuigkeitenEditor() {
	}

	public void FilterNachBenutzer(NutzerZelle.ZellenObjekt nah) {
	}

	public void FilterNachHashtag(HashtagZelle.ZellenObjekt nah) {
	}

	/**
	 * Die onLoad Methode wird ausgeführt, wenn ein neues Objekt der Klasse
	 * "NutzerBearbeiten" erstellt wird. Es werden in den Textboxen die Daten
	 * des eingeloggten Nutzers gesetzt. Des Weiteren werden die Widgets den
	 * Paneln zugeordnet. In diesem Bereich werden den Widgets zusätzlich
	 * CSS-Styles zugeordnet.
	 */
	public void onLoad() {

		/*
		 * 
		 */
		setzeOptionenButton(null, null);

		TreeViewModel model = new NeuigkeitenNachrichtenBaumModel();
		/*
		 * Create the tree using the model. We use <code>null</code> as the
		 * default value of the root node. The default value will be passed to
		 * CustomTreeModel#getNodeInfo();
		 */
		CellTree tree = new CellTree(model, null);
		tree.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		ScrollPanel scPanel = new ScrollPanel();
		scPanel.setHeight("100%");
		scPanel.add(tree);

		RootPanel.get("content_right").add(scPanel);

	}

	public static void showDialogBox(DialogBox anzuzeigendeBox) {
		anzuzeigendeBox.center();
		anzuzeigendeBox.show();
	}

	/**
	 * 
	 * Die Methode <code>setzeOptionenButton </code> gibt die Auswahlbuttons
	 * frei wenn eine Unterhaltung/Nachricht angeklickt worden ist
	 * 
	 * @param _ausgewaehlteUnterhaltung
	 * @param _ausgewaehlteNachricht
	 */
	public static void setzeOptionenButton(
			Unterhaltung _ausgewaehlteUnterhaltung,
			Nachricht _ausgewaehlteNachricht) {
		RootPanel.get("ButtonBar").clear();

		// ###################### Nachrichtenoptionen

		/**
		 * Es wird ein neues Horizontal Panel erstellt und
		 */
		HorizontalPanel hpHeadline = new HorizontalPanel();
		hpHeadline.setStylePrimaryName("hpHeadline");
		hpHeadline.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		HorizontalPanel hpUnterhaltungsOptionen = new HorizontalPanel();

		HTML headline = new HTML(" <h2>Alle Neuigkeiten</h2> ");
		hpHeadline.add(headline);

		/**
		 * Ein neuer Button mit dem Namen "Neue Nachricht" wird erstellt und ein
		 * CSS-Style hinzugefügt. Ein ClickHandler wird hinzugefügt und der
		 * Button wird dem HorizontalPanel hpHeadline hinzugefügt.
		 */

		Button btnNeueNachricht = new Button("Neue Nachricht");
		btnNeueNachricht.setStylePrimaryName("neueNchrichtBtn");
		btnNeueNachricht.addClickHandler(btnNeueNachrichtClickHandler);
		hpHeadline.add(btnNeueNachricht);

		/**
		 * Neuer Button mit dem Namen "Aktualisieren" wird erstellt und ein
		 * CSS-Style hinzugefügt. Ein ClickHandler wird hinzugefügt und der
		 * Button wird dem HorizontalPanel hpHeadline hinzugefügt.
		 */
		Button btnAktualisieren = new Button("Aktualisieren");
		btnAktualisieren.setStylePrimaryName("aktualisierenBtn");
		btnAktualisieren
				.addClickHandler(btnUnterhaltungenAktualisierenClickHandler);
		hpHeadline.add(btnAktualisieren);

		/**
		 * Ein neues HorziontalPanel wird hinzugefügt "hpOptionen" und durch ein
		 * CSS-Style optisch verändert. Die Position wird auf links verschoben.
		 * TODO
		 */
		HorizontalPanel hpOptionen = new HorizontalPanel();
		hpOptionen.setStylePrimaryName("hpOptionenButtonbar");
		hpOptionen.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		/**
		 * Neuer Button mit dem Namen "Unterhaltung beantworten" wird erstellt
		 * und ein CSS-Style hinzugefügt. Ein ClickHandler wird hinzugefügt und
		 * der Button wird dem HorizontalPanel hpOptionen hinzugefügt.
		 */

		Button btnAntworten = new Button("Unterhaltung beantworten");
		btnAntworten.setEnabled(false);
		btnAntworten.setStylePrimaryName("btnUnterhaltungVerlassen");
		btnAntworten.addClickHandler(btnAntwortenClickHandler);
		hpOptionen.add(btnAntworten);

		/**
		 * Ein neuer Button mit dem Namen "Teilnehmer bearbeiten" wird erstellt
		 * und ein CSS-Style hinzugefügt. Ein ClickHandler wird hinzugefügt und
		 * der Button wird den beiden HorizontalPanel hpUnterhaltungsOptionen
		 * und hpOptionen hinzugefügt.
		 */

		Button btnTeilnehmerBearbeiten = new Button("Teilnehmer bearbeiten");
		btnTeilnehmerBearbeiten.setStylePrimaryName("btnTeilnehmerBearbeiten");
		btnTeilnehmerBearbeiten
				.addClickHandler(btnTeilnehmerBearbeitenClickHandler);
		hpUnterhaltungsOptionen.add(btnTeilnehmerBearbeiten);
		hpOptionen.add(btnTeilnehmerBearbeiten);

		/**
		 * Ein neuer Button mit dem Namen "Unterhaltung verlassen" wird erstellt
		 * und ein CSS-Style hinzugefügt. Ein ClickHandler wird hinzugefügt und
		 * der Button wird dem HorizontalPanel hpOptionen hinzugefügt.
		 */

		Button btnUnterhaltungVerlassen = new Button("Unterhaltung verlassen");
		btnUnterhaltungVerlassen
				.setStylePrimaryName("btnUnterhaltungVerlassen");
		btnUnterhaltungVerlassen
				.addClickHandler(btnUnterhaltungVerlassenClickHandler);
		hpOptionen.add(btnUnterhaltungVerlassen);

		/**
		 * Ein neuer Button mit dem Namen "Unterhaltung bearbeiten" wird
		 * erstellt und ein CSS-Style hinzugefügt. Ein ClickHandler wird
		 * hinzugefügt und der Button wird dem HorizontalPanel hpOptionen
		 * hinzugefügt.
		 */

		Button btnNachrichtBearbeiten = new Button("Nachricht bearbeiten");
		btnNachrichtBearbeiten
				.addClickHandler(btnNachrichtBearbeitenClickHandler);
		btnNachrichtBearbeiten.setStylePrimaryName("btnUnterhaltungVerlassen");
		hpOptionen.add(btnNachrichtBearbeiten);

		/**
		 * Ein neuer Button mit dem Namen "Unterhaltung löschen" wird erstellt
		 * und ein CSS-Style hinzugefügt. Ein ClickHandler wird hinzugefügt und
		 * der Button wird dem HorizontalPanel hpOptionen hinzugefügt.
		 */

		Button btnNachrichtLoeaschen = new Button("Nachricht löschen");
		btnNachrichtLoeaschen.setStylePrimaryName("btnUnterhaltungVerlassen");
		btnNachrichtLoeaschen.addClickHandler(btnNachrichtLoeschenClickHandler);
		hpOptionen.add(btnNachrichtLoeaschen);

		/**
		 * Wenn keine Nachricht ausgewählt wurde, dann kann keine Nachricht
		 * bearbeitet oder gelöscht werden.
		 */
		if (_ausgewaehlteNachricht == null) {
			ausgewaehlteNachricht = null;
			btnNachrichtBearbeiten.setEnabled(false);
			btnNachrichtLoeaschen.setEnabled(false);

			/**
			 * Wurde eine Nachricht ausgewählt, dann kann eine Nachricht
			 * bearbeitet und gelöscht weden.
			 */
		} else {
			ausgewaehlteNachricht = _ausgewaehlteNachricht;
			btnNachrichtBearbeiten.setEnabled(true);
			btnNachrichtLoeaschen.setEnabled(true);
		}

		/**
		 * Wenn keine Unterhaltung ausgewählt wurde, dann kann keine
		 * Unterhaltung bearbeitet oder gelöscht werden.
		 */
		if (_ausgewaehlteUnterhaltung == null) {
			ausgewaehlteUnterhaltung = null;
			btnAntworten.setEnabled(false);
			btnTeilnehmerBearbeiten.setEnabled(false);
			btnUnterhaltungVerlassen.setEnabled(false);

			/**
			 * Wurde eine Unterhaltung ausgewählt, dann kann eine Unterhaltung
			 * bearbeitet und gelöscht weden. Wenn man Teilnehmer einer
			 * Unterhaltung ist, kann man diese verlassen. Ansonsten wird der
			 * Button ausgegraut.
			 */
		} else {
			ausgewaehlteUnterhaltung = _ausgewaehlteUnterhaltung;
			boolean istTeilnehmerInUnterhaltung = false;
			btnAntworten.setEnabled(true);

			for (Nutzer nutzer : ausgewaehlteUnterhaltung.getTeilnehmer()) {
				if (nutzer.getId() == TellMe.gibEingeloggterBenutzer().getUser().getId())istTeilnehmerInUnterhaltung = true;
			}
			if (istTeilnehmerInUnterhaltung) {
				btnTeilnehmerBearbeiten.setEnabled(true);
				btnUnterhaltungVerlassen.setEnabled(true);
			} else {
				btnTeilnehmerBearbeiten.setEnabled(false);
				btnUnterhaltungVerlassen.setEnabled(false);
			}
		}

		/**
		 * 
		 * Der ButtonBar wird der hpHeadline und der hpOptionen hinzugefügt TODO
		 */
		RootPanel.get("ButtonBar").add(hpHeadline);
		RootPanel.get("ButtonBar").add(hpOptionen);
	}

	/**
	 * 
	 * Die Methode <code> gibFilterPanel()</code> ordnet in einem VerticalPanel
	 * zwei Celllisten und ein Button an
	 * 
	 * @return vpFilterPanel
	 */

	public static VerticalPanel gibFilterPanel() {

		/**
		 * Ein neues VerticalPanel wird erstellt
		 */
		VerticalPanel vpFilterPanel = new VerticalPanel();

		/**
		 * Dem VerticalPanel vpFilterPanel wird die NutzerCelllist und
		 * HashtagCelllist hinzugefügt
		 */
		vpFilterPanel.add(nuCL.generiereCellList(CellListModus.Nachrichtenuebersicht, 0));
		vpFilterPanel.add(hsCL.generiereCellList(CellListModus.Nachrichtenuebersicht));

		/**
		 * Ein neuer Button mit dem Namen "Filter zurücksetzen" wird erstellt
		 * und ein CSS-Style hinzugefügt. Ein ClickHandler wird hinzugefügt und
		 * der Button wird dem VerticalPanel vpFilterPanel hinzugefügt.
		 */

		Button btnFilterZuruecksetzen = new Button("Filter zurücksetzen");
		btnFilterZuruecksetzen.setStylePrimaryName("filterBtn");
		btnFilterZuruecksetzen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				NeuigkeitenNachrichtenBaumModel.setzeKeinenFilter();
			}
		});
		vpFilterPanel.add(btnFilterZuruecksetzen);
		return vpFilterPanel;
	}

	/**
	 * Um eine neue Nachricht zu schreiben klickt man auf den Button
	 * "Neue Nachricht". Es öffnet sich eine neue Dialogbox, die es einen
	 * ermöglicht, eine neue Nachricht zu schreiben
	 * 
	 */
	static ClickHandler btnNeueNachrichtClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox()
					.getNeueNachrichtDialogbox());
		}
	};

	/**
	 * Um eine Antwort zu schreiben klickt man auf den Button "Antworten". Es
	 * öffnet sich eine neue Dialogbox, die es einen ermöglicht, eine Antwort zu
	 * schreiben
	 * 
	 */
	static ClickHandler btnAntwortenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox()
					.getAntwortNachrichtDialogbox(ausgewaehlteUnterhaltung));
		}
	};

	/**
	 * Um eine Nachricht zu bearbeiten klickt man auf den Button "Bearbeiten".
	 * Es öffnet sich eine neue Dialogbox, die es einen ermöglicht, eine
	 * Nachricht zu beantworten
	 * 
	 */
	static ClickHandler btnNachrichtBearbeitenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox()
					.getNachrichtBearbeitenDialogbox(ausgewaehlteNachricht));
		}
	};

	/**
	 * Um eine Nachricht zu löschen klickt man auf den Button "Löschen". Es
	 * öffnet sich eine neue Dialogbox, die es einen ermöglicht, eine Nachricht
	 * zu löschen
	 * 
	 */
	static ClickHandler btnNachrichtLoeschenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenJaNeinDialogbox()
					.getNachrichtLoeschenDialogBox(ausgewaehlteNachricht));
		}
	};

	/**
	 * Um die Teilnehmer der Unterhaltung zu bearbeiten klickt man auf den
	 * Button "Teilnehmer bearbeiten". Es öffnet sich eine neue Dialogbox, die
	 * es einen ermöglicht, eine die Teilnehmer einer Unterhaltung zu bearbeiten
	 * 
	 */
	static ClickHandler btnTeilnehmerBearbeitenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor
					.showDialogBox(new NeuigkeitenTeilnehmerBearbeitenDialogbox()
							.getTeilnehmerBearbeiten(ausgewaehlteUnterhaltung));
		}
	};

	/**
	 * Um Unterhaltung zu verlassen klickt man auf den Button
	 * "Unterhaltung verlassen". Es öffnet sich eine neue Dialogbox, die es
	 * einen ermöglicht, eine Unterhaltung zu verlassen
	 * 
	 */
	static ClickHandler btnUnterhaltungVerlassenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor
					.showDialogBox(new NeuigkeitenJaNeinDialogbox()
							.getUnterhaltungVerlassenDialogBox(ausgewaehlteUnterhaltung));
		}
	};

	/**
	 * Um Unterhaltung zu aktualisieren klickt man auf den Button
	 * "Aktualisieren". Die Seite wird asynchron geladen, sodas neue
	 * Unterhaltungen angezeigt werden können
	 * 
	 */
	static ClickHandler btnUnterhaltungenAktualisierenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenNachrichtenBaumModel.ladeUnterhaltungenAsync();
		}
	};

}
