package de.hdm.tellme.shared.bo;

import java.util.Vector;

public class Unterhaltung {
	
	private Vector <Nutzer> teilnehmerListe;
	private Vector <Nachricht> nachrichtListe;
	private Nutzer initiator;
	
	public Vector<Nutzer> getTeilnehmerListe() {
		return teilnehmerListe;
	}
	public void setTeilnehmerListe(Vector<Nutzer> teilnehmerListe) {
		this.teilnehmerListe = teilnehmerListe;
	}
	public Vector<Nachricht> getNachrichtListe() {
		return nachrichtListe;
	}
	public void setNachrichtListe(Vector<Nachricht> nachrichtListe) {
		this.nachrichtListe = nachrichtListe;
	}
	public Nutzer getInitiator() {
		return initiator;
	}
	public void setInitiator(Nutzer initiator) {
		this.initiator = initiator;
	}

}