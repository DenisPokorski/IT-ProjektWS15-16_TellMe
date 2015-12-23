package de.hdm.tellme.client.gui.report;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.Impressum;
import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.server.report.Report2Generator;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nutzer;
/**
 * TODO
 * @author denispokorski
 *
 */
public class ReportFormular1 extends Composite{


	
}


//	private Nutzer nutzer = null;
//Button report3GenerierenButton = new Button("Report 3 erstellen");
//
//public ReportFormular1(){
//	report3GenerierenButton.addClickHandler(new ClickHandler(){
//		public void onClick(ClickEvent event){
//			if (nutzer == null){
//				return;
//			}
//		//	NutzerDataProvider.gib().report3Generieren(nutzer);
//			}			
//	});
//}
//
//public VerticalPanel gibFormular(){
//	VerticalPanel vpForm = new VerticalPanel();
//	
//	vpForm.add(new Label(nutzer.getVorname() + nutzer.getNachname()));
//	vpForm.add(report3GenerierenButton);
//	return vpForm;
//}
//public void report3Generieren(NutzerZelle.ZellenObjekt ZellenObjekt){
//	this.nutzer = ZellenObjekt.nutzer;
//
//}
//