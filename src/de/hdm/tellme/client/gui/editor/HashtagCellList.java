package de.hdm.tellme.client.gui.editor;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nutzer;



public class HashtagCellList {

	Hashtag selektiertesHashstag = null;

	public FlowPanel generiereCellList(CellListModus modi) {

		CellList<HashtagZelle.ZellenObjekt> cellList = new CellList<HashtagZelle.ZellenObjekt>(new HashtagZelle().new ZellenElement());

		HashtagDataProvider.gib().addDataDisplay(cellList);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<HashtagZelle.ZellenObjekt> selectionModel = new SingleSelectionModel<HashtagZelle.ZellenObjekt>();
		cellList.setSelectionModel(selectionModel);
		switch (modi) {
		case Einstellungen:
			selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
				public void onSelectionChange(SelectionChangeEvent event) {
					// TODO:Prüfen ob abboniert
					// TODO:rechtel aktuallisieren
					HashtagFomular nf = new HashtagFomular();
					nf.setzeHashtagAbo(selectionModel.getSelectedObject());
					RootPanel.get("content_right").clear();
					RootPanel.get("content_right").add(nf.gibFormular());

				}
			});
			break;

		case Nachrichtenuebersicht:
			selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
				public void onSelectionChange(SelectionChangeEvent event) {
					MenuBarEditor.gibansichtNeuigkeiten().FilterNachBenutzer(selectionModel.getSelectedObject());
				}
			});
			
			break;

		default:
			break;
		}
		

		ShowMorePagerPanel sPager = new ShowMorePagerPanel();
		sPager.setWidth("250px");
		sPager.setHeight("100px");
		sPager.setDisplay(cellList);

		RangeLabelPager fPager = new RangeLabelPager();
		fPager.setDisplay(cellList);

		FlowPanel fP = new FlowPanel();

		fP.add(new ScrollPanel(sPager));
		fP.add(new SimplePanel(fPager));
		return fP;

	}
	

}
