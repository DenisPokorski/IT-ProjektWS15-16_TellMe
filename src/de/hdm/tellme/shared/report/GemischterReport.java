package de.hdm.tellme.shared.report;

import java.io.Serializable;
import java.util.Vector;

public abstract class GemischterReport
	extends Report 
	implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Die Menge der einzelnen Reports.
	 */
	
	private Vector<Report> teilReports = new Vector<Report>();
	
	/**
	 * Teilreport wird zum Report hinzugefügt
	 * @param r der Teilreport der hinzugefügt werden soll
	 */
	public void addTeilReport(Report r){
		this.teilReports.addElement(r);
	}
	
	/**
	 * Teilreport aus Report soll gelöscht werden
	 * @param r der zu löschende Teil
	 */
	public void entferneTeilReport(Report r){
		this.teilReports.removeElement(r);
	}
	
	
	/**
	 * Ausgabe der Anzahl der Teilreports	
	 * @return int Anzahl der Teilreports
	 */
	public int getAnzahlDerTeilReports(){
		return this.teilReports.size();
		
	}

	/**
	 * Ausgabe eines bestimmten Teil Reports
	 * @param i Index eines TeilReports, der ausgelesen werden soll
	 * @return Position des Teilreports
	 */
	public Report getTeilReportBei(int i){
		return this.teilReports.elementAt(i);
	}
	


}
