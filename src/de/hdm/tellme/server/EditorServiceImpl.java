package de.hdm.tellme.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.shared.EditorService;

@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements
		EditorService {
	private NutzerMapper nMapper = null;

}
