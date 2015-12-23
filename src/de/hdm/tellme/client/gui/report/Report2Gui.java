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
 * Dieser Report zeigt alle Nutzerabonnements von einem bestimmten Nutzer an. 
 * @author Pokorski
 * @version 1.0
 * 
 */
/**
 * TODO
 */
/* TODO Kommentare nachtragen*/

public class Report2Gui  extends VerticalPanel {
	
	private VerticalPanel reportPanel = new VerticalPanel();
	private Label ueberSchrift1 =  new Label("Report2: Nutzerabonnements abfragen");
	private ListBox hashtagAboDropDown = new ListBox();
 	
 	public void onLoad()  {
		

		RootPanel.get("content").clear();
		RootPanel.get("content").add(ueberSchrift1);
		RootPanel
		.get("content_left")
		.add(new NutzerCellList()
				.generiereCellList(CellListModus.Report2_NutzerNutzerAbonnement));
		
		
 	}
	public void setLoginInfo(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		
	}

}