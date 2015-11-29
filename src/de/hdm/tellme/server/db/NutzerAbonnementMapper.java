
package de.hdm.tellme.server.db;


import java.util.Vector;

import com.google.cloud.sql.jdbc.Connection;
import com.google.gwt.dev.generator.ast.Statement;  // STIMMT DIE?????

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

public void anlegen(NutzerAbonnement n) {
 Connection con = DatenbankVerbindung.connection();
 try {
  Statement state = con.createStatement();
  String sqlquery = "INSERT INTO NutzerAbonnement (VonId, NachId) VALUES (" + n.getAboNutzer().getId() +"'," +  n.getAboNutzer().getId() +");";

  
 }
}

public void aktualisieren(NutzerAbonnement n) {
 Connection state = con.createStatement();
 try {
  Statement state = con.createStatement();
  String sqlquery = "UPDATE"
    
  state.executeUpdate(sqlquery);
 } catch (Execption e) {
  e.printStackTrace();
 }
 
}

public void entfernen(NutzerAbonnement n){
 Connection con = DatenbankVerbindung.connection();
 try {
  Statement state = con.createStatement();
  String sqlquery = "DELETE FROM AbonnentBenutzer WHERE Id=' " + n.getId() + "';";
  
  state.executeUpdate(sqlquery);
  
 } catch (Execption e) {
  e.printStackTrace();
 }
}

public  Vector <NutzerAbonnement>suchenMitAbonnementUndNutzer (Nutzer nutzer) {
 Connection con = DatenbankVerbindung.connection();
 
 Vector<NutzerAbonnement> result = new Vector<NutzerAbonnement>();
 
 try {
  Statement state = con.createStatement();
  String sqlquery = ""
    
  state.executeUpdate(sqlquery);
 } catch (Execption e) {
  e.printStackTrace();
 }
 
 return result; //result unglücklich formuliert!!! -.-** 
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