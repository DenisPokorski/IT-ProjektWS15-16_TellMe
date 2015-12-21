package de.hdm.tellme.client.gui.editor;

import java.util.List;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;

public class HashtagDataProvider {

	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	private ListDataProvider<HashtagZelle.ZellenObjekt> dataProvider = new ListDataProvider<HashtagZelle.ZellenObjekt>();

	private static Vector<Hashtag> hashTagListeTemp = null;

	private static HashtagDataProvider instanz = null;

	public static HashtagDataProvider gib() {
		if (instanz == null)
			instanz = new HashtagDataProvider();
		return instanz;
	}

	private HashtagDataProvider() {
		holeHashtagListe();
	}

	private void holeHashtagListe() {
		final List<HashtagZelle.ZellenObjekt> dataList = dataProvider.getList();

		asyncObj.gibHashtagListe(new AsyncCallback<Vector<Hashtag>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO
			}

			@Override
			public void onSuccess(Vector<Hashtag> hashTagListe) {

				hashTagListeTemp = hashTagListe;

				final int meineId = 7;
				asyncObj.ladeAbonnierendeHashtagListe(meineId,
						new AsyncCallback<Vector<Integer>>() {

							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(Vector<Integer> aboListe) {
								for (int i = 0; i <= hashTagListeTemp.size(); i++) {
									HashtagZelle.ZellenObjekt nah = new HashtagZelle().new ZellenObjekt();
									nah.hashtag = hashTagListeTemp.get(i);
									nah.aboniert = false;

									if (aboListe.contains(nah.hashtag.getId()))
										nah.aboniert = true;
									dataList.add(nah);
								}

							}

						});
			}
		});

	}

	public void addDataDisplay(CellList<HashtagZelle.ZellenObjekt> cellList) {
		dataProvider.addDataDisplay(cellList);
	}

	public void refreshDisplays() {
		dataProvider.refresh();
	}

	public void abonieren(Hashtag _nutzerAbonieren) {
		// db
	}

	public void deabonieren(Hashtag _nutzerDeabonieren) {
		// idb
	}

}
