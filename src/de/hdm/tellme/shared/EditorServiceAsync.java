package de.hdm.tellme.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Nutzer;

public interface EditorServiceAsync {

	void getNutzerVonMailadresse(String eMailAdress, AsyncCallback<Nutzer> asyncCallback);

	void nutzerAktualisieren(Nutzer n, AsyncCallback<Void> asyncCallback);

	void nutzerLoeschen(Nutzer n, AsyncCallback<Void> asyncCallback);

}
