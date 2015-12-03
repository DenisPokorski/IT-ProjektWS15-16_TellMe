package de.hdm.tellme.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Nutzer;

public interface EditorServiceAsync {

	void getNutzerVonMailadresse(String eMailAdress, AsyncCallback<Nutzer> asyncCallback);

	void nutzerAktualisieren(Nutzer n, AsyncCallback<Void> asyncCallback);

	void nutzerLoeschen(Nutzer n, AsyncCallback<Void> asyncCallback);

 
	void getZuAbonnieredeNutzerListe(int i, AsyncCallback<Vector<Nutzer>> asyncCallback);

	void getZuAbonnieredeLoeschenNutzerListe(int n,
			AsyncCallback<Vector<Nutzer>> callback);

}
