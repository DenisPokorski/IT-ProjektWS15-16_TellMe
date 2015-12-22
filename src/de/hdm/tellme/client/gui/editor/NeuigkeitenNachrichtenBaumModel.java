package de.hdm.tellme.client.gui.editor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.TreeViewModel.NodeInfo;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.server.db.DateHelperClass;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class NeuigkeitenNachrichtenBaumModel implements TreeViewModel {
	private final Vector<Unterhaltung> alleUnterhaltungen;

	/**
	 * This selection model is shared across all leaf nodes. A selection model
	 * can also be shared across all nodes in the tree, or each set of child
	 * nodes can have its own instance. This gives you flexibility to determine
	 * how nodes are selected.
	 */
	private final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();

	public NeuigkeitenNachrichtenBaumModel() {
		alleUnterhaltungen = new Vector<Unterhaltung>();

		// TESTDATA
		{
			long now = 100000;
			Nutzer empfaenger1 = new Nutzer();
			empfaenger1.setId(2);
			empfaenger1.setVorname("Testvorname");
			empfaenger1.setNachname("Testnachname");

			Nutzer empfaenger2 = new Nutzer();
			empfaenger2.setId(5);
			empfaenger2.setVorname("Testvorname2");
			empfaenger2.setNachname("Testnachname2");

			Vector<Nutzer> TeilnehmerListe = new Vector<Nutzer>();
			TeilnehmerListe.add(empfaenger1);
			TeilnehmerListe.add(empfaenger2);

			Unterhaltung u1 = new Unterhaltung();
			u1.setErstellungsDatum(new Timestamp(now - 300));
			u1.setId(333);
			u1.setSichtbarkeit(1);
			u1.setTeilnehmer(TeilnehmerListe);
//			u1.setUnterhaltungstyp(Unterhaltung.eUnterhaltungsTyp.privat);

			Nachricht u1n1 = new Nachricht();
			u1n1.setErstellungsDatum(new Timestamp(now));
			u1n1.setSenderId(empfaenger1.getId());
			u1n1.setSender(empfaenger1);
			u1n1.setText("Gut und dir?");

			Nachricht u1n2 = new Nachricht();
			u1n2.setErstellungsDatum(new Timestamp(now - 300));
			u1n2.setSenderId(empfaenger2.getId());
			u1n2.setSender(empfaenger2);
			u1n2.setText("Hey Wie gehts dir?");

			Vector<Nachricht> alleNachrichten = new Vector<Nachricht>();
			alleNachrichten.add(u1n2);
			alleNachrichten.add(u1n1);
			u1.setAlleNachrichten(alleNachrichten);

			alleUnterhaltungen.add(u1);
		}

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
			ListDataProvider<Unterhaltung> dataProvider = new ListDataProvider<Unterhaltung>(alleUnterhaltungen);
			// Create a cell to display a composer.
			Cell<Unterhaltung> cell = new AbstractCell<Unterhaltung>() {
				@Override
				public void render(Context context, Unterhaltung value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendHtmlConstant("<div class='NeuigkeitenUnterhalungsOptionen'>");						
						sb.appendHtmlConstant("<div style='float: left' >Empfängerliste</div>");
						sb.appendHtmlConstant("<div style='float: left' >" + new Button("Antworten").toString() + "</div>");
						sb.appendHtmlConstant("<div style='float: left' >" + new Button("Teilnehmer bearbeiten").toString() + "</div>");
						sb.appendHtmlConstant("<div style='float: left' >" + new Button("Unterhaltung verlassen").toString() + "</div>");
						sb.appendHtmlConstant("</div>");

						NeuigkeitenNachrichtenZelle nnz = new NeuigkeitenNachrichtenZelle();
						nnz.render(context, value.getAlleNachrichten().get(0), sb);
					}
				}
			};
			// Return a node info that pairs the data provider and the cell.
			return new DefaultNodeInfo<Unterhaltung>(dataProvider, cell);
		} else if (value instanceof Unterhaltung) {
			// LEVEL 1.

			// Erste Nachricht aus Liste aller Nachrichten entfernen,
			// da diese schon als Unterhaltungs Rahmen ausgegeben wird
			Vector<Nachricht> alleNachrichtenAusserErste = ((Unterhaltung) value).getAlleNachrichten();
			if (alleNachrichtenAusserErste.size() > 1) {
				alleNachrichtenAusserErste.remove(0);

				ListDataProvider<Nachricht> dataProvider = new ListDataProvider<Nachricht>(alleNachrichtenAusserErste);
				Cell<Nachricht> cell = new NeuigkeitenNachrichtenZelle();
				return new DefaultNodeInfo<Nachricht>(dataProvider, cell);
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
		if (value instanceof String) {
			return true;
		}
		return false;
	}

}
