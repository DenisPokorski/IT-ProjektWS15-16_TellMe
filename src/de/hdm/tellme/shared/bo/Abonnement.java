package de.hdm.tellme.shared.bo;

/**
 * Die Klasse Abonnement erbt von der Superklasse BusinessObject. Es werden die
 * get-und set-Methoden für AbonnementErsteller erstellt.
 * 
 * @author Nicole Reum
 *
 */

public abstract class Abonnement extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Die Variable AbonnementErsteller ist vom Typ Nutzer
	 */

	protected Nutzer AbonnementErsteller;

	/**
	 * Mit dieser Methode wird der AbonnementErsteller ausgelesen
	 * 
	 * @return AbonnementErsteller
	 */

	public Nutzer getAbonnementErsteller() {
		return AbonnementErsteller;
	}

	/**
	 * Setzen des abonnementErsteller
	 * 
	 * @param abonnementErsteller
	 */

	public void setAbonnementErsteller(Nutzer abonnementErsteller) {
		AbonnementErsteller = abonnementErsteller;
	}

}
