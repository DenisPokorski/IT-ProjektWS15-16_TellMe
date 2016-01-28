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
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Die Klasse <class>NeuigkeitenNachrichtenBaumModel</code> zeigt die
 * Unterhaltungen und die dazugehörigen Nachrichten als Neuigkeiten-Feed an.
 * 
 * @author Denis Feltrin, Alex Homann
 *
 */
public class NeuigkeitenNachrichtenBaumModel implements TreeViewModel {
	/**
	 * Um die Unterhaltungen anzuzeigen werden neue Vektoren erstellt, die die
	 * Nachrichten einer Unterhaltung beinhalten.
	 */
	private static Vector<UnterhaltungsNachricht> alleUnterhaltungen = new Vector<UnterhaltungsNachricht>();
	private static Vector<UnterhaltungsNachricht> alleUnterhaltungenGefiltert = new Vector<UnterhaltungsNachricht>();
	private static ListDataProvider<UnterhaltungsNachricht> dataProvider;
	/**
	 * Um unterschiedliche Nachrichten anzuzeigen, werden unterschiedliche
	 * Filter benötigt, die das anzeigen der Nachrichten nach bestimmten
	 * Kriterien benötigen.
	 */
	/**
	 * Um Nachrichten anzuzeigen, die durch das Kriterium "Nutzer" gefiltert
	 * werden, wird hier ein <code>nutzerFilter</code> instanziiert, dieser wird
	 * durch das ausgewählte Objekt der NutzerCellList gesetzt.
	 */
	private static Nutzer nutzerFilter;
	static SingleSelectionModel<NutzerZelle.ZellenObjekt> selectionModelNutzer = null;

	/**
	 * Um Nachrichten anzuzeigen, die durch das Kriterium "Hashtag" gefiltert
	 * werden, wird hier ein <code>hashtagFilter</code> instanziiert, dieser
	 * wird durch das ausgewählte Objekt der HashtagCellList gesetzt.
	 */
	private static Hashtag hashtagFilter;
	static SingleSelectionModel<HashtagZelle.ZellenObjekt> selectionModelHashtag = null;

	/**
	 * RPC Methode, die auf Client in einer bestimmten Runtime ausgeführt wird
	 * um Daten mit dem Server auszutauschen
	 */
	private final static EditorServiceAsync asyncObj = GWT
			.create(EditorService.class);
	/**
	 * Um eine Unterhaltung oder eine Nachricht der Unterhaltungen auszuwählen
	 * wird auch hier ein <code>SingleSelectionModel</code> benötigt.
	 */
	final SingleSelectionModel<UnterhaltungsNachricht> unterhaltungSelectionModel = new SingleSelectionModel<UnterhaltungsNachricht>();
	final SingleSelectionModel<Nachricht> nachrichtenSelectionModel = new SingleSelectionModel<Nachricht>();

	/**
	 * Zu Beginn wird der boolean Wert, wenn ein Element selektiert wird auf
	 * null gesetzt.
	 */
	private static boolean selektiereNeuesElement = false;

	/**
	 * This selection model is shared across all leaf nodes. A selection model
	 * can also be shared across all nodes in the tree, or each set of child
	 * nodes can have its own instance. This gives you flexibility to determine
	 * how nodes are selected.
	 */

