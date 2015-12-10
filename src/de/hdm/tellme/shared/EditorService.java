package de.hdm.tellme.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Hashtag;


@RemoteServiceRelativePath("editorservice")
public interface EditorService extends RemoteService {

	Nutzer getNutzerVonMailadresse(String eMailAdress);

	void nutzerAktualisieren(Nutzer n);

	void nutzerLoeschen(Nutzer n);

	void loescheNutzeraboById(int vonId, int nachId);

	Vector<Nutzer> getNochNichtAbonnenteNutzerListe(int meineId);

	Vector<Nutzer> getAlleNochNichtAbonnierteNutzerListe(int i);

	Vector<Nutzer> getZuAbonnieredeLoeschenNutzerListe(int i);

	void erstellenNutzeraboById(int vonId, int nachId);

	Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId);
	

	Vector<Hashtag> getZuAbonnierendeLoeschenHashtagAboListe(int meineId);

}
