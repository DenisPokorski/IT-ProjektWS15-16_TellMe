package de.hdm.tellme.shared.bo;

public class NutzerAbonnement extends Abonnement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Nutzer aboNutzer;

	public Nutzer getAboNutzer() {
		return aboNutzer;
	}

	public void setAboNutzer(Nutzer aboNutzer) {
		this.aboNutzer = aboNutzer;
	}

}
