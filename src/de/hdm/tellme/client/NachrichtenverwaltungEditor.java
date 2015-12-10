package de.hdm.tellme.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.EditorServiceAsync;
import de.hdm.tellme.shared.bo.Nutzer;

import java.util.Arrays;
import java.util.List;;

public class NachrichtenverwaltungEditor extends VerticalPanel {

	private final EditorServiceAsync asyncObj = GWT.create(EditorService.class);
  	
	private HorizontalPanel NachrichtenÜbersichtMasterRap = new HorizontalPanel();
	private VerticalPanel  NachrichtenÜbersichtMasterLeft = new VerticalPanel(); 
	private VerticalPanel  NachrichtenÜbersichtMasterRight = new VerticalPanel(); 
	
	private HorizontalPanel NachrichtenÜbersichtMasterLeftButtonTop = new HorizontalPanel();
	private Button privatNachrichten = new Button("Privat");
	private Button publicNachrichten = new Button("Public");
	private Button nutzeraboNachrichten = new Button("NautzerAbo");
	private Button hashtagaboNachrichten = new Button("HastagAbo");
	private Button UnterhaltungStarten = new Button("Unterhaltung erstellen");

	
	
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	  Vector<Nutzer> resultListe2 = new Vector<Nutzer>();

		 
	  	HorizontalPanel NachrichtSendeBtnWrap = new HorizontalPanel();
		    TextArea textArea = new TextArea();
		    Button sendenButton = new Button("Nachricht Senden");
		 
			private VerticalPanel  EmpfaengerHinzufügenWrap = new VerticalPanel(); 
			SuggestBox  EmpfaengerHinzufügenSug = new SuggestBox(oracle); 
			//EmpfaengerHinzufügenBar
			HorizontalPanel EmpfaengerHinzufügenBar = new HorizontalPanel(); 
			private FlowPanel  EmpfaengerHinzufügenLeiste = new FlowPanel(); 

	  /**
	   * The list of data to display.
	   */
	  private static final List<String> UserTestList = Arrays.asList("User 1", "User 2",
	      "User 3", "User 4", "User 5", "User 6", "User 7");

	  public void onLoad() {
		  
		  
		  
		  NachrichtenÜbersichtMasterLeftButtonTop.add(privatNachrichten);
		  NachrichtenÜbersichtMasterLeftButtonTop.add(publicNachrichten);
		  NachrichtenÜbersichtMasterLeftButtonTop.add(nutzeraboNachrichten);
		  NachrichtenÜbersichtMasterLeftButtonTop.add(hashtagaboNachrichten);
		  NachrichtenÜbersichtMasterLeft.add(NachrichtenÜbersichtMasterLeftButtonTop);
		  
		  
		  //NachrichtenÜbersichtMasterLeft.add(//CellList);
		  NachrichtenÜbersichtMasterLeft.add(UnterhaltungStarten);
		  
		  NachrichtenÜbersichtMasterRap.add(NachrichtenÜbersichtMasterLeft);
		  NachrichtenÜbersichtMasterRap.add(NachrichtenÜbersichtMasterRight);
		  
		  
		  
		  
		  genearteCellList();
		  loadNutzerSugestListe();
		  setloadNutzerSugestListeHizufügenButton();
		  EmpfaengerHinzufügenWrap.add(EmpfaengerHinzufügenBar);
		  EmpfaengerHinzufügenWrap.add(EmpfaengerHinzufügenLeiste);
		
		  NachrichtSendeBtnWrap.add(textArea);
		  NachrichtSendeBtnWrap.add(sendenButton);
		  EmpfaengerHinzufügenWrap.add(NachrichtSendeBtnWrap);
		  NachrichtenÜbersichtMasterRap.add(EmpfaengerHinzufügenWrap);
		  //EmpfaengerHinzufügenWrap
		  RootPanel.get("content").add(NachrichtenÜbersichtMasterRap);

		  
 	   }
	
	  int counter = -1;
	  
	  void loadNutzerSugestListe(){
		  int meineId = 7; 
			asyncObj.getAlleNutzerAußerMeineId(meineId, new AsyncCallback <Vector<Nutzer>>() {
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
			
//			asyncObj.getAlleNutzerAußerMeineId(meineId, new AsyncCallback <Vector<Nutzer>>() {
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert("bba -" );
//	 			}
//
//				@Override
//				public void onSuccess(Vector<Nutzer> resultListe) {
//					 resultListe2 =resultListe;
//						for (int i=0; i <=resultListe2.size(); i++ ){
// 							oracle.add(resultListe2.get(i).getId() +"---"+resultListe2.get(i).getVorname() + " " + resultListe2.get(i).getNachname());
//						}
//	 			}
//				});
			
			

			
			//  EmpfaengerHinzufügenWrap.add(EmpfaengerHinzufügenSug);

			EmpfaengerHinzufügenBar.add(EmpfaengerHinzufügenSug);
	  }
	 
	  
	void setloadNutzerSugestListeHizufügenButton(){
		
		Button b = new Button("+ Hinzufügen"); 
		EmpfaengerHinzufügenBar.add(b); 
  
		b.addClickHandler(new ClickHandler() {
			
				public void onClick(ClickEvent event) {

				  
				//	final saveRowIdAndRunHandler e  = new saveRowIdAndRunHandler(u);
 					
 
				
					 final Button b2 = new Button(EmpfaengerHinzufügenSug.getText() +"  (X)  ");
					 
					 b2.setStylePrimaryName("ButtonX-Nachrichtenverwaltung");
						 EmpfaengerHinzufügenLeiste.add(b2);

				     b2.addClickHandler(new ClickHandler() {
						
						public void onClick(ClickEvent event) {
						//((	final int i = e.getRowId();
							//Window.alert("Auswahl " + e.getRowId());

									//EmpfaengerHinzufügenLeiste.remove(i) ;
									b2.removeFromParent();
							
							
							} 
					});
					
					
					 
				} 
			});
		
	
		
		
	}
		
	
	   
	  
	void  genearteCellList(){
		VerticalPanel cellListPanel = new VerticalPanel();//Panel wird erstellt

		    TextCell textCell = new TextCell();

		    CellList<String> cellList = new CellList<String>(textCell);
		    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
		    cellList.setSelectionModel(selectionModel);
		    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		      public void onSelectionChange(SelectionChangeEvent event) {
		        String selected = selectionModel.getSelectedObject();
		        if (selected != null) {
		          Window.alert("Auswahl " + selected);
		        }
		      }
		    });

		    cellList.setRowCount(UserTestList.size(), true);

		    cellList.setRowData(0, UserTestList);
		    Label a = new Label ("aa");
		    cellListPanel.add(a);
		    cellListPanel.add(cellList);
		    RootPanel.get("content").add(cellListPanel);
	  }
	  
	
	
}
