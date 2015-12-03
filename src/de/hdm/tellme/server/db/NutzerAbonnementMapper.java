
package de.hdm.tellme.server.db;


import java.sql.Connection;
import java.sql.ResultSet;
 import java.sql.Statement;
 import java.sql.SQLException;

import java.util.Vector;

  




import de.hdm.tellme.shared.bo.Nutzer;
import de.hdm.tellme.shared.bo.NutzerAbonnement;

public class NutzerAbonnementMapper {
 private static NutzerAbonnementMapper nutzerAbonnementMapper = null;
 
 protected NutzerAbonnementMapper() {
  
 }
 
 
public static NutzerAbonnementMapper nutzerAbonnementMapper() {
 if (nutzerAbonnementMapper == null) {
  nutzerAbonnementMapper = new NutzerAbonnementMapper();
 } return nutzerAbonnementMapper;
}

//public void anlegen(NutzerAbonnement n) {
// Connection con = DatenbankVerbindung.connection();
// try {
//  Statement state = con.createStatement();
//  String sqlquery = "INSERT INTO NutzerAbonnement (VonId, NachId) VALUES (" + n.getAboNutzer().getId() +"'," +  n.getAboNutzer().getId() +");";
//
//  
// }
//}
//
//public void aktualisieren(NutzerAbonnement n) {
// Connection state = con.createStatement();
// try {
//  Statement state = con.createStatement();
//  String sqlquery = "UPDATE"
//    
//  state.executeUpdate(sqlquery);
// } catch (Execption e) {
//  e.printStackTrace();
// }
// 
//}
//
//public void entfernen(NutzerAbonnement n){
// Connection con = DatenbankVerbindung.connection();
// try {
//  Statement state = con.createStatement();
//  String sqlquery = "DELETE FROM AbonnentBenutzer WHERE Id=' " + n.getId() + "';";
//  
//  state.executeUpdate(sqlquery);
//  
// } catch (Execption e) {
//  e.printStackTrace();
// }
//}

public  Vector <Nutzer>ladeZuAbonnierendeNutzerListe (int nutzer) {
 Connection con = DatenbankVerbindung.connection();
 
 Vector<Nutzer> result = new Vector<Nutzer>();
 
 try {
  Statement state = con.createStatement();
	ResultSet rs = state.executeQuery("SELECT AbonnentBenutzer.NachId, Nutzer.Id, Nutzer.Vorname, Nutzer.Nachname, Nutzer.Mailadresse FROM AbonnentBenutzer LEFT JOIN Nutzer ON AbonnentBenutzer.NachId = Nutzer.Id Where AbonnentBenutzer.NachId = '"+nutzer+ "';");
			 
	while (rs.next()) {
		Nutzer na = new Nutzer();
		na.setId(rs.getInt("Id"));
		na.setVorname(rs.getString("Vorname"));
		na.setNachname(rs.getString("Nachname"));
		na.setMailadresse(rs.getString("Mailadresse"));
		result.add(na);
		}
 	} 
 
 catch (Exception e) {
  e.printStackTrace();
 	}
 
 return result; //result ungl�cklich formuliert!!! -.-** 
}
 
  
 


public Vector<NutzerAbonnement> ladeNutzerAboListe(int n) {
	// TODO Auto-generated method stub
	return null;
}
 

}