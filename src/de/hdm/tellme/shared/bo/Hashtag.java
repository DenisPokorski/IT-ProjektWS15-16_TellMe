package de.hdm.tellme.shared.bo;

/**
 * Die Klasse Hashtag erbt von der Superklasse BusinessObject. Es werden die
 * get-und set-Methoden für HashtagId, Schlagwort erstellt.
 * 
 * @author Nicole Reum
 *
 */

public class Hashtag extends BusinessObject {

	/**
	 * Die ID ist nötig, damit die Klasse serialisierbar ist
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Die Variable hashtagId ist vom Typ Integer 
	 * Die Variable Schlagwort ist vom Typ String
	 */

	private String schlagwort;


	/**
	 * Mit dieser Methode wird das Schlagwort ausgelesen
	 * 
	 * @return Schlagwort
	 */

	public String getSchlagwort() {
		return schlagwort;
	}

	/**
	 * Mit dieser Methode wird das Schlagwort gesetzt.
	 * 
	 * @param schlagwort
	 */

	public void setSchlagwort(String schlagwort) {
		this.schlagwort = schlagwort;
	}

}
