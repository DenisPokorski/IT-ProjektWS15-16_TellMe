package de.hdm.tellme.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.client.gui.report.Report2Gui;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nutzer;

public interface ReportServiceAsync {
	void report1_1Generieren(int nutzerId, Timestamp vonDatum,
			Timestamp bisDatum, AsyncCallback<Vector<Nachricht>> callback);
	
	void report1_2Generieren(Timestamp vonDatum, Timestamp bisDatum, AsyncCallback<Vector<Nachricht>> asyncCallback);
	
	void report1_3Generieren(int i, AsyncCallback<Vector<Nachricht>> asyncCallback);
	
	void report1_4Generieren(AsyncCallback<Vector<Nachricht>> asyncCallback);
 	void report2GenerierenListe(int i, AsyncCallback<Vector<Nutzer>> callback);
 	
	void report3Generieren(int i, AsyncCallback<Vector<Hashtag>> asyncCallback);
	
	
}