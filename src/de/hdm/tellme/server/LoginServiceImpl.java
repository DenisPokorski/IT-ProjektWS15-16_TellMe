package de.hdm.tellme.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.shared.LoginInfo;
import de.hdm.tellme.shared.LoginService;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	@Override
	public LoginInfo getNutzerInfo(String uri) {
		LoginInfo loginInfo = new LoginInfo();
		return loginInfo;
	}

}
