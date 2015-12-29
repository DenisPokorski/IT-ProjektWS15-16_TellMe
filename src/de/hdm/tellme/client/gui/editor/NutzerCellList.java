package de.hdm.tellme.client.gui.editor;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.client.gui.report.ReportFormular1_3;
import de.hdm.tellme.client.gui.report.ReportFormular2;
import de.hdm.tellme.client.gui.report.ReportFormular3;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.NutzerAbonnement;

public class NutzerCellList {
	NutzerAbonnement nA = new NutzerAbonnement();
	Nutzer selektiererBenutzer = null;
	
	public FlowPanel generiereCellList(CellListModus modi) {

		CellList<NutzerZelle.ZellenObjekt> cellList = new CellList<NutzerZelle.ZellenObjekt>(
				new NutzerZelle().new ZellenElement());

		NutzerDataProvider.gib().addDataDisplay(cellList);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<NutzerZelle.ZellenObjekt> selectionModel = new SingleSelectionModel<NutzerZelle.ZellenObjekt>();
		cellList.setSelectionModel(selectionModel);
		switch (modi) {
		case Einstellungen:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							
							// TODO:rechtel aktuallisieren
							NutzerFormular nf = new NutzerFormular();
							nf.setzeNutzerAbo(selectionModel
									.getSelectedObject());
							RootPanel.get("content_right").clear();
							RootPanel.get("content_right")
									.add(nf.gibFormular());
						}
					});
			break;

		case Nachrichtenuebersicht:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							MenuBarEditor.gibansichtNeuigkeiten()
									.FilterNachBenutzer(
											selectionModel.getSelectedObject());
						}
					});

			break;
			
		case Report1_3_NachrichtNutzer:
			selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event){
						
						ReportFormular1_3 rF = new ReportFormular1_3();
						
					}
					
				});
			
//		case Report1_NachrichtNutzerZeitraum:
//			selectionModel
//					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//						public void onSelectionChange(SelectionChangeEvent event) {
//														
//							ReportFormular1_1 rF = new ReportFormular1_1();
//							rF.reportGenerieren(selectionModel.getSelectedObject());
//							RootPanel.get("content_right").clear();
//							RootPanel.get("content_right").add(rF.gibFormular());
//							
//						}
//					});
//
//			break;

			
		case Report3_NutzerHashtagAbonnement:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
														
							ReportFormular3 rF = new ReportFormular3();
							rF.report3Generieren(selectionModel.getSelectedObject());
							RootPanel.get("content_left").clear();
							RootPanel.get("content_left").add(rF.gibFormular());
							
						}
					});

			break;
			
		case Report2_NutzerNutzerAbonnement:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
														
							ReportFormular2 rF = new ReportFormular2();
							rF.report2Generieren(selectionModel.getSelectedObject());
							RootPanel.get("content_left").clear();
							RootPanel.get("content_left").add(rF.gibFormular());
							
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
