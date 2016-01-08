package de.hdm.tellme.client.gui.editor;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.tellme.shared.bo.Hashtag;

/**
 * Die Hilfs-Klasse <class>HashtagZelle</class> wird benötigt um ein
 * ZellenElement der <class>HashtagCellList</class> auszuwählen. Hier befinden
 * sich auch die Nested-Classes <class>ZellenObjekt</class>
 * <class>ZellenElement</class>. Durch die Klasse
 * <class>ZellenObjekt</class>kann festgestellt werden ob ein Hashtag abonniert
 * ist.
 * 
 * @author Alex
 *
 */
public class HashtagZelle {
	/**
	 * Dies ist die Nested-Class <class>ZellenObjekt</class>. Ihre Funktion
	 * wurde bereits für die Klasse <class>HashtagZelle</class> beschrieben.
	 * 
	 * @author Alex
	 *
	 */
	public class ZellenObjekt {
		public Hashtag hashtag;
		public boolean aboniert;
	}

	/**
	 * Die Klasse <class>ZellenElement</class> ist eine Klasse die durch die
	 * Klasse <class>Abstract Cell</class> erweitert wird. Sie hat die Aufgabe
	 * die ZellenElemente darzustellen. Außerdem dient sie zur Erzeugung von
	 * HTML-Code für die benutzerdefinierten Objekte. Daraufhin wird das Hashtag
	 * in eine HTML-Tabelle eingefügt. 
	 * 
	 * 
	 * @author Alex
	 *
	 */
	public class ZellenElement extends AbstractCell<HashtagZelle.ZellenObjekt> {
		@Override
		public void render(Context context, HashtagZelle.ZellenObjekt value,
				SafeHtmlBuilder sb) {
			// Value can be null, so do a null check..
			if (value == null) {
				return;
			}

			sb.appendHtmlConstant("<table><tr><td>");
			sb.appendEscaped("# " + value.hashtag.getSchlagwort());
			sb.appendHtmlConstant("</td></tr></table>");
		}
	}

}
