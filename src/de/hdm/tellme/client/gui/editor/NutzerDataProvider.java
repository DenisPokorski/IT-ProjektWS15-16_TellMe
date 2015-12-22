package de.hdm.tellme.client.gui.editor;

import java.util.List;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;

public class NutzerDataProvider {
	List<NutzerZelle.ZellenObjekt> dataList = null;
	private ListDataProvider<NutzerZelle.ZellenObjekt> dataProvider = new ListDataProvider<NutzerZelle.ZellenObjekt>();
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
	private static NutzerDataProvider instanz = null;
	private static Vector<Nutzer> alleNutzer = null;
	private static Vector<Integer> alleAbonniertenNutzer = null;

	public static NutzerDataProvider gib() {
		if (instanz == null)
			instanz = new NutzerDataProvider();
		return instanz;
	}

	private NutzerDataProvider() {
		fuelleListe();

		dataList = dataProvider.getList();

	}

	public void fuelleListe() {
		if (dataList != null)
			dataList.clear();

		asyncObj.getAlleNutzerAußerMeineId(TellMe.eingeloggterBenutzer
				.getUser().getId(), new AsyncCallback<Vector<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
				alleNutzer = result;
				asyncObj.holeAlleAbonniertenNutzer(TellMe.eingeloggterBenutzer
						.getUser().getId(),
						new AsyncCallback<Vector<Integer>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(Vector<Integer> result) {
								alleAbonniertenNutzer = result;
								for (int i = 0; i <= alleNutzer.size(); i++) {
									NutzerZelle.ZellenObjekt nah = new NutzerZelle().new ZellenObjekt();
									nah.nutzer = alleNutzer.get(i);
									nah.aboniert = false;
									if (alleAbonniertenNutzer
											.contains(nah.nutzer.getId()))
										nah.aboniert = true;
									dataList.add(nah);

								}

							}
						});

			}
		});

	}

	public void addDataDisplay(CellList<NutzerZelle.ZellenObjekt> cellList) {
		dataProvider.addDataDisplay(cellList);
	}

	public void refreshDisplays() {
		dataProvider.refresh();
	}

	public void abonieren(Nutzer _nutzer) {

		asyncObj.nutzerAbonnementErstellen(TellMe.eingeloggterBenutzer
				.getUser().getId(), _nutzer, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {

				fuelleListe();
				Window.alert("Erfolgreich aboniert");

			}
		});
	}

	public void deabonieren(Nutzer _nutzerDeabonieren) {
		asyncObj.nutzerAbonnementLoeschen(TellMe.eingeloggterBenutzer.getUser()
				.getId(), _nutzerDeabonieren, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {

				fuelleListe();
				Window.alert("Erfolgreich deaboniert");

			}
		});
	}
}
