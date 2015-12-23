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

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;

public class NeuigkeitenNachrichtDialogbox {

	public enum eNachrichtenmodus {
		NeueNachricht, AntwortNachricht, BearbeitenNachricht;
	}

	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	// Ausgelagert, da sie durch einen async call gefüllt werden
	final MultiWordSuggestOracle suggestOracleEmpfaenger = new MultiWordSuggestOracle();
	Vector<Nutzer> moeglicheEmpfaenger = new Vector<Nutzer>();
	Vector<Nutzer> AusgewaehlteEmpfaenger = new Vector<Nutzer>();

	final MultiWordSuggestOracle suggestOracleHashtags = new MultiWordSuggestOracle();
	Vector<Hashtag> moeglicheHashtags = new Vector<Hashtag>();
	Vector<Hashtag> AusgewaehlteHashtags = new Vector<Hashtag>();
	

	final DialogBox db = new DialogBox();

	eNachrichtenmodus NachrichtenModus = null;

	// Elemente, die von jedem Nachrichtemodus unterschiedlich befüllt werden
	private String boxTitel;
	private String textAreaInhalt = "";
	private String textFunktionsbutton = "";

	public DialogBox getNeueNachrichtDialogbox() {
		NachrichtenModus = eNachrichtenmodus.NeueNachricht;
		boxTitel = "Neue Nachricht verfassen";
		textFunktionsbutton = "Senden";

		return gibDialogBox();
	}

	public DialogBox getAntwortNachrichtDialogbox() {
		NachrichtenModus = eNachrichtenmodus.AntwortNachricht;
		boxTitel = "Neue Nachricht verfassen";
		textFunktionsbutton = "Senden";

		return gibDialogBox();
	}

	public DialogBox getNachrichtBearbeitenDialogbox(Nachricht _nachrichtZumBearbeiten) {
		NachrichtenModus = eNachrichtenmodus.BearbeitenNachricht;
		boxTitel = "Neue Nachricht verfassen";
		textFunktionsbutton = "Speichern";
		textAreaInhalt = _nachrichtZumBearbeiten.getText();
				
		return gibDialogBox();
	}

	private DialogBox gibDialogBox() {
		ladVorschlagListen();

		// Dialog und FlowPanel definition
		db.setText(boxTitel);
		db.setAnimationEnabled(true);
		db.setGlassEnabled(true);

		FlowPanel fpDialog = new FlowPanel();
//		fpDialog.setHeight("500px");
//		fpDialog.setWidth("500px");

		// Empfaenger Panele
		if (NachrichtenModus == eNachrichtenmodus.NeueNachricht) {
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

					// Wenn Text Valide ist, füge einen Button zum Entfernen
					// hinzu.
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

						hpAusgewaehlteEmpfanger.add(btnLoescheEmpfaenger);
					}

					EmpfaengerHinzufuegenSug.setText("");

				}
			});
			fpDialog.add(hpEmpfaenger);
			fpDialog.add(hpAusgewaehlteEmpfanger);
		}

		// Textarea
		TextArea textArea = new TextArea();
		textArea.setWidth("90%");
		textArea.setVisibleLines(10);
		textArea.setText(textAreaInhalt);		
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

		HorizontalPanel hpButtons = new HorizontalPanel();
		hpButtons.setWidth("100%");
		hpButtons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		Button btnFunktionsbutton = new Button(textFunktionsbutton);				
		btnFunktionsbutton.addClickHandler(clickHandlerFunktionsbutton);

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

	
	private ClickHandler clickHandlerFunktionsbutton = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			switch (NachrichtenModus) {
			case NeueNachricht:
			default:
				
				break;

			case AntwortNachricht:
				Window.alert("Auf Unterhaltung antworten");
				break;
			case BearbeitenNachricht:
				Window.alert("Speichern");
				break;
			}
			
		}
	};
}