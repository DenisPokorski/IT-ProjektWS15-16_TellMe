package de.hdm.tellme.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Die Klasse <class>LoginService</class> ist ein synchrones Interface für den Login Service Editor
 *
 * @author denispokorski
 *
 */

@RemoteServiceRelativePath("loginservice")
public interface LoginService extends RemoteService {
	LoginInfo getNutzerInfo(String string);

}
