package de.hdm.tellme.shared.bo;

public class Nutzer extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Nachname;
	private int Vorname;
	private String GoogleId;
	private String Mailadresse;

	public int getNachname() {
		return Nachname;
	}

	public void setNachname(int nachname) {
		Nachname = nachname;
	}

	public int getVorname() {
		return Vorname;
	}

	public void setVorname(int vorname) {
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
