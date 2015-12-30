package de.hdm.tellme.shared.bo;

import java.sql.Timestamp;
import java.util.Vector;

public class Unterhaltung extends BusinessObject implements Comparable<Unterhaltung> {

	/**
  * 
  */
	private static final long serialVersionUID = 1L;
	private Vector<Nachricht> alleNachrichten = new Vector<Nachricht>();

	private Vector<Nutzer> teilnehmer = new Vector<Nutzer>();

	private eUnterhaltungsTyp Unterhaltungstyp;

	public enum eUnterhaltungsTyp {
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

	public eUnterhaltungsTyp getUnterhaltungstyp() {
		return Unterhaltungstyp;
	}

	public void setUnterhaltungstyp(eUnterhaltungsTyp unterhaltungstyp) {
		Unterhaltungstyp = unterhaltungstyp;
	}

	public Timestamp getVergleichsDatum() {
		// gibt Erstellungsdatum oder Datum der letzten Nachricht zurück (wenn 1
		// oder mehrere Nachrichten vorhanden)
		Timestamp rueckgabeWert;

		rueckgabeWert = this.getErstellungsDatum();

		if (this.getAlleNachrichten() != null) {
			if (this.getAlleNachrichten().size() > 0) {
				if (this.getAlleNachrichten().get(0) != null) {
					rueckgabeWert = this.getAlleNachrichten().get(0).getErstellungsDatum();
				}
			}
		}

		return rueckgabeWert;
	}

	@Override
	public int compareTo(Unterhaltung arg0) {
		if (arg0.getVergleichsDatum() == null || this.getVergleichsDatum() == null)
			return 0;
		if (arg0.getVergleichsDatum().before(this.getVergleichsDatum()))
			return -1;
		else
			return 1;
	}

}