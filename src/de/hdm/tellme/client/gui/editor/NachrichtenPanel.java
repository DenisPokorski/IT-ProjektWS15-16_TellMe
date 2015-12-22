package de.hdm.tellme.client.gui.editor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.NachrichtenErstellen;
import de.hdm.tellme.client.UnterhaltungErstellen;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;

public class NachrichtenPanel {

	
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	  Vector<Nutzer> resultListe2 = new Vector<Nutzer>();

	  private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);

	  	HorizontalPanel NachrichtSendeBtnWrap = new HorizontalPanel();
		    TextArea textArea = new TextArea();
		    Button sendenButton = new Button("Senden");
		    
		    
		 
			private VerticalPanel  EmpfaengerHinzuf�genWrap = new VerticalPanel(); 
			SuggestBox  EmpfaengerHinzuf�genSug = new SuggestBox(oracle); 
			//EmpfaengerHinzuf�genBar
			HorizontalPanel EmpfaengerHinzuf�genBar = new HorizontalPanel(); 
			private FlowPanel  EmpfaengerHinzuf�genLeiste = new FlowPanel(); 

	
	
	public void ErsteNarchichtErstellenPanel(){
		 loadNutzerSugestListe();
		  setloadNutzerSugestListeHizuf�genButton();
		  
		  textArea.setStylePrimaryName("textAreaNachrichtSenden");
		  sendenButton.setStylePrimaryName("textAreaNachrichtSendenButton");
		  NachrichtSendeBtnWrap.setStylePrimaryName("textAreaNachrichtSendenWrap");
		  
		  EmpfaengerHinzuf�genWrap.add(EmpfaengerHinzuf�genBar);
		  EmpfaengerHinzuf�genWrap.add(EmpfaengerHinzuf�genLeiste);
		
		  textArea.setWidth("100%");
		  NachrichtSendeBtnWrap.add(textArea);
		  NachrichtSendeBtnWrap.add(sendenButton);
		  EmpfaengerHinzuf�genWrap.add(NachrichtSendeBtnWrap);

		  RootPanel.get("content_right").add(EmpfaengerHinzuf�genWrap);
		
		  sendenButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					
 						if (textArea.getValue().isEmpty()) {
							Window.alert("Bitte Textfeld bef�llen");
						} else {
					
						 Date utilDate = new  Date();
					 	 Timestamp ts = new  Timestamp(utilDate.getTime());
						 new NachrichtenErstellen().oeffentlicheNachrichtErstellen(ts, textArea.getText());
						 int uId = new UnterhaltungErstellen().oeffentlicheUnterhaltungErstellen(ts, textArea.getText());
						 RootPanel.get("content_right").clear();
						 new UnterhaltungPanel().unterhaltungsInhaltazeigenPanel(uId);
						 Window.alert("lalala -------  "+ts);

						}
 					 
					
				}
			});
		  
		  
		
 	}
	
	public HorizontalPanel antwortErstellenPanel(int uId){
		
		final int uid = uId; 
	
		  NachrichtSendeBtnWrap.add(textArea);
		  NachrichtSendeBtnWrap.add(sendenButton);
		  
		  textArea.setStylePrimaryName("textAreaNachrichtSenden");
		  sendenButton.setStylePrimaryName("textAreaNachrichtSendenButton");
		  NachrichtSendeBtnWrap.setStylePrimaryName("textAreaNachrichtSendenWrap");

		  
		  sendenButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					
						if (textArea.getValue().isEmpty()) {
							Window.alert("Bitte Textfeld bef�llen");
						} else {
					
						 Date utilDate = new  Date();
					 	 Timestamp ts = new  Timestamp(utilDate.getTime());
					 	 
					 	 NachrichtenErstellen a = new NachrichtenErstellen();
						 a.oeffentlicheNachrichtErstellenUnterhaltungZuweisen(ts, textArea.getText(), uid);

						}
						 new UnterhaltungPanel().unterhaltungsInhaltazeigenPanel(uid);

					 
					
				}
			});
		  return NachrichtSendeBtnWrap;
	}
	
	 
	  void loadNutzerSugestListe(){
		  int meineId = 7; 
			asyncObj.getAlleNutzerAu�erMeineId(meineId, new AsyncCallback <Vector<Nutzer>>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("bba -" );
	 			}

				@Override
				public void onSuccess(Vector<Nutzer> resultListe) {
					 resultListe2 =resultListe;
						for (int i=0; i <=resultListe2.size(); i++ ){
							oracle.add(resultListe2.get(i).getId() +"---"+resultListe2.get(i).getVorname() + " " + resultListe2.get(i).getNachname());
						}
	 			}
				});
			
			// GET ALLE HASHSTAGS
			
//			asyncObj.getAlleNutzerAu�erMeineId(meineId, new AsyncCallback <Vector<Nutzer>>() {
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert("bba -" );
//	 			}
//
//				@Override
//				public void onSuccess(Vector<Nutzer> resultListe) {
//					 resultListe2 =resultListe;
//						for (int i=0; i <=resultListe2.size(); i++ ){
//							oracle.add(resultListe2.get(i).getId() +"---"+resultListe2.get(i).getVorname() + " " + resultListe2.get(i).getNachname());
//						}
//	 			}
//				});
			
			

			
			//  EmpfaengerHinzuf�genWrap.add(EmpfaengerHinzuf�genSug);

			EmpfaengerHinzuf�genBar.add(EmpfaengerHinzuf�genSug);
	  }
	 
	  
	void setloadNutzerSugestListeHizuf�genButton(){
		
		Button b = new Button("+ Hinzuf�gen"); 
		EmpfaengerHinzuf�genBar.add(b); 

		b.addClickHandler(new ClickHandler() {
			
				public void onClick(ClickEvent event) {

				  
				//	final saveRowIdAndRunHandler e  = new saveRowIdAndRunHandler(u);
					

				
					 final Button b2 = new Button(EmpfaengerHinzuf�genSug.getText() +"  (X)  ");
					 
					 b2.setStylePrimaryName("ButtonX-Nachrichtenverwaltung");
						 EmpfaengerHinzuf�genLeiste.add(b2);

				     b2.addClickHandler(new ClickHandler() {
						
						public void onClick(ClickEvent event) {
						//((	final int i = e.getRowId();
							//Window.alert("Auswahl " + e.getRowId());

									//EmpfaengerHinzuf�genLeiste.remove(i) ;
									b2.removeFromParent();
							
							
							} 
					});
					
					
					 
				} 
			});
		
	
		
		
	}
		
	
	   
	  
	
	
	

}