	public NeuigkeitenNachrichtenBaumModel() {
		/**
		 * Wenn eine Unterhaltung ausgewählt ist, werden im NeuigkeitenEditor
		 * die OptionenButtons gesetzt.
		 */
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

							NeuigkeitenEditor.setzeOptionenButton(unterhaltungSelectionModel.getSelectedObject().u,unterhaltungSelectionModel.getSelectedObject().n);
						} else {
							selektiereNeuesElement = false;
						}
					}
				});
		/**
		 * Wenn eine Nachricht ausgewählt wurde, werden die OptionenButtons
		 * gesetzt.
		 */
		nachrichtenSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						if (selektiereNeuesElement == false) {
							selektiereNeuesElement = true;
							
							if (unterhaltungSelectionModel.getSelectedObject() != null) {
								unterhaltungSelectionModel.setSelected(
										unterhaltungSelectionModel.getSelectedObject(), false);
							} else {
								selektiereNeuesElement = false;
							}
							NeuigkeitenEditor.setzeOptionenButton(null,nachrichtenSelectionModel.getSelectedObject());
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
	/**
	 * Beim erstellen des Baums wird jedes Objekt des DataProviders darauf
	 * geprüft, zu welcher Ebene des Baums es gehört. Entweder es gehört zur
	 * Ebene 0, also der untersten Ebene, oder es gehört zur Ebene 1, also der
	 * Ebene, die aufklappbar ist. Auf Ebene 0 werden die
	 * UnterhaltunsNachricht-Objekte angezeigt, während auf Ebene 1 die anderen
	 * zu einer Unterhaltung gehörenden Nachrichten angezeigt werden.
	 */
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			//TODO
			// LEVEL 0.
			// We passed null as the root value. Return the composers.
			// Create a data provider that contains the list of composers.
			dataProvider = new ListDataProvider<UnterhaltungsNachricht>(alleUnterhaltungenGefiltert);
			// Create a cell to display a composer.

			Cell<UnterhaltungsNachricht> cell = new AbstractCell<UnterhaltungsNachricht>() {

				@Override
				public void render(Context context,UnterhaltungsNachricht value, SafeHtmlBuilder sb) {
					if (value != null) {
						NeuigkeitenNachrichtenZelle nnz = new NeuigkeitenNachrichtenZelle(value.u);
						nnz.render(context, value.n, sb);
					}
				}
			};
			//TODO

			// Return a node info that pairs the data provider and the cell.
			return new DefaultNodeInfo<UnterhaltungsNachricht>(dataProvider,cell, unterhaltungSelectionModel, null);
			
		} else if (value instanceof UnterhaltungsNachricht) {
			// LEVEL 1.
			// Erste Nachricht aus Liste aller Nachrichten entfernen,
			// da diese schon als Unterhaltungs Rahmen ausgegeben wird
			if (((UnterhaltungsNachricht) value).u.getAlleNachrichten().size() > 1) {

				Vector<Nachricht> alleNachrichtenAusserErste = new Vector<Nachricht>();
				for (int i = 1; i < ((UnterhaltungsNachricht) value).u
						.getAlleNachrichten().size(); i++) {
					alleNachrichtenAusserErste.add(((UnterhaltungsNachricht) value).u.getAlleNachrichten().get(i));
				}

				ListDataProvider<Nachricht> dataProvider = new ListDataProvider<Nachricht>(alleNachrichtenAusserErste);
				Cell<Nachricht> cell = new NeuigkeitenNachrichtenZelle(((UnterhaltungsNachricht) value).u);

				return new DefaultNodeInfo<Nachricht>(dataProvider, cell,nachrichtenSelectionModel, null);
			}
			return null;
		}
		return null;
	}

	/**
	 * Check if the specified value represents a leaf node. Leaf nodes cannot be
	 * opened.
	 */
	/**
	 * Hier wird überprüft, ob das Object ein "leaf" also ein Blatt des Baums
	 * darstellt, denn diese können nicht aufgeklappt werden.
	 */
	public boolean isLeaf(Object value) {

		// The leaf nodes are the songs, which are Strings.
		if (value instanceof Nachricht) {
			// Wenn value eine Nachricht ist, return true -> knoten ist ein
			// "Blatt" und hat keine Kinder
			return true;
		}

		if (value instanceof UnterhaltungsNachricht) {

			if (((UnterhaltungsNachricht) value).u.getAlleNachrichten().size() == 1) {
				// Wenn value eine Unterhaltungsnachricht ist aber nicht mehr
				// als eine Nachricht enthält,
				// return true -> knoten ist ein "Blatt" und hat keine Kinder
				return true;
			}

		}

		return false;
	}

	/**
	 * Die Unterhaltungen, die für den eingeloggten Nutzer relevant sind werden
	 * geladen. Dies wird durch einen RPC durchgeführt, der einen Vektor mit
	 * Unterhaltung-Objekten zurückgibt.
	 */
	public static void ladeUnterhaltungenAsync() {

		asyncObj.getAlleRelevantenUnterhaltungen(TellMe.gibEingeloggterBenutzer().getUser().getId(),
				new AsyncCallback<Vector<Unterhaltung>>() {

					@Override
					public void onSuccess(Vector<Unterhaltung> result) {
						alleUnterhaltungen.clear();
						for (Unterhaltung unterhaltung : result) {
							try {
								UnterhaltungsNachricht un = new UnterhaltungsNachricht(unterhaltung);
								alleUnterhaltungen.addElement(un);
							} catch (Exception ex) {
								Window.alert("Fehler beim hinzufügen einer Nachricht"+ ex.toString());
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

	}

	/**
	 * Der NutzerFilter wird gesetzt um die relevanten Nachrichten durch das
	 * Kriterium "Nutzer" zu verändern.
	 * 
	 * @param n
	 *            , das Nutzer-Objekt
	 * @param selectionModel
	 *            , das ausgewählte Nutzer-Objekt aus der NutzerCellList.
	 */
	public static void setzeNutzerFilter(Nutzer n,SingleSelectionModel<NutzerZelle.ZellenObjekt> selectionModel) {
		nutzerFilter = n;
		selectionModelNutzer = selectionModel;

		if (hashtagFilter != null) {
			hashtagFilter = null;
			selectionModelHashtag.setSelected(selectionModelHashtag.getSelectedObject(), false);
		}
		UnterhaltungsListeErneuern();
	}

	/**
	 * Der HashtagFilter wird gesetzt um die relevanten Nachrichten durch das
	 * Kriterium "Hashtag" zu verändern.
	 * 
	 * @param h
	 *            , Hashtag-Objekt
	 * @param selectionModel
	 */
	public static void setzeHashtagFilter(Hashtag h,
		SingleSelectionModel<HashtagZelle.ZellenObjekt> selectionModel) {
		hashtagFilter = h;
		selectionModelHashtag = selectionModel;

		if (nutzerFilter != null) {
			nutzerFilter = null;
			selectionModelNutzer.setSelected(selectionModelNutzer.getSelectedObject(), false);
		}
		UnterhaltungsListeErneuern();
	}

	/**
	 * Der Filter wird gelöscht, so dass wieder die Nachrichten angezeigt
	 * werden, die keinen Filter haben.
	 */
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

	/**
	 * Die Methode <code>UnterhaltungsListeErneuern</code> wird verwendet um das
	 * NeuigkeitenNachrichtenBaumModel zu aktualisieren.
	 */
	public static void UnterhaltungsListeErneuern() {

		dataProvider.getList().clear();

		for (UnterhaltungsNachricht unterhaltungsNachricht : alleUnterhaltungen) {
			boolean durchFilterAussortiert = false;

			// NUTZERFILTER
			if (nutzerFilter != null) {
				// Prüfe ob Filternutzer teilnehmer ist
				boolean FilterNutzerIstSenderOderTeilnehmer = false;
				for (Nutzer teilnehmer : unterhaltungsNachricht.u.getTeilnehmer()) {
					
					if (teilnehmer.getId() == nutzerFilter.getId()) {
						FilterNutzerIstSenderOderTeilnehmer = true;
						break;
					}
				}
				// wenn Filternutzer kein teilnehmer ist,
				// prüfe ob er sender einer Nachricht ist
				if (FilterNutzerIstSenderOderTeilnehmer == false) {
					for (Nachricht nachricht : unterhaltungsNachricht.u.getAlleNachrichten()) {
						
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
				// Übeprüfe jede Nachricht ob Hashtag vorhanden
				for (Nachricht nachricht : unterhaltungsNachricht.u.getAlleNachrichten()) {
					
					for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
						if (hashtag.getId() == hashtagFilter.getId()) {
							HashtagVorhanden = true;
							break;
						}
					}
				}
				if (HashtagVorhanden == false)
					durchFilterAussortiert = true;
			}

			// Wenn nicht durch Filter aussortiert, zur Liste hinzufügen
			if (durchFilterAussortiert == false)
				alleUnterhaltungenGefiltert.addElement(unterhaltungsNachricht);

		}

		dataProvider.flush();
		dataProvider.refresh();
	}

	/**
	 * Die Klasse <class>UnterhaltungsNachricht</class> setzt, die erste
	 * Nachricht der Nachrichten als UnterhaltungsNachricht, damit im TreeModel
	 * unterschieden werden kann. So werden auf Level 0 die
	 * UnterhaltungsNachrichten angezeigt, während auf Level 1 die anderen
	 * Nachrichten angezeigt werden.
	 *
	 */
	public static class UnterhaltungsNachricht {
		Nachricht n;
		Unterhaltung u;

		public UnterhaltungsNachricht(Unterhaltung _u) {
			u = _u;

			// Setze erste Nachricht der Unterhaltung als Unterhaltungsnachricht
			if (_u.getAlleNachrichten().get(0) != null)
				n = _u.getAlleNachrichten().get(0);
		}
	}
}
