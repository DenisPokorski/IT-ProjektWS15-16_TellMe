package de.hdm.tellme.shared.bo;

import java.util.Vector;

/**
 * Die Klasse Nachricht erbt von der Superklasse BusinessObject. Es werden die
 * get- und set- Methoden für Text, SenderId, SenderVorname, SenderNachname
 * erstellt
 * 
 * @author Nicole Reum
 *
 */

public class Nachricht extends BusinessObject {

	/**
	 * Die ID ist nötig, damit die Klasse serialisierbar ist
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Die Variable text ist vom Typ String Die Variable senderId ist vom Typ
	 * Integer Die Variable senderVorname ist vom Typ String Die Variable
	 * senderNachname ist vom Typ String Die Variable empfaengerListe ist vom
	 * Typ Vector<Nutzer>
	 */

	private String text;
	private Nutzer sender;
	private int senderId;
	private Vector<Nutzer> empfaengerListe;

	/**
	 * Mit dieser Methode wird der Text ausgelesen
	 * 
	 * @return text
	 */

	public String getText() {
		return text;
	}

	/**
	 * Mit dieser Methode wird der Text zugewiesen
	 * 
	 * @param text
	 */

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Mit dieser Methode wird die senderId ausgelesen
	 * 
	 * @return senderId
	 */

	public int getSenderId() {
		return senderId;
	}

	/**
	 * Mit dieser Methode wird die senderId zugewiesen
	 * 
	 * @param senderId
	 */

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	/**
	 * Mit dieser Methode wird die empfaengerListe ausgelesen
	 * 
	 * @return empfaengerListe
	 */

	public Vector<Nutzer> getEmpfaengerListe() {
		return empfaengerListe;
	}

	/**
	 * Mit dieser Methode wird die empfaengerListe zugewiesen
	 * 
	 * @param empfaengerListe
	 */

	public void setEmpfaengerListe(Vector<Nutzer> empfaengerListe) {
		this.empfaengerListe = empfaengerListe;
	}

	/**
	 * Mit dieser Methode wird der senderVorname ausgelesen
	 * 
	 * @return senderVorname
	 */

	/**
	 * Mit dieser Methode wird der senderVorname zugewiesen
	 * 
	 * @param senderVorname
	 */

	/**
	 * Mit dieser Methode wird der senderNachname zugewiesen
	 * 
	 * @param senderNachname
	 */

	public Nutzer getSender() {
		return sender;
	}

	public void setSender(Nutzer nutzer) {
		this.sender = nutzer;
	}

}
