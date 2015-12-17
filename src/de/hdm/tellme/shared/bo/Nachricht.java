package de.hdm.tellme.shared.bo;

import java.sql.Timestamp;
import java.util.Vector;

public class Nachricht extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;
	private int senderId;
	private String senderVorname; 
	private String senderNachname; 

 	private Vector<Nutzer> empfaengerListe;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int sender) {
		this.senderId = sender;
	}


	public Vector<Nutzer> getEmpfaengerListe() {
		return empfaengerListe;
	}

	public void setEmpfaengerListe(Vector<Nutzer> empfaengerListe) {
		this.empfaengerListe = empfaengerListe;
	}

	public String getSenderVorname() {
		return senderVorname;
	}

	public void setSenderVorname(String senderVorname) {
		this.senderVorname = senderVorname;
	}

	public String getSenderNachname() {
		return senderNachname;
	}

	public void setSenderNachname(String senderNachname) {
		this.senderNachname = senderNachname;
	}

}
