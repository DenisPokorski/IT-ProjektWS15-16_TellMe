package de.hdm.tellme.shared.bo;

import java.sql.Timestamp;
import java.util.Vector;

public class Nachricht extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;
	private Nutzer sender;
	private Vector<Hashtag> hashtagListe;
	private Vector<Nutzer> empfaengerListe;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Nutzer getSender() {
		return sender;
	}

	public void setSender(Nutzer sender) {
		this.sender = sender;
	}

	public Vector<Hashtag> getHashtagListe() {
		return hashtagListe;
	}

	public void setHashtagListe(Vector<Hashtag> hashtagListe) {
		this.hashtagListe = hashtagListe;
	}

	public Vector<Nutzer> getEmpfaengerListe() {
		return empfaengerListe;
	}

	public void setEmpfaengerListe(Vector<Nutzer> empfaengerListe) {
		this.empfaengerListe = empfaengerListe;
	}

}
