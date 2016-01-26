package de.hdm.tellme.client.gui.editor;

import java.sql.Timestamp;
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
import de.hdm.tellme.shared.ReportService;
import de.hdm.tellme.shared.ReportServiceAsync;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;
import de.hdm.tellme.shared.report.HTMLReportWriter;

/**
 * 
 * 
 * @author denispokorski
 *
 */
public class NutzerDataProvider {
	List<NutzerZelle.ZellenObjekt> dataList = null;
	private ListDataProvider<NutzerZelle.ZellenObjekt> dataProvider = new ListDataProvider<NutzerZelle.ZellenObjekt>();
	private final EditorServiceAsync _editorAsyncObj = GWT
			.create(EditorService.class);
	private final ReportServiceAsync _reportAsyncObj = GWT
			.create(ReportService.class);

	private static NutzerDataProvider instanz = null;
	private static Vector<Nutzer> alleNutzer = null;
	private static Vector<Integer> alleAbonniertenNutzer = null;
	private static int lastI;
	
	public static NutzerDataProvider gib(int i) {
	if (i == 0) {
			if (instanz == null || lastI != 0) {
				instanz = new NutzerDataProvider(0);
				lastI = 0;
			}
		} else {
			if (instanz == null || lastI != 1) {
				instanz = new NutzerDataProvider(1);
				lastI = 1;
			}
		}
		return instanz;
	}

	private NutzerDataProvider(int i) {

		if (i == 0)
			fuelleListe();
		else
			fuelleListeReport();

		dataList = dataProvider.getList();

	}

	public void fuelleListeReport() {
		if (dataList != null)
			dataList.clear();

		dataList = dataProvider.getList();
		
		_editorAsyncObj.getAlleNutzer(true,
				new AsyncCallback<Vector<Nutzer>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei der Reportgenerierung. Bitte wenden Sie sich an den Administrator des Systems.");
					}

					@Override
					public void onSuccess(Vector<Nutzer> result) {
						alleNutzer = result;
						for (int i = 0; i <= alleNutzer.size(); i++) {
							NutzerZelle.ZellenObjekt nah = new NutzerZelle().new ZellenObjekt();
							nah.nutzer = alleNutzer.get(i);
							dataList.add(nah);

						}

					}
				});

	}

	public void fuelleListe() {
		if (dataList != null)
			dataList.clear();

		_editorAsyncObj.getAlleNutzerAußerMeineId(TellMe.gibEingeloggterBenutzer()
				.getUser().getId(), new AsyncCallback<Vector<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Vector<Nutzer> result) {
				alleNutzer = result;
				_editorAsyncObj.holeAlleAbonniertenNutzer(
						TellMe.gibEingeloggterBenutzer().getUser().getId(),
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


	/**
	 * Diese Methode sorgt dafür, dass die Nutzerliste aktuallisiert
	 * wird wenn ein Nutzer seinen Namen ändert.
	 * 
	 * @param hashtag
	 */
	public void nutzerBearbeitet() {
		fuelleListe();

	}
	
	public void abonieren(Nutzer _nutzer) {

		_editorAsyncObj.nutzerAbonnementErstellen(TellMe.gibEingeloggterBenutzer()
				.getUser().getId(), _nutzer, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {

				fuelleListe();
				Window.alert("Erfolgreich abonniert");
				RootPanel.get("content_right").clear();
				RootPanel.get("content_right").add(new NutzerFormular().gibBeschreibung());

			}
		});
	}

	public void deabonieren(Nutzer _nutzerDeabonieren) {
		_editorAsyncObj.nutzerAbonnementLoeschen(TellMe.gibEingeloggterBenutzer()
				.getUser().getId(), _nutzerDeabonieren,
				new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Void result) {

						fuelleListe();
						
						Window.alert("Erfolgreich deabonniert");
						RootPanel.get("content_right").clear();
						RootPanel.get("content_right").add(new NutzerFormular().gibBeschreibung());

					}
				});
	}

	public void report3Generieren(Nutzer n) {
		final Nutzer b = n;
		_reportAsyncObj.alleUnterhaltungenFuerAutor(b.getId(),
				new AsyncCallback<Vector<Unterhaltung>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler 1_3");

					}

					@Override
					public void onSuccess(Vector<Unterhaltung> result) {

						HTMLReportWriter hRW = new HTMLReportWriter();

						hRW.generateReport3(result, b);
					}

				});

	}

	public void report7Generieren(Nutzer n) {
		final Nutzer b = n;

		_reportAsyncObj.report7Generieren(b.getId(),
				new AsyncCallback<Vector<Nutzer>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei der Generierung");
					}

					@Override
					public void onSuccess(Vector<Nutzer> result) {
						HTMLReportWriter hRW = new HTMLReportWriter();

						hRW.generateReport7(result, b);
					}

				});
	}

	public void report6Generieren(Nutzer n) {
		final Nutzer b = n;
		_reportAsyncObj.report6Generieren(b.getId(),
				new AsyncCallback<Vector<Hashtag>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei der Generierung");
					}

					@Override
					public void onSuccess(Vector<Hashtag> result) {

						HTMLReportWriter hRW = new HTMLReportWriter();

						hRW.generateReport6(result, b);

					}
				});

	}

	public void report5Generieren(Nutzer n) {
		final Nutzer b = n;
		_reportAsyncObj.report5GenerierenListe(b.getId(),
				new AsyncCallback<Vector<Nutzer>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei der Generierung");
					}

					@Override
					public void onSuccess(Vector<Nutzer> result) {

						HTMLReportWriter hRW = new HTMLReportWriter();

						hRW.generateReport5(result, b);
					}
				});
	}

	public void report4Generieren(Nutzer n) {
		final Nutzer b = n;
		_reportAsyncObj
				.alleUnterhaltungen(new AsyncCallback<Vector<Unterhaltung>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei der Generierung");
					}

					public void onSuccess(Vector<Unterhaltung> result) {
						HTMLReportWriter hRW = new HTMLReportWriter();

						hRW.generateReport4(result, b);
					}
				});
	}

	public void report1Generieren(Nutzer n, Timestamp vonDate, Timestamp bisDate) {
		final Nutzer b = n;
		final Timestamp vD = vonDate;
		final Timestamp bD = bisDate;
		_reportAsyncObj.alleUnterhaltungenFuerAutorMitZeitraum(b.getId(), vD,
				bD, new AsyncCallback<Vector<Unterhaltung>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}

					@Override
					public void onSuccess(Vector<Unterhaltung> result) {
						HTMLReportWriter hRW = new HTMLReportWriter();

						hRW.generateReport1(result, b);
					}
				});
	}

	public void report2Generieren(Nutzer n, Timestamp vonDate, Timestamp bisDate) {
		final Nutzer b = n;
		final Timestamp vD = vonDate;
		final Timestamp bD = bisDate;
		_reportAsyncObj.alleUnterhaltungenMitZeitraum(vD, bD,
				new AsyncCallback<Vector<Unterhaltung>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}

					@Override
					public void onSuccess(Vector<Unterhaltung> result) {
						HTMLReportWriter hRW = new HTMLReportWriter();

						hRW.generateReport2(result, b);
					}
				});
	}
}
