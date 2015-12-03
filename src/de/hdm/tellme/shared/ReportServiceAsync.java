package de.hdm.tellme.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Nachricht;

public interface ReportServiceAsync {

	void report1GenerierenListe(AsyncCallback<Vector<Nachricht>> asyncCallback);

}
