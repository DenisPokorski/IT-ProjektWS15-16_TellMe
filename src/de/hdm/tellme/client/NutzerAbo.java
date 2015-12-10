package de.hdm.tellme.client;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.bo.Nutzer;



public class NutzerAbo {
	
	
	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
	
 
	private ListBox dropDownNutzerBereitsAbonniert = new ListBox();
	private ListBox dropDownNutzerNochNichtAbonniert = new ListBox();

	private int auswahlIdAboLoeschen;
	private int auswahlIdAboHinzufügen;

	

	

	public ListBox getAbonnierteNutzerLoeschenListe() {

		final int meineId =7;
		asyncObj.getZuAbonnieredeLoeschenNutzerListe(meineId, new AsyncCallback <Vector<Nutzer>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO 
 			}

			@Override
			public void onSuccess(Vector<Nutzer> resultListe) {
				dropDownNutzerBereitsAbonniert.clear();
				dropDownNutzerBereitsAbonniert.addItem("---");
				
 				for(int i=0; i < resultListe.size(); i++){
					dropDownNutzerBereitsAbonniert.addItem(resultListe.get(i).getId() +"-"+resultListe.get(i).getVorname() +"  "+ resultListe.get(i).getNachname());
 				}
 			}
			});
		
		dropDownNutzerBereitsAbonniert.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int i = dropDownNutzerBereitsAbonniert.getSelectedIndex();
				String s = dropDownNutzerBereitsAbonniert.getValue(i);
						s = s.substring(0,s.indexOf('-'));
						auswahlIdAboLoeschen = Integer.parseInt(s);
			}
	    });

		return dropDownNutzerBereitsAbonniert;
	}
	
	
	public Button AboLoeschenButton (){
		
		Button AboLoeschenBtn = new Button("Nutzerabonnement löschen"); 
		final int meineId = 7;
 
		AboLoeschenBtn.addClickHandler(new ClickHandler() {
			
				public void onClick(ClickEvent event) {
					
					if((auswahlIdAboLoeschen != 0) && (dropDownNutzerBereitsAbonniert.getSelectedIndex() > 0 )) {
						NutzerAboloeschenByIds(meineId,auswahlIdAboLoeschen );
					}
					else{
						Window.alert("Bitte wählen Sie ein NutzerAbonnement in der Liste aus, dass gelöscht werden soll.");
					}
				} 
			});
		
		return AboLoeschenBtn;
	}
	
// __________________________________	
// Nutzerabo löschen Funktionen ende	
// __________________________________	
	
	
	
	
	public ListBox getZuAbonnierndeNutzerHinzufügenListe( ){
		
		asyncObj.getAlleNochNichtAbonnierteNutzerListe( new AsyncCallback <Vector<Nutzer>>() {
			@Override
			public void onFailure(Throwable caught) {
				//TODO
				Window.alert( "fail1");

			}

			@Override
			public void onSuccess(Vector<Nutzer> alleNutzerListe) {
				
					dropDownNutzerNochNichtAbonniert.clear();
					dropDownNutzerNochNichtAbonniert.addItem("---");
					
					for(int i = 0; i <= alleNutzerListe.size(); i++){
 						dropDownNutzerNochNichtAbonniert.addItem(alleNutzerListe.get(i).getId() +"-"+alleNutzerListe.get(i).getVorname() +"  "+ alleNutzerListe.get(i).getNachname());
					}
 			}
			});
		
		dropDownNutzerNochNichtAbonniert.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int i = dropDownNutzerNochNichtAbonniert.getSelectedIndex();
				String s = dropDownNutzerNochNichtAbonniert.getValue(i);
						s = s.substring(0,s.indexOf('-'));
						auswahlIdAboHinzufügen = Integer.parseInt(s);
			}
	    });

		return dropDownNutzerNochNichtAbonniert;
	}
	
	
	
	
	
	
public Button AboErstellenButton (){
		
		Button AboErstellenButtonBtn = new Button("Nutzerabonnement erstellen"); 
		final int meineId = 7;
 
		AboErstellenButtonBtn.addClickHandler(new ClickHandler() {
			
				public void onClick(ClickEvent event) {

					if((auswahlIdAboHinzufügen != 0) && (dropDownNutzerNochNichtAbonniert.getSelectedIndex() > 0 )) {
						NutzerAboErstellenByIds(meineId,auswahlIdAboHinzufügen );
					}
					else{
						Window.alert("Bitte wählen Sie ein NutzerAbonnement in der Liste aus, dass erstellt werden soll, aus. ");
					}
				} 
			});
		
		return AboErstellenButtonBtn;
	}	
	
	
 
	
	
	
	
	
	
	
	
	
public void NutzerAboloeschenByIds(int vonId, int nachId ){
		
		asyncObj.loescheNutzeraboById(vonId, nachId, new AsyncCallback <Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Nutzerabo wurde erfolgreich NICHT gelöscht.");
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Das Nutzerabonnement wurde erfolgreich gelöscht.");
				RootPanel.get("content").clear();
				AboverwaltungEditor aE = new AboverwaltungEditor();
				RootPanel.get("content").add(aE);
			}
			});
	}
	
	
	

public void NutzerAboErstellenByIds(int vonId, int nachId ){
		
		asyncObj.erstellenNutzeraboById(vonId, nachId, new AsyncCallback <Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Nutzerabo wurde erfolgreich NICHT erstellt.");
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Das Nutzerabonnement wurde erfolgreich erstellt.");
				RootPanel.get("content").clear();
				AboverwaltungEditor aE = new AboverwaltungEditor();
				RootPanel.get("content").add(aE);
			}
			});
	}
 


 

}
