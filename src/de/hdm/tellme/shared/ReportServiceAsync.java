package de.hdm.tellme.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.client.gui.report.Report2;
import de.hdm.tellme.shared.bo.Nutzer;

public interface ReportServiceAsync {

	void report2GenerierenListe(Nutzer n,
			AsyncCallback<Report2> callback);
	
}
