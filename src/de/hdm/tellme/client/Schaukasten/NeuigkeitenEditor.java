package de.hdm.tellme.client.Schaukasten;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.HashtagCellList;
import de.hdm.tellme.client.gui.editor.HashtagZelle;
import de.hdm.tellme.client.gui.editor.NeuigkeitenJaNeinDialogbox;
import de.hdm.tellme.client.gui.editor.NeuigkeitenNachrichtDialogbox;
import de.hdm.tellme.client.gui.editor.NeuigkeitenNachrichtenBaumModel;
import de.hdm.tellme.client.gui.editor.NeuigkeitenTeilnehmerBearbeitenDialogbox;
import de.hdm.tellme.client.gui.editor.NutzerCellList;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class NeuigkeitenEditor extends VerticalPanel {

	private static Unterhaltung ausgewaehlteUnterhaltung;
	private static  Nachricht ausgewaehlteNachricht;

	private static HashtagCellList hsCL = new HashtagCellList();
	private static NutzerCellList nuCL = new NutzerCellList();
	
	public NeuigkeitenEditor() {
	}

	public void FilterNachBenutzer(NutzerZelle.ZellenObjekt nah) {
	}

	public void FilterNachHashtag(HashtagZelle.ZellenObjekt nah) {
	}

	public void onLoad() {
		
		setzeOptionenButton(null, null);

//		Button btnNeueNachricht = new Button("+ Nachricht");
//
//		btnNeueNachricht.setStylePrimaryName("neueNchrichtBtn");

		TreeViewModel model = new NeuigkeitenNachrichtenBaumModel();
		/*
		 * Create the tree using the model. We use <code>null</code> as the
		 * default value of the root node. The default value will be passed to
		 * CustomTreeModel#getNodeInfo();
		 */
		CellTree tree = new CellTree(model, null);
		tree.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		ScrollPanel scPanel = new ScrollPanel();
		scPanel.setHeight("600px");
		scPanel.add(tree);

		RootPanel.get("content_right").add(scPanel);

		
		
	}

	public static void showDialogBox(DialogBox anzuzeigendeBox) {
		anzuzeigendeBox.center();
		anzuzeigendeBox.show();
	}

	public static void setzeOptionenButton(Unterhaltung _ausgewaehlteUnterhaltung, Nachricht _ausgewaehlteNachricht) {
		RootPanel.get("ButtonBar").clear();


		//###################### Nachrichtenoptionen
		HorizontalPanel hpHeadline = new HorizontalPanel();
		hpHeadline.setStylePrimaryName("hpHeadline");
		hpHeadline.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		
		HTML headline = new HTML(" <h2>Alle Neuigkeiten</h2> ");
		hpHeadline.add(headline);
		
		Button btnNeueNachricht = new Button("+ Nachricht");
		btnNeueNachricht.setStylePrimaryName("neueNchrichtBtn");
		btnNeueNachricht.addClickHandler(btnNeueNachrichtClickHandler);
		hpHeadline.add(btnNeueNachricht);
		
		Button btnAktualisieren = new Button("Aktualisieren");
		btnAktualisieren.setStylePrimaryName("aktualisierenBtn");
		btnAktualisieren.addClickHandler(btnUnterhaltungenAktualisierenClickHandler);
		hpHeadline.add(btnAktualisieren);
		
		
		
		
		HorizontalPanel hpOptionen = new HorizontalPanel();
		hpOptionen.setStylePrimaryName("hpOptionenButtonbar");
		hpOptionen.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);


		
//		Label sublineHpOtion = new Label("Unterhaltung bearbeiten");
//		hpOptionen.add(sublineHpOtion);
		
		Button btnAntworten =new Button("Unterhaltung Antworten");
		btnAntworten.setEnabled(false);
		btnAntworten.setStylePrimaryName("btnUnterhaltungVerlassen"); 
		btnAntworten.addClickHandler(btnAntwortenClickHandler);
		hpOptionen.add(btnAntworten);
		
		Button btnTeilnehmerBearbeiten =new Button("Teilnehmer bearbeiten");
		btnTeilnehmerBearbeiten.setStylePrimaryName("btnTeilnehmerBearbeiten");
		btnTeilnehmerBearbeiten.addClickHandler(btnTeilnehmerBearbeitenClickHandler);
		hpOptionen.add(btnTeilnehmerBearbeiten);

		Button btnUnterhaltungVerlassen =new Button("Unterhaltung verlassen");
		btnUnterhaltungVerlassen.setStylePrimaryName("btnUnterhaltungVerlassen");
		btnUnterhaltungVerlassen.addClickHandler(btnUnterhaltungVerlassenClickHandler);
		hpOptionen.add(btnUnterhaltungVerlassen);

//		Button btnUnterhaltungAktualisieren =new Button("Aktualisieren");
//		btnUnterhaltungAktualisieren.setStylePrimaryName("btnAktualisieren");
//		btnUnterhaltungVerlassen.addClickHandler(btnUnterhaltungenAktualisierenClickHandler);
//		hpOptionen.add(btnUnterhaltungAktualisieren);
 
		
	
		
		
		
		Button btnNachrichtBearbeiten = new Button("Nachricht bearbeiten");
		btnNachrichtBearbeiten.addClickHandler(btnNachrichtBearbeitenClickHandler);
		btnNachrichtBearbeiten.setStylePrimaryName("btnUnterhaltungVerlassen"); 
		hpOptionen.add(btnNachrichtBearbeiten);
		
		Button btnNachrichtLoeaschen = new Button("Nachricht löschen");
		btnNachrichtLoeaschen.setStylePrimaryName("btnUnterhaltungVerlassen"); 
		btnNachrichtLoeaschen.addClickHandler(btnNachrichtLoeschenClickHandler);
		hpOptionen.add(btnNachrichtLoeaschen);
		
		if (_ausgewaehlteNachricht == null) {
			ausgewaehlteNachricht = null;
			btnNachrichtBearbeiten.setEnabled(false);
			btnNachrichtLoeaschen.setEnabled(false);
		} else {
			ausgewaehlteNachricht = _ausgewaehlteNachricht;
			btnNachrichtBearbeiten.setEnabled(true);
			btnNachrichtLoeaschen.setEnabled(true);
		}

		
		RootPanel.get("ButtonBar").add(hpHeadline);
		//RootPanel.get("content_right").add(hpHeadline);
		RootPanel.get("ButtonBar").add(hpOptionen);
	}
		//###################### Unterhaltungsoptionen
		HorizontalPanel hpUnterhaltungsOptionen = new HorizontalPanel();
		
		Button btnAntworten = new Button("Unterhaltung beantworten");
