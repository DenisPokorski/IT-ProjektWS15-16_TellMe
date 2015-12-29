package de.hdm.tellme.client.gui.editor;

/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;

/**
 * A form used for editing contacts.
 */

public class HashtagVerwaltungFomular extends Composite {

	private final EditorServiceAsync _asyncObj = GWT
			.create(EditorService.class);

	private Hashtag hashtag = null;
	Button htSpeichern = new Button("Hashtag speichern");
	Button htLoeschen = new Button("Hashtag löschen");
	Button htAnlegen = new Button("Hashtag neu erstellen");
	TextBox schlagwortBox = new TextBox();
	Vector<Hashtag> htl;

	public HashtagVerwaltungFomular() {

		// Handle events.
		htSpeichern.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				htl = HashtagDataProvider.gebeHashtagListe();

				if (schlagwortBox.getValue() == "") {
					Window.alert("Bitte geben Sie einen Wert ein");
				} else {

					boolean existiertBereits = false;

					for (Hashtag hashtag : htl) {
						if (schlagwortBox.getValue() == hashtag.getSchlagwort()) {
							existiertBereits = true;
						}
					}

					Window.alert("hallo " + existiertBereits);

					if (existiertBereits == true) {
						Window.alert("Hashtag Existiert bereits");
					} else {
						hashtag.setSchlagwort(schlagwortBox.getText());
						HashtagDataProvider.gib().hashtagSpeichern(hashtag);
					}

				}
			}
		});

		// Handle events.
		htLoeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				HashtagDataProvider.gib().hashtagEntfernen(hashtag);

			}
		});

		// Handle events.
		htAnlegen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				htl = HashtagDataProvider.gebeHashtagListe();


				if (schlagwortBox.getValue() == "") {
					Window.alert("Bitte geben Sie einen Wert ein");
				} else {

					boolean existiertBereits = false;

					for (Hashtag hashtag : htl) {
						if (schlagwortBox.getValue() == hashtag.getSchlagwort()) {
							existiertBereits = true;
						}
					}

					Window.alert("hallo " + existiertBereits);

					if (existiertBereits == true) {
						Window.alert("Hashtag Existiert bereits");
					} else {
						hashtag.setSchlagwort(schlagwortBox.getText());
						HashtagDataProvider.gib().hashtagErstellen(hashtag);
					}

				}
 
			}
		});
	}

	// Panel Rückgabe
	public VerticalPanel gibFormular() {
		VerticalPanel vpForm = new VerticalPanel();
		vpForm.clear();
		vpForm.add(schlagwortBox);

		HorizontalPanel hpButtonBar = new HorizontalPanel();
		hpButtonBar.add(htSpeichern);
		hpButtonBar.add(htLoeschen);
		hpButtonBar.add(htAnlegen);
		vpForm.add(hpButtonBar);

		// vpForm.add(new Label(hashtag.getSchlagwort() ));
		return vpForm;
	}

	// Setze Buttons
	public void setzeHashtag(HashtagZelle.ZellenObjekt ZellenObjekt) {
		this.hashtag = ZellenObjekt.hashtag;

		this.schlagwortBox.setText(ZellenObjekt.hashtag.getSchlagwort());

		// if (ZellenObjekt.aboniert) {
		// btnAbonieren.setEnabled(false);
		// btnDeabonieren.setEnabled(true);
		//
		// } else {
		// btnAbonieren.setEnabled(true);
		// btnDeabonieren.setEnabled(false);
		//
		//
		//
		// }

	}
}