package de.hdm.tellme.shared.report;

import java.io.Serializable;

/*
 * Reports müssen Texte strukturiert abspeichern können.
 * Der abgespeicherte Text kann später durch den ReportWriter
 *  in verschiedene Zielformate konvertiert werden.
 *  
 *  
 *  Serializable, damit das Objekt vom Server
 *  auf den Client übertragen werden kann.
 * 
 */

public abstract class Paragraph implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
