package de.hdm.tellme.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;

@RemoteServiceRelativePath("reportservice")
public interface ReportService extends RemoteService {

	Vector<Nachricht> report1GenerierenListe();

	 
}
