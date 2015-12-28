package de.hdm.tellme.shared.bo;

import java.util.HashMap;
import java.util.Vector;

public class Unterhaltung extends BusinessObject {

 /**
  * 
  */
 private static final long serialVersionUID = 1L;
 private Vector<Nachricht> alleNachrichten = new Vector<Nachricht>();

 private Vector<Nutzer> teilnehmer = new Vector<Nutzer>();
 
 private eUnterhaltungsTyp Unterhaltungstyp;
 
 
 public enum eUnterhaltungsTyp {
  privat, oeffentlich;
 }

 public Vector<Nachricht> getAlleNachrichten() {
  return alleNachrichten;
 }

 public void setAlleNachrichten(Vector<Nachricht> alleNachrichten) {
  this.alleNachrichten = alleNachrichten;
 }

 public Vector<Nutzer> getTeilnehmer() {
  return teilnehmer;
 }

 public void setTeilnehmer(Vector<Nutzer> teilnehmer) {
  this.teilnehmer = teilnehmer;
 }

 public eUnterhaltungsTyp getUnterhaltungstyp() {
  return Unterhaltungstyp;
 }

 public void setUnterhaltungstyp(eUnterhaltungsTyp unterhaltungstyp) {
  Unterhaltungstyp = unterhaltungstyp;
 }

 

}