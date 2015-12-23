package de.hdm.tellme.client.gui.editor;

import java.util.List;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.sun.java.swing.plaf.windows.resources.windows_de;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.ReportService;
import de.hdm.tellme.shared.ReportServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.report.HTMLReportWriter;

public class NutzerDataProvider {
	List<NutzerZelle.ZellenObjekt> dataList = null;
	private ListDataProvider<NutzerZelle.ZellenObjekt> dataProvider = new ListDataProvider<NutzerZelle.ZellenObjekt>();
	private final EditorServiceAsync _editorAsyncObj = GWT
			.create(EditorService.class);
	private final ReportServiceAsync _reportAsyncObj = GWT
			.create(ReportService.class);
	private final ReportServiceAsync _report2AsyncObj = GWT
			.create(ReportService.class);

	private static NutzerDataProvider instanz = null;
	private static Vector<Nutzer> alleNutzer = null;
	private static Vector<Integer> alleAbonniertenNutzer = null;
	private LoginInfo loginInfo = new LoginInfo();

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

		_editorAsyncObj.getAlleNutzerAußerMeineId(TellMe.eingeloggterBenutzer
				.getUser().getId(), new AsyncCallback<Vector<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
				alleNutzer = result;
				_editorAsyncObj.holeAlleAbonniertenNutzer(
						TellMe.eingeloggterBenutzer.getUser().getId(),
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

		_editorAsyncObj.nutzerAbonnementErstellen(TellMe.eingeloggterBenutzer
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
		_editorAsyncObj.nutzerAbonnementLoeschen(TellMe.eingeloggterBenutzer
				.getUser().getId(), _nutzerDeabonieren,
				new AsyncCallback<Void>() {

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

	public void report3Generieren(Nutzer n) {
		final Nutzer b = n;
		_reportAsyncObj.report3Generieren(n.getId(),
				new AsyncCallback<Vector<Hashtag>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("nutzredata");
					}

					@Override
					public void onSuccess(Vector<Hashtag> result) {
						Window.alert("Erfolgreich tod");

						HTMLReportWriter hRW = new HTMLReportWriter();

						hRW.generateReport3(result, b);

						// for (int i =0; i < result.size(); i++) {
						// Window.alert(result.get(i).getSchlagwort());
						// }
					}
				});

	}

	public void report2Generieren(Nutzer n) {
		final Nutzer b = n;
		_report2AsyncObj.report2GenerierenListe(n.getId(),
				new AsyncCallback<Vector<Nutzer>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("nutzer2data");
					}

					@Override
					public void onSuccess(Vector<Nutzer> result) {
						Window.alert("ErfolgreichReport2");
					}
				});
	}
}
