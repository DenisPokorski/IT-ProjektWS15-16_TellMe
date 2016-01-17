package de.hdm.tellme.shared;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * 
 * Das ist das asynchrone Gegenstück des Interface zum {@link ReportService}.
 * Das wird teil-automatisch durch das Google-Plugin erstellt und gewartet.
 * Deswegen keine weitere Dokumentation.
 *
 *
 */
public interface ReportServiceAsync {
	void report5GenerierenListe(int i, AsyncCallback<Vector<Nutzer>> callback);

	void report6Generieren(int i, AsyncCallback<Vector<Hashtag>> asyncCallback);

	void getAlleNutzer(boolean zwingeNeuladen,
			AsyncCallback<Vector<Nutzer>> callback);

	void ladeAlleNachrichtenZuUnterhaltung(int UnterhaltungsID,
			AsyncCallback<Vector<Nachricht>> callback);

	void alleUnterhaltungenFuerAutor(int aktiverTeilnehmerID,
			AsyncCallback<Vector<Unterhaltung>> callback);

	void alleUnterhaltungenFuerAutorMitZeitraum(int AutorId, Timestamp vonDate,
			Timestamp bisDate, AsyncCallback<Vector<Unterhaltung>> callback);

	void ladeAlleNachrichtenZuUnterhaltungMitZeitraum(int UnterhaltungsID,
			Timestamp vonDate, Timestamp bisDate,
			AsyncCallback<Vector<Nachricht>> callback);

	void alleUnterhaltungenMitZeitraum(Timestamp vonDate, Timestamp bisDate,
			AsyncCallback<Vector<Unterhaltung>> callback);

	void alleUnterhaltungen(AsyncCallback<Vector<Unterhaltung>> asyncCallback);


}