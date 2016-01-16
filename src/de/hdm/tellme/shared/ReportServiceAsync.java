package de.hdm.tellme.shared;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;

/**
 * 
 * Das ist das asynchrone Gegenstück des Interface zum {@link ReportService}.
 * Das wird teil-automatisch durch das Google-Plugin erstellt und gewartet.
 * Deswegen keine weitere Dokumentation.
 *
 *
 */
public interface ReportServiceAsync {
	void report1Generieren(int nutzerId, Date vonDatum, Date bisDatum,
			AsyncCallback<Vector<Nachricht>> asyncCallback);

	void report2Generieren(Timestamp vonDatum, Timestamp bisDatum,
			AsyncCallback<Vector<Nachricht>> asyncCallback);

	void report3Generieren(int i, AsyncCallback<Vector<Nachricht>> asyncCallback);

	void report4Generieren(AsyncCallback<Vector<Nachricht>> asyncCallback);

	void report5GenerierenListe(int i, AsyncCallback<Vector<Nutzer>> callback);

	void report6Generieren(int i, AsyncCallback<Vector<Hashtag>> asyncCallback);

}