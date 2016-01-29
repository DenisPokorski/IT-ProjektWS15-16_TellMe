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

import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasRows;
/**
 * TODO
 */
/**
 * 
 * Diese Klasse ermöglicht das der Inhalt scrollbar dargestellt wird.
 * Wenn die Bildlaufleiste den Boden erreicht hat werden Elemente nachgeladen und mit einem Label angezeigt. 
 */
public class ShowMorePagerPanel extends AbstractPager {

	/**
	 * Diese statische Integer Variale definiert die maximale Größe der angezeigten Elemnte. 
	 */
	private static final int DEFAULT_INCREMENT = 20;
	private int incrementSize = DEFAULT_INCREMENT;

	/**
	 * In dieser Variable wird der Index der letzten Scrollposition hinterlegt. 
	 */
	private int lastScrollPos = 0;

	/**
	 * Das  scrollable panel.
	 */
	private final ScrollPanel scrollable = new ScrollPanel();

	/**
	 * Konstruktor: {@link ShowMorePagerPanel}.
	 */
	public ShowMorePagerPanel() {
		initWidget(scrollable);

		// On Fokus: Der Index des Scrollpanels wird um -1 vermindert, minus des ausgewälten Elemntes. 
		scrollable.getElement().setTabIndex(-1);

		// Eventhandling
		scrollable.addScrollHandler(new ScrollHandler() {
			public void onScroll(ScrollEvent event) {
				// If scrolling up, ignore the event.
				int oldScrollPos = lastScrollPos;
				lastScrollPos = scrollable.getVerticalScrollPosition();
				if (oldScrollPos >= lastScrollPos) {
					return;
				}

				HasRows display = getDisplay();
				if (display == null) {
					return;
				}
				int maxScrollTop = scrollable.getWidget().getOffsetHeight() - scrollable.getOffsetHeight();
				if (lastScrollPos >= maxScrollTop) {
					int newPageSize = Math.min(display.getVisibleRange().getLength() + incrementSize, display.getRowCount());
					display.setVisibleRange(0, newPageSize);
				}
			}
		});
	}

	/**
	 * 
	 * Diese Methode gibt die Nummer der Zeilen zurück.
	 *
	 * @return the increment size
	 */
	public int getIncrementSize() {
		return incrementSize;
	}

	@Override
	public void setDisplay(HasRows display) {
		assert display instanceof Widget : "Konnte nicht angezeigt werden.";
		scrollable.setWidget((Widget) display);
		super.setDisplay(display);
	}

	/**
	 * Diese Methode gibt die Nummer der Zeilen zurück.
	 *
	 */
	public void setIncrementSize(int incrementSize) {
		this.incrementSize = incrementSize;
	}

	@Override
	protected void onRangeOrRowCountChanged() {
	}
}
