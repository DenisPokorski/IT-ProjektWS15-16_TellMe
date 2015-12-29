package Archiv;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.bo.Nachricht;

public class UnterhaltungErstellen {
    Vector<Nachricht> nachrichtenListe = new Vector<Nachricht>();
	VerticalPanel vP = new VerticalPanel();
	ArrayList<String> unterhaltungenListe = new ArrayList<String>();
// private static   List<String> unterhaltungenListe  ;
      String a; 

	
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
//	Label nachrichtDatum = new Label("- "+dtf.format(nachrichtenListe.get(i).getErstellungsDatum()));
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

 
