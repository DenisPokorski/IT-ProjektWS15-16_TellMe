package de.hdm.tellme.shared.report;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.tellme.client.TellMe;
import de.hdm.tellme.client.Schaukasten.Impressum;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Der ReportWriter formatiert den Report mit HTML.
 * 
 * @author Nicole Reum
 *
 */

public class HTMLReportWriter {
	DateTimeFormat dF = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");

	private String reportText = "";

	private StringBuffer buffer = new StringBuffer();
	private Label ueberrschrift1 = new Label("Reportgenerator 3: ");
	private Label subtext = new Label(
			"Ausgabe aller Hashtagabonnoments eines Nutzers");

	public void generateReport6(Vector<Hashtag> hashtagliste, Nutzer nutzer) {
		
		RootPanel.get("content_right").clear();
		ueberrschrift1.setStylePrimaryName("ueberschrift_report");
		subtext.setStylePrimaryName("subtext_report");

		erstelleKopfdatenReport6(nutzer);
		erstelleKoerperDatenReport6(hashtagliste);

		this.reportText = buffer.toString();
		HTML html = new HTML(reportText);
		html.setStylePrimaryName("report_inhalt");
		html.setStylePrimaryName("report_inhalt_ganze_breite");

		RootPanel.get("content_right").add(ueberrschrift1);
		RootPanel.get("content_right").add(subtext);
		RootPanel.get("content_right").add(html);
	}


	/**
	 * Report 6
	 */
	public void erstelleKopfdatenReport6(Nutzer n) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + n.getVorname()
				+ " " + n.getNachname();

