package de.hdm.tellme.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.tellme.server.db.NutzerMapper;
import de.hdm.tellme.shared.EditorService;
import de.hdm.tellme.shared.bo.*;

@SuppressWarnings("serial")
public class EditorServiceImpl extends RemoteServiceServlet implements
		EditorService {
	private NutzerMapper nMapper = null;

	public void nutzerAnlegen(Nutzer na) {
		Nutzer n = new Nutzer();
		n.setGoogleId(na.getGoogleId());
		n.setVorname(na.getVorname());
		n.setNachname(na.getNachname());
		n.setMailadresse(na.getMailadresse());

		nMapper.anlegen(n);

	}

	public void nutzerAktualisieren(Nutzer na) {
		Nutzer n = new Nutzer();
		n.setGoogleId(na.getGoogleId());
		n.setVorname(na.getVorname());
		n.setNachname(na.getNachname());
		n.setMailadresse(na.getMailadresse());

		nMapper.aktualisieren(n);
	}

	public void nutzerLoeschen(Nutzer na) {
		Nutzer n = new Nutzer();
		n.setId(na.getId());

		nMapper.entfernen(n);
	}

	public Nutzer getNutzerVonId(Nutzer na) {
		Nutzer n = new Nutzer();
		n = nMapper.suchenNutzerMitId(n);
		return n;

	}

	public Nutzer getNutzerVonMailadresse(String eMailAdress) {
		Nutzer n = new Nutzer();
		n = nMapper.suchenNutzerIdMitMailadresse(eMailAdress);
		return n;
	}

}
