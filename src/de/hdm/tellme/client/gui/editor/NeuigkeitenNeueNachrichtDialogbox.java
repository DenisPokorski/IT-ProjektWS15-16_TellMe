package de.hdm.tellme.client.gui.editor;

import java.util.ArrayList;
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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;

public class NeuigkeitenNeueNachrichtDialogbox extends DialogBox {

	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	final MultiWordSuggestOracle suggestOracleEmpfaenger = new MultiWordSuggestOracle();
	Vector<Nutzer> moeglicheEmpfaenger = new Vector<Nutzer>();
	Vector<Nutzer> AusgewaehlteEmpfaenger = new Vector<Nutzer>();

	final MultiWordSuggestOracle suggestOracleHashtags = new MultiWordSuggestOracle();
	Vector<Hashtag> moeglicheHashtags = new Vector<Hashtag>();
	Vector<Hashtag> AusgewaehlteHashtags = new Vector<Hashtag>();

	public NeuigkeitenNeueNachrichtDialogbox() {
		ladVorschlagListen();

		// Dialog und FlowPanel definition
		setText("Neue Nachricht verfassen");
		setAnimationEnabled(true);
		setGlassEnabled(true);

		FlowPanel fpDialog = new FlowPanel();
		fpDialog.setHeight("500px");
		fpDialog.setWidth("500px");

		// Empfaenger Panele
		final HorizontalPanel hpAusgewaehlteEmpfanger = new HorizontalPanel();
		HorizontalPanel hpEmpfaenger = new HorizontalPanel();
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

				// Wenn Text Valide ist, füge einen Button zum Entfernen hinzu.
				// Ansonsten Fehlermeldung ausgeben
				if (bereitsHinzugefuegt) {

				} else if (zuHinzuzufuegenderBenutzer == null) {
					Window.alert("Kein vorhandener Benutzer ausgewaehlt.");
				} else {
					AusgewaehlteEmpfaenger.addElement(zuHinzuzufuegenderBenutzer);
					final Button btnLoescheEmpfaenger = new Button(gibVorschlageTextFuerNutzer(zuHinzuzufuegenderBenutzer) + "(X)");
					btnLoescheEmpfaenger.setStylePrimaryName("ButtonX-Nachrichtenverwaltung");

					btnLoescheEmpfaenger.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							// Wenn ein Benutzer in der AusgewaehlteEmpfaenger
							// Liste mit Vorschlagtext des Entfernenbutton,
							// entferne button von Panel und Nutzer von Liste
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

					hpAusgewaehlteEmpfanger.add(btnLoescheEmpfaenger);
				}

				EmpfaengerHinzufuegenSug.setText("");

			}
		});
		fpDialog.add(hpEmpfaenger);
		fpDialog.add(hpAusgewaehlteEmpfanger);

		// Textarea
		TextArea textArea = new TextArea();
		textArea.setWidth("90%");
		textArea.setVisibleLines(10);
		fpDialog.add(textArea);

		// Hashtag Panele
		HorizontalPanel hpHashtags = new HorizontalPanel();
		final HorizontalPanel hpAusgewaehlteHashtags = new HorizontalPanel();
		hpHashtags.add(new Label("Hashtags:"));

		final SuggestBox HastagHinzufuegenSug = new SuggestBox(suggestOracleHashtags);
		hpHashtags.add(HastagHinzufuegenSug);

		final Button btnHashtagHinzufuegen = new Button("+ Hinzufügen");
		hpHashtags.add(btnHashtagHinzufuegen);

		btnHashtagHinzufuegen.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Hashtag zuHinzuzufuegenderHashtag = null;
				boolean bereitsHinzugefuegt = false;
				// Überprüfe, ob Text in Suggestionbox mit Text eines
				// Vorhandenen Hashtags
				// übereinstimmt. Falls ja setzte speichere Hastag in
				// zuHinzuzufuegenderHashtag
				for (Hashtag hashtag : moeglicheHashtags) {
					if (HastagHinzufuegenSug.getText().equals("#" + hashtag.getSchlagwort())) {
						zuHinzuzufuegenderHashtag = hashtag;
						continue;
					}
				}

				// überprüfe, ob Hashtag schon vorhanden
				for (Hashtag hashtag2 : AusgewaehlteHashtags) {

					if (zuHinzuzufuegenderHashtag == hashtag2)
						bereitsHinzugefuegt = true;

				}

				// Wenn Text Valide ist, füge einen Button zum Entfernen hinzu.
				// Ansonsten Fehlermeldung ausgeben
				if (bereitsHinzugefuegt) {

				} else if (zuHinzuzufuegenderHashtag == null) {
					Window.alert("Kein vorhandener Hashtag ausgewaehlt.");
				} else {
					AusgewaehlteHashtags.addElement(zuHinzuzufuegenderHashtag);
					final Button btnLoescheHashtag = new Button("#" + zuHinzuzufuegenderHashtag.getSchlagwort() + "(X)");
					btnLoescheHashtag.setStylePrimaryName("ButtonX-Nachrichtenverwaltung");

					btnLoescheHashtag.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							// Wenn ein Hashtag in der AusgewaehlteHashtag
							// Liste mit Text des Entfernenbutton,
							// entferne button von Panel und Hashtag von Liste
							// der Ausgewählten Hastags
							for (Hashtag hashtag : AusgewaehlteHashtags) {
								if (btnLoescheHashtag.getText().equals("#" + hashtag.getSchlagwort() + "(X)")) {
									AusgewaehlteHashtags.remove(hashtag);
									btnLoescheHashtag.removeFromParent();
									continue;
								}
							}
						}
					});

					hpAusgewaehlteHashtags.add(btnLoescheHashtag);
				}
				HastagHinzufuegenSug.setText("");
			}
		});
		fpDialog.add(hpHashtags);
		fpDialog.add(hpAusgewaehlteHashtags);

		HorizontalPanel hpButton = new HorizontalPanel();
		hpButton.setWidth("100%");
		hpButton.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		Button btnSenden = new Button("Senden");
		btnSenden.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				NeuigkeitenNeueNachrichtDialogbox.this.hide();
			}
		});

		Button btnAbbrechen = new Button("Abbrechen");
		btnAbbrechen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				NeuigkeitenNeueNachrichtDialogbox.this.hide();
			}
		});
		hpButton.add(btnSenden);
		hpButton.add(btnAbbrechen);
		fpDialog.add(hpButton);
		setWidget(fpDialog);
	}

	private void ladVorschlagListen() {
		asyncObj.getAlleNutzerAußerMeineId(TellMe.eingeloggterBenutzer.getUser().getId(), new AsyncCallback<Vector<Nutzer>>() {
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

		asyncObj.gibHashtagListe(new AsyncCallback<Vector<Hashtag>>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Hashtag> resultListe) {
				moeglicheHashtags = resultListe;
				for (Hashtag hashtag : moeglicheHashtags) {
					suggestOracleHashtags.add("#" + hashtag.getSchlagwort());
				}
			}
		});
	}

	private String gibVorschlageTextFuerNutzer(Nutzer _nutzer) {
		return _nutzer.getVorname() + " " + _nutzer.getNachname() + " (" + _nutzer.getMailadresse() + ")";
	}

}