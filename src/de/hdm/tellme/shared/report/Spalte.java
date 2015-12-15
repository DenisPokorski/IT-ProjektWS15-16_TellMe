package de.hdm.tellme.shared.report;

import java.io.Serializable;

/**
 * Spalte eines Zeilen-Objekts. Spalten-Objekt implementiert Serializable, damit
 * wieder von Server an den Client übertragen werden kann.
 *
 */
public class Spalte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Der Wert eines Spaltenobjekts entspricht dem jeweiligen Inhalt also dem
	 * Text in der Zelle.
	 */
	private String wert = "";

	/**
	 * Konstruktor würde auch automatisch erstellt werden.
	 */
	public Spalte() {

	}

	/**
	 * Konstrukter "erzwingt", dass der beim Anlegen eines Eintrages ein Wert
	 * zugewiesen wird.
	 * 
	 * @param s der Inhalt der Spalte, bzw. der Wert des Spalten-Objekts.
	 *            
	 */
	public Spalte(String s) {
		this.wert = s;
	}

	/**
	 * Spaltenwert wird ausgelesen
	 * 
	 * @return Eintrag der Spalte
	 */
	public String getWert() {
		return wert;
	}

	/**
	 * Spaltenwert wird gesetzt
	 * 
	 * @param wert  neuer Wert des Spalten-Objekts
	 *           
	 */

	public void setWert(String wert) {
		this.wert = wert;
	}

	/**
	 * Spalte wird durch toString-Methode umgewandelt.
	 */
	@Override
	public String toString() {
		return this.wert;
	}

}
