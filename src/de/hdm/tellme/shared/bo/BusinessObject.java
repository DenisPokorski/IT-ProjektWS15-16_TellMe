package de.hdm.tellme.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BusinessObject implements Serializable {

	/**
	 * Eindeutige Identifikationsnummer
	 */
	private static final long serialVersionUID = 1L;

	private int id = 0;
	private Boolean sichtbarkeit;
	private Timestamp erstellungsDatum;

	/**
	 * Auslesen der ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setzen der ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Auslesen der Sichtbarkeit
	 */
	public Boolean getSichtbarkeit() {
		return sichtbarkeit;
	}

	/**
	 * Setzen der Sichtbarkeit
	 */
	public void setSichtbarkeit(Boolean sichtbarkeit) {
		this.sichtbarkeit = sichtbarkeit;
	}

	/**
	 * Auslesen des ErstellungsDatums
	 */
	public Timestamp getErstellungsDatum() {
		return erstellungsDatum;
	}

	/**
	 * Setzen des ErstellungsDatums
	 */
	public void setErstellungsDatum(Timestamp erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	/**
	 * String-Objekt erstellen mittels ID
	 */
	/**public String toString() {
		return '#' + this.id;
	}
	*/

}
