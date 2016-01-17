package de.hdm.tellme.client.gui.report;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.NutzerCellList;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.client.gui.editor.NutzerZelle.ZellenObjekt;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * TODO
 */
/**
 * 
 * 
 * 
 * 
 * Dieser Report zeigt alle Nachrichten eines Nutzers an. Deshalb benötigt die GUI
 * von Report 3 eine NutzerCellList um einen Nutzer auszuwählen.
 * 
 * @author Zimmerman & Alex Homann
 * @version 1.1
 * 
 */

public class Report3Gui extends VerticalPanel {
	/*
	 * Es werden in einem Vertical Panel die verschiedenen benötigten Elemente
	 * dargestellt. Dies sind die NutzerCellList um den Nutzer zu wählen, und
	 * ein Button der die Funktion ReportGenerieren beinhaltet. Außerdem gibt es
	 * eine Überschrift und eine Beschreibung für den Report 3.
	 */

	private Nutzer nutzer = null;

	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift3 = new Label("Report3: Nachrichten abfragen");
	private HorizontalPanel reportPanel3 = new HorizontalPanel();
	private VerticalPanel reportPanel3_left = new VerticalPanel();
	private VerticalPanel reportPanel3_right = new VerticalPanel();
	VerticalPanel vP = new VerticalPanel();

	private Button report3Generieren = new Button("Report 3 generieren");

	public void report1Generieren(NutzerZelle.ZellenObjekt ZellenObjekt) {
		this.nutzer = ZellenObjekt.nutzer;

	}

	/**
	 * Die onLoad-Methode wird verwendet um in der Seite die verschiedenen
	 * Panels anzuordnen. Sie startet beim Ausführen der Seite, da sie das
	 * Pendant zur <code>main()</code>-Methode einer normalen Java Applikation
	 * darstellt
	 */

	public void onLoad() {

		HTML headline = new HTML(
				" <div class='"
						+ "subline"
						+ "'><h2>Reportgenerator 3: Alle Nachrichten je Nutzer anzeigen \n </h2></div> ");
		HTML subtext = new HTML(
				" <div class='"
						+ "subtext"
						+ "'><h4> 		Um einen Report auszugeben, der alle Nachrichten von<b> einem bestimmten Nutzer</b>"
						+ " darstellt, musst du <b>einen Nutzer </b>auswählen und darfst <b>keinen Zeitraum  </h4></div> ");

		/*
		 * Die Panels werden anschaulich angeordnet.
		 */

		RootPanel.get().clear();
		reportPanel.add(ueberSchrift3);
		reportPanel3.add(reportPanel3_left);
		reportPanel3.add(reportPanel3_right);

		/**
		 * Dem <code>reportPanel3_left</code> wird die
		 * <code>NutzerCellList</code> im modi Report3_NachrichtNutzer
		 * zugewiesen, dies geschieht im Modus 1, da 1 den Report darstellt.
		 */

		reportPanel3_left.add(new NutzerCellList().generiereCellList(
				CellListModus.Report3_NachrichtNutzer, 1));

		reportPanel.add(reportPanel3);
		/*
		 * Das RootPanel wird gesäubert und die verschiedenen Elemente für
		 * Report 3 zugeordnet.
		 */

		// reportPanel3_right.add(headline);
		// reportPanel3_right.add(subtext);

		vP.add(headline);
		vP.add(subtext);

		RootPanel.get("content").clear();
		RootPanel.get("content_left").clear();
		RootPanel.get("content_right").clear();
		RootPanel.get("content_right").add(reportPanel3_right);
		RootPanel.get("content_right").add(vP);

		RootPanel.get("content_left").add(reportPanel3_left);

		/*
		 * Der Button <code>report3Generieren</code> bekommt eine Funktion,
		 * damit der Report 3 generiert werden kann.
		 */

	}

}
