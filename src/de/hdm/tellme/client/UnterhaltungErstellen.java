package de.hdm.tellme.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.server.rpc.core.java.util.Arrays;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class UnterhaltungErstellen {
    Vector<Nachricht> nachrichtenListe = new Vector<Nachricht>();
	VerticalPanel vP = new VerticalPanel();
	ArrayList<String> unterhaltungenListe = new ArrayList<String>();
// private static   List<String> unterhaltungenListe  ;
      String a; 

	
	  private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	  public class UnterhaltungsIdSpeicher{
		  
			 private  int uId;
			 
			 public int getId() {
					return uId;
				}
 
				public void setId(int uId) {
					this.uId = uId;
				}
			

		  
	  }
	  
	  
	  
	public int oeffentlicheUnterhaltungErstellen(Timestamp ts, String text) {
		  final UnterhaltungsIdSpeicher uIdSpeicehr = new UnterhaltungsIdSpeicher();
//		  
//		asyncObj.UnterhaltungErstellen(ts,text, new AsyncCallback <Integer>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert( "fail1");
//			}
//			 
//			@Override
//			public void onSuccess(Integer result) {
//				uIdSpeicehr.setId(result);
//			}
//			
//			});
		return uIdSpeicehr.getId(); 
	} 
	 
	

public VerticalPanel  ladeAlleNachrichtenVonUnterhaltungListe(int uId) {
	
 
//	asyncObj.alleNachrichtenVonUnterhaltungListe (uId, new AsyncCallback <Vector <Nachricht>>() {
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert( "fail1");
//		}
//
//		@Override
//		public void onSuccess(Vector<Nachricht> result) {
//			nachrichtenListe.clear();
//			nachrichtenListe = result; 
//				//Window.alert("asd2 " + result.size() );
//
//				for(int i=0; i< result.size(); i++){
//					FlowPanel fP = new FlowPanel();
//					DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
//
//					Label nachrichtSenderName= new Label(nachrichtenListe.get(i).getSender().getVorname() +" "+ nachrichtenListe.get(i).getSender().getNachname());
//					Label nachrichtDatum = new Label("- "+dtf.format(nachrichtenListe.get(i).getErstellungsDatum()));
//					Label nachrichtText = new Label(nachrichtenListe.get(i).getText());
//					Button bearbeiten = new Button("bearbeiten");
//					
//					fP.add(nachrichtSenderName);
//					fP.add(nachrichtDatum);
//					fP.add(bearbeiten);
//					fP.add(nachrichtText);
//					vP.add(fP);
//					
//					fP.setStyleName("NachrichtFlowPanel");
//					nachrichtText.setStyleName("NachrichtText");
//					nachrichtSenderName.setStyleName("NachrichtSenderName");
//					nachrichtDatum.setStyleName("NachrichtDatum");
//					bearbeiten.setStyleName("NachrichtButton");
//
//					bearbeiten.addClickHandler(new ClickHandler() {
//
//							@Override
//							public void onClick(ClickEvent event) {
//								
//								//Nachricht bearbeiten Löschen Panel
//								
//							}
//						});
//					
//				
//					
//				}
//				
//				
//				
//				
// 			if(result.size()==0){
// 				Window.alert("result ist 0");
// 			}
//
//			
//		}
//	});
return vP;  
}

 


public  Vector <Nachricht> oeffentlicheUnterhaltungNachrichtenAnzeigen(int uId){
 
	  ladeAlleNachrichtenVonUnterhaltungListe(uId);
	  
	return nachrichtenListe;
	
	 
}



public ArrayList<String> ladeAlleOeffentlichenUnterhaltungen() {
	final	 TextCell textCell = new TextCell();

  final  CellList<String> cellList = new CellList<String>(textCell);
 // final   Arrayist<String> unterhaltungListeV = null ;

//	asyncObj.ladeAlleOeffentlichenUnterhaltungen( new AsyncCallback <Vector<Unterhaltung>>() {
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert( "Fehler");
//		}
//		 
//
//		@Override
//		public void onSuccess(Vector<Unterhaltung> result) {
//		//	unterhaltungenListe = result;
//	          Window.alert("list sucsses " +unterhaltungenListe.size());
//	          
//	       
//	      	for(int i = 0; i <= result.size(); i++){
//
//	  
//	      		unterhaltungenListe.add("Unterhaltung: "+result.get(i).getId()); 
//	      		
// 			}  
//	        
////	 		    if(result.size() != 0){
////			          Window.alert("list is not null  " );
////	 		  
//			
////			    cellList.setRowCount(unterhaltungenListe.size(), true);
////			    cellList.setRowData(0,unterhaltungenListe); 
////
////	 		    
////	 		    }
////				private static List<Unterhaltung> CONTACTS = Arrays.asList(
////						unterhaltungenListe);
////  }
////			
////	 		    }	
////		}
//		
//		}});
// 
	Window.alert("list end sizel  "+unterhaltungenListe.size() );

	return unterhaltungenListe;
}


 






}
	
//	private VerticalPanel  EmpfaengerHinzufügenWrap = new VerticalPanel(); 
//	SuggestBox  EmpfaengerHinzufügenSug = new SuggestBox(oracle); 
//	//EmpfaengerHinzufügenBar
//	HorizontalPanel EmpfaengerHinzufügenBar = new HorizontalPanel(); 
//	private FlowPanel  EmpfaengerHinzufügenLeiste = new FlowPanel(); 

 
