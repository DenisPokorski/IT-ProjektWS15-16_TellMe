package de.hdm.tellme.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Unterhaltung;
import de.hdm.tellme.shared.bo.Unterhaltung.eUnterhaltungsTyp;

@RemoteServiceRelativePath("editorservice")
public interface EditorService extends RemoteService {

	void nutzerAktualisieren(Nutzer n);

	void nutzerLoeschen(Nutzer n);


	Vector<Nutzer> getNochNichtAbonnenteNutzerListe(int meineId);

	Vector<Nutzer> getAlleNochNichtAbonnierteNutzerListe(int i);

	Vector<Nutzer> getZuAbonnieredeLoeschenNutzerListe(int i);


	Vector<Nutzer> getAlleNutzerAußerMeineId(int meineId);

	Vector<Hashtag> getZuAbonnierendeLoeschenHashtagAboListe(int meineId);

	void erstellenHashtagAboById(int NutzerId, int HashtagId);

	void nachrichtUnterhaltungZuweisen(String txt, int uId, Timestamp ts);

	void NachrichtErstellen(Nachricht n);

	Vector<Hashtag> getAlleNochNichtAbonnierteHashtagAboListe(int id);

	Vector<Hashtag> gibHashtagListe();

	Vector<Integer> ladeAbonnierendeHashtagListe(int meineId);

	void hashtagAboErstellen(int nutzerId, int hashtagId);

	void nutzerAbonnementErstellen(int i, Nutzer _nutzer);

	Vector<Integer> holeAlleAbonniertenNutzer(int meineId);

	void nutzerAbonnementLoeschen(int id, Nutzer _nutzerDeabonieren);

	void hashtagSpeichern(Hashtag hashtag);

	void hashtagErstellen(Hashtag hashtag);

	void hashtagEntfernen(Hashtag hashtag);

	void hashtagAboEntfernen(int nutzerId, int hashtagId);

	Unterhaltung oeffentlicheNachrichtenNachHashtag(int id);

	boolean unterhaltung_loeschen(int unterhaltungsID);

	Vector<Unterhaltung> alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(int teilnehmerID);

	boolean unterhaltungStarten(eUnterhaltungsTyp unterhaltungsTyp, Nachricht ersteNachricht, Vector<Nutzer> teilnehmer);

}
