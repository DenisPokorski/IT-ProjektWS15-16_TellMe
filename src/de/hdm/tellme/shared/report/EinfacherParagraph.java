package de.hdm.tellme.shared.report;

import java.io.Serializable;

/**
 * In der Klasse werden einzelne Absätze dargestellt. Die Inhalte werden 
 * als String eingefügt. 
 * @author Nicole Reum
 *
 */

public class EinfacherParagraph extends Paragraph implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Definiert den Inhalt eines Absatzes.
	 */
	
	private String text = "";
	
	/**
	 * TODO
	 */
	
	public EinfacherParagraph(){
		
	}
	
	/**
	 * Durch den Konstruktur wird ermöglicht, dass bei der Instantiierung 
	 * von EinfacherParagraph-Objekten der Inhalt angegeben werden kann.
	 * @param wert ist der Inhalt des Absatzes
	 */
	
	public EinfacherParagraph(String wert) {
		this.text = wert;
		
	}
	
	/**
	 * Der Inhalt wird ausgelesen
	 * @return der Inhalt wird als Sting zurückgegeben
	 */
	
	public String getText() {
		return this.text;
	}
	
	/** 
	 * Der Inhalt wird überschrieben.
	 * @param text ist der neue Inhalt des Absatzes.
	 */
	
	public void setText (String text) {
		this.text = text;
	}
	
	/**
	 * Das Objekt EinfacherParagraph wird in einen String umgewandelt
	 */
	
	public String toString() {
		return this.text;
	}
	

}
