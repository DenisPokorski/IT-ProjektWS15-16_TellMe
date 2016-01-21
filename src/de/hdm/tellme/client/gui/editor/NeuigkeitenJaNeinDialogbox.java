package de.hdm.tellme.client.gui.editor;

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
import com.google.gwt.user.client.ui.Label;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * 
 * Die Klasse <class> NeuigkeitenJaNeinDialogbox</class> sind die beiden
 * Dialogboxen, die erscheinen, wenn eine Nachricht/Unterhaltung ausgewählt
 * wurde und diese durch die Auswahl der Buttons "Nachricht löschen" oder
 * "Unterhaltung verlassen" bestätigt wurden.
 * 
 * @author denispokorski
 *
 */
public class NeuigkeitenJaNeinDialogbox {

	/**
	 * Es gibt verschiedene Modis bei denen verschiedene Dialogboxen geöffnet
	 * werden. Es wird eine Auswahl durch ein enum eDialogModus realisiert.
	 * Diese sind <code>NachrichtLoeschen</code> oder
	 * <code> UnterhaltungVerlassen </code>
	 * 
	 * 
	 * @author denispokorski
	 *
	 */
	public enum eDialogModus {
		NachrichtLoeschen, UnterhaltungVerlassen;
	}

	/**
	 * Eine neue Dialogbox "db" wird erstellt. TODO
	 * 
	 */
	final DialogBox db = new DialogBox();
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
	eDialogModus DialogModus = null;

	// Felder von beiden
	String textTitel = "";
	String textFrage = "";

	Nachricht zuLoeschendeNachricht;
	Unterhaltung zuVerlassendeUnterhaltung;

	/**
	 * Die Methode <code> getNachrichtLoeschenDialogBox() </code> wählt das enum
	 * "NachrichtLoeschen" aus. Der Titel und der Text werden entsprechend
	 * angepasst.
	 * 
	 * @param _zuLoeschendeNachricht
	 * @return gibDialogBox()
	 */
	public DialogBox getNachrichtLoeschenDialogBox(
			Nachricht _zuLoeschendeNachricht) {
		textTitel = "Nachricht löschen";
		textFrage = "Möchtest du die Nachricht wirklich löschen?";

		zuLoeschendeNachricht = _zuLoeschendeNachricht;

		DialogModus = eDialogModus.NachrichtLoeschen;

		return gibDialogBox();
	}

	/**
	 * Die Methode <code> getUnterhaltungVerlassenDialogBox() </code> wählt das
	 * enum "UnterhaltungVerlassen" aus. Der Titel und der Text werden
	 * entsprechend angepasst.
	 * 
	 * 
	 * @param _zuVerlassendeUnterhaltung
	 * @return gibDialogBox()
	 */
	public DialogBox getUnterhaltungVerlassenDialogBox(
			Unterhaltung _zuVerlassendeUnterhaltung) {
		textTitel = "Unterhaltung verlassen";
		textFrage = "Möchtest du die Unterhaltung wirklich verlassen?";

		zuVerlassendeUnterhaltung = _zuVerlassendeUnterhaltung;

		DialogModus = eDialogModus.UnterhaltungVerlassen;

		return gibDialogBox();
	}

	/**
	 * Die Methode <code> gibDialogBox() </code> definiert die Dialogbox und das
	 * Flowpanel, dass noch erstellt wird.
	 * 
	 * 
	 * @return db
	 */
	private DialogBox gibDialogBox() {

		// Dialog und FlowPanel definition
		db.setText(textTitel);
		db.setAnimationEnabled(true);
		db.setGlassEnabled(true);

		/**
		 * Ein neues FlowPanel wird erstellt.In dem FlowPanel wird ein
		 * Abbrechen-PNG hinzugefügt und mit einem ClickHandler versehen.
		 *
		 */
		FlowPanel fpDialog = new FlowPanel();

		fpDialog.setWidth("100%");
		// fpDialog.setWidth("500px");
		Image btnAbbrechen = new Image("xbtn.png");
		btnAbbrechen.setStylePrimaryName("xbtn");
		btnAbbrechen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				db.hide();
			}
		});

		fpDialog.add(btnAbbrechen);

		/**
		 * Ein HorizontalPanel "hpFrage" wird erstellt. Dem HorizontalPanel wird
		 * ein Label "textFrage" hinzugefügt. Das erstellte HorizontalPanel wird
		 * der DialogBox hinzugefügt.
		 */
		HorizontalPanel hpFrage = new HorizontalPanel();
		hpFrage.add(new Label(textFrage));
		fpDialog.add(hpFrage);

		/**
		 * Ein neues HorizontalPanel "hpButtons" wird erstellt, die Breite wird
		 * auf 100% gesetzt und auf die rechte Seite positioniert.
		 */
		HorizontalPanel hpButtons = new HorizontalPanel();
		hpButtons.setWidth("100%");
		hpButtons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		/**
		 * Ein neuer Button wird erstellt "Ja" und mittels CSS-Style optisch
		 * verändert und mit einem ClickHandler versehen.
		 * TODO 
		 */
		Button btnJa = new Button("Ja");
		btnJa.setStylePrimaryName("NeuigkeitenDialogboxSendenBtn");
		btnJa.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				switch (DialogModus) {
				case NachrichtLoeschen:
				default:
					asyncObj.NachrichtLoeschen(zuLoeschendeNachricht,
							new AsyncCallback<Boolean>() {

						
								/**
								 *  Bei erfolgreichen Ausführen 
								 */
								@Override
								public void onSuccess(Boolean result) {
									if (result)
										Window.alert("Nachricht erfolgreich gelöscht");
									else
										Window.alert("Fehler beim löschen der Nachricht, bitte wende dich an den Administrator");

								}

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Fehler beim löschen der Nachricht, bitte wende dich an den Administrator");

								}
							});
					break;

				case UnterhaltungVerlassen:
					asyncObj.UnterhaltungVerlassen(zuVerlassendeUnterhaltung,
							TellMe.gibEingeloggterBenutzer().getUser().getId(),
							new AsyncCallback<Boolean>() {

								@Override
								public void onSuccess(Boolean result) {
									if (result)
										Window.alert("Unterhaltung erfolgreich verlassen");
									else
										Window.alert("Fehler beim verlassen der Unterhaltung, bitte wende dich an den Administrator");

								}

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Fehler beim löschen der Nachricht, bitte wende dich an den Administrator");

								}
							});
					break;
				}
				db.hide();
			}
		});

		hpButtons.add(btnJa);
		// hpButtons.add(btnAbbrechen);
		fpDialog.add(hpButtons);

		db.setWidget(fpDialog);

		return db;
	}

}