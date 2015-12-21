package de.hdm.tellme.shared.bo;

/**
 * Die Klasse Nutzer erbt von der Superklasse BusinessObject. Es werden die get-
 * und set-Methoden für Nachname, Vorname und Mailadresse erstellt.
 * 
 * @author Nicole Reum
 *
 */

public class Nutzer extends BusinessObject {

	/**
	 * Die ID ist nötig, damit die Klasse serialisierbar ist
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Die Variable Nachname ist vom Typ String
	 * Die Variable Vorname ist vom Typ String
	 * Die Variable Mailadresse ist vom Typ String
	 * Die Variable GoogleId ist vom Typ Id
	 */
	
	private String Nachname;
	private String Vorname;
	private String Mailadresse;
	private String GoogleId;
	
	/**
	 * Mit dieser Methode wird der nachname ausgelesen
	 * @return Nachname
	 */

	public String getNachname() {
		return Nachname;
	}
	
	/**
	 * Mit dieser Methode wird der nachname zugewiesen
	 * @param nachname
	 */

	public void setNachname(String nachname) {
		Nachname = nachname;
	}
	
	/**
	 * Mit dieser Methode wird der vorname ausgelesen
	 * @return Vorname
	 */

	public String getVorname() {
		return Vorname;
	}
	
	/**
	 * Mit dieser Methode wird der vorname zugewiesen
	 * @param vorname
	 */

	public void setVorname(String vorname) {
		Vorname = vorname;
	}
	
	/**
	 * Mit dieser Methode wird die mailadresse ausgelesen
	 * @return mailadresse
	 */

	public String getMailadresse() {
		return Mailadresse;
	}
	
	/**
	 * Mit dieser Methode wird die mailadresse zugewiesen
	 * @param mailadresse
	 */

	public void setMailadresse(String mailadresse) {
		Mailadresse = mailadresse;
	}

	public String getGoogleId() {
		return GoogleId;
	}

	public void setGoogleId(String googleId) {
		GoogleId = googleId;
	}

}
