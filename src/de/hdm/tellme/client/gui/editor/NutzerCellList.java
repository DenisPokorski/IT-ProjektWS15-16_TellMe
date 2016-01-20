package de.hdm.tellme.client.gui.editor;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.client.gui.report.Report1Gui;
import de.hdm.tellme.client.gui.report.ReportFormular3;
import de.hdm.tellme.client.gui.report.ReportFormular5;
import de.hdm.tellme.client.gui.report.ReportFormular6;
import de.hdm.tellme.client.gui.report.ReportFormular7;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * Die Klasse <class>NutzerCellList</class> wird benötigt um die Nutzer in einer
 * CellList anzuzeigen. Da die selbe CellList für verschiedene Fälle benötigt
 * wird, wird sie durch die verschiedenen modi mittels eines case switchs
 * verwendet. Der int-Wert i wird verwendet um zwischen Editor und Report zu
 * unterscheiden.
 * 
 * @author Denis Feltrin, Björn Zimmermann
 *
 */
public class NutzerCellList {
	Nutzer selektiererBenutzer = null;

	public FlowPanel generiereCellList(CellListModus modi, int i) {

		/**
		 * Um ein bestimmtes Objekt aus der NutzerCellList auszuwählen wird die
		 * CellList aufgerufen. Der Rückgabewert ist ein vom Typ ZellenObjekt
		 * der Klasse <class>ZellenObjekt</class>, dass eine Nested-Class in der
		 * Klasse <class>NutzerZelle</class> darstellt.
		 */
		CellList<NutzerZelle.ZellenObjekt> cellList = new CellList<NutzerZelle.ZellenObjekt>(
				new NutzerZelle().new ZellenElement());

		/**
		 * Der NutzerDataProvider stellt die NutzerCellLists zur Verfügung.
		 */
		NutzerDataProvider.gib(i).addDataDisplay(cellList);

		/**
		 * Hier wird ein Selection Model hinzugefügt, damit man Zellen auswählen
		 * kann.
		 */
		// Add a selection model so we can select cells.
		final SingleSelectionModel<NutzerZelle.ZellenObjekt> selectionModel = new SingleSelectionModel<NutzerZelle.ZellenObjekt>();
		cellList.setSelectionModel(selectionModel);
		switch (modi) {

		/**
		 * Der Fall Einstellungen wird genutzt, falls ein Nutzer-Objekt in den
		 * Einstellungen ausgewählt wird. Wenn ein Nutzer angeklickt wird, wird
		 * auf der rechten Seite der Content gecleart, also gelöscht und es
		 * werden die Formulare gibBeschreibung() und gibFormular() geladen.
		 */
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

		/**
		 * Der Fall Nachrichtenuebersicht wird genutzt, falls ein Nutzer-Objekt
		 * in der Nachrichtenübersicht ausgewählt wird. Wenn ein Hashtag
		 * angeklickt wird, wird das NeuigkeitenNachrichtenBaumModel so
		 * verändert, dass durch die Methode setzeNutzerFilter,der
		 * NeuigkeitenNachrichtenBaum des jeweiligen Nutzers angezeigt werden.
		 */
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

		/**
		 * Der Fall Report1_NachrichtNutzerZeitraum wird genutzt, falls ein
		 * Nutzer-Objekt in der Report1Gui ausgewählt wird. Wenn ein Nutzer
		 * angeklickt wird, wird das ReportFormular1 angezeigt.
		 */
		case Report1_NachrichtNutzerZeitraum:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							Report1Gui rF = new Report1Gui();
							rF.report1Generieren(selectionModel
									.getSelectedObject());
						}
					});
			break;

		/**
		 * Der Fall Report3_NachrichtNutzer wird genutzt, falls ein
		 * Nutzer-Objekt in der Report3Gui ausgewählt wird. Wenn ein Nutzer
		 * angeklickt wird, wird das ReportFormular3 angezeigt.
		 */

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
											+ "'><h4>Um einen Report auszugeben, der alle Nachrichten von<b> einem bestimmten Nutzer</b>"
											+ " darstellt, musst du <b>einen Nutzer </b>auswählen und darfst <b>keinen Zeitraum </b> auswählen. </h4></div> ");

							vP.add(headline);
							vP.add(subtext);
							vP.add(rF.gibFormular());

							RootPanel.get("content_right").add(vP);
						}
					});
			break;

		/**
		 * Der Fall Report5:NutzerNutzer wird genutzt, falls ein Nutzer-Objekt
		 * in der Report5Gui ausgewählt wird. Wenn ein Nutzer angeklickt wird,
		 * wird das ReportFormular5 angezeigt.
		 */
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

		/**
		 * Der Fall Report6_NutzerHashtagAbonnement wird genutzt, falls ein
		 * Nutzer-Objekt in der Report6Gui ausgewählt wird. Wenn ein Nutzer
		 * angeklickt wird, wird das ReportFormular6 angezeigt.
		 */
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
											+ "'><h2>Reportgenerator 6: Alle Hashtagabos je Nutzer anzeigen</h2></div> ");
							HTML subtext = new HTML(
									" <div class='"
											+ "subtext"
											+ "'><h4> Der Report 6 gibt alle Hashtagabonnements eines Nutzers in einem bestimmten Zeitraum zurück.   </h4></div> ");

							vP.add(headline);
							vP.add(subtext);
							vP.add(rF.gibFormular());

							RootPanel.get("content_right").add(vP);
						}
					});
			break;

		case Report7_AlleNutzerDieDemAusgewaehltenNutzerFolgen:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							VerticalPanel vP = new VerticalPanel();
							RootPanel.get("content_right").clear();
							ReportFormular7 rF = new ReportFormular7();
							rF.report7Generieren(selectionModel.getSelectedObject());
							vP.add(rF.gibFormular());

							RootPanel.get("content_right").add(vP);

						}
					});

			break;
		default:
		}

		/**
		 * Es wird ein neues <code>ShowMorePagerPanel</code> erstellt, damit die
		 * Liste automatisch erweitert wird. Die Panels werden geordnet und
		 * gestylet.
		 */
		ShowMorePagerPanel sPager = new ShowMorePagerPanel();
		sPager.setWidth("250px");
		sPager.setHeight("100%");
		sPager.setStylePrimaryName("pagerStyleBlue");
		sPager.setDisplay(cellList);

		/**
		 * Es wird ein neuer <code>RangeLabelPager</code> erstellt, damit die
		 * aktuelle Größe nicht verändert werden kann.
		 */
		RangeLabelPager fPager = new RangeLabelPager();
		fPager.setDisplay(cellList);

		/**
		 * Hier wird ein FlowPanel hinzugefügt, dass die Überschrift
		 * "Liste aller Nutzer:" in Form eines HTML-Codes erhält.
		 */
		FlowPanel fP = new FlowPanel();
		fP.add(new HTML("Liste aller Nutzer:"));

		/**
		 * Das FlowPanel wird dem Scroll- und dem SimplePanel hinzugefügt
		 */
		fP.add(new ScrollPanel(sPager));
		fP.add(new SimplePanel(fPager));
		return fP;

	}

}
