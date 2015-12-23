package de.hdm.tellme.client.gui.editor;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.shared.bo.Nachricht;

public class NeuigkeitenEinzelNachricht extends Composite {

	public static FlowPanel EinzelneNachricht(Nachricht _nachricht, boolean _istUnterhaltungsRahmen) {
		
		FlowPanel fpNachricht = new FlowPanel();
		fpNachricht.setWidth("550px");
		
		HorizontalPanel hpHeader = new HorizontalPanel();
		
		Label NachrichtenTyp = new Label("");
		NachrichtenTyp.setText("OE");
		hpHeader.add(NachrichtenTyp);
		
		Label OeffentlicheNachrichtenHerkunft = new Label("");
		OeffentlicheNachrichtenHerkunft.setText("#");
		hpHeader.add(OeffentlicheNachrichtenHerkunft);
		
		Label DatumUhrzeit = new Label("22.12.2015 09:50");
		hpHeader.add(DatumUhrzeit);
		
		fpNachricht.add(hpHeader);
		
		if(_istUnterhaltungsRahmen){
				Button btnVerlasseUnterhaltung = new Button("Unterhaltung verlassen");
				hpHeader.add(btnVerlasseUnterhaltung);
		}

		HorizontalPanel hpNachricht = new HorizontalPanel();
		
		Label Nachrichtinhalt = new Label("");
		Nachrichtinhalt.setWidth("90%");
		Nachrichtinhalt.setWordWrap(true);
		Nachrichtinhalt.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
		hpNachricht.add(Nachrichtinhalt);		
		
		fpNachricht.add(hpNachricht);
		

		HorizontalPanel hpFooter = new HorizontalPanel();
		

		Label Verfasser = new Label("");
		Verfasser.setText("Denis Feltrin");
		Verfasser.setTitle("d.feltrin@aol.com");
		hpFooter.add(Verfasser);	


		Label Empfänger = new Label("");
		Empfänger.setText("Denis Feltrin");
		Empfänger.setTitle("d.feltrin@aol.com");
		hpFooter.add(Empfänger);	
		
		Button Antwort = new Button();
		Antwort.setText("Antwort");
		hpFooter.add(Antwort);
		
		fpNachricht.add(hpFooter);
		
		return fpNachricht;
	}
}
