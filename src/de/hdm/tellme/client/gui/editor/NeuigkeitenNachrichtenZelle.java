package de.hdm.tellme.client.gui.editor;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Unterhaltung;

public class NeuigkeitenNachrichtenZelle extends AbstractCell<Nachricht> {
	
	Unterhaltung u = null;
	
	public NeuigkeitenNachrichtenZelle(Unterhaltung _u){
		u = _u;
	}
	
	public void render(Context context, Nachricht value, SafeHtmlBuilder sb) {
		// Value can be null, so do a null check..
		if (value == null) {
			return;
		}		
		sb.appendHtmlConstant("<div class='neuigkeitenNachrichtInhalt'>");
		sb.appendHtmlConstant("<table style='width:100%'><tr><td>");
		sb.appendEscaped("OE");
		sb.appendHtmlConstant("</td><td>");
		sb.appendEscaped("NA");
		sb.appendHtmlConstant("</td><td>");
		sb.appendEscaped("Absender: " + u.getId() + "-"+ value.getSender().getVorname() + " " + value.getSender().getNachname());
		sb.appendHtmlConstant("</td><td>");
		sb.appendEscaped("22.11.2015 Bla");
		sb.appendHtmlConstant("</td></tr><tr><td colspan='3'>");
		sb.appendEscaped(value.getText());
		sb.appendHtmlConstant("</td>");		
		sb.appendHtmlConstant("</tr></table>");
		sb.appendHtmlConstant("</div>");
		
	}
	
}
