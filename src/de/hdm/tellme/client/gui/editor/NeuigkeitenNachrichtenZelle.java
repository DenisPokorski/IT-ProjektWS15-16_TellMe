package de.hdm.tellme.client.gui.editor;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;
import de.hdm.tellme.shared.bo.Unterhaltung.eUnterhaltungsTyp;

public class NeuigkeitenNachrichtenZelle extends AbstractCell<Nachricht> {

	Unterhaltung u = null;

	public NeuigkeitenNachrichtenZelle(Unterhaltung _u) {
		u = _u;
	}

	public void render(Context context, Nachricht value, SafeHtmlBuilder sb) {
		// Value can be null, so do a null check..
		if (value == null) {
			return;
		}
		
		String strUnterhaltungsTyp = "";
		String strDatum = "";
		String strAbsender = "";
		String strText = "";

		if (u.getUnterhaltungstyp() == eUnterhaltungsTyp.oeffentlich) {
			strUnterhaltungsTyp = "OE";
		}
		else{
			strUnterhaltungsTyp = "PR";
		}
		
		strDatum = value.getErstellungsDatum().toString();
		
		strAbsender = value.getSender().getVorname() + " " + value.getSender().getNachname();

		strText = value.getText();
		
		sb.appendHtmlConstant("<div class='neuigkeitenNachrichtInhalt'>");
		sb.appendHtmlConstant("<table style='width:100%'><tr><td>");

		sb.appendHtmlConstant(strUnterhaltungsTyp);

		sb.appendHtmlConstant("</td><td>");

		sb.appendEscaped("Absender: " + strAbsender);
		sb.appendHtmlConstant("</td><td>");
		sb.appendHtmlConstant(strDatum);
		sb.appendHtmlConstant("</td></tr><tr><td colspan='3'>");
		sb.appendEscaped(strText);
		sb.appendHtmlConstant("</td>");
		sb.appendHtmlConstant("</tr></table>");
		sb.appendHtmlConstant("</div>");

	}

}
