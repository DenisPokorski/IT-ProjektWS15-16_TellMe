package de.hdm.tellme.client.gui.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.Schaukasten.NeuigkeitenEditor;
import de.hdm.tellme.client.gui.editor.NutzerZelle.ZellenObjekt;
import de.hdm.tellme.client.gui.report.ReportFormular1;
import de.hdm.tellme.client.gui.report.ReportFormular2;
import de.hdm.tellme.client.gui.report.ReportFormular3;
import de.hdm.tellme.client.gui.report.ReportFormular5;
import de.hdm.tellme.client.gui.report.ReportFormular6;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.NutzerAbonnement;

/**
 * 
 * @author Denis Feltrin, Björn Zimmermann Int i unterscheidet zwischen Editor
 *         und Report
 *
 */
public class NutzerCellList {
	Nutzer selektiererBenutzer = null;

	public FlowPanel generiereCellList(CellListModus modi, int i) {

		CellList<NutzerZelle.ZellenObjekt> cellList = new CellList<NutzerZelle.ZellenObjekt>(
				new NutzerZelle().new ZellenElement());

		NutzerDataProvider.gib(i).addDataDisplay(cellList);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<NutzerZelle.ZellenObjekt> selectionModel = new SingleSelectionModel<NutzerZelle.ZellenObjekt>();
		cellList.setSelectionModel(selectionModel);
		switch (modi) {
		case Einstellungen:

			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {

							NutzerFormular nf = new NutzerFormular();

							nf.setzeNutzerAbo(selectionModel
									.getSelectedObject());

							RootPanel.get("content_right").clear();
							RootPanel.get("content_right").add(
									nf.gibBeschreibung());
							RootPanel.get("content_right")
									.add(nf.gibFormular());
						}
					});
			break;

		case Nachrichtenuebersicht:

			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							NeuigkeitenNachrichtenBaumModel.setzeNutzerFilter(
									selectionModel.getSelectedObject().nutzer,
									selectionModel);
						}
					});

			break;

		case Report1_NachrichtNutzerZeitraum:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {

							ReportFormular1 rF = new ReportFormular1();
							rF.report1Generieren(selectionModel
									.getSelectedObject());

						}

					});

		case Report3_NachrichtNutzer:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							VerticalPanel vP = new VerticalPanel();
							RootPanel.get("content_right").clear();


							ReportFormular3 rF = new ReportFormular3();
							rF.report3Generieren(selectionModel
									.getSelectedObject());
							
							HTML headline = new HTML(
									" <div class='"
											+ "subline"
											+ "'><h2>Reportgenerator 3: Alle Nachrichten je Nutzer anzeigen </h2></div> ");
							HTML subtext = new HTML(
									" <div class='"
											+ "subtext"
											+ "'><h4> 		Um einen Report auszugeben, der alle Nachrichten von<b> einem bestimmten Nutzer</b>" + " darstellt, musst du <b>einen Nutzer </b>auswählen und darfst <b>keinen Zeitraum  </h4></div> ");


							vP.add(headline);
							vP.add(subtext);
							vP.add(rF.gibFormular());

							RootPanel.get("content_right").add(vP);

						}

					});
			break;

		case Report5_NutzerNutzerAbonnement:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							ReportFormular5 rF = new ReportFormular5();
							rF.report5Generieren(selectionModel
									.getSelectedObject());
							VerticalPanel vP = new VerticalPanel();

							RootPanel.get("content_right").clear();

							

							HTML headline = new HTML(
									" <div class='"
											+ "subline"
											+ "'><h2>Reportgenerator 5: Alle Nachrichten je Nutzer anzeigen</h2></div> ");
							HTML subtext = new HTML(
									" <div class='"
											+ "subtext"
											+ "'><h4> Der Report 5 gibt alle Nutzerabonnements aus.   </h4></div> ");

							vP.add(headline);
							vP.add(subtext);
							vP.add(rF.gibFormular());
							RootPanel.get("content_right").add(vP);


						}
					});

			break;

		case Report6_NutzerHashtagAbonnement:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {

							VerticalPanel vP = new VerticalPanel();
							RootPanel.get("content_right").clear();

							ReportFormular6 rF = new ReportFormular6();
							rF.report6Generieren(selectionModel
									.getSelectedObject());

							HTML headline = new HTML(
									" <div class='"
											+ "subline"
											+ "'><h2>Reportgenerator 6: Alle Hashtagabos je anzeigen</h2></div> ");
							HTML subtext = new HTML(
									" <div class='"
											+ "subtext"
											+ "'><h4> Der Report 6 gibt alle Hashtagabonnoments eines Nutzers in einen bestimmten Zeitraum zurück.   </h4></div> ");

							vP.add(headline);
							vP.add(subtext);
							vP.add(rF.gibFormular());

							RootPanel.get("content_right").add(vP);
						}
					});

			break;

		default:
			break;
		}

		ShowMorePagerPanel sPager = new ShowMorePagerPanel();
		sPager.setWidth("250px");
		sPager.setHeight("100%");
		sPager.setStylePrimaryName("pagerStyleBlue");
		sPager.setDisplay(cellList);

		RangeLabelPager fPager = new RangeLabelPager();
		fPager.setDisplay(cellList);

		FlowPanel fP = new FlowPanel();
		fP.add(new HTML("Liste aller Nutzer:"));

		fP.add(new ScrollPanel(sPager));
		fP.add(new SimplePanel(fPager));
		return fP;

	}

}
