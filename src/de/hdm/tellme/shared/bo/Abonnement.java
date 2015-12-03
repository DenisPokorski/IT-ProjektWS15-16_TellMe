package de.hdm.tellme.shared.bo; 

public abstract class Abonnement extends BusinessObject {

	/**
	 * ToDo
	 */
	private static final long serialVersionUID = 1L;
	
	protected Nutzer AbonnementErsteller;

	public Nutzer getAbonnementErsteller() {
		return AbonnementErsteller;
	}

	public void setAbonnementErsteller(Nutzer abonnementErsteller) {
		AbonnementErsteller = abonnementErsteller;
	}
	
}
