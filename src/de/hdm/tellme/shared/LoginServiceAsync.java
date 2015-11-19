package de.hdm.tellme.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void getNutzerInfo(String uri, AsyncCallback<LoginInfo> callback);

}
