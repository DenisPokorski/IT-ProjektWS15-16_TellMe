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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class NeuigkeitenTeilnehmerBearbeitenDialogbox {

	final DialogBox db = new DialogBox();
	TextArea textArea = new TextArea();
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	// Ausgelagert, da sie durch einen async call gefüllt werden
	final MultiWordSuggestOracle suggestOracleEmpfaenger = new MultiWordSuggestOracle();
	Vector<Nutzer> moeglicheEmpfaenger = new Vector<Nutzer>();
	Vector<Nutzer> AusgewaehlteEmpfaenger = new Vector<Nutzer>();

	// Panel für Nachricht Empfänger Dialog Box
	final FlowPanel fpAusgewaehlteEmpfanger = new FlowPanel();
	HorizontalPanel hpEmpfaenger = new HorizontalPanel();

	// Elemente, die von jedem Nachrichtemodus unterschiedlich befüllt werden
	private String boxTitel;
	private String textFunktionsbutton = "";

	private Unterhaltung zuBearbeitendeUnterhaltung;

	public DialogBox getTeilnehmerBearbeiten(Unterhaltung _unterhaltungZumBearbeiten) {
		boxTitel = "Teilnehmer bearbeiten";
		textFunktionsbutton = "Speichern";

		zuBearbeitendeUnterhaltung = _unterhaltungZumBearbeiten;

		// Fuelle TeilehmerPanel
		for (Nutzer zuHinzuzufuegenderBenutzer : _unterhaltungZumBearbeiten.getTeilnehmer()) {

			AusgewaehlteEmpfaenger.addElement(zuHinzuzufuegenderBenutzer);
			final Button btnLoescheEmpfaenger = new Button(gibVorschlageTextFuerNutzer(zuHinzuzufuegenderBenutzer) + "(X)");
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
						if (btnLoescheEmpfaenger.getText().equals(gibVorschlageTextFuerNutzer(nutzer) + "(X)")) {
							AusgewaehlteEmpfaenger.remove(nutzer);
							btnLoescheEmpfaenger.removeFromParent();
							continue;
						}
					}

				}
			});

			fpAusgewaehlteEmpfanger.add(btnLoescheEmpfaenger);
		}

		return gibDialogBox();
	}

	private DialogBox gibDialogBox() {
		ladVorschlagListen();

		// Dialog und FlowPanel definition
		db.setText(boxTitel);
		db.setAnimationEnabled(true);
		db.setGlassEnabled(true);

		FlowPanel fpDialog = new FlowPanel();
		// fpDialog.setHeight("500px");
		// fpDialog.setWidth("500px");

		// ############################################## Empfaenger Panele
		// ##############################################

		hpEmpfaenger.add(new Label("Empfänger:"));

		final SuggestBox EmpfaengerHinzufuegenSug = new SuggestBox(suggestOracleEmpfaenger);
		hpEmpfaenger.add(EmpfaengerHinzufuegenSug);

		final Button btnEmpfaengerHinzufuegen = new Button("+ Hinzufügen");
		hpEmpfaenger.add(btnEmpfaengerHinzufuegen);

		btnEmpfaengerHinzufuegen.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Nutzer zuHinzuzufuegenderBenutzer = null;
				boolean bereitsHinzugefuegt = false;

				// Überprüfe, ob Text in Suggestionbox mit Text (Vorname
				// Nachname (Email)) eines Vorhandenen Benutzers
				// übereinstimmt. Falls ja setzte speichere Nutzerobjekt in
				// zuHinzuzufuegenderBenutzer
				for (Nutzer einzelnerNutzer : moeglicheEmpfaenger) {
					if (EmpfaengerHinzufuegenSug.getText().equals(gibVorschlageTextFuerNutzer(einzelnerNutzer))) {
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
					Window.alert("Kein vorhandener Benutzer ausgewaehlt.");
				} else {
					AusgewaehlteEmpfaenger.addElement(zuHinzuzufuegenderBenutzer);
					final Button btnLoescheEmpfaenger = new Button(gibVorschlageTextFuerNutzer(zuHinzuzufuegenderBenutzer) + "(X)");
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
								if (btnLoescheEmpfaenger.getText().equals(gibVorschlageTextFuerNutzer(nutzer) + "(X)")) {
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
		fpDialog.add(hpEmpfaenger);
		fpDialog.add(fpAusgewaehlteEmpfanger);

		HorizontalPanel hpButtons = new HorizontalPanel();
		hpButtons.setWidth("100%");
		hpButtons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		Button btnFunktionsbutton = new Button(textFunktionsbutton);
		btnFunktionsbutton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert("Teilnehmer bearbeitet!");

				db.hide();
			}
		});
		Button btnAbbrechen = new Button("Abbrechen");
		btnAbbrechen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				db.hide();
			}
		});
		hpButtons.add(btnFunktionsbutton);
		hpButtons.add(btnAbbrechen);
		fpDialog.add(hpButtons);

		db.setWidget(fpDialog);

		return db;
	}

	private void ladVorschlagListen() {
		
		
		asyncObj.getAlleNutzer(false,new AsyncCallback<Vector<Nutzer>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Nutzer> resultListe) {
				moeglicheEmpfaenger = resultListe;
				for (Nutzer einzelnerUser : moeglicheEmpfaenger) {
					suggestOracleEmpfaenger.add(gibVorschlageTextFuerNutzer(einzelnerUser));
				}

			}
		});

	}

	private String gibVorschlageTextFuerNutzer(Nutzer _nutzer) {
		return _nutzer.getVorname() + " " + _nutzer.getNachname() + " (" + _nutzer.getMailadresse() + ")";
	}

}