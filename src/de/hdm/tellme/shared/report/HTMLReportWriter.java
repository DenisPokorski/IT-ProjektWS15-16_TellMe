package de.hdm.tellme.shared.report;

import java.util.Vector;

/**
 * Der ReportWriter formatiert den Report mit HTML.
 * 
 * @author Nicole Reum
 *
 */

public class HTMLReportWriter extends ReportWriter {
	
	/**
	 * Die Variable wird mit dem Ergebnis  in HTML-Text umgewandelt.
	 */

	private String reportText = "";
	
	/**
	 * Variable wird zurückgesetzt
	 */

	public void resetReportText() {
		this.reportText = "";
	}
	
	/**
	 * Ein Paragraph-Objekt wird in HTML umgewandelt
	 * @param p der Paragraph
	 * @return HTML-Text
	 */

	public String paragraphzuHTML(Paragraph p) {
		if (p instanceof VerbundenerParagraph) {
			return this.paragraphzuHTML((VerbundenerParagraph) p);
		} else {
			return this.paragraphzuHTML((EinfacherParagraph) p);
		}
	}
	
	/**
	 * Das Objekt VerbundenerParagraph wird in HTML umgewandelt
	 * @param p VerbundenerParagraph
	 * @return HTML-Text
	 */

	public String paragraphzuHTML(VerbundenerParagraph p) {
		StringBuffer ergebnis = new StringBuffer();

		for (int i = 0; i < p.getAnzahlParagraphen(); i++) {
			ergebnis.append("<p>" + p.getParagraphAt(i) + "</p>");
		}

		return ergebnis.toString();
	}
	
	/**
	 * Das Objekt EinfacherParagraph in HTML umwandeln
	 * @param p EinfacherParagraph
	 * @return HTML-Text
	 */

	public String paragraphzuHTML(EinfacherParagraph p) {
		return "<p>" + p.toString() + "</p>";
	}
	
	/**
	 * Es wird ein HTML-Header-Text produziert.
	 * @return HTML-Text
	 */

	public String getHeader() {
		StringBuffer ergebnis = new StringBuffer();

		ergebnis.append("<html><head><title></title></head></html>");
		return ergebnis.toString();
	}
	
	/**
	 * Es wird ein HTML-Header-Text produziert.
	 * @return HTML-Text
	 */
	
	public String getTrailer() {
		return "</body></html>";
	}
	
	  /**
	   * Übergeben des Reports und Ablage im Zielformat. 
	   * 
	   * @param r der Report
	   */

	public void process(Report2 r) {
		// Das Ergebnis vorhergehender Prozessierungen wird gelöscht.
		this.resetReportText();
	    /*
	     * In den Buffer werden die Ergebnisse der Prozessierung geschrieben.
	     */
		StringBuffer ergebnis = new StringBuffer();
		/*
		 * Die einzelnen Bestandteile des Reports werden ausgelesen und in HTML übersetzt.
		 */
		ergebnis.append("<H1>" + r.getTitel() + "</H1>");
		ergebnis.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		ergebnis.append("<td valign=\"top\"><b>"
				+ paragraphzuHTML(r.getKopfDaten()) + "</b></td>");
		ergebnis.append("<td valign=\"top\">" + paragraphzuHTML(r.getImprint())
				+ "</td>");
		ergebnis.append("</tr><tr><td></td><td>"
				+ r.getErstellungsDatum().toString() + "</td></tr></table>");

		Vector<Zeile> zeilen = r.getZeilen();
		ergebnis.append("<table style=\"width:400px\">");

		for (int i = 0; i < zeilen.size(); i++) {
			Zeile zeile = zeilen.elementAt(i);
			ergebnis.append("<tr>");
			for (int k = 0; k < zeile.getAnzahlSpalten(); k++) {
				if (i == 0) {
					ergebnis.append("<td style\background:silver;front-weight:bold\">"
							+ zeile.getSpalteBei(k) + "</td>");
				} else {
					if (i > 1) {
						ergebnis.append("<td style=\"border-top:1px solid silver\">"
								+ zeile.getSpalteBei(k) + "</td>");
					} else {
						ergebnis.append("<td valign=\"top\">"
								+ zeile.getSpalteBei(k) + "</td>");
					}
				}
			}
			ergebnis.append("</tr>");
		}

		ergebnis.append("</table>");
	    /*
	     * Buffer wird in einen String umgewandelt und der reportText-Variablen zugewiesen
	     */

		this.reportText = ergebnis.toString();
	}

	public String getReportText() {
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
