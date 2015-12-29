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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.bo.Hashtag;

/**
 * A form used for editing contacts.
 */

public class HashtagFormular extends Composite {
	
	private Hashtag hashtag = null;
	Button btnAbonieren = new Button("abonnieren");
	Button btnDeabonieren = new Button("deabonnieren");
	TextBox schlagwortBox = new TextBox();
	Button htAnlegen = new Button("Hashtag neu erstellen");
	Vector<Hashtag> htl;
	

	public HashtagFormular() {
		
		// Handle events.
		btnAbonieren.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (hashtag == null) {
					return;
				}
				HashtagDataProvider.gib().abonieren(hashtag);
			}
		});
		
		// Handle events.
		btnDeabonieren.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (hashtag == null) {
					return;
				}
				HashtagDataProvider.gib().deabonieren(hashtag);
			}
		});
		
		
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


					if (existiertBereits == true) {
						Window.alert("Hashtag existiert bereits");
					} else {
						 Hashtag neuesHashtag = new Hashtag();
						neuesHashtag.setSchlagwort(schlagwortBox.getText());
						HashtagDataProvider.gib().hashtagErstellen(neuesHashtag);
					}

				}
 
			}
		});
	}
	
	// Panel Rückgabe 
	public VerticalPanel gibBearbeitenFormular(){
		VerticalPanel vpForm = new VerticalPanel();
		vpForm.clear();
		vpForm.add(new Label(hashtag.getSchlagwort() ));
		vpForm.add(btnAbonieren);
		vpForm.add(btnDeabonieren);
		
		return vpForm;
	}
	

	
	public VerticalPanel gibAnlegenFormular(){
		VerticalPanel vpForm = new VerticalPanel();
		vpForm.clear();
		vpForm.add(schlagwortBox);
		vpForm.add(htAnlegen);
		
		return vpForm;
	}
	
	 
// Setze Buttons
	public void setzeHashtagAbo(HashtagZelle.ZellenObjekt ZellenObjekt) {
		this.hashtag = ZellenObjekt.hashtag;
		
		if (ZellenObjekt.aboniert) {
			btnAbonieren.setEnabled(false);
			btnDeabonieren.setEnabled(true);
			
		} else {
			btnAbonieren.setEnabled(true);
			btnDeabonieren.setEnabled(false);
			
			
			
		}

	}
}