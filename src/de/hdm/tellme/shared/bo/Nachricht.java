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

	private Vector<Hashtag> verknuepfteHashtags = new Vector<Hashtag>();

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
		// Textbegrenzung auf 255 Zeichen
		if (text.length() > 255)
			text = text.substring(0, 254);

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
	 * Mit dieser Methode wird der Sender ausgelesen
	 * 
	 * @return sender
	 */

	public Nutzer getSender() {
		return sender;
	}

	/**
	 * 
	 * Mit dieser Methode wird der Nutzer dem Sender zugewiesen und die nutzerId
	 * der senderId zugewiesen
	 * 
	 * @param senderId
	 */

	public void setSender(Nutzer nutzer) {
		this.sender = nutzer;
		this.senderId = nutzer.getId();
	}

	/**
	 * Mit dieser Methode werden die verknüpften Hashtags ausgelesen
	 * 
	 * @return verknuepfteHashtags
	 */

	public Vector<Hashtag> getVerknuepfteHashtags() {
		return verknuepfteHashtags;
	}

	/**
	 * Mit dieser Methode werden die verknüpften Hashtags zugewiesen
	 * 
	 * @param verknuepfteHashtags
	 */

	public void setVerknuepfteHashtags(Vector<Hashtag> verknuepfteHashtags) {
		this.verknuepfteHashtags = verknuepfteHashtags;
	}

}
