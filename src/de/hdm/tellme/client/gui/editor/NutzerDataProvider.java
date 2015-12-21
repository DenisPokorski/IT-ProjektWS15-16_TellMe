package de.hdm.tellme.client.gui.editor;

import java.util.List;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.tellme.shared.bo.Nutzer;

public class NutzerDataProvider {

	private ListDataProvider<NutzerZelle.ZellenObjekt> dataProvider = new ListDataProvider<NutzerZelle.ZellenObjekt>();
		
	private static NutzerDataProvider instanz = null;

	public static NutzerDataProvider gib() {
		if (instanz == null)
			instanz = new NutzerDataProvider();
		return instanz;
	}

	private NutzerDataProvider() {
		List<NutzerZelle.ZellenObjekt> dataList = dataProvider.getList();

		// Daten aus DB lesen
		for (int i = 0; i <= 200; i++) {
			NutzerZelle.ZellenObjekt nah = new NutzerZelle(). new ZellenObjekt();
			Nutzer nu = new Nutzer();
			nu.setVorname("Hans" + i);
			nu.setNachname("Peter" + i);
			
			nah.nutzer = nu;
			
			nah.aboniert = false;
			
			if(i%2==0)
				nah.aboniert = true;
			dataList.add(nah);
		}
	}

	public void addDataDisplay(CellList<NutzerZelle.ZellenObjekt> cellList) {
		dataProvider.addDataDisplay(cellList);
	}

	public void refreshDisplays() {
		dataProvider.refresh();
	}
	
	public void abonieren(Nutzer _nutzerAbonieren){
		//db
	}
	
	public void deabonieren(Nutzer _nutzerDeabonieren){
		//idb
	}
}
