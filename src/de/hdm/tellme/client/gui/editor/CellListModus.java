package de.hdm.tellme.client.gui.editor;

/**
 * Die Klasse <code>CellListModus<T></code> ist eine Klasse, die die jeweiligen
 * Modi der Klasse<code>NutzerCellList<T></code> und der Klasse
 * <class>HashtagCellList</class> aufzählt, um die verschiedenen Verwendungen
 * der CellLists darzustellen. Jeder der aufgezählten Elemente ist ein Case,
 * also ein Anwendungsfall, für die verschiedenen Verwendungen der
 * <class>NutzerCellList</class> und der Klasse <class>HashtagCellList</class>.
 * 
 * @author Alex
 *
 */
public enum CellListModus {
	Einstellungen, HashtagVerwaltung, Nachrichtenuebersicht, Report1_NachrichtNutzerZeitraum, Report3_NachrichtNutzer, Report5_NutzerNutzerAbonnement, Report6_NutzerHashtagAbonnement, Report7_AlleNutzerDieDemAusgewaehltenNutzerFolgen;
}
