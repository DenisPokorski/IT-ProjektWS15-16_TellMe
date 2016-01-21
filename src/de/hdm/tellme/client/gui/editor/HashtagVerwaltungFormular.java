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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.bo.Hashtag;

/**
 * A form used for editing contacts.
 */
/**
 * Die Klasse <class>HashtagVerwaltungFormular</class> wird verwendet, falls ein
 * Hashtag in der HashtagCellList aufgerufen wird und man sich im Modus
 * <code>HashtagVerwaltung</code> befindet.
 * 
 * @author Alex
 *
 */
public class HashtagVerwaltungFormular extends Composite {
	/**
	 * Ein leeres Hashtag-Objekt und sowohl die benötigten Buttons, als auch die
	 * TextBox die benötigt wird um ein Hashtag zu editieren wird erstellt.
	 */
	private Hashtag hashtag = null;
	Button htSpeichern = new Button("Hashtag speichern");
	Button htLoeschen = new Button("Hashtag löschen");
	Button htAnlegen = new Button("Hashtag neu erstellen");
	Button btnAbonieren = new Button("Abonnement hinzufügen");
	Button btnDeabonieren = new Button("Abonnement löschen");
	TextBox schlagwortBox = new TextBox();
	/**
	 * Ein Vektor mit Hashtag-Objekten wird erstellt.
	 */
	Vector<Hashtag> htl;

	public HashtagVerwaltungFormular() {

		/**
		 * ClickHandler um ein Hashtag zu speichern.
		 */
		htSpeichern.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				htl = HashtagDataProvider.gebeHashtagListe();

				if (schlagwortBox.getValue() == "") {
					Window.alert("Bitte gib einen Wert ein");
				} else {

					hashtag.setSchlagwort(schlagwortBox.getText());
					HashtagDataProvider.gib().hashtagSpeichern(hashtag);

				}
			}
		});

		/**
		 * ClickHandler um ein Hashtag zu löschen.
		 */
		htLoeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				HashtagDataProvider.gib().hashtagEntfernen(hashtag);

			}
		});

		/**
		 * ClickHandler um ein neues Hashtag anzulegen.
		 */
		htAnlegen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				htl = HashtagDataProvider.gebeHashtagListe();

				if (schlagwortBox.getValue() == "") {
					Window.alert("Bitte gib einen Wert ein");
				} else {

					boolean existiertBereits = false;

					for (Hashtag hashtag : htl) {
						if (schlagwortBox.getValue().toUpperCase()
								.equals(hashtag.getSchlagwort().toUpperCase())) {
							existiertBereits = true;
						}
					}

					if (existiertBereits == true) {
						Window.alert("Hashtag existiert bereits");
					} else {
						hashtag.setSchlagwort(schlagwortBox.getText());
						HashtagDataProvider.gib().hashtagErstellen(hashtag);
					}

				}

			}
		});
	}

	/**
	 * Die Methode <code>gibFormular</code> gibt ein VerticalPanel zurück, das
	 * die TextBox mit einem ausgewählten Hashtag beinhaltet, außerdem werden
	 * die Buttons um ein Hashtag zu speichern, löschen der neu anzulegen an das
	 * VerticalPanel angehängt.
	 * 
	 * @return VerticalPanel, das die Elemente beinhaltet, die nötig sind um ein
	 *         Hashtag zu verwalten.
	 */
	public VerticalPanel gibFormular() {

		VerticalPanel vpForm = new VerticalPanel();
		vpForm.setStylePrimaryName("top-margin");
		vpForm.clear();
		vpForm.add(schlagwortBox);

		HorizontalPanel hpButtonBar = new HorizontalPanel();
		hpButtonBar.add(htSpeichern);
		htSpeichern.setStylePrimaryName("btnPositiv");

		hpButtonBar.add(htLoeschen);
		htLoeschen.setStylePrimaryName("btnNegativ");

		hpButtonBar.add(htAnlegen);
		htAnlegen.setStylePrimaryName("btnAnlegen");

		vpForm.add(hpButtonBar);

		return vpForm;
	}

	/**
	 * Die Methode <code>gibInfoFormular</code> gibt ein VerticalPanel zurück,
	 * das die Informationen für den Nutzer enthält, die er benötigt um ein
	 * Hashtagabonnement hinzuzufügen oder zu löschen.
	 * 
	 * @return VerticalPanel, das Informationen darüber beinhaltet, wie ein
	 *         Hashtag abonniert oder ein Hashtagabonnement gelöscht werden
	 *         kann.
	 */
	public VerticalPanel gibInfoFormular() {
		VerticalPanel vpForm = new VerticalPanel();

		vpForm.clear();
		vpForm.add(new HTML(
				"Hier kannst du ein <b>Hashtagabonnement</b> erstellen oder löschen. </br>"
						+ "Wähle dazu das gewünschte Hashtag aus und drücke dann den Button zum <b>abonnieren</b> oder zum <b>deabonnieren</b>."));
		return vpForm;
	}

	/**
	 * In der Methode <code>setzeHashtag</code> wird das veränderte oder neue
	 * Hashtag in der HashtagCellLost dargestellt.
	 * 
	 * @param ZellenObjekt
	 */
	public void setzeHashtag(HashtagZelle.ZellenObjekt ZellenObjekt) {
		this.hashtag = ZellenObjekt.hashtag;

		this.schlagwortBox.setText(ZellenObjekt.hashtag.getSchlagwort());

		if (ZellenObjekt.aboniert) {
			btnAbonieren.setEnabled(false);
			btnDeabonieren.setEnabled(true);

		} else {
			btnAbonieren.setEnabled(true);
			btnDeabonieren.setEnabled(false);

		}

	}
}