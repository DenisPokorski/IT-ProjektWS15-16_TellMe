package de.hdm.tellme.shared;

import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.tellme.shared.bo.Hashtag;
import de.hdm.tellme.shared.bo.Nachricht;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Unterhaltung;

/**
 * Das asynchrone Gegenstück des Interface {@link EditorService}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link EditorService}.
 * 
 * @author Thies, denispokorski
 */

public interface EditorServiceAsync {

	void nutzerAktualisieren(Nutzer n, AsyncCallback<Void> asyncCallback);

	void nutzerLoeschen(Nutzer n, AsyncCallback<Void> asyncCallback);

	void getAlleAbonniertenHashtagsfuerAbonehmer(int meineId,AsyncCallback<Vector<Integer>> asyncCallback);

	void hashtagAboErstellen(int nutzerId, int hashtagId,AsyncCallback<Void> asyncCallback);

	void getAlleNutzerAußerMeineId(int meineId,AsyncCallback<Vector<Nutzer>> callback);

	void holeAlleAbonniertenNutzer(int meineId,AsyncCallback<Vector<Integer>> callback);

	void nutzerAbonnementErstellen(int i, Nutzer _nutzer,AsyncCallback<Void> callback);

	void nutzerAbonnementLoeschen(int id, Nutzer _nutzerDeabonieren,AsyncCallback<Void> callback);

	void hashtagErstellen(Hashtag hashtag, AsyncCallback<Void> callback);

	void hashtagEntfernen(Hashtag hashtag, AsyncCallback<Void> callback);

	void hashtagAboEntfernen(int nutzerId, int hashtagId,AsyncCallback<Void> asyncCallback);

	void unterhaltung_loeschen(int unterhaltungsID,AsyncCallback<Boolean> callback);

	void unterhaltungStarten(Nachricht ersteNachricht,Vector<Nutzer> teilnehmer, AsyncCallback<Boolean> callback);

	void ladeAlleNachrichtenZuUnterhaltung(int UnterhaltungsID,AsyncCallback<Vector<Nachricht>> callback);

	void oeffentlicheUnterhaltungenAbonnierterNutzer(int meineId,AsyncCallback<Vector<Unterhaltung>> callback);

	void alleUnterhaltungenVonAbonniertemHashtagUeberNutzerId(int nutzerId,AsyncCallback<Vector<Unterhaltung>> callback);

	void NachrichtLoeschen(Nachricht n, AsyncCallback<Boolean> callback);

	void UnterhaltungVerlassen(Unterhaltung u, int nutzerId,AsyncCallback<Boolean> callback);

	void getAlleRelevantenUnterhaltungen(int UserID,AsyncCallback<Vector<Unterhaltung>> callback);

	void gibAlleHashtags(AsyncCallback<Vector<Hashtag>> callback);

	void hashtagAktualisieren(Hashtag hashtag, AsyncCallback<Void> callback);

	void erstellenHashtagAbo(int NutzerId, int HashtagId,AsyncCallback<Void> callback);

	void getAbonnierteHashtags(int aboNehmerID,AsyncCallback<Vector<Hashtag>> callback);

	void unterhaltungBeantworten(Nachricht antwortNachricht,Unterhaltung unterhaltung, AsyncCallback<Boolean> callback);

	void getAlleNutzer(boolean zwingeNeuladen,AsyncCallback<Vector<Nutzer>> callback);

	void UnterhaltungAktualisieren(Unterhaltung original, Unterhaltung neu,AsyncCallback<Boolean> callback);

	void NachrichtAktualisieren(Nachricht original, Nachricht neu,AsyncCallback<Boolean> callback);
}
