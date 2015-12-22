package de.hdm.tellme.shared.bo;

import java.util.Vector;

public class Unterhaltung extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Nachricht> alleNachrichten;
	private Vector<Nutzer> teilnehmer;

	public enum UnterhaltungsTyp {
		privat, oeffentlich;
	}

	public Vector<Nachricht> getAlleNachrichten() {
		return alleNachrichten;
	}

	public void setAlleNachrichten(Vector<Nachricht> alleNachrichten) {
		this.alleNachrichten = alleNachrichten;
	}

	public Vector<Nutzer> getTeilnehmer() {
		return teilnehmer;
	}

	public void setTeilnehmer(Vector<Nutzer> teilnehmer) {
		this.teilnehmer = teilnehmer;
	}

}
