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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Nachricht> alleNachrichten = new Vector<Nachricht>();
	private Vector<Nutzer> teilnehmer = new Vector<Nutzer>();
	private eUnterhaltungsTyp Unterhaltungstyp;
	private String anzeigeHerkunft = "";

	/**
	 * Durch die Methode <code>getAnzeigeHerkunft</code> wird gezeigt, warum die
	 * Unterhaltung angezeigt wird, dies kann auf ein Nutzerabonnement, ein
	 * Hashtagabonnement oder auf die Teilnahme an der Unterhaltung beruhen.
	 * 
	 * @return String, der den Grund anzeigt, warum die Unterhaltung bei den
	 *         Neuigkeiten des Nutzer angezeigt werden.
	 */
	public String getAnzeigeHerkunft() {
		return anzeigeHerkunft;
	}

	/**
	 * Der Grund für das Erscheinen der Unterhaltung in den Neuigkeiten wird
	 * durch die Methode <code>setAnzeigeHerkunft</code> gesetzt.
	 * 
	 * @param anzeigeHerkunft
	 */
	public void setAnzeigeHerkunft(String anzeigeHerkunft) {
		this.anzeigeHerkunft = anzeigeHerkunft;
	}

	/**
	 * Es gibt verschiedene Modis für die Unterhaltungen, einerseits gibt es
	 * private, andererseits öffentliche Nachrichten.
	 */
	public enum eUnterhaltungsTyp {
		privat, oeffentlich;
	}

	/**
	 * Die Methode <code>getAlleNachrichten</code> gibt alle Nachrichten in
	 * einem Vektor zurück.
	 * 
	 * @return Vektor, der mit allen Nachricht-Objekten gefüllt ist.
	 */
	public Vector<Nachricht> getAlleNachrichten() {
		return alleNachrichten;
	}

	/**
	 * Alle Nachrichten setzen.
	 * 
	 * @param alleNachrichten
	 */
	public void setAlleNachrichten(Vector<Nachricht> alleNachrichten) {
		this.alleNachrichten = alleNachrichten;
	}

	/**
	 * Die Methode <code>getTeilnehmer</code> gibt die Teilnehmer einer
	 * Unterhaltung in einem Vektor zurück.
	 * 
	 * @return Vektor mit Nutzer-Objekten, die an einer Unterhaltung teilnehmen.
	 */
	public Vector<Nutzer> getTeilnehmer() {
		return teilnehmer;
	}

	/**
	 * Die Teilnehmer vom Typ Nutzer, werden durch die Methode
	 * <code>setTeilnehmer</code> gesetzt.
	 * 
	 * @param teilnehmer
	 */
	public void setTeilnehmer(Vector<Nutzer> teilnehmer) {
		this.teilnehmer = teilnehmer;
	}

	/**
	 * Die Methode <code>getUnterhaltungstyp</code> gibt den Typ (
	 * <code>eUnterhaltungsTyp</code>) der Unterhaltung zurück, der entweder
	 * öffentlich oder privat sein kann.
	 * 
	 * @return Typ der Unterhaltung vom Typ <code>eUnterhaltungsTyp</code>.
	 */
	public eUnterhaltungsTyp getUnterhaltungstyp() {
		return Unterhaltungstyp;
	}

	/**
	 * Der Typ der Unterhaltung wird gesetzt.
	 * 
	 * @param unterhaltungstyp
	 *            , es kann eine private oder eine öffentliche Unterhaltung sein
	 */
	public void setUnterhaltungstyp(eUnterhaltungsTyp unterhaltungstyp) {
		Unterhaltungstyp = unterhaltungstyp;
	}

	/**
	 * Die Methode <code>getVergleichsDatum</code> gibt das Erstellungsdatum
	 * oder das Datum der letzten Nachricht zurück.
	 * 
	 * @return Timestamp, mit dem Datum der letzen Nachricht oder dem
	 *         Erstellungsdatum.
	 */
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

	/**
	 * Mit der Methode <code>compareTo()</code> werden die Unterhaltungen
	 * zeitlich in eine Reihenfolge gebracht
	 */
	@Override
	public int compareTo(Unterhaltung arg0) {

		if (arg0.getVergleichsDatum() == null|| this.getVergleichsDatum() == null)
			return 0;
		if (arg0.getVergleichsDatum().before(this.getVergleichsDatum()))
			return -1;
		else
			return 1;
	}
}