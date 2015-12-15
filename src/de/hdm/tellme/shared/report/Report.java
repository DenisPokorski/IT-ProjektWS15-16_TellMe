package de.hdm.tellme.shared.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Basisklasse aller Reports. Reports sind als Serializable deklariert, damit
 * sie von dem Server an den Client gesendet werden können. Der Zugriff auf
 * Reports erfolgt also nach deren Bereitstellung lokal auf dem Client.
 * 
 * 
 * Ein Report besitzt eine Reihe von Standartelementen. Sie werden mittels
 * Attributen modelliert und dort dokumentiert.
 */

public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ein kleines Impressum, das eine Art Briefkopf darstellt. Jedes
	 * Unternehmen einige Daten wie Firmenname, Adresse, Logo, etc. auf
	 * Geschäftsdokumenten ab. Dies gilt auch für die hier realisierten Reports.
	 */
	private Paragraph imprint = null;

	/**
	 * Kopfdaten des Reports
	 */
	private Paragraph kopfDaten = null;

	/*
	 * Jeder Report soll einen eigenen Titel besitzen
	 */
	private String titel = "Report";

	/*
	 * Jeder Report braucht ein Erstellungsdatum
	 */
	private Date erstellungsDatum = new Date();

	/**
	 * Impressum wird ausgelesen
	 * 
	 * @return Text des Impressums
	 * 
	 */
	public Paragraph getImprint() {
		return imprint;
	}

	/**
	 * Impressum wird gesetzt
	 * 
	 * @param imprint
	 *            Text des Impressums
	 */

	public void setImprint(Paragraph imprint) {
		this.imprint = imprint;
	}

	/**
	 * Kopfdaten werden ausgelesen
	 * 
	 * @return Text der Kopfdaten
	 * 
	 */
	public Paragraph getKopfDaten() {
		return kopfDaten;
	}

	/**
	 * Kopfdaten werden gesetzt
	 * 
	 * @param kopfDaten
	 *            Text der Kopfdaten
	 */
	public void setKopfDaten(Paragraph kopfDaten) {
		this.kopfDaten = kopfDaten;
	}

	/**
	 * Report-Titel wird ausgelesen
	 * 
	 * @return titel Titeltext
	 */
	public String getTitel() {
		return titel;
	}

	/**
	 * Report-Titel wird gesetzt
	 * 
	 * @param titel
	 *            Titeltext
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}

	/**
	 * Erstellungsdatum wird ausgelesen
	 * 
	 * @return Datum der Report-Erstellung
	 */
	public Date getErstellungsDatum() {
		return erstellungsDatum;
	}

	/**
	 * Erstellungsdatum wird gesetzt
	 * 
	 * @param erstellungsDatum
	 *            Zeitpunkt der Report-Erstellung
	 */
	public void setErstellungsDatum(Date erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}
}
