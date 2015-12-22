package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.CellListModus;
import de.hdm.tellme.client.gui.editor.HashtagCellList;
import de.hdm.tellme.client.gui.editor.NutzerCellList;
import de.hdm.tellme.shared.LoginInfo;
/**
 * 
 * Dieser Report zeigt alle Hashtagabonnements von einem bestimmten Nutzer an. 
 * @author Zimmermann
 * @version 1.0
 * 
 */

/* TODO Kommentare nachtragen*/

public class Report3  extends VerticalPanel {
	
	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift1 =  new Label("Report3: Hashtagabonnements abfragen");
	private ListBox hashtagAboDropDown = new ListBox();
 	
 	public void onLoad()  {
		

		RootPanel.get("content").clear();
		RootPanel.get("content").add(ueberSchrift1);
//		RootPanel
//		.get("content_left")
//		.add(new NutzerCellList()
//				.generiereCellList(CellListModus.Report));
		
		
 	}
	public void setLoginInfo(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		
	}

}