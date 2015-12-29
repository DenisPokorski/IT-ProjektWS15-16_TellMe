package de.hdm.tellme.shared.report;

import de.hdm.tellme.client.Impressum;

public class ReportPanel {
	
	
	
	private String ueberschrift; 
	private Impressum impressum; 
	private String kopfDaten;
	private String reportDatum;
	
	
	public String getUeberschrift() {
		return ueberschrift;
	}
	public void setUeberschrift(String ueberschrift) {
		this.ueberschrift = ueberschrift;
	}
	public Impressum getImpressum() {
		return impressum;
	}
	public void setImpressum(Impressum impressum) {
		this.impressum = impressum;
	}
	public String getKopfDaten() {
		return kopfDaten;
	}
	public void setKopfDaten(String kopfDaten) {
		this.kopfDaten = kopfDaten;
	}
	public String getReportDatum() {
		return reportDatum;
	}
	public void setReportDatum(String reportDatum) {
		this.reportDatum = reportDatum;
	} 
	
	
//	public class SimpleReport extends ReportPanel {
//		private HtmlGenerator null; 
//		private Vector <Nutzer> nList ; 
//		private Vector <Hashtag> htList; 
//		private String contentTableString = null; 
//		
//		public SimpleReport(Vector <Nutzer> nList, Nutzer n  ){
//			  ueberschrift= "Report 1"; 
//			  reportDatum= "Date";
//			  kopfDaten = HtmlGenerator.setKopfdatenString();
//			  contentTableString = HtmlGenerator.setcontentTableString(nList);
//			  HtmlGenerator.generate(ueberschrift,kopfdaten, reportDatum,contentTableString, footerString  );
//		}
//		
//		public SimpleReport(Vector <Hashtag> nList, Nutzer n  ){
//			  ueberschrift= "Report 1"; 
//			  reportDatum= "Date";
//			  kopfDaten = HtmlGenerator.setKopfdatenString();
//			  contentTableString = HtmlGenerator.setcontentTableString(nList);
//			  HtmlGenerator.generate(ueberschrift,kopfdaten, reportDatum,contentTableString, impressum  );
//		}
//		
//	 
//		 
//		
//		
//	}
	
	public class ProReport {
		//Nachricht Nutzer 

	}

}
