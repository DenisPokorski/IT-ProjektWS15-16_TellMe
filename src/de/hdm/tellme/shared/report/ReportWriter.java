package de.hdm.tellme.shared.report;



/**
 * Diese Klasse dient dazu, um auf dem Client, die ihm vom Server
 * zur Verfügung gestellten Report-Objekte in ein menschenlesbares
 * Format zu überführen.
 * 
 * Methoden zum Auslesen werden in den Subklassen definiert. 
 */

public abstract class ReportWriter {

	/**
	 * Übersetzen eines Report1 in das Zielformat, Report1 stellt alle
	 * Nachrichten für einen Nutzer in einem gewissen Zeitraum dar.
	 */

	public abstract void process(Report1 r1);

	/**
	 * Übersetzen eines Report2 in das Zielformat, Report2 stellt alle
	 * NutzerAbonnements eines Nutzers dar.
	 */

	public abstract void process(Report2 r2);

	/**
	 * Übersetzen eines Report3 in das Zielformat. Report3 stellt alle
	 * HashtagAbonnements eines Nutzers dar.
	 */
	
	public abstract void process(Report3 r3);

}
