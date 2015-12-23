package de.hdm.tellme.client.gui.editor;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.safehtml.shared.SafeHtml;

import de.hdm.tellme.client.NeuigkeitenEditor;
import de.hdm.tellme.shared.bo.Nachricht;

public class NeuigkeitenNachrichtenZelle extends AbstractCell<Nachricht> {
	
	
	public void render(Context context, Nachricht value, SafeHtmlBuilder sb) {
		// Value can be null, so do a null check..
		if (value == null) {
			return;
		}
		final Nachricht test = value;	
		
		sb.appendHtmlConstant("<div class='neuigkeitenNachrichtInhalt'>");
		sb.appendHtmlConstant("<table style='width:100%'><tr><td>");
		sb.appendEscaped("OE");
		sb.appendHtmlConstant("</td><td>");
		sb.appendEscaped("NA");
		sb.appendHtmlConstant("</td><td>");
		sb.appendEscaped("Absender: " + value.getSender().getVorname() + " " + value.getSender().getNachname());
		sb.appendHtmlConstant("</td><td>");
		sb.appendEscaped("22.11.2015 Bla");
		sb.appendHtmlConstant("</td></tr><tr><td colspan='3'>");
		sb.appendEscaped(value.getText());
		sb.appendHtmlConstant("</td>");		
		sb.appendHtmlConstant("</tr></table>");
		sb.appendHtmlConstant("</div>");
		
	}
	
}
