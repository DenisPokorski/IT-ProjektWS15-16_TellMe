package de.hdm.tellme.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Nutzer;

public interface EditorServiceAsync {

	void getNutzerVonMailadresse(String eMailAdress, AsyncCallback<Nutzer> asyncCallback);

	void nutzerAktualisieren(Nutzer n, AsyncCallback<Void> asyncCallback);

	void nutzerLoeschen(Nutzer n, AsyncCallback<Void> asyncCallback);

 
 

	void loescheNutzeraboById(int vonId, int nachId,
			AsyncCallback<Void> asyncCallback);

	void getNochNichtAbonnenteNutzerListe(int meineId,
			AsyncCallback<Vector<Nutzer>> asyncCallback);

	void getAlleNochNichtAbonnierteNutzerListe(int i, AsyncCallback<Vector<Nutzer>> asyncCallback);

	void getZuAbonnieredeLoeschenNutzerListe(int i,
			AsyncCallback<Vector<Nutzer>> callback);

	void erstellenNutzeraboById(int vonId, int nachId,
			AsyncCallback<Void> asyncCallback);

	void getAlleNutzerAußerMeineId(int meineId,
			AsyncCallback<Vector<Nutzer>> asyncCallback);
}
