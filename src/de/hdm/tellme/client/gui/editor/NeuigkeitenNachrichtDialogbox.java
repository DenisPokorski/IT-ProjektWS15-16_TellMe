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
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.BusinessObject.eSichtbarkeit;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * 
 * Die Klasse <class>NeuigkeitenNachrichtDialogbox </class> ist eine Dialogbox
 * mit drei verschiedenen Anwendungsmöglichkeiten (NeueNachricht,
 * AntwortNachricht, BearbeitenNachricht).
 * 
 * 
 * @author denispokorski
 *
 */
public class NeuigkeitenNachrichtDialogbox {

	/**
	 * 
	 * Es gibt verschiedene Modis bei denen verschiedene Dialogboxen geöffnet
	 * werden. Es wird eine Auswahl durch ein enum eDialogModus realisiert.
	 * Diese sind <code>NeueNachricht</code>, <code> AntwortNachricht </code>
	 * oder <code>BearbeitenNachricht </code>
	 * 
	 * @author denispokorski
	 *
	 */
	public enum eNachrichtenmodus {
		NeueNachricht, AntwortNachricht, BearbeitenNachricht;
	}

	/**
	 * 
	 * Eine neue Dialogbox und Textarea wird erstellt. Es wird ein AsyncObj
	 * erstellt. Der Modus der Dialogbox wird anfangs auf Null gesetzt.
	 * 
	 */
	final DialogBox db = new DialogBox();
	TextArea textArea = new TextArea();
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
	eNachrichtenmodus NachrichtenModus = null;

	// Ausgelagert, da sie durch einen Asynccall gefüllt werden
	final MultiWordSuggestOracle suggestOracleEmpfaenger = new MultiWordSuggestOracle();
	Vector<Nutzer> moeglicheEmpfaenger = new Vector<Nutzer>();
	Vector<Nutzer> AusgewaehlteEmpfaenger = new Vector<Nutzer>();

	// Ausgelagert, da sie durch einen Asynccall gefüllt werden
	final MultiWordSuggestOracle suggestOracleHashtags = new MultiWordSuggestOracle();
	Vector<Hashtag> moeglicheHashtags = new Vector<Hashtag>();
	Vector<Hashtag> AusgewaehlteHashtags = new Vector<Hashtag>();

	// Panel für Nachricht Empfänger Dialog Box
	final FlowPanel fpAusgewaehlteEmpfanger = new FlowPanel();
	HorizontalPanel hpEmpfaenger = new HorizontalPanel();

	// Panel für Nachricht Hashtag Dialog Box
	HorizontalPanel hpHashtags = new HorizontalPanel();
	final FlowPanel fpAusgewaehlteHashtags = new FlowPanel();

	// Elemente, die von jedem Nachrichtemodus unterschiedlich befüllt werden
	private String boxTitel;
	private String textAreaInhalt = "";
	private String textFunktionsbutton = "";
	private Unterhaltung antwortUnterhaltung;
	private Nachricht originalNachricht;

	/**
	 * Das enum NeueNachricht wird ausgewählt. Ein Titel wird ersetllt und der
	 * String textFunktionsbutton wird mit Senden gefüllt
	 * 
	 * @return gibDialogBox() - Die Dialogbox mit den entsprechenden Werten
	 *         zurückgeben
	 */
	public DialogBox getNeueNachrichtDialogbox() {
		NachrichtenModus = eNachrichtenmodus.NeueNachricht;
		boxTitel = "Neue Nachricht verfassen";
		textFunktionsbutton = "Senden";

		return gibDialogBox();
	}

	/**
	 * Das enum AntwortNachricht wird ausgewählt. Ein Titel wird erstellt und
	 * der String textFunktionsbutton wird mit Senden gefüllt
	 * 
	 * @param u - Unterhaltungs -ID wird übergeben
	 * @return gibDialogBox() - Die Dialogbox wird mit entsprechenden Werten
	 *         zurückgegeben
	 */
	public DialogBox getAntwortNachrichtDialogbox(Unterhaltung u) {
		NachrichtenModus = eNachrichtenmodus.AntwortNachricht;
		boxTitel = "Antwort verfassen";
		textFunktionsbutton = "Senden";

		antwortUnterhaltung = u;
		return gibDialogBox();
	}

