package de.hdm.tellme.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Unterhaltung;


@RemoteServiceRelativePath("editorservice")
public interface EditorService extends RemoteService {

	

	void nutzerAktualisieren(Nutzer n);

	void nutzerLoeschen(Nutzer n);

	void loescheNutzeraboById(int vonId, int nachId);

	Vector<Nutzer> getNochNichtAbonnenteNutzerListe(int meineId);

	Vector<Nutzer> getAlleNochNichtAbonnierteNutzerListe(int i);

	Vector<Nutzer> getZuAbonnieredeLoeschenNutzerListe(int i);

	void erstellenNutzeraboById(int vonId, int nachId);

	Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId);
	
	Vector<Hashtag> getZuAbonnierendeLoeschenHashtagAboListe(int meineId);

	void erstellenHashtagAboById(int NutzerId, int HashtagId);

	Vector<Unterhaltung> ladeAlleOeffentlichenUnterhaltungen();

	void NachrichtErstellenUnnterhaltungZuweisen(Nachricht n, int uid);

	void nachrichtUnterhaltungZuweisen(String txt, int uId, Timestamp ts);

	Vector<Nachricht> alleNachrichtenVonUnterhaltungListe(int uId);

	int UnterhaltungErstellen(Timestamp ts, String text);

	void NachrichtErstellen(Nachricht n);

	Vector<Hashtag> getAlleNochNichtAbonnierteHashtagAboListe(int id);

	Vector<Hashtag> gibHashtagListe();

	Vector<Integer> ladeAbonnierendeHashtagListe(int meineId);

	void hashtagAboErstellen(int nutzerId, int hashtagId);

	void hashtagEntfernen(Hashtag hashtag);

	void hashtagSpeichern(Hashtag hashtag);

	void hashtagAboEntfernen(int nutzerId, int hashtagId);

	void hashtagErstellen(Hashtag hashtag);

 
}
