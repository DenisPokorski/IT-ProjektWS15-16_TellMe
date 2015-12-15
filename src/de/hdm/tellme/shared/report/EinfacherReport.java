package de.hdm.tellme.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * In diesem Report sollen die Informationen der Superklasse Report 
 * in eine Tabelle eingefügt werden. 
 * Die Tabelle greift auf die Klassen Zeile und Spalte zurück, um die
 * Tabelle zu definieren.
 * 
 * 
 * 
 *
 */

public abstract class EinfacherReport extends Report implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Vector<Zeile> tabelle = new Vector<Zeile>();
	
	/**
	 * Zeilen werden hinzugefügt
	 * 
	 * @param z die hinzugefügte Zeile
	 */

	public void addZeile(Zeile z){
		this.tabelle.addElement(z);
	}
	/**
	 * Zele wird gelöscht
	 * @param z die gelöschte Zeile
	 */
	public void entferneZeile(Zeile z){
		this.tabelle.removeElement(z);
	}
	/**
	 * Auslesen des gesamten Vektors
	 * @return die Tabelle mit den jeweiligen Zeilen
	 */
	public Vector<Zeile> getZeilen(){
		return this.tabelle;
	}
}
