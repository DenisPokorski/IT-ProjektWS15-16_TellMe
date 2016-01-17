package de.hdm.tellme.client.gui.report;

import java.sql.Timestamp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
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
