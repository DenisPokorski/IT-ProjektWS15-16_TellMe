package de.hdm.tellme.shared.bo;

public class Nutzer extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Nachname;
	private String Vorname;
	private String GoogleId;
	private String Mailadresse;

	public String getNachname() {
		return Nachname;
	}

	public void setNachname(String nachname) {
		Nachname = nachname;
	}

	public String getVorname() {
		return Vorname;
	}

	public void setVorname(String vorname) {
		Vorname = vorname;
	}

	public String getMailadresse() {
		return Mailadresse;
	}

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
