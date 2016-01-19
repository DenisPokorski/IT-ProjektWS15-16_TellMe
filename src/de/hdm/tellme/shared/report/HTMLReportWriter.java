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
 * Der ReportWriter formatiert den Report in HTML-Code. Er wird für jeden Report
 * einzeln aufgerufen und beinhaltet eine generateReport Methode eine
 * erstelleKörperDaten und eine erstelleKopfdaten Methode.
 * 
 * @author Nicole Reum
 *
 */

/*
 * Da diese Klasse ein gewisse Größe besitzt - dies ist eigentlich ein Hinweise,
 * dass hier eine weitere Gliederung sinnvoll ist - haben wir zur besseren
 * Übersicht Abschnittskomentare eingefügt. Sie leiten ein Cluster in
 * irgeneinerweise zusammengehöriger Methoden ein. Ein entsprechender Kommentar
 * steht am Ende eines solchen Clusters.
 */
public class HTMLReportWriter {

	DateTimeFormat dF = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	/**
	 * Diese Variable wird mit dem Ergebenis einer Umwandlung belegt.
	 */
	private String reportText = "";
	/**
	 * Die Elemente werden in einem StringBuffer gespeichert und später
	 * ausgegeben.
	 */
	private StringBuffer buffer = new StringBuffer();

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Report 1 ***************************************
	 * ************************************
	 */
	/**
	 * Die Methode <code>generateReport1</code> säubert den Content, setzt dann
	 * die Überschrift des Report 1. Darauffolgend erstellt sie für den
	 * ausgewählten Nutzer die Kopfdaten und die Körperdaten des Reports durch
	 * die Methoden <code>erstelleKopfDatenReport1</code>
	 * <code>erstelleKoerperDatenReport1</code>. Der Text der dem StringBuffer
	 * angehängt wurde wird durch die <code>toString</code>-Methode in einen
	 * String umgewandelt und in HTML-Code formatiert. Anschließend wird die
	 * <code>überschrift</code> und der Report als <code>html</code> dem Content
	 * hinzugefügt.
	 * 
	 * @param result
	 *            , die benötogten Unterhaltungen
	 * @param b
	 *            , das übergebene NutzerObjekt
	 */
	public void generateReport1(Vector<Unterhaltung> result, Nutzer b) {
		RootPanel.get("content").clear();

		Label ueberschrift1 = new Label(
				"Report 1 gibt alle Nachrichten eines Nutzers aus.");
		ueberschrift1.setStylePrimaryName("ueberschrift_report");

		erstelleKopfDatenReport1(b);
		erstelleKoerperDatenReport1(result, b);

		this.reportText = buffer.toString();

		HTML html = new HTML(reportText);
		html.setStylePrimaryName("report_inhalt_volle_breite");

		RootPanel.get("content").add(ueberschrift1);
		RootPanel.get("content").add(html);
	}

