package de.hdm.tellme.client.gui.editor;

import java.sql.Timestamp;
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
import com.sun.java.swing.plaf.windows.resources.windows;

import de.hdm.tellme.client.NeuigkeitenEditor;
import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class NeuigkeitenNachrichtenBaumModel implements TreeViewModel {
	private static Vector<UnterhaltungsNachicht> alleUnterhaltungen = new Vector<UnterhaltungsNachicht>();
	private static ListDataProvider<UnterhaltungsNachicht> dataProvider;

	// RPC Methode, die auf Client in einer bestimmten Runtime ausgeführt wird
	// um Daten mit dem Server auszutauschen
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

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
			dataProvider = new ListDataProvider<UnterhaltungsNachicht>(alleUnterhaltungen);
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
			return true;
		}
		return false;
	}

	private void ladeUnterhaltungenAsync() {

		asyncObj.getAlleRelevantenUnterhaltungen(TellMe.gibEingeloggterBenutzer().getUser().getId(), new AsyncCallback<Vector<Unterhaltung>>() {

			@Override
			public void onSuccess(Vector<Unterhaltung> result) {

				dataProvider.getList().clear();

				
				for (Unterhaltung unterhaltung : result) {
					UnterhaltungsNachicht un = new UnterhaltungsNachicht(unterhaltung);

					alleUnterhaltungen.addElement(un);
				}

				Window.alert(dataProvider.getList().size() + "");

				dataProvider.flush();
				dataProvider.refresh();

				// cellTable.redraw();

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		//

	}

	public class UnterhaltungsNachicht {
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
