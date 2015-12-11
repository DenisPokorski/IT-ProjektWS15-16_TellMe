package de.hdm.tellme.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.NutzerAbonnement;

@RemoteServiceRelativePath("reportservice")
public interface ReportService extends RemoteService {


	Vector<Nutzer> report2GenerierenListe(int i);

 
 
	 
}
