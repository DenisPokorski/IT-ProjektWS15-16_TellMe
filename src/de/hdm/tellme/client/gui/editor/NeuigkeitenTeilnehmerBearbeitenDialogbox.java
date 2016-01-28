package de.hdm.tellme.client.gui.editor;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Die Klasse <class> NeuigkeitenTeilnehmerBearbeitenDialogbox</class> zeigt ein
 * Dialogfenster an zum hinzufügen und entfernen von Teilnehmern
 * 
 * @author denispokorski
 *
 */
public class NeuigkeitenTeilnehmerBearbeitenDialogbox {

	/**
	 * Eine neues Dialogbox-Fenster wird erstellt
	 */
	final DialogBox db = new DialogBox();

	/**
	 * Asyncobj wird erstellt.
	 *
	 */
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	/**
	 * Eine neue MultiWordSuggestionBox wird erstellt. Zwei neue Vektor-Listen
	 * für mögliche Empfänger und ausgewählte Empfänger werden erstellt.
	 * 
	 * 
	 */
	/**
	 * Ausgelagert, da sie durch einen async call gefüllt werden
	 * 
	 */
	final MultiWordSuggestOracle suggestOracleEmpfaenger = new MultiWordSuggestOracle();
	Vector<Nutzer> moeglicheEmpfaenger = new Vector<Nutzer>();
	Vector<Nutzer> AusgewaehlteEmpfaenger = new Vector<Nutzer>();

	/**
	 * Panel für Nachricht Empfänger Dialog Box
	 */
	final FlowPanel fpAusgewaehlteEmpfanger = new FlowPanel();
	HorizontalPanel hpEmpfaenger = new HorizontalPanel();

	/**
	 * Elemente, die von jedem Nachrichtemodus unterschiedlich befüllt werden
	 */
	private String boxTitel;
	private String textFunktionsbutton = "";

	private Unterhaltung zuBearbeitendeUnterhaltung;

