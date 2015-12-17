package de.hdm.tellme.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

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

	void NachrichtErstellen(Nachricht n);

	int UnterhaltungErstellen(Timestamp ts, String text);

 
	Vector<Nachricht> alleNachrichtenVonUnterhaltungListe(int uId);

	void nachrichtUnterhaltungZuweisen(String txt, int uId, Timestamp ts);

	void NachrichtErstellenUnnterhaltungZuweisen(Nachricht n, int uid);

	Vector<Unterhaltung> ladeAlleOeffentlichenUnterhaltungen();

	  Vector<Hashtag> getZuAbonnierendeLoeschenHashtagAboListe(int meineId);

	  void erstellenHashtagAboById(int NutzerId, int HashtagId);

	  Vector<Nutzer> getAlleNochNichtAbonnierteHashtagAboListe(int id);
	
}
