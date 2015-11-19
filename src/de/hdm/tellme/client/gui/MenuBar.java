package de.hdm.tellme.client.gui;

 
 
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

 
public class MenuBar {

	// Kommentar nachtragen
	private HorizontalPanel menuBarPanel = new HorizontalPanel(); 
	private Button nachrichtBtn = new Button(); 
	private Button unterhaltungBtn = new Button(); 
	private Button aboverwaltungBtn = new Button(); 
	private Button profilBtn = new Button(); 
	private Image logo = new Image();


	public void onLoad() {
		
		// Kommentar nachtragen
		logo.setWidth("70px");
		// image.setUrl('http://127.0.0.1:8888/images/accounts.png');
		
		// Kommentar nachtragen 
		menuBarPanel.addStyleName("menuBarPanel");
		nachrichtBtn.addStyleName("nachrichtBtn");
		unterhaltungBtn.addStyleName("unterhaltungBtn");
		aboverwaltungBtn.addStyleName("aboverwaltungBtn");
		profilBtn.addStyleName("profilBtn");
		logo.addStyleName("logo");
	
		// Kommentar nachtragen
		menuBarPanel.add(nachrichtBtn);
		menuBarPanel.add(unterhaltungBtn);
		menuBarPanel.add(aboverwaltungBtn);
		menuBarPanel.add(profilBtn);
		
		// Kommentar nachtragen
		RootPanel.get().add(menuBarPanel);
		
	}

}