		// Datum formatiert
		Date currentTime = new Date(System.currentTimeMillis());
		HTML Erstellungsdatum = new HTML(dF.format(new Timestamp(currentTime
				.getTime())));

		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<th colspan='2' class='reportKopfzeile'> Report 6 </th>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> <br />Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");
	}

	public void erstelleKoerperDatenReport6(Vector<Hashtag> result) {
		if (result.size() <= 0) {
			buffer.append("<table class ='" +"report_kein_inhalt"+"'>");
			buffer.append("<tr> <th>FÜR DEN AUSGEWÄHLTEN NUTZER"
					+ " IST ZUR ZEIT KEIN REPORT 6 VERFÜGBAR, DA DER NUTZER KEINE "
					+ "HASHTAGS ABONNIERT HAT!</th></tr>");
			buffer.append("</table>");
			
		} else {

			buffer.append("<table class='reportkoerper'>");
			buffer.append("<tr class='kopfZeileKoerper'> <th>Hashtag</th><th>Erstellungsdatum</th></tr>");
			for (int i = 0; i < result.size(); i++) {
				buffer.append("<tr class='ergebnisZeileReport'>" + "<td>#"
						+ result.get(i).getSchlagwort() + "</td>" + "<td>"
						+ dF.format(result.get(i).getErstellungsDatum())
						+ "</td>" + "</tr>");
			}
			buffer.append("</table>");
		}
	}

	/**
	 * Report 5
	 */
	public void generateReport5(Vector<Nutzer> result, Nutzer n) {

		RootPanel.get("content_right").clear();
		Label ueberrschrift1 = new Label("Reportgenerator 5: ");
		Label subtext = new Label("Ausgabe aller Nachrichten eines Nutzers");
		ueberrschrift1.setStylePrimaryName("ueberschrift_report");
		subtext.setStylePrimaryName("subtext_report");

		RootPanel.get("content_right").add(ueberrschrift1);
		RootPanel.get("content_right").add(subtext);

		erstelleKopfDatenReport5(n);
		erstelleKoerperDatenReport5(result);

		this.reportText = buffer.toString();
		HTML html = new HTML(reportText);
		html.setStylePrimaryName("report_inhalt");

		RootPanel.get("content_right").add(html);
	}

	public void erstelleKopfDatenReport5(Nutzer n) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + n.getVorname()
				+ " " + n.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());

		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<th colspan='2' class='reportKopfzeile'> Report 2 </th>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");

	}

	/**
	 * TODO Erstellungsdatum zeigt NULL an.
	 * 
	 * @param result
	 */
	public void erstelleKoerperDatenReport5(Vector<Nutzer> result) {

		if (result.size() <= 0) {
			buffer.append("<table>");
			buffer.append("<tr> <th>FÜR DEN AUSGEWÄHLTEN NUTZER"
					+ " IST ZUR ZEIT KEIN REPORT 2 VERFÜGBAR, DA DER NUTZER KEINE "
					+ "ANDEREN NUTZER ABONNIERT HAT!</th></tr>");
			buffer.append("</table>");
		} else {

			buffer.append("<table class='reportkoerper'>");
			buffer.append("<tr class='kopfZeileKoerper'> <th>Nutzer</th><th>Erstellungsdatum</th></tr>");
			for (int i = 0; i < result.size(); i++) {
				buffer.append("<tr class='ergebnisZeileReport'>" + "<td>"
						+ result.get(i).getVorname() + " "
						+ result.get(i).getNachname() + "</td>" + "<td>"
						+ dF.format(result.get(i).getErstellungsDatum())
						+ "</td>" + "</tr>");
			}
			buffer.append("</table>");

		}

	}

	/**
	 * Report 1
	 */
	private Label ueberschrift1 = new Label("Report 1");
	private Label subtext1 = new Label(
			"Report 1 gibt alle Nachrichten eines Nutzers aus.");

	public void generateReport1(Vector<Unterhaltung> result, Nutzer b) {
		RootPanel.get("content").clear();
		ueberschrift1.setStylePrimaryName("ueberschrift_report");
		subtext1.setStylePrimaryName("subtext_report");

		erstelleKopfDatenReport1(b);
		erstelleKoerperDatenReport1(result, b);

		this.reportText = buffer.toString();

		RootPanel.get("content_right").add(ueberschrift1);
		RootPanel.get("content_right").add(subtext1);

		RootPanel.get("content_right").add(new HTML(reportText));
	}

	public void erstelleKopfDatenReport1(Nutzer n) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + n.getVorname()
				+ " " + n.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());
		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<th colspan='2' class='reportKopfzeile'> Report 1 </th>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> <br />Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");

	}

	public void erstelleKoerperDatenReport1(Vector<Unterhaltung> result, Nutzer b) {

		buffer.append("<table class='reportkoerper'>");
		buffer.append("<tr class='kopfZeileKoerper'> <th>Autor</th><th>Nachricht</th><th>Empfänger</th><th>Erstellungsdatum</th><th>Hashtag</th><th>Sichtbarkeit</th></tr>");
		for (Unterhaltung unterhaltung : result) {
			for (Nachricht nachricht : unterhaltung.getAlleNachrichten()) {
				buffer.append("<tr class='reportKoerper'> <th>"
						+ b.getVorname() + " " + b.getNachname() + "</th><th>"
						+ nachricht.getText() + "</th>");
				buffer.append("<th>");
				for (Nutzer nutzer : unterhaltung.getTeilnehmer()) {
					if (nutzer.getId() != TellMe.gibEingeloggterBenutzer()
							.getUser().getId())
						buffer.append("" + nutzer.getVorname() + " "
								+ nutzer.getNachname() + "<br />");

				}
				buffer.append("</th><th>"
						+ dF.format(nachricht.getErstellungsDatum())
						+ "</th><th>");

				for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
					buffer.append("#" + hashtag.getSchlagwort() + " <br />");

				}
				if (nachricht.getSichtbarkeit() == 0)
					buffer.append("</th><th>inaktiv</th>");
				else
					buffer.append("</th><th>aktiv</th>");
			}

		}
		buffer.append("</table>");
	}

	/**
	 * Report 2
	 */

	private Label ueberschrift2 = new Label("Report 2");
	private Label subtext2 = new Label(
			"Report 2 gibt alle Nachrichten in einem bestimmten Zeitraum aus.");

	public void generateReport2(Vector<Unterhaltung> result, Nutzer b) {
		RootPanel.get("content").clear();
		ueberschrift2.setStylePrimaryName("ueberschrift_report");
		subtext2.setStylePrimaryName("subtext_report");

		erstelleKopfDatenReport2(b);
		erstelleKoerperDatenReport2(result);

		this.reportText = buffer.toString();

		RootPanel.get("content_right").add(ueberschrift2);
		RootPanel.get("content_right").add(subtext2);

		RootPanel.get("content_right").add(new HTML(reportText));
	}

	private void erstelleKoerperDatenReport2(Vector<Unterhaltung> result) {
		buffer.append("<table class='reportkoerper'>");
		buffer.append("<tr class='kopfZeileKoerper'> <th>Autor</th><th>Nachricht</th><th>Empfänger</th><th>Erstellungsdatum</th><th>Hashtag</th><th>Sichtbarkeit</th></tr>");
		for (Unterhaltung unterhaltung : result) {
			for (Nachricht nachricht : unterhaltung.getAlleNachrichten()) {
				buffer.append("<tr class='reportKoerper'> <th>"
						+ nachricht.getSender().getVorname() + " " + nachricht.getSender().getNachname() + "</th><th>"
						+ nachricht.getText() + "</th>");
				buffer.append("<th>");
				for (Nutzer nutzer : unterhaltung.getTeilnehmer()) {
					if (nutzer.getId() != TellMe.gibEingeloggterBenutzer()
							.getUser().getId())
						buffer.append("" + nutzer.getVorname() + " "
								+ nutzer.getNachname() + "<br />");

				}
				buffer.append("</th><th>"
						+ dF.format(nachricht.getErstellungsDatum())
						+ "</th><th>");

				for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
					buffer.append("#" + hashtag.getSchlagwort() + " <br />");

				}
				if (nachricht.getSichtbarkeit() == 0)
					buffer.append("</th><th>inaktiv</th>");
				else
					buffer.append("</th><th>aktiv</th>");
			}

		}
		buffer.append("</table>");


	}

	private void erstelleKopfDatenReport2(Nutzer b) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + b.getVorname()
				+ " " + b.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());
		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<th colspan='2' class='reportKopfzeile'> Report 2 </th>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");
	}

	/**
	 * Report 4
	 */
	private Label ueberschrift4 = new Label("Report 4");
	private Label subtext4 = new Label("Report 4 gibt alle Nachrichten aus.");

	public void generateReport4(Vector<Unterhaltung> result, Nutzer b) {
		RootPanel.get("content").clear();
		ueberschrift4.setStylePrimaryName("ueberschrift_report");
		subtext4.setStylePrimaryName("subtext_report");

		erstelleKopfDatenReport4(b);
		erstelleKoerperDatenReport4(result);

		this.reportText = buffer.toString();

		RootPanel.get("content").add(ueberschrift4);
		RootPanel.get("content").add(subtext4);

		RootPanel.get("content").add(new HTML(reportText));
	}

	private void erstelleKoerperDatenReport4(Vector<Unterhaltung> result) {
		buffer.append("<table class='reportkoerper'>");
		buffer.append("<tr class='kopfZeileKoerper'> <th>Autor</th><th>Nachricht</th><th>Empfänger</th><th>Erstellungsdatum</th><th>Hashtag</th><th>Sichtbarkeit</th></tr>");
		for (Unterhaltung unterhaltung : result) {
			for (Nachricht nachricht : unterhaltung.getAlleNachrichten()) {
				buffer.append("<tr class='reportKoerper'> <th>"
						+ nachricht.getSender().getVorname() + " " + nachricht.getSender().getNachname() + "</th><th>"
						+ nachricht.getText() + "</th>");
				buffer.append("<th>");
				for (Nutzer nutzer : unterhaltung.getTeilnehmer()) {
					if (nutzer.getId() != TellMe.gibEingeloggterBenutzer()
							.getUser().getId())
						buffer.append("" + nutzer.getVorname() + " "
								+ nutzer.getNachname() + "<br />");

				}
				buffer.append("</th><th>"
						+ dF.format(nachricht.getErstellungsDatum())
						+ "</th><th>");

				for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
					buffer.append("#" + hashtag.getSchlagwort() + " <br />");

				}
				if (nachricht.getSichtbarkeit() == 0)
					buffer.append("</th><th>inaktiv</th>");
				else
					buffer.append("</th><th>aktiv</th>");
			}

		}
		buffer.append("</table>");

	}

	private void erstelleKopfDatenReport4(Nutzer b) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + b.getVorname()
				+ " " + b.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());
		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<th colspan='2' class='reportKopfzeile'> Report 4 </th>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");
	}

	/**
	 * Report 3
	 */
	private Label ueberschrift3 = new Label("Report 3");
	private Label subtext3 = new Label(
			"Report 3 gibt alle Nachrichten eines Nutzers aus.");

	public void generateReport3(Vector<Unterhaltung> result, Nutzer b) {
		RootPanel.get("content").clear();
		ueberschrift3.setStylePrimaryName("ueberschrift_report");
		subtext3.setStylePrimaryName("subtext_report");

		erstelleKopfDatenReport3(b);
		erstelleKoerperDatenReport3(result, b);

		this.reportText = buffer.toString();

		RootPanel.get("content_right").add(ueberschrift3);
		RootPanel.get("content_right").add(subtext3);

		RootPanel.get("content_right").add(new HTML(reportText));
	}

	private void erstelleKoerperDatenReport3(Vector<Unterhaltung> result,
			Nutzer b) {
		buffer.append("<table class='reportkoerper'>");
		buffer.append("<tr class='kopfZeileKoerper'> <th>Autor</th><th>Nachricht</th><th>Empfänger</th><th>Erstellungsdatum</th><th>Hashtag</th><th>Sichtbarkeit</th></tr>");
		for (Unterhaltung unterhaltung : result) {
			for (Nachricht nachricht : unterhaltung.getAlleNachrichten()) {
				buffer.append("<tr class='reportKoerper'> <th>"
						+ b.getVorname() + " " + b.getNachname() + "</th><th>"
						+ nachricht.getText() + "</th>");
				buffer.append("<th>");
				for (Nutzer nutzer : unterhaltung.getTeilnehmer()) {
					if (nutzer.getId() != TellMe.gibEingeloggterBenutzer()
							.getUser().getId())
						buffer.append("" + nutzer.getVorname() + " "
								+ nutzer.getNachname() + "<br />");

				}
				buffer.append("</th><th>"
						+ dF.format(nachricht.getErstellungsDatum())
						+ "</th><th>");

				for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
					buffer.append("#" + hashtag.getSchlagwort() + " <br />");

				}
				if (nachricht.getSichtbarkeit() == 0)
					buffer.append("</th><th>inaktiv</th>");
				else
					buffer.append("</th><th>aktiv</th>");
			}

		}
		buffer.append("</table>");

	}

	private void erstelleKopfDatenReport3(Nutzer b) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + b.getVorname()
				+ " " + b.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());
		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<th colspan='2' class='reportKopfzeile'> Report 3 </th>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");
	}

}