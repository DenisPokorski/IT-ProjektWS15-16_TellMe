package de.hdm.tellme.shared.bo;

import java.sql.Timestamp;
import java.util.Vector;

/**
 * Die Klasse Nutzer erbt von der Superklasse BusinessObject. Es werden die get-
 * und set-Methoden für AlleNachrichten, Teilnehmer und Unterhaltungstyp
 * erstellt. Die Methode VergleichsDatum gibt das letzte Datum und die
 * compareTo()-Methode vergleicht das Datum der Unterhaltungen
 * 
 * @author denispokorski
 *
 */
public class Unterhaltung extends BusinessObject implements
		Comparable<Unterhaltung> {

	private static final long serialVersionUID = 1L;
	private Vector<Nachricht> alleNachrichten = new Vector<Nachricht>();

	private Vector<Nutzer> teilnehmer = new Vector<Nutzer>();

	private eUnterhaltungsTyp Unterhaltungstyp;
	
	private String anzeigeHerkunft = "";

	public String getAnzeigeHerkunft() {
		return anzeigeHerkunft;
	}

	public void setAnzeigeHerkunft(String anzeigeHerkunft) {
		this.anzeigeHerkunft = anzeigeHerkunft;
	}

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
					rueckgabeWert = this.getAlleNachrichten().get(0)
							.getErstellungsDatum();
				}
			}
		}

		return rueckgabeWert;
	}

	/**
	 * Mit der Methode <code>compareTo()</code> werden die Unterhaltungen
	 * zeitlich in eine Reihenfolge gebracht
	 */
	@Override
	public int compareTo(Unterhaltung arg0) {

		if (arg0.getVergleichsDatum() == null
				|| this.getVergleichsDatum() == null)
			return 0;
		if (arg0.getVergleichsDatum().before(this.getVergleichsDatum()))
			return -1;
		else
			return 1;
	}

}