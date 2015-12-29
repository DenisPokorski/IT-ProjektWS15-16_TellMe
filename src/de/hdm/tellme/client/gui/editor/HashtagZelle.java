package de.hdm.tellme.client.gui.editor;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.tellme.shared.bo.Hashtag;

public class HashtagZelle  {
	public class ZellenObjekt {
		public Hashtag hashtag;
		public boolean aboniert;
	}

	public class ZellenElement extends AbstractCell<HashtagZelle.ZellenObjekt> {
		@Override
		public void render(Context context, HashtagZelle.ZellenObjekt value, SafeHtmlBuilder sb) {
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
