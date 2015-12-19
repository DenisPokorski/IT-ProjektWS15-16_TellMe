package de.hdm.tellme.shared.bo;

/**
 * Die Klasse HashtagAbonnement erbt von der Klasse Abonnement. Es werden die
 * get- und set-Methoden für Hashtag erstellt
 * 
 * @author Nicole Reum
 *
 */

public class HashtagAbonnement extends Abonnement {

	/**
	 * Die ID ist nötig, damit die Klasse serialisierbar ist
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Die Variable Hashtag ist vom Typ Hashtag
	 */

	private Hashtag Hashtag;

	/**
	 * Mit dieser Methode wird der Hashtag ausgelesen
	 * 
	 * @return Hashtag
	 */

	public Hashtag getHashtag() {
		return Hashtag;
	}

	/**
	 * Mit dieser Methode wird der Hashtag zugewiesen
	 * 
	 * @return Hashtag
	 */

	public void setHashtag(Hashtag hashtag) {
		Hashtag = hashtag;
	}

}
