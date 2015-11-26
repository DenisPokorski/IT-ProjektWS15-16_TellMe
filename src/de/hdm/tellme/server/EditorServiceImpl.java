package de.hdm.tellme.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.bo.*;

@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements
		EditorService {
	public EditorServiceImpl() throws IllegalArgumentException {

	}

	public void init() throws IllegalArgumentException {
		this.nMapper = NutzerMapper.nutzermapper();
	}

	private NutzerMapper nMapper = null;

	public void nutzerAnlegen(Nutzer na) {
		Nutzer n = new Nutzer();
		n.setVorname(na.getVorname());
		n.setNachname(na.getNachname());
		n.setMailadresse(na.getMailadresse());

		nMapper.anlegen(n);

	}

	public void nutzerAktualisieren(Nutzer n) {

		nMapper.aktualisieren(n);
	}

	public void nutzerLoeschen(Nutzer n) {

		nMapper.entfernen(n);
	}

	public Nutzer getNutzerVonId(Nutzer na) {
		Nutzer n = new Nutzer();
		n = nMapper.suchenNutzerMitId(n);
		return n;

	}

	public Nutzer getNutzerVonMailadresse(String eMailAdress) {
		init();
		Nutzer n = new Nutzer();
		n = nMapper.suchenNutzerIdMitMailadresse(eMailAdress);
		return n;
	}

}
