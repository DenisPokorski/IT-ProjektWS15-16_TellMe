package de.hdm.tellme.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;
import de.hdm.tellme.shared.bo.Unterhaltung.eUnterhaltungsTyp;

public interface EditorServiceAsync {

	void nutzerAktualisieren(Nutzer n, AsyncCallback<Void> asyncCallback);

	void nutzerLoeschen(Nutzer n, AsyncCallback<Void> asyncCallback);

 
	void getNochNichtAbonnenteNutzerListe(int meineId,
			AsyncCallback<Vector<Nutzer>> asyncCallback);

	void getAlleNochNichtAbonnierteNutzerListe(int i, AsyncCallback<Vector<Nutzer>> asyncCallback);

	void getZuAbonnieredeLoeschenNutzerListe(int i,
			AsyncCallback<Vector<Nutzer>> callback);


	void NachrichtErstellen(Nachricht n, AsyncCallback<Void> asyncCallback);

	void nachrichtUnterhaltungZuweisen(String txt, int uId, Timestamp ts,
			AsyncCallback<Void> asyncCallback);

	void getZuAbonnierendeLoeschenHashtagAboListe(int meineId,
			AsyncCallback<Vector<Hashtag>> callback);

	void erstellenHashtagAboById(int NutzerId, int HashtagId,
			AsyncCallback<Void> callback);

	void getAlleNochNichtAbonnierteHashtagAboListe(int id,
			AsyncCallback<Vector<Hashtag>> asyncCallback);

	void gibHashtagListe(AsyncCallback<Vector<Hashtag>> asyncCallback);

	void ladeAbonnierendeHashtagListe(int meineId,
			AsyncCallback<Vector<Integer>> asyncCallback);

	void hashtagAboErstellen(int nutzerId, int hashtagId,
			AsyncCallback<Void> asyncCallback);


	void getAlleNutzerAußerMeineId(int meineId,
			AsyncCallback<Vector<Nutzer>> callback);

	void holeAlleAbonniertenNutzer(int meineId,
			AsyncCallback<Vector<Integer>> callback);

	void nutzerAbonnementErstellen(int i, Nutzer _nutzer,
			AsyncCallback<Void> callback);

	void nutzerAbonnementLoeschen(int id, Nutzer _nutzerDeabonieren,
			AsyncCallback<Void> callback);

	void hashtagErstellen(Hashtag hashtag, AsyncCallback<Void> callback);

	void hashtagSpeichern(Hashtag hashtag, AsyncCallback<Void> callback);

	void hashtagEntfernen(Hashtag hashtag, AsyncCallback<Void> callback);

	void hashtagAboEntfernen(int nutzerId, int hashtagId, AsyncCallback<Void> asyncCallback);

	void oeffentlicheNachrichtenNachHashtag(int id,
			AsyncCallback<Unterhaltung> callback);

	void unterhaltung_anlegen(eUnterhaltungsTyp _unterhaltungsTyp, AsyncCallback<Integer> callback);

	void unterhaltung_loeschen(int unterhaltungsID, AsyncCallback<Boolean> callback);

	void alleUnterhaltungenFuerAktivenTeilnehmerOhneNachrichten(int teilnehmerID, AsyncCallback<Vector<Unterhaltung>> callback);

	 
 }
