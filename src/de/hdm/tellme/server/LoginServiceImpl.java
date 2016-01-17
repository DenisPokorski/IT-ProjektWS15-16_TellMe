package de.hdm.tellme.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.LoginService;
import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.Nutzer.eStatus;

/**
 * Die Klasse <class>LoginServiceImpl</class> implementiert das Interface
 * <code>LoginService</code>. Die Realisierung für ein GWT RPC erfolgt analog zu @
 * link EditorServiceImpl} . Für Details muss man es dort überprüfen.
 * 
 * @author Feltrin, Zimmermann
 * @version 1.0
 * @since 26.11.2015
 * 
 */

public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	private String uri_;

	public LoginServiceImpl() throws IllegalArgumentException {

		uri_ = "";

	}

	private void setURI() {
		String URIs = "";
		StringBuffer requestURL = this.perThreadRequest.get().getRequestURL();
		String queryString = this.perThreadRequest.get().getQueryString();
		if (queryString == null) {
			URIs = requestURL.toString();

			int amountServerletPathChar = this.perThreadRequest.get()
					.getServletPath().length();
			int amountURISPath = URIs.length();
			uri_ = URIs
					.substring(0, amountURISPath - (amountServerletPathChar));
		} else {
			uri_ = requestURL.append('?').append(queryString).toString();
		}
	}

	private static final long serialVersionUID = -1L;

	/**
	 * Die Email-Adresse wird durch den GoogleUserService bezogen.
	 * 
	 */
	@Override
	public LoginInfo getNutzerInfo() {

		UserService userService = UserServiceFactory.getUserService();
		User nutzer = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();

		setURI();

		if (nutzer != null) {
			NutzerMapper nMapper = NutzerMapper.nutzerMapper();

			loginInfo.setLoggedIn(true);
			loginInfo.setEmailAddress(nutzer.getEmail());
			loginInfo.setLogoutUrl(userService.createLogoutURL(uri_));

			Nutzer n = nMapper.suchenMitEmailAdresse(loginInfo
					.getEmailAddress());

			if (n.getMailadresse() != null) {
				if (n.getStatus() != eStatus.inaktiv) {
					loginInfo.setUser(n);
				} else {
					nMapper.setzeNutzerAktiv(n.getId());
					loginInfo.setUser(n);
				}
			}

			else {
				Nutzer na = new Nutzer();
				na.setMailadresse(loginInfo.getEmailAddress());
				na.setVorname("undefined");
				na.setNachname("undefined");
				na.setId(nMapper.anlegen(na));

				loginInfo.setUser(na);
			}

		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(uri_));
		}
		return loginInfo;

	}
}
