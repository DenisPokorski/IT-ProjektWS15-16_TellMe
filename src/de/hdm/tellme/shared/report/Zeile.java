package de.hdm.tellme.shared.report;

import java.io.Serializable;
import java.util.Vector;

public class Zeile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Spalten der Tabelle werden in Vector gespeichert.
	 */
	private Vector<Spalte> spalten = new Vector<Spalte>();
	
	
	/**
	 * Spalte hinzufügen
	 * @param s neue Spalte
	 */
	public void addSpalte(Spalte s){
		this.spalten.addElement(s);
	}
	
	
	/**
	 * Spalte entfernen
	 * @param s entfernte Spalte
	 */
	public void entferneSpalte(Spalte s){
		this.spalten.removeElement(s);
	}
	
	
	/**
	 * Alle Spalten der Tabelle werden ausgelesen.
	 * @return Vector der Spalten 
	 */
	
	public Vector<Spalte> getSpalten(){
		return this.spalten;
	}
	

	/**
	 * Auslesen der Anzahl der Spalten
	 * @return int Spaltenanzahl
	 */
	public int getAnzahlSpalten(){
		return this.spalten.size();
	}
	
	
	/**
	 * Auslesen einer individuellen Spalte
	 * @param i Index um ausgewählte Spalte zu erkennen
	 * @return gesuchte Spalte
	 */
	public Spalte getSpalteBei(int i){
		return this.spalten.elementAt(i);
	}

}