//		hpUnterhaltungsOptionen.add(btnAntworten);		
//
//		Button btnTeilnehmerBearbeiten = new Button("Teilnehmer bearbeiten");
//		btnTeilnehmerBearbeiten.addClickHandler(btnTeilnehmerBearbeitenClickHandler);
//		hpUnterhaltungsOptionen.add(btnTeilnehmerBearbeiten);
//
//		Button btnUnterhaltungVerlassen = new Button("Unterhaltung verlassen");
//		btnUnterhaltungVerlassen.addClickHandler(btnUnterhaltungenAktualisierenClickHandler);
//		hpUnterhaltungsOptionen.add(btnUnterhaltungVerlassen);
//
//		Button btnUnterhaltungenAktualisieren = new Button("Unterhaltungen aktualisieren");
//		btnUnterhaltungenAktualisieren.addClickHandler(btnUnterhaltungenAktualisierenClickHandler);
//		hpUnterhaltungsOptionen.add(btnUnterhaltungenAktualisieren);
//
//		if (_ausgewaehlteUnterhaltung == null) {
//			ausgewaehlteUnterhaltung = null;
//			btnAntworten.setEnabled(false);
//			btnTeilnehmerBearbeiten.setEnabled(false);
//			btnUnterhaltungVerlassen.setEnabled(false);
//			
//		} else {
//			ausgewaehlteUnterhaltung = _ausgewaehlteUnterhaltung;
//			btnAntworten.setEnabled(true);
//			btnTeilnehmerBearbeiten.setEnabled(true);
//			btnUnterhaltungVerlassen.setEnabled(true);
//
//		}
//		
//		RootPanel.get("ButtonBar").add(hpUnterhaltungsOptionen);
//	}

	public static VerticalPanel gibFilterPanel(){
		VerticalPanel vpFilterPanel = new VerticalPanel();
		vpFilterPanel.add(nuCL.generiereCellList(CellListModus.Nachrichtenuebersicht));
		vpFilterPanel.add(new Label("________________________________________"));
		vpFilterPanel.add(hsCL.generiereCellList(CellListModus.Nachrichtenuebersicht));
		vpFilterPanel.add(new Label("________________________________________"));
		Button btnFilterZuruecksetzen = new Button("Filter zurücksetzen");
		btnFilterZuruecksetzen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				NeuigkeitenNachrichtenBaumModel.setzeKeinenFilter();				
			}
		});
		vpFilterPanel.add(btnFilterZuruecksetzen);
		
		return vpFilterPanel;
	}
	
	static ClickHandler btnNeueNachrichtClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox().getNeueNachrichtDialogbox());
		}
	};
	static ClickHandler btnAntwortenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox().getAntwortNachrichtDialogbox(ausgewaehlteUnterhaltung));
		}
	};
	static ClickHandler btnNachrichtBearbeitenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenNachrichtDialogbox().getNachrichtBearbeitenDialogbox(ausgewaehlteNachricht));
		}
	};
	static ClickHandler btnNachrichtLoeschenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenJaNeinDialogbox().getNachrichtLoeschenDialogBox(ausgewaehlteNachricht));
		}
	};
	static ClickHandler btnTeilnehmerBearbeitenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenTeilnehmerBearbeitenDialogbox().getTeilnehmerBearbeiten(ausgewaehlteUnterhaltung));
		}
	};
	static ClickHandler btnUnterhaltungVerlassenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenEditor.showDialogBox(new NeuigkeitenJaNeinDialogbox().getUnterhaltungVerlassenDialogBox(ausgewaehlteUnterhaltung));
		}
	};
	static ClickHandler btnUnterhaltungenAktualisierenClickHandler = new ClickHandler() {
		public void onClick(ClickEvent event) {
			NeuigkeitenNachrichtenBaumModel.ladeUnterhaltungenAsync();
		}
	};
	
	
	

}
