package de.hdm.tellme.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.tellme.client.gui.editor.NutzerDataProvider;
import de.hdm.tellme.client.gui.editor.NutzerZelle;
import de.hdm.tellme.server.report.Report2Generator;
import de.hdm.tellme.shared.bo.Nutzer;
/**
 * TODO
 * @author denispokorski
 *
 */
public class ReportFormular2 extends Composite{
	private Nutzer nutzer = null;
	Button report2GenerierenButton = new Button("Report 2 erstellen");
	
	public ReportFormular2(){
		report2GenerierenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if (nutzer == null){
					return;
				}
				NutzerDataProvider.gib().report2Generieren(nutzer);
				}			
		});
	}
	
	public VerticalPanel gibFormular(){
		VerticalPanel vpForm = new VerticalPanel();
		
		vpForm.add(new Label(nutzer.getVorname() + nutzer.getNachname()));
		vpForm.add(report2GenerierenButton);
		return vpForm;
	}
	public void report3Generieren(NutzerZelle.ZellenObjekt ZellenObjekt){
		this.nutzer = ZellenObjekt.nutzer;

	}


}