	/**
	 * Das enum BearbeitenNachricht wird ausgewählt. Ein Titel wird erstellt
	 * und der String textFunktionsbutton wird mit Speichern gefüllt. Die zu
	 * bearbeitende Nachricht wird gezeigt. Das HashtagPanel mit den Funktionen
	 * (hinzufügen und löschen) wird eingefügt.
	 * 
	 * @param _nachrichtZumBearbeiten - ausgewähltes Nachrichtenobjekt wird übergeben
	 * 
	 * 
	 * @return gibDialogBox() - Die Dialogbox wird zurückgegeben
	 */
	public DialogBox getNachrichtBearbeitenDialogbox(Nachricht _nachrichtZumBearbeiten) {
		NachrichtenModus = eNachrichtenmodus.BearbeitenNachricht;
		boxTitel = "Nachricht bearbeiten";
		textFunktionsbutton = "Speichern";

		originalNachricht = _nachrichtZumBearbeiten;
		textAreaInhalt = _nachrichtZumBearbeiten.getText();

		// Das HashtagPanel wird befüllt.
		for (Hashtag zuHinzuzufuegenderHashtag : _nachrichtZumBearbeiten.getVerknuepfteHashtags()) {
			AusgewaehlteHashtags.addElement(zuHinzuzufuegenderHashtag);
			
			final Button btnLoescheHashtag = new Button("#"+ zuHinzuzufuegenderHashtag.getSchlagwort() + "(X)");
			btnLoescheHashtag.setStylePrimaryName("ButtonX-Nachrichtenverwaltung");
			btnLoescheHashtag.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					for (Hashtag hashtag : AusgewaehlteHashtags) {
						if (btnLoescheHashtag.getText().equals("#" + hashtag.getSchlagwort() + "(X)")) {
							AusgewaehlteHashtags.remove(hashtag);
							btnLoescheHashtag.removeFromParent();
							continue;
						}
					}
				}
			});
			fpAusgewaehlteHashtags.add(btnLoescheHashtag);
		}
		return gibDialogBox();
	}

	/**
	 * Die Dialogbox wird definiert.
	 * 
	 * 
	 * @return db - die Dialogbox wird zurückgegeben
	 */
	private DialogBox gibDialogBox() {
		ladVorschlagListen();

		db.setText(boxTitel);
		db.setAnimationEnabled(true);
		db.setGlassEnabled(true);

		FlowPanel fpDialog = new FlowPanel();
		fpDialog.setWidth("100%");
		Image btnAbbrechen = new Image("xbtn.png");
		
		btnAbbrechen.setStylePrimaryName("xbtn");
		btnAbbrechen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				db.hide();
			}
		});
		fpDialog.add(btnAbbrechen);

		// ############################################## Empfaenger: Panel  ##############################################
		
		if (NachrichtenModus == eNachrichtenmodus.NeueNachricht) {

			final SuggestBox EmpfaengerHinzufuegenSug = new SuggestBox(suggestOracleEmpfaenger);
			hpEmpfaenger.add(EmpfaengerHinzufuegenSug);

			final Button btnEmpfaengerHinzufuegen = new Button("+ Empfänger");
			btnEmpfaengerHinzufuegen.setStylePrimaryName("EmpfaengerPlusBtn");

			hpEmpfaenger.add(btnEmpfaengerHinzufuegen);

			btnEmpfaengerHinzufuegen.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					Nutzer zuHinzuzufuegenderBenutzer = null;
					boolean bereitsHinzugefuegt = false;

					/**
					*   Überprüfe, ob Text in Suggestionbox mit Text (Vorname
				 	*	Nachname (Email)) eines Vorhandenen Benutzers
					*	übereinstimmt. Falls ja setzte speichere Nutzerobjekt in
					*	zuHinzuzufuegenderBenutzer
					*
					*/
					for (Nutzer einzelnerNutzer : moeglicheEmpfaenger) {
						if (EmpfaengerHinzufuegenSug.getText().equals(gibVorschlageTextFuerNutzer(einzelnerNutzer))) {
							zuHinzuzufuegenderBenutzer = einzelnerNutzer;
							continue;
						}
					}

					// Prüfung, ob Empfänger schon vorhanden ist
					for (Nutzer nutzer : AusgewaehlteEmpfaenger) {
						if (zuHinzuzufuegenderBenutzer == nutzer)
							bereitsHinzugefuegt = true;
					}

					// Wenn Text Valide ist, wird ein Button zum Entfernen hinzgefügt.
					// Ansonsten wird eine Fehlermeldung ausgeben.
					if (bereitsHinzugefuegt) {
						Window.alert("Der Empfänger kann nicht hinzugefügt werden, da dieser bereits hinzugefügt wurde.");
					} else if (zuHinzuzufuegenderBenutzer == null) {
						Window.alert("Keinen vorhandenen Benutzer ausgewählt.");
					} else {
						AusgewaehlteEmpfaenger.addElement(zuHinzuzufuegenderBenutzer);
						
						final Button btnLoescheEmpfaenger = new Button(gibVorschlageTextFuerNutzer(zuHinzuzufuegenderBenutzer)+ "(X)");
						btnLoescheEmpfaenger.setStylePrimaryName("ButtonX-Nachrichtenverwaltung");
						btnLoescheEmpfaenger.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										 /**
										  * Jedem Emfpänger, der über ein Suggest-Box-Element ausgewählt wurde,
										  * wird ein Entfernen-Button hinzugefügt, sodass das zuvor hinzugefügt
										  * Element aus der Auswahl gelöscht werden kann.
										  */
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
			fpDialog.add(hpEmpfaenger);
			fpDialog.add(fpAusgewaehlteEmpfanger);
		}

		// ############################################## Textarea ##############################################
		textArea.setWidth("98.4%");
		textArea.setVisibleLines(10);
		textArea.setText(textAreaInhalt);
		textArea.setStylePrimaryName("dialogtextArea");
		textArea.getElement().setAttribute("maxlength", "255");

		fpDialog.add(textArea);

		// ############################################## Hashtag Panel  ##############################################
		final SuggestBox HastagHinzufuegenSug = new SuggestBox(suggestOracleHashtags);
		hpHashtags.add(HastagHinzufuegenSug);

		final Button btnHashtagHinzufuegen = new Button("+ Hashtag");
		hpHashtags.add(btnHashtagHinzufuegen);

		btnHashtagHinzufuegen.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Hashtag zuHinzuzufuegenderHashtag = null;
				boolean bereitsHinzugefuegt = false;
				 /**
				  * Jedem Emfpänger, der über ein Suggest-Box-Element ausgewählt wurde,
				  * wird ein Entfernen-Button hinzugefügt, sodass das zuvor hinzugefügt
				  * Element aus der Auswahl gelöscht werden kann.
				  */
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

				/**
				 *  Falls das hinzugefügte Suggest-Box-Element bereits hinzugefügt wurde, wird dies mit einer Fehlermeldung ausgegeben. 
				 */
				if (bereitsHinzugefuegt) {
					Window.alert("Der Hashtag kann nicht hinzugefügt werden, da dieser bereits hinzugefügt wurde.");
				} else if (zuHinzuzufuegenderHashtag == null) {
					Window.alert("Keinen vorhandenen Hashtag ausgewaehlt.");
				} else {
					AusgewaehlteHashtags.addElement(zuHinzuzufuegenderHashtag);
					final Button btnLoescheHashtag = new Button("#"+ zuHinzuzufuegenderHashtag.getSchlagwort() + "(X)");
					btnLoescheHashtag.setStylePrimaryName("ButtonX-Hashtagverwaltung");

					btnLoescheHashtag.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							 /**
							  * Jedem Emfpänger, der über ein Suggest-Box-Element ausgewählt wurde,
							  * wird ein Entfernen-Button hinzugefügt, sodass das zuvor hinzugefügt
							  * Element aus der Auswahl gelöscht werden kann.
							  */
							for (Hashtag hashtag : AusgewaehlteHashtags) {
								if (btnLoescheHashtag.getText().equals("#" + hashtag.getSchlagwort() + "(X)")) {
									AusgewaehlteHashtags.remove(hashtag);
									btnLoescheHashtag.removeFromParent();
									continue;
								}
							}
						}
					});
					fpAusgewaehlteHashtags.add(btnLoescheHashtag);
				}
				HastagHinzufuegenSug.setText("");
			}
		});
		/**
		 * Dem FlowPanel fpDialog wird das HorizontalPanel hpHashtags und ein
		 * weiteres FlowPanel fpAusgewaehlteHashtags hinzugefügt
		 */
		fpDialog.add(hpHashtags);
		fpDialog.add(fpAusgewaehlteHashtags);

		/**
		 * Ein Horizontal hpButtons wird erstellt, auf 100% Breit gesetzt und
		 * auf die rechte Seite gelegt.
		 */
		HorizontalPanel hpButtons = new HorizontalPanel();
		hpButtons.setWidth("100%");
		hpButtons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		/**
		 * Ein neuer Button wird erstellt und mittels CSS-Sytles optisch
		 * verändert mit einem ClickHandler versehen.
		 */
		Button btnFunktionsbutton = new Button(textFunktionsbutton);
		btnHashtagHinzufuegen.setStylePrimaryName("HashtagPlusBtn");

		/**
		 * Ein neuer Button wird erstellt und mittels CSS-Sytles optisch
		 * verändert mit einem ClickHandler versehen.
		 */
		btnFunktionsbutton.setStylePrimaryName("NeuigkeitenDialogboxSendenBtn");
		btnFunktionsbutton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				switch (NachrichtenModus) {
				
				/**
				 * Der Case NeueNachricht ersetllt eine neue Nachricht und wird mit
				 * den Daten des eingeloggten Nutzers versehen. Der Text, die
				 * Sichtbarkeit und die ausgewählten Hashtags werden ebenfalls eingefügt.
				 */
				case NeueNachricht:
				default:
					if (textArea.getValue() == "") {
						Window.alert("Bitte gib einen Text ein, um die Nachricht versenden zu können.");

					} else {
						/**
						 * Ein neues Nachrichten-Objekt wird erstellt. In diesem wird das Objekt
						 * des eingeloggten Nutzers übergeben. Zudem die ID des eingeloggten Nutzer.
						 * Die Sichtbarkeit der zu erstellenden Nachricht. Danach wird die Methode
						 * <code>unterhaltungStarten</code> ausgeführt.
						 */
						Nachricht neueNachricht = new Nachricht();
						neueNachricht.setSender(TellMe.gibEingeloggterBenutzer().getUser());
						neueNachricht.setSenderId(TellMe.gibEingeloggterBenutzer().getUser().getId());
						neueNachricht.setSichtbarkeit(eSichtbarkeit.Sichtbar.ordinal());

						neueNachricht.setText(textArea.getText());
						neueNachricht.setVerknuepfteHashtags(AusgewaehlteHashtags);

						AusgewaehlteEmpfaenger.addElement(TellMe.gibEingeloggterBenutzer().getUser());

						asyncObj.unterhaltungStarten(neueNachricht,AusgewaehlteEmpfaenger, new AsyncCallback<Boolean>() {
									@Override
									public void onSuccess(Boolean result) {
										if (result)
											Window.alert("Nachricht erfolgreich erstellt");
										else
											Window.alert("Fehler beim erstellen der Nachricht, bitte wende dich an den Administrator.");
									}
									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Fehler beim erstellen der Nachricht, bitte wende dich an den Administrator.");
									}
						});
					}
					break;

				/**
				 * Es wird eine neue Nachricht erstellt und mit den Daten des
				 * eingeloggten Nutzers versehen. Die Sichtbarkeit, der Text und
				 * die Hashtags werden ebenfalls versehen
				 * 
				 */
				case AntwortNachricht:
					if (textArea.getValue() == "") {
						Window.alert("Bitte gib einen Text ein um die Unterhaltung zu beantworten.");
					} else {
						Nachricht antwortNachricht = new Nachricht();
						antwortNachricht.setSender(TellMe.gibEingeloggterBenutzer().getUser());
						antwortNachricht.setSichtbarkeit(1);
						antwortNachricht.setText(textArea.getValue());
						antwortNachricht.setVerknuepfteHashtags(AusgewaehlteHashtags);

						asyncObj.unterhaltungBeantworten(antwortNachricht,antwortUnterhaltung,
								new AsyncCallback<Boolean>() {
									@Override
									public void onSuccess(Boolean result) {
										if (result)
											Window.alert("Nachricht erfolgreich gesendet.");
										else
											Window.alert("Fehler beim erstellen der Nachricht, bitte wende dich an den Administrator.");
									}
									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Fehler beim erstellen der Nachricht, bitte wende dich an den Administrator.");
									}
								});
					}
					break;

				/**
				 * Die zu bearbeitende Nachricht wird mit einem
				 * Erstellungsdatum, Id, Sender, Sichtbarkeit, Text, Hashtags
				 * versehen
				 * 
				 */
				case BearbeitenNachricht:
					if (textArea.getValue() == "") {
						Window.alert("Bitte gib einen Text ein um die Nachricht speichern zu können.");
					} else {

						Nachricht bearbeiteteNachricht = new Nachricht();
						bearbeiteteNachricht.setErstellungsDatum(originalNachricht.getErstellungsDatum());
						bearbeiteteNachricht.setId(originalNachricht.getId());
						bearbeiteteNachricht.setSender(originalNachricht.getSender());
						bearbeiteteNachricht.setSichtbarkeit(originalNachricht.getSichtbarkeit());
						bearbeiteteNachricht.setText(textArea.getValue());
						bearbeiteteNachricht.setVerknuepfteHashtags(AusgewaehlteHashtags);
						
						asyncObj.NachrichtAktualisieren(originalNachricht,bearbeiteteNachricht,new AsyncCallback<Boolean>() {

								@Override
								public void onSuccess(Boolean result) {
									if (result)
										Window.alert("Nachricht erfolgreich aktualisiert");
									else
										Window.alert("Fehler beim bearbeiten der Nachricht, bitte wende dich an den Administrator.");
								}
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Fehler beim bearbeiten der Nachricht, bitte wende dich an den Administrator.");
								}
						});
					}
					break;
				}
				db.hide();
			}
		});

		hpButtons.add(btnFunktionsbutton);
		fpDialog.add(hpButtons);
		db.setWidget(fpDialog);
		return db;
	}

	/**
	 * Eine Liste von vorhanden Nutzern und Hashtags wird geladen.
	 * 
	 */

	private void ladVorschlagListen() {
		asyncObj.getAlleNutzerAußerMeineId(TellMe.gibEingeloggterBenutzer().getUser().getId(), new AsyncCallback<Vector<Nutzer>>() {
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(Vector<Nutzer> resultListe) {
				moeglicheEmpfaenger = resultListe;
				for (Nutzer einzelnerUser : moeglicheEmpfaenger) {
					suggestOracleEmpfaenger
							.add(gibVorschlageTextFuerNutzer(einzelnerUser));
				}
			}
		});

		asyncObj.gibAlleHashtags(new AsyncCallback<Vector<Hashtag>>() {
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

	/**
	 * Bei der Eingabe eines Empfängers werden vorhandene Nutzer angezeigt.
	 * 
	 * @param _nutzer
	 * @return Der Vorname, Nachname und die E-MailAdresse werden zurückgegeben
	 */
	private String gibVorschlageTextFuerNutzer(Nutzer _nutzer) {
		return _nutzer.getVorname() + " " + _nutzer.getNachname() + " ("
				+ _nutzer.getMailadresse() + ")";
	}
}