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


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;

/**
 * A form used for editing contacts.
 */

public class HashtagFomular extends Composite {
	
	private final EditorServiceAsync _asyncObj = GWT.create(EditorService.class);

	private Hashtag hashtag = null;
	Button btnAbonieren = new Button("abonieren");
	Button btnDeabonieren = new Button("deabonieren");
	
	

	public HashtagFomular() {
		
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
	}
	
	// Panel Rückgabe 
	public VerticalPanel gibFormular(){
		VerticalPanel vpForm = new VerticalPanel();
		vpForm.clear();
		vpForm.add(new Label(hashtag.getSchlagwort() ));
		vpForm.add(btnAbonieren);
		vpForm.add(btnDeabonieren);
		
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