	/**
	 * Die Methode <code>getTeilnehmerBearbeiten </code> nimmt die ausgewählte
	 * Unterhaltung/Nachricht und lädt die Teilnehmer die zum hinzufügen
	 * vorhanden sind. Die zur Auwahl stehenden Teilnehmer werden hinzugefügt.
	 * Buttons zum hinzufügen, entfernen und zum schließen der Dialogbox werden
	 * hinzugefügt.
	 * 
	 * @param _unterhaltungZumBearbeiten
	 * @return
	 */
	public DialogBox getTeilnehmerBearbeiten(Unterhaltung _unterhaltungZumBearbeiten) {
		boxTitel = "Teilnehmer bearbeiten";
		textFunktionsbutton = "Speichern";

		zuBearbeitendeUnterhaltung = _unterhaltungZumBearbeiten;
		// Fuelle TeilehmerPanel
		for (Nutzer zuHinzuzufuegenderBenutzer : _unterhaltungZumBearbeiten.getTeilnehmer()) {

			/**
			 * Ausgewählte Teilnehmer werden der Unterhaltung/Nachricht
			 * hinzugefügt. Die bereits hinzugefügten Teilnehmer sind als Button
			 * zum entfernen der Teilnehmer durch eine Stringerweiterung "(X)".
			 * Dieser Button wird mittels CSS-Style optisch verändert.
			 *
			 */

			AusgewaehlteEmpfaenger.addElement(zuHinzuzufuegenderBenutzer);
			final Button btnLoescheEmpfaenger = new Button(gibVorschlageTextFuerNutzer(zuHinzuzufuegenderBenutzer)+ "(X)");
			btnLoescheEmpfaenger.setStylePrimaryName("ButtonX-Nachrichtenverwaltung");

			/**
			 * Ein ClickHandler um den Teilnehmer zu entfernen wird aufgerufen
			 */
			btnLoescheEmpfaenger.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					// Wenn ein Benutzer in der
					// AusgewaehlteEmpfaenger
					// Liste mit Vorschlagtext des Entfernenbutton,
					// entferne button von Panel und Nutzer von
					// Liste
					// der Ausgewählten Empfänger
					for (Nutzer nutzer : AusgewaehlteEmpfaenger) {
						if (btnLoescheEmpfaenger.getText().equals(gibVorschlageTextFuerNutzer(nutzer) + "(X)")) {
							AusgewaehlteEmpfaenger.remove(nutzer);
							btnLoescheEmpfaenger.removeFromParent();
							continue;
						}
					}

				}
			});
			if (zuHinzuzufuegenderBenutzer.getId() != TellMe.gibEingeloggterBenutzer().getUser().getId())
				fpAusgewaehlteEmpfanger.add(btnLoescheEmpfaenger);
		}

		return gibDialogBox();
	}

	private DialogBox gibDialogBox() {
		ladVorschlagListen();

		/**
		 * Dialog und FlowPanel werden definiert
		 */
		db.setText(boxTitel);
		db.setAnimationEnabled(true);
		db.setGlassEnabled(true);

		/**
		 * Ein neues FlowPanel wird erstellt mit der Breite 100%
		 */
		FlowPanel fpDialog = new FlowPanel();

		fpDialog.setWidth("100%");

		/**
		 * Ein neues Bild wird hinzugefügt der als Abbrech-Button fungiert. Ein
		 * ClickHandler um die Dialogbox zu schließen wird aufgerufen.
		 */
		Image btnAbbrechen = new Image("xbtn.png");

		btnAbbrechen.setStylePrimaryName("xbtn");

		btnAbbrechen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				db.hide();
			}
		});

		fpDialog.add(btnAbbrechen);

		// ############################################## Empfaenger Panele
		// ##############################################

		/**
		 * Eine neue SuggestBox wird hinzugefügt die eine Auswahl an
		 * Teilnehmer,durch Multi-Word-Vorschläge, anzeigt, die hinzugefügt
		 * werden können.
		 * 
		 * 
		 */
		final SuggestBox EmpfaengerHinzufuegenSug = new SuggestBox(suggestOracleEmpfaenger);
		hpEmpfaenger.add(EmpfaengerHinzufuegenSug);

		/**
		 * Es wird ein neuer Button "+ Empfänger" erstellt und mit einem
		 * CSS-Style ergänzt
		 */
		final Button btnEmpfaengerHinzufuegen = new Button("+ Empfänger");
		btnEmpfaengerHinzufuegen.setStylePrimaryName("EmpfaengerPlusBtn");
		hpEmpfaenger.add(btnEmpfaengerHinzufuegen);

		/**
		 * Ein Clickhandler wird verwendet, um den Nutzer als Teilnehmer
		 * hinzuzufügen.
		 * 
		 */
		btnEmpfaengerHinzufuegen.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Nutzer zuHinzuzufuegenderBenutzer = null;
				boolean bereitsHinzugefuegt = false;

				// Überprüfe, ob Text in Suggestionbox mit Text (Vorname
				// Nachname (Email)) eines Vorhandenen Benutzers
				// übereinstimmt. Falls ja setzte speichere Nutzerobjekt in
				// zuHinzuzufuegenderBenutzer
				for (Nutzer einzelnerNutzer : moeglicheEmpfaenger) {
					if (EmpfaengerHinzufuegenSug.getText().equals(
							gibVorschlageTextFuerNutzer(einzelnerNutzer))) {
						zuHinzuzufuegenderBenutzer = einzelnerNutzer;
						continue;
					}
				}

				// überprüfe, ob Empfänger schon vorhanden
				for (Nutzer nutzer : AusgewaehlteEmpfaenger) {
					if (zuHinzuzufuegenderBenutzer == nutzer)
						bereitsHinzugefuegt = true;

				}

				// Wenn Text Valide ist, füge einen Button zum Entfernen
				// hinzu.
				// Ansonsten Fehlermeldung ausgeben
				if (bereitsHinzugefuegt) {
					Window.alert("Der Empfänger kann nicht hinzugefügt werden, da dieser bereits hinzugefügt wurde.");

				} else if (zuHinzuzufuegenderBenutzer == null) {
					Window.alert("Keinen vorhandenen Benutzer ausgewählt.");
				} else {
					AusgewaehlteEmpfaenger
							.addElement(zuHinzuzufuegenderBenutzer);
					final Button btnLoescheEmpfaenger = new Button(gibVorschlageTextFuerNutzer(zuHinzuzufuegenderBenutzer)+ "(X)");
					btnLoescheEmpfaenger.setStylePrimaryName("ButtonX-Nachrichtenverwaltung");

					btnLoescheEmpfaenger.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							// Wenn ein Benutzer in der
							// AusgewaehlteEmpfaenger
							// Liste mit Vorschlagtext des Entfernenbutton,
							// entferne button von Panel und Nutzer von
							// Liste
							// der Ausgewählten Empfänger
							for (Nutzer nutzer : AusgewaehlteEmpfaenger) {
								if (btnLoescheEmpfaenger.getText().equals(gibVorschlageTextFuerNutzer(nutzer)+ "(X)")) {
									AusgewaehlteEmpfaenger.remove(nutzer);
									btnLoescheEmpfaenger.removeFromParent();
									continue;
								}
							}
						}
					});

					fpAusgewaehlteEmpfanger.add(btnLoescheEmpfaenger);
				}
				EmpfaengerHinzufuegenSug.setText("");
			}
		});

		/**
		 * Dem FlowPanel "fpDialog" das Horizontalpanel "hpEmpfaenger" und ein
		 * weiteres FlowPanel "fpAusgewaehlteEmpfaenger" hinzugefügt
		 * 
		 */
		fpDialog.add(hpEmpfaenger);
		fpDialog.add(fpAusgewaehlteEmpfanger);

		/**
		 * 
		 * Ein neues HorizontalPanel "hpButtons" wird erstellt und auf der
		 * rechten Seite positioniert mit 100% Breite.
		 * 
		 */
		HorizontalPanel hpButtons = new HorizontalPanel();
		hpButtons.setWidth("100%");
		hpButtons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		/**
		 * 
		 * Ein neuer Button "textFunktionsbuton" der den Namen "Speichern" in im
		 * Dialogbox hat. Der Button wird mittels CSS optisch verändert. Ein
		 * ClickHandler wird aufgerufen, um zu Speichern.
		 */
		Button btnFunktionsbutton = new Button(textFunktionsbutton);
		btnFunktionsbutton.setStylePrimaryName("NeuigkeitenDialogboxSendenBtn");
		btnFunktionsbutton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Unterhaltung aktualisierteTeilnehmerUnterhaltung = new Unterhaltung();
				aktualisierteTeilnehmerUnterhaltung.setId(zuBearbeitendeUnterhaltung.getId());
				aktualisierteTeilnehmerUnterhaltung.setTeilnehmer(AusgewaehlteEmpfaenger);
				aktualisierteTeilnehmerUnterhaltung.setUnterhaltungstyp(zuBearbeitendeUnterhaltung.getUnterhaltungstyp());
				aktualisierteTeilnehmerUnterhaltung.setSichtbarkeit(zuBearbeitendeUnterhaltung.getSichtbarkeit());
				
				asyncObj.UnterhaltungAktualisieren(zuBearbeitendeUnterhaltung, aktualisierteTeilnehmerUnterhaltung, new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim bearbeiten der Teilnehmer. Bitte wenden Sie sich an den Systemadministrator.");
					}

					@Override
					public void onSuccess(Boolean result) {
						Window.alert("Teilnehmer bearbeitet!");
					}
				});
				db.hide();
			}
		});

		/**
		 * "btnFunktionsbutton" wird dem HorizontalPanel "hpButtones"
		 * hinzugefügt. "hpButteons" wird dem FlowPanel "fpDialog"
		 */
		hpButtons.add(btnFunktionsbutton);
		fpDialog.add(hpButtons);
		db.setWidget(fpDialog);
		return db;
	}

	/**
	 * Die Methode <code>ladVorschlagListen() </code> ruft eine RPC-Methode auf,
	 * die einen Vector vom Nutzer-Objekten zurückgibt.
	 * 
	 */

	private void ladVorschlagListen() {

		asyncObj.getAlleNutzer(false, new AsyncCallback<Vector<Nutzer>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			/**
			 * 
			 * Bei Erfolg werden durch Multi-Word-Vorschläge die potenziellen
			 * Teilnehmer angezeigt.
			 * 
			 */
			@Override
			public void onSuccess(Vector<Nutzer> resultListe) {
				moeglicheEmpfaenger = resultListe;
				for (Nutzer einzelnerUser : moeglicheEmpfaenger) {
					if (einzelnerUser.getId() != TellMe.gibEingeloggterBenutzer().getUser().getId())
						suggestOracleEmpfaenger.add(gibVorschlageTextFuerNutzer(einzelnerUser));
				}
			}
		});
	}

	/**
	 * 
	 * Die Methode <code> gibVorschlageTextFuerNutzer </code> liefert vom Nutzer
	 * den Vornamen, Nachnamen und die E-Mail-Adresse zurück, um Vorschläge von
	 * Nutzern bei der Eingabe von einzelnen Buchstaben zurückzugeben.
	 * 
	 * @param _nutzer
	 * @return _nutzer.getVorname(); _nutzer.getNachname();
	 *         _nutzer.getMailadresse()
	 */
	private String gibVorschlageTextFuerNutzer(Nutzer _nutzer) {
		return _nutzer.getVorname() + " " + _nutzer.getNachname() + " ("+ _nutzer.getMailadresse() + ")";
	}
}