package de.hdm.tellme.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class Showcase extends VerticalPanel {

	
	@Override
public void onLoad() {
		
		super.onLoad();
		
		this.add(this.erstelleUeberschrift(this.getUeberschriftText()));
		
		this.run();
		}
	
	


	protected HTML erstelleUeberschrift(String text) {
		HTML content = new HTML(text);
		content.setStylePrimaryName("Tellme-ueberschrift");
	    return content;
	  }
	
	protected void append(String text) {
		HTML content = new HTML(text);
		content.setStylePrimaryName("Tellme-simplertext)");
		this.add(content);
	}
	
	protected abstract String getUeberschriftText(); 
	protected abstract void run();

}


