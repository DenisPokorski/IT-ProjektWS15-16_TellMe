package de.hdm.tellme.client.gui.editor;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.shared.bo.Hashtag;



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
					HashtagFormular nf = new HashtagFormular();
					nf.setzeHashtagAbo(selectionModel.getSelectedObject());
					RootPanel.get("content_right").clear();
					RootPanel.get("content_right").add(nf.gibAnlegenFormular());
					RootPanel.get("content_right").add(nf.gibBearbeitenFormular());


				}
			});
			break;

		case Nachrichtenuebersicht:
			selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
				public void onSelectionChange(SelectionChangeEvent event) {
					NeuigkeitenNachrichtenBaumModel.setzeHashtagFilter(selectionModel.getSelectedObject().hashtag, selectionModel);
					
				}
			});
			
			break;
			
		case HastagVerwaltung:
			selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
				public void onSelectionChange(SelectionChangeEvent event) {
					HashtagVerwaltungFomular nvf = new HashtagVerwaltungFomular();
					nvf.setzeHashtag(selectionModel.getSelectedObject());
					
					RootPanel.get("content_right").clear();
					RootPanel.get("content_right").add(nvf.gibInfoFormular());
					RootPanel.get("content_right").add(nvf.gibFormular());
				
				}
			});
			
			break;

		default:
			break;
		}
		

		ShowMorePagerPanel sPager = new ShowMorePagerPanel();
		sPager.setWidth("250px");
		sPager.setHeight("100%");
		sPager.setDisplay(cellList);

		RangeLabelPager fPager = new RangeLabelPager();
		fPager.setDisplay(cellList);

		FlowPanel fP = new FlowPanel();

		fP.add(new ScrollPanel(sPager));
		fP.add(new SimplePanel(fPager));
		
		RootPanel.get("content_left").clear();
		return fP;

	}
	

}
