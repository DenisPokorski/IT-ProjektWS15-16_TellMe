package de.hdm.tellme.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.tellme.shared.LoginService;


/**
 * Das asynchrone Gegenstück des Interface {@link LoginService}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link LoginService}.
 * 
 * @author Thies, denispokorski
 */

public interface LoginServiceAsync {

	void getNutzerInfo(String string, AsyncCallback<LoginInfo> callback);

}
