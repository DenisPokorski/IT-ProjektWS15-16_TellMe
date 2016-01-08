package de.hdm.tellme.client.gui.editor;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.shared.bo.Hashtag;

/**
 * Die Klasse <class>HashtagCellList</class> wird benötigt um die Hashtags in
 * einer CellList anzuzeigen. Da die selbe CellList für verschiedene Fälle
 * benötigt wird, wird sie durch die verschiedenen modi mittels eines case
 * switchs verwendet.
 * 
 * @author Alex
 *
 */

public class HashtagCellList {

	Hashtag selektiertesHashstag = null;

	public FlowPanel generiereCellList(CellListModus modi) {
		/**
		 * Um ein bestimmtes Objekt aus der HashtagCellList auszuwählen wird die
		 * CellList aufgerufen. Der Rückgabewert ist ein vom Typ ZellenObjekt
		 * der Klasse <class>ZellenObjekt</class>, dass eine Nested-Class in der
		 * Klasse <class>HashtagZelle</class> darstellt.
		 */
		CellList<HashtagZelle.ZellenObjekt> cellList = new CellList<HashtagZelle.ZellenObjekt>(
				new HashtagZelle().new ZellenElement());
		/**
		 * Der
		 */
		HashtagDataProvider.gib().addDataDisplay(cellList);
		/**
		 * Hier wird ein Selection Model hinzugefügt, damit man Zellen auswählen
		 * kann.
		 */
		// Add a selection model so we can select cells.
		final SingleSelectionModel<HashtagZelle.ZellenObjekt> selectionModel = new SingleSelectionModel<HashtagZelle.ZellenObjekt>();
		cellList.setSelectionModel(selectionModel);
		switch (modi) {

		/**
		 * Der Fall Einstellungen wird genutzt, falls ein Hashtag in den
		 * Einstellungen ausgewählt wird. Wenn ein Hashtag angeklickt wird, wird
		 * auf der rechten Seite der Content gecleart, also gelöscht und es
		 * werden die Formulare gibBeschreibungHtAbo() und
		 * gibBearbeitenFormular() geladen.
		 */
		case Einstellungen:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							HashtagFormular nf = new HashtagFormular();
							nf.setzeHashtagAbo(selectionModel
									.getSelectedObject());
							RootPanel.get("content_right").clear();
							RootPanel.get("content_right").add(
									nf.gibBeschreibungHtAbo());
							RootPanel.get("content_right").add(
									nf.gibBearbeitenFormular());

						}
					});
			break;

		/**
		 * Der Fall Nachrichtenuebersicht wird genutzt, falls ein Hashtag in der
		 * Nachrichtenübersicht ausgewählt wird. Wenn ein Hashtag angeklickt
		 * wird, wird das NeuigkeitenNachrichtenBaumModel so verändert, dass
		 * durch die Methode setzeHashtagFilter,der NeuigkeitenNachrichtenBaum
		 * die jeweiligen Hashtags angezeigt werden.
		 */

		case Nachrichtenuebersicht:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							NeuigkeitenNachrichtenBaumModel.setzeHashtagFilter(
									selectionModel.getSelectedObject().hashtag,
									selectionModel);

						}
					});

			break;

		/**
		 * Der Fall HashtagVerwaltung wird genutzt, falls ein Hashtag in der
		 * HashtagVerwaltung ausgewählt wird. Wenn ein Hashtag angeklickt
		 * wird,wird ein neues Objekt des HashtagVerwaltungFomular erstellt und
		 * für das aktuell ausgewählte Hashtag erstellt. Danach wird auf der
		 * rechten Seite der Content gecleart, also gelöscht und es werden die
		 * Formulare gibBeschreibungHtVerwaltung() und gibFormular() für das
		 * vorher erstellte HashtagVerwaltungsFomular geladen.
		 */

		case HashtagVerwaltung:
			selectionModel
					.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							HashtagVerwaltungFomular nvf = new HashtagVerwaltungFomular();
							nvf.setzeHashtag(selectionModel.getSelectedObject());

							RootPanel.get("content_right").clear();
							RootPanel.get("content_right").add(
									new HashtagFormular()
											.gibBeschreibungHtVerwaltung());
							RootPanel.get("content_right").add(
									nvf.gibFormular());

						}
					});

			break;

		default:
			break;
		}
		/**
		 * TODO
		 */
		ShowMorePagerPanel sPager = new ShowMorePagerPanel();
		sPager.setWidth("250px");
		sPager.setHeight("100%");
		sPager.setDisplay(cellList);
		sPager.setStylePrimaryName("pagerStyleBlue");
		/**
		 * TODO
		 */
		RangeLabelPager fPager = new RangeLabelPager();
		fPager.setDisplay(cellList);
		/**
		 * Hier wird ein FlowPanel hinzugefügt, dass die Überschrift
		 * "Liste aller Hasthags:" in Form eines HTML-Codes erhält.
		 */
		FlowPanel fP = new FlowPanel();
		fP.add(new HTML("Liste aller Hashtags:"));
		/**
		 * Das FlowPanel wird dem Scroll- und dem SimplePanel hinzugefügt
		 */
		fP.add(new ScrollPanel(sPager));
		fP.add(new SimplePanel(fPager));
		/**
		 * Die linke Seite des RootPanels wird neu geladen.
		 */
		RootPanel.get("content_left").clear();
		return fP;

	}

}
