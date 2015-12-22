package de.hdm.tellme.shared.bo;

import java.util.HashMap;
import java.util.Vector;

public class Unterhaltung extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Nachricht> alleNachrichten;

	private HashMap<Boolean, Nutzer> teilnehmer;

	public enum UnterhaltungsTyp {
		privat, oeffentlich;
	}

	public Vector<Nachricht> getAlleNachrichten() {
		return alleNachrichten;
	}

	public void setAlleNachrichten(Vector<Nachricht> alleNachrichten) {
		this.alleNachrichten = alleNachrichten;
	}

	public HashMap<Boolean, Nutzer> getTeilnehmer() {
		return teilnehmer;
	}

	public void setTeilnehmer(HashMap<Boolean, Nutzer> teilnehmer) {
		this.teilnehmer = teilnehmer;
	}

}
