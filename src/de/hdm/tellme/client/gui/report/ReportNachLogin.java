package de.hdm.tellme.client.gui.report;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * TODO
 */
/**
 * 
 * Diese Oberfläche erscheint nach dem erfolgreichen Login in der Reportapplikation. 
 * @author Zimmermann
 * @version 1.0
 * 
 */
public class ReportNachLogin extends VerticalPanel{
	 
		/* TODO Kommentare nachtragen*/
		
	private Label headline = new Label("Der Reportgenerator");//Überschrift eingefügt
	private Label willkommensText = new Label("Willkommen im Reportgenerator. Mit dieser Applikation ahben Sie die Möglichkeit drei verschiedene Reports auszugeben. Report 1 zeit Alle Nachrichten je Nutzer und Zeitraum an. Report 2 zeigt alle Abonnoments eines ausgewählten Nutzers an. Report 3 zeigt alle ausgewählten Abonennten von einem Hashtag an.");//Überschrift eingefügt
	private VerticalPanel reportPanel = new VerticalPanel();

	public void onLoad(){
 
		reportPanel.add(headline);
		reportPanel.add(willkommensText);
		RootPanel.get("content").clear();
		RootPanel.get("content").add(reportPanel);

		
	}
}
