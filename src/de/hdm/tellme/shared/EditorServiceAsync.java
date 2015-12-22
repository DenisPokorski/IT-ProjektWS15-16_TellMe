package de.hdm.tellme.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

public interface EditorServiceAsync {

	void nutzerAktualisieren(Nutzer n, AsyncCallback<Void> asyncCallback);

	void nutzerLoeschen(Nutzer n, AsyncCallback<Void> asyncCallback);

 
	void getNochNichtAbonnenteNutzerListe(int meineId,
			AsyncCallback<Vector<Nutzer>> asyncCallback);

	void getAlleNochNichtAbonnierteNutzerListe(int i, AsyncCallback<Vector<Nutzer>> asyncCallback);

	void getZuAbonnieredeLoeschenNutzerListe(int i,
			AsyncCallback<Vector<Nutzer>> callback);


	void NachrichtErstellen(Nachricht n, AsyncCallback<Void> asyncCallback);

	void UnterhaltungErstellen(Timestamp ts, String text,
			AsyncCallback<Integer> asyncCallback);

	void alleNachrichtenVonUnterhaltungListe(int uId,
			AsyncCallback<Vector<Nachricht>> asyncCallback);

	void nachrichtUnterhaltungZuweisen(String txt, int uId, Timestamp ts,
			AsyncCallback<Void> asyncCallback);

 
	void NachrichtErstellenUnnterhaltungZuweisen(Nachricht n,
			int uid, AsyncCallback<Void> asyncCallback);

	void ladeAlleOeffentlichenUnterhaltungen(
			AsyncCallback<Vector<Unterhaltung>> asyncCallback);

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

	void hashtagEntfernen(int nutzerId, int hashtagId,
			AsyncCallback<Void> asyncCallback);

	void getAlleNutzerAußerMeineId(int meineId,
			AsyncCallback<Vector<Nutzer>> callback);

	void holeAlleAbonniertenNutzer(int meineId,
			AsyncCallback<Vector<Integer>> callback);

	void nutzerAbonnementErstellen(int i, Nutzer _nutzer,
			AsyncCallback<Void> callback);

	void nutzerAbonnementLoeschen(int id, Nutzer _nutzerDeabonieren,
			AsyncCallback<Void> callback);

	 
 }
