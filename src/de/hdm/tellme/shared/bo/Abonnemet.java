package de.hdm.tellme.shared.bo;

public abstract class Abonnemet {

	protected Nutzer AbonnementErsteller;

	public Nutzer getAbonnementErsteller() {
		return AbonnementErsteller;
	}

	public void setAbonnementErsteller(Nutzer abonnementErsteller) {
		AbonnementErsteller = abonnementErsteller;
	}
	
}
