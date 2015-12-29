package de.hdm.tellme.shared;

import java.io.Serializable;

import de.hdm.tellme.shared.bo.Nutzer;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = -5207880593956618550L;
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String emailAddress;
	private String googleId;
	private Nutzer user;
	

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Nutzer getUser() {
		return user;
	}

	public void setUser(Nutzer user) {
		this.user = user;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String string) {
		this.googleId = string;
	}
}
