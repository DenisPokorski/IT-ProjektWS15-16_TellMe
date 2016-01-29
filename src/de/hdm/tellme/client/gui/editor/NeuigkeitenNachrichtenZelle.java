package de.hdm.tellme.client.gui.editor;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;
import de.hdm.tellme.shared.bo.Unterhaltung.eUnterhaltungsTyp;

/**
 * 
 * Die Klasse <class> NeuigkeitenNachrichtenZelle </class> ist dafür
 * verantwortlich, die einzelnen Elemente der Nachricht in einer Zelle
 * zusammenzuführen.
 * 
 * @author denispokorski
 *
 */
public class NeuigkeitenNachrichtenZelle extends AbstractCell<Nachricht> {
	DateTimeFormat dF = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	Unterhaltung u = null;

	/**
	 * Der Konstruktor <code> NeuigkeitenNachrichtenZelle </code> übergibt das
	 * Unterhaltung-Objekt dem in der Klasse erstellten Unterhaltungsobjekt.
	 * 
	 * @param _u
	 */
	public NeuigkeitenNachrichtenZelle(Unterhaltung _u) {
		u = _u;

	}

	/**
	 * Die Methode <code>render()</code> fügt ein Objekt zusammen zu einem plain
	 * text.
	 * 
	 * @param context
	 *            , der Context der NachrichtenZelle
	 * @param value
	 *            , die eigentliche Nachricht
	 * @param sb
	 *            , der SafeHtmlBuilder, der die benötigten Elemente
	 *            zusammenfügt.
	 * 
	 */
	public void render(Context context, Nachricht value, SafeHtmlBuilder sb) {
		// Value can be null, so do a null check..
		if (value == null) {
			return;
		}
		/**
		 * Strings für die benötigten Elemente der NeuigkeitenNachrichtenZelle.
		 */
		String strUnterhaltungsTyp = "";
		String strDatum = "";
		String strAbsender = "";
		String strText = "";
		String strHashtags = "";
		String strTeilnehmer = "";

		/**
		 * Unterhaltungstyp wird beim <code>eUnterhaltungsTyp.oeffentlich</code>
		 * als öffentlich und anderenfalls als privat deklariert.
		 */
		if (u.getUnterhaltungstyp() == eUnterhaltungsTyp.oeffentlich) {
			strUnterhaltungsTyp = "öffentlich";
		} else {
			strUnterhaltungsTyp = "privat";
		}
		/**
		 * Das Datum der Nachricht wird gesetzt.
		 */
		strDatum = dF.format(value.getErstellungsDatum()).toString();
		/**
		 * Der Absender wird als Autor hinzugefügt.
		 */
		strAbsender = value.getSender().getVorname() + " "+ value.getSender().getNachname();
		/**
		 * Der Text wird aus der Nachricht ausgelesen.
		 */
		strText = value.getText();
		/**
		 * Wenn Hashtags verknüpft sind, wird das Hashtag hinzugefügt,
		 * anderenfals wir der Text: "keine Hashtags vorhanden" hinzugefügt.
		 */
		if (value.getVerknuepfteHashtags() != null) {
			if (value.getVerknuepfteHashtags().size() != 0) {
				for (Hashtag hashtag : value.getVerknuepfteHashtags()) {
					strHashtags += "#" + hashtag.getSchlagwort() + " ";
				}
			} else
				strHashtags += "keine Hashtags vorhanden";
		}
		/**
		 * Die Teilnehmer werden hinzugefügt, falls keine Teilnehmer vorhanden
		 * sind wird der Text "keine Teilnehmer" ausgegeben.
		 */
		if (u.getTeilnehmer() != null) {
			if (u.getTeilnehmer().size() != 0) {
				for (Nutzer teilnehmer : u.getTeilnehmer()) {
					strTeilnehmer += teilnehmer.getVorname() + " "+ teilnehmer.getNachname() + ", ";
				}
				strTeilnehmer = strTeilnehmer.substring(0,
						strTeilnehmer.length() - 2);
			} else
				strTeilnehmer += "keine Teilnehmer";
		}

		/**
		 * Dem SafeHtmlBuilder werden die HTML-Daten angehängt.
		 * 
		 */
		sb.appendHtmlConstant("<div class='neuigkeitenNachrichtInhalt'>");
		sb.appendHtmlConstant("<table style='width:100%'><tr><td class='nachrichten_linie_unten'>");

		sb.appendHtmlConstant(strUnterhaltungsTyp);

		sb.appendHtmlConstant("</td><td class='nachrichten_linie_unten'>");

		sb.appendEscaped("Absender: " + strAbsender);
		sb.appendHtmlConstant("</td><td class='nachrichten_linie_unten'>");
		sb.appendHtmlConstant(strDatum);
		sb.appendHtmlConstant("</td></tr><tr>  <td  class='NachrichtTextMitAbstand'  colspan='3'>");
		sb.appendHtmlConstant(strText.replace("\n", "<br>"));
		sb.appendHtmlConstant("</td>");
		sb.appendHtmlConstant("</tr><tr><td colspan='2' class='nachrichten_linie_oben'>" + "Teilnehmer: "
				+ strTeilnehmer + "</td><td class='nachrichten_linie_oben'>" + "Hashtags: " + strHashtags
				+ "</td></tr>");
		sb.appendHtmlConstant("<tr><td colspan='3'   >");
		sb.appendHtmlConstant("Herkunft: " + u.getAnzeigeHerkunft());
		sb.appendHtmlConstant("</td></tr>");
		sb.appendHtmlConstant("</table>");
		sb.appendHtmlConstant("</div>");

	}

}
