package de.hdm.tellme.client.gui.report;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * TODO
 * 
 * @author denispokorski
 *
 */
public class ReportFormular1 extends Composite {

	public ReportFormular1() {
	}
	public void reportGenerieren(Nutzer n, Timestamp vonDate, Timestamp bisDate) {
//		NutzerDataProvider.gib(1).report1Generieren(n, vonDate, bisDate);
		Window.alert(""+n.getVorname());
		
	}

}