	/**
	 * Die Methode <code>erstelleKopfDatenReport1</code> stellt den
	 * Ausgangsnutzer und das aktuelle Datum bzw, den Zeitpunkt dar, wann der
	 * Report erstellt wurde. Außerdem wird auf der rechten Tabellenspalte das
	 * Impressum des Programms dargestellt.
	 * 
	 * @param n
	 *            , das Nutzer-Objekt, dass den Autor darstellt.
	 */
	public void erstelleKopfDatenReport1(Nutzer n) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + n.getVorname()
				+ " " + n.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());
		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<td colspan='2' class='reportKopfzeile'> Report 1 </td>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> <br />Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");

	}

	/**
	 * Im Report 1 werden alle Nachrichten eines Nutzers während eines
	 * bestimmten Zeitpunkts dargestellt. Deshalb gibt es die Spalten Autor,
	 * Nachricht, Teilnehmer der Unterhaltung, Erstellungsdatum, Hashtags und
	 * Sichtbarkeit. In den jeweiligen Spalten werden die Ergebnisse der
	 * Unterhaltung hinzugefügt.
	 * 
	 * @param result
	 *            , die gesuchten Unterhaltungen
	 * @param b
	 *            , Nutzer-Objekt
	 */
	public void erstelleKoerperDatenReport1(Vector<Unterhaltung> result,
			Nutzer b) {

		buffer.append("<table class='reportkoerper_vollbild'>");
		buffer.append("<tr class='kopfZeileKoerper'> <td>Autor</td><td>Nachricht</td><td>Teilnehmer der Unterhaltung</td><td>Erstellungsdatum</td><td>Hashtag</td><td>Sichtbarkeit</td></tr>");
		for (Unterhaltung unterhaltung : result) {
			for (Nachricht nachricht : unterhaltung.getAlleNachrichten()) {
				buffer.append("<tr class='reportKoerper'> <td>"
						+ b.getVorname() + " " + b.getNachname() + "</td><td>"
						+ nachricht.getText() + "</td>");
				buffer.append("<td>");
				for (Nutzer nutzer : unterhaltung.getTeilnehmer()) {
					if (nutzer.getId() != TellMe.gibEingeloggterBenutzer()
							.getUser().getId())
						buffer.append("" + nutzer.getVorname() + " "
								+ nutzer.getNachname() + "<br />");

				}
				buffer.append("</td><td>"
						+ dF.format(nachricht.getErstellungsDatum())
						+ "</td><td>");

				for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
					buffer.append("#" + hashtag.getSchlagwort() + " <br />");

				}
				if (nachricht.getSichtbarkeit() == 0)
					buffer.append("</td><td>inaktiv</td>");
				else
					buffer.append("</td><td>aktiv</td>");
			}

		}
		buffer.append("</table>");
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Report 1 *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Report 2 ***************************************
	 * ************************************
	 */
	/**
	 * Die Methode <code>generateReport2</code> setzt die Überschrift des Report
	 * 2. Daraufhin erstellt sie für den ausgewählten Nutzer die Kopfdaten und
	 * die Körperdaten des Reports durch die Methoden
	 * <code>erstelleKopfDatenReport2</code>
	 * <code>erstelleKoerperDatenReport2</code>. Der Text der dem StringBuffer
	 * angehängt wurde wird durch die <code>toString</code>-Methode in einen
	 * String umgewandelt und in HTML-Code formatiert. Anschließend wird die
	 * <code>überschrift</code> und der Report als <code>html</code> dem Content
	 * hinzugefügt.
	 * 
	 * @param result
	 *            , die benötogten Unterhaltungen
	 * @param b
	 *            , das übergebene NutzerObjekt
	 */
	public void generateReport2(Vector<Unterhaltung> result, Nutzer b) {

		Label ueberschrift2 = new Label(
				"Report 2: Gibt alle Nachrichten in einem bestimmten Zeitraum aus.");
		ueberschrift2.setStylePrimaryName("ueberschrift_report");

		erstelleKopfDatenReport2(b);
		erstelleKoerperDatenReport2(result);

		this.reportText = buffer.toString();

		HTML html = new HTML(reportText);
		html.setStylePrimaryName("report_inhalt_volle_breite");

		RootPanel.get("content").clear();
		RootPanel.get("content").add(ueberschrift2);
		RootPanel.get("content").add(html);
	}

	/**
	 * Im Report 2 werden alle Nachrichten während eines bestimmten Zeitpunkts
	 * dargestellt. Deshalb gibt es die Spalten Autor, Nachricht, Teilnehmer der
	 * Unterhaltung, Erstellungsdatum, Hashtags und Sichtbarkeit. In den
	 * jeweiligen Spalten werden die Ergebnisse der Unterhaltung hinzugefügt.
	 * 
	 * @param result
	 *            , die gesuchten Unterhaltungen
	 * @param b
	 *            , Nutzer-Objekt
	 */

	private void erstelleKoerperDatenReport2(Vector<Unterhaltung> result) {
		buffer.append("<table class='reportkoerper_vollbild'>");
		buffer.append("<tr class='kopfZeileKoerper'> <td>Autor</td><td>Nachricht</td><td>Empfänger</td><td>Erstellungsdatum</td><td>Hashtag</td><td>Sichtbarkeit</td></tr>");
		for (Unterhaltung unterhaltung : result) {
			for (Nachricht nachricht : unterhaltung.getAlleNachrichten()) {
				buffer.append("<tr class='reportKoerper'> <td>"
						+ nachricht.getSender().getVorname() + " "
						+ nachricht.getSender().getNachname() + "</td><td>"
						+ nachricht.getText() + "</td>");
				buffer.append("<td>");
				for (Nutzer nutzer : unterhaltung.getTeilnehmer()) {
					if (nutzer.getId() != TellMe.gibEingeloggterBenutzer()
							.getUser().getId())
						buffer.append("" + nutzer.getVorname() + " "
								+ nutzer.getNachname() + "<br />");

				}
				buffer.append("</td><td>"
						+ dF.format(nachricht.getErstellungsDatum())
						+ "</td><td>");

				for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
					buffer.append("#" + hashtag.getSchlagwort() + " <br />");

				}
				if (nachricht.getSichtbarkeit() == 0)
					buffer.append("</td><td>inaktiv</td>");
				else
					buffer.append("</td><td>aktiv</td>");
			}

		}
		buffer.append("</table>");

	}

	/**
	 * Die Methode <code>erstelleKopfDatenReport2</code> stellt den
	 * Ausgangsnutzer und das aktuelle Datum bzw, den Zeitpunkt dar, wann der
	 * Report erstellt wurde. Außerdem wird auf der rechten Tabellenspalte das
	 * Impressum des Programms dargestellt.
	 * 
	 * @param n
	 *            , das Nutzer-Objekt, dass den Autor darstellt.
	 */
	private void erstelleKopfDatenReport2(Nutzer b) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + b.getVorname()
				+ " " + b.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());
		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<td colspan='2' class='reportKopfzeile'> Report 2 </td>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Report 2 *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Report 3 ***************************************
	 * ************************************
	 */

	/**
	 * Die Methode <code>generateReport3</code> säubert den Content, setzt dann
	 * die Überschrift des Report 3. Darauffolgend erstellt sie für den
	 * ausgewählten Nutzer die Kopfdaten und die Körperdaten des Reports durch
	 * die Methoden <code>erstelleKopfDatenReport3</code>
	 * <code>erstelleKoerperDatenReport3</code>. Der Text der dem StringBuffer
	 * angehängt wurde wird durch die <code>toString</code>-Methode in einen
	 * String umgewandelt und in HTML-Code formatiert. Anschließend wird die
	 * <code>überschrift</code> und der Report als <code>html</code> dem Content
	 * hinzugefügt.
	 * 
	 * @param result
	 *            , die benötogten Unterhaltungen
	 * @param b
	 *            , das übergebene NutzerObjekt
	 */

	public void generateReport3(Vector<Unterhaltung> result, Nutzer b) {

		Label ueberschrift3 = new Label(
				"Report 3:gibt alle Nachrichten eines Nutzers aus");
		ueberschrift3.setStylePrimaryName("ueberschrift_report");

		erstelleKopfDatenReport3(b);
		erstelleKoerperDatenReport3(result, b);

		this.reportText = buffer.toString();

		HTML html = new HTML(reportText);
		html.setStylePrimaryName("report_inhalt_volle_breite");

		RootPanel.get("content").clear();
		RootPanel.get("content").add(ueberschrift3);
		RootPanel.get("content").add(html);
	}

	/**
	 * Im Report 3 werden alle Nachrichten eines Nutzers dargestellt. Deshalb
	 * gibt es die Spalten Autor, Nachricht, Teilnehmer der Unterhaltung,
	 * Erstellungsdatum, Hashtags und Sichtbarkeit. In den jeweiligen Spalten
	 * werden die Ergebnisse der Unterhaltung hinzugefügt.
	 * 
	 * @param result
	 *            , die gesuchten Unterhaltungen
	 * @param b
	 *            , Nutzer-Objekt
	 */
	private void erstelleKoerperDatenReport3(Vector<Unterhaltung> result,
			Nutzer b) {
		buffer.append("<table class='reportkoerper_vollbild'>");
		buffer.append("<tr class='kopfZeileKoerper'> <td>Autor</td><td>Nachricht</td><td>Empfänger</td><td>Erstellungsdatum</td><td>Hashtag</td><td>Sichtbarkeit</td></tr>");
		for (Unterhaltung unterhaltung : result) {
			for (Nachricht nachricht : unterhaltung.getAlleNachrichten()) {
				buffer.append("<tr class='reportKoerper'> <td>"
						+ b.getVorname() + " " + b.getNachname() + "</td><td>"
						+ nachricht.getText() + "</td>");
				buffer.append("<td>");
				for (Nutzer nutzer : unterhaltung.getTeilnehmer()) {
					if (nutzer.getId() != TellMe.gibEingeloggterBenutzer()
							.getUser().getId())
						buffer.append("" + nutzer.getVorname() + " "
								+ nutzer.getNachname() + "<br />");

				}
				buffer.append("</td><td>"
						+ dF.format(nachricht.getErstellungsDatum())
						+ "</td><td>");

				for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
					buffer.append("#" + hashtag.getSchlagwort() + " <br />");

				}
				if (nachricht.getSichtbarkeit() == 0)
					buffer.append("</td><td>inaktiv</td>");
				else
					buffer.append("</td><td>aktiv</td>");
			}

		}
		buffer.append("</table>");

	}

	/**
	 * Die Methode <code>erstelleKopfDatenReport2</code> stellt den
	 * Ausgangsnutzer und das aktuelle Datum bzw, den Zeitpunkt dar, wann der
	 * Report erstellt wurde. Außerdem wird auf der rechten Tabellenspalte das
	 * Impressum des Programms dargestellt.
	 * 
	 * @param n
	 *            , das Nutzer-Objekt, dass den Autor darstellt.
	 */
	private void erstelleKopfDatenReport3(Nutzer b) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + b.getVorname()
				+ " " + b.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());
		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<td colspan='2' class='reportKopfzeile'> Report 3 </td>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Report 3 *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Report 4 ***************************************
	 * ************************************
	 */
	/**
	 * Die Methode <code>generateReport4</code> säubert den Content, setzt dann
	 * die Überschrift des Report 4. Darauffolgend erstellt sie für den
	 * ausgewählten Nutzer die Kopfdaten und die Körperdaten des Reports durch
	 * die Methoden <code>erstelleKopfDatenReport4</code>
	 * <code>erstelleKoerperDatenReport4</code>. Der Text der dem StringBuffer
	 * angehängt wurde wird durch die <code>toString</code>-Methode in einen
	 * String umgewandelt und in HTML-Code formatiert. Anschließend wird die
	 * <code>überschrift</code> und der Report als <code>html</code> dem Content
	 * hinzugefügt.
	 * 
	 * @param result
	 *            , die benötogten Unterhaltungen
	 * @param b
	 *            , das übergebene NutzerObjekt
	 */
	public void generateReport4(Vector<Unterhaltung> result, Nutzer b) {

		RootPanel.get("content").clear();

		Label ueberschrift4 = new Label("Report 4: Gibt alle Nachrichten aus.");
		ueberschrift4.setStylePrimaryName("ueberschrift_report");

		erstelleKopfDatenReport4(b);
		erstelleKoerperDatenReport4(result);

		this.reportText = buffer.toString();

		RootPanel.get("content").add(ueberschrift4);
		HTML html = new HTML(reportText);
		html.setStylePrimaryName("report_inhalt_volle_breite");

		RootPanel.get("content").add(html);
	}

	/**
	 * Im Report 4 werden alle Nachrichten dargestellt. Deshalb gibt es die
	 * Spalten Autor, Nachricht, Teilnehmer der Unterhaltung, Erstellungsdatum,
	 * Hashtags und Sichtbarkeit. In den jeweiligen Spalten werden die
	 * Ergebnisse der Unterhaltung hinzugefügt.
	 * 
	 * @param result
	 *            , die gesuchten Unterhaltungen
	 * @param b
	 *            , Nutzer-Objekt
	 */
	private void erstelleKoerperDatenReport4(Vector<Unterhaltung> result) {
		buffer.append("<table class='reportkoerper_vollbild'>");
		buffer.append("<tr class='kopfZeileKoerper'> <td>Autor</td><td>Nachricht</td>"
				+ "<td>Empfänger</td><td>Erstellungsdatum</td><td>Hashtag</td><td>Sichtbarkeit</td></tr>");
		for (Unterhaltung unterhaltung : result) {
			for (Nachricht nachricht : unterhaltung.getAlleNachrichten()) {
				buffer.append("<tr class='reportKoerper'> <td>"
						+ nachricht.getSender().getVorname() + " "
						+ nachricht.getSender().getNachname() + "</td><td>"
						+ nachricht.getText() + "</td>");
				buffer.append("<td>");
				for (Nutzer nutzer : unterhaltung.getTeilnehmer()) {
					if (nutzer.getId() != TellMe.gibEingeloggterBenutzer()
							.getUser().getId())
						buffer.append("" + nutzer.getVorname() + " "
								+ nutzer.getNachname() + "<br />");

				}
				buffer.append("</td><td>"
						+ dF.format(nachricht.getErstellungsDatum())
						+ "</td><td>");

				for (Hashtag hashtag : nachricht.getVerknuepfteHashtags()) {
					buffer.append("#" + hashtag.getSchlagwort() + " <br />");

				}
				if (nachricht.getSichtbarkeit() == 0)
					buffer.append("</td><td>inaktiv</td>");
				else
					buffer.append("</td><td>aktiv</td>");
			}

		}
		buffer.append("</table>");

	}

	/**
	 * Die Methode <code>erstelleKopfDatenReport4</code> stellt den
	 * Ausgangsnutzer und das aktuelle Datum bzw, den Zeitpunkt dar, wann der
	 * Report erstellt wurde. Außerdem wird auf der rechten Tabellenspalte das
	 * Impressum des Programms dargestellt.
	 * 
	 * @param n
	 *            , das Nutzer-Objekt, dass den Autor darstellt.
	 */
	private void erstelleKopfDatenReport4(Nutzer b) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + b.getVorname()
				+ " " + b.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());
		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<td colspan='2' class='reportKopfzeile'> Report 4 </td>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Report 4 *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Report 5 ***************************************
	 * ************************************
	 */
	/**
	 * Die Methode <code>generateReport5</code> säubert den Content, setzt dann
	 * die Überschrift des Report 5. Darauffolgend erstellt sie für den
	 * ausgewählten Nutzer die Kopfdaten und die Körperdaten des Reports durch
	 * die Methoden <code>erstelleKopfDatenReport5</code>
	 * <code>erstelleKoerperDatenReport5</code>. Der Text der dem StringBuffer
	 * angehängt wurde wird durch die <code>toString</code>-Methode in einen
	 * String umgewandelt und in HTML-Code formatiert. Anschließend wird die
	 * <code>überschrift</code> und der Report als <code>html</code> dem Content
	 * hinzugefügt.
	 * 
	 * @param result
	 *            , die benötogten Unterhaltungen
	 * @param b
	 *            , das übergebene NutzerObjekt
	 */
	public void generateReport5(Vector<Nutzer> result, Nutzer n) {

		RootPanel.get("content_right").clear();
		Label ueberrschrift1 = new Label("Reportgenerator 5: ");
		Label subtext = new Label("Ausgabe aller Nutzer, die der ausgewählte Nutzer abonniert hat.");
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

	/**
	 * Die Methode <code>erstelleKopfDatenReport2</code> stellt den
	 * Ausgangsnutzer und das aktuelle Datum bzw, den Zeitpunkt dar, wann der
	 * Report erstellt wurde. Außerdem wird auf der rechten Tabellenspalte das
	 * Impressum des Programms dargestellt.
	 * 
	 * @param n
	 *            , das Nutzer-Objekt, dass den Autor darstellt.
	 */
	public void erstelleKopfDatenReport5(Nutzer n) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + n.getVorname()
				+ " " + n.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());

		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<td colspan='2' class='reportKopfzeile'> Report 5 </td>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");

	}

	/**
	 * Im Report 5 werden alle Nutzerabonnements eines ausgwählten Nutzer
	 * angezeigt, deshalb hat die Ergebnistabelle 2 Spalten. In der ersten
	 * Spalte steht der Nutzer der abonniert ist und in der zweiten Spalte steht
	 * das Erstellungsdatum des Abonnements.
	 * 
	 * @param result
	 *            , Vektor mit Nutzer-Objekten
	 */
	public void erstelleKoerperDatenReport5(Vector<Nutzer> result) {

		if (result.size() <= 0) {
			buffer.append("<table class ='" + "report_kein_inhalt" + "'>");
			buffer.append("<tr> <td>FÜR DEN AUSGEWÄHLTEN NUTZER"
					+ " IST ZUR ZEIT KEIN REPORT 5 VERFÜGBAR, DA DER NUTZER KEINE "
					+ "ANDEREN NUTZER ABONNIERT HAT!</td></tr>");
			buffer.append("</table>");
		} else {

			buffer.append("<table class='reportkoerper_vollbild'>");
			buffer.append("<tr class='kopfZeileKoerper'> <td>Nutzer</td><td>Erstellungsdatum</td></tr>");
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

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Report 5 *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Report 6 ***************************************
	 * ************************************
	 */

	/**
	 * Die Methode <code>generateReport6</code> säubert den Content, setzt dann
	 * die Überschrift des Report 6. Darauffolgend erstellt sie für den
	 * ausgewählten Nutzer die Kopfdaten und die Körperdaten des Reports durch
	 * die Methoden <code>erstelleKopfDatenReport6</code>
	 * <code>erstelleKoerperDatenReport6</code>. Der Text der dem StringBuffer
	 * angehängt wurde wird durch die <code>toString</code>-Methode in einen
	 * String umgewandelt und in HTML-Code formatiert. Anschließend wird die
	 * <code>überschrift</code> und der Report als <code>html</code> dem Content
	 * hinzugefügt.
	 * 
	 * @param result
	 *            , die benötogten Unterhaltungen
	 * @param b
	 *            , das übergebene NutzerObjekt
	 */
	public void generateReport6(Vector<Hashtag> hashtagliste, Nutzer nutzer) {

		Label ueberrschrift1 = new Label(
				"Reportgenerator 6: Ausgabe aller Hashtagabonnoments eines Nutzers");
		ueberrschrift1.setStylePrimaryName("ueberschrift_report");

		RootPanel.get("content_right").clear();
		erstelleKopfdatenReport6(nutzer);
		erstelleKoerperDatenReport6(hashtagliste);

		this.reportText = buffer.toString();
		HTML html = new HTML(reportText);
		html.setStylePrimaryName("report_inhalt");
		// html.setStylePrimaryName("report_inhalt_ganze_breite");

		RootPanel.get("content_right").add(ueberrschrift1);
		RootPanel.get("content_right").add(html);
	}

	/**
	 * Die Methode <code>erstelleKopfDatenReport6</code> stellt den
	 * Ausgangsnutzer und das aktuelle Datum bzw, den Zeitpunkt dar, wann der
	 * Report erstellt wurde. Außerdem wird auf der rechten Tabellenspalte das
	 * Impressum des Programms dargestellt.
	 * 
	 * @param n
	 *            , das Nutzer-Objekt, dass den Autor darstellt.
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
		buffer.append("<td colspan='2' class='reportKopfzeile'> Report 6 </td>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> <br />Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");
	}

	/**
	 * Im Report 6 werden alle Hashtagabonnements eines ausgwählten Nutzer
	 * angezeigt, deshalb hat die Ergebnistabelle 2 Spalten. In der ersten
	 * Spalte steht das Hashtag das der Nutzer abonniert hat und in der zweiten
	 * Spalte steht das Erstellungsdatum des Abonnements.
	 * 
	 * @param result
	 *            , Vektor mit Hashtag-Objekten
	 */
	public void erstelleKoerperDatenReport6(Vector<Hashtag> result) {
		if (result.size() <= 0) {
			buffer.append("<table class ='" + "report_kein_inhalt" + "'>");
			buffer.append("<tr> <td>FÜR DEN AUSGEWÄHLTEN NUTZER"
					+ " IST ZUR ZEIT KEIN REPORT 6 VERFÜGBAR, DA DER NUTZER KEINE "
					+ "HASHTAGS ABONNIERT HAT!</td></tr>");
			buffer.append("</table>");

		} else {

			buffer.append("<table class='reportkoerper_vollbild'>");
			buffer.append("<tr class='kopfZeileKoerper'> <td>Hashtag</td><td>Erstellungsdatum</td></tr>");
			for (int i = 0; i < result.size(); i++) {
				buffer.append("<tr class='ergebnisZeileReport'>" + "<td>#"
						+ result.get(i).getSchlagwort() + "</td>" + "<td>"
						+ dF.format(result.get(i).getErstellungsDatum())
						+ "</td>" + "</tr>");
			}
			buffer.append("</table>");
		}
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Report 6 *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Report 7 ***************************************
	 * ************************************
	 */
	/**
	 * Die Methode <code>generateReport7</code> säubert den Content, setzt dann
	 * die Überschrift des Report 7. Darauffolgend erstellt sie für den
	 * ausgewählten Nutzer die Kopfdaten und die Körperdaten des Reports durch
	 * die Methoden <code>erstelleKopfDatenReport7</code>
	 * <code>erstelleKoerperDatenReport7</code>. Der Text der dem StringBuffer
	 * angehängt wurde wird durch die <code>toString</code>-Methode in einen
	 * String umgewandelt und in HTML-Code formatiert. Anschließend wird die
	 * <code>überschrift</code> und der Report als <code>html</code> dem Content
	 * hinzugefügt.
	 * 
	 * @param result
	 *            , die benötigten Nutzer-Objekte
	 * @param b
	 *            , das übergebene NutzerObjekt
	 */
	public void generateReport7(Vector<Nutzer> result, Nutzer n) {

		RootPanel.get("content_right").clear();
		Label ueberrschrift1 = new Label("Reportgenerator 7: ");
		Label subtext = new Label(
				"Ausgabe aller Nutzer, die diesen Nutzer abonniert haben:");
		ueberrschrift1.setStylePrimaryName("ueberschrift_report");
		subtext.setStylePrimaryName("subtext_report");

		RootPanel.get("content_right").add(ueberrschrift1);
		RootPanel.get("content_right").add(subtext);

		erstelleKopfDatenReport7(n);
		erstelleKoerperDatenReport7(result);

		this.reportText = buffer.toString();
		HTML html = new HTML(reportText);
		html.setStylePrimaryName("report_inhalt");

		RootPanel.get("content_right").add(html);
	}

	/**
	 * Die Methode <code>erstelleKopfDatenReport7</code> stellt den
	 * Ausgangsnutzer und das aktuelle Datum bzw, den Zeitpunkt dar, wann der
	 * Report erstellt wurde. Außerdem wird auf der rechten Tabellenspalte das
	 * Impressum des Programms dargestellt.
	 * 
	 * @param n
	 *            , das Nutzer-Objekt, dass den Autor darstellt.
	 */
	public void erstelleKopfDatenReport7(Nutzer n) {
		String ausgangsnutzer = "Ausgangsnutzer:<div></div>" + n.getVorname()
				+ " " + n.getNachname();
		Date currentTime = new Date(System.currentTimeMillis());

		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<td colspan='2' class='reportKopfzeile'> Report 7 </td>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgangsnutzer
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");

	}

	/**
	 * Im Report 7 werden alle Nutzer dargestellt, die den ausgwählten Nutzer
	 * abonniert haben. Deshalb hat die Ergebnistabelle 2 Spalten. In der ersten
	 * Spalte steht der Nutzer der den ausgewählten Nutzer abonniert hat. In
	 * der zweiten Spalte steht das Erstellungsdatum des Abonnements.
	 * 
	 * @param result
	 *            ,Vektor mit Nutzer-Objekten
	 */
	public void erstelleKoerperDatenReport7(Vector<Nutzer> result) {

		if (result.size() <= 0) {
			buffer.append("<table class ='" + "report_kein_inhalt" + "'>");
			buffer.append("<tr> <td>FÜR DEN AUSGEWÄHLTEN NUTZER"
					+ " IST ZUR ZEIT KEIN REPORT 7 VERFÜGBAR, DA KEIN ANDERER NUTZER DIESEN"
					+ " NUTZER ABONNIERT HAT!</td></tr>");
			buffer.append("</table>");
		} else {

			buffer.append("<table class='reportkoerper_vollbild'>");
			buffer.append("<tr class='kopfZeileKoerper'> <td>Nutzer</td><td>Erstellungsdatum</td></tr>");
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
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Report 7 *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Report 8 ***************************************
	 * ************************************
	 */
	/**
	 * Die Methode <code>generateReport8</code> säubert den Content, setzt dann
	 * die Überschrift des Report 8. Darauffolgend erstellt sie für den
	 * ausgewählten Nutzer die Kopfdaten und die Körperdaten des Reports durch
	 * die Methoden <code>erstelleKopfDatenReport8</code>
	 * <code>erstelleKoerperDatenReport8</code>. Der Text der dem StringBuffer
	 * angehängt wurde wird durch die <code>toString</code>-Methode in einen
	 * String umgewandelt und in HTML-Code formatiert. Anschließend wird die
	 * <code>überschrift</code> und der Report als <code>html</code> dem Content
	 * hinzugefügt.
	 * 
	 * @param result
	 *            , die benötigten Nutzer-Objekte
	 * @param b
	 *            , das übergebene NutzerObjekt
	 */
	public void generateReport8(Vector<Nutzer> result, Hashtag h) {

		RootPanel.get("content_right").clear();
		Label ueberrschrift1 = new Label("Reportgenerator 8: ");
		Label subtext = new Label(
				"Ausgabe aller Nutzer, die dieses Hashtag abonniert haben:");
		ueberrschrift1.setStylePrimaryName("ueberschrift_report");
		subtext.setStylePrimaryName("subtext_report");

		RootPanel.get("content_right").add(ueberrschrift1);
		RootPanel.get("content_right").add(subtext);

		erstelleKopfDatenReport8(h);
		erstelleKoerperDatenReport8(result);

		this.reportText = buffer.toString();
		HTML html = new HTML(reportText);
		html.setStylePrimaryName("report_inhalt");

		RootPanel.get("content_right").add(html);
	}

	/**
	 * Die Methode <code>erstelleKopfDatenReport8</code> stellt den
	 * Ausgangsnutzer und das aktuelle Datum bzw, den Zeitpunkt dar, wann der
	 * Report erstellt wurde. Außerdem wird auf der rechten Tabellenspalte das
	 * Impressum des Programms dargestellt.
	 * 
	 * @param n
	 *            , das Nutzer-Objekt, dass den Autor darstellt.
	 */
	public void erstelleKopfDatenReport8(Hashtag h) {
		String ausgewaehltesHashtag = "gewähltes Hashtag:<div></div>" + h.getSchlagwort()
				;
		Date currentTime = new Date(System.currentTimeMillis());

		HTML Erstellungsdatum = new HTML(""
				+ dF.format(new Timestamp(currentTime.getTime())));
		// Nutzerabo Klasse Format
		buffer.append("<table class='reportTabelle'>");
		buffer.append("<td colspan='2' class='reportKopfzeile'> Report 8 </td>");

		buffer.append("<tr><td class='kopfdatenbox_links'> <div>Ausgewählte(s) Element(e)"
				+ ausgewaehltesHashtag
				+ "</div><div> Datum: "
				+ Erstellungsdatum
				+ "</div></td><td class='kopfdatenbox_rechts'>"
				+ new Impressum().getHtmlImpressum() + "</td></tr>");
		buffer.append("</table>");

	}

	/**
	 * Im Report 7 werden alle Nutzer dargestellt, die den ausgwählten Nutzer
	 * abonniert haben. Deshalb hat die Ergebnistabelle 2 Spalten. In der ersten
	 * Spalte steht der Nutzer der den ausgewählten Nutzer abonniert hat. In
	 * der zweiten Spalte steht das Erstellungsdatum des Abonnements.
	 * 
	 * @param result
	 *            ,Vektor mit Nutzer-Objekten
	 */
	public void erstelleKoerperDatenReport8(Vector<Nutzer> result) {

		if (result.size() <= 0) {
			buffer.append("<table class ='" + "report_kein_inhalt" + "'>");
			buffer.append("<tr> <td>FÜR DEN AUSGEWÄHLTEN NUTZER"
					+ " IST ZUR ZEIT KEIN REPORT 8 VERFÜGBAR, DA KEIN NUTZER DIESES"
					+ " HASHTAG ABONNIERT HAT!</td></tr>");
			buffer.append("</table>");
		} else {

			buffer.append("<table class='reportkoerper_vollbild'>");
			buffer.append("<tr class='kopfZeileKoerper'> <td>Nutzer</td><td>Erstellungsdatum</td></tr>");
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
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Report 8 *****************************************
	 * **********************************
	 */

}