package de.hdm.tellme.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.NutzerAbonnement;

public interface ReportServiceAsync {

	void report1GenerierenListe(AsyncCallback<Vector<Nachricht>> asyncCallback);

	void report2GenerierenListe(Nutzer n,
			AsyncCallback<Vector<Nutzer>> asyncCallback);

 

}