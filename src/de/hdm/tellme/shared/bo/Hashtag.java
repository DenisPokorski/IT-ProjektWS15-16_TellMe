package de.hdm.tellme.shared.bo;

import java.sql.Timestamp;

public class Hashtag extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hashtagId;
	private String schlagwort;
	private Timestamp erstellungsDatum;
	
	
	public int getHashtagId() {
		return hashtagId;
	}
	public void setHashtagId(int hashtagId) {
		this.hashtagId = hashtagId;
	}
	public String getSchlagwort() {
		return schlagwort;
	}
	public void setSchlagwort(String schlagwort) {
		this.schlagwort = schlagwort;
	}
	public Timestamp getErstellungsDatum() {
		return erstellungsDatum;
	}
	public void setErstellungsDatum(Timestamp erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}
	
	

}
