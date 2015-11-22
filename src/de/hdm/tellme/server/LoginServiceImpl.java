package de.hdm.tellme.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.LoginService;
import de.hdm.tellme.shared.bo.Nutzer;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	private NutzerMapper nMapper = null;

	private static final long serialVersionUID = -1682217411238580183L;

	@Override
	public LoginInfo getNutzerInfo(String uri) {

		UserService userService = UserServiceFactory.getUserService();
		User nutzer = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();

		if (nutzer != null) {

			loginInfo.setLoggedIn(true);
			loginInfo.setEmailAddress(nutzer.getEmail());
			loginInfo.setLogoutUrl(userService.createLogoutURL(uri));

			Nutzer n = nMapper.suchenNutzerIdMitMailadresse(loginInfo
					.getEmailAddress());
			if (n != null) {
				loginInfo.setUser(n);
			}

			else {
				Nutzer na = new Nutzer();
				na.setMailadresse(loginInfo.getEmailAddress());
				nMapper.anlegen(na);
				loginInfo.setUser(na);
			}

		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(uri));
		}
		return loginInfo;

	}
}
