package de.hdm.tellme.shared.report;

import java.util.Vector;

/**
 * Report wird einfach ohne richtige Formatierung wiedergegegen. Sie wird in der Variable reportText gespeichert
 * und wird mit der Methode getReportText ausgegeben.
 * @author Alex
 *
 */

public class PlainTextReportWriter extends ReportWriter {
	
	
	/**
	 * Variable wird mit dem Ergebnis einer Umwandlung belegt.
	 */
	private String reportText ="";
	
	/**
	 * Die Variable reportText wird zurückgesetzt.
	 */
	public void resetReportText(){
		this.reportText = "";
	}
	/**
	 * Header-Text wird erstellt
	 * @return
	 */
	public String getHeader(){
		return "Report";
	}
	/**
	 * Text zur Trennung wird erstellt.
	 * @return
	 */
	public String getTrailer(){
		//Trennlinie für Report-Ende markieren.
		return"____________________________________________";
	}
	
	@Override
	public void process(Report2 r){
		
		//Erst wird der ReportText zurückgesetzt
		this.resetReportText();
		
		/*
		 * StringBuffer wird genutzt um alle Ergebnisse dem String anzuhängen
		 */
		StringBuffer ergebnis = new StringBuffer();
		
		/*
		 * Einzelne Teile des Reports werden ausgelesen und in Text übersetzt.
		 */
		ergebnis.append("*** "+ r.getTitel() +" ***\n\n");
		ergebnis.append(r.getKopfDaten() + "\n");
		ergebnis.append("Erstellungsdatum: "+r.getErstellungsDatum().toString() + "\n\n");
		Vector<Zeile> zeilen = r.getZeilen();
		
		for (Zeile zeile : zeilen){
			for(int k=0; k< zeile.getAnzahlSpalten(); k++) {
				ergebnis.append(zeile.getSpalteBei(k)+ "\t ; \t");
			}
			ergebnis.append("\n");
			
		}
		
		ergebnis.append("\n");
		ergebnis.append(r.getImprint() + "\n");
		
		/*
		 * Jetzt wird der StringBuffer in einen String umgewandelt.
		 * Dieser wird danach der Variable reportText zugewiesen. 
		 * Dadurch kann die Variable mit der getReportText()-Methode 
		 */
		this.reportText = ergebnis.toString();
	}
	
	
	/**
	 * Ergebnis wird ausgelesen 
	 * @return String der aus dem Text besteht
	 */
	public String getReportText(){
		return this.getHeader() + this.reportText + this.getTrailer();
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void process(Report1 r1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void process(Report3 r3) {
		// TODO Auto-generated method stub
		
	}
	



}
