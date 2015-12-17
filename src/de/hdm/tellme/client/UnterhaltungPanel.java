package de.hdm.tellme.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class UnterhaltungPanel {
	
	
	//public void unterhaltungsInhaltazeigenPanel(int unterhaltungsId){
		
		//VerticalPanel nachrichtenVerlauf =  new UnterhaltungErstellen().oeffentlicheUnterhaltungNachrichtenAnzeigen(unterhaltungsId);

	public void unterhaltungsPanelGenerierung(Vector <Nachricht> nachrichtenListe){
		
		
		//RootPanel.get("content_right").add(antwortPanel);
		
	}
	
	public void unterhaltungsInhaltazeigenPanel(int uId){
			RootPanel.get("content_right").clear();

			VerticalPanel vP = new VerticalPanel();
			HorizontalPanel antwortPanel = new NachrichtenPanel().antwortErstellenPanel(24);
 			VerticalPanel nachrichtenPanel = new UnterhaltungErstellen().ladeAlleNachrichtenVonUnterhaltungListe(uId);
 			vP.add(nachrichtenPanel);
			vP.add(antwortPanel);

			RootPanel.get("content_right").add(vP);
		}

	public Button unterhaltungStarten(){
	 Button UnterhaltungStarten = new Button("Unterhaltung erstellen");

    UnterhaltungStarten.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("content").clear();
 			RootPanel.get("content_right").clear();

		 new NachrichtenPanel().ErsteNarchichtErstellenPanel();
		}
	});
    return UnterhaltungStarten;
	}
	
	
	private static class Contact {
	    private final String address;
	    private final String name;

	    public Contact(String name, String address) {
	      this.name = name;
	      this.address = address;
	    }
	  }
	
//	  private static List<Contact> CONTACTS = Arrays.asList(
//			    new Contact("John", "123 Fourth Road"),
//			    new Contact("Mary", "222 Lancer Lane"));
//		
// private static List<Unterhaltung> UNTERHALTUNG = Arrays.asList();
	  private static List< Contact> CONTACTS = Arrays.asList();
	  
	  
	public CellList oeffenlticheUnterhaltungenAnzeigen() {
		ArrayList<String> d = new UnterhaltungErstellen().ladeAlleOeffentlichenUnterhaltungen();
		
		    TextCell cell = new TextCell();
		    CellList cellList = new CellList(cell);
		    
		    //put data into the cellList widget
		    //cellList.setRowData(d);
		    cellList.setRowCount(d.size(), true);

		    // Push the data into the widget.
		    cellList.setRowData(0, d);

		    Window.alert("d-size " + d.size() );
//		    cellList.addRangeChangeHandler(new RangeChangeEvent.Handler() {
//		        @Override
//		        public void onRangeChange(RangeChangeEvent event) {
//		          Range range = event.getNewRange();
//		          int start = range.getStart();
//		          int length = range.getLength();
//
//		          // Create the data to push into the view. At this point, you could send
//		          // an asynchronous RPC request to a server.
//		          List<String> data =d;
//		          
//		          for (int i = start; i < start + length; i++) {
//		            data.add("Item " + i);
//		          }
//
//		          // Push the data into the list.
//		          updateRowData(start, data);
//		        }
//		      });
		    
		    CellList<String> cellList2 = new CellList<String>(new TextCell());
		    cellList.setVisibleRangeAndClearData(new Range(0, 25), true);

		    SimplePager pager = new SimplePager();
		    pager.setDisplay(cellList);
		    
		    
		    
		    

		    // Add the widgets to the root panel.
		    VerticalPanel vPanel = new VerticalPanel();
		    vPanel.add(pager);
		    vPanel.add(cellList);
		    RootPanel.get("content_left").add(vPanel);
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		
//	    // Create a CellTable.
//	    CellTable<Contact> table = new CellTable<Contact>();
//
//	    // Create name column.
//	    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
//	      @Override
//	      public String getValue(Contact contact) {
//	        return contact.name;
//	      }
//	    };
//
//	    // Create address column.
//	    TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
//	      @Override
//	      public String getValue(Contact contact) {
//	        return contact.address;
//	      }
//	    };
//
//	    // Add the columns.
//	    table.addColumn(nameColumn, "Name");
//	    table.addColumn(addressColumn, "Address");
//
//	    // Set the total row count. This isn't strictly necessary, but it affects
//	    // paging calculations, so its good habit to keep the row count up to date.
//	    table.setRowCount(CONTACTS.size(), true);
//
//	    // Push the data into the widget.
//	    table.setRowData(0, CONTACTS);

	    // Add it to the root panel.
	    RootPanel.get("content_left").add(cellList);
	 
	
//        Window.alert("hallo " );
//
// 			UnterhaltungErstellen n=  new UnterhaltungErstellen();
// 			  unterhaltungListeV =  n.ladeAlleOeffentlichenUnterhaltungen( );
//			  Arrays.asList(unterhaltungListeV);
//			    cellList.setRowCount(unterhaltungListeV.size(), true);
//			    cellList.setRowData(0,unterhaltungListeV.get(i) ) ;
//  		    
//
//  	        Window.alert("hallo 2" );
//
// // 		    if(unterhaltungListeV.size() != 0){
//		        
//			cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
//
////		    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
////		    cellList.setSelectionModel(selectionModel);
////		    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
////		       public void onSelectionChange(SelectionChangeEvent event) {
////		        String selected = selectionModel.getSelectedObject();
////		        if (selected != null) {
////		          Window.alert("Auswahl " + selected);
////		        }
////		      }
////		    });
//		
//	
//		    //cellListPanel.add(cellList);
//				 RootPanel.get("content_left").add(cellList);
 				return null;
//
//			
//// 		    }
//		    
//		    
 
		}

	protected void updateRowData(int start, List<String> data) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	}


