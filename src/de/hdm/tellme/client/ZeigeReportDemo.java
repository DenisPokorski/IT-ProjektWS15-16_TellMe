package de.hdm.tellme.client;
/**
 * Dieser Showcase soll den Report für einen Nutzer mit der NutzerId 7 ausgeben
 * @author Alex
 *
 */
public class ZeigeReportDemo extends Showcase {
	
	/**
	 * Jeder Showcase hat eine Überschrift, die durch eine Methode erstellt werden soll
	 * 
	 */
	@Override
	protected String getUeberschriftText(){
		return "Zeige Report";
	}
	/**
	 * Ein Showcase muss die Methode run() implementieren. Mit ihr wird der Showcase
	 * aufgerufen.
	 */
	protected void start() {
		// Jetzt wird gezeigt, was geschieht.
		this.append("Nutzer mit NutzerId 3 auslesen. ");
		//Editorservice wird abgefragt.
	//	EditorServiceAsync editorService = ClientsideSettings.getEditor();
		
	}
	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
	}

}
