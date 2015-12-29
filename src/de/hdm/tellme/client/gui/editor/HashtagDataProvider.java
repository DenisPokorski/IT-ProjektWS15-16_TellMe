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
import de.hdm.tellme.shared.bo.Hashtag;

public class HashtagDataProvider {

	private final EditorServiceAsync _asyncObj = GWT.create(EditorService.class);

	private ListDataProvider<HashtagZelle.ZellenObjekt> dataProvider = new ListDataProvider<HashtagZelle.ZellenObjekt>();

	private static Vector<Hashtag> hashTagListeTemp = null;
	 private List<HashtagZelle.ZellenObjekt> dataList = dataProvider.getList();

	
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
		
		if(dataList != null){
		    dataList.clear();}
		dataList = dataProvider.getList();


		_asyncObj.gibAlleHashtags(new AsyncCallback<Vector<Hashtag>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO
			}

			@Override
			public void onSuccess(Vector<Hashtag> hashTagListe) {

				hashTagListeTemp = hashTagListe;
				int NutzerId = TellMe.eingeloggterBenutzer.getUser().getId();

 				_asyncObj.getAlleAbonniertenHashtagsfuerAbonehmer(NutzerId,
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

	public static Vector<Hashtag> gebeHashtagListe(){
		return hashTagListeTemp;
	}
	
	public void addDataDisplay(CellList<HashtagZelle.ZellenObjekt> cellList) {
		dataProvider.addDataDisplay(cellList);
	}

	public void refreshDisplays() {
		dataProvider.refresh();
	}

	public void hashtagSpeichern(Hashtag hashtag){
		
		_asyncObj.hashtagAktualisieren(hashtag, new AsyncCallback <Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler" );
 			}
			@Override
			public void onSuccess(Void  resultListe) {
			holeHashtagListe();
			RootPanel.get("content_right").clear();
			Window.alert("Der Hashtag wurde erfolgreich angelegt.");

 			}
			});
		
	}

	public void hashtagEntfernen(Hashtag hashtag){
		
		
		_asyncObj.hashtagEntfernen(hashtag, new AsyncCallback <Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler" );
 			}
			@Override
			public void onSuccess(Void  resultListe) {
			holeHashtagListe();
			RootPanel.get("content_right").clear();
			Window.alert("Der Hashtag wurde erfolgreich entfernt.");

 			}
			});
		
	}

	public void hashtagErstellen(Hashtag hashtag){
		
		_asyncObj.hashtagErstellen(hashtag, new AsyncCallback <Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler" );
 			}
			
			@Override
			public void onSuccess(Void  resultListe) {
				holeHashtagListe();
				RootPanel.get("content_right").clear();
				Window.alert("Der Hashtag wurde erfolgreich erstellt.");

 			}
			});
		
	}
	
	public void abonieren(Hashtag hashtag) {

 
				int NutzerId = TellMe.eingeloggterBenutzer.getUser().getId();
				int  HashtagId = hashtag.getId();
				
				_asyncObj.hashtagAboErstellen(NutzerId,HashtagId, new AsyncCallback <Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler" );
		 			}
					@Override
					public void onSuccess(Void  resultListe) {
					holeHashtagListe();
					RootPanel.get("content_right").clear();
					Window.alert("Das Abo wurde erfolgreich erstellt.");

		 			}
					});
	 
	
	}

	public void deabonieren(Hashtag hashtag) {
		
		int NutzerId = TellMe.eingeloggterBenutzer.getUser().getId();
		int  HashtagId = hashtag.getId();
		
		_asyncObj.hashtagAboEntfernen(NutzerId,HashtagId, new AsyncCallback <Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler" );
 			}
			@Override
			public void onSuccess(Void  resultListe) {
			holeHashtagListe();
			RootPanel.get("content_right").clear();
			Window.alert("Das Abo wurde erfolgreich entfernt.");

 			}
			});
	}

}
