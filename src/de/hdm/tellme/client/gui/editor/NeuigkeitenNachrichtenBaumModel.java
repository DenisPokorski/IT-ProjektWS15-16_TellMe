package de.hdm.tellme.client.gui.editor;

import java.util.Vector;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.Schaukasten.NeuigkeitenEditor;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class NeuigkeitenNachrichtenBaumModel implements TreeViewModel {
	private static Vector<UnterhaltungsNachicht> alleUnterhaltungen = new Vector<UnterhaltungsNachicht>();
	private static Vector<UnterhaltungsNachicht> alleUnterhaltungenGefiltert = new Vector<UnterhaltungsNachicht>();
	private static ListDataProvider<UnterhaltungsNachicht> dataProvider;

	private static Nutzer nutzerFilter;
	static SingleSelectionModel<NutzerZelle.ZellenObjekt> selectionModelNutzer = null;

	private static Hashtag hashtagFilter;
	static SingleSelectionModel<HashtagZelle.ZellenObjekt> selectionModelHashtag = null;

	// RPC Methode, die auf Client in einer bestimmten Runtime ausgeführt wird
	// um Daten mit dem Server auszutauschen
	private final static EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	final SingleSelectionModel<UnterhaltungsNachicht> unterhaltungSelectionModel = new SingleSelectionModel<UnterhaltungsNachicht>();
	final SingleSelectionModel<Nachricht> nachrichtenSelectionModel = new SingleSelectionModel<Nachricht>();

	private static boolean selektiereNeuesElement = false;

	/**
	 * This selection model is shared across all leaf nodes. A selection model
	 * can also be shared across all nodes in the tree, or each set of child
	 * nodes can have its own instance. This gives you flexibility to determine
	 * how nodes are selected.
	 */

	public NeuigkeitenNachrichtenBaumModel() {
		
		ladeUnterhaltungenAsync();
		unterhaltungSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				if (selektiereNeuesElement == false) {
					selektiereNeuesElement = true;
					if (nachrichtenSelectionModel.getSelectedObject() != null) {
						nachrichtenSelectionModel.setSelected(nachrichtenSelectionModel.getSelectedObject(), false);
					} else {
						selektiereNeuesElement = false;
					}

					NeuigkeitenEditor.setzeOptionenButton(unterhaltungSelectionModel.getSelectedObject().u, unterhaltungSelectionModel.getSelectedObject().n);
				} else {
					selektiereNeuesElement = false;
				}
			}
		});

		nachrichtenSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				if (selektiereNeuesElement == false) {
					selektiereNeuesElement = true;
					if (unterhaltungSelectionModel.getSelectedObject() != null) {
						unterhaltungSelectionModel.setSelected(unterhaltungSelectionModel.getSelectedObject(), false);
					} else {
						selektiereNeuesElement = false;
					}

					NeuigkeitenEditor.setzeOptionenButton(null, nachrichtenSelectionModel.getSelectedObject());
				} else {
					selektiereNeuesElement = false;
				}
			}
		});

	}

	/**
	 * Get the {@link NodeInfo} that provides the children of the specified
	 * value.
	 */
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			// LEVEL 0.
			// We passed null as the root value. Return the composers.
			// Create a data provider that contains the list of composers.
			dataProvider = new ListDataProvider<UnterhaltungsNachicht>(alleUnterhaltungenGefiltert);
			// Create a cell to display a composer.

			Cell<UnterhaltungsNachicht> cell = new AbstractCell<UnterhaltungsNachicht>() {

				@Override
				public void render(Context context, UnterhaltungsNachicht value, SafeHtmlBuilder sb) {
					if (value != null) {
						NeuigkeitenNachrichtenZelle nnz = new NeuigkeitenNachrichtenZelle(value.u);
						nnz.render(context, value.n, sb);
					}
				}
			};

			// Return a node info that pairs the data provider and the cell.
			return new DefaultNodeInfo<UnterhaltungsNachicht>(dataProvider, cell, unterhaltungSelectionModel, null);
		} else if (value instanceof UnterhaltungsNachicht) {
			// LEVEL 1.

			// Erste Nachricht aus Liste aller Nachrichten entfernen,
			// da diese schon als Unterhaltungs Rahmen ausgegeben wird
			Vector<Nachricht> alleNachrichtenAusserErste = ((UnterhaltungsNachicht) value).u.getAlleNachrichten();
			if (alleNachrichtenAusserErste.size() > 1) {
				alleNachrichtenAusserErste.remove(0);

				ListDataProvider<Nachricht> dataProvider = new ListDataProvider<Nachricht>(alleNachrichtenAusserErste);
				Cell<Nachricht> cell = new NeuigkeitenNachrichtenZelle(((UnterhaltungsNachicht) value).u);

				return new DefaultNodeInfo<Nachricht>(dataProvider, cell, nachrichtenSelectionModel, null);
			}
			return null;
		}
		return null;
	}

	/**
	 * Check if the specified value represents a leaf node. Leaf nodes cannot be
	 * opened.
	 */
	public boolean isLeaf(Object value) {

		// The leaf nodes are the songs, which are Strings.
		if (value instanceof Nachricht) {
			// Wenn value eine Nachricht ist, return true -> knoten ist ein
			// "Blatt" und hat keine Kinder
			return true;
		}

		if (value instanceof UnterhaltungsNachicht) {
			if (((UnterhaltungsNachicht) value).u.getAlleNachrichten().size() == 1) {
				// Wenn value eine Unterhaltungsnachricht ist aber nicht mehr
				// als eine Nachricht enthält,
				// return true -> knoten ist ein "Blatt" und hat keine Kinder
				return true;
			}
		}
		return false;
	}

	public static void ladeUnterhaltungenAsync() {

		asyncObj.getAlleRelevantenUnterhaltungen(TellMe.gibEingeloggterBenutzer().getUser().getId(), new AsyncCallback<Vector<Unterhaltung>>() {

			@Override
			public void onSuccess(Vector<Unterhaltung> result) {
				alleUnterhaltungen.clear();
				for (Unterhaltung unterhaltung : result) {
					try {

						UnterhaltungsNachicht un = new UnterhaltungsNachicht(unterhaltung);
						alleUnterhaltungen.addElement(un);
					} catch (Exception ex) {
						Window.alert("Fehler beim hinzufuegen einer Nachricht" + ex.toString());
						ex.printStackTrace();
					}
				}

				UnterhaltungsListeErneuern();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim laden der Nachrichten");

			}
		});

		//

	}

	public static void setzeNutzerFilter(Nutzer n, SingleSelectionModel<NutzerZelle.ZellenObjekt> selectionModel) {
		nutzerFilter = n;
		selectionModelNutzer = selectionModel;

		if (hashtagFilter != null) {
			hashtagFilter = null;
			selectionModelHashtag.setSelected(selectionModelHashtag.getSelectedObject(), false);
		}

		UnterhaltungsListeErneuern();
	}

	public static void setzeHashtagFilter(Hashtag h, SingleSelectionModel<HashtagZelle.ZellenObjekt> selectionModel) {
		hashtagFilter = h;
		selectionModelHashtag = selectionModel;

		if (nutzerFilter != null) {
			nutzerFilter = null;
			selectionModelNutzer.setSelected(selectionModelNutzer.getSelectedObject(), false);
		}
		UnterhaltungsListeErneuern();
	}

	public static void setzeKeinenFilter() {

		if (nutzerFilter != null) {
			nutzerFilter = null;
			selectionModelNutzer.setSelected(selectionModelNutzer.getSelectedObject(), false);
		}

		if (hashtagFilter != null) {
			hashtagFilter = null;
			selectionModelHashtag.setSelected(selectionModelHashtag.getSelectedObject(), false);
		}

		selectionModelNutzer = null;
		selectionModelHashtag = null;
		hashtagFilter = null;
		nutzerFilter = null;

		UnterhaltungsListeErneuern();
	}

	public static void UnterhaltungsListeErneuern() {

		dataProvider.getList().clear();

//		// NutzerFilter
//		if (nutzerFilter != null)
//			Window.alert("Wird nach nutzer " + nutzerFilter.getVorname() + " " + nutzerFilter.getNachname() + " gefiltert");
//		// HashtagFilter
//		else if (hashtagFilter != null)
//			Window.alert("Wird nach hashtag " + hashtagFilter.getSchlagwort() + " gefiltert");
//		else
//			Window.alert("Ungefiltert");

		for (UnterhaltungsNachicht unterhaltungsNachicht : alleUnterhaltungen) {
			boolean durchFilterAussortiert = false;

			// NUTZERFILTER
			if (nutzerFilter != null) {
				// Prüfe ob Filternutzer teilnehmer ist
				boolean FilterNutzerIstSenderOderTeilnehmer = false;
				for (Nutzer teilnehmer : unterhaltungsNachicht.u.getTeilnehmer()) {
					if (teilnehmer.getId() == nutzerFilter.getId()) {
						FilterNutzerIstSenderOderTeilnehmer = true;
						break;
					}
				}

				// wenn Filternutzer kein teilnehmer ist,
				// prüfe ob er sender einer Nachricht ist
				if (FilterNutzerIstSenderOderTeilnehmer == false) {
					for (Nachricht nachricht : unterhaltungsNachicht.u.getAlleNachrichten()) {
						if (nachricht.getSenderId() == nutzerFilter.getId()) {
							FilterNutzerIstSenderOderTeilnehmer = true;
							break;
						}
					}
				}
				// wenn er weder teilnehmer noch sender einer nachricht ist ->
				// aussortieren
				if (FilterNutzerIstSenderOderTeilnehmer == false)
					durchFilterAussortiert = true;
			}

			// HASHTAGFILTER
			if (hashtagFilter != null) {
				boolean HashtagVorhanden = false;
				//Übeprüfe jede Nachricht ob Hashtag vorhanden
				for (Nachricht nachricht : unterhaltungsNachicht.u.getAlleNachrichten()) {
					for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
						if (hashtag.getId() == hashtagFilter.getId()) {
							HashtagVorhanden = true;
							break;
						}
					}
				}
				if(HashtagVorhanden == false)
					durchFilterAussortiert = true;
			}
			
			//Wenn nicht durch Filter aussortiert, zur Liste hinzufügen
			if (durchFilterAussortiert == false)
				alleUnterhaltungenGefiltert.addElement(unterhaltungsNachicht);

		}

		dataProvider.flush();
		dataProvider.refresh();
	}

	public static class UnterhaltungsNachicht {
		Nachricht n;
		Unterhaltung u;

		public UnterhaltungsNachicht(Unterhaltung _u) {
			u = _u;

			// Setze erste Nachricht der Unterhaltung als Unterhaltungsnachricht
			if (_u.getAlleNachrichten().get(0) != null)
				n = _u.getAlleNachrichten().get(0);
		}
	}
}
