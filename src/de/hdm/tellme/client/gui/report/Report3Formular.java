package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.server.report.Report2Generator;
import de.hdm.tellme.shared.bo.Nutzer;

public class Report3Formular extends Composite{
	private Nutzer nutzer = null;
	private Button report3GenerierenButton = new Button("Report 3 erstellen");
	
	public Report3Formular(){
		report3GenerierenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
//				Window.alert("halloE");
//
//				if (nutzer == null){
//					
//					Window.alert("hallo222");
//					return;
//				}
//				Window.alert("hallo1");

				NutzerDataProvider.gib().report3Generieren(nutzer);
				Window.alert("hallo");
				}			
		});
	}
	
	public VerticalPanel gibFormular(){
		VerticalPanel vpForm = new VerticalPanel();
		
		vpForm.add(new Label(nutzer.getVorname() + nutzer.getNachname()));
		vpForm.add(report3GenerierenButton);
		return vpForm;
	}
	public void report3Generieren(NutzerZelle.ZellenObjekt ZellenObjekt){
		this.nutzer = ZellenObjekt.nutzer;

	}


}
