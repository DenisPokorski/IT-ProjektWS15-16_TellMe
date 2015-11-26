package de.hdm.tellme.server.db;



import com.google.cloud.sql.jdbc.Connection;
import com.google.gwt.dev.generator.ast.Statement;  // STIMMT DIE?????

import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.NutzerAbonnement;

public class NutzerAbonnementMapper {
	private static NutzerAbonnementMapper nutzerAbonnementMapper = null;
	
	protected NutzerAbonnementMapper() {
		
	}
	
	
public static NutzerAbonnementMapper nutzerAbonnementMapper()	{
	if (nutzerAbonnementMapper == null)	{
		nutzerAbonnementMapper = new NutzerAbonnementMapper();
	} return nutzerAbonnementMapper;
}

public void anlegen(NutzerAbonnement n) {
	Connection con = DatenbankVerbindung.connection();
	try {
		Statement state = con.createStatement();
		String sqlquery = "INSERT INTO NutzerAbonnement (" 
		
	}
}

public void aktualisieren(NutzerAbonnement n) {
	
}

public void entfernen(NutzerAbonnement n){
	
}

public void suchenMitAbonnementUndNutzer (Nutzer nutzer) {
	
}
/*
	private Nutzer aboNutzer;

	public Nutzer getAboNutzer() {
		return aboNutzer;
	}

	public void setAboNutzer(Nutzer aboNutzer) {
		this.aboNutzer = aboNutzer;
	}
	*/
	

}
