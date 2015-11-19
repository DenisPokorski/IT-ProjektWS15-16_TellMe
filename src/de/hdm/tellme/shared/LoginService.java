package de.hdm.tellme.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;



@RemoteServiceRelativePath("loginservice")
public interface LoginService extends RemoteService {
	public LoginInfo getNutzerInfo(String uri);

}
