package de.hdm.tellme.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * In der Klasse wird eine Menge einzelner Absätze dargestellt. Die
 * TeilParagraphen werden in einem Vector abgelegt.
 * 
 * @author Nicole Reum
 *
 */

public class VerbundenerParagraph extends Paragraph implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Der Speicherort für die TeilParagraphen wird definiert.
	 */

	private Vector<EinfacherParagraph> teilParagraph = new Vector<EinfacherParagraph>();

	/**
	 * Ein TeilParagraph wird hinzugefügt.
	 * 
	 * @param p der hinzuzufügende TeilParagraph
	 *            
	 */

	public void addTeilParagraph(EinfacherParagraph p) {
		this.teilParagraph.addElement(p);
	}
	
	/**
	 * EinTeilParagrap wird entfernt.
	 * @param p der zu löschende TeilParagraph
	 */
	
	public void entfernenTeilParagraph(EinfacherParagraph p) {
		this.teilParagraph.removeElement(p);
	}
	
	/**
	 * Auslesen aller Teilparagraphen
	 * @return Vector mit allen Teilparagraphen
	 */
	
	public Vector<EinfacherParagraph> getTeilParagraph () {
		return this.teilParagraph;
	}
	
	/**
	 * Die Anzahl der Teilparagraphen wird ausgelesen
	 * @return Anzahl der Teilpraragraphen
	 */
	
	public int getAnzahlParagraphen() {
		return this.teilParagraph.size();
	}
	
	/**
	 * Ein einzelner Teilparagraph wird ausgelesen.
	 * @param i ist der Index des gewünschten Teilparagraphen mit n=Anzahl der Teilparagraphen
	 * @return der gewünschte Teilparagraph
	 */
	
	public EinfacherParagraph getParagraphAt(int i) {
		return this.teilParagraph.elementAt(i);
	}
	
	/**
	 * Umwandeln des VerbundenerParagraph in einen String 
	 */
	
	public String toString() {
		/**
		 * Es wird ein leerer String angelegt, der die Strings in Unterabschnitte einträgt.
		 */
		StringBuffer result = new StringBuffer();
		
		// Schleife über alle Unterabschnitte
		
		for (int i = 0; i < this.teilParagraph.size(); i++) {
			EinfacherParagraph p = this.teilParagraph.elementAt(i);
			
			/** 
			 * Die Unterabschnitte in einen Sting umwandeln und an den Buffer anhängen
			 */
			
			result.append(p.toString()+"/n");
		}
		
		/**
		 * Buffer wird in String umgewandelt und zurückgegeben.
		 */
		
		return result.toString();
	}
}
