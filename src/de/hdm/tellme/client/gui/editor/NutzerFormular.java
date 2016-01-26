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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Die Klasse <class>NutzerFormular</class> wird aufgerufen, wenn in der
 * Nutzerabonnementverwaltung auf einen Nutzer geklickt wird. Bei Klick auf
 * einen Nutzer wird dieses Formular geladen. Es wird eine Beschreibung für
 * Nutzerabonnements dargestellt. Außerdem erscheinen Buttons um ein Abonnement
 * hinzuzufügen oder zu löschen. Bei Klick auf die jeweiligen Buttons wird ein
 * Nutzerabonnement hinzugefügt oder gelöscht.
 */

public class NutzerFormular extends Composite {

	private Nutzer nutzer = null;

	/**
	 * Die Buttons zum Abonnement hinzufügen und löschen werden erstellt.
	 */
	Button btnAbonieren = new Button("Abonnement hinzufügen");
	Button btnDeabonieren = new Button("Abonnement löschen");

	public NutzerFormular() {
		// Handle events.
		/**
		 * Clickhandler für den abonieren-Button
		 */
		btnAbonieren.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (nutzer == null) {
					return;
				}
				RootPanel.get("content_right").clear();
				RootPanel.get("content_right").add(gibBeschreibung());
				NutzerDataProvider.gib(0).abonieren(nutzer);
			}
		});
		// Handle events.
		/**
		 * Clickhandler für den abonnement löschen-Button
		 */
		btnDeabonieren.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (nutzer == null) {
					return;
				}
				
				NutzerDataProvider.gib(0).deabonieren(nutzer);
			}
		});
	}

	/**
	 * Die Methode <code>gibFormular()</code> gibt ein VerticalPanel zurück,
	 * dass das ausgewählte Nutzer Objekt abonniert werden kann oder das
	 * Abonnement gelöscht wird.
	 * 
	 * @return VerticalPanel mit dem gewünschten Inhalt, in diesem Fall Formular
	 *         für ein Nutzerabonnement.
	 */
	public VerticalPanel gibFormular() {
		VerticalPanel vpForm = new VerticalPanel();

		HorizontalPanel hpForm = new HorizontalPanel();
		btnAbonieren.setStylePrimaryName("btnPositiv");
		btnDeabonieren.setStylePrimaryName("btnNegativ");

		Label selctionLabel = new Label("Ausgewählter Nutzer: "
				+ nutzer.getVorname() + " " + nutzer.getNachname());
		selctionLabel.setStylePrimaryName("selectionLabel");
		vpForm.add(selctionLabel);

		hpForm.add(btnAbonieren);
		hpForm.add(btnDeabonieren);

		vpForm.add(hpForm);

		return vpForm;
	}

	/**
	 * Die Methode <code>gibBeschreibung()</code> gibt ein VerticalPanel zurück,
	 * dass das die Beschreibung für die Nutzerabonnements enthält.
	 * 
	 * @return VerticalPanel mit dem gewünschten Inhalt, in diesem Fall die
	 *         Beschreibung des Nutzerabonnements.
	 */
	public VerticalPanel gibBeschreibung() {
		VerticalPanel vpForm = new VerticalPanel();

		HTML headline = new HTML(" <div class='" + "subline"
				+ "'><h2>Nutzerabo(s) verwalten:</h2></div> ");
		vpForm.add(headline);

		HTML subtext = new HTML(
				" <div class='"
						+ "subtext"
						+ "'><h4>Hier kannst du andere Nutzer abonnieren. Danach werden alle öffentlichen Nachrichten im Menüpunkt Neuigkeiten angezeigt. <br /> Zudem kannst du bereits vorhandene Abonnements löschen. </h4></div> ");
		vpForm.add(subtext);

		return vpForm;
	}

	/**
	 * TODO ein button davon sollte immer grau sein sodass nur der farbige
	 * angeklickt wird.
	 * 
	 * @param ZellenObjekt
	 */
	/**
	 * Hier wird ein Nutzerabonnement in der Datenbank gesetzt. Das ausgewählte
	 * ZellenObjekt, wird als Parameter übergeben.
	 * 
	 * @param ZellenObjekt
	 */
	public void setzeNutzerAbo(NutzerZelle.ZellenObjekt ZellenObjekt) {
		this.nutzer = ZellenObjekt.nutzer;
		if (ZellenObjekt.aboniert) {
			btnAbonieren.setEnabled(false);
			btnDeabonieren.setEnabled(true);

		} else {
			btnAbonieren.setEnabled(true);
			btnDeabonieren.setEnabled(false);

		}

	}
}