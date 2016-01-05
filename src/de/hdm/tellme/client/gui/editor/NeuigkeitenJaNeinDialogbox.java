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
import com.google.gwt.user.client.ui.Label;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class NeuigkeitenJaNeinDialogbox {

	public enum eDialogModus {
		NachrichtLoeschen, UnterhaltungVerlassen;
	}

	final DialogBox db = new DialogBox();
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
	eDialogModus DialogModus = null;

	// Felder von beiden
	String textTitel = "";
	String textFrage = "";

	Nachricht zuLoeschendeNachricht;
	Unterhaltung zuVerlassendeUnterhaltung;

	public DialogBox getNachrichtLoeschenDialogBox(Nachricht _zuLoeschendeNachricht) {
		textTitel = "Nachricht löschen";
		textFrage = "Möchten Sie die Nachricht wirklich löschen?";

		zuLoeschendeNachricht = _zuLoeschendeNachricht;

		DialogModus = eDialogModus.NachrichtLoeschen;

		return gibDialogBox();
	}

	public DialogBox getUnterhaltungVerlassenDialogBox(Unterhaltung _zuVerlassendeUnterhaltung) {
		textTitel = "Unterhaltung verlassen";
		textFrage = "Möchten Sie die Unterhaltung wirklich verlassen?";

		zuVerlassendeUnterhaltung = _zuVerlassendeUnterhaltung;

		DialogModus = eDialogModus.UnterhaltungVerlassen;

		return gibDialogBox();
	}

	private DialogBox gibDialogBox() {

		// Dialog und FlowPanel definition
		db.setText(textTitel);
		db.setAnimationEnabled(true);
		db.setGlassEnabled(true);

		FlowPanel fpDialog = new FlowPanel();

		HorizontalPanel hpFrage = new HorizontalPanel();
		hpFrage.add(new Label(textFrage));
		fpDialog.add(hpFrage);

		HorizontalPanel hpButtons = new HorizontalPanel();
		hpButtons.setWidth("100%");
		hpButtons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		Button btnJa = new Button("Ja");
		btnJa.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				switch (DialogModus) {
				case NachrichtLoeschen:
				default:
					asyncObj.NachrichtLoeschen(zuLoeschendeNachricht, new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							if (result)
								Window.alert("Nachricht erfolgreich gelöscht");
							else
								Window.alert("Fehler beim löschen der Nachricht, bitte wenden Sie sich an den Administrator");

						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler beim löschen der Nachricht, bitte wenden Sie sich an den Administrator");

						}
					});
					break;

				case UnterhaltungVerlassen:
					asyncObj.UnterhaltungVerlassen(zuVerlassendeUnterhaltung, TellMe.gibEingeloggterBenutzer().getUser().getId(), new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							if (result)
								Window.alert("Unterhaltung erfolgreich verlassen");
							else
								Window.alert("Fehler beim verlassen der Unterhaltung, bitte wenden Sie sich an den Administrator");

						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler beim löschen der Nachricht, bitte wenden Sie sich an den Administrator");

						}
					});break;
				}
				db.hide();
			}
		});

		Button btnAbbrechen = new Button("Abbrechen");
		btnAbbrechen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				db.hide();
			}
		});
		hpButtons.add(btnJa);
		hpButtons.add(btnAbbrechen);
		fpDialog.add(hpButtons);

		db.setWidget(fpDialog);

		return db;
	}

}