package de.hdm.tellme.shared.report;

import de.hdm.tellme.client.gui.report.ImpressumReport;
/**
 * Die Klasse <class>ReportPanel</class> zeigt die Kopfdaten jeden Reports an. Dieser besteht aus einer Überschrift, den
 *  Impressums-Daten, den Daten des ausgewählten Nutzers und dem Erstellungsdatum des Reports.
 * @author denispokorski
 *
 */
public class ReportPanel {

	private String ueberschrift;
	private ImpressumReport impressum;
	private String kopfDaten;
	private String reportDatum;

	public String getUeberschrift() {
		return ueberschrift;
	}

	public void setUeberschrift(String ueberschrift) {
		this.ueberschrift = ueberschrift;
	}

	public ImpressumReport getImpressum() {
		return impressum;
	}

	public void setImpressum(ImpressumReport impressum) {
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
}
