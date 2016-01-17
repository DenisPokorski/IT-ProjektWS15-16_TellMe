package de.hdm.tellme.client.gui.editor;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.tellme.shared.bo.Nutzer;
/**
 * Die Hilfs-Klasse <class>NutzerZelle</class> wird benötigt um ein
 * ZellenElement der <class>NutzerCellList</class> auszuwählen. Hier befinden
 * sich auch die Nested-Classes <class>ZellenObjekt</class>
 * <class>ZellenElement</class>. Durch die Klasse
 * <class>ZellenObjekt</class>kann festgestellt werden ob ein Nutzer abonniert
 * ist.
 * 
 * @author denispokorski
 *
 */
public class NutzerZelle  {
	
	/**
	 * Dies ist die Nested-Class <class>ZellenObjekt</class>. Ihre Funktion
	 * wurde bereits für die Klasse <class>NutzerZelle</class> beschrieben.
	 * 
	 * @author denispokorski
	 *
	 */
	public class ZellenObjekt {
		public Nutzer nutzer;
		public boolean aboniert;
	}
	/**
	 * Die Klasse <class>ZellenElement</class> ist eine Klasse die durch die
	 * Klasse <class>Abstract Cell</class> erweitert wird. Sie hat die Aufgabe
	 * die ZellenElemente darzustellen. Außerdem dient sie zur Erzeugung von
	 * HTML-Code für die benutzerdefinierten Objekte. Daraufhin wird das Nutzer-Objekt
	 * in eine HTML-Tabelle eingefügt. 
	 * 
	 * 
	 * @author denispokorski
	 *
	 */
	public class ZellenElement extends AbstractCell<NutzerZelle.ZellenObjekt> {
		@Override
		public void render(Context context, NutzerZelle.ZellenObjekt value, SafeHtmlBuilder sb) {
			// Value can be null, so do a null check..
			if (value == null) {
				return;
			}

			sb.appendHtmlConstant("<table><tr><td>");
			sb.appendEscaped(value.nutzer.getVorname() + " " + value.nutzer.getNachname());
			sb.appendHtmlConstant("</td></tr></table>");
		}
	}

	

}
