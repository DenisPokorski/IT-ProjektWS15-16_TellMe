package de.hdm.tellme.shared.bo;

/**
 * Die Klasse NutzerAbonnement erbt von der Klasse Abonnement. Es werden die
 * get- und set- Methoden für AboNutzer erstellt
 * 
 * @author Nicole
 *
 */

public class NutzerAbonnement extends Abonnement {

	/**
	 * Die ID ist nötig, damit die Klasse serialisierbar ist
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Die Variable aboNutzer ist vom Typ Nutzer
	 */

	private Nutzer aboNutzer;

	/**
	 * Mit dieser Methode wird der aboNutzer ausgelesen
	 * 
	 * @return aboNutzer
	 */

	public Nutzer getAboNutzer() {
		return aboNutzer;
	}

	/**
	 * Mit dieser Methode wird der aboNutzer zugewiesen
	 * 
	 * @param aboNutzer
	 */

	public void setAboNutzer(Nutzer aboNutzer) {
		this.aboNutzer = aboNutzer;
	}

}
