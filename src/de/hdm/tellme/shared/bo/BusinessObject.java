package de.hdm.tellme.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Die Klasse BusinessObject ist die Superklasse für alle anderen Businnes
 * Objekte. Sie vererbt die ID, Sichtbarkeit und ErstellungsDatum sowie die get-
 * und set-Methoden
 * 
 * @author Nicole Reum
 *
 */

public class BusinessObject implements Serializable {

	public enum eSichtbarkeit {
		Unsichtbar, Sichtbar;
	}

	/**
	 * Die ID ist nötig, damit die Klasse serialisierbar ist
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Bei der ID handelt es sich um eine Integer-Variable Bei der Sichtbarkeit
	 * handelt es sich um eine Integer-Variable Bei erstellungsDatum handelt es
	 * sich um die Veriable vom Typ Timestamp
	 */

	private int id = 0;
	private int sichtbarkeit;
	private Timestamp erstellungsDatum;

	/**
	 * Mit dieser Methode wird die Id ausgelesen
	 * 
	 * @return Id
	 */

	public int getId() {
		return id;
	}

	/**
	 * Die ID bekommt die ID aus der Datenbank zugewiesen
	 * 
	 * @param id
	 *            die neue ID
	 */

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Mit dieser Methode wird die sichtbarkeit ausgelesen
	 * 
	 * @return sichtbarkeit
	 */

	public int getSichtbarkeit() {
		return sichtbarkeit;
	}

	/**
	 * Die sichtbarkeit bekommt die sichtbarkeit aus der Datenbank zugewiesen
	 * 
	 * @param sichtbarkeit
	 */

	public void setSichtbarkeit(int sichtbarkeit) {
		this.sichtbarkeit = sichtbarkeit;
	}

	/**
	 * Mit dieser Methode wird die erstellungsDatum ausgelesen
	 * 
	 * @return erstellungsDatum
	 */

	public Timestamp getErstellungsDatum() {
		return erstellungsDatum;
	}

	/**
	 * Das erstellungsDatum bekommt das erstellungsDatum aus der Datenbank
	 * zugewiesen
	 * 
	 * @param erstellungsDatum
	 */
	public void setErstellungsDatum(Timestamp erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	/**
	 * String-Objekt erstellen mittels ID
	 */
	/**
	 * public String toString() { return '#' + this.id; }
	 */

}
