package de.hdm.tellme.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.tellme.shared.bo.Nutzer;

@RemoteServiceRelativePath("editorservice")
public interface EditorService extends RemoteService {

	Nutzer getNutzerVonMailadresse(String eMailAdress);

	void nutzerAktualisieren(Nutzer n);

	void nutzerLoeschen(Nutzer n);

}